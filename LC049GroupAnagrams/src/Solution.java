import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/**
	 * Given an array of strings, group anagrams together.
	 * 
	 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
	 * Return:
	 * 
	 * [
	 *   ["ate", "eat","tea"],
	 *   ["nat","tan"],
	 *   ["bat"]
	 * ]
	 * Note: All inputs will be in lower-case.
	 */

	
	public static List<List<String>> groupAnagrams(String[] strs) {
    	
        HashMap<String, List<String>> map= new HashMap<>();
    	
    	for (String s: strs) {
    		char[] charArray = s.toCharArray();
    		Arrays.sort(charArray);
    		String key = String.valueOf(charArray);
    		if (!map.containsKey(key)) {
    			map.put(key, new ArrayList<>());
    		}    		
    		map.get(key).add(s);
    	}
    	
    	return new ArrayList<>(map.values());
    }
    
	
	public static void main(String[] args) {
		String[] test = {"eat", "tea", "tan", "ate", "nat", "bat"};
		String[] test2 = {};
		System.out.println(groupAnagrams(test));
		System.out.println(groupAnagrams(test2));
	}
}
