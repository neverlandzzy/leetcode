
public class Solution {
	/*
	 * On a N * N grid, we place some 1 * 1 * 1 cubes.
	 * 
	 * Each value v = grid[i][j] represents a tower of v cubes placed on top of grid cell (i, j).
	 * 
	 * Return the total surface area of the resulting shapes. 
	 * 
	 * Example 1:
	 * 
	 * Input: [[2]]
	 * Output: 10
	 * 
	 * Example 2:
	 * 
	 * Input: [[1,2],[3,4]]
	 * Output: 34
	 * 
	 * Example 3:
	 * 
	 * Input: [[1,0],[0,2]]
	 * Output: 16
	 * 
	 * Example 4:
	 * 
	 * Input: [[1,1,1],[1,0,1],[1,1,1]]
	 * Output: 32
	 * 
	 * Example 5:
	 * 
	 * Input: [[2,2,2],[2,1,2],[2,2,2]]
	 * Output: 46 
	 * 
	 * Note:
	 * 	1. 1 <= N <= 50
	 * 	2. 0 <= grid[i][j] <= 50
	 */

	// Time: O(n^2), Space: O(1)
    public static int surfaceArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] > 0) {
        			result += 2;
        			for (int[] dir: direction) {
            			int nextI = i + dir[0];
            			int nextJ = j + dir[1];
            			if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) {
            				result += grid[i][j];
            			} else if (grid[nextI][nextJ] < grid[i][j]) {
            				result += grid[i][j] - grid[nextI][nextJ];
            			}        				
        			}
        		}
        	}
        }
        
        return result;
        
    }
    
	// Time: O(n^2), Space: O(n^2)
	/*
    public static int surfaceArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int result = 0;
        
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] > 0) {
        			dp[i][j] = 2;
        		}
        	}
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		for (int[] dir: direction) {
        			int nextI = i + dir[0];
        			int nextJ = j + dir[1];
        			
        			if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) {
        				dp[i][j] += grid[i][j];
        			} else if (grid[i][j] > grid[nextI][nextJ]) {
        				dp[i][j] += grid[i][j] - grid[nextI][nextJ];
        			}
        			
        		}
        	}
        }
        
        for (int[] d: dp) {
        	for (int k: d) {
        		result += k;
        	}
        }
        
        return result;        
    }
    */

    
    public static void main(String[] args) {
		int[][] test11 = {{2}};
		int[][] test12 = {{1,2},{3, 4}};
		int[][] test13 = {{1,0},{0,2}};
		int[][] test14 = {{1,1,1},{1,0,1},{1,1,1}};
		int[][] test15 = {{2,2,2},{2,1,2},{2,2,2}};
		
		System.out.println(surfaceArea(test11));
		System.out.println(surfaceArea(test12));
		System.out.println(surfaceArea(test13));
		System.out.println(surfaceArea(test14));
		System.out.println(surfaceArea(test15));
	}
}
