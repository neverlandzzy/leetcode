
public class Solution {
	/*
	 * Given a word, you need to judge whether the usage of capitals in it is right or not.
	 * 
	 * We define the usage of capitals in a word to be right when one of the following cases holds:
	 * 	1. All letters in this word are capitals, like "USA".
	 * 	2. All letters in this word are not capitals, like "leetcode".
	 * 	3. Only the first letter in this word is capital if it has more than one letter, like "Google".
	 * Otherwise, we define that this word doesn't use capitals in a right way.
	 * 
	 * Example 1:
	 * Input: "USA"
	 * Output: True
	 * 
	 * Example 2:
	 * Input: "FlaG"
	 * Output: False
	 * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
	 */
	
    public static boolean detectCapitalUse(String word) {
        if (word == null || word.length() <= 1) {
        	return true;
        }
        
        boolean firstCap = false;
        boolean secondCap = false;
        
        if (Character.isUpperCase(word.charAt(0))) {
        	firstCap = true;
        } 
        
        if (Character.isUpperCase(word.charAt(1))) {
        	if (!firstCap) {
        		return false;
        	}
        	secondCap = true;
        }
        
        for (int i = 2; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (secondCap && Character.isLowerCase(c)) {
        		return false;
        	}
        	
        	if (!secondCap && Character.isUpperCase(c)) {
        		return false;
        	}
        }
        
        return true;
    }
    
    public static void main(String[] args) {
		System.out.println(detectCapitalUse("USA"));
		System.out.println(detectCapitalUse("FlaG"));
		System.out.println(detectCapitalUse("FLAg"));
	}
}
