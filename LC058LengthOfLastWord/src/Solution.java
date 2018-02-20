
public class Solution {
	
	/*
	 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', 
	 * return the length of last word in the string.
	 * 
	 * If the last word does not exist, return 0.
	 * Note: A word is defined as a character sequence consists of non-space characters only.
	 * 
	 * For example, 
	 * 	Given s = "Hello World",
	 * 	return 5.
	 */
	
    public static int lengthOfLastWord(String s) {
    	
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int counter = 0;
        
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                counter++;
            } 
            
            if (counter > 0 && s.charAt(i) == ' '){
                break;
            }
        }
        
        return counter;
    }
    
    public static void main(String[] args) {
		System.out.println(lengthOfLastWord("Hello World   "));
		System.out.println(lengthOfLastWord("  "));
		System.out.println(lengthOfLastWord(" a    b    "));
		System.out.println(lengthOfLastWord("a"));
		
	}
}
