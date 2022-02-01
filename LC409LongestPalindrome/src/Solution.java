import java.util.HashMap;
import java.util.Map;


public class Solution {
	/**
	 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those 
	 * letters.
	 * 
	 * This is case sensitive, for example "Aa" is not considered a palindrome here.
	 * 
	 * Note:
	 * Assume the length of given string will not exceed 1,010.
	 * 
	 * Example:
	 * 
	 * Input:
	 * "abccccdd"
	 * 
	 * Output:
	 * 7
	 * 
	 * Explanation:
	 * One longest palindrome that can be built is "dccaccd", whose length is 7.
	 */
	
	// Solution 1:
	/*
    public static int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        int sum = 0;
        int odd = 0;
        
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            int val = entry.getValue();
            if (val % 2 == 0) {
                sum += val;
            } else {
                sum += val - 1;
                odd = 1;
            }
        }
        
        return sum + odd;
    }
    */
	
    // Solution 2:
    public static int longestPalindrome(String s) {
    	int[] count = new int[128];

		for (char c: s.toCharArray()) {
			count[c]++;
		}
    	
    	int sum = 0;
    	int odd = 0;
    	
    	for (int i = 0; i < 128; i++) {
    		if (count[i] % 2 == 0) {
    			sum += count[i];
    		} else {
    			sum += count[i] - 1;
    			odd = 1;
    		}
    	}
    	
    	return sum + odd;
    }
    
    public static void main(String[] args) {
		System.out.println(longestPalindrome("abccccdd"));
		System.out.println(longestPalindrome("abccccdd"));
	}
}
