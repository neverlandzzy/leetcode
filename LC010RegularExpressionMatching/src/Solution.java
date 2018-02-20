
public class Solution {
	/*
	 * Implement regular expression matching with support for '.' and '*'.
	 * 
	 * '.' Matches any single character.
	 * '*' Matches zero or more of the preceding element.
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * The function prototype should be:
	 * bool isMatch(const char *s, const char *p)
	 * 
	 * Some examples:
	 * isMatch("aa","a") -> false
	 * isMatch("aa","aa") -> true
	 * isMatch("aaa","aa") -> false
	 * isMatch("aa", "a*") -> true
	 * isMatch("aa", ".*") -> true
	 * isMatch("ab", ".*") -> true
	 * isMatch("aab", "c*a*b") -> true
	 */
	
    public static boolean isMatch(String s, String p) {
    	
    	// Solution 1: 2D - DP
    	/*
        boolean[][] isMatch = new boolean[s.length() + 1][p.length() + 1];
        
        isMatch[0][0] = true;
        
        for (int i = 1; i <= s.length(); i++) {
            isMatch[i][0] = false;
        }
        
        for (int j = 1; j <= p.length(); j++) {
            isMatch[0][j] = (j > 1) && isMatch[0][j - 2] && p.charAt(j - 1) == '*';
        }
        
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                if (p.charAt(j-1) != '*') {
                    isMatch[i][j] = isMatch[i - 1][j - 1] && (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.');
                } else {
                    isMatch[i][j] = (j > 1) && ((isMatch[i][j - 2]) || (isMatch[i - 1][j] && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')));
                }
            }
        }
        
        return isMatch[s.length()][p.length()];
    	*/
    	
    	// Solution 2: 1D - DP
    	boolean[] isMatch = new boolean[p.length() + 1];
    	isMatch[0] = true;
    	
    	for (int i = 2; i <= p.length(); i++) {
    		isMatch[i] = isMatch[i - 2] && p.charAt(i - 1) == '*';
    	}
    	
    	for (int i = 1; i <= s.length(); i++) {
    		boolean prev = isMatch[0]; // isMatch[i - 1][j - 1];
    		isMatch[0] = false;
    		
    		for (int j = 1; j <= p.length(); j++) {
    			boolean cur = isMatch[j]; // isMatch[i - 1][j];
    		
    			if (p.charAt(j - 1) != '*') {
    				isMatch[j] = (prev && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'));
    			} else {
    				isMatch[j] = (j > 1) && ((isMatch[j - 2]) || (cur && (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')));
    			}
    			prev = cur;
    		}
    	}
    	
    	return isMatch[p.length()];
    }
    
    public static void main(String[] args) {

    	
    	System.out.println("1 - " + isMatch("aa", "a"));
    	System.out.println("2 - " + isMatch("aa", "a."));
    	System.out.println("3 - " + isMatch("aa", "aa"));
    	System.out.println("4 - " + isMatch("aa", "a*"));
    	System.out.println("5 - " + isMatch("aa", "aa*"));
    	System.out.println("6 - " + isMatch("aa", "aa."));
    	System.out.println("7 - " + isMatch("aa", "a*a"));
    	System.out.println("8 - " + isMatch("aa", ".*"));
    	System.out.println("9 - " + isMatch("aab", "c*a*b"));
    	
    }
}
