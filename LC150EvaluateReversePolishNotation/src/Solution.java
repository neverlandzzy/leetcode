import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;


public class Solution {
	/*
	 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
	 * 
	 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
	 * 
	 * Some examples:
	 * 
	 *   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	 *   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
	 */
	
    public static int evalRPN(String[] tokens) {
    	Set<String> operators = new HashSet<String>(Arrays.asList("+", "-", "*", "/"));
        Stack<Integer> stack = new Stack<Integer>();
        
        for (String s: tokens) {
        	if (operators.contains(s)) {
        		int y = stack.pop();
        		int x = stack.pop();
        		stack.push(eval(x,y,s));
        	} else {
        		stack.push(Integer.valueOf(s));
        	}
        }
        
        return stack.pop();
    }
    
    private static int eval(int x, int y, String operator) {
    	switch (operator) {
    	
    	case "+": return x+y;
    	case "-": return x-y;
    	case "*": return x*y;
    	default:  return x/y;
    	}
    }
    
    public static void main(String[] args) {
		String[] test = {"2", "1", "+", "3", "*"};
		System.out.println(evalRPN(test));
	}
}
