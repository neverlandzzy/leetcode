
public class Solution {
	/*
	 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
	 * 
	 * Examples:
	 * 
	 * s = "leetcode"
	 * return 0.
	 * 
	 * s = "loveleetcode",
	 * return 2.
	 * Note: You may assume the string contain only lowercase letters.
	 */
	
    public static int firstUniqChar(String s) {
        int[] array = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
        	array[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < s.length(); i++) {
        	if (array[s.charAt(i) - 'a'] == 1) {
        		return i;
        	}
        }
        
        return -1;
    }
    
    public static void main(String[] args) {
		String test1 = "leetcode";
		String test2 = "loveleetcode";
		
		System.out.println(firstUniqChar(test1));
		System.out.println(firstUniqChar(test2));
	}
}
