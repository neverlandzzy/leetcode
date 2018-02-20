
public class Solution {
	/*
	 * After robbing those houses on that street, the thief has found himself a new place 
	 * for his thievery so that he will not get too much attention. This time, all houses 
	 * at this place are arranged in a circle. That means the first house is the neighbor 
	 * of the last one. Meanwhile, the security system for these houses remain the same as 
	 * for those in the previous street.
	 * 
	 * Given a list of non-negative integers representing the amount of money of each house, 
	 * determine the maximum amount of money you can rob tonight without alerting the police.
	 */
	
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int rob1 = helper(nums, 2, nums.length - 2) + nums[0];
        int rob2 = helper(nums, 1, nums.length - 1);
        
        return Math.max(rob1, rob2);
    }
    
    private static int helper(int[] nums, int start, int end) {
        if (start > end) {
            return 0;
        }
        
        if (start == end) {
            return nums[start];
        }
        
        int[] dp = new int[nums.length];
        dp[start] = nums[start];
        dp[start + 1] = Math.max(nums[start], nums[start + 1]);
        
        for (int i = start + 2; i <= end; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        
        return dp[end];
    }
    
    // Solution 2: Space O(2n)  -- 空间复杂度高，但容易想
    
    /*
	public static int rob(int[] nums) {
     
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        if (n == 1) {
            return nums[0];
        }
        
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        
        dp1[0] = nums[0];
        dp1[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }
        
        dp2[0] = 0;
        dp2[1] = nums[1];
        
        for (int i = 2; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i]);
        }
        
        return Math.max(dp1[n - 2], dp2[n - 1]);
    }
     */
    
    public static void main(String[] args) {
		int[] money = {3, 5, 1, 10, 8, 7, 12, 5, 9};
		
    	//int[] money = {0, 0};
		System.out.println(rob(money));
	}
}
