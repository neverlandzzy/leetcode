
public class Solution {
	/*
	 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
	 * 
	 * The robot can only move either down or right at any point in time. 
	 * The robot is trying to reach the bottom-right corner of the grid 
	 * (marked 'Finish' in the diagram below).
	 * 
	 * How many possible unique paths are there?
	 */
	
	/*
	 * Solution 1: 2d DP Time: O(m*n) Space(m*n)
	 *
	 
    public static int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];
        
        if (m == 1 || n == 1) {
        	return 1;
        }
        
        
        for (int i = 0; i < m; i++) {
        	d[i][0] = 1;
        }
        
        for (int i = 0; i < n; i++) {
        	d[0][i] = 1;
        }
        
        for(int i = 1; i < m; i++) {
        	for(int j = 1; j < n; j++) {
        		d[i][j] = d[i][j-1] + d[i-1][j];
        	}
        }
        
        return d[m-1][n-1];
    }
    */
	
	// 	Solution 2: 1d DP Time: O(m*n) Space(min(m,n))
	 
    public static int uniquePaths(int m, int n) {
        if (m < n) {
            return uniquePaths(n, m);
        }
        
        int[] d = new int[n];
        
        for (int i = 0; i < n; i++) {
            d[i] = 1;
        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                d[j] = d[j - 1] + d[j];
            }
        }
        
        return d[n - 1];
    	/*
    	int min = Math.min(m, n);
    	int max = Math.max(m, n);
    	int[] d = new int[min];
    	
        for (int i = 0; i < min; i++) {
            d[i] = 1;
        }
        
        for (int i = 1; i < max; i++) {
        	for (int j = 1; j < min; j++) {
        		d[j] = d[j-1]+d[j];
        	}
        }
        return d[min-1];
        */
    }
    
    public static void main(String[] args) {
		System.out.println(uniquePaths(6,6));
	}
}
