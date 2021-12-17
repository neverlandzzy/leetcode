import java.util.Arrays;
import java.util.HashSet;


public class Solution {
	/**
	 * Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets such 
	 * that the sum of elements in both subsets is equal.
	 * 
	 * Note:
	 * Each of the array element will not exceed 100.
	 * The array size will not exceed 200.
	 * 
	 * Example 1:
	 * 
	 * Input: [1, 5, 11, 5]
	 * 
	 * Output: true
	 * 
	 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
	 * 
	 * Example 2:
	 * 
	 * Input: [1, 2, 3, 5]
	 * 
	 * Output: false
	 * 
	 * Explanation: The array cannot be partitioned into equal sum subsets.
	 */
	
	// https://discuss.leetcode.com/topic/67539/0-1-knapsack-detailed-explanation
    public static boolean canPartition(int[] nums) {
    	
    	// 2D - DP
    	
    	/*
    	int sum = 0;
    	
    	for (int n: nums) {
    		sum += n;
    	}
    	
    	// sum 为奇数则不可能
    	if ((sum & 1) == 1) {
    		return false;
    	}
    	
    	sum /= 2;
    	
    	boolean[][] dp = new boolean[nums.length][sum + 1]; //dp[i][j]: nums的(nums[0], ... nums[i])能否获得sum == j. 
    	dp[0][0] = true; // nums的前0项能获得sum == 0;
    	
    	for (int i = 1; i < nums.length; i++) {
    		dp[i][0] = true;
    	}
    	
    	for (int i = 1; i <= sum; i++) {
    		dp[0][i] = false;
    	}
    	
    	for (int i = 1; i < nums.length; i++) {
    		for (int j = 1; j <= sum; j++) {
    			dp[i][j] = dp[i - 1][j]; // 不要nums[i]
    			if (j - nums[i] >= 0) {
    				dp[i][j] = dp[i - 1][j - nums[i]] || dp[i][j]; // 要nums[i]
    			}
    		}
    	}
    	
    	return dp[nums.length - 1][sum];
    	*/
    	// 1D - DP 
    	
    	int sum = 0;
    	
    	for (int n: nums) {
    		sum += n;
    	}

    	if ((sum & 1) == 1) {
    		return false;
    	}
    	
    	sum /= 2;
    	
    	boolean[] dp = new boolean[sum + 1];
    	dp[0] = true;
    	
    	for (int i = 1; i < nums.length; i++) {
    		// Transition function for 1d solution
    		// dp[i] = dp[i] || dp[i - nums[i]];
    		// dp[i] will depend on dp[i - nums[i]];
    		// "i" always large than "i - nums[i]" , we can't have dp[i-nums] be calculated and overwritten before we do it for dp[i]
    		// that's why the loop go from high to low.
    		// in 2d solution, we don't need to worry about intermediate result been overwrite
    		// 01背包 空间优化 -- 倒序
    		for (int j = sum; j >= 0; j--) {
    			if (j - nums[i] >= 0) {
    				dp[j] = dp[j] || dp[j - nums[i]];
    			}
    		}
    	}
    	
    	return dp[sum];
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 5, 11, 5};
		int[] test2 = {1, 2, 3, 5};
		
		System.out.println(canPartition(test1));
		System.out.println(canPartition(test2));
	}
}
