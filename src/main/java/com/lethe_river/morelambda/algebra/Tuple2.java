package com.lethe_river.morelambda.algebra;

import java.io.Serializable;

/**
 * 型パラメータで指定した，2つの型の要素を持つコンテナオブジェクト
 * 
 * メソッドの返り値に複数の値を指定したい場面や，
 * フィールドの複数の関連した値をまとめて指定したい場面で，
 * 専用のクラスを定義するまでもないような場合に利用する．
 * 
 * 典型的には{@link java.util.stream.Stream#map(java.util.function.Function)}などで
 * 写像する前の値と後の値を次の処理で両方利用したいような場面を想定している．
 * 
 * @author YuyaAizawa
 *
 * @param <T1>
 * @param <T2>
 */
public final class Tuple2<T1,T2> implements Serializable {
	private static final long serialVersionUID = 2752206660938385231L;
	
	public final T1 v1;
	public final T2 v2;
	
	public Tuple2(T1 v1, T2 v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public String toString() {
		return "("+v1+", "+v2+")";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		@SuppressWarnings("rawtypes")
		Tuple2 subject = (Tuple2) obj;
		if (v1 == null) {
			if (subject.v1 != null)
				return false;
		} else if (!v1.equals(subject.v1))
			return false;
		
		if (v2 == null) {
			if (subject.v2 != null)
				return false;
		} else if (!v2.equals(subject.v2))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
		return result;
	}
}
