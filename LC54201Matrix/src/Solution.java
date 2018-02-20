
public class Solution {
	/*
	 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
	 * The distance between two adjacent cells is 1.
	 * 
	 * Example 1: 
	 * Input:
	 * 0 0 0
	 * 0 1 0
	 * 0 0 0
	 * 
	 * Output:
	 * 0 0 0
	 * 0 1 0
	 * 0 0 0
	 * 
	 * Example 2: 
	 * Input:
	 * 0 0 0
	 * 0 1 0
	 * 1 1 1
	 * 
	 * Output:
	 * 0 0 0
	 * 0 1 0
	 * 1 2 1
	 * 
	 * Note:
	 * 1. The number of elements of the given matrix will not exceed 10,000.
	 * 2. There are at least one 0 in the given matrix.
	 * 3. The cells are adjacent in only four directions: up, down, left and right.
	 */
	
    public static int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
		int[][] test2 = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
		
		int[][] result1 = updateMatrix(test1);
		int[][] result2 = updateMatrix(test2);
		
		for (int[] nums: result1) {
			for (int n: nums) {
				System.out.print(n);
			}
			System.out.println();
		}
		
		System.out.println();
		
		for (int[] nums: result2) {
			for (int n: nums) {
				System.out.print(n);
			}
			System.out.println();
		}
	}
}
