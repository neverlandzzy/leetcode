import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Solution {
	/*
	 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally 
	 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
	 * 
	 * Count the number of distinct islands. An island is considered to be the same as another if and only if one island can be translated 
	 * (and not rotated or reflected) to equal the other.
	 * 
	 * Example 1:
	 * 11000
	 * 11000
	 * 00011
	 * 00011
	 * Given the above grid map, return 1.
	 * 
	 * Example 2:
	 * 11011
	 * 10000
	 * 00001
	 * 11011
	 * Given the above grid map, return 3.
	 * 
	 * Notice that:
	 * 11
	 * 1
	 * and
	 *  1
	 * 11
	 * are considered different island shapes, because we do not consider reflection / rotation.
	 * Note: The length of each dimension in the given grid does not exceed 50.
	 */
	
	// Solution 1: DFS O(m * n)
	
    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1 && !visited[i][j]) {
        			StringBuilder sb= new StringBuilder();
        			helper(grid, i, j, sb, visited);
        			set.add(sb.toString());
        		}
        	}
        }
        //System.out.println(set);
        return set.size();
    }
    
    private static void helper(int[][] grid, int i, int j, StringBuilder sb, boolean[][] visited) {
    	
    	int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    	String[] flag = {"u", "d", "l", "r"};
    	
		for (int k = 0; k < direction.length; k++) {
			int x = i + direction[k][0];
			int y = j + direction[k][1];
			
			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1 && !visited[x][y]) {				
				sb.append(flag[k]);
				visited[x][y] = true;
				helper(grid, x, y, sb, visited);
			}
		}
		sb.append("b");
    }
   
    /*
    // Solution 2: BFS O(m * n)
    public static int numDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1 && !visited[i][j]) {
        			StringBuilder sb= new StringBuilder();
        			helper(grid, i, j, sb, visited);
        			set.add(sb.toString());
        		}
        	}
        }   
        
        //System.out.println(set);
        return set.size();
    }
    
    private static void helper(int[][] grid, int i, int j, StringBuilder sb, boolean[][] visited) {
    	Queue<int[]> queue = new LinkedList<>();
    	queue.offer(new int[]{i, j});
    	visited[i][j] = true;
    	int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    	String[] flag = {"u", "d", "l", "r"};
    	
    	while (!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		for (int k = 0; k < direction.length; k++) {
    			int x = cur[0] + direction[k][0];
    			int y = cur[1] + direction[k][1];
    			
    			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1 && !visited[x][y]) {
    				queue.offer(new int[]{x, y});
    				sb.append(flag[k]);
    				visited[x][y] = true;
    			}
    		}
    		
    		sb.append("b");
    	}
    }
    */
    public static void main(String[] args) {
		int[][] test1 = {{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}};
		int[][] test2 = {{1, 1, 0, 1, 1}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {1, 1, 0, 1, 1}};
		int[][] test3 = {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 1, 0}};
				
		System.out.println(numDistinctIslands(test1));
		System.out.println(numDistinctIslands(test2));
		System.out.println(numDistinctIslands(test3));
	}
}
