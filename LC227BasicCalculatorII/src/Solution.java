import java.util.Stack;


public class Solution {
	/**
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
	
	// Solution 1: Time O(n), Space O(1)
	public static int calculate(String s) {
		int number = 0;
		int result = 0;
		int pre = 0;
		char operator = '+';
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			
			if (c >= '0' && c <= '9') {
				number = number * 10 + c - '0';
			}

			if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1) {
				if (operator == '+') {
					result += pre;
					pre = number;
				}
				
				if (operator == '-') {
					result += pre;
					pre = -number;
				}
				
				if (operator == '*') {
					pre = pre * number;
				}
				
				if (operator == '/') {
					pre = pre / number;
				}
				
				operator = c;
				number = 0;
			}
		}
		
		result += pre;
		return result;
	}
	
	
	// Solution 2: Time O(n), Space O(n)
	/*
    public static int calculate(String s) {
    	
    	int number = 0;
    	int result = 0;
    	char operator = '+';
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	
        	if (c >= '0' && c <= '9') {
        		number = number * 10 + (int) (c - '0');
        	} 
        	
        	if (!Character.isDigit(c) && c != ' ' || i == s.length() - 1){
        		if (operator == '+') {
        			stack.push(number);
        		}
        		if (operator == '-') {
        			stack.push(-number);
        		}
        		if (operator == '*') {
        			stack.push(stack.pop() * number);
        		}
        		if (operator == '/') {
        			stack.push(stack.pop() / number);
        		}
        		
        		operator = c;
        		number = 0;
        	}
        		
        }
        
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    */
    public static void main(String[] args) {
		System.out.println(calculate("3+2*2"));
		System.out.println(calculate(" 3/2 "));
		System.out.println(calculate(" 3+5 / 2 "));
	}
}
