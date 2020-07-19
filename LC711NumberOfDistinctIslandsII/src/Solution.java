import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/**
	 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally 
	 * (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
	 * 
	 * Count the number of distinct islands. An island is considered to be the same as another if they have the same shape, or have the 
	 * same shape after rotation (90, 180, or 270 degrees only) or reflection (left/right direction or up/down direction).
	 * 
	 * Example 1:
	 * 11000
	 * 10000
	 * 00001
	 * 00011
	 * Given the above grid map, return 1. 
	 * 
	 * Notice that:
	 * 11
	 * 1
	 * and
	 *  1
	 * 11
	 * are considered same island shapes. Because if we make a 180 degrees clockwise rotation on the first island, then two islands will 
	 * have the same shapes.
	 * 
	 * Example 2:
	 * 11100
	 * 10001
	 * 01001
	 * 01110
	 * Given the above grid map, return 2.
	 * 
	 * Here are the two distinct islands:
	 * 111
	 * 1
	 * and
	 * 1
	 * 1
	 * 
	 * Notice that:
	 * 111
	 * 1
	 * and
	 * 1
	 * 111
	 * are considered same island shapes. Because if we flip the first array in the up/down direction, then they have the same shapes.
	 * Note: The length of each dimension in the given grid does not exceed 50.
	 */
	
	// https://discuss.leetcode.com/topic/107494/consise-c-solution-using-dfs-sorting-to-find-canonical-representation-for-each-island/9
	// 先用LC694的方法找到每个island的坐标，然后再将这些坐标归一化
	// 由于找到island后需要处理坐标，这里不再用String记录每个岛屿，而是用List<int[]>
	
	static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int[][] transformation = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	
    public static int numDistinctIslands2(int[][] grid) {
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
        			List<int[]> cells = new ArrayList<>();
        			helper(grid, i, j, cells, visited);
        			String key = normalize(cells);
        			set.add(key);
        		}
        	}
        }
        //System.out.println(set);
        return set.size();
    }
    
    private static void helper(int[][] grid, int i, int j, List<int[]> cells, boolean[][] visited) {
    	cells.add(new int[]{i, j});
    	visited[i][j] = true;
    	
		for (int k = 0; k < direction.length; k++) {
			int x = i + direction[k][0];
			int y = j + direction[k][1];
			
			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1 && !visited[x][y]) {				
				helper(grid, x, y, cells, visited);
			}
		}
    }
    
    private static String normalize(List<int[]> cells) {
    	// 每一个输入的List<int[]> 代表了一个岛屿
    	// 对这个岛屿进行8种变形，并对每一个变形求其归一化的坐标(key)，记录到forms里，forms里的每一个key都可以表示这个岛屿的形状，将其sort后，取第一个，则所有相同形状
    	// 的岛屿都可以用这个String表示
    	
    	List<String> forms = new ArrayList<>();
        // 8 different transformations
        // (x, y), (x, -y), (-x, y), (-x, -y)
        // (y, x), (-y, x), (y, -x), (-y, -x)
    	
    	for (int[] tran: transformation) {
    		List<int[]> list1 = new ArrayList<>();
    		List<int[]> list2 = new ArrayList<>();
    		
    		for (int[] cell: cells) {
    			list1.add(new int[]{cell[0] * tran[0], cell[1] * tran[1]});
    			list2.add(new int[]{cell[1] * tran[1], cell[0] * tran[0]});
    		}
    		
            forms.add(getKey(list1));
            forms.add(getKey(list2));
    	}
    	
    	Collections.sort(forms);
    	return forms.get(0);
    }
    
    private static String getKey(List<int[]> cells) {
    	Collections.sort(cells, new Comparator<int[]>(){
    		public int compare(int[] o1, int[] o2){
    			if (o1[0] != o2[0]) {
    				return o1[0] - o2[0];
    			} else {
    				return o1[1] - o2[1];
    			}
    		}
    	});
    	
    	StringBuilder sb = new StringBuilder();
    	int x = cells.get(0)[0];
    	int y = cells.get(0)[1];
    	
    	for (int[] cell: cells) {
    		sb.append((cell[0] - x) + ":" + (cell[1] - y) + ":");
    	}
    	
    	return sb.toString();
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 1, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 1, 1}};
		int[][] test2 = {{1, 1, 1, 0, 0}, {1, 0, 0, 0, 1}, {0, 1, 0, 0, 1}, {0, 1, 1, 1, 0}};
		int[][] test3 = {{1, 1, 0}, {0, 1, 1}, {0, 0, 0}, {1, 1, 1}, {0, 1, 0}};
				
		System.out.println(numDistinctIslands2(test1));
		System.out.println(numDistinctIslands2(test2));
		System.out.println(numDistinctIslands2(test3));
	}
}
