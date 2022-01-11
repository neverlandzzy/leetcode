import java.util.Arrays;


public class Solution {
	/**
	 * In an N x N grid representing a field of cherries, each cell is one of three possible integers.
	 * 
	 * 0 means the cell is empty, so you can pass through;
	 * 1 means the cell contains a cherry, that you can pick up and pass through;
	 * -1 means the cell contains a thorn that blocks your way.
	 * Your task is to collect maximum number of cherries possible by following the rules below:
	 * 
	 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or down through valid path cells (cells with value 0 or 1);
	 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through valid path cells;
	 * When passing through a path cell containing a cherry, you pick it up and the cell becomes an empty cell (0);
	 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries can be collected.
	 * 
	 * Example 1:
	 * Input: grid =
	 * [[0, 1, -1],
	 *  [1, 0, -1],
	 *  [1, 1,  1]]
	 * Output: 5
	 * Explanation: 
	 * The player started at (0, 0) and went down, down, right right to reach (2, 2).
	 * 4 cherries were picked up during this single trip, and the matrix becomes [[0,1,-1],[0,0,-1],[0,0,0]].
	 * Then, the player went left, up, up, left to return home, picking up one more cherry.
	 * The total number of cherries picked up is 5, and this is the maximum possible.
	 * Note:
	 * 
	 * grid is an N by N 2D array, with 1 <= N <= 50.
	 * Each grid[i][j] is an integer in the set {-1, 0, 1}.
	 * It is guaranteed that grid[0][0] and grid[N-1][N-1] are not -1.
	 */
	
	// https://leetcode.com/articles/cherry-pickup/
	// https://discuss.leetcode.com/topic/112877/annotated-c-dp-solution
	// 一个常见的错误是分别计算从(0, 0)到(N-1, N-1)和从(N-1, N-1)到(0, 0)的两条路径最大值。
	// 正确解法是将回来的路线翻转，因此题目转化为找从(0, 0)到(N-1, N-1)同时走两条路径能获得的最大值。当同一时刻，两个人走到(i1, j1)和(i2, j2)时，两个坐标关系为：
	// i1 + j1 = i2 + j2 = t  t是步数，(0 ~ 2N-2), 因为相当于横纵坐标分别从0走到N-1。
	
    public static int cherryPickup(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }
        
        int n = grid.length;
        // dp[i][j]: 两条路径分别走到(i, t - i)和(j, t - j)时摘得最多的樱桃数量
        int[][] dp = new int[n][n];
        for (int[] row: dp) Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][0] = grid[0][0];
        
        // 遍历走的步数
        for (int t = 1; t <= 2 * n - 2; t++) {

        	int[][] tmp = new int[n][n]; 
        	for (int[] row: tmp) {
        		Arrays.fill(row, Integer.MIN_VALUE);
			}
        	
        	// 当步数为t时，第一条路径到达(i, t - i)
        	for (int i = Math.max(0, t - (n - 1)); i <= Math.min(n - 1, t); i++) {
               
        		// 当步数为t时，第二条路径到达(j, t - j)
        		for (int j = Math.max(0, t - (n - 1)); j <= Math.min(n - 1, t); j++) {
        			
        			if (grid[i][t - i] == -1 || grid[j][t - j] == -1) {
        				continue;
        			}
        			// 当前两条路径摘得的樱桃数量和
        			int val = dp[i][j];
        			
        			if (i > 0) {
        				val = Math.max(val, dp[i - 1][j]);
        			}
        			if (j > 0) {
        				val = Math.max(val, dp[i][j - 1]);
        			}
        			
        			if (i > 0 && j > 0) {
        				val = Math.max(val, dp[i - 1][j - 1]);
        			}
        			
        			if (val < 0) {
        				// 没办法走到 (i, t - i) 和 (j, t - j).
        				continue;
        			}
        			
        			//加上当前点的樱桃数量，当i == j时，只加一次，注意不要重复 -- 这也是常见错误解法错误的原因
        			val += grid[i][t - i] + (i == j ? 0 : grid[j][t - j]);
        			tmp[i][j] = val;
        		}
        	}
        	
        	dp = tmp;
        }
        
        return Math.max(0, dp[n-1][n-1]);
    }
    
    public static void main(String[] args) {
		int[][] test = {{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
		
		System.out.println(cherryPickup(test));
	}
}
