import java.util.Stack;


public class Solution {
	/*
	 * Given a circular array (the next element of the last element is the first element of the array), 
	 * print the Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to 
	 * its traversing-order next in the array, which means you could search circularly to find its next greater number. 
	 * If it doesn't exist, output -1 for this number.
	 * 
	 * Example 1:
	 * Input: [1,2,1]
	 * Output: [2,-1,2]
	 * Explanation: The first 1's next greater number is 2; 
	 * The number 2 can't find next greater number; 
	 * The second 1's next greater number needs to search circularly, which is also 2.
	 * Note: The length of given array won't exceed 10000.
	 */
	
	// Time: O(n), Space: O(n)
    public static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n * 2 - 1; i >= 0; i--) {
        	while (!stack.isEmpty() && nums[stack.peek()] <= nums[i % n]) {
        		stack.pop();
        	}
        	
        	result[i % n] = stack.isEmpty() ? -1 : nums[stack.peek()];
            stack.push(i % n);
        }
        
        return result;
    }
    
    private static void print(int[] nums) {
    	for (int n: nums) {
    		System.out.print(n + ", ");
    	}
    	
    	System.out.println();
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 2, 1};
		int[] result1 = nextGreaterElements(test1);
		
		print(result1);
	}
}
