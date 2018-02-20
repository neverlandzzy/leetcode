
public class Solution {
	/*
	 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
	 * 
	 * For example,
	 * 
	 * Given:
	 * s1 = "aabcc",
	 * s2 = "dbbca",
	 * 
	 * When s3 = "aadbbcbcac", return true.
	 * When s3 = "aadbbbaccc", return false.
	 */
	
    public static boolean isInterleave(String s1, String s2, String s3) {
        // Solution 1: Time O(n1 * n2) Space O(n^2)
        /*
        int n1 = s1.length();
        int n2 = s2.length();
        
        if (s3.length() != n1 + n2) {
            return false;
        }
        
        boolean[][] d = new boolean[n1 + 1][n2 + 1];
        
        d[0][0] = true;
        
        for (int i = 1; i <= n1; i++) {
            d[i][0] = d[i - 1][0] && (s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        
        for (int i = 1; i <= n2; i++) {
            d[0][i] = d[0][i - 1] && (s2.charAt(i - 1) == s3.charAt(i - 1));
        }
        
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                d[i][j] = (d[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                          (d[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        
        return d[n1][n2];
        */
        
        // Solution 2: Time O(n1 * n2) Space O(n)
        
        int n1 = s1.length();
        int n2 = s2.length();
        
        if (s3.length() != n1 + n2) {
            return false;
        }
        
        if (n1 > n2) {
            return isInterleave(s2, s1, s3);
        }
        
        boolean[] d = new boolean[n1 + 1];
        
        d[0] = true;
        
        for (int i = 1; i <= n1; i++) {
            d[i] = d[i - 1] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        
        for (int j = 1; j <= n2; j++) {
            d[0] = d[0] && s2.charAt(j - 1) == s3.charAt(j - 1);
            for (int i = 1; i <= n1; i++) {
                d[i] = d[i - 1] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
                       d[i] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }
        
        return d[n1];
    }
    
    public static void main(String[] args) {
		String s1 = "aabcc";
		String s2 = "dbbca";
		String test1 = "aadbbcbcac";
		String test2 = "aadbbbaccc";
		
		System.out.println(isInterleave(s1, s2, test1));
		System.out.println(isInterleave(s1, s2, test2));
		
		
	}
}
