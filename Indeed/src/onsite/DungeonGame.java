package onsite;

public class DungeonGame {
	
	// Solution 1: 2D - DP
	/*
    public static int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0) {
        	return 0;
        }
        
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int[][] dp = new int[m][n];
        
        dp[m - 1][n - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        
        for (int i = m - 2; i >= 0; i--) {
        	dp[i][n - 1] = Math.max(1, dp[i + 1][n - 1] - dungeon[i][n - 1]);
        }
        
        for (int i = n - 2; i >= 0; i--) {
        	dp[m - 1][i] = Math.max(1, dp[m - 1][i + 1] - dungeon[m - 1][i]);
        }
        
        for (int i = m - 2; i >= 0; i--) {
        	for (int j = n - 2; j >= 0; j--) {
        		dp[i][j] = Math.min(Math.max(1, dp[i][j + 1] - dungeon[i][j]), Math.max(1, dp[i + 1][j] - dungeon[i][j]));
        	}
        }
        
        return dp[0][0];
    }
    */
	
	// Solution 2: 1D - DP
    public static int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0] == null || dungeon[0].length == 0) {
        	return 0;
        }
        
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int min = Math.min(m, n);
        int max = Math.max(m, n);
        
        int[] dp = new int[min];
        dp[min - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        
        for (int i = min - 2; i >= 0; i--) {
        	if (m > n) {
        		dp[i] = Math.max(1, dp[i + 1] - dungeon[m - 1][i]);
        	} else {
        		dp[i] = Math.max(1, dp[i + 1] - dungeon[i][n - 1]);
        	}
        }
        
        for (int i = max - 2; i>= 0; i--) {
        	if (m > n) {
        		dp[min - 1] = Math.max(1, dp[min - 1] - dungeon[i][n - 1]);
        	} else {
        		dp[min - 1] = Math.max(1, dp[min - 1] - dungeon[m - 1][i]);
        	}
        	
        	for (int j = min - 2; j >= 0; j--) {
        		if (m > n) {
        			dp[j] = Math.min(Math.max(1, dp[j + 1] - dungeon[i][j]), Math.max(1, dp[j] - dungeon[i][j]));
        		} else {
        			dp[j] = Math.min(Math.max(1, dp[j + 1] - dungeon[j][i]), Math.max(1, dp[j] - dungeon[j][i]));
        		}
        	}
        }
        
        return dp[0];
    }
    
    
    public static void main(String[] args) {
		int[][] test = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
		
		System.out.println(calculateMinimumHP(test));
	}
}
