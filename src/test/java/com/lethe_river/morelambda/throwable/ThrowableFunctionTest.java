package com.lethe_river.morelambda.throwable;

import static org.junit.Assert.*;

import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.lethe_river.morelambda.throwable.ThrowableFunction.*;
import org.junit.Test;

public class ThrowableFunctionTest {
	
	public static List<Integer> withException    = Arrays.asList(1, -2, 3, -4);
	public static List<Integer> withoutException = Arrays.asList(1,  2, 3,  4);
	
	public static List<Integer> exceptionOnly    = Arrays.asList(   -2,    -4);
	public static List<Integer> ignored          = Arrays.asList(1,     3    );
	public static List<Integer> completion       = Arrays.asList(1,  2, 3,  4);
	
	// TODO jUtaimeで書き直す
	@Test
	public void uncheckedTest01() {
		assertEquals(
				withoutException,
				withoutException.stream()
						.map(unchecked(i -> Thrower.mayThrowA(i)))
						.collect(Collectors.toList()));
	}
	
	@Test(expected = RuntimeException.class)
	public void uncheckedTest02() {
		withException.stream()
				.map(unchecked(i -> Thrower.mayThrowA(i)))
				.collect(Collectors.toList());
	}
	
	@Test
	public void complementTest01() {
		assertEquals(
				withoutException,
				withException.stream()
						.map(complement(i -> Thrower.mayThrowA(i), (ExceptionA e, Integer p) -> -e.getParam()))
						.collect(Collectors.toList()));
	}
	
	@Test
	public void includesToValueTest01() {
		assertEquals(
				withoutException,
				withException.stream()
						.map(includesToValue(i -> Thrower.mayThrowA(i)))
						.map(u -> u.match(i -> i, e -> -e.getParam()))
						.collect(Collectors.toList()));
	}
	
	@Test
	public void maybeTest01() {
		assertEquals(
				ignored,
				withException.stream()
						.map(maybe(i -> Thrower.mayThrowA(i)))
						.flatMap(o -> o.map(i -> Stream.of(i)).orElse(Stream.empty()))
						.collect(Collectors.toList()));
	}
}
