import java.util.ArrayDeque;
import java.util.Deque;


public class Solution {
	/**
	 * Given an array nums, there is a sliding window of size k which is moving from the very 
	 * left of the array to the very right. You can only see the k numbers in the window. 
	 * Each time the sliding window moves right by one position.
	 * 
	 * For example,
	 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
	 * 
	 * Window position                Max
	 * ---------------               -----
	 * [1  3  -1] -3  5  3  6  7       3
	 *  1 [3  -1  -3] 5  3  6  7       3
	 *  1  3 [-1  -3  5] 3  6  7       5
	 *  1  3  -1 [-3  5  3] 6  7       5
	 *  1  3  -1  -3 [5  3  6] 7       6
	 *  1  3  -1  -3  5 [3  6  7]      7
	 *  
	 * Therefore, return the max sliding window as [3,3,5,5,6,7].
	 * 
	 * Note: 
	 * You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
	 * 
	 * Follow up:
	 * Could you solve it in linear time?
	 */

	// Similar to LC862
    public static int[] maxSlidingWindow(int[] nums, int k) {
    	
    	if (nums == null || nums.length == 0) {
    		return new int[0];
    	}
    	
        Deque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] result = new int[n - k + 1];
        int j = 0;
        
        for (int i = 0; i < n; i++) {
        	while (!deque.isEmpty() && deque.peek() < i - k + 1) {
        		deque.poll();
        	}
        	
        	while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
        		deque.pollLast();
        	}
        	
        	deque.offer(i);
        	
        	if (i >= k - 1) {
        		result[j] = nums[deque.peek()];
        		j++;
        	}

        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test = {1,3,-1,-3,5,3,6,7};
		int[] res = maxSlidingWindow(test, 3);
		
		for (int i: res) {
			System.out.print(i + ",");
		}
		
	}
}
