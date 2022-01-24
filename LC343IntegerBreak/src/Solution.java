
public class Solution {
	/**
	 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. 
	 * Return the maximum product you can get.
	 * 
	 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
	 * Note: You may assume that n is not less than 2 and not larger than 58.
	 */
	
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i ++) {
            for(int j = 1; 2 * j <= i; j ++) {
            	dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i - j, dp[i - j])); 
            }
        }
        
        return dp[n];
    }
    
    public static void main(String[] args) {
		//System.out.println(integerBreak(2));
		System.out.println(integerBreak(10));
	}
}
