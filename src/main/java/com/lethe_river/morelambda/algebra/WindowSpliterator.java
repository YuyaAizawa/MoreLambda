package com.lethe_river.morelambda.algebra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;


final class WindowSpliterators {

	static class Window2Spliterator<T> implements Spliterator<Tuple2<T, T>> {
		Spliterator<T> original;
		Window2 window;

		public Window2Spliterator(Spliterator<T> original) {
			this.original = Objects.requireNonNull(original);
		}

		@Override
		public boolean tryAdvance(Consumer<? super Tuple2<T, T>> action) {
			if(window == null) {
				initWindow();
			}
			return original.tryAdvance(t -> window.next(t, action));
		}

		@Override
		public Spliterator<Tuple2<T, T>> trySplit() {
			if(window == null) {
				initWindow();
			}

			if(original.estimateSize() < 2) {
				return null;
			}

			Spliterator<T> headSpliterator = original.trySplit();
			if(headSpliterator == null) {
				return null;
			}

			Window2 headWindow = window;

			List<T> tail = initWindow();
			headSpliterator = concat(headSpliterator, Spliterators.spliterator(tail.iterator(), tail.size(), original.characteristics()));

			Window2Spliterator<T> result = new Window2Spliterator<>(headSpliterator);
			result.window = headWindow;

			return result;
		}

		@Override
		public long estimateSize() {
			if(window == null) {
				initWindow();
			}
			return original.estimateSize();
		}

		@Override
		public int characteristics() {
			return original.characteristics();
		}

		/**
		 * windowを初期化する
		 * @return windowに要素が準備され，初期化が成功すればtrue
		 */
		private List<T> initWindow() {
			window = new Window2();
			List<T> loaded = new ArrayList<>(1);
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			return loaded;
		}

		class Window2 {
			Object[] array = new Object[2];
			int index;

			@SuppressWarnings("unchecked")
			public void next(T t, Consumer<? super Tuple2<T, T>> action) {
				array[index++] = t;
				if(index == 2) {
					index = 0;
				}

				action.accept(Tuple.of(
						(T)array[index],
						(T)array[index < 1 ? index+1 : index-1]));
			}
		}
	}

	static class Window3Spliterator<T> implements Spliterator<Tuple3<T, T, T>> {
		Spliterator<T> original;
		Window3 window;

		public Window3Spliterator(Spliterator<T> original) {
			this.original = Objects.requireNonNull(original);
		}

		@Override
		public boolean tryAdvance(Consumer<? super Tuple3<T, T, T>> action) {
			if(window == null) {
				initWindow();
			}
			return original.tryAdvance(t -> window.next(t, action));
		}

		@Override
		public Spliterator<Tuple3<T, T, T>> trySplit() {
			if(window == null) {
				initWindow();
			}

			if(original.estimateSize() < 3) {
				return null;
			}

			Spliterator<T> headSpliterator = original.trySplit();
			if(headSpliterator == null) {
				return null;
			}

			Window3 headWindow = window;

			List<T> tail = initWindow();
			headSpliterator = concat(headSpliterator, Spliterators.spliterator(tail.iterator(), tail.size(), original.characteristics()));

			Window3Spliterator<T> result = new Window3Spliterator<>(headSpliterator);
			result.window = headWindow;

			return result;
		}

		@Override
		public long estimateSize() {
			if(window == null) {
				initWindow();
			}
			return original.estimateSize();
		}

		@Override
		public int characteristics() {
			return original.characteristics();
		}

		/**
		 * windowを初期化する
		 * @return windowに要素が準備され，初期化が成功すればtrue
		 */
		private List<T> initWindow() {
			window = new Window3();
			List<T> loaded = new ArrayList<>(2);
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			return loaded;
		}

		class Window3 {
			Object[] array = new Object[3];
			int index;

			@SuppressWarnings("unchecked")
			public void next(T t, Consumer<? super Tuple3<T, T, T>> action) {
				array[index++] = t;
				if(index == 3) {
					index = 0;
				}

				action.accept(Tuple.of(
						(T)array[index],
						(T)array[index < 2 ? index+1 : index-2],
						(T)array[index < 1 ? index+2 : index-1]));
			}
		}
	}

	static class Window4Spliterator<T> implements Spliterator<Tuple4<T, T, T, T>> {
		Spliterator<T> original;
		Window4 window;

		public Window4Spliterator(Spliterator<T> original) {
			this.original = Objects.requireNonNull(original);
		}

		@Override
		public boolean tryAdvance(Consumer<? super Tuple4<T, T, T, T>> action) {
			if(window == null) {
				initWindow();
			}
			return original.tryAdvance(t -> window.next(t, action));
		}

		@Override
		public Spliterator<Tuple4<T, T, T, T>> trySplit() {
			if(window == null) {
				initWindow();
			}

			if(original.estimateSize() < 4) {
				return null;
			}

			Spliterator<T> headSpliterator = original.trySplit();
			if(headSpliterator == null) {
				return null;
			}

			Window4 headWindow = window;

			List<T> tail = initWindow();
			headSpliterator = concat(headSpliterator, Spliterators.spliterator(tail.iterator(), tail.size(), original.characteristics()));

			Window4Spliterator<T> result = new Window4Spliterator<>(headSpliterator);
			result.window = headWindow;

			return result;
		}

		@Override
		public long estimateSize() {
			if(window == null) {
				initWindow();
			}
			return original.estimateSize();
		}

		@Override
		public int characteristics() {
			return original.characteristics();
		}

		/**
		 * windowを初期化する
		 * @return windowに要素が準備され，初期化が成功すればtrue
		 */
		private List<T> initWindow() {
			window = new Window4();
			List<T> loaded = new ArrayList<>(3);
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			return loaded;
		}

		class Window4 {
			Object[] array = new Object[4];
			int index;

			@SuppressWarnings("unchecked")
			public void next(T t, Consumer<? super Tuple4<T, T, T, T>> action) {
				array[index++] = t;
				if(index == 4) {
					index = 0;
				}

				action.accept(Tuple.of(
						(T)array[index],
						(T)array[index < 3 ? index+1 : index-3],
						(T)array[index < 2 ? index+2 : index-2],
						(T)array[index < 1 ? index+3 : index-1]));
			}
		}
	}

	static class Window5Spliterator<T> implements Spliterator<Tuple5<T, T, T, T, T>> {
		Spliterator<T> original;
		Window5 window;

		public Window5Spliterator(Spliterator<T> original) {
			this.original = Objects.requireNonNull(original);
		}

		@Override
		public boolean tryAdvance(Consumer<? super Tuple5<T, T, T, T, T>> action) {
			if(window == null) {
				initWindow();
			}
			return original.tryAdvance(t -> window.next(t, action));
		}

		@Override
		public Spliterator<Tuple5<T, T, T, T, T>> trySplit() {
			if(window == null) {
				initWindow();
			}

			if(original.estimateSize() < 5) {
				return null;
			}

			Spliterator<T> headSpliterator = original.trySplit();
			if(headSpliterator == null) {
				return null;
			}

			Window5 headWindow = window;

			List<T> tail = initWindow();
			headSpliterator = concat(headSpliterator, Spliterators.spliterator(tail.iterator(), tail.size(), original.characteristics()));

			Window5Spliterator<T> result = new Window5Spliterator<>(headSpliterator);
			result.window = headWindow;

			return result;
		}

		@Override
		public long estimateSize() {
			if(window == null) {
				initWindow();
			}
			return original.estimateSize();
		}

		@Override
		public int characteristics() {
			return original.characteristics();
		}

		/**
		 * windowを初期化する
		 * @return windowに要素が準備され，初期化が成功すればtrue
		 */
		private List<T> initWindow() {
			window = new Window5();
			List<T> loaded = new ArrayList<>(4);
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			original.tryAdvance(t -> {window.next(t, u ->{});loaded.add(t);});
			return loaded;
		}

		class Window5 {
			Object[] array = new Object[5];
			int index;

			@SuppressWarnings("unchecked")
			public void next(T t, Consumer<? super Tuple5<T, T, T, T, T>> action) {
				array[index++] = t;
				if(index == 5) {
					index = 0;
				}

				action.accept(Tuple.of(
						(T)array[index],
						(T)array[index < 4 ? index+1 : index-4],
						(T)array[index < 3 ? index+2 : index-3],
						(T)array[index < 2 ? index+3 : index-2],
						(T)array[index < 1 ? index+4 : index-1]));
			}
		}
	}

	/**
	 * 同じ性質のSpliteratorを結合する．引数に渡したSpliteratorはトラバースしてはならない．
	 *
	 */
	static <T> Spliterator<T> concat(Spliterator<T> s1, Spliterator<T> s2) {
		if(s1.characteristics() != s2.characteristics()) {
			throw new Error();
		}
		return new Spliterator<T>() {

			// 前半 nullを許容
			Spliterator<T> sp1 = s1;

			// 後半
			Spliterator<T> sp2 = s2;
			@Override
			public boolean tryAdvance(Consumer<? super T> action) {
				return (sp1 != null && sp1.tryAdvance(action)) || sp2.tryAdvance(action);
			}

			@Override
			public void forEachRemaining(Consumer<? super T> action) {
				if(sp1 != null) {
					sp1.forEachRemaining(action);
				}
				sp2.forEachRemaining(action);
			}

			@Override
			public Spliterator<T> trySplit() {
				if(sp1 == null) {
					return sp2.trySplit();
				}
				long e1 = sp1.estimateSize();
				long e2 = sp2.estimateSize();

				Spliterator<T> head;
				if(e1 / 2 > e2) {
					head = sp1.trySplit();
				} else {
					if(e1 > e2 / 2) {
						head = sp1;
					} else {
						head = concat(sp1, sp2.trySplit());
					}
					sp1 = null;
				}
				return head;
			}

			@Override
			public long estimateSize() {
				long est = sp2.estimateSize();
				if(sp1 != null) {
					est += sp1.estimateSize();
				}
				return est < 0 ? Long.MAX_VALUE : est;
			}

			@Override
			public int characteristics() {
				return sp2.characteristics();
			}

			@Override
			public Comparator<? super T> getComparator() {
				return sp2.getComparator();
			}

		};
	}
}


