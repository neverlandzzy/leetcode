import java.util.Stack;


public class Solution {
	/*
	 * Implement a basic calculator to evaluate a simple expression string.
	 * 
	 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
	 * The integer division should truncate toward zero.
	 * 
	 * You may assume that the given expression is always valid.
	 * 
	 * Some examples:
	 * 
	 * "3+2*2" = 7
	 * " 3/2 " = 1
	 * " 3+5 / 2 " = 5
	 */
    public static int calculate(String s) {
    	
    	int number = 0;
    	int result = 0;
    	char sign = '+';
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	
        	if (c >= '0' && c <= '9') {
        		number = number * 10 + (int) (c - '0');
        	} 
        	
        	if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1){
        		if (sign == '+') {
        			stack.push(number);
        		}
        		if (sign == '-') {
        			stack.push(-number);
        		}
        		if (sign == '*') {
        			stack.push(stack.pop() * number);
        		}
        		if (sign == '/') {
        			stack.push(stack.pop() / number);
        		}
        		
        		sign = c;
        		number = 0;
        	}
        		
        }
        
        for (int i : stack) {
        	result += i;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		System.out.println(calculate("3+2*2"));
		System.out.println(calculate(" 3/2 "));
		System.out.println(calculate(" 3+5 / 2 "));
	}
}
