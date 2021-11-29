import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/*
	 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns 
	 * the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each 
	 * addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
	 * You may assume all four edges of the grid are all surrounded by water.
	 * 
	 * Example:
	 * 
	 * Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
	 * Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
	 * 
	 * 0 0 0
	 * 0 0 0
	 * 0 0 0
	 * 
	 * Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
	 * 
	 * 1 0 0
	 * 0 0 0   Number of islands = 1
	 * 0 0 0
	 * 
	 * Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
	 * 
	 * 1 1 0
	 * 0 0 0   Number of islands = 1
	 * 0 0 0
	 * 
	 * Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
	 * 
	 * 1 1 0
	 * 0 0 1   Number of islands = 2
	 * 0 0 0
	 * 
	 * Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
	 * 
	 * 1 1 0
	 * 0 0 1   Number of islands = 3
	 * 0 1 0
	 * 
	 * We return the result as an array: [1, 1, 2, 3]
	 * 
	 * Challenge:
	 * Can you do it in time complexity O(k log mn), where k is the length of the positions?
	 */
	
	// 用hashmap比array慢，但更通用
	static class UnionFindWithHashMap{
		Map<Integer, Integer> parent;
		
		UnionFindWithHashMap(int[][] grid) {
			int m = grid.length;
			int n = grid[0].length;
			parent = new HashMap<>();
			
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					parent.put(i * n + j, i * n + j);
				}
			}
		}
		
		int find(int x) {
            if (parent.get(x) == x) {
                return x;
            }
            
            int pX = find(parent.get(x));
            parent.put(x, pX);
            return pX;
		}
		
        void union(int x, int y){
            int faX = find(x);
            int faY = find(y);
            if(faX != faY) {
            	parent.put(faX, faY);
            }
        }
	}
	
	static class UnionFind{
		int[] parent;
		
		UnionFind(int[][] grid) {
			int m = grid.length;
			int n = grid[0].length;
			parent = new int[m * n];
			
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					parent[i * n + j] = i * n + j;
				}
			}
		}
		
		int find(int x) {
            if (parent[x] == x) {
                return x;
            }
            
            parent[x] = find(parent[x]);
            return parent[x];
		}
		
        void union(int x, int y){
            int faX = find(x);
            int faY = find(y);
            if(faX != faY) {
                parent[faX] = faY;
            }
        }
	}
    public static List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new ArrayList<>();
        if (m == 0 || n == 0 || positions.length == 0 || positions[0].length == 0) {
        	return result;
        }
        
        int[][] grid = new int[m][n];
        int count = 0;
        
        int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        UnionFind uf = new UnionFind(grid);
        
        for (int[] pos: positions) {
        	int i = pos[0];
        	int j = pos[1];
        	
        	if (grid[i][j] != 1) {
        		count++;
        		grid[i][j] = 1;
        		
        		for (int[] dir: direction) {
    				int x = i + dir[0];
    				int y = j + dir[1];
    				
    				if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] != 1) {
    					continue;
    				}
    				
    				int father = uf.find(i * n + j);
    				int newFather = uf.find(x * n + y);
    				
    				if (father != newFather) {
    					count--;
    					uf.union(i * n + j, x * n + y);
    				}
        		}
        	}
        	
        	result.add(count);
        }
        return result;
    }
    
    public static void main(String[] args) {
		int[][] test = {{0,0}, {0,1}, {1,2}, {2,1}};
		System.out.println(numIslands2(3, 3, test));
	}
}
