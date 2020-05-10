
public class Solution {
	/**
	 * Write an efficient algorithm that searches for a value in an m x n matrix. 
	 * This matrix has the following properties:
	 * 
	 * Integers in each row are sorted from left to right.
	 * The first integer of each row is greater than the last integer of the previous row.
	 * 
	 * For example,
	 * Consider the following matrix:
	 * [
	 *   [1,   3,  5,  7],
	 *   [10, 11, 16, 20],
	 *   [23, 30, 34, 50]
	 * ]
	 * 
	 * Given target = 3, return true.
	 */

	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return false;
		}

		int m = matrix.length;
		int n = matrix[0].length;

		int start = 0;
		int end = m * n - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;

			if (target == matrix[mid / n][mid % n]) {
				return true;
			}

			if (target < matrix[mid / n][mid % n]) {
				end = mid;
			} else {
				start = mid;
			}
		}

		if (matrix[start / n][start % n] == target) {
			return true;
		}

		return matrix[end / n][end % n] == target;
	}

	/*
    public static boolean searchMatrix(int[][] matrix, int target) {
        
    	int m = matrix.length;
    	int n = matrix[0].length;
    	
    	int lo = 0;
    	int hi = m * n - 1;
    	
    	while (lo < hi) {
    		int mid = (lo + hi) / 2;
    		if (matrix[mid / n][mid % n] == target) {
    			return true;
    		} else if (matrix[mid / n][mid % n] > target) {
    			hi = mid;
    		} else {
    			lo = mid + 1;
    		}
    	}
    	
    	if (matrix[lo / n][lo % n] == target) {
    		return true;
    	}
    	
    	return false;
    }
	*/
    
    public static void main(String[] args) {
		int[][] test = {{1,3,5,7}, {10,11,16,20}, {23,30,34,50}};
		int[][] test2 = {{1}};
		
		System.out.println(searchMatrix(test, 7));
		System.out.println(searchMatrix(test, 0));
		System.out.println(searchMatrix(test, 51));
		System.out.println(searchMatrix(test, 21));
		System.out.println(searchMatrix(test, 30));
		
		System.out.println(searchMatrix(test2, 1));
		System.out.println(searchMatrix(test2, 2));
	}
}
