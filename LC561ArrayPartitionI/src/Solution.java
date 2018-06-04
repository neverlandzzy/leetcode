import java.util.Arrays;


public class Solution {
	/*
	 * Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) 
	 * which makes sum of min(ai, bi) for all i from 1 to n as large as possible.
	 * 
	 * Example 1:
	 * Input: [1,4,3,2]
	 * 
	 * Output: 4
	 * Explanation: n is 2, and the maximum sum of pairs is 4 = min(1, 2) + min(3, 4).
	 * Note:
	 * n is a positive integer, which is in the range of [1, 10000].
	 * All the integers in the array will be in the range of [-10000, 10000].
	 */
	
	/*
	 * The total sum will now be maximum if the overall loss incurred from such pairings is minimized. 
	 * This minimization of loss in every pairing is possible only if the numbers chosen for the pairings lie 
	 * closer to each other than to the other elements of the array.
	 */
	
	// Solution 1: Time: O(nlogn), Space:O(1)
	/*
    public static int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        
        int sum = 0;
        
        for (int i = 0; i < nums.length; i += 2) {
        	sum += nums[i];
        }
        
        return sum;
    }
    */
	
	// Solution 2: Time: O(n), Space:O(n)
	// 类似桶排序的思想
	// https://leetcode.com/problems/array-partition-i/discuss/102180/Java-O(n)-beats-100
    public static int arrayPairSum(int[] nums) {
    	int[] arr = new int[20001];
    	for (int i: nums) {
    		arr[i + 10000]++;
    	}
    	
    	int sum = 0;
    	boolean odd = true;
    	
    	for (int i = 0; i < arr.length; i++) {
    		while (arr[i] > 0) {
    			if (odd) {
    				sum += i - 10000;
    			}
    			odd = !odd;
    			arr[i]--;
    		}
    	}

    	return sum;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 4, 3, 2};
		
		System.out.println(arrayPairSum(test1));
	}
}
