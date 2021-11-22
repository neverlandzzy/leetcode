
public class Solution {
	/**
	 * Follow up for "Unique Paths":
	 * 
	 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
	 * 
	 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
	 * 
	 * For example,
	 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
	 * 	[
	 * 		[0,0,0],
	 * 		[0,1,0],
	 * 		[0,0,0]
	 * 	]
	 * The total number of unique paths is 2.
	 * 
	 */
	
	/*
	 * Solution 1: 2d DP Time: O(m*n) Space(m*n)
	 *
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        if (obstacleGrid[0][0] == 1) {
        	return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        
        int[][] d = new int[m][n];
        
        d[0][0] = 1;
        
        for (int i = 1; i < m; i++) {
        	d[i][0] = (obstacleGrid[i][0] == 1) ? 0 : d[i-1][0];        	
        }
        
        for (int i = 1; i < n; i++) {
        	d[0][i] = (obstacleGrid[0][i] == 1) ? 0 : d[0][i-1];          	
        }
        
        for (int i = 1; i < m; i++) {
        	for (int j = 1; j < n; j++) {
        		d[i][j]  = (obstacleGrid[i][j] == 1) ? 0 : d[i-1][j] +d[i][j-1];
        	}
        }
        
        return d[m-1][n-1];
    	
    }
    */
	
	// 	Solution 2: 1d DP Time: O(m*n) Space(min(m,n))
	
	public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
		
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        int min = Math.min(m, n);
        int max = Math.max(m, n);
        
        int[] d = new int[min];
        
        d[0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        
        for (int i = 1; i < min; i++) {
            if (m > n) {
                d[i] = obstacleGrid[0][i] == 1 ? 0 : d[i - 1];
            } else {
                d[i] = obstacleGrid[i][0] == 1 ? 0 : d[i - 1];
            }
        }
        
        for (int i = 1; i < max; i++) {
            if (m > n) {
                d[0] = obstacleGrid[i][0] == 1 ? 0 : d[0];
            } else {
                d[0] = obstacleGrid[0][i] == 1 ? 0 : d[0];
            }
            
            for (int j = 1; j < min; j++) {
                if (m > n) {
                    d[j] = obstacleGrid[i][j] == 1 ? 0 : d[j - 1] + d[j];
                } else {
                    d[j] = obstacleGrid[j][i] == 1 ? 0 : d[j - 1] + d[j];
                }
            }
        }
        
        return d[min - 1];
		
	}
	
    public static void main(String[] args) {
		int[][] test = {{0,0,0}, {0,1,0}, {0,0,0}};
		int[][] test2 = {{0,0,0,0,0,1}};
		int[][] test3 = {{0,1}};
		int[][] test4 = {{0,0}, {1,0}};
		
		System.out.println(uniquePathsWithObstacles(test));
		System.out.println(uniquePathsWithObstacles(test2));
		System.out.println(uniquePathsWithObstacles(test3));
		System.out.println(uniquePathsWithObstacles(test4));
	}
}
