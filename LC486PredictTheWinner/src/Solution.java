
public class Solution {
	/**
	 * Given an array of scores that are non-negative integers. Player 1 picks one of the numbers from either end of the array followed 
	 * by the player 2 and then player 1 and so on. Each time a player picks a number, that number will not be available for the next player. 
	 * This continues until all the scores have been chosen. The player with the maximum score wins.
	 * 
	 * Given an array of scores, predict whether player 1 is the winner. You can assume each player plays to maximize his score.
	 * 
	 * Example 1:
	 * Input: [1, 5, 2]
	 * Output: False
	 * Explanation: Initially, player 1 can choose between 1 and 2. 
	 * If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
	 * So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
	 * Hence, player 1 will never be the winner and you need to return False.
	 * 
	 * Example 2:
	 * Input: [1, 5, 233, 7]
	 * Output: True
	 * Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. No matter which number player 2 choose, player 1 can choose 233.
	 * Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
	 * 
	 * Note:
	 * 1 <= length of the array <= 20.
	 * Any scores in the given array are non-negative integers and will not exceed 10,000,000.
	 * If the scores of both players are equal, then player 1 is still the winner.
	 */
	
	// https://leetcode.com/problems/predict-the-winner/#/solution
	
	// Solution 1: DFS + mem O(n^2)
	/*
    public static boolean PredictTheWinner(int[] nums) {
    	Integer[][] cache = new Integer[nums.length][nums.length];
        
    	return helper(cache, nums, 0, nums.length - 1) >= 0; 
    }
    
    private static int helper(Integer[][] cache, int[] nums, int start, int end) {
    	if (start == end) {
    		return nums[start];
    	}
    	
    	if (cache[start][end] != null) {
    		return cache[start][end];
    	}
    	
    	int a = nums[start] - helper(cache, nums, start + 1, end);
    	int b = nums[end] - helper(cache, nums, start, end - 1);
    	
    	cache[start][end] = Math.max(a, b);
    	return cache[start][end];
    }
    */
	
	// Solution 2: 2D - DP 
	
	// 从array头部开始遍历，矩阵从右上角开始填
	// dp[i][j] the difference between the scores of two players for subarray nums[i,j]
	// dp[i][i] = nums[i] -- 起始
	/*
	public static boolean PredictTheWinner(int[] nums) {
		int[][] dp = new int[nums.length][nums.length];
		for (int i = 0; i < nums.length; i++) {
			dp[i][i] = nums[i];
		}
        for (int len = 1; len < nums.length; len++) {
            for (int start = 0; start + len < nums.length; start++) {
                int end = start + len;
                dp[start][end] = Math.max(nums[start] - dp[start + 1][end], nums[end] - dp[start][end - 1]);

//				System.out.println("start = " + start + " end = " + end + " dp[start + 1][end] = " + dp[start + 1][end] + " dp[start][end - 1] = " + dp[start][end - 1]);
//                for (int[] k : dp) {
//                	for(int l: k) {
//                		System.out.print(l + ", ");
//                	}
//                	System.out.println();
//                }
//                System.out.println();

            }
        }
		return dp[0][nums.length - 1] >= 0;
	}
	*/

	// 从array末尾开始遍历，矩阵从右下角开始填 - 更清晰简洁
	public static boolean PredictTheWinner(int[] nums) {
		// dp[i][j] is used to store the difference between the scores of two players for subarray nums[i,j]
		int[][] dp = new int[nums.length][nums.length];
		
		for (int start = nums.length - 1; start >= 0; start--) {
			dp[start][start] = nums[start];
			for (int end = start + 1; end < nums.length; end++) {
				int a = nums[start] - dp[start + 1][end];
				int b = nums[end] - dp[start][end - 1];
				dp[start][end] = Math.max(a, b);

//				System.out.println("start = " + start + " end = " + end + " dp[start + 1][end] = " + dp[start + 1][end] + " dp[start][end - 1] = " + dp[start][end - 1]);
//				for (int[] k : dp) {
//					for(int l: k) {
//						System.out.print(l + ", ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
		}
		return dp[0][nums.length - 1] >= 0;
	}

	
	/*
	// Solution 3: 1D - DP
	public static boolean PredictTheWinner(int[] nums) {
		int[] dp = new int [nums.length];
		
		for (int start = nums.length - 1; start >= 0; start--) {
			for (int end = start; end < nums.length; end++) {
				if (start == end) {
					dp[start] = nums[start];
				} else {
					int a = nums[start] - dp[end];
					int b = nums[end] - dp[end - 1];
					dp[end] = Math.max(a, b);
				}
			}
		}
		
		return dp[nums.length - 1] >= 0;
	}
	*/
    public static void main(String[] args) {
		int[] test1 = {1, 5, 2};
		int[] test2 = {1, 5, 233, 7};
		int[] test3 = {1, 5, 2, 4, 6};
		
		//System.out.println(PredictTheWinner(test1));
		//System.out.println(PredictTheWinner(test2));
		System.out.println(PredictTheWinner(test3));
	}
}
