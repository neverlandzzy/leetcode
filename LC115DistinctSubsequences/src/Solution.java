
public class Solution {
	/**
	 * Given a string S and a string T, count the number of distinct subsequences of T in S.
	 * 
	 * A subsequence of a string is a new string which is formed from the original string 
	 * by deleting some (can be none) of the characters without disturbing the relative positions 
	 * of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
	 * 
	 * Here is an example:
	 * 
	 * S = "rabbbit", T = "rabbit"
	 * Return 3.
	 */
	
    public static int numDistinct(String s, String t) {
    	// Solution 1: Space O(n^2)
    	/*
    	if (s == null ||  t == null) {
    		return 0;
    	}
    	
    	int sizeS = s.length();
    	int sizeT = t.length();
    	
    	int[][] d = new int[sizeS + 1][sizeT + 1];
    	
    	for (int i = 0; i <= sizeS; i++) {
    		d[i][0] = 1;
    	}
    	
    	for (int i = 1; i <= sizeS; i++) {
    		for (int j = 1; j <= sizeT; j++) {
    			if (s.charAt(i-1) != t.charAt(j-1)) {
    				d[i][j] = d[i-1][j];
    			} else {
    				d[i][j] = d[i-1][j-1] + d[i-1][j];
    			}
    		}
    	}
    	
    	return d[sizeS][sizeT];
    	*/
    	
    	// Solution 2: Space O(n)
        
        if (s == null || t == null) {
            return 0;
        }
        
        int m = s.length();
        int n = t.length();
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        
        for (int i = 1; i <= m; i++) {
            int pre = dp[0];
            for (int j = 1; j <= n; j++) {
                int tmp = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] = pre + dp[j];
                } 
                pre = tmp;
            }
        }
        
        return dp[n];
    }
    
    
    public static void main(String[] args) {
		String s = "rabbbit";
		String t = "rabbit";
	
		
		System.out.println(numDistinct(s,t));
	}
}
