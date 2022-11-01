
public class Solution {
	/**
	 * Given a string s, find the longest palindromic subsequence's length in s. You may assume that the maximum length of s is 1000.
	 * 
	 * Example 1:
	 * Input:
	 * 
	 * "bbbab"
	 * Output:
	 * 4
	 * One possible longest palindromic subsequence is "bbbb".
	 * 
	 * Example 2:
	 * Input:
	 * "cbbd"
	 * Output:
	 * 2
	 * One possible longest palindromic subsequence is "bb".
	 */
	
	// https://discuss.leetcode.com/topic/78603/straight-forward-java-dp-solution
	
	// Solution 1: DFS + mem
	/*
    public static int longestPalindromeSubseq(String s) {
        return helper(s, 0, s.length() - 1, new Integer[s.length()][s.length()]);
    }
    
    private static int helper(String s, int i, int j, Integer[][] cache) {
    	if (cache[i][j] != null) {
    		return cache[i][j];
    	}
    	
    	if (i > j) {
    		return 0;
    	}
    	
    	if (i == j) {
    		return 1;
    	}
    	
    	if (s.charAt(i) == s.charAt(j)) {
    		cache[i][j] = helper(s, i + 1, j - 1, cache) + 2;
    	} else {
    		cache[i][j] = Math.max(helper(s, i + 1, j, cache), helper(s, i, j - 1, cache));
    	}
    	
    	return cache[i][j];
    }
    */
	
	// Solution 2: 2D - DP 
	
	// 从右上角填
	/*  
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        for (int len = 1; len < n; len++) {
            for (int i = 0; i + len < n; i++) {
                int j = i + len;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[0][n - 1];
    }
	*/
	
	// 从右下角填，便于空间优化 类似LC486
	/*
	public static int longestPalindromeSubseq1(String s) {
		//dp[i][j] -- s.substring(i, j)的最大Palindrome长度
		int[][] dp = new int[s.length()][s.length()];
		for (int i = s.length() - 1; i >= 0; i--) {
			dp[i][i] = 1;
			
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[0][s.length() - 1];
	}
    */

	// Solution 3: 1D - DP
	public static int longestPalindromeSubseq(String s) {
		int[] dp = new int[s.length()];
		
		int prev = 0, current = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			
			dp[i] = 1;
			prev = 0;
			for (int j = i + 1; j < s.length(); j++) {
				current = dp[j];
				
				if (s.charAt(i) == s.charAt(j)) {
					dp[j] = prev + 2;
				} else {
					dp[j] = Math.max(dp[j], dp[j - 1]);
				}
				prev = current;
			}
			/*
			for (int k: dp) {
				System.out.print(k + ", ");
			}
			System.out.println();
			*/
		}
		
		return dp[s.length() - 1];
	}
	
    public static void main(String[] args) {
		System.out.println(longestPalindromeSubseq("bbbab"));
    	System.out.println(longestPalindromeSubseq("cbbd"));
	}
}
