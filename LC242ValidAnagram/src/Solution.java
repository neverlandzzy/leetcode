import java.util.HashMap;


public class Solution {
	/**
	 * Given two strings s and t, write a function to determine if t is an anagram of s.
	 * 
	 * For example,
	 * 
	 * s = "anagram", t = "nagaram", return true.
	 * s = "rat", t = "car", return false.
	 * 
	 * Note:
	 * You may assume the string contains only lowercase alphabets.
	 */
	
    public static boolean isAnagram(String s, String t) {
        /*
    	if (s.length() != t.length()) {
    		return false;
    	}
    	
    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		if (!map.containsKey(s.charAt(i))) {
    			map.put(s.charAt(i), 1);
    		} else {
    			map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
    		}
    	}
    	
    	for (int i = 0; i < t.length(); i++) {
    		if (!map.containsKey(t.charAt(i))) {
    			return false;
    		} else {
    			map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
    		}
    		
    		if (map.get(t.charAt(i)) == 0) {
    			map.remove(t.charAt(i));
    		}
    	}
    	
    	if (map.isEmpty()) {
    		return true;
    	} else {
    		return false;
    	}
    	*/
    	
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        }
        
        if (s.length() != t.length()) {
            return false;
        }
        
        int[] map = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
            map[t.charAt(i) - 'a']--;
        }
        
        for (int i = 0; i < 26; i++) {
            if (map[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    public static void main(String[] args) {
		String s1 = "anagram";
		String t1 = "nagaram";
		
		String s2 = "rat";
		String t2 = "car";
		
		System.out.println(isAnagram(s1, t1));
		System.out.println(isAnagram(s2, t2));
	}
}
