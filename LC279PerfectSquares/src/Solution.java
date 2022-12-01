
public class Solution {
	/**
	 * Given a positive integer n, find the least number of perfect square numbers 
	 * (for example, 1, 4, 9, 16, ...) which sum to n.
	 * 
	 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, 
	 * return 2 because 13 = 4 + 9.
	 */
	
	// dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
	// https://discuss.leetcode.com/topic/26400/an-easy-understanding-dp-solution-in-java
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                min = Math.min(min, dp[i - j * j]);
            }
            
            dp[i] = min + 1;
        }
        
        return dp[n];
        
    }
    
    public static void main(String[] args) {
		System.out.println(numSquares(13));
		System.out.println(numSquares(12));
		System.out.println(numSquares(1));
	}
}
