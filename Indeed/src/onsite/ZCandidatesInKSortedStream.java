package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import onsite.CandidatesInKSortedStream.PeekingIterator;

public class ZCandidatesInKSortedStream {

	public static List<Integer> findConsensus(List<PeekingIterator> streams, int K){

	}
	
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>(Arrays.asList(0, 0, 1, 1, 2, 3, 3, 4, 5, 6, 7, 10));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 3, 4, 5, 5, 6, 7, 10));
		List<Integer> list3 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 4, 4, 4, 11));
		List<Integer> list4 = new ArrayList<>(Arrays.asList(5, 6, 8, 9, 10));
		List<Integer> list5 = new ArrayList<>(Arrays.asList());
		List<Integer> list6 = new ArrayList<>(Arrays.asList(1));
		
		Iterator<Integer> iterator1 = list1.iterator();
		Iterator<Integer> iterator2 = list2.iterator();
		Iterator<Integer> iterator3 = list3.iterator();
		Iterator<Integer> iterator4 = list4.iterator();
		Iterator<Integer> iterator5 = list5.iterator();
		Iterator<Integer> iterator6 = list6.iterator();
		
		PeekingIterator pIterator1 = new PeekingIterator(iterator1);
		PeekingIterator pIterator2 = new PeekingIterator(iterator2);
		PeekingIterator pIterator3 = new PeekingIterator(iterator3);
		PeekingIterator pIterator4 = new PeekingIterator(iterator4);
		PeekingIterator pIterator5 = new PeekingIterator(iterator5);
		PeekingIterator pIterator6 = new PeekingIterator(iterator6);
				
		List<PeekingIterator> streams = new ArrayList<>();
		
		streams.add(pIterator1);
		streams.add(pIterator2);
		streams.add(pIterator3);
		streams.add(pIterator4);
		streams.add(pIterator5);
		streams.add(pIterator6);
	
		System.out.println(findConsensus(streams, 3));
	}
	
	
	public static class PeekingIterator implements Iterator<Integer> {
		
		private Integer next;
		Iterator<Integer> peekingIterator;
		
		public PeekingIterator(Iterator<Integer> iterator) {
		    // initialize any member here.
			
			if (!iterator.hasNext()) {
				return;
			}
			peekingIterator = iterator;
			next = iterator.next();
		    
		}

	    // Returns the next element in the iteration without advancing the iterator.
		public Integer peek() {
	        return next;
		}

		// hasNext() and next() should behave the same as in the Iterator interface.
		// Override them if needed.
		@Override
		public Integer next() {
			Integer res = next; 
		    
		    if (peekingIterator.hasNext()) {
		    	next = peekingIterator.next();
		    } else {
		    	next = null;
		    }
		    
		    return res;
		}

		@Override
		public boolean hasNext() {
			return next != null;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

	}
}
