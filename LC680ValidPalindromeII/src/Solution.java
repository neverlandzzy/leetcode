
public class Solution {
	/*
	 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
	 * 
	 * Example 1:
	 * Input: "aba"
	 * Output: True
	 * 
	 * Example 2:
	 * Input: "abca"
	 * Output: True
	 * Explanation: You could delete the character 'c'.
	 * Note:
	 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
	 */
	
	// Time: O(n), Space: O(1)
    public static boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int i = 0;
        int j = s.length() - 1;
        
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            i++;
            j--;
        }
        
        return true;
    }
    
    private static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        
        return true;
    }
	
	
    /* 
    // 这个解法是易犯的错误："lcuppucul"会fail
    public static boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int n = s.length();
        int i = 0;
        int j = n - 1;
        boolean changed = false;
        
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                if (changed) {
                    return false;
                }
                
                if (i + 1 == j) {
                    return true;
                }
                
                if (s.charAt(i + 1) == s.charAt(j)) {
                    i++;
                    changed = true;
                } else if (s.charAt(i) == s.charAt(j - 1)) {
                    j--;
                    changed = true;
                } else {
                    return false;
                }
            }
            
            i++;
            j--;
        }
        
        return true;
    }
    */
    
    
	public static void main(String[] args) {
		System.out.println(validPalindrome("aba"));
		System.out.println(validPalindrome("abca"));
		System.out.println(validPalindrome("abc"));
		System.out.println(validPalindrome("lcuppucul"));
		
		
	}
}
