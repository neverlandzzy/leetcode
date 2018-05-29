import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/*
	 * Given a string s, return all the palindromic permutations (without duplicates) of it. 
	 * Return an empty list if no palindromic permutation could be form.
	 * 
	 * For example:
	 * 
	 * Given s = "aabb", return ["abba", "baab"].
	 * Given s = "abc", return [].
	 */
	
	//Solution 1: ! -- Time Limit Exceeded
	/*
    public static List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        boolean[] visited = new boolean[s.length()];
        
        helper(result, s, sb, visited);
        
        return result;
    }
    
    private static void helper (List<String> result, String s, StringBuilder sb, boolean[] visited) {
    	if (sb.length() == s.length() && isPalindrome(sb.toString()) && !result.contains(sb.toString())) {
    		result.add(sb.toString());
    		return;
    	}
    	
    	for (int i = 0; i < s.length(); i++) {
            if (i > 0 && !visited[i - 1] && s.charAt(i) == s.charAt(i-1)) {
                continue;
            }
            
            if(!visited[i]) {
	            visited[i] = true;
	    		sb.append(s.charAt(i));
	    		
	    		helper(result, s, sb, visited);
	    		visited[i] = false;
	    		sb.deleteCharAt(sb.length() - 1);
            }
    	}
    }
    
    private static boolean isPalindrome(String s) {
    	if (s == null || s.length() == 0) {
    		return true;
    	}
    	
    	int start = 0;
    	int end = s.length() - 1;
    	
    	while (start < end) {
    		if (s.charAt(start) != s.charAt(end)) {
    			return false;
    		} else {
    			start++;
    			end--;
    		}
    	}
    	
    	return true;
    }
    */
	
	
	// Solution 2:
    public static List<String> generatePalindromes(String s) {
    	
    	int odd = 0;
    	String mid = "";
        List<String> result = new ArrayList<String>();
        List<Character> list = new ArrayList<Character>();
        StringBuilder sb = new StringBuilder();
      
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	map.put(c, map.containsKey(c) ? (map.get(c) + 1) : 1);
        	odd += map.get(c) % 2 != 0 ? 1 : -1;
        }
        
        if (odd > 1) {
        	return result;
        }
        
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
        	char key = entry.getKey();
        	int val = entry.getValue();
        	
        	if (val % 2 != 0) {
        		mid += key;
        	}
        	
        	for (int i = 0; i < val / 2; i++) {
        		list.add(key);
        	}
        }
        boolean[] visited = new boolean[list.size()];
        
        helper(result, list, visited, sb, mid);
        
        return result;
    }
    
    private static void helper(List<String> result, List<Character> list, boolean[] visited, StringBuilder sb, String mid) {
    	if (sb.length() == list.size()) {
    		result.add(sb.toString() + mid + sb.reverse().toString());
    		//sb.reverse();
    		return;
    	}
    	
    	for (int i = 0; i < list.size(); i++) {
    		if (i > 0 && list.get(i - 1) == list.get(i) && !visited[i - 1]) {
    			continue;
    		}
    		
    		if (!visited[i]) {
    			sb.append(list.get(i));
    			visited[i] = true;
    			helper(result, list, visited, sb, mid);
    			visited[i] = false;
    			sb.deleteCharAt(sb.length() - 1);
    		}
    	}
    }
    
    public static void main(String[] args) {
    	String test1 = "aaaa";
    	String test2 = "aba";
    	
    	System.out.println(generatePalindromes(test1));
    	System.out.println(generatePalindromes(test2));
	}
}
