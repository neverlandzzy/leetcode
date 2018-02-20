
public class Solution {
	/*
	 * Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you 
	 * can kill using one bomb.
	 * 
	 * The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too 
	 * strong to be destroyed.
	 * 
	 * Note that you can only put the bomb at an empty cell.
	 * 
	 * Example:
	 * For the given grid
	 * 
	 * 0 E 0 0
	 * E 0 W E
	 * 0 E 0 0
	 * 
	 * return 3. (Placing a bomb at (1,1) kills 3 enemies)
	 */
	
    public static int maxKilledEnemies(char[][] grid) {
    	// 注意： 要先判断 grid.length ==  0，再判断 grid[0] == null
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        int max = 0; 
        
        // scan from left to right and then right to left
        for (int i = 0; i < m; i++) {
        	int counter = 0;
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == '0') {
        			dp[i][j] += counter;
        		} else if (grid[i][j] == 'E') {
        			counter ++;
        		} else if (grid[i][j] == 'W') {
        			counter = 0;
        		}
        	}
        	
        	counter = 0;
        	
        	for (int j = n - 1; j >= 0; j--) {
        		if (grid[i][j] == '0') {
        			dp[i][j] += counter;
        		} else if (grid[i][j] == 'E') {
        			counter ++;
        		} else if (grid[i][j] == 'W') {
        			counter = 0;
        		}	
        	}
        }
        
        // scan from top to bottom and then bottom to top
        for (int j = 0; j < n; j++) {
        	int counter = 0;
        	for (int i = 0; i < m; i++) {
        		if (grid[i][j] == '0') {
        			dp[i][j] += counter;
        		} else if (grid[i][j] == 'E') {
        			counter ++;
        		} else if (grid[i][j] == 'W') {
        			counter = 0;
        		}
        	}
        	
        	counter = 0;
        	
        	for (int i = m - 1; i >= 0; i--) {
        		if (grid[i][j] == '0') {
        			dp[i][j] += counter;
        			max = Math.max(max, dp[i][j]);
        		} else if (grid[i][j] == 'E') {
        			counter ++;
        		} else if (grid[i][j] == 'W') {
        			counter = 0;
        		}	
        	}
        }
        
        return max;
    }
    
    public static void main(String[] args) {
		char[][] test = {{'0', 'E', '0', '0'}, 
						 {'E', '0', 'W', 'E'}, 
						 {'0', 'E', '0', '0'}};
		
		System.out.println(maxKilledEnemies(test));
	}
}
