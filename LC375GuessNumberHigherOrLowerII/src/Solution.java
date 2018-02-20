
public class Solution {
	/*
	 * We are playing the Guess Game. The game is as follows:
	 * 
	 * I pick a number from 1 to n. You have to guess which number I picked.
	 * 
	 * Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.
	 * 
	 * However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.
	 * 
	 * Example:
	 * 
	 * n = 10, I pick 8.
	 * 
	 * First round:  You guess 5, I tell you that it's higher. You pay $5.
	 * Second round: You guess 7, I tell you that it's higher. You pay $7.
	 * Third round:  You guess 9, I tell you that it's lower. You pay $9.
	 * 
	 * Game over. 8 is the number I picked.
	 * 
	 * You end up paying $5 + $7 + $9 = $21.
	 * Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
	 */
	
	// https://github.com/PersonalExcercise/leetcodeExercise/blob/master/src/prob375guessnumberhigherorlowerII.md
	// https://discuss.leetcode.com/topic/52100/dp-java-o-n-3-solution-with-explanation-15ms-17-lines
	// Similar to LC312BurstBalloons
	// MinMax
	
    public static int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        // dp[i][j] 在 [i, j]中猜的最小cost
        
        for (int len = 1; len < n; len++) {
        	for (int from = 1; from + len <= n; from++) {
        		int to = from + len;
        		dp[from][to] = Integer.MAX_VALUE;
        		for (int k = from; k < to; k++) {
        			// 若k <= to 则k+1 <= n + 1 --> 越界 
        			dp[from][to] = Math.min(dp[from][to], k + Math.max(dp[from][k - 1], dp[k + 1][to]));
        		}
        	}
        }
        
        /*
        for (int [] k: dp) {
        	for (int m: k) {
        		System.out.print(m + ", ");
        	}
        	System.out.println();
        }
        */
        return dp[1][n];
    }
    
    
    public static void main(String[] args) {
    	System.out.println(getMoneyAmount(10));
	}
}
