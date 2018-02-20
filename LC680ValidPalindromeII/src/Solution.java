
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
	
	public static void main(String[] args) {
		System.out.println(validPalindrome("aba"));
		System.out.println(validPalindrome("abca"));
		System.out.println(validPalindrome("abc"));
	}
}
