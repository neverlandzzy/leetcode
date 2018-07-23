import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  
	 * Return a list of all possible strings we could create.
	 * 
	 * Examples:
	 * Input: S = "a1b2"
	 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
	 * 
	 * Input: S = "3z4"
	 * Output: ["3z4", "3Z4"]
	 * 
	 * Input: S = "12345"
	 * Output: ["12345"]
	 * 
	 * Note:
	 * 	1. S will be a string with length at most 12.
	 * 	2. S will consist only of letters or digits.
	 */
	
    public static List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        if (S == null || S.length() == 0) {
        	return result;
        }
        
        char[] chars = S.toCharArray();
        result.add("");
        
        for (int i = 0; i < chars.length; i++) {
        	char c = chars[i];
        	int size = result.size();
        	for (int k = 0; k < size; k++) {
        		String s = result.get(k);
	        	if (Character.isLetter(c)) {
	        		s +=  Character.toLowerCase(c);
	        		result.add(new String(s + Character.toUpperCase(c)));
	        	} else {
	        		s += c;
	        	}
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		System.out.println(letterCasePermutation("a1b2"));
		System.out.println(letterCasePermutation("3z4"));
		System.out.println(letterCasePermutation("12345"));
	}
}
