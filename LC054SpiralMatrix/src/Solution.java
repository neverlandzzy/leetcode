import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a matrix of m x n elements (m rows, n columns), return all elements of 
	 * the matrix in spiral order.
	 * 
	 * For example,
	 * 
	 * Given the following matrix:
	 * 
	 * [
	 * 	[ 1, 2, 3 ],
	 *  [ 4, 5, 6 ],
	 *  [ 7, 8, 9 ]
	 * ]
	 * You should return [1,2,3,6,9,8,7,4,5].
	 */
    public static List<Integer> spiralOrder(int[][] matrix) {
        
        List<Integer> result = new ArrayList<Integer>();
    	int row = matrix.length;
    	
    	if (row == 0) {
    		return result;
    	}
    	
    	int col = matrix[0].length;
    	int i = 0; 
    	int j = -1;
  
     	while (true) {
    		for (int n = 0; n < col; n++) {
    			result.add(matrix[i][++j]);
    		}
			if (--row == 0) {
				break;
			}
			
    		for (int n = 0; n < row; n++) {

    			result.add(matrix[++i][j]);

    		}
			if (--col == 0) {
				break;
			}
    		for (int n = 0; n < col; n++) {
    			result.add(matrix[i][--j]);
    			

    		}
			if (--row == 0) {
				break;
			}
    		
    		for (int n = 0; n < row; n++) {
    		
    			result.add(matrix[--i][j]);

    		}
			if (--col == 0) {
				break;
			}

    	}
    	
    	return result;
    }
    
    
    public static void main(String[] args) {
		int test1[][] = {{1,2,3}, {4,5,6},{7,8,9}};
		int test2[][] = {{1,2,3,4,5,6,7}};
		System.out.println(spiralOrder(test1));
		System.out.println(spiralOrder(test2));
	}
}
