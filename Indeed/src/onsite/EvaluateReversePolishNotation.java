package onsite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class EvaluateReversePolishNotation {
    
	public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        
        for (String s: tokens) {
        	if (!operators.contains(s)) {
        		stack.push(Integer.parseInt(s));
        	} else {
        		int val = 0;
        		int num1 = stack.pop();
        		int num2 = stack.pop();
        		
        		switch(s) {
        		case "+":
        			val = num2 + num1;
        			break;
        		case "-":
        			val = num2 - num1;
        			break;
        		case "*":
        			val = num2 * num1;
        			break;
        		case "/":
        			val = num2 / num1;
        			break;
        		}
        		stack.push(val);
        	}
        }
        
        return stack.pop();
    }
	
    public static void main(String[] args) {
		String[] test1 = {"2", "1", "+", "3", "*"};
		String[] test2 = {"4", "13", "5", "/", "+"};
		
		System.out.println(evalRPN(test1));
		System.out.println(evalRPN(test2));
	}
    
}
