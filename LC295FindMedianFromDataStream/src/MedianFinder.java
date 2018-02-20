import java.util.Comparator;
import java.util.PriorityQueue;


public class MedianFinder {
	/*
	 * Median is the middle value in an ordered integer list. If the size of the list is even, 
	 * there is no middle value. So the median is the mean of the two middle value.
	 * 
	 * Examples: 
	 * [2,3,4] , the median is 3
	 * [2,3], the median is (2 + 3) / 2 = 2.5
	 * Design a data structure that supports the following two operations:
	 * 
	 * void addNum(int num) - Add a integer number from the data stream to the data structure.
	 * double findMedian() - Return the median of all elements so far.
	 */
	
	// https://leetcode.com/discuss/64811/easy-to-understand-double-heap-solution-in-java
	static class ReversedOrder implements Comparator<Integer> {
		 
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}
	}
	
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(1, new ReversedOrder()); 
        
        /* another way to re-define Comparator 
        maxHeap = new PriorityQueue<>(1, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        */
    }
    
    // Adds a number into the data structure.
    public void addNum(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
        	maxHeap.add(num);
        } else {
        	minHeap.add(num);
        }
        
        if (maxHeap.size() - minHeap.size() >= 2) {
        	minHeap.add(maxHeap.poll());
        } else if (minHeap.size() - maxHeap.size() >= 2) {
        	maxHeap.add(minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {

        if (maxHeap.size() > minHeap.size()) {
        	return maxHeap.peek();
        } else if (minHeap.size() > maxHeap.size()) {
        	return minHeap.peek();
        } else {
        	return (double) (maxHeap.peek() + minHeap.peek())/2;
        }

    }
    
	
}
