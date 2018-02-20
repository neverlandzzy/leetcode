
public class Solution {
	/*
	 * Given a m x n grid filled with non-negative numbers, find a path from top left to 
	 * bottom right which minimizes the sum of all numbers along its path.
	 * 
	 * Note: You can only move either down or right at any point in time.
	 */
	
	/*
	 * Solution 1: 2d DP Time: O(m*n) Space(m*n)
	 *
    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] d = new int[m][n];
        
        d[0][0] = grid[0][0];
        
        for(int i = 1; i < n; i++) {
        	d[0][i] = d[0][i-1] + grid[0][i];
        }
        
        for(int i = 1; i < m; i++) {
        	d[i][0] = d[i-1][0] + grid[i][0];
        }
        
        for(int i = 1; i < m; i++) {
        	for(int j = 1; j < n; j++) {
        		d[i][j] = Math.min(d[i-1][j], d[i][j-1]) + grid[i][j];
        	}
        }
        
        return d[m-1][n-1];
    }
    */

	// 	Solution 2: 1d DP Time: O(m*n) Space(min(m,n))
	
	public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        int min = Math.min(m, n);
        int max = Math.max(m, n);
        
        int[] dp = new int[min];
        
        dp[0] = grid[0][0];
        
        for (int i = 1; i < min; i++) {
            if (m > n) {
                dp[i] = dp[i - 1] + grid[0][i];
            } else {
                dp[i] = dp[i - 1] + grid[i][0];
            }
        }
        
        for (int i = 1; i < max; i++) {
            if (m > n) {
                dp[0] = dp[0] + grid[i][0];
            } else {
                dp[0] = dp[0] + grid[0][i];
            }
            
            for (int j = 1; j < min; j++) {
                if (m > n) {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[j][i];
                }
            }
        }
        
        return dp[min - 1];
	}
    
    public static void main(String[] args) {
 		int[][] test = {{3,2,5}, {4,7,3}, {6,2,1}};
 		int[][] test2 = {{1,2,5}, {3,2,1}};
 		int[][] test3 = {{0}};
 		int[][] test4 = {{9,1,4,8}};
 		
 		System.out.println(minPathSum(test));
 		System.out.println(minPathSum(test2));
 		System.out.println(minPathSum(test3));
 		System.out.println(minPathSum(test4));
 	}
}
