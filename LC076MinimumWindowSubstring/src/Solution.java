import java.util.HashMap;
import java.util.Map;


public class Solution {
	/**
	 * Given a string S and a string T, find the minimum window in S which will contain all the 
	 * characters in T in complexity O(n).
	 * 
	 * For example,
	 * S = "ADOBECODEBANC"
	 * T = "ABC"
	 * Minimum window is "BANC".
	 * 
	 * Note:
	 * If there is no such window in S that covers all characters in T, return the emtpy string "".
	 * If there are multiple such windows, you are guaranteed that there will always be only one 
	 * unique minimum window in S.
	 * 
	 */
	
	// 描述类似于LC727，但解法不一样
	public static String minWindow(String s, String t) {
		int[] map = new int[256];
		for (char c: t.toCharArray()) {
			map[c]++;
		}

		int j = 0;
		int counter = 0;
		int minLen = Integer.MAX_VALUE;
		int minStart = 0;

		for (int i = 0; i < s.length(); i++) {
			map[s.charAt(i)]--;
			if (map[s.charAt(i)] >= 0) {
				counter++;
			}

			while (counter == t.length()) {
				map[s.charAt(j)]++;
				if (map[s.charAt(j)] > 0) {
					if (minLen > i - j + 1) {
						minLen = i - j + 1;
						minStart = j;
					}
					counter--;
				}
				j++;
			}
		}

		return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
	}

	/*
	public static String minWindow(String s, String t) {
    	Map<Character, Integer> map = new HashMap<>();
    	
    	for (int i = 0; i < t.length(); i++) {
    		if (!map.containsKey(t.charAt(i))) {
    			map.put(t.charAt(i), 1);
    		} else {
    			map.put(t.charAt(i), map.get(t.charAt(i)) + 1);
    		}
    	}
    	
    	int prev = 0; 
    	int counter = 0;
    	int minStart = 0;
    	int minLen = Integer.MAX_VALUE;
    	
    	for (int i = 0; i < s.length(); i++) {
    		if (map.containsKey(s.charAt(i))) {
    			map.put(s.charAt(i), map.get(s.charAt(i)) - 1);
    			
    			if (map.get(s.charAt(i)) >= 0) {
    				counter++;
    			}
    			
    			while (counter == t.length()) {
    				if (map.containsKey(s.charAt(prev))) {
    					map.put(s.charAt(prev), map.get(s.charAt(prev)) + 1);
    				
    					if (map.get(s.charAt(prev)) > 0) {
    						
    						if (minLen > i - prev + 1) {
    							minLen = i - prev + 1;
    							minStart = prev;
    						}
    					
    						counter--;
    					}
    				}
    				prev++;
    			}
    		}
    	}
	    
    	if (minLen == Integer.MAX_VALUE) {
    		return "";
    	}
    	return s.substring(minStart, minLen+minStart);
    }
    */
    public static void main(String[] args) {
		String A = "ADOBECODEBANC";
		String B = "ABC";
		
		System.out.println(minWindow(A,B));
		
		String C = "cabwefgewcwaefgcf";
		String D = "cae";
		System.out.println(minWindow(C,D));
		
		
	}
}
