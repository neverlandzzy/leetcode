
public class Solution {
	/**
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing 
	 * all 1's and return its area.
	 * 
	 * For example, given the following matrix:
	 * 
	 * 1 0 1 0 0
	 * 1 0 1 1 1
	 * 1 1 1 1 1
	 * 1 0 0 1 0
	 * 
	 * Return 4.
	 */
	
	// Solution 1: 2d - DP
	/*
    public static int maximalSquare(char[][] matrix) {
        
    	if (matrix.length == 0 || matrix[0].length == 0) {
    		return 0;
    	}
    	
    	int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] length = new int[m][n];
        int maxLength = 0;
        
        for (int i = 0 ; i < m; i++) {
        	length[i][0] = matrix[i][0] == '0' ? 0 : 1; 
        	maxLength = Math.max(maxLength, length[i][0]);
        }
        
        for (int i = 0 ; i < n; i++) {
        	length[0][i] = matrix[0][i] == '0' ? 0 : 1;
        	maxLength = Math.max(maxLength, length[0][i]);
        }
        
        for (int i = 1; i < m; i++) {
        	for (int j = 1; j < n; j++) {
        		if (matrix[i][j] == '0') {
        			length[i][j] = 0;
        		} else {
        			length[i][j] = Math.min(Math.min(length[i-1][j], length[i][j-1]), length[i-1][j-1]) + 1;
        		}
        		maxLength = Math.max(maxLength, length[i][j]);
        	}
        	
        }
        
        return maxLength * maxLength; 
    }
    */
	
	// Solution 2: 1d DP
	public static int maximalSquare(char[][] matrix) {
		
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int maxLength = 0;
        
        int min = Math.min(m, n);
        int max = Math.max(m, n);
        
        int[]dp = new int[min];
        
        for (int i = 0; i < min; i++) {
            if (m > n) {
                dp[i] = matrix[0][i] == '1' ? 1 : 0;
            } else {
                dp[i] = matrix[i][0] == '1' ? 1 : 0;
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
                
        for (int i = 1; i < max; i ++) {
            int pre = dp[0];
            if (m > n) {
                dp[0] = matrix[i][0] == '1' ? 1 : 0;
            } else {
                dp[0] = matrix[0][i] == '1' ? 1 : 0;
            }
            maxLength = Math.max(maxLength, dp[0]);

            for (int j = 1; j < min; j++) {
                char element = m > n ? matrix[i][j] : matrix[j][i];
                int cur = dp[j];
                if (element == '0') {
                    dp[j] = 0;
                } else {
                    dp[j] = Math.min(Math.min(dp[j], dp[j - 1]), pre) + 1;
                }
                pre = cur;
                maxLength = Math.max(maxLength, dp[j]);

            }
        }
        return maxLength * maxLength;
	
	}
    public static void main(String[] args) {
		//char[][] test = {{'1','0','1','0','0'}, {'1','0','1','1','1'}, {'1','1','1','1','1'}, {'1','0','0','1','0'}};
    	char[][] test = {{'0','0','1','0'}, {'1','1','1','1'}, {'1','1','1','1'}, {'1','1','1','0'}, {'1','1','0','0'}, {'1','1','1','1'}, {'1','1','1','0'}};
    	//char[][] test = {{'0', '1','0','1','0','0'}, {'0', '0', '1', '1','0','1'}};
		System.out.println(maximalSquare(test));
	}
}
