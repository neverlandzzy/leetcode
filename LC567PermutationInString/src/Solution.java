
public class Solution {
	/*
	 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, 
	 * one of the first string's permutations is the substring of the second string.
	 * 
	 * Example 1:
	 * Input:s1 = "ab" s2 = "eidbaooo"
	 * Output:True
	 * Explanation: s2 contains one permutation of s1 ("ba").
	 * 
	 * Example 2:
	 * Input:s1= "ab" s2 = "eidboaoo"
	 * Output: False
	 * Note:
	 *  1. The input strings only contain lower case letters.
	 *  2. The length of both given strings is in range [1, 10,000].
	 */
	
	// https://leetcode.com/problems/permutation-in-string/discuss/102590/8-lines-slide-window-solution-in-Java
    public static boolean checkInclusion(String s1, String s2) {
        int[] map = new int[26];
        int n1 = s1.length();
        int n2 = s2.length();
        int counter = n1;
        
        for (int i = 0; i < n1; i++) {
        	char c = s1.charAt(i);
        	map[c - 'a']++;
        }
        
        int i = 0;
        int j = 0;
        
        while (j < n2) {
        	char c = s2.charAt(j);
        	if (map[c - 'a'] > 0) {
        		map[c - 'a']--;
        		counter--;
        		if (counter == 0) {
        			return true;
        		}
        	} else {
        		while (i <= j && s2.charAt(i) != s2.charAt(j)) {
        			map[s2.charAt(i) - 'a']++;
        			i++;
        			counter++;
        		}
        		i++;
        	}
        	
        	j++;
        }
        
        return false;
    }
    
    public static void main(String[] args) {
		System.out.println(checkInclusion("ab", "eidbaooo"));
		System.out.println(checkInclusion("ab", "eidboaoo"));
	}
}
