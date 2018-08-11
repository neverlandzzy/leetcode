import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ZigzagIterator {
	/*
	 * Given two 1d vectors, implement an iterator to return their elements alternately.
	 * 
	 * Example:
	 * 
	 * Input:
	 * v1 = [1,2]
	 * v2 = [3,4,5,6] 
	 * 
	 * Output: [1,3,2,4,5,6]
	 * 
	 * Explanation: By calling next repeatedly until hasNext returns false, 
	 *              the order of elements returned by next should be: [1,3,2,4,5,6].
	 *              
	 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
	 * 
	 * Clarification for the follow up question:
	 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, 
	 * replace "Zigzag" with "Cyclic". For example:
	 * 
	 * Input:
	 * [1,2,3]
	 * [4,5,6,7]
	 * [8,9]
	 * 
	 * Output: [1,4,8,2,5,9,3,6,7].
	 */
	
	Queue<Iterator<Integer>> queue;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<>();
        if (!v1.isEmpty()) {
        	queue.offer(v1.iterator());
        }
        
        if (!v2.isEmpty()) {
        	queue.offer(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> iterator = queue.poll();
        int result = iterator.next();
        
        if (iterator.hasNext()) {
        	queue.offer(iterator);
        }
        
        return result;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
