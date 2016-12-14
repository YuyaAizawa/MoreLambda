package com.lethe_river.morelambda.throwable;

public class ExceptionA extends Exception {
	private final int param;
	public ExceptionA(int param) {
		this.param = param;
	}
	
	public int getParam() {
		return param;
	}
}
