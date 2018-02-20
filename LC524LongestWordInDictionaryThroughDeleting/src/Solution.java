import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting 
	 * some characters of the given string. If there are more than one possible results, return the longest word with the 
	 * smallest lexicographical order. If there is no possible result, return the empty string.
	 * 
	 * Example 1:
	 * Input:
	 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
	 * Output: 
	 * "apple"
	 * 
	 * Example 2:
	 * Input:
	 * s = "abpcplea", d = ["a","b","c"]
	 * Output: 
	 * "a"
	 * 
	 * Note:
	 * All the strings in the input will only contain lower-case letters.
	 * The size of the dictionary won't exceed 1,000.
	 * The length of all the strings in the input won't exceed 1,000.
	 */
	
    public static String findLongestWord(String s, List<String> d) {
        String result = "";
        
        for (String word: d) {
        	if (isSubsequence(s, word)) {
        		if (word.length() > result.length() || (word.length() == result.length() && word.compareTo(result) < 0)) {
        			result = word;
        		}
        	}
        }
        
        return result;
    }
    
    private static boolean isSubsequence(String pattern, String word) {
    	if (word.length() > pattern.length()) {
    		return false;
    	}
    	
    	int j = 0;
    	
    	for (int i = 0; i < pattern.length() && j < word.length(); i++) {
    		if (pattern.charAt(i) == word.charAt(j)) {
    			j++;
    		}
    	}
    	
    	return j == word.length();
    }
    
    public static void main(String[] args) {
		String s = "abpcplea";
		List<String> list1 = new ArrayList<>();
		list1.add("ale");
		list1.add("apple");
		list1.add("monkey");
		list1.add("plea");
		
		List<String> list2 = new ArrayList<>();
		list2.add("a");
		list2.add("b");
		list2.add("c");
		
		System.out.println(findLongestWord(s, list1));
		System.out.println(findLongestWord(s, list2));
	}
}
