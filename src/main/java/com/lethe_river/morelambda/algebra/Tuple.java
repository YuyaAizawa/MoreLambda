package com.lethe_river.morelambda.algebra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Tupleのユーティリティクラス
 * @author YuyaAizawa
 *
 */
public final class Tuple {
	private Tuple() {}

	/**
	 * 指定された要素を持つTupleを返す．
	 *
	 * @param v1 新しいTupleの1番目の要素
	 * @param v2 新しいTupleの2番目の要素
	 * @param <V1> 新しいTupleの1番目の要素の型
	 * @param <V2> 新しいTupleの2番目の要素の型
	 * @return 新しいTuple
	 */
	public static <V1, V2> Tuple2<V1, V2> of(V1 v1, V2 v2) {
		return new Tuple2<>(v1, v2);
	}

	/**
	 * 指定された要素を持つTupleを返す．
	 *
	 * @param v1 新しいTupleの1番目の要素
	 * @param v2 新しいTupleの2番目の要素
	 * @param v3 新しいTupleの3番目の要素
	 * @param <V1> 新しいTupleの1番目の要素の型
	 * @param <V2> 新しいTupleの2番目の要素の型
	 * @param <V3> 新しいTupleの3番目の要素の型
	 * @return 新しいTuple
	 */
	public static <V1, V2, V3> Tuple3<V1, V2, V3> of(V1 v1, V2 v2, V3 v3) {
		return new Tuple3<>(v1, v2, v3);
	}

	/**
	 * 指定された要素を持つTupleを返す．
	 *
	 * @param v1 新しいTupleの1番目の要素
	 * @param v2 新しいTupleの2番目の要素
	 * @param v3 新しいTupleの3番目の要素
	 * @param v4 新しいTupleの4番目の要素
	 * @param <V1> 新しいTupleの1番目の要素の型
	 * @param <V2> 新しいTupleの2番目の要素の型
	 * @param <V3> 新しいTupleの3番目の要素の型
	 * @param <V4> 新しいTupleの4番目の要素の型
	 * @return 新しいTuple
	 */
	public static <V1, V2, V3, V4> Tuple4<V1, V2, V3, V4> of(V1 v1, V2 v2, V3 v3, V4 v4) {
		return new Tuple4<>(v1, v2, v3, v4);
	}

	/**
	 * 指定された要素を持つTupleを返す．
	 *
	 * @param v1 新しいTupleの1番目の要素
	 * @param v2 新しいTupleの2番目の要素
	 * @param v3 新しいTupleの3番目の要素
	 * @param v4 新しいTupleの4番目の要素
	 * @param v5 新しいTupleの5番目の要素
	 * @param <V1> 新しいTupleの1番目の要素の型
	 * @param <V2> 新しいTupleの2番目の要素の型
	 * @param <V3> 新しいTupleの3番目の要素の型
	 * @param <V4> 新しいTupleの4番目の要素の型
	 * @param <V5> 新しいTupleの5番目の要素の型
	 * @return 新しいTuple
	 */
	public static <V1, V2, V3, V4, V5> Tuple5<V1, V2, V3, V4, V5> of(V1 v1, V2 v2, V3 v3, V4 v4, V5 v5) {
		return new Tuple5<>(v1, v2, v3, v4, v5);
	}

	/**
	 * 指定された型のTupleのコンパレータを返す.
	 *
	 * @param c1 Tupleの1番目の要素の型をあらわすクラス
	 * @param c2 Tupleの2番目の要素の型をあらわすクラス
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @return Tupleのコンパレータ
	 */
	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>>
	Comparator<Tuple2<T1, T2>> comparatorOf(Class<T1> c1, Class<T2> c2) {
		return new Comparator<Tuple2<T1, T2>>() {
			@Override
			public int compare(Tuple2<T1, T2> o1, Tuple2<T1, T2> o2) {
				int c;
				c = o1.v1.compareTo(o2.v1);
				if(c != 0) {
					return c;
				}
				c = o1.v2.compareTo(o2.v2);
				return c;
			}
		};
	}

	/**
	 * 指定された型のTupleのコンパレータを返す.
	 *
	 * @param c1 Tupleの1番目の要素の型をあらわすクラス
	 * @param c2 Tupleの2番目の要素の型をあらわすクラス
	 * @param c3 Tupleの3番目の要素の型をあらわすクラス
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @return Tupleのコンパレータ
	 */

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>>
	Comparator<Tuple3<T1, T2, T3>> comparatorOf(Class<T1> c1, Class<T2> c2, Class<T3> c3) {
		return new Comparator<Tuple3<T1, T2, T3>>() {
			@Override
			public int compare(Tuple3<T1, T2, T3> o1, Tuple3<T1, T2, T3> o2) {
				int c;
				c = o1.v1.compareTo(o2.v1);
				if(c != 0) {
					return c;
				}
				c = o1.v2.compareTo(o2.v2);
				if(c != 0) {
					return c;
				}
				c = o1.v3.compareTo(o2.v3);
				return c;
			}
		};
	}

	/**
	 * 指定された型のTupleのコンパレータを返す.
	 *
	 * @param c1 Tupleの1番目の要素の型をあらわすクラス
	 * @param c2 Tupleの2番目の要素の型をあらわすクラス
	 * @param c3 Tupleの3番目の要素の型をあらわすクラス
	 * @param c4 Tupleの4番目の要素の型をあらわすクラス
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @param <T4> Tupleの4番目の要素の型
	 * @return Tupleのコンパレータ
	 */

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>>
	Comparator<Tuple4<T1, T2, T3, T4>> comparatorOf(Class<T1> c1, Class<T2> c2, Class<T3> c3, Class<T4> c4) {
		return new Comparator<Tuple4<T1, T2, T3, T4>>() {
			@Override
			public int compare(Tuple4<T1, T2, T3, T4> o1, Tuple4<T1, T2, T3, T4> o2) {
				int c;
				c = o1.v1.compareTo(o2.v1);
				if(c != 0) {
					return c;
				}
				c = o1.v2.compareTo(o2.v2);
				if(c != 0) {
					return c;
				}
				c = o1.v3.compareTo(o2.v3);
				if(c != 0) {
					return c;
				}
				c = o1.v4.compareTo(o2.v4);
				return c;
			}
		};
	}

	/**
	 * 指定された型のTupleのコンパレータを返す.
	 *
	 * @param c1 Tupleの1番目の要素の型をあらわすクラス
	 * @param c2 Tupleの2番目の要素の型をあらわすクラス
	 * @param c3 Tupleの3番目の要素の型をあらわすクラス
	 * @param c4 Tupleの4番目の要素の型をあらわすクラス
	 * @param c5 Tupleの5番目の要素の型をあらわすクラス
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @param <T4> Tupleの4番目の要素の型
	 * @param <T5> Tupleの5番目の要素の型
	 * @return Tupleのコンパレータ
	 */

	public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>, T5 extends Comparable<T5>>
	Comparator<Tuple5<T1, T2, T3, T4, T5>> comparatorOf(Class<T1> c1, Class<T2> c2, Class<T3> c3, Class<T4> c4, Class<T5> c5) {
		return new Comparator<Tuple5<T1, T2, T3, T4, T5>>() {
			@Override
			public int compare(Tuple5<T1, T2, T3, T4, T5> o1, Tuple5<T1, T2, T3, T4, T5> o2) {
				int c;
				c = o1.v1.compareTo(o2.v1);
				if(c != 0) {
					return c;
				}
				c = o1.v2.compareTo(o2.v2);
				if(c != 0) {
					return c;
				}
				c = o1.v3.compareTo(o2.v3);
				if(c != 0) {
					return c;
				}
				c = o1.v4.compareTo(o2.v4);
				if(c != 0) {
					return c;
				}
				c = o1.v5.compareTo(o2.v5);
				return c;
			}
		};
	}

	/**
	 * 入力されたStreamの同じ位置にある要素をTupleで結合したStreamを作る．
	 * 新しいStreamの要素の数はもっとも少ないものに合わせられる．
	 * @param s1 Tupleの1番目の要素のソースとなるStream
	 * @param s2 Tupleの2番目の要素のソースとなるStream
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @return 結合したTupleを要素とするStream
	 */
	public static <T1, T2> Stream<Tuple2<T1, T2>>
	stream(Stream<T1> s1, Stream<T2> s2) {
		Objects.nonNull(s1);
		Objects.nonNull(s2);
		final Spliterator<T1> sp1 = s1.spliterator();
		final Spliterator<T2> sp2 = s2.spliterator();
		final int c1 = sp1.characteristics();
		final int c2 = sp2.characteristics();
		final int c = zipCharacteristics(c1, c2);
		final Iterator<Tuple2<T1, T2>> i =new Tuple2Ziper<>(
				Spliterators.iterator(sp1),
				Spliterators.iterator(sp2));

		if((c & Spliterator.SIZED) != 0) {
			final long limit = Math.min(sp1.getExactSizeIfKnown(),
										sp2.getExactSizeIfKnown());
			assert(limit != -1);
			return StreamSupport.stream(Spliterators.spliterator(i, limit, c), false);
		}

		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(i, c), false);
	}

	/**
	 * 入力されたListの同じ位置にある要素をTupleで結合したStreamを作る．
	 * 新しいStreamの要素の数はもっとも少ないものに合わせられる．
	 * @param l1 Tupleの1番目の要素のソースとなるList
	 * @param l2 Tupleの2番目の要素のソースとなるList
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @return 結合したTupleを要素とするStream
	 */
	public static <T1, T2> Stream<Tuple2<T1, T2>>
	stream(List<T1> l1, List<T2> l2) {
		int min = Integer.MAX_VALUE;
		min = Math.min(min, l1.size());
		min = Math.min(min, l2.size());

		if(min == 0) {
			return Stream.empty();
		}else if(min == Integer.MAX_VALUE) {
			return stream(l1.stream(), l2.stream());
		} else {
			return StreamSupport.stream(new Zip2Spliterator<>(
					new ListHalfSplitSpliterator<>(l1.subList(0, min)),
					new ListHalfSplitSpliterator<>(l2.subList(0, min))
					), false);
		}
	}

	/**
	 * 入力されたStreamの同じ位置にある要素をTupleで結合したStreamを作る．
	 * 新しいStreamの要素の数はもっとも少ないものに合わせられる．
	 * @param s1 Tupleの1番目の要素のソースとなるStream
	 * @param s2 Tupleの2番目の要素のソースとなるStream
	 * @param s3 Tupleの3番目の要素のソースとなるStream
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @return 結合したTupleを要素とするStream
	 */
	public static <T1, T2, T3> Stream<Tuple3<T1, T2, T3>>
	stream(Stream<T1> s1, Stream<T2> s2, Stream<T3> s3) {
		Objects.nonNull(s1);
		Objects.nonNull(s2);
		Objects.nonNull(s3);
		final Spliterator<T1> sp1 = s1.spliterator();
		final Spliterator<T2> sp2 = s2.spliterator();
		final Spliterator<T3> sp3 = s3.spliterator();
		final int c1 = sp1.characteristics();
		final int c2 = sp2.characteristics();
		final int c3 = sp3.characteristics();
		final int c = zipCharacteristics(c1, c2, c3);
		final Iterator<Tuple3<T1, T2, T3>> i = new Tuple3Ziper<>(
				Spliterators.iterator(sp1),
				Spliterators.iterator(sp2),
				Spliterators.iterator(sp3)
				);
		if((c & Spliterator.SIZED) != 0) {
			long limit = Math.min(sp1.getExactSizeIfKnown(),
								  sp2.getExactSizeIfKnown());
			limit = Math.min(limit, sp3.getExactSizeIfKnown());
			assert(limit != -1);
			return StreamSupport.stream(Spliterators.spliterator(i, limit, c), false);
		}

		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(i, c), false);
	}

	/**
	 * 入力されたListの同じ位置にある要素をTupleで結合したStreamを作る．
	 * 新しいStreamの要素の数はもっとも少ないものに合わせられる．
	 * @param l1 Tupleの1番目の要素のソースとなるList
	 * @param l2 Tupleの2番目の要素のソースとなるList
	 * @param l3 Tupleの3番目の要素のソースとなるList
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @return 結合したTupleを要素とするStream
	 */
	public static <T1, T2, T3> Stream<Tuple3<T1, T2, T3>>
	stream(List<T1> l1, List<T2> l2, List<T3> l3) {
		int min = Integer.MAX_VALUE;
		min = Math.min(min, l1.size());
		min = Math.min(min, l2.size());
		min = Math.min(min, l3.size());

		if(min == 0) {
			return Stream.empty();
		}else if(min == Integer.MAX_VALUE) {
			return stream(l1.stream(), l2.stream(), l3.stream());
		} else {
			return StreamSupport.stream(new Zip3Spliterator<>(
					new ListHalfSplitSpliterator<>(l1.subList(0, min)),
					new ListHalfSplitSpliterator<>(l2.subList(0, min)),
					new ListHalfSplitSpliterator<>(l3.subList(0, min))
					), false);
		}
	}


	/**
	 * 入力されたStreamの同じ位置にある要素をTupleで結合したStreamを作る．
	 * 新しいStreamの要素の数はもっとも少ないものに合わせられる．
	 * @param s1 Tupleの1番目の要素のソースとなるStream
	 * @param s2 Tupleの2番目の要素のソースとなるStream
	 * @param s3 Tupleの3番目の要素のソースとなるStream
	 * @param s4 Tupleの4番目の要素のソースとなるStream
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @param <T4> Tupleの4番目の要素の型
	 * @return 結合したTupleを要素とするStream
	 */
	public static <T1, T2, T3, T4> Stream<Tuple4<T1, T2, T3, T4>>
	stream(Stream<T1> s1, Stream<T2> s2, Stream<T3> s3, Stream<T4> s4) {
		Objects.nonNull(s1);
		Objects.nonNull(s2);
		Objects.nonNull(s3);
		Objects.nonNull(s4);
		final Spliterator<T1> sp1 = s1.spliterator();
		final Spliterator<T2> sp2 = s2.spliterator();
		final Spliterator<T3> sp3 = s3.spliterator();
		final Spliterator<T4> sp4 = s4.spliterator();
		final int c1 = sp1.characteristics();
		final int c2 = sp2.characteristics();
		final int c3 = sp3.characteristics();
		final int c4 = sp4.characteristics();
		final int c = zipCharacteristics(c1, c2, c3, c4);
		final Iterator<Tuple4<T1, T2, T3, T4>> i = new Tuple4Ziper<>(
				Spliterators.iterator(sp1),
				Spliterators.iterator(sp2),
				Spliterators.iterator(sp3),
				Spliterators.iterator(sp4)
				);
		if((c & Spliterator.SIZED) != 0) {
			long limit = Math.min(sp1.getExactSizeIfKnown(),
								  sp2.getExactSizeIfKnown());
			limit = Math.min(limit, sp3.getExactSizeIfKnown());
			limit = Math.min(limit, sp4.getExactSizeIfKnown());

			assert(limit != -1);
			return StreamSupport.stream(Spliterators.spliterator(i, limit, c), false);
		}

		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(i, c), false);
	}

	/**
	 * 入力されたListの同じ位置にある要素をTupleで結合したStreamを作る．
	 * 新しいStreamの要素の数はもっとも少ないものに合わせられる．
	 * @param l1 Tupleの1番目の要素のソースとなるList
	 * @param l2 Tupleの2番目の要素のソースとなるList
	 * @param l3 Tupleの3番目の要素のソースとなるList
	 * @param l4 Tupleの4番目の要素のソースとなるList
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @param <T4> Tupleの4番目の要素の型
	 * @return 結合したTupleを要素とするStream
	 */
	public static <T1, T2, T3, T4> Stream<Tuple4<T1, T2, T3, T4>>
	stream(List<T1> l1, List<T2> l2, List<T3> l3, List<T4> l4) {
		int min = Integer.MAX_VALUE;
		min = Math.min(min, l1.size());
		min = Math.min(min, l2.size());
		min = Math.min(min, l3.size());
		min = Math.min(min, l4.size());

		if(min == 0) {
			return Stream.empty();
		}else if(min == Integer.MAX_VALUE) {
			return stream(l1.stream(), l2.stream(), l3.stream(), l4.stream());
		} else {
			return StreamSupport.stream(new Zip4Spliterator<>(
					new ListHalfSplitSpliterator<>(l1.subList(0, min)),
					new ListHalfSplitSpliterator<>(l2.subList(0, min)),
					new ListHalfSplitSpliterator<>(l3.subList(0, min)),
					new ListHalfSplitSpliterator<>(l4.subList(0, min))
					), false);
		}
	}


	/**
	 * 入力されたStreamの同じ位置にある要素をTupleで結合したStreamを作る．
	 * 新しいStreamの要素の数はもっとも少ないものに合わせられる．
	 * @param s1 Tupleの1番目の要素のソースとなるStream
	 * @param s2 Tupleの2番目の要素のソースとなるStream
	 * @param s3 Tupleの3番目の要素のソースとなるStream
	 * @param s4 Tupleの4番目の要素のソースとなるStream
	 * @param s5 Tupleの5番目の要素のソースとなるStream
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @param <T4> Tupleの4番目の要素の型
	 * @param <T5> Tupleの5番目の要素の型
	 * @return 結合したTupleを要素とするStream
	 */
	public static <T1, T2, T3, T4, T5> Stream<Tuple5<T1, T2, T3, T4, T5>>
	stream(Stream<T1> s1, Stream<T2> s2, Stream<T3> s3, Stream<T4> s4, Stream<T5> s5) {
		Objects.nonNull(s1);
		Objects.nonNull(s2);
		Objects.nonNull(s3);
		Objects.nonNull(s4);
		Objects.nonNull(s5);
		final Spliterator<T1> sp1 = s1.spliterator();
		final Spliterator<T2> sp2 = s2.spliterator();
		final Spliterator<T3> sp3 = s3.spliterator();
		final Spliterator<T4> sp4 = s4.spliterator();
		final Spliterator<T5> sp5 = s5.spliterator();
		final int c1 = sp1.characteristics();
		final int c2 = sp2.characteristics();
		final int c3 = sp3.characteristics();
		final int c4 = sp4.characteristics();
		final int c5 = sp5.characteristics();
		final int c = zipCharacteristics(c1, c2, c3, c4, c5);
		final Iterator<Tuple5<T1, T2, T3, T4, T5>> i = new Tuple5Ziper<>(
				Spliterators.iterator(sp1),
				Spliterators.iterator(sp2),
				Spliterators.iterator(sp3),
				Spliterators.iterator(sp4),
				Spliterators.iterator(sp5)
				);

		if((c & Spliterator.SIZED) != 0) {
			long limit = Math.min(sp1.getExactSizeIfKnown(),
								  sp2.getExactSizeIfKnown());
			limit = Math.min(limit, sp3.getExactSizeIfKnown());
			limit = Math.min(limit, sp4.getExactSizeIfKnown());
			limit = Math.min(limit, sp5.getExactSizeIfKnown());
			assert(limit != -1);
			return StreamSupport.stream(Spliterators.spliterator(i, limit, c), false);
		}

		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(i, c), false);
	}

	/**
	 * 入力されたListの同じ位置にある要素をTupleで結合したStreamを作る．
	 * 新しいStreamの要素の数はもっとも少ないものに合わせられる．
	 * @param l1 Tupleの1番目の要素のソースとなるList
	 * @param l2 Tupleの2番目の要素のソースとなるList
	 * @param l3 Tupleの3番目の要素のソースとなるList
	 * @param l4 Tupleの4番目の要素のソースとなるList
	 * @param l5 Tupleの5番目の要素のソースとなるList
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @param <T4> Tupleの4番目の要素の型
	 * @param <T5> Tupleの5番目の要素の型
	 * @return 結合したTupleを要素とするStream
	 */
	public static <T1, T2, T3, T4, T5> Stream<Tuple5<T1, T2, T3, T4, T5>>
	stream(List<T1> l1, List<T2> l2, List<T3> l3, List<T4> l4, List<T5> l5) {

		int min = Integer.MAX_VALUE;
		min = Math.min(min, l1.size());
		min = Math.min(min, l2.size());
		min = Math.min(min, l3.size());
		min = Math.min(min, l4.size());
		min = Math.min(min, l5.size());

		if(min == 0) {
			return Stream.empty();
		}else if(min == Integer.MAX_VALUE) {
			return stream(l1.stream(), l2.stream(), l3.stream(), l4.stream(), l5.stream());
		} else {
			return StreamSupport.stream(new Zip5Spliterator<>(
					new ListHalfSplitSpliterator<>(l1.subList(0, min)),
					new ListHalfSplitSpliterator<>(l2.subList(0, min)),
					new ListHalfSplitSpliterator<>(l3.subList(0, min)),
					new ListHalfSplitSpliterator<>(l4.subList(0, min)),
					new ListHalfSplitSpliterator<>(l5.subList(0, min))
					), false);
		}
	}

	/**
	 * 入力されたStreamの直積をTupleで結合したStreamを作る．
	 * Tuple内の各要素は入力されたStreamの要素の参照となる．
	 * @param s1 Tupleの1番目の要素のソースとなるStream
	 * @param s2 Tupleの2番目の要素のソースとなるStream
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @return 直積のTupleを要素とするStream
	 */

	public static <T1, T2> Stream<Tuple2<T1, T2>> productStream(Stream<T1> s1, Stream<T2> s2) {
		Objects.requireNonNull(s1);
		Objects.requireNonNull(s2);
		List<T2> l2 = s2.collect(Collectors.toList());
		return s1.flatMap(v1 -> l2.stream().map(v2 -> Tuple.of(v1, v2)));
	}

	/**
	 * 入力されたCollectionの直積をTupleで結合したStreamを作る．
	 * Tuple内の各要素は入力されたStreamの要素の参照となる．
	 * @param c1 Tupleの1番目の要素のソースとなるStream
	 * @param c2 Tupleの2番目の要素のソースとなるStream
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @return 直積のTupleを要素とするStream
	 */

	public static <T1, T2> Stream<Tuple2<T1, T2>> productStream(Collection<T1> c1, Collection<T2> c2) {
		return c1.size() < c2.size()
				? productStream(c1, c2.stream())
				: productStream(c1.stream(), c2);
	}
	private static <T1, T2> Stream<Tuple2<T1, T2>> productStream(Collection<T1> s1, Stream<T2> s2) {
		return s2.flatMap(v2 -> s1.stream().map(v1 -> Tuple.of(v1, v2)));
	}
	private static <T1, T2> Stream<Tuple2<T1, T2>> productStream(Stream<T1> s1, Collection<T2> s2) {
		return s1.flatMap(v1 -> s2.stream().map(v2 -> Tuple.of(v1, v2)));
	}

	/**
	 * 入力されたCollectionの要素と指定した初期値から増加するインデックスの組のStreamを作る.
	 * 例えば，List["A", "B", "C"]と初期値2を入力としたとき，作られるStreamの要素は[("A", 2), ("B", 3), ("C", 4)]である.
	 * @param collection 要素のCollection
	 * @param start 添え字の初期値
	 * @param <E> 要素の型
	 * @return Collectionの要素とインデックスを要素とするStream
	 */
	public static <E> Stream<Tuple2<E, Integer>> streamWithIndex(Collection<E> collection, int start) {
		return stream(collection.stream(), IntStream.iterate(start, i -> i+1).boxed());
	}
	/**
	 * 入力されたCollectionの要素と0から増加するインデックスの組のStreamを作る.
	 * 例えば，List["A", "B", "C"]を入力としたとき，作られるStreamの要素は[("A", 0), ("B", 1), ("C", 2)]である.
	 * @param collection 要素のCollection
	 * @param <E> 要素の型
	 * @return Collectionの要素とインデックスを要素とするStream
	 */
	public static <E> Stream<Tuple2<E, Integer>> streamWithIndex(Collection<E> collection) {
		return streamWithIndex(collection, 0);
	}

	/**
	 * Tuple2&lt;T1, T2&gt;をMap&lt;T1, T2&gt;に変換するためのCollectorを得る．
	 * @param mergeFunction 同じキーに関連付けられた値同士の衝突の解決に使用されるマージ関数
	 * ({@link Map#merge(Object, Object, java.util.function.BiFunction) Map#merge(T1, T2, BiFunction)}に渡される)
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @return Collector
	 */
	public static <T1, T2> Collector<Tuple2<T1, T2>, ?, Map<T1, T2>> toMap(
			BinaryOperator<T2> mergeFunction) {
		Objects.requireNonNull(mergeFunction);
		return Collectors.toMap(t -> t.v1, t -> t.v2, mergeFunction);
	}

	/**
	 * Tuple2&lt;T1, T2&gt;をMap&lt;T1, T2&gt;に変換するためのCollectorを得る．
	 * @param mergeFunction 同じキーに関連付けられた値同士の衝突の解決に使用されるマージ関数
	 * ({@link Map#merge(Object, Object, java.util.function.BiFunction) Map#merge(T1, T2, BiFunction)}に渡される)
	 * @param mapSupplier 結果の挿入先となる新しい空のMapを返す関数
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <M> Mapの型
	 * @return Collector
	 */
	public static <T1, T2, M extends Map<T1, T2>> Collector<Tuple2<T1, T2>, ?, M> toMap(
			BinaryOperator<T2> mergeFunction, Supplier<M> mapSupplier) {
		Objects.requireNonNull(mergeFunction);
		Objects.requireNonNull(mapSupplier);
		return Collectors.toMap(t -> t.v1, t -> t.v2, mergeFunction, mapSupplier);
	}

	/**
	 * 指定したMapのkeyとvalueのペアを要素とするStreamを作る．
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param map StreamのもととなるMap
	 * @return keyとvalueのペアを要素とするStream
	 */
	public static <T1, T2> Stream<Tuple2<T1, T2>> stream(Map<T1, T2> map) {
		final int MAP_CHARACTERISTICS =
				  Spliterator.DISTINCT
				| Spliterator.NONNULL
				| Spliterator.SIZED;

		final Set<Entry<T1, T2>> entries = map.entrySet();
		final Iterator<Tuple2<T1, T2>> iterator = new Entry2Tuple<>(entries.iterator());

		return StreamSupport.stream(Spliterators.spliterator(
				iterator, entries.size(), MAP_CHARACTERISTICS), false);
	}

	/**
	 * 入力したListから前後2つの要素の組を操作するためのStreamを作る．
	 * 例えば元のListが['A', 'B', 'C', 'D']であるとき，作られるStreamの要素は
	 * [('A', 'B'), ('B', 'C'), ('C', 'D')]となる．
	 * @param list もとになるList
	 * @param <T> 要素の型
	 * @return 前後2つの要素の組を要素とするStream
	 */
	public static <T> Stream<Tuple2<T, T>> window2Stream(List<T> list) {
		Objects.requireNonNull(list);

		if(list.size() < 2) {
			return Stream.empty();
		}
		return stream(list, list.subList(1, list.size()));
	}

	/**
	 * 入力したStreamの前後2つの要素の組を操作するためのStreamを作る．
	 * 例えば元のStreamの要素が['A', 'B', 'C', 'D']であるとき，作られるStreamの要素は
	 * [('A', 'B'), ('B', 'C'), ('C', 'D')]となる．
	 * 入力したStreamは消費される．
	 *
	 * @param stream もとになるStream
	 * @param <T> 要素の型
	 * @return 前後2つの要素の組を要素とするStream
	 */
	public static <T> Stream<Tuple2<T, T>> window2Stream(Stream<T> stream) {
		Objects.requireNonNull(stream);

		Spliterator<T> spliterator = stream.spliterator();

		if(spliterator.estimateSize() < 2) {
			spliterator = Spliterators.emptySpliterator();
		}

		return StreamSupport.stream(new WindowSpliterators.Window2Spliterator<>(spliterator), stream.isParallel());
	}

	/**
	 * 入力したListから前後3つの要素の組を操作するためのStreamを作る．
	 *
	 * @see Tuple#window2Stream(List)
	 * @param list もとになるList
	 * @param <T> 要素の型
	 * @return 前後3つの要素の組を要素とするStream
	 */
	public static <T> Stream<Tuple3<T, T, T>> window3Stream(List<T> list) {
		Objects.requireNonNull(list);

		if(list.size() < 3) {
			return Stream.empty();
		}
		return stream(
				list,
				list.subList(1, list.size()),
				list.subList(2, list.size()));
	}

	/**
	 * 入力したListから前後3つの要素の組を操作するためのStreamを作る．
	 *
	 * @see Tuple#window2Stream(Stream)
	 * @param stream もとになるStream
	 * @param <T> 要素の型
	 * @return 前後3つの要素の組を要素とするStream
	 */
	public static <T> Stream<Tuple3<T, T, T>> window3Stream(Stream<T> stream) {
		Objects.requireNonNull(stream);

		Spliterator<T> spliterator = stream.spliterator();

		if(spliterator.estimateSize() < 3) {
			spliterator = Spliterators.emptySpliterator();
		}

		return StreamSupport.stream(new WindowSpliterators.Window3Spliterator<>(spliterator), stream.isParallel());
	}

	/**
	 * 入力したListから前後4つの要素の組を操作するためのStreamを作る．
	 *
	 * @see Tuple#window2Stream(List)
	 * @param list もとになるList
	 * @param <T> 要素の型
	 * @return 前後4つの要素の組を要素とするStream
	 */
	public static <T> Stream<Tuple4<T, T, T, T>> window4Stream(List<T> list) {
		Objects.requireNonNull(list);

		if(list.size() < 5) {
			return Stream.empty();
		}
		return stream(
				list,
				list.subList(1, list.size()),
				list.subList(2, list.size()),
				list.subList(3, list.size()));
	}

	/**
	 * 入力したListから前後4つの要素の組を操作するためのStreamを作る．
	 *
	 * @see Tuple#window2Stream(Stream)
	 * @param stream もとになるStream
	 * @param <T> 要素の型
	 * @return 前後4つの要素の組を要素とするStream
	 */
	public static <T> Stream<Tuple4<T, T, T, T>> window4Stream(Stream<T> stream) {
		Objects.requireNonNull(stream);

		Spliterator<T> spliterator = stream.spliterator();

		if(spliterator.estimateSize() < 4) {
			spliterator = Spliterators.emptySpliterator();
		}

		return StreamSupport.stream(new WindowSpliterators.Window4Spliterator<>(spliterator), stream.isParallel());
	}

	/**
	 * 入力したListから前後5つの要素の組を操作するためのStreamを作る．
	 *
	 * @see Tuple#window2Stream(List)
	 * @param list もとになるList
	 * @param <T> 要素の型
	 * @return 前後5つの要素の組を要素とするStream
	 */
	public static <T> Stream<Tuple5<T, T, T, T, T>> window5Stream(List<T> list) {
		Objects.requireNonNull(list);

		if(list.size() < 5) {
			return Stream.empty();
		}
		return stream(
				list,
				list.subList(1, list.size()),
				list.subList(2, list.size()),
				list.subList(3, list.size()),
				list.subList(4, list.size()));
	}

	/**
	 * 入力したListから前後5つの要素の組を操作するためのStreamを作る．
	 *
	 * @see Tuple#window2Stream(Stream)
	 * @param stream もとになるStream
	 * @param <T> 要素の型
	 * @return 前後5つの要素の組を要素とするStream
	 */
	public static <T> Stream<Tuple5<T, T, T, T, T>> window5Stream(Stream<T> stream) {
		Objects.requireNonNull(stream);

		Spliterator<T> spliterator = stream.spliterator();

		if(spliterator.estimateSize() < 5) {
			spliterator = Spliterators.emptySpliterator();
		}

		return StreamSupport.stream(new WindowSpliterators.Window5Spliterator<>(spliterator), stream.isParallel());
	}

	/**
	 * 要素ごとのListに変換するCollectorを得る.
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @return Collector
	 */
	public static <T1, T2> Collector<Tuple2<T1, T2>, ?,
			Tuple2<List<T1>, List<T2>>> toList2() {
		return Collector.of(
				() -> Tuple.of(
						new ArrayList<>(),
						new ArrayList<>()),
				(l, t) -> {
					l.v1.add(t.v1);
					l.v2.add(t.v2);},
				(left, right) -> {
					left.v1.addAll(right.v1);
					left.v2.addAll(right.v2);
					return left;}
				);
	}

	/**
	 * 要素ごとのListに変換するCollectorを得る.
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @return Collector
	 */
	public static <T1, T2, T3> Collector<Tuple3<T1, T2, T3>, ?,
			Tuple3<List<T1>, List<T2>, List<T3>>> toList3() {
		return Collector.of(
				() -> Tuple.of(
						new ArrayList<>(),
						new ArrayList<>(),
						new ArrayList<>()),
				(l, t) -> {
					l.v1.add(t.v1);
					l.v2.add(t.v2);
					l.v3.add(t.v3);},
				(left, right) -> {
					left.v1.addAll(right.v1);
					left.v2.addAll(right.v2);
					left.v3.addAll(right.v3);
					return left;}
				);
	}

	/**
	 * 要素ごとのListに変換するCollectorを得る.
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @param <T4> Tupleの4番目の要素の型
	 * @return Collector
	 */
	public static <T1, T2, T3, T4> Collector<Tuple4<T1, T2, T3, T4>, ?,
			Tuple4<List<T1>, List<T2>, List<T3>, List<T4>>> toList4() {
		return Collector.of(
				() -> Tuple.of(
						new ArrayList<>(),
						new ArrayList<>(),
						new ArrayList<>(),
						new ArrayList<>()),
				(l, t) -> {
					l.v1.add(t.v1);
					l.v2.add(t.v2);
					l.v3.add(t.v3);
					l.v4.add(t.v4);},
				(left, right) -> {
					left.v1.addAll(right.v1);
					left.v2.addAll(right.v2);
					left.v3.addAll(right.v3);
					left.v4.addAll(right.v4);
					return left;}
				);
	}

	/**
	 * 要素ごとのListに変換するCollectorを得る.
	 * @param <T1> Tupleの1番目の要素の型
	 * @param <T2> Tupleの2番目の要素の型
	 * @param <T3> Tupleの3番目の要素の型
	 * @param <T4> Tupleの4番目の要素の型
	 * @param <T5> Tupleの5番目の要素の型
	 * @return Collector
	 */
	public static <T1, T2, T3, T4, T5> Collector<Tuple5<T1, T2, T3, T4, T5>, ?,
			Tuple5<List<T1>, List<T2>, List<T3>, List<T4>, List<T5>>> toList5() {
		return Collector.of(
				() -> Tuple.of(
						new ArrayList<>(),
						new ArrayList<>(),
						new ArrayList<>(),
						new ArrayList<>(),
						new ArrayList<>()),
				(l, t) -> {
					l.v1.add(t.v1);
					l.v2.add(t.v2);
					l.v3.add(t.v3);
					l.v4.add(t.v4);
					l.v5.add(t.v5);},
				(left, right) -> {
					left.v1.addAll(right.v1);
					left.v2.addAll(right.v2);
					left.v3.addAll(right.v3);
					left.v4.addAll(right.v4);
					left.v5.addAll(right.v5);
					return left;}
				);
	}

	private static int zipCharacteristics(int... c) {
		int dis = Arrays.stream(c).reduce((l, r) -> l | r).getAsInt();
		int con = Arrays.stream(c).reduce((l, r) -> l & r).getAsInt();
		return (Spliterator.CONCURRENT & (con))
			 | (Spliterator.DISTINCT   & (dis))
			 | (Spliterator.IMMUTABLE  & (con))
			 | (Spliterator.SORTED     & (con))
			 |  Spliterator.NONNULL
			 | (Spliterator.ORDERED    & (con))
			 | (Spliterator.SIZED      & (con));
	}

	private static class Tuple2Ziper<T1, T2> implements Iterator<Tuple2<T1, T2>> {
		private final Iterator<T1> i1;
		private final Iterator<T2> i2;

		public Tuple2Ziper(Iterator<T1> i1, Iterator<T2> i2) {
			this.i1 = i1;
			this.i2 = i2;
		}

		@Override
		public boolean hasNext() {
			return i1.hasNext()
				&& i2.hasNext();
		}

		@Override
		public Tuple2<T1, T2> next() {
			return Tuple.of(i1.next(),
							i2.next());
		}
	}

	private static class Tuple3Ziper<T1, T2, T3> implements Iterator<Tuple3<T1, T2, T3>> {
		private final Iterator<T1> i1;
		private final Iterator<T2> i2;
		private final Iterator<T3> i3;

		public Tuple3Ziper(Iterator<T1> i1, Iterator<T2> i2, Iterator<T3> i3) {
			this.i1 = i1;
			this.i2 = i2;
			this.i3 = i3;
		}

		@Override
		public boolean hasNext() {
			return i1.hasNext()
				&& i2.hasNext()
				&& i3.hasNext();
		}

		@Override
		public Tuple3<T1, T2, T3> next() {
			return Tuple.of(i1.next(),
							i2.next(),
							i3.next());
		}
	}

	private static class Tuple4Ziper<T1, T2, T3, T4> implements Iterator<Tuple4<T1, T2, T3, T4>> {
		private final Iterator<T1> i1;
		private final Iterator<T2> i2;
		private final Iterator<T3> i3;
		private final Iterator<T4> i4;


		public Tuple4Ziper(Iterator<T1> i1, Iterator<T2> i2, Iterator<T3> i3, Iterator<T4> i4) {
			this.i1 = i1;
			this.i2 = i2;
			this.i3 = i3;
			this.i4 = i4;
		}

		@Override
		public boolean hasNext() {
			return i1.hasNext()
				&& i2.hasNext()
				&& i3.hasNext()
				&& i4.hasNext();
		}

		@Override
		public Tuple4<T1, T2, T3, T4> next() {
			return Tuple.of(i1.next(), i2.next(), i3.next(), i4.next());
		}
	}

	private static class Tuple5Ziper<T1, T2, T3, T4, T5> implements Iterator<Tuple5<T1, T2, T3, T4, T5>> {
		private final Iterator<T1> i1;
		private final Iterator<T2> i2;
		private final Iterator<T3> i3;
		private final Iterator<T4> i4;
		private final Iterator<T5> i5;

		public Tuple5Ziper(Iterator<T1> i1, Iterator<T2> i2, Iterator<T3> i3, Iterator<T4> i4, Iterator<T5> i5) {
			this.i1 = i1;
			this.i2 = i2;
			this.i3 = i3;
			this.i4 = i4;
			this.i5 = i5;
		}

		@Override
		public boolean hasNext() {
			return i1.hasNext()
				&& i2.hasNext()
				&& i3.hasNext()
				&& i4.hasNext()
				&& i5.hasNext();
		}

		@Override
		public Tuple5<T1, T2, T3, T4, T5> next() {
			return Tuple.of(i1.next(),
							i2.next(),
							i3.next(),
							i4.next(),
							i5.next());
		}
	}
	private static class Zip2Spliterator<T1, T2> implements Spliterator<Tuple2<T1, T2>> {
		private final ListHalfSplitSpliterator<T1> s1;
		private final ListHalfSplitSpliterator<T2> s2;

		Object[] tuple = new Object[2];

		public Zip2Spliterator(
				ListHalfSplitSpliterator<T1> s1,
				ListHalfSplitSpliterator<T2> s2) {
			this.s1 = Objects.requireNonNull(s1);
			this.s2 = Objects.requireNonNull(s2);
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean tryAdvance(Consumer<? super Tuple2<T1, T2>> action) {
			boolean accepted = true;
			accepted &= s1.tryAdvance(t -> tuple[0] = t);
			accepted &= s2.tryAdvance(t -> tuple[1] = t);
			if(accepted) {
				action.accept(Tuple.of(
						(T1)tuple[0],
						(T2)tuple[1]));
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Spliterator<Tuple2<T1, T2>> trySplit() {
			try {
				return new Zip2Spliterator<>(
						s1.trySplit(),
						s2.trySplit());
			} catch(NullPointerException e) {
				return null;
			}
		}

		@Override
		public long estimateSize() {
			return s1.estimateSize();
		}

		@Override
		public int characteristics() {
			return ORDERED | NONNULL | SUBSIZED;
		}
	}
	private static class Zip3Spliterator<T1, T2, T3> implements Spliterator<Tuple3<T1, T2, T3>> {
		private final ListHalfSplitSpliterator<T1> s1;
		private final ListHalfSplitSpliterator<T2> s2;
		private final ListHalfSplitSpliterator<T3> s3;

		Object[] tuple = new Object[3];

		public Zip3Spliterator(
				ListHalfSplitSpliterator<T1> s1,
				ListHalfSplitSpliterator<T2> s2,
				ListHalfSplitSpliterator<T3> s3) {
			this.s1 = Objects.requireNonNull(s1);
			this.s2 = Objects.requireNonNull(s2);
			this.s3 = Objects.requireNonNull(s3);
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean tryAdvance(Consumer<? super Tuple3<T1, T2, T3>> action) {
			boolean accepted = true;
			accepted &= s1.tryAdvance(t -> tuple[0] = t);
			accepted &= s2.tryAdvance(t -> tuple[1] = t);
			accepted &= s3.tryAdvance(t -> tuple[2] = t);
			if(accepted) {
				action.accept(Tuple.of(
						(T1)tuple[0],
						(T2)tuple[1],
						(T3)tuple[2]));
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Spliterator<Tuple3<T1, T2, T3>> trySplit() {
			try {
				return new Zip3Spliterator<>(
						s1.trySplit(),
						s2.trySplit(),
						s3.trySplit());
			} catch(NullPointerException e) {
				return null;
			}
		}

		@Override
		public long estimateSize() {
			return s1.estimateSize();
		}

		@Override
		public int characteristics() {
			return ORDERED | NONNULL | SUBSIZED;
		}
	}
	private static class Zip4Spliterator<T1, T2, T3, T4> implements Spliterator<Tuple4<T1, T2, T3, T4>> {
		private final ListHalfSplitSpliterator<T1> s1;
		private final ListHalfSplitSpliterator<T2> s2;
		private final ListHalfSplitSpliterator<T3> s3;
		private final ListHalfSplitSpliterator<T4> s4;

		Object[] tuple = new Object[4];

		public Zip4Spliterator(
				ListHalfSplitSpliterator<T1> s1,
				ListHalfSplitSpliterator<T2> s2,
				ListHalfSplitSpliterator<T3> s3,
				ListHalfSplitSpliterator<T4> s4) {
			this.s1 = Objects.requireNonNull(s1);
			this.s2 = Objects.requireNonNull(s2);
			this.s3 = Objects.requireNonNull(s3);
			this.s4 = Objects.requireNonNull(s4);
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean tryAdvance(Consumer<? super Tuple4<T1, T2, T3, T4>> action) {
			boolean accepted = true;
			accepted &= s1.tryAdvance(t -> tuple[0] = t);
			accepted &= s2.tryAdvance(t -> tuple[1] = t);
			accepted &= s3.tryAdvance(t -> tuple[2] = t);
			accepted &= s4.tryAdvance(t -> tuple[3] = t);
			if(accepted) {
				action.accept(Tuple.of(
						(T1)tuple[0],
						(T2)tuple[1],
						(T3)tuple[2],
						(T4)tuple[3]));
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Spliterator<Tuple4<T1, T2, T3, T4>> trySplit() {
			try {
				return new Zip4Spliterator<>(
						s1.trySplit(),
						s2.trySplit(),
						s3.trySplit(),
						s4.trySplit());
			} catch(NullPointerException e) {
				return null;
			}
		}

		@Override
		public long estimateSize() {
			return s1.estimateSize();
		}

		@Override
		public int characteristics() {
			return ORDERED | NONNULL | SUBSIZED;
		}
	}

	private static class Zip5Spliterator<T1, T2, T3, T4, T5> implements Spliterator<Tuple5<T1, T2, T3, T4, T5>> {
		private final ListHalfSplitSpliterator<T1> s1;
		private final ListHalfSplitSpliterator<T2> s2;
		private final ListHalfSplitSpliterator<T3> s3;
		private final ListHalfSplitSpliterator<T4> s4;
		private final ListHalfSplitSpliterator<T5> s5;

		Object[] tuple = new Object[5];

		public Zip5Spliterator(
				ListHalfSplitSpliterator<T1> s1,
				ListHalfSplitSpliterator<T2> s2,
				ListHalfSplitSpliterator<T3> s3,
				ListHalfSplitSpliterator<T4> s4,
				ListHalfSplitSpliterator<T5> s5) {
			this.s1 = Objects.requireNonNull(s1);
			this.s2 = Objects.requireNonNull(s2);
			this.s3 = Objects.requireNonNull(s3);
			this.s4 = Objects.requireNonNull(s4);
			this.s5 = Objects.requireNonNull(s5);
		}

		@SuppressWarnings("unchecked")
		@Override
		public boolean tryAdvance(Consumer<? super Tuple5<T1, T2, T3, T4, T5>> action) {
			boolean accepted = true;
			accepted &= s1.tryAdvance(t -> tuple[0] = t);
			accepted &= s2.tryAdvance(t -> tuple[1] = t);
			accepted &= s3.tryAdvance(t -> tuple[2] = t);
			accepted &= s4.tryAdvance(t -> tuple[3] = t);
			accepted &= s5.tryAdvance(t -> tuple[4] = t);
			if(accepted) {
				action.accept(Tuple.of(
						(T1)tuple[0],
						(T2)tuple[1],
						(T3)tuple[2],
						(T4)tuple[3],
						(T5)tuple[4]));
				return true;
			} else {
				return false;
			}
		}

		@Override
		public Spliterator<Tuple5<T1, T2, T3, T4, T5>> trySplit() {
			try {
				return new Zip5Spliterator<>(
						s1.trySplit(),
						s2.trySplit(),
						s3.trySplit(),
						s4.trySplit(),
						s5.trySplit());
			} catch(NullPointerException e) {
				return null;
			}
		}

		@Override
		public long estimateSize() {
			return s1.estimateSize();
		}

		@Override
		public int characteristics() {
			return ORDERED | NONNULL | SUBSIZED;
		}
	}

	private static class Entry2Tuple<T1, T2> implements Iterator<Tuple2<T1, T2>> {
		private final Iterator<Entry<T1, T2>> entries;

		public Entry2Tuple(Iterator<Entry<T1, T2>> iterator) {
			entries = iterator;
		}

		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public Tuple2<T1, T2> next() {
			Entry<T1, T2> entry = entries.next();
			return new Tuple2<>(entry.getKey(), entry.getValue());
		}
	}

	private static class Window2Spliterator<T> implements Spliterator<Tuple2<T, T>> {

		Spliterator<T> spliterator;
		Iterator<T> iterator;

		T previous;
		T last;

		public Window2Spliterator(Spliterator<T> spliterator) {
			this(checkOrderd(spliterator), null, null);
		}

		private static <T> Spliterator<T> checkOrderd(Spliterator<T> spliterator) {
			if((spliterator.characteristics() & Spliterator.ORDERED) == 0) {
				throw new IllegalArgumentException("spliterator must be orderd");
			}
			return spliterator;
		}

		private Window2Spliterator(Spliterator<T> spliterator, T first, T last) {
			this.spliterator = spliterator;
			this.last = last;
			this.previous = first;
		}

		@Override
		public boolean tryAdvance(Consumer<? super Tuple2<T, T>> action) {
			Tuple2<T, T> tuple;

			if(iterator == null) {
				iterator = Spliterators.iterator(spliterator);
			}

			if(previous == null) {
				previous = iterator.next();
			}

			if(iterator.hasNext()) {
				tuple = Tuple.of(previous, iterator.next());
			} else {
				tuple = Tuple.of(previous, last);
				last = null;
			}
			previous = tuple.v2;

			action.accept(tuple);

			return iterator.hasNext() || last != null;
		}

		@Override
		public Spliterator<Tuple2<T, T>> trySplit() {
			Spliterator<T> prefix = spliterator.trySplit();
			if(prefix == null) {
				return null;
			}

			iterator = Spliterators.iterator(spliterator);
			T prefixPrevious = previous;
			previous = iterator.next();

			return new Window2Spliterator<>(prefix, prefixPrevious, previous);
		}

		@Override
		public long estimateSize() {
			return spliterator.estimateSize() - (last == null ? 1 : 0);
		}

		@Override
		public int characteristics() {
			return spliterator.characteristics();
		}
	}
}
