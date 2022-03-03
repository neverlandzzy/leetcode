import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Solution {
	/**
	 * Given a string s and a string t, check if s is subsequence of t.
	 * 
	 * You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) 
	 * string, and s is a short string (<=100).
	 * 
	 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters 
	 * without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).
	 * 
	 * Example 1:
	 * s = "abc", t = "ahbgdc"
	 * 
	 * Return true.
	 * 
	 * Example 2:
	 * s = "axc", t = "ahbgdc"
	 * 
	 * Return false.
	 * 
	 * Follow up:
	 * If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its 
	 * subsequence. In this scenario, how would you change your code?
	 */
	
    public static boolean isSubsequence(String s, String t) {
    	// Solution 1:
    	// Time: O(mn) m -> s.length(), n -> t.length()
    	/*    	
    	if (s == null || s.length() == 0) {
    		return true;
    	}
    	
        int j = 0;
        
        for (int i = 0; i < t.length(); i++) {
        	if (s.charAt(j) == t.charAt(i)) {
        		j++;
        		if (j == s.length()) {
        			return true;
        		}
        	}
        }
        
        return false;
    	*/
    	
    	// Solution 2: faster, as we only scan s, which is much shorter. 
    	// Time: O(mn) m -> s.length(), n -> t.length()
    	/*
    	if (s == null || s.length() == 0) {
    		return true;
    	}
    	        
        int fromIndex = 0;
        
        for (int i = 0; i < s.length(); i++) {
        	fromIndex = t.indexOf(s.charAt(i), fromIndex);
        	if (fromIndex == -1) {
        		return false;
        	}
        	fromIndex++;
        }
        return true;
        */
        
        // Solution 3: for the follow-up question
        // Time: O(m * log(n)) m -> s.length(), n -> t.length()
    	if (s == null || s.length() == 0) {
    		return true;
    	}
    	
    	HashMap<Character, List<Integer>> map = new HashMap<>();
    	
    	for (int i = 0; i < t.length(); i++) {
    		char c = t.charAt(i);
    		
    		if (!map.containsKey(c)) {
    			map.put(c, new ArrayList<Integer>());
    		}
    		map.get(c).add(i);
    	}
    	//System.out.println(map);
    	int fromIndex = 0;
    	
    	for (int i = 0; i < s.length(); i++) {
    		if (!map.containsKey(s.charAt(i))) {
    			return false;
    		} else {
    			List<Integer> list = map.get(s.charAt(i));
    			fromIndex = helper(list, fromIndex);
    			
    			if (fromIndex == -1) {
    				return false;
    			}
    			
    			fromIndex++;
    		}
    	}
    	
    	return true;
    }
    
    // find the first element of list that is greater than or equal to the index that is passed in.
    private static int helper(List<Integer> list, int fromIndex) {
        int start = 0;
        int end = list.size() - 1;
        
        if (list.get(list.size() - 1) < fromIndex) {
            return -1;
        }
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (list.get(mid) == fromIndex) {
                return fromIndex;
            } else if (list.get(mid) < fromIndex) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (list.get(start) >= fromIndex) {
            return list.get(start);
        } else {
            return list.get(end);
        }
    }
    
    public static void main(String[] args) {
		System.out.println(isSubsequence("abc", "ahbgdc"));
		System.out.println(isSubsequence("axc", "ahbgdc"));
	}
}
