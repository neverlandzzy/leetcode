import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/**
	 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally 
	 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
	 * 
	 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
	 * 
	 * Example 1:
	 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
	 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
	 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
	 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
	 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
	 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
	 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
	 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
	 * Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
	 * 
	 * Example 2:
	 * [[0,0,0,0,0,0,0,0]]
	 * Given the above grid, return 0.
	 * Note: The length of each dimension in the given grid does not exceed 50.
	 */
	
    public static int maxAreaOfIsland(int[][] grid) {
    	if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
    		return 0;
    	}
    	
        int result = 0;
        
        for (int i = 0; i < grid.length; i++) {
        	for (int j = 0; j < grid[0].length; j++) {
        		if (grid[i][j] == 1) {
        			//result = Math.max(result, bfs(grid, i, j));
        			result = Math.max(result, dfs(grid, i, j));
        		}
        	}
        }
        
        return result;
    }
    
    // Solution 1: bfs Time: O(m * n) Space: O(1) or O(m * n) if input is immutable
    private static int bfs(int[][] grid, int i, int j) {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0 ,-1}};
        Queue<int[]> queue = new LinkedList<>();
        int area = 1;
        queue.offer(new int[] {i, j});
        grid[i][j] = -1;
        
        while (!queue.isEmpty()) {
        	int[] point = queue.poll();
        	
        	for (int[] dir: directions) {
        		int nextI = point[0] + dir[0];
        		int nextJ = point[1] + dir[1];
        		
        		if (nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid[0].length && grid[nextI][nextJ] == 1) {
        			area++;

        			// 必须在这里将grid[nextI][nextJ]置为-1而不能在for loop之上，因为对于
					// [[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]，在(1, 1)位置
					// 的1会被计算两遍 - 处理(0, 1)和(1, 0)时分别计算了一遍
        			grid[nextI][nextJ] = -1;
        			queue.offer(new int[] {nextI, nextJ});
        		}
        	}
        }
        
        return area;
    }
    
    // Solution 2: dfs Time: O(m * n) Space: O(1) or O(m * n) if input is immutable
    private static int dfs(int[][] grid, int i, int j) {
    	int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0 ,-1}};
    	int area = 1;
    	grid[i][j] = -1;
    	
    	for (int[] dir: directions) {
    		int nextI = i + dir[0];
    		int nextJ = j + dir[1];
    		
    		if (nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid[0].length && grid[nextI][nextJ] == 1) {
    			area += dfs(grid, nextI, nextJ);
    		}
    	}
    	
    	return area;
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{0,0,1,0,0,0,0,1,0,0,0,0,0}, {0,0,0,0,0,0,0,1,1,1,0,0,0}, 
				{0,1,1,0,1,0,0,0,0,0,0,0,0}, {0,1,0,0,1,1,0,0,1,0,1,0,0}, {0,1,0,0,1,1,0,0,1,1,1,0,0}, 
				{0,0,0,0,0,0,0,0,0,0,1,0,0}, {0,0,0,0,0,0,0,1,1,1,0,0,0}, {0,0,0,0,0,0,0,1,1,0,0,0,0}};
		
		System.out.println(maxAreaOfIsland(test1));
	}
}
