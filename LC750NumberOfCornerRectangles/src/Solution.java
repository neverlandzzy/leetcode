
public class Solution {
	/*
	 * Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
	 * 
	 * A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.
	 * 
	 * Example 1:
	 * Input: grid = 
	 * [[1, 0, 0, 1, 0],
	 *  [0, 0, 1, 0, 1],
	 *  [0, 0, 0, 1, 0],
	 *  [1, 0, 1, 0, 1]]
	 * Output: 1
	 * Explanation: There is only one corner rectangle, with corners grid[1][2], grid[1][4], grid[3][2], grid[3][4].
	 * 
	 * Example 2:
	 * Input: grid = 
	 * [[1, 1, 1],
	 *  [1, 1, 1],
	 *  [1, 1, 1]]
	 * Output: 9
	 * Explanation: There are four 2x2 rectangles, four 2x3 and 3x2 rectangles, and one 3x3 rectangle.
	 * 
	 * Example 3:
	 * Input: grid = 
	 * [[1, 1, 1, 1]]
	 * Output: 0
	 * Explanation: Rectangles must have four distinct corners.
	 * Note:
	 * The number of rows and columns of grid will each be in the range [1, 200].
	 * Each grid[i][j] will be either 0 or 1.
	 * The number of 1s in the grid will be at most 6000.
	 */
	
	
	// B-F 
    public static  int countCornerRectangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int counter = 0;
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1) {
        			for (int l = i + 1; l < m; l++) {
        				if (grid[l][j] == 1) {
        					for (int k = j + 1; k < n; k++) {
        						if (grid[i][k] == 1) {
        							if (grid[l][k] == 1) {
        								counter++;
        							}
        						}
        					}
        				}
        			}
        		}
        	}
        }
        
        return counter;
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 0, 0, 1, 0}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 1, 0, 1}};
		int[][] test2 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		int[][] test3 = {{1, 1, 1, 1}};
		
		System.out.println(countCornerRectangles(test1));
		System.out.println(countCornerRectangles(test2));
		System.out.println(countCornerRectangles(test3));
	}
}
