
public class Solution {
	/**
	 * You are given coins of different denominations and a total amount of money amount. 
	 * Write a function to compute the fewest number of coins that you need to make up that amount. 
	 * If that amount of money cannot be made up by any combination of the coins, return -1.
	 * 
	 * Example 1:
	 * coins = [1, 2, 5], amount = 11
	 * return 3 (11 = 5 + 5 + 1)
	 * 
	 * Example 2:
	 * coins = [2], amount = 3
	 * return -1.
	 * 
	 * Note:
	 * You may assume that you have an infinite number of each kind of coin.
	 */
	
    public static int coinChange(int[] coins, int amount) {

        int[] dp = new int[amount + 1];
        
        for (int i = 1; i <= amount; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                int diff = i - coins[j];
                
                if (diff >= 0 && dp[diff] != -1) {
                    min = Math.min(min, dp[diff] + 1);
                }
            }
            
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        
        return dp[amount];
     
    }
    
    public static void main(String[] args) {
    	
    	int[] coins1 = {1, 2, 5};
    	int[] coins2 = {2};
    	
		System.out.println(coinChange(coins1, 11));
		System.out.println(coinChange(coins2, 3));
	}
}
