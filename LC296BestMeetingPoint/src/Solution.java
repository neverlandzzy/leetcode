import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * A group of two or more people wants to meet and minimize the total travel distance. 
	 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
	 * The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
	 * 
	 * For example, given three people living at (0,0), (0,4), and (2,2):
	 * 
	 * 1 - 0 - 0 - 0 - 1
	 * |   |   |   |   |
	 * 0 - 0 - 0 - 0 - 0
	 * |   |   |   |   |
	 * 0 - 0 - 1 - 0 - 0
	 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal. So return 6.
	 */
	
	// 根据Manhattan Distance定义，行列可以分开独立计算
    public static int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
        	return 0;
        }
        
        List<Integer> rows = getRowsList(grid);
        List<Integer> cols = getColsList(grid);
        
        return getMinDistance(rows) + getMinDistance(cols);
    }
    
    private static List<Integer> getRowsList(int[][] grid) {
    	List<Integer> result = new ArrayList<>();
    	
    	for (int i = 0; i < grid.length; i++) {
    		for (int j = 0; j < grid[0].length; j++) {
    			if (grid[i][j] == 1) {
    				result.add(i);
    			}
    		}
    	}
    	
    	return result;
    }
    
    private static List<Integer> getColsList(int[][] grid) {
    	List<Integer> result = new ArrayList<>();
    	
    	for (int i = 0; i < grid[0].length; i++) {
    		for (int j = 0; j < grid.length; j++) {
    			if (grid[j][i] == 1) {
    				result.add(i);
    			}
    		}
    	}
    	
    	return result;
    }
    
    private static int getMinDistance(List<Integer> list) {
    	int result = 0;
    	
    	int i = 0; 
    	int j = list.size() - 1;
    	
    	while (i < j) {
    		result += list.get(j) - list.get(i);
    		i++;
    		j--;
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		int[][] test = {{1, 0, 0, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
		
		System.out.println(minTotalDistance(test));
	}
}
