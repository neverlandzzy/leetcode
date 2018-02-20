
public class Solution {
	/*
	 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) 
	 * and lower right corner (row2, col2).
	 * 
	 * Example:
	 * Given matrix = [
	 *   [3, 0, 1, 4, 2],
	 *   [5, 6, 3, 2, 1],
	 *   [1, 2, 0, 1, 5],
	 *   [4, 1, 0, 1, 7],
	 *   [1, 0, 3, 0, 5]
	 * ]
	 * 
	 * sumRegion(2, 1, 4, 3) -> 8
	 * update(3, 2, 2)
	 * sumRegion(2, 1, 4, 3) -> 10
	 * 
	 * Note:
	 * The matrix is only modifiable by the update function.
	 * You may assume the number of calls to update and sumRegion function is distributed evenly.
	 * You may assume that row1 ≤ row2 and col1 ≤ col2.
	 */
	
	public static void main(String[] args) {
		int[][] nums = {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}};
		
		NumMatrix numMatrix = new NumMatrix(nums);
		
		System.out.println(numMatrix.sumRegion(2, 1, 4, 3));		
		numMatrix.update(3, 2, 2);
		System.out.println(numMatrix.sumRegion(2, 1, 4, 3));

	}
}
