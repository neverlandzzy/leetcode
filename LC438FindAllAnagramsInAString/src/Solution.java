import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
	 * 
	 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
	 * 
	 * The order of output does not matter.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * s: "cbaebabacd" p: "abc"
	 * 
	 * Output:
	 * [0, 6]
	 * 
	 * Explanation:
	 * The substring with start index = 0 is "cba", which is an anagram of "abc".
	 * The substring with start index = 6 is "bac", which is an anagram of "abc".
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * s: "abab" p: "ab"
	 * 
	 * Output:
	 * [0, 1, 2]
	 * 
	 * Explanation:
	 * The substring with start index = 0 is "ab", which is an anagram of "ab".
	 * The substring with start index = 1 is "ba", which is an anagram of "ab".
	 * The substring with start index = 2 is "ab", which is an anagram of "ab".
	 */
	
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] map = new int[256];
        
        for (int i = 0; i < p.length(); i++) {
            map[p.charAt(i)]++;
        }
        
        int start = 0;
        int end = 0;
        int counter = p.length();
        
        while (end < s.length()) {
            if (map[s.charAt(end)] > 0) {
                counter--;
            }
            map[s.charAt(end)]--;
            end++;
            
            if (counter == 0) {
                result.add(start);
            }
            
            if (end - start == p.length()) {
                if (map[s.charAt(start)] >= 0) {
                    counter++;
                }
                map[s.charAt(start)]++;
                start++;
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String s1 = "cbaebabacd";
		String p1 = "abc";
		
		String s2 = "abab";
		String p2 = "ab";
		
		System.out.println(findAnagrams(s1, p1));
		System.out.println(findAnagrams(s2, p2));
	}
}
