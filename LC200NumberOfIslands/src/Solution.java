import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
	 * An island is surrounded by water and is formed by connecting adjacent lands 
	 * horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
	 * 
	 * Example 1:
	 * 11110
	 * 11010
	 * 11000
	 * 00000
	 * 
	 * Answer: 1
	 * 
	 * Example 2:
	 * 11000
	 * 11000
	 * 00100
	 * 00011
	 * 
	 * Answer: 3
	 */
	
	// 模板题，三种解法必须掌握
	
	// Solution 1 - 1: BFS, 2D坐标转换为1D
	/*
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
    	int n = grid[0].length;
    	
    	int counter = 0;
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (grid[i][j] == '1') {
    				bfs(grid, i, j);
    				counter++;
    			}
    		}
    	}
    	
        // 若面试时需要恢复输入，则将'#'还原为'1'，否则可以跳过这步
    	for (int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(grid[i][j] == '#') {
    				grid[i][j] = '1';
    			}
    		}
    	}
    	return counter;
    }
    
    private static void visited(char[][] grid, int i, int j, Queue<Integer> queue) {
    	int m = grid.length;
    	int n = grid[0].length;
    	
    	if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || grid[i][j] != '1') {
    		return;
    	}
    	
    	grid[i][j] = '#';
    	queue.offer(i * n + j);
    }
    
    private static void bfs(char[][] grid, int i, int j) {
    	Queue<Integer> queue = new LinkedList<Integer>();
    	
    	int n = grid[0].length;

    	visited(grid, i, j, queue);
    	
    	while(!queue.isEmpty()) {
    		int position = queue.poll();
    		
    		int x = position / n;
    		int y = position % n;
    		visited(grid, x + 1, y, queue);
    		visited(grid, x - 1, y, queue);
    		visited(grid, x, y + 1, queue);
    		visited(grid, x, y - 1, queue);
    	}

    }
    */
	
	// Solution 1 - 1: BFS, 不转换2D坐标，queue里加2D数组表示坐标 -- 免去记忆坐标转换
	/*
    public static int numIslands(char[][] grid) {
    	
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
    	int n = grid[0].length;
    	
    	int counter = 0;
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (grid[i][j] == '1') {
    				bfs(grid, i, j);
    				counter++;
    			}
    		}
    	}
    	
        // 若面试时需要恢复输入，则将'#'还原为'1'，否则可以跳过这步
    	for (int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(grid[i][j] == '#') {
    				grid[i][j] = '1';
    			}
    		}
    	}
    	return counter;
    }
    
    private static void visited(char[][] grid, int i, int j, Queue<int[]> queue) {
    	int m = grid.length;
    	int n = grid[0].length;
    	
    	if (i < 0 || i > m - 1 || j < 0 || j > n - 1 || grid[i][j] != '1') {
    		return;
    	}
    	
    	grid[i][j] = '#';
    	queue.offer(new int[] {i, j});
    }
    
    private static void bfs(char[][] grid, int i, int j) {
    	Queue<int[]> queue = new LinkedList<>();
    	
    	visited(grid, i, j, queue);
    	
    	while(!queue.isEmpty()) {
    		int[] node = queue.poll();
    		
    		int x = node[0];
    		int y = node[1];
    		visited(grid, x + 1, y, queue);
    		visited(grid, x - 1, y, queue);
    		visited(grid, x, y + 1, queue);
    		visited(grid, x, y - 1, queue);
    	}

    }
	*/
	
	
    // Solution 2: DFS
    
	/*
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
    	int n = grid[0].length;
    	
    	int counter = 0;
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (grid[i][j] == '1') {
	    			dfs(grid, i, j);
	    			counter++;
    			}
    		}
    	}
        
    	for (int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(grid[i][j] == '#') {
    				grid[i][j] = '1';
    			}
    		}
    	}
    	return counter;		
    }
    
    private static void dfs(char[][] grid, int i, int j) {
    	if (grid[i][j] == '0') return;
    	if (grid[i][j] == '1') {
    		
    		int m = grid.length;
    		int n = grid[0].length;
    		
    		grid[i][j] = '#';
    		
    		if (i - 1 >= 0) dfs(grid, i - 1, j);
    		if (i + 1 < m)  dfs(grid, i + 1, j);
    		if (j - 1 >= 0) dfs(grid, i, j - 1);
    		if (j + 1 < n)  dfs(grid, i, j + 1);
    	}
    }
    */
	
	// Solution 3: Union Find O(m * n)
	static class UnionFind{
		Map<Integer, Integer> father;
		int count = 0;
		
		UnionFind(char[][] grid) {
			int m = grid.length;
			int n = grid[0].length;
			father = new HashMap<>();
			
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (grid[i][j] == '1') {
						count++;
						father.put(i * n + j, i * n + j);
					}
				}
			}
		}
		
		int find(int x) {
            if (father.get(x) == x) {
                return x;
            }
            
            int parent = find(father.get(x));
            father.put(x, parent);
            return parent;
		}
		
        void union(int x, int y){
            int faX = find(x);
            int faY = find(y);
            if(faX != faY) {
            	count--;
                father.put(faX, faY);
            }
        }
        
        int getCount() {
        	return count;
        }
	}
	
	public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
    	int n = grid[0].length;
    	
    	int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    	UnionFind uf = new UnionFind(grid);
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (grid[i][j] == '1') {
    				for (int[] dir: direction) {
    					int x = i + dir[0];
    					int y = j + dir[1];
    					if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != '1') {
    						continue;
    					}
    					
    					uf.union(i * n + j, x * n + y);
    				}
    			}
    		}
    	}
    	
    	return uf.getCount();
	}
	
    public static void main(String[] args) {
		char[][] test = {{'1', '1', '1', '1', '0'}, {'1', '1', '0', '1', '0'}, 
				{'1', '1', '0', '0', '0'}, {'0', '0', '0', '0', '0'}};
		
		System.out.println(numIslands(test));
	}
}
