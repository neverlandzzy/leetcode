import java.util.Arrays;


public class Solution {
	/**
	 * Given an integer matrix, find the length of the longest increasing path.
	 * 
	 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or 
	 * move outside of the boundary (i.e. wrap-around is not allowed).
	 * 
	 * Example 1:
	 * 
	 * nums = [
	 *   [9,9,4],
 	 *   [6,6,8],
 	 *   [2,1,1]
	 * ]
	 * Return 4
	 * The longest increasing path is [1, 2, 6, 9].
	 * 
	 * Example 2:
	 * 
	 * nums = [
	 *   [3,4,5],
	 *   [3,2,6],
	 *   [2,2,1]
	 * ]
	 * Return 4
	 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
	 */

	// Time: O(mn)
	private final static int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
        	return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        int result = 0;
        
        int[][] cache = new int[m][n];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		result = Math.max(result, helper(matrix, cache, i, j));
        	}
        }
        
        return result;
    }
    
    private static int helper(int[][] matrix, int[][] cache, int i, int j) {
    	if (cache[i][j] != 0) {
    		return cache[i][j];
    	}
    	
    	for (int[] dir: DIR) {
    		int x = i + dir[0];
    		int y = j + dir[1];
    		
    		if (x < 0 || y < 0 || x >= matrix.length || y >= matrix[0].length) {
    			continue;
    		}
    		
    		if (matrix[x][y] > matrix[i][j]) {
    			cache[i][j] = Math.max(cache[i][j], helper(matrix, cache, x, y));
    		}
    	}
    	
    	cache[i][j]++;
    	return cache[i][j];
    	
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}};
		int[][] test2 = {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}};
		
		System.out.println(longestIncreasingPath(test1));
		System.out.println(longestIncreasingPath(test2));
	}
}
