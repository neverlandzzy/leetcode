import java.util.HashMap;


public class Solution {
	/*
	 * Given a pattern and a string str, find if str follows the same pattern.
	 * 
	 * Here follow means a full match, such that there is a bijection between a letter 
	 * in pattern and a non-empty word in str.
	 * 
	 * Examples:
	 * 
	 * pattern = "abba", str = "dog cat cat dog" should return true.
	 * pattern = "abba", str = "dog cat cat fish" should return false.
	 * pattern = "aaaa", str = "dog cat cat dog" should return false.
	 * pattern = "abba", str = "dog dog dog dog" should return false.
	 * 
	 * Notes:
	 * You may assume pattern contains only lowercase letters, and str contains lowercase 
	 * letters separated by a single space.
	 */
	
    public static boolean wordPattern(String pattern, String str) {
        
    	String[] words = str.split(" ");
    	
    	if (words.length != pattern.length()) {
    		return false;
    	}
    	
    	HashMap<Character, String> map = new HashMap<Character, String>();
    	
    	for (int i = 0; i < words.length; i++) {
    		if (!map.containsKey(pattern.charAt(i)) && !map.containsValue(words[i])) {
    			map.put(pattern.charAt(i), words[i]);
    		} else {
    			if (!map.containsKey(pattern.charAt(i)) ||!map.get(pattern.charAt(i)).equals(words[i])) {
    				return false;
    			}
    		}
    	}
    	
    	return true;
    	
    }
    
    
    public static void main(String[] args) {
		String pattern = "abba";
		String test1 = "dog cat cat dog";
		String test2 = "dog cat cat fish";
		String test3 = "dog dog dog dog";
		
		System.out.println(wordPattern(pattern, test1));
		System.out.println(wordPattern(pattern, test2));
		System.out.println(wordPattern(pattern, test3));
	}
	
}
