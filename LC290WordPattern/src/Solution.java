import java.util.HashMap;
import java.util.Map;


public class Solution {
	/**
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
	
    public static boolean wordPattern(String pattern, String s) {

    	String[] words = s.split(" ");

    	if (words.length != pattern.length()) {
    		return false;
    	}

    	Map<Character, String> pattern2Str = new HashMap<>();
    	Map<String, Character> str2Pattern = new HashMap<>();

    	for (int i = 0; i < words.length; i++) {
			char c = pattern.charAt(i);
			String w = words[i];

			if (!pattern2Str.containsKey(c)) {
				if (str2Pattern.containsKey(w)) {
					return false;
				}
				pattern2Str.put(c, w);
				str2Pattern.put(w, c);
			} else {
				if (!str2Pattern.containsKey(w) || str2Pattern.get(w) != c) {
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
