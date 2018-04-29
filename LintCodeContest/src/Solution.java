import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
	
	private final static int[][] DIR = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
	
    public static int cableCarRide(int[][] height) {
        if (height == null || height.length == 0 || height[0] == null || height[0].length == 0) {
        	return 0;
        }
        
        int m = height.length;
        int n = height[0].length;
        int result = 0;
        
        int[][] cache = new int[m][n];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		result = Math.max(result, helper(height, cache, i, j));
        	}
        }
        
        return result;
    }
    
    private static int helper(int[][] height, int[][] cache, int i, int j) {
    	if (cache[i][j] != 0) {
    		return cache[i][j];
    	}
    	
    	for (int[] dir: DIR) {
    		int x = i + dir[0];
    		int y = j + dir[1];
    		
    		if (x < 0 || y < 0 || x >= height.length || y >= height[0].length) {
    			continue;
    		}
    		
    		if (height[x][y] > height[i][j]) {
    			cache[i][j] = Math.max(cache[i][j], helper(height, cache, x, y));
    		}
    	}
    	
    	cache[i][j]++;
    	return cache[i][j];
    	
    }
    
    /*
    public static int cableCarRide(int[][] height) {
        // Write your code here
    	if (height == null || height.length == 0 || height[0] == null || height[0].length == 0) {
    		return 0;
    	}
    	
    	int m = height.length;
    	int n = height[0].length;
    	int[][] dp = new int[m][n];
    	for (int[] d: dp) {
    		Arrays.fill(d, -1);
    	}
    	
    	int max = 0;
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			max = Math.max(max, dfs(height, i, j, dp, 0));
    		}
    	}
    	
    	return max;
    }
    
    private static int dfs(int[][] height, int i, int j, int[][] dp, int count) {
    	int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    	
    	if (i < 0 || i >= height.length || j < 0 || j >= height[0].length) {
    		return count;
    	}
    	
    	if (dp[i][j] != -1) {
    		return dp[i][j];
    	}
    	
    	int result = 0;
    	dp[i][j] = 0;
    	
    	for (int[] dir: direction) {
    		result = Math.max(result, dfs(height, i + dir[0], j + dir[1], dp, count + 1));
    	}
    	
    	dp[i][j] = result + 1;
    	
    	return result + 1;
    }
    */
    
    public static int getMinimumCost(int n, int left, int right, int[] weight) {
        // Write your code here
    	int[][][] dp = new int[n + 1][n + 1][right + 1];
    }
    
    public static void main(String[] args) {
		int[][] test11 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] test12 = {{1, 2, 3}, {6, 5, 4}, {7, 8, 9}};
		System.out.println(cableCarRide(test11));
		System.out.println(cableCarRide(test12));
		
		int[] test21 = {1, 2, 3};
		int[] test22 = {1, 2, 3, 4};
		System.out.println(getMinimumCost(3, 3, 3, test21));
		System.out.println(getMinimumCost(4, 3, 3, test22));
	}
}
