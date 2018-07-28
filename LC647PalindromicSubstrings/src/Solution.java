
public class Solution {
	/*
	 * Given a string, your task is to count how many palindromic substrings in this string.
	 * 
	 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
	 * 
	 * Example 1:
	 * Input: "abc"
	 * Output: 3
	 * Explanation: Three palindromic strings: "a", "b", "c".
	 * 
	 * Example 2:
	 * Input: "aaa"
	 * Output: 6
	 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
	 * Note:
	 * The input string length won't exceed 1000.
	 */
	
	/*// Solution 1: O(n^2)
    private static int count = 0;
    
    public static int countSubstrings(String s) {
    	count = 0;
        for (int i = 0; i < s.length(); i++) {
            expand(s, i, i);
            expand(s, i, i + 1);
        }
        
        return count;
    }
    
    private static void expand(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }
    }
    */
	
	// Solution 2: O(n^2)
    public static int countSubstrings(String s) {
    	int count = 0;
    	int n = s.length();
    	boolean[][] dp = new boolean[n][n];
    	
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j <= i; j++) {
        		if (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1])) {
        			dp[j][i] = true;
        			count++;
        		}
        	}
        }
        
        return count;
    }
    
    public static void main(String[] args) {
		System.out.println(countSubstrings("abc"));
		System.out.println(countSubstrings("aaaa"));
	}
}
