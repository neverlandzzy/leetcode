
public class Solution {
	/*
	 * Given a string, determine if it is a palindrome, considering only alphanumeric characters 
	 * and ignoring cases.
	 * 
	 * For example,
	 * "A man, a plan, a canal: Panama" is a palindrome.
	 * "race a car" is not a palindrome.
	 * 
	 * Note:
	 * Have you consider that the string might be empty? This is a good question to 
	 * ask during an interview.
	 * 
	 * For the purpose of this problem, we define empty string as valid palindrome.
	 */
	
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int i = 0; 
        int j = s.length() - 1;
        
        while (i < j) {
            while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
                   
            while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            
            if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
                return false;
            }
            
            i++;
            j--;
        }
        
        return true;

    }
    
    public static void main(String[] args) {
    	String test1 = "A man, a plan, a canal: Panama";
    	String test2 = "race a car";
    	String test3 = " ";
    	String test4 = ".,";
    	String test5 = "1a2";
    	
    	System.out.println(isPalindrome(test1));
    	System.out.println(isPalindrome(test2));
    	System.out.println(isPalindrome(test3));
    	System.out.println(isPalindrome(test4));
    	System.out.println(isPalindrome(test5));
	}
}
