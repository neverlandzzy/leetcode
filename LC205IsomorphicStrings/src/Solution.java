import java.util.HashMap;


public class Solution {
	/*
	 * Given two strings s and t, determine if they are isomorphic.
	 * 
	 * Two strings are isomorphic if the characters in s can be replaced to get t.
	 * 
	 * All occurrences of a character must be replaced with another character while preserving 
	 * the order of characters. No two characters may map to the same character but a character 
	 * may map to itself.
	 * 
	 * For example,
	 * Given "egg", "add", return true.
	 * Given "foo", "bar", return false.
	 * Given "paper", "title", return true.
	 */
	
    public static boolean isIsomorphic(String s, String t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        }
        
        if (s.length() != t.length()) {
            return false;
        }
        
        HashMap<Character, Character> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char d = t.charAt(i);
            
            if (!map.containsKey(c)) {                
                if (map.containsValue(d)) {
                    return false;
                }
                map.put(c, d);
            } else {
                if (map.get(c) != d) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
		System.out.println(isIsomorphic("egg", "add"));
		System.out.println(isIsomorphic("foo", "bar"));
		System.out.println(isIsomorphic("paper", "title"));
	}
}
