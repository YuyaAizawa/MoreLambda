package com.lethe_river.morelambda.algebra;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * ListをトラバースするためのSpliterator．
 *
 * trySplitで分割されたSpliteratorはListを正確に2等分する．
 * @author YuyaAizawa
 *
 * @param <T>
 */
final class ListHalfSplitSpliterator<T> implements Spliterator<T> {

	int count;
	List<T> list;
	Iterator<T> iterator;

	public ListHalfSplitSpliterator(List<T> list) {
		if(list.size() == Integer.MAX_VALUE) {
			throw new IllegalArgumentException("Sized list only");
		}
		this.list = list;
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		if(iterator == null){
			iterator = list.iterator();
		}

		if(iterator.hasNext()) {
			action.accept(iterator.next());
			count++;
			return true;
		}
		return false;
	}

	@Override
	public ListHalfSplitSpliterator<T> trySplit() {
		if(iterator == null){
			iterator = list.iterator();
		}

		if(list.size() - count < 2) {
			return null;
		}

		int to = (list.size() - count/2)/2;
		ListHalfSplitSpliterator<T> result = new ListHalfSplitSpliterator<>(list.subList(count, to));

		count = 0;
		list = list.subList(to, list.size());
		iterator = list.iterator();
		return result;
	}

	@Override
	public void forEachRemaining(Consumer<? super T> action) {
		if(iterator == null){
			iterator = list.iterator();
		}

		int limit = list.size();
		while(count < limit) {
			action.accept(iterator.next());
			count++;
		}
	}

	@Override
	public long estimateSize() {
		if(iterator == null) {
			iterator = list.iterator();
		}
		return list.size() - count;
	}

	@Override
	public int characteristics() {
		if(iterator == null) {
			iterator = list.iterator();
		}
		return Spliterator.ORDERED | Spliterator.SUBSIZED;
	}
}
