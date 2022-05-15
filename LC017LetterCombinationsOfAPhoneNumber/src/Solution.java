import java.util.ArrayList;
import java.util.List;



public class Solution {
	/**
	 * Given a digit string, return all possible letter combinations that the number could represent.
	 * 
	 * Input:Digit string "23"
	 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
	 */
	
	// Solotion 1: iteration
	
    public static List<String> letterCombinations(String digits) {
    	
    	List<String> result = new ArrayList<>();
    	String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno",  "pqrs", "tuv", "wxyz"};
    	result.add("");
    	
    	if (digits.length()==0) return result;
     	
    	
    	for (char c: digits.toCharArray()) {
    		String tmp = keys[c - '0'];
 
    		for(Object s: result.toArray()) {
    				for (int i = 0; i < tmp.length(); i++) {
    				result.add((String)s + tmp.charAt(i));
    				}
    			}	
    	}
    	
    	List<String> removed = new ArrayList<>();
    	
    	for (int i = 0; i < result.size(); i++) {
    		if (result.get(i).length() < digits.length()) {
    			removed.add(result.get(i));
    		}
    	}
    	for (String s: removed) {
    		result.remove(s);
    	}
    	return result;
     }
	
	// Time: O(k^n) where k = 3~4 (average keys length) ?
	/*
    public static List<String> letterCombinations(String digits) {
        
        List<String> result = new ArrayList<String>();
        
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder();
        
        helper(result, keys, digits, sb, 0);
        return result;
    }
    
    private static void helper(List<String> result, String[] keys, String digits, StringBuilder sb, int offset) {
        if (offset == digits.length()) {
            result.add(sb.toString());
            return;
        }
        
        String letters = keys[digits.charAt(offset) - '0'];
        
        for (int i = 0; i < letters.length(); i++) {
            helper(result, keys, digits, sb.append(letters.charAt(i)), offset + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    */

	/* Another backtracking solution
	public static List<String> letterCombinations(String digits) {
        String[] keys = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        StringBuilder sb = new StringBuilder();

        helper(result, keys, digits, sb, 0);
        return result;
    }

    private static void helper(List<String> result, String[] keys, String digits, StringBuilder sb, int pos) {
        if (sb.length() == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (int i = pos; i < digits.length(); i++) {
            String key = keys[digits.charAt(i) - '0'];

            for (char c: key.toCharArray()) {
                sb.append(c);
                helper(result, keys, digits, sb, i + 1);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
	 */
    public static void main (String[] args) {
    	
    	String test1 = "";
    	String test2 = "23";


    	System.out.println(letterCombinations(test1));
    	System.out.println(letterCombinations(test2));
    	
    }
}
