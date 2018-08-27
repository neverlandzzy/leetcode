
public class Solution {
	/*
	 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that 
	 * exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings 
	 * wrapped by bold tags are consecutive, you need to combine them.
	 * 
	 * Example 1:
	 * Input: 
	 * s = "abcxyz123"
	 * dict = ["abc","123"]
	 * Output:
	 * "<b>abc</b>xyz<b>123</b>"
	 * 
	 * Example 2:
	 * Input: 
	 * s = "aaabbcc"
	 * dict = ["aaa","aab","bc"]
	 * Output:
	 * "<b>aaabbc</b>c"
	 * Note:
	 * 	1. The given dict won't contain duplicates, and its length won't exceed 100.
	 * 	2. All the strings in input have length in range [1, 1000].
	 */
	
	// https://leetcode.com/problems/add-bold-tag-in-string/discuss/104248/Java-Solution-boolean-array
	
    public static String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        int end = 0;
        
        for (int i = 0; i < s.length(); i++) {
        	for (String word: dict) {
        		if (s.startsWith(word, i)) {
        			end = Math.max(end, i + word.length());
        		}
        	}
        	bold[i] = end > i;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
        	if (!bold[i]) {
        		sb.append(s.charAt(i));
        		continue;
        	}
        	
        	int j = i;
        	while (j < s.length() && bold[j]) {
        		j++;
        	}
        	
        	sb.append(("<b>" +  s.substring(i, j) + "</b>"));
        	i = j - 1;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	String s1 = "abcxyz123";
    	String[] dict1 = {"abc","123"};   	
		
    	String s2 = "aaabbcc";
    	String[] dict2 = {"aaa","aab","bc"};
    	
    	System.out.println(addBoldTag(s1, dict1));
    	System.out.println(addBoldTag(s2, dict2));
	}
}
