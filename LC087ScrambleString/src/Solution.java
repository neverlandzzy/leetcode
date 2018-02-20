
public class Solution {
	/*
	 * Given a string s1, we may represent it as a binary tree by partitioning it to 
	 * two non-empty substrings recursively.
	 * 
	 * Below is one possible representation of s1 = "great":
	 *     great
	 *    /    \
	 *   gr    eat
	 *  / \    /  \
	 * g   r  e   at
	 *     / \
	 *    a   t
	 *    
	 * To scramble the string, we may choose any non-leaf node and swap its two children.
	 * 
	 * For example, if we choose the node "gr" and swap its two children, it produces a 
	 * scrambled string "rgeat".
	 * 
	 *    rgeat
	 *    /    \
	 *   rg    eat
	 *  / \    /  \
	 * r   g  e   at
	 *     / \
	 *    a   t
	 *    
	 * We say that "rgeat" is a scrambled string of "great".
	 * 
	 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a 
	 * scrambled string "rgtae".
	 * 
	 *     rgtae
	 *     /    \
	 *    rg    tae
	 *   / \    /  \
	 *  r   g  ta  e
	 *      / \
	 *     t   a
	 *     
	 * We say that "rgtae" is a scrambled string of "great".
	 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
	 */
	
	// http://wlcoding.blogspot.com/2015/03/scramble-string.html?view=sidebar
    public static boolean isScramble(String s1, String s2) {
    	/*
    	// Solution 1: Time O(n ^ 4) Space O(n ^ 3)
        int n = s1.length();
        
        if (s2.length() != n) {
        	return false;
        }
        
        if (s1.equals(s2)) {
        	return true;
        }
        
        boolean [][][] d = new boolean[n][n][n];
        // d[i][j][k] = true iff s1.substring(i,i+k+1) and s2.substring(j,j+k+1) is scrambled pair 
        
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		if (s1.charAt(i) == s2.charAt(j)) {
        			d[i][j][0] = true;
        		}
        	}
        }
        
        for (int k = 1; k < n; k++) {
        	for(int i = 0; i < n - k; i++) {
        		for(int j = 0; j < n-k; j++) {
        			for(int p = 0; p < k; p++) {
        				
        				d[i][j][k] = (d[i][j][p] && d[i+p+1][j+p+1][k-p-1])||(d[i][j+k-p][p] && d[i+p+1][j][k-p-1]);
        				
        				if(d[i][j][k]) {
        					break;
        				}
        			}
        		}
        	}
        }
        return d[0][0][n-1];
        */
        // Solution 2: Time ?
        
        if (s1.length() != s2.length()) {
            return false;
        }
        
        if (s1.equals(s2)) {
            return true;
        }
        
        int[] letters = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            letters[s1.charAt(i)]++;
            letters[s2.charAt(i)]--;
        }
        
        for (int i = 0; i < 256; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }
        
        for (int i = 1; i < s1.length(); i++) {
        	// 如果从i = 0开始，isScramble(s1.substring(i), s2.substring(i))将一直重复检查s1, s2，长度不会减少，因此会死循环
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
            	
                return true;
            }
            
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length()-i))) {
                return true;
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
		String test1 = "great";
		String test2 = "rgtae";
		System.out.println(isScramble(test1, test2));
	}
}
