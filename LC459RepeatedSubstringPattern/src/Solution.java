
public class Solution {
	
	/*
	 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring together. 
	 * You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
	 * 
	 * Example 1:
	 * Input: "abab"
	 * Output: True
	 * Explanation: It's the substring "ab" twice.
	 * 
	 * Example 2:
	 * Input: "aba"
	 * Output: False
	 * 
	 * Example 3:
	 * Input: "abcabcabcabc"
	 * Output: True 
	 * Explanation: It's the substring "abc" four times. (And the substring "abcabc" twice.)
	 */
	
	/*
    public static boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        
        for (int i = n / 2; i > 0; i--) {
        	// i -- pattern length;
        	// 当n % i != 0时，不可能成为pattern
        	if (n % i == 0) {
        		int m = n / i; // 共有几个pattern
        		String pattern = s.substring(0, i);
        		StringBuilder sb = new StringBuilder();
        		
        		for (int j = 0; j < m; j++) {
        			sb.append(pattern);
        		}
        		
        		if (sb.toString().equals(s)) {
        			return true;
        		}
        	}
        }
        
        return false;
    }
    */
	
	// https://leetcode.com/problems/repeated-substring-pattern/discuss/94344/Simple-Java-solution-2-lines
	public static boolean repeatedSubstringPattern(String s) {
		String str = s + s;
		if (str.substring(1, str.length() - 1).contains(s)) {
			return true;
		}
		
		return false;
	}
	
    public static void main(String[] args) {
		String s1 = "abab";
		String s2 = "aba";
		String s3 = "abcabcabcabc";
		
		System.out.println(repeatedSubstringPattern(s1));
		System.out.println(repeatedSubstringPattern(s2));
		System.out.println(repeatedSubstringPattern(s3));
	}
}
