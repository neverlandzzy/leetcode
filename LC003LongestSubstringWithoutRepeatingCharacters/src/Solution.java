import java.util.HashMap;
import java.util.Map;


public class Solution {

	/**
	 * Given a string, find the length of the longest substring without repeating characters. 
	 * 
	 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
	 * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
	 * 
	 */
	
	// similar to LC159 LC340
    public static int lengthOfLongestSubstring(String s) {
       if (s == null || s.length() == 0) {
           return 0;
       }
       
       int[] map = new int[256];
       int result = 0;
       int j = 0;
       
       for (int i = 0; i < s.length(); i++) {
           while (j < s.length()) {
               if (map[s.charAt(j)] == 0) {
                   map[s.charAt(j)] = 1;
               } else {                  
                   break;
               }
               result = Math.max(result, j - i + 1);
               j++;
           }
           map[s.charAt(i)] = 0;
       }
        
       return result;
    }
    
    // Another way
    /*
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        int[] map = new int[256];
        int result = 0;
        int j = 0;
        int i = 0;
        
        while (j < n) {
            if (map[s.charAt(j)] == 0) {
                map[s.charAt(j)]++;
                result = Math.max(result, j - i + 1);
                j++;
            } else {
                map[s.charAt(i)] = 0;
                i++;
            }
        } 
        
        return result;
    }
    */
   /*
	public static int lengthOfLongestSubstring(String s) {
	
		Map<Character, Integer> map = new HashMap<Character, Integer>(); 
		
		if(s.length() == 1) {
			return 1;
		}
		
		int j = 0; 
		int length = 0;
		
		for (int i = 0; i < s.length(); i++) {
			
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(j,map.get(s.charAt(i)) + 1);
			}
			
			length = Math.max(length, i-j+1);
			map.put(s.charAt(i), i);
		}
		
		return length;
	}
	*/
	
	public static void main(String[] args) {
		
		String testStr1 = "abcabcbb";
		String testStr2 = "bbbb";
		String testStr3 = "a";
		String testStr4 = "";
		String testStr5 = "au";
		String testStr6 = "abba";
		String testStr7 = "pwwkew";
		
		
		System.out.println(lengthOfLongestSubstring(testStr1));
		System.out.println(lengthOfLongestSubstring(testStr2));
		System.out.println(lengthOfLongestSubstring(testStr3));
		System.out.println(lengthOfLongestSubstring(testStr4));
		System.out.println(lengthOfLongestSubstring(testStr5));
		System.out.println(lengthOfLongestSubstring(testStr6));
		System.out.println(lengthOfLongestSubstring(testStr7));

		
		
		/*
		Map<Character, Integer> map = new HashMap<Character,Integer>();
		map.put('a', 1);
		map.put('a', 2);
		
		
		System.out.println(map.get('b'));
		*/
	}
}
