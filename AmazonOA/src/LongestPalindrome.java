import java.util.HashMap;


public class LongestPalindrome {
	/*
	 * Given a string which consists of lowercase or uppercase letters, find the length
	 * of the longest palindromes that can be built with those letters.
	 * 
	 * This is case sensitive, for example "Aa" is not considered a palindrome here.
	 * 
	 * Example
	 * 
	 * Given s = "abccccdd" return 7
	 * One longest palindrome that can be built is "dccaccd", whose length is 7.
	 */
	
    /**
     * @param s a string which consists of lowercase or uppercase letters
     * @return the length of the longest palindromes that can be built
     */
    public static int longestPalindrome(String s) {
        // Write your code here
    	
    	HashMap<Character, Integer> map = new HashMap<>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (!map.containsKey(c)) {
    			map.put(c, 1);
    		} else {
    			map.put(c, map.get(c) + 1);
    		}
    	}
    	
    	int odd = 0;
    	int counter = 0;
    	
    	for (int k: map.values()) {
    		if (k % 2 == 0) {
    			counter += k;
    		} else {
    			if (odd == 0) {
    				counter += k;
    				odd = 1;
    			} else {
    				counter += k - 1;
    			}
    		}
    	}
    	
    	return counter;
    }
    
    public static void main(String[] args) {
		System.out.println(longestPalindrome("abccccdd"));
	}
}
