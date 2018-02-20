import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given n pairs of parentheses, write a function to generate all combinations of well-formed 
	 * parentheses.
	 * 
	 * For example, given n = 3, a solution set is:
	 * "((()))", "(()())", "(())()", "()(())", "()()()"
	 * 
	 */
	
	public static void addPair(List<String> result, String str, int open, int close, int size) {
		System.out.println(str);
		
		if (str.length() == 2*size) {
			
			result.add(str);
			System.out.println(result);
			return;
		}
		
		if (open < size) {
			addPair(result, str+"(", open+1, close, size);
			// sb.deleteCharAt(sb.length() - 1); // if using StringBuilder instead of String, need to delete last char
		}
		
		if (close < open) {
			addPair(result, str+")", open, close+1, size);
			// sb.deleteCharAt(sb.length() - 1); // if using StringBuilder instead of String, need to delete last char
		}


		
	}
    
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        
        addPair(result, "", 0, 0, n);
    	return result;
    }
    
    public static void main(String[] args) {
    	
    	//System.out.println(generateParenthesis(1));
    	System.out.println(generateParenthesis(3));
    	//System.out.println(generateParenthesis(4));

    }
}
