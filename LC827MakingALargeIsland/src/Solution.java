import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Solution {
	
	/**
	 * In a 2D grid of 0s and 1s, we change at most one 0 to a 1.
	 * 
	 * After, what is the size of the largest island? (An island is a 4-directionally connected group of 1s).
	 * 
	 * Example 1:
	 * 
	 * Input: [[1, 0], [0, 1]]
	 * Output: 3
	 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
	 * 
	 * Example 2:
	 * 
	 * Input: [[1, 1], [1, 0]]
	 * Output: 4
	 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 1.
	 * 
	 * Example 3:
	 * 
	 * Input: [[1, 1], [1, 1]]
	 * Output: 4
	 * Explanation: Can't change any 0 to 1, only one island with area = 1. 
	 * 
	 * Notes:
	 * 	1. 1 <= grid.length = grid[0].length <= 50.
	 * 	2. 0 <= grid[i][j] <= 1.
	 */
	
	// O(n^2): 先用DFS将每一块岛屿编上不同的号（index），并将编号和面积存到hashmap里。然后遍历每个为0的点的邻居，发现不同的编号就加上该编号对应的岛屿面积
	
	private static final int[][] DIRECTION = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	
    public static int largestIsland(int[][] grid) {
        int n = grid.length;
        Map<Integer, Integer> map = new HashMap<>();
        
        int index = 2;
        int result = 0;
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1) {
        			int area = dfs(grid, i, j, index);
        			map.put(index++, area);
        			result = Math.max(result, area);
        		}
        	}
        }
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 0) {
        			Set<Integer> set = new HashSet<>();
        			int area = 1;
        			for (int[] dir: DIRECTION) {
        				int nextI = i + dir[0];
        				int nextJ = j + dir[1];
        				if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < n) {
        					if (grid[nextI][nextJ] != 0 && !set.contains(grid[nextI][nextJ])) {
        						set.add(grid[nextI][nextJ]);
        						area += map.get(grid[nextI][nextJ]);
        					}
        				}
        			}
        			result = Math.max(result, area);
        		}
        	}
        }
        
        return result;
    }
    
    private static int dfs(int[][] grid, int i, int j, int index) {
    	int area = 1;
    	grid[i][j] = index;
    	for (int[] dir: DIRECTION) {
    		int nextI = i + dir[0];
    		int nextJ = j + dir[1];
    		
    		if (nextI >= 0 && nextI < grid.length && nextJ >= 0 && nextJ < grid[0].length && grid[nextI][nextJ] == 1) {
    			area += dfs(grid, nextI, nextJ, index);
    		}
    	}
    	
    	return area;
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 0}, {0, 1}};
		int[][] test2 = {{1, 1}, {1, 0}};
		int[][] test3 = {{1, 1}, {1, 1}};
		
		System.out.println(largestIsland(test1));
		System.out.println(largestIsland(test2));
		System.out.println(largestIsland(test3));
	}
    
}
