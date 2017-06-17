package com.lethe_river.morelambda.throwable;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.lethe_river.morelambda.algebra.Union2;

/**
 * 結果を返すことのできるオブジェクトのクラス
 * 結果は呼出しごとに変化するかもしれないし，例外を発生させるかもしれない．
 *
 * @author Yuya
 *
 * @param <T> 結果の型
 * @param <E> 例外の型
 */
@FunctionalInterface
public interface ThrowableSupplier<T, E extends Exception> {
	/**
	 * 検査例外を発生させる結果を返すオブジェクトを指定し，実行時例外にラップしたSupplierを生成する．
	 * @param s 検査例外を発生させる結果を返すオブジェクト
	 * @param <T> 結果の型
	 * @param <E> 例外の型
	 * @return Supplier
	 */
	public static <T> Supplier<T> unchecked(ThrowableSupplier<T, ?> s) {
		return s.unchecked();
	}

	/**
	 * 検査例外を発生させる結果を返すオブジェクトと，発生した例外を戻り値に変換する関数を指定し，例外を発生させないSupplierを生成する．
	 * @param s 検査例外を発生させる結果を返すオブジェクト
	 * @param c 発生した例外とその時の引数から戻り値を生成する関数
	 * @param <T> 結果の型
	 * @param <E> 例外の型
	 * @return Supplier
	 */
	public static <T, E extends Exception> Supplier<T> complement(ThrowableSupplier<T, E> s, Function<E ,T> c) {
		return s.complement(c);
	}

	/**
	 * 検査例外を発生させる結果を返すオブジェクトを指定し，結果または例外を返す結果を返すSupplierを生成する．
	 * @param s 検査例外を発生させる関数
	 * @param <T> 結果の型
	 * @param <E> 例外の型
	 * @return 結果または例外を返すSupplier
	 */
	public static <T, E extends Exception> Supplier<Union2<T, E>> includesToValue(ThrowableSupplier<T, E> s) {
		return s.includesToValue();
	}

	/**
	 * 検査例外を発生させる結果を返すオブジェクトを指定し，結果をOptional(例外発生時はempty)として返すSupplierを生成する．
	 * @param s 検査例外を発生させる関数
	 * @param <T> 結果の型
	 * @param <E> 例外の型
	 * @return 結果をOptionalとして返すSupplier
	 */
	public static <T, E extends Exception> Supplier<Optional<T>> maybe(ThrowableSupplier<T, E> s) {
		return s.maybe();
	}

	/**
	 * 検査例外を発生させる結果を返すオブジェクトを指定し，結果をStreamとして返すSupplierを生成する．例外発生時Streamは空となる.
	 * @param s 検査例外を発生させる結果を返すオブジェクト
	 * @param <T> 結果の型
	 * @param <E> 例外の型
	 * @return 結果をStreamとして返すSupplier
	 */
	public static <T, E extends Exception> Supplier<Stream<T>> ignored(ThrowableSupplier<T, E> s) {
		return () -> s.includesToValue().get()
				.match(
						t -> Stream.of(t),
						e -> Stream.empty());
	}

	/**
	 * 結果を返す．検査例外が発生する可能性がある.
	 *
	 * @return 結果
	 * @throws E 発生するかもしれない例外
	 */
	public T get() throws E;

	/**
	 * 例外が発生したときの結果を与える関数を指定し，例外なしに結果を返すSupplierを生成する.
	 *
	 * @param complementer 発生した例外から戻り値を生成する関数
	 * @return 検査例外を発生させないオブジェクト
	 */
	public default Supplier<T> complement(Function<? super E, ? extends T> complementer) {
		return () -> {
			try {
				return get();
			} catch (RuntimeException | Error e) {
				throw e;
			} catch (Exception e) {
				return complementer.apply((E) e);
			}
		};
	}

	/**
	 * 発生した例外を実行時例外にラップするSupplierを返す．
	 * @return 検査例外を発生させないオブジェクト
	 */
	public default Supplier<T> unchecked() {
		return () -> {
			try {
				return get();
			} catch (RuntimeException | Error e) {
				throw e;
			} catch (Exception e) {
				if(e instanceof IOException) {
					throw new UncheckedIOException((IOException) e);
				}
				throw new RuntimeException(e);
			}
		};
	}

	/**
	 * 結果か，発生した例外のどちらかを戻り値とするSupplierを返す.
	 * @return 検査例外を発生させない関数
	 */
	public default Supplier<Union2<T, E>> includesToValue() {
		return () -> {
			try {
				return Union2.of1(get());
			} catch (RuntimeException | Error e) {
				throw e;
			} catch (Exception e) {
				return Union2.of2(((E) e));
			}
		};
	}

	/**
	 * 結果をOptionalでラップするSupplierを返す．例外が発生した場合はempty.
	 * @return 検査例外を発生させない関数
	 */
	public default Supplier<Optional<T>> maybe() {
		return () -> {
			try {
				return Optional.ofNullable(get());
			} catch (RuntimeException | Error e) {
				throw e;
			} catch (Exception e) {
				return Optional.empty();
			}
		};
	}
}
