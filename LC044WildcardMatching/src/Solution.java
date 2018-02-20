
public class Solution {
	/*
	 * Implement wildcard pattern matching with support for '?' and '*'.
	 * 
	 * '?' Matches any single character.
	 * '*' Matches any sequence of characters (including the empty sequence).
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * The function prototype should be:
	 * bool isMatch(const char *s, const char *p)
	 * 
	 * Some examples:
	 * isMatch("aa","a") → false
	 * isMatch("aa","aa") → true
	 * isMatch("aaa","aa") → false
	 * isMatch("aa", "*") → true
	 * isMatch("aa", "a*") → true
	 * isMatch("ab", "?*") → true
	 * isMatch("aab", "c*a*b") → false
	 * 
	 */
	
	// Solution 1: Time: O(m * n), Space: O(m * n)
	/*
    public static boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        
        int count = 0;
        for (int i = 0; i < pLength; i++) {
            if (p.charAt(i) == '*') count++;
        }
        if (count==0 && sLength != pLength) return false;
        else if (pLength - count > sLength) return false;
        
        boolean isMatch[][] = new boolean[sLength+1][pLength+1];
        
    	isMatch[0][0] = true;
    	
    	// if p is empty, return false;
    	
    	for (int i = 1; i <= sLength; i++) {
    		isMatch[i][0] = false;
    	}
    	
    	// if s is empty
    	for (int j = 1; j <= pLength; j++) {
    		isMatch[0][j] = isMatch[0][j-1] && (p.charAt(j-1)== '*');
    		
    	}

    	for (int i = 1; i <= sLength; i++) {
    		for (int j = 1; j <= pLength; j++) {
    			if (p.charAt(j-1) != '*') {  				
    				isMatch[i][j] = isMatch[i-1][j-1] && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?');    			
    			} else {
    				for (int k = 0; k <= i; k++) {
    					if (isMatch[k][j-1] == true) {
    						isMatch[i][j] = true;
    						break;
    					}
    				}
    			}
    		}
    	}
        return isMatch[sLength][pLength];
        
    }
    */
	
	// Solution 2:  Time: O(m * n), Space: O(1)
	public static boolean isMatch(String s, String p) {
		int m = 0, n = 0, starP = -1;
		int tmp = 0;
		
		while (m < s.length()) {
			if (n < p.length() && (s.charAt(m) == p.charAt(n) || p.charAt(n) == '?')) {
				// s and p match, so advance both;
				m++;
				n++;
			} else if (n < p.length() && p.charAt(n) == '*') {
				// found '*' in p, just advance p;
				starP = n;
				n++;
				tmp = m;
			} else if (starP != -1) {
				// found '*' in p last time, just advance s, so that the '*' can match sequence in s
				n = starP+1;
				tmp++;
				m = tmp;
			} else {
				return false;
			}
			//System.out.println("m = " + m + " n = " + n + " tmp = " + tmp);
		}
		while (n < p.length() && p.charAt(n) == '*') {
			n++;
		}
		
		return n==p.length();
		
	
	}
    
    public static void main(String[] args) {
		
    	
    	System.out.println(isMatch("aa","a"));
		System.out.println(isMatch("aa","aa"));
		System.out.println(isMatch("aaa","aa"));
		System.out.println(isMatch("aa","*"));
		System.out.println(isMatch("aa","a*"));
		System.out.println(isMatch("aa","a?"));
		System.out.println(isMatch("ab","?*"));
		System.out.println(isMatch("aab","c*a*b"));
    	System.out.println(isMatch("aaaa","***a"));
		
	}
}
