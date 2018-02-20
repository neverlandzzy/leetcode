
public class Solution {
	
	/*
	 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on 
	 * it represented by array nums. You are asked to burst all the balloons. If the you 
	 * burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and 
	 * right are adjacent indices of i. After the burst, the left and right then becomes 
	 * adjacent.
	 * 
	 * Find the maximum coins you can collect by bursting the balloons wisely.
	 * 
	 * Note: 
	 * (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can 
	 * not burst them.
	 * 
	 * (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
	 * 
	 * Example:
	 * 
	 * Given [3, 1, 5, 8]
	 * Return 167
	 *     nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
	 *    coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
	 */
	
	// https://discuss.leetcode.com/topic/30746/share-some-analysis-and-explanations/2
	// Similar to LC375GuessNumberHigherOrLowerII
	
    public static int maxCoins(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int[] newNums = new int[nums.length + 2];
        int n = newNums.length;
        
        newNums[0] = 1;
        newNums[n - 1] = 1;
        
        for (int i = 0; i < nums.length; i++) {
            newNums[i + 1] = nums[i];
        }
        
        // dp[i][j]: 把第i到第j个气球打爆能得到的最大值
        // k: 对于所有k属于[i, j]， 表示第k个气球最后打爆，所以在k处得到的值是区间外的两个气球[i - 1]和[j + 1] 与k的乘积
        int[][] dp = new int[n][n];
        
        
        for (int len = 1; len < n; len++) {
            for (int from = 1; from + len < n; from++) {
                int to = from + len - 1;
                
                for (int k = from; k <= to; k++) {
                    dp[from][to] = Math.max(dp[from][to], newNums[k] * newNums[from - 1] * newNums[to + 1] + dp[from][k - 1] + dp[k + 1][to]);
                }
            }
        }
        
        return dp[1][n - 2];
    }
    
    
	/*
    public static int maxCoins(int[] nums) {
    	
        int n = nums.length;
        int[] newNums = new int[n + 2];
        
        for (int i = 1; i <= n; i++) {
        	newNums[i] = nums[i - 1];
        }
        
        newNums[0] = 1;
        newNums[n + 1] = 1;
        
        int[][] dp = new int[n + 2][n + 2];
        // dp[i][j]: 把第i到第j个气球打爆能得到的最大值
        // k: 对于所有k属于[i, j]， 表示第k个气球最后打爆，所以在k处得到的值是区间外的两个气球[i - 1]和[j + 1] 与k的乘积
        
        for (int len = 1; len <= n; len++) {
        	for (int from = 1; from + len <= n + 1; from++) {
        		int to = from + len - 1;
        		for (int k = from; k <= to; k++) {
        			dp[from][to] = Math.max(dp[from][to], newNums[from - 1] * newNums[k] * newNums[to + 1] + dp[from][k - 1] + dp[k + 1][to]);
        		}
        		
        		System.out.println("from = " + from + " to = " + to + " len = " + len);
        	}
        }
                return dp[1][n];

    }
    */
    
    /*
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] newNums = new int[n + 2];
        int[][] dp = new int[n + 2][n + 2];
        
        for (int i = 1; i <= n; i++) {
        	newNums[i] = nums[i - 1];
        }
        
        newNums[0] = 1;
        newNums[n + 1] = 1;
        
        return helper(newNums, dp, 1, n);
    }
    
    private static int helper(int[] nums, int[][] dp, int start, int end) {
    	if (dp[start][end] != 0) {
    		return dp[start][end];
    	}
    	
    	int result = 0;
    	
    	for (int i = start; i <= end; i++) {
    		int left = helper(nums, dp, start, i - 1);
    		int right = helper(nums, dp, i + 1, end);
    		int cur = nums[start - 1] * nums[i] * nums[end + 1];
    		
    		result = Math.max(left + right + cur, result);
    	}
    	dp[start][end] = result;
    	return result;
    }
    */
    public static void main(String[] args) {
		int[] test = {3,1,5,8};
		
		System.out.println(maxCoins(test));
	}
}
