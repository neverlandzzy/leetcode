
public class Solution {
	/*
	 * Given two words word1 and word2, find the minimum number of steps required to 
	 * convert word1 to word2. (each operation is counted as 1 step.)
	 * 
	 * You have the following 3 operations permitted on a word:
	 * a) Insert a character
	 * b) Delete a character
	 * c) Replace a character
	 */
	
	// similar to LC524, LC712
    public static int minDistance(String word1, String word2) {
        // solution 1: 2-d DP
    	/*
        if (word1 == null || word2 == null) {
            return 0;
        }
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;
        
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + 1);
                }
            }
        }
        
        return dp[m][n];
        */
    	
    	// Solution 2: 1-d DP
        if (word1 == null || word2 == null) {
            return 0;
        }
        
        if (word1.length() > word2.length()) {
            return minDistance(word2, word1);
        }
        
        int m = word1.length();
        int n = word2.length();
        
        int[] dp = new int[m + 1];
        for (int i = 0; i <= m; i++) {
            dp[i] = i;
        }
        
        for (int i = 1; i <= n; i++) {
            int pre = dp[0];
            dp[0] = i;
            
            for (int j = 1; j <= m; j++) {
                int cur = dp[j];
                if (word1.charAt(j - 1) == word2.charAt(i - 1)) {
                    dp[j] = Math.min(Math.min(dp[j - 1] + 1, dp[j] + 1), pre);
                } else {
                    dp[j] = Math.min(Math.min(dp[j - 1] + 1, dp[j] + 1), pre + 1);
                }
                pre = cur;
            }
        }
        
        return dp[m];
    	
    }
    
    public static void main(String[] args) {
		System.out.println(minDistance("insert","insor"));
		//System.out.println(minDistance("opera","operation"));
		//System.out.println(minDistance("test","atest"));
	}
}
