import java.util.Stack;


public class Solution {
	/**
	 * Implement a basic calculator to evaluate a simple expression string.
	 * 
	 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .
	 * 
	 * The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . 
	 * The integer division should truncate toward zero.
	 * 
	 * You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].
	 * 
	 * Some examples:
	 * 
	 * "1 + 1" = 2
	 * " 6-4 / 2 " = 4
	 * "2*(5+5*2)/3+(6/2+8)" = 21
	 * "(2+6* 3+5- (3*14/7+2)*5)+3"=-12
	 *  
	 * Note: Do not use the eval built-in library function.
	 */
	
	// https://leetcode.com/problems/basic-calculator-iii/discuss/113592/Development-of-a-generic-solution-for-the-series-of-the-calculator-problems
	// 2 levels: l1 and l2
	// operators: +: o1 = 1, -: o1 = -1, *: o2 = 1, /: o2 = -1
	// level 1: +, -, starts with l1 = 0, o1 = 1;
	// level 2: *, /, starts with l2 = 1, o2 = 1;
	// O(n) time, O(n) space
    public static int calculate(String s) {

    }

    // Solution 2: Recursion -- based on LC227
    // Time: O(n^2), Space: O(n)
    // https://leetcode.com/problems/basic-calculator-iii/discuss/129179/Java-Solution-with-little-adjustment-of-Basic-Calculator-II
    /*
    public static int calculate(String s) {
    	int number = 0;
    	int result = 0;
    	char operator = '+';
    	int n = s.length();
    	
    	Stack<Integer> stack = new Stack<Integer>();
    	
        for (int i = 0; i < n; i++) {
        	char c = s.charAt(i);
        	
        	if (c >= '0' && c <= '9') {
        		number = number * 10 + (int) (c - '0');
        	} else if (c == '(') {
        		int j = i + 1;
        		int count = 1;
        		
        		while (j < n) {
        			if (s.charAt(j) == '(') {
        				count++;
        			} else if (s.charAt(j) == ')') {
        				count--;
        			}
        			
        			if (count == 0) {
        				break;
        			}
        			
        			j++;
        		}
        		
        		number = calculate(s.substring(i + 1, j));
        		i = j;
        	}
        	
        	if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1){
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
		System.out.println(calculate("1 + 1"));
		System.out.println(calculate(" 6-4 / 2 "));
		System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
		System.out.println(calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
	}
}
