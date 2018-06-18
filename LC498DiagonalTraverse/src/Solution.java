
public class Solution {
	/*
	 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as shown in the below image.
	 * 
	 * Example:
	 * Input:
	 * [
	 *  [ 1, 2, 3 ],
	 *  [ 4, 5, 6 ],
	 *  [ 7, 8, 9 ]
	 * ]
	 * Output:  [1,2,4,7,5,3,6,8,9]
	 * 
	 * Note:
	 * The total number of elements of the given matrix will not exceed 10,000.
	 */
	
	// https://leetcode.com/problems/diagonal-traverse/discuss/97711/Java-15-lines-without-using-boolean
	// r + c 为偶数时，向上走，r + c 为奇数时，向下走
    public static int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return new int[0];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[] result = new int[m * n];
        int r = 0;
        int c = 0;
        
        for (int i = 0; i < result.length; i++) {
        	result[i] = matrix[r][c];
        	
        	if ((r + c) % 2 == 0) {
        		// 向上走
        		if (c == n - 1) {
        			r++;
        		} else if (r == 0) {
        			c++;
        		} else {
        			r--;
        			c++;
        		}
        	} else {
        		// 向下走
        		if (r == m - 1) {
        			c++;
        		} else if (c == 0) {
        			r++; 
        		} else {
        			r++;
        			c--;
        		}
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[] result1 = findDiagonalOrder(test1);
		
		for (int i: result1) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
