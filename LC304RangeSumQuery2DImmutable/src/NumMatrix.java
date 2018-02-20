
public class NumMatrix {
	/*
	 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its 
	 * upper left corner (row1, col1) and lower right corner (row2, col2).
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
	 * sumRegion(1, 1, 2, 2) -> 11
	 * sumRegion(1, 2, 2, 4) -> 12
	 * 
	 * Note:
	 * You may assume that the matrix does not change.
	 * There are many calls to sumRegion function.
	 * You may assume that row1 ≤ row2 and col1 ≤ col2.
	 */
	
	private int[][] dp;
	
    public NumMatrix(int[][] matrix) {
    	
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            dp = new int[0][0];
            return;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        dp = new int[m][n];
        
        dp[0][0] = matrix[0][0];
        
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] + dp[i - 1][0];    
        }
        
        for (int i = 1; i < n; i++) {        	
            dp[0][i] = matrix[0][i] + dp[0][i - 1];

        }
        
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] + dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1];
            }
        }
        /*
    	for (int[] k: dp) {
    		for (int l: k) {
    			System.out.print(l + ", ");
    		}
    		System.out.println();
    	}
    	*/

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (dp == null || dp.length == 0) {
            return 0;
        }
        int up = (row1 == 0) ? 0: dp[row1 - 1][col2];
        int left = (col1 == 0) ? 0: dp[row2][col1 - 1];
        int corner = (row1 == 0 || col1 == 0) ? 0 : dp[row1 - 1][col1 - 1];
        

        return dp[row2][col2] - left - up + corner;
    }

}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
