package com.lethe_river.morelambda.throwable;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * 検査例外を伴う操作をラムダ式で扱うためのインターフェース.
 * Throwableをgenericに出来ないのと，generic型はcatch出来ないので今のところEに意味はない．
 * 引数や戻り値を通して不完全ながら利用できるかも．
 * 非検査例外はうまく扱えない
 * @author YuyaAizawa
 *
 * @param <T> 処理の引数の型
 * @param <E> 発生する可能性のある検査例外の型
 */
public interface ThrowableConsumer<T, E extends Throwable> {

	/**
	 * 検査例外が発生する可能性のある操作と例外の処理方法を指定し，それらを合成した操作を返す.
	 * @param o 検査例外が発生する可能性のある操作
	 * @param handler 発生した例外とその時の引数を消費する操作
	 * @param <T> 消費する要素の型
	 * @param <E> 発生する可能性のある検査例外の型
	 * @return 検査例外を発生させない操作
	 */
	public static <T, E extends Exception> Consumer<T> handling(ThrowableConsumer<T, E> o, BiConsumer<E, T> handler) {
		return o.handling(handler);
	}

	/**
	 * 検査例外が発生する可能性のある操作を指定し，例外発生時にラップした実行時例外を発生させる操作を返す.
	 * @param o 検査例外が発生する可能性のある操作
	 * @param <T> 消費する要素の型
	 * @return 検査例外を発生させない操作
	 */
	public static <T> Consumer<T> unchecked(ThrowableConsumer<T, ?> o) {
		return o.unchecked();
	}

	/**
	 * 検査例外が発生する可能性のある操作を指定し，例外発生時にこれを無視する操作を返す.
	 * @param o 検査例外が発生する可能性のある操作
	 * @param <T> 消費する要素の型
	 * @return 検査例外を発生させない操作
	 */
	public static <T> Consumer<T> ignored(ThrowableConsumer<T, ?> o) {
		return o.ignored();
	}

	/**
	 * 指定された引数を用いてこの操作を実行する．検査例外が発生する可能性がある．
	 * @param t 引数
	 * @throws E この操作が発生させる可能性のある例外
	 */
	public void accept(T t) throws E;

	/**
	 * 例外の処理方法を指定し，この操作と合成した操作を返す．
	 * @param handler 発生した例外とその時の引数を消費する操作
	 * @return 検査例外を発生させない操作
	 */
	public default Consumer<T> handling(BiConsumer<? super E, ? super T> handler) {
		return v -> {
			try {
				accept(v);
			} catch (RuntimeException | Error e) {
				throw e;
			} catch (Throwable t) {
				@SuppressWarnings("unchecked")
				E e = (E) t; // チェック例外でコンパイルが通るのはEのみ
				handler.accept(e, v);
			}
		};
	}

	/**
	 * この操作が例外を発生させたとき実行時例外でラップするような操作を返す．
	 * @return 検査例外を発生させない操作
	 */
	public default Consumer<T> unchecked() {
		return v -> {
			try {
				accept(v);
			} catch (RuntimeException | Error e) {
				throw e;
			} catch (Throwable e) {
				if(e instanceof IOException) {
					throw new UncheckedIOException((IOException) e);
				}
				throw new RuntimeException(e);
			}
		};
	}

	/**
	 * この操作が例外を発生させたときそれを無視する操作を返す．
	 * @return 検査例外を発生させない操作
	 */
	public default Consumer<T> ignored() {
		return v -> {
			try {
				accept(v);
			} catch (RuntimeException | Error e) {
				throw e;
			} catch (Throwable e) {
				// チェック例外でコンパイルが通るのはEのみ
			}
		};
	}
}
