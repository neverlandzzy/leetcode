
public class Solution {
	/*
	 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to make the gray scale of 
	 * each cell becomes the average gray scale (rounding down) of all the 8 surrounding cells and itself. If a cell has less than 8 surrounding cells, 
	 * then use as many as you can.
	 * 
	 * Example 1:
	 * Input:
	 * [[1,1,1],
	 *  [1,0,1],
	 *  [1,1,1]]
	 * Output:
	 * [[0, 0, 0],
	 *  [0, 0, 0],
	 *  [0, 0, 0]]
	 * Explanation:
	 * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
	 * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
	 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0
	 * 
	 * Note:
	 * 	1. The value in the given matrix is in the range of [0, 255].
	 * 	2. The length and width of the given matrix are in the range of [1, 150].
	 */
	
    public static int[][] imageSmoother(int[][] M) {
        int m = M.length;
        int n = M[0].length;
        int[][] result = new int[m][n];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		result[i][j] = averageScale(M, i, j);
        	}
        }
        
        return result;
    }
    
    private static int averageScale(int[][] M, int i, int j) {
    	int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
    	
        int m = M.length;
        int n = M[0].length;
        int sum = M[i][j];
        int count = 1;
        
    	for (int[] dir: direction) {
    		int nextI = i + dir[0];
    		int nextJ = j + dir[1];
    		
    		if (nextI >= 0 && nextJ >= 0 && nextI < m && nextJ < n) {
    			sum += M[nextI][nextJ];
    			count++;
    		}
    	}
    	
    	return sum / count;
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
		int[][] result1 = imageSmoother(test1);
		print(result1);
	}
    
    private static void print(int[][] arr) {
    	for (int[] a: arr) {
    		for (int i: a) {
    			System.out.print(i + ", ");
    		}
    		System.out.println();
    	}
    }
}
