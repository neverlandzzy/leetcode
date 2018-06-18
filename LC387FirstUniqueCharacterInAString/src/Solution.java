import java.util.Arrays;


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
	
	// Solution 1: O(n): O(2n), 2 pass
	/*
    public static int firstUniqChar(String s) {
        int[] map = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
        	map[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < s.length(); i++) {
        	if (map[s.charAt(i) - 'a'] == 1) {
        		return i;
        	}
        }
        
        return -1;
    }
    */
	
	// Solution 2: O(n): 1 pass
	public static int firstUniqChar(String s) {
		int[] map = new int[26];
		
		Arrays.fill(map, -1);
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map[c - 'a'] == -1) {
				map[c - 'a'] = i;
			} else {
				map[c - 'a'] = -2;
			}
		}
		
		int index = s.length();
		
		for (int i = 0; i < map.length; i++) {
			if (map[i] >= 0) {
				index = Math.min(index, map[i]);
			}
		}
		
		return index == s.length() ? -1 : index;
	}
	
	// Solution 3: O(n): 2 pass
	/*
    public static int firstUniqChar(String s) {
        int[] map = new int[26];
        int i = 0;
        int j = 0;
        int n = s.length();
        
        while (j < n) {
            map[s.charAt(j) - 'a']++;
            
            while (i <= j && map[s.charAt(i) - 'a'] > 1) {
                i++;
            }
                       
            j++;
        }
        
        return i == n ? -1 : i;
    }
	*/
	
    public static void main(String[] args) {
		String test1 = "leetcode";
		String test2 = "loveleetcode";
		
		System.out.println(firstUniqChar(test1));
		System.out.println(firstUniqChar(test2));
	}
}
