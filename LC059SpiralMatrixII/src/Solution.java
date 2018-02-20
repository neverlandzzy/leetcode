
public class Solution {
	/*
	 * Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.
	 * 
	 * For example,
	 * Given n = 3,
	 * You should return the following matrix:
	 * 
	 * [
	 *  [ 1, 2, 3 ],
	 *  [ 8, 9, 4 ],
	 *  [ 7, 6, 5 ]
	 * ]
	 */
	
    public static int[][] generateMatrix(int n) {
        int col = n;
        int row = n;
        int i = 0, j = -1;
        
        int[][] matrix = new int[n][n];
        int counter = 1;
        
        if( n == 0) {
        	return matrix;
        }
        
        while (true) {
        	for (int m = 0; m < col; m++) {
        		matrix[i][++j] = counter;
        		counter++;
        	}
        	if (--row == 0) break;
        	
        	for (int m = 0; m < row; m++) {
        		matrix[++i][j] = counter;
        		counter++;
        	}
        	if (--col == 0) break;
        	
        	for (int m = 0; m < col; m++) {
        		matrix[i][--j] = counter;
        		counter++;
        	}
        	if (--row == 0) break;
        	
        	for (int m = 0; m < row; m++) {
        		matrix[--i][j] = counter;
        		counter++;
        	}
        	if (--col == 0) break;
        	
        }
        return matrix;
    }
    
    public static void main(String[] args) {
    	int[][] test1 = generateMatrix(3);
    	for(int[] m: test1) {
    		for(int k: m) {
    			System.out.print(k);
    		}
    		System.out.println();
    	}
    	System.out.println("------------");
    	int[][] test2 = generateMatrix(4);
    	for(int[] m: test2) {
    		for(int k: m) {
    			System.out.print(k);
    		}
    		System.out.println();
    	}
    	System.out.println("------------");
    	int[][] test3 = generateMatrix(1);
    	for(int[] m: test3) {
    		for(int k: m) {
    			System.out.print(k);
    		}
    		System.out.println();
    	}
    	System.out.println("------------");
    	int[][] test4 = generateMatrix(0);
    	for(int[] m: test4) {
    		for(int k: m) {
    			System.out.print(k);
    		}
    		System.out.println();
    	}
	}
}
