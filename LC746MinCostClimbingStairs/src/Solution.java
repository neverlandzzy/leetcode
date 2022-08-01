
public class Solution {
	/**
	 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).
	 * 
	 * Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, 
	 * and you can either start from the step with index 0, or the step with index 1.
	 * 
	 * Example 1:
	 * Input: cost = [10, 15, 20]
	 * Output: 15
	 * Explanation: Cheapest is start on cost[1], pay that cost and go to the top.
	 * 
	 * Example 2:
	 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
	 * Output: 6
	 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
	 * 
	 * Note:
	 *  1. cost will have a length in the range [2, 1000].
	 *  2. Every cost[i] will be an integer in the range [0, 999].
	 */
	
    public static int minCostClimbingStairs(int[] cost) {
    	
    	// Solution 1: DP: Time O(n), Space: O(n)
    	/*
    	int n = cost.length;
    	// dp[i]: min cost to reach i
    	int[] dp = new int[n + 1];
    	
    	dp[0] = 0;
    	dp[1] = 0;
    	
    	for (int i = 2; i <= n; i++) {
    		dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
    	}
    	
    	return dp[n];
    	*/
    	
    	// Solution 2: DP: Time O(n), Space: O(1)
    	int n = cost.length;
    	int cost0 = 0;
    	int cost1 = 0;
    	int min = 0;
    	
    	for (int i = 2; i <= n; i++) {
    		min = Math.min(cost0 + cost[i - 2], cost1 + cost[i - 1]);
    		cost0 = cost1;
    		cost1 = min;
    	}
    	
    	return min;
    	
    }
    
    public static void main(String[] args) {
		int[] test1 = {10, 15, 20};
		int[] test2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		
		System.out.println(minCostClimbingStairs(test1));
		System.out.println(minCostClimbingStairs(test2));
	}
}
