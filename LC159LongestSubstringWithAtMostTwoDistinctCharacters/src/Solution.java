import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Given a string, find the length of the longest substring T that contains at 
	 * most 2 distinct characters.
	 * 
	 * For example, Given s = “eceba”,
	 * T is "ece" which its length is 3.
	 */
	
	// similar to LC3 LC340
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int[] map = new int[256];
        int result = 0;
        int counter = 0;
        int j = 0;
        
        for (int i = 0; i < s.length(); i++) {        
            
            while (j < s.length()) {
                if (map[s.charAt(j)] != 0) {
                    map[s.charAt(j)]++;
                } else {
                    if (counter == 2) {
                        break;
                    } 
                    map[s.charAt(j)]++;
                    counter++;
                }
                result = Math.max(result, j - i + 1);
                j++;
            }
                        
            map[s.charAt(i)]--;
            
            if (map[s.charAt(i)] == 0) {
                counter--;
            }
        }
        
        return result;
    }
    
    /*
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        int[] count = new int[256];
        int start = 0;
        int numOfDistinct = 0;
        int maxLen = 0;
        
        for (int i = 0; i < s.length(); i++) {
        	if (count[s.charAt(i)] == 0) {
        		numOfDistinct++;
        	}
        	count[s.charAt(i)]++;
        	
        	while (numOfDistinct > 2) {
        		
        		count[s.charAt(start)]--;
        		
        		if (count[s.charAt(start)] == 0) {
        			numOfDistinct--;
        		}
        		
        		start++;
        	}
        	
        	maxLen = Math.max(maxLen, i - start + 1);
        }
        
        return maxLen;
    }
    */
    
    public static void main(String[] args) {
    	String test = "e";
		String test2 = "eceba";
		
		System.out.println(lengthOfLongestSubstringTwoDistinct(test));
	}
}
