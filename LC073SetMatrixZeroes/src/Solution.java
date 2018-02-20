
public class Solution {
	/*
	 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
	 * 
	 * Could you devise a constant space solution?
	 */
	
    public static void setZeroes(int[][] matrix) {
    	
    	int firstRow = 0;
    	int firstCol = 0;
    	
        for(int i = 0; i < matrix.length; i++) {
        	for(int j = 0; j < matrix[0].length; j++) {
        		if (matrix[i][j] == 0) {
        			if (i == 0) {
        				firstRow = 1;
        			}
        			if (j == 0) {
        				firstCol = 1;
        			}
        			
        			matrix[i][0] = 0;
        			matrix[0][j] = 0;
        		}
        	}
        }
        for (int i = 1; i < matrix.length; i++) {
        	if(matrix[i][0] == 0) {
        		for(int j = 0; j < matrix[0].length; j++) {
        			matrix[i][j] = 0;
        		}
        	}
        }
        
        for (int j = 1; j < matrix[0].length; j++) {
        	if(matrix[0][j] == 0) {
        		for(int i = 0; i < matrix.length; i++) {
        			matrix[i][j] = 0;
        		}
        	}
        }
        
        if (firstRow == 1) {
        	for(int j = 0; j < matrix[0].length; j++) {
        		matrix[0][j] = 0;
        	}
        }
        
        if (firstCol == 1) {
        	for(int i = 0; i < matrix.length; i++) {
        		matrix[i][0] = 0;
        	}
        }
    }
    
    public static void printMatrix(int[][] matrix) {
		for(int[] m: matrix) {
			for(int i: m) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
    }
    public static void main(String[] args) {
		int[][] test1 = {{3,2,5,0,6}, {7,0,2,4,8}, {7,9,1,6,8}, {3,2,5,4,9}};
		int[][] test2 = {{0}};
		int[][] test3 = {{3}};
		
		printMatrix(test1);
		setZeroes(test1);
		printMatrix(test1);
		System.out.println("==============");
		printMatrix(test2);
		setZeroes(test2);
		printMatrix(test2);
		System.out.println("==============");
		printMatrix(test3);
		setZeroes(test3);
		printMatrix(test3);
	}
}
