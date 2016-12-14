package com.lethe_river.morelambda.throwable;

import java.io.IOException;

public class Thrower {
	public static int mayThrowA(int param) throws ExceptionA {
		if(param < 0) {
			throw new ExceptionA(param);
		}
		return param;
	}
	
	public static int mayThrowIo(int param) throws IOException {
		if(param < 0) {
			throw new IOException();
		}
		return param;
	}
}
