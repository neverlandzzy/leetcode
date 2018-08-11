import java.util.LinkedList;
import java.util.Queue;


public class MovingAverage {
	
	/*
	 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
	 * 
	 * Example:
	 * 
	 * MovingAverage m = new MovingAverage(3);
	 * m.next(1) = 1
	 * m.next(10) = (1 + 10) / 2
	 * m.next(3) = (1 + 10 + 3) / 3
	 * m.next(5) = (10 + 3 + 5) / 3
	 */
    /** Initialize your data structure here. */
	
	int sum;
	int size;
	Queue<Integer> queue;
	
    public MovingAverage(int size) {
    	this.sum = 0;
    	this.size = size;
    	queue = new LinkedList<>();
    }
    
    public double next(int val) {
        if (queue.size() == size) {
        	int remove = queue.poll();
        	this.sum -= remove;
        }
        
        queue.offer(val);
        this.sum += val;
        
        return (double)this.sum / (double)queue.size();
    }
}
