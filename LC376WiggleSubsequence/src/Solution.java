
public class Solution {
	/**
	 * A sequence of numbers is called a wiggle sequence if the differences between successive numbers strictly alternate 
	 * between positive and negative. The first difference (if one exists) may be either positive or negative. A sequence 
	 * with fewer than two elements is trivially a wiggle sequence.
	 * 
	 * For example, [1,7,4,9,2,5] is a wiggle sequence because the differences (6,-3,5,-7,3) are alternately positive and negative. 
	 * In contrast, [1,4,7,2,5] and [1,7,4,5,5] are not wiggle sequences, the first because its first two differences are positive 
	 * and the second because its last difference is zero.
	 * 
	 * Given a sequence of integers, return the length of the longest subsequence that is a wiggle sequence. A subsequence 
	 * is obtained by deleting some number of elements (eventually, also zero) from the original sequence, leaving the remaining elements 
	 * in their original order.
	 * 
	 * Examples:
	 * Input: [1,7,4,9,2,5]
	 * Output: 6
	 * The entire sequence is a wiggle sequence.
	 * 
	 * Input: [1,17,5,10,13,15,10,5,16,8]
	 * Output: 7
	 * There are several subsequences that achieve this length. One is [1,17,10,13,10,16,8].
	 * 
	 * Input: [1,2,3,4,5,6,7,8,9]
	 * Output: 2
	 * 
	 * Follow up:
	 * Can you do it in O(n) time?
	 */
	
	// https://leetcode.com/problems/wiggle-subsequence/#/solution
    public static int wiggleMaxLength(int[] nums) {
    	
    	// Solution 1: Time: O(n) Space: O(n)
    	/*
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int length = nums.length;
        
    	int[] up = new int[length];
    	int[] down = new int[length];
    	
    	up[0] = 1;
    	down[0] = 1;
    	
    	for (int i = 1; i < nums.length; i++) {
    		if (nums[i] > nums[i - 1]) {
    			up[i] = down[i - 1] + 1;
    			down[i] = down[i - 1];
    		} else if (nums[i] < nums[i - 1]) {
    			down[i] = up[i - 1] + 1;
    			up[i] = up[i - 1];
    		} else {
    			down[i] = down[i - 1];
    			up[i] = up[i - 1];
    		}
    	}
    	
    	return Math.max(down[length - 1], up[length - 1]);
    	*/
    	
    	// Solution 2: Time: O(n) Space: O(1)
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int up = 1;
        int down = 1;
        
        for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i - 1]) {
				up = down + 1;
			} else if (nums[i] < nums[i - 1]) {
				down = up + 1;
			} 
        }
		return Math.max(down, up);
    }
    
    public static void main(String[] args) {
    	int[] test1 = {1,7,4,9,2,5};
    	int[] test2 = {1,17,5,10,13,15,10,5,16,8};
		int[] test3 = {1,2,3,4,5,6,7,8,9};
		
		System.out.println(wiggleMaxLength(test1));
		System.out.println(wiggleMaxLength(test2));
		System.out.println(wiggleMaxLength(test3));
	}
}
