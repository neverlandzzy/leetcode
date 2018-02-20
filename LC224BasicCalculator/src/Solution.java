import java.util.Stack;


public class Solution {
	
	/*
	 * Implement a basic calculator to evaluate a simple expression string.
	 * 
	 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, 
	 * non-negative integers and empty spaces .
	 * 
	 * You may assume that the given expression is always valid.
	 * 
	 * Some examples:
	 * "1 + 1" = 2
	 * " 2-1 + 2 " = 3
	 * "(1+(4+5+2)-3)+(6+8)" = 23
	 */
	
    public static int calculate(String s) {
    	
    	int sign = 1;
    	int result = 0;
    	int number = 0;
    	
    	Stack<Integer> stack = new Stack<Integer>();
        stack.push(sign);
        
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		
    		if (c == '+' || c == '-') {
    			result += sign * number;
    			sign = stack.peek() * (c == '+' ? 1 : -1);
    			number = 0;
    		}
    		
    		if (c >= '0' && c <= '9') {
    			number = 10 * number + (int)(c - '0');
    		}
    		
    		if (c == '(') { 		
    			stack.push(sign);
    		}
    		
    		if (c == ')') {
    			stack.pop();
    		}
    	}
    	
    	result += sign * number;
    	
    	return result;
    }
    
    public static void main(String[] args) {
		String test1 = "1 + 1";
		String test2 = " 2-1 + 2 ";
		String test3 = "(1+(4+5+2)-3)+(6+8)";
		
		//System.out.println(calculate(test1));
		//System.out.println(calculate(test2));
		System.out.println(calculate(test3));
		
	}
}
