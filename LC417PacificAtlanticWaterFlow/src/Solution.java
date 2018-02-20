import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
	/*
	 * Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
	 * the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
	 * 
	 * Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
	 * Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.
	 * 
	 * Note:
	 * The order of returned grid coordinates does not matter.
	 * Both m and n are less than 150.
	 * Example:
	 * 
	 * Given the following 5x5 matrix:
	 *   Pacific ~   ~   ~   ~   ~ 
     *        ~  1   2   2   3  (5) *
     *        ~  3   2   3  (4) (4) *
     *        ~  2   4  (5)  3   1  *
     *        ~ (6) (7)  1   4   5  *
     *        ~ (5)  1   1   2   4  *
     *        *   *   *   *   * Atlantic
     *        
     * Return:
     * 
     * [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
	 */
	// Solution 1: BFS 34ms
	/*
    public static List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> result = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        	return result;
        }
        
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        
        // vertical
        for (int i = 0; i < n; i++) {
        	pacific[i][0] = true;
        	atlantic[i][m - 1] = true;
        	pQueue.offer(new int[]{i, 0});
        	aQueue.offer(new int[]{i, m - 1});
        }
        
        // horizontal
        for (int i = 0; i < m; i++) {
        	pacific[0][i] = true;
        	atlantic[n - 1][i] = true;
        	pQueue.offer(new int[]{0, i});
        	aQueue.offer(new int[]{n - 1, i});
        }
        
        bfs(pQueue, pacific, matrix);
        bfs(aQueue, atlantic, matrix);
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		if (pacific[i][j] && atlantic[i][j]) {
        			result.add(new int[]{i, j});
        		}
        	}
        }
        
        return result;
    }
    
    private static void bfs(Queue<int[]> queue, boolean[][] visited, int[][] matrix) {
    	int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    	int n = matrix.length;
    	int m = matrix[0].length;
    	
    	while(!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		for (int[] d: dir) {
    			int x = cur[0] + d[0];
    			int y = cur[1] + d[1];
    			
    			if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || matrix[cur[0]][cur[1]] > matrix[x][y] ) {
    				continue;
    			}
    			
    			visited[x][y] = true;
    			queue.offer(new int[]{x, y});
    		}
    	}
    }
    */
    // Solution 2: DFS 19ms
	
    public static List<int[]> pacificAtlantic(int[][] matrix) {
    	List<int[]> result = new ArrayList<>();
    	
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    		return result;
    	}
    	
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, i, 0);
            dfs(matrix, atlantic, Integer.MIN_VALUE, i, m - 1);
        }
        
        for (int i = 0; i < m; i++) {
            dfs(matrix, pacific, Integer.MIN_VALUE, 0, i);
            dfs(matrix, atlantic, Integer.MIN_VALUE, n - 1, i);
        }
    	
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < m; j++) {
        		if (pacific[i][j] && atlantic[i][j]) {
        			result.add(new int[]{i, j});
        		}
        	}
        }
    	return result;
    }
    
    private static void dfs(int[][] matrix, boolean[][] visited, int height, int x, int y) {
    	int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    	int n = matrix.length;
    	int m = matrix[0].length;
    	
    	if (x < 0 || x >= n || y < 0 || y >= m || visited[x][y] || matrix[x][y] < height) {
    		return;
    	}
    	visited[x][y] = true;
    	
    	for (int[] d: dir) {
    		dfs(matrix, visited, matrix[x][y], x + d[0], y + d[1]);
    	}
    }
    
    public static void main(String[] args) {
		int[][] test = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
		
		List<int[]> result = pacificAtlantic(test);
		
		for (int[] array: result) {
			for (int n: array) {
				System.out.print(n + ", ");
			}
			System.out.println();
		}
	}
}
