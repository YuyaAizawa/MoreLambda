package com.lethe_river.morelambda.algebra;

import java.io.Serializable;

/**
 * 型パラメータで指定した，3つの型の要素を持つコンテナオブジェクト
 * 
 * メソッドの返り値に複数の値を指定したい場面や，
 * フィールドの複数の関連した値をまとめて指定したい場面で，
 * 専用のクラスを定義するまでもないような場合に利用する．
 * 
 * @author YuyaAizawa
 *
 * @param <T1>
 * @param <T2>
 * @param <T3>
 */
public final class Tuple3<T1,T2,T3> implements Serializable {
	private static final long serialVersionUID = -1284274552648028090L;
	
	public final T1 v1;
	public final T2 v2;
	public final T3 v3;
	
	public Tuple3(T1 v1, T2 v2, T3 v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	@Override
	public String toString() {
		return "("+v1+", "+v2+", "+v3+")";
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
		Tuple3 subject = (Tuple3) obj;
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
		
		if (v3 == null) {
			if (subject.v3 != null)
				return false;
		} else if (!v3.equals(subject.v3))
			return false;
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((v1 == null) ? 0 : v1.hashCode());
		result = prime * result + ((v2 == null) ? 0 : v2.hashCode());
		result = prime * result + ((v3 == null) ? 0 : v3.hashCode());
		return result;
	}
}