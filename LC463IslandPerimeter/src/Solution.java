
public class Solution {
	
	/*
	 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. 
	 * Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, 
	 * and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" 
	 * (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, 
	 * width and height don't exceed 100. Determine the perimeter of the island.
	 * 
	 * Example:
	 * 
	 * [[0,1,0,0],
	 *  [1,1,1,0],
	 *  [0,1,0,0],
	 *  [1,1,0,0]]
	 * 
	 * Answer: 16
	 */
	
    public static int islandPerimeter(int[][] grid) {
        int result = 0;
        
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1) {
        			for (int[] dir: direction) {
        				int nextI = i + dir[0];
        				int nextJ = j + dir[1];
        				
        				if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || grid[nextI][nextJ] == 0) {
        					result++;
        				}
        			}
        		}
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	int[][] test = {{0, 1, 0, 0}, {1, 1, 1, 0}, {0, 1, 0, 0}, {1, 1, 0, 0}};
    	System.out.println(islandPerimeter(test));
	}
}
