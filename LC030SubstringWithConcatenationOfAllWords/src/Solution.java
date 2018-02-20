import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Solution {
	/*
	 * You are given a string, s, and a list of words, words, that are all of the same length. 
	 * Find all starting indices of substring(s) in s that is a concatenation of each word in words 
	 * exactly once and without any intervening characters.
	 * 
	 * For example, given:
	 * s: "barfoothefoobarman"
	 * words: ["foo", "bar"]
	 * You should return the indices: [0,9].
	 * (order does not matter).
	 */
	
    public static List<Integer> findSubstring(String s, String[] words) {
    	
    	List<Integer> result = new ArrayList<Integer>();
    	
    	int wordLen = words[0].length();
    	
    	HashMap<String, Integer> wMap = new HashMap<String, Integer>();
    	
    	for (String word: words) {
    		if (!wMap.containsKey(word)) {
    			wMap.put(word, 1);
    		} else {
    			wMap.put(word, wMap.get(word) + 1);
    		}
    	}
    	
    	for (int i = 0; i < wordLen; i++) {
    		
        	int count = 0;
    		int prev = i;
    		HashMap<String, Integer> sMap = new HashMap<String, Integer>();
    		
    		for (int j = i; j <= s.length() - wordLen; j += wordLen) {  			
    			
    			String curWord = s.substring(j, j + wordLen);
    			
    			if (!wMap.containsKey(curWord)) {
    				prev = j + wordLen;
    				sMap.clear();
    				count = 0;
    			} else {
    				if (!sMap.containsKey(curWord)) {
    					sMap.put(curWord, 1);
    				} else {
    					sMap.put(curWord, sMap.get(curWord)+1);
    				}
    				
    				if (sMap.get(curWord) <= wMap.get(curWord)) {
    					count++;
    				} else {
    					while( sMap.get(curWord) > wMap.get(curWord)) {
    						String prevWord = s.substring(prev, prev + wordLen);
    						sMap.put(prevWord, sMap.get(prevWord)-1);
    						
    						if (sMap.get(prevWord) < wMap.get(prevWord)) {
    							count--;
    						}
    						prev += wordLen;
    					}
    				}
        			
        			if (count == words.length) {
        				result.add(prev);
        				String prevWord = s.substring(prev, prev + wordLen);
        				sMap.put(prevWord, sMap.get(prevWord)-1);
        				prev += wordLen;
        				count--;
        			}
    			}

    		}
    	}
    	
    	return result;
        
    }
    
    public static void main(String[] args) {
		String s = "barfoothefoobarman";
		String[] words = {"foo", "bar"};
		
		System.out.println(findSubstring(s, words));
	}
}
