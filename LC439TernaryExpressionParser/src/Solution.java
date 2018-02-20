import java.util.Stack;


public class Solution {
	/*
	 * Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression. 
	 * You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F 
	 * (T and F represent True and False respectively).
	 * 
	 * Note:
	 * 
	 * The length of the given string is ≤ 10000.
	 * Each number will contain only one digit.
	 * The conditional expressions group right-to-left (as usual in most languages).
	 * The condition will always be either T or F. That is, the condition will never be a digit.
	 * The result of the expression will always evaluate to either a digit 0-9, T or F.
	 * 
	 * Example 1:
	 * 
	 * Input: "T?2:3"
	 * Output: "2"
	 * Explanation: If true, then result is 2; otherwise result is 3.
	 * 
	 * Example 2:
	 * 
	 * Input: "F?1:T?4:5"
	 * Output: "4"
	 * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
     *        "(F ? 1 : (T ? 4 : 5))"                   "(F ? 1 : (T ? 4 : 5))"
     *     -> "(F ? 1 : 4)"                 or       -> "(T ? 4 : 5)"
     *     -> "4"                                    -> "4"
     *     
     * Example 3:
     * 
     * Input: "T?T?F:5:3"
     * Output: "F"
     * Explanation: The conditional expressions group right-to-left. Using parenthesis, it is read/evaluated as:
     *        "(T ? (T ? F : 5) : 3)"                   "(T ? (T ? F : 5) : 3)"
     *     -> "(T ? F : 3)"                 or       -> "(T ? F : 5)"
     *     -> "F"                                    -> "F"
	 */
	
	// Solution 1: iteration O(n^2) (push -- O(n), pop -- O(n))
	/*
    public static String parseTernary(String expression) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = expression.length() - 1; i >= 0; i--) {
        	char c = expression.charAt(i);
        	
        	if (!stack.isEmpty() && stack.peek() == '?') {
        		stack.pop(); // ?
        		char first = stack.pop();
        		stack.pop(); // :
        		char second = stack.pop();
        		
        		if (c == 'T') {
        			stack.push(first);
        		} else {
        			stack.push(second);
        		}
        	} else {
        		stack.push(c);
        	}
        }
        
        return String.valueOf(stack.peek());
    }
    */
	
	// Solution 2: dfs O(nlogn)
	
	public static String parseTernary(String expression) {
		
		if (expression == null || expression.length() == 0) {
			return expression;
		}
		
		return helper(expression.toCharArray(), 0, expression.length() - 1) + "";
	}
	
	private static char helper(char[] expression, int start, int end) {
		if (start == end) {
			return expression[start];
		}
		
		int i = start;
		int counter = 0;
		
		for (; i < end; i++) {
			if (expression[i] == '?') {
				counter++;
			} else if (expression[i] == ':'){
				counter--;
				
				if (counter == 0) {
					break;
				}
			}
		}
		
		return expression[start] == 'T' ? helper(expression, start + 2, i - 1) : helper(expression, i + 1, end);
	}
    
    public static void main(String[] args) {
		System.out.println(parseTernary("T?2:3"));
		System.out.println(parseTernary("F?1:T?4:5"));
		System.out.println(parseTernary("T?T?F:5:3"));

	}
}
