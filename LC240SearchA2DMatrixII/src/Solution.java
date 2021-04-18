
public class Solution {
	/**
	 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has 
	 * the following properties:
	 * 
	 * Integers in each row are sorted in ascending from left to right.
	 * Integers in each column are sorted in ascending from top to bottom.
	 * 
	 * For example,
	 * 
	 * Consider the following matrix:
	 * [
	 *   [1,   4,  7, 11, 15],
	 *   [2,   5,  8, 12, 19],
	 *   [3,   6,  9, 16, 22],
	 *   [10, 13, 14, 17, 24],
	 *   [18, 21, 23, 26, 30]
	 * ]
	 * 
	 * Given target = 5, return true.
	 * Given target = 20, return false.
	 */
	
    public static boolean searchMatrix(int[][] matrix, int target) {

       	int m = matrix.length;
    	int n = matrix[0].length;
    	
    	if (m == 0 || n == 0)  return false;

    	int col = n - 1;
    	int row = 0;
    	
    	while (row < m && col >= 0) {
    		if (matrix[row][col] == target) {
    			return true;
    		} else if (matrix[row][col] < target) {
    			row++;
    		} else {
    			col--;
    		}
    	}
    	
    	return false;
        
    }
    
    public static void main(String[] args) {
    	int[][] test = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
    	
    	//int[][] test = {{1,1}};
    	System.out.println(searchMatrix(test, 5));
    	System.out.println(searchMatrix(test, 1));
    	System.out.println(searchMatrix(test, 20));
    	System.out.println(searchMatrix(test, 15));
	}
}
