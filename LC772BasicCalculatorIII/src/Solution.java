import java.util.ArrayDeque;
import java.util.Deque;


public class Solution {
	/*
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
        int l1 = 0;
        int l2 = 1;
        int o1 = 1;
        int o2 = 1;
        int n = s.length();
        
        Deque<Integer> deque = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
        	char c = s.charAt(i);
        	
        	if (Character.isDigit(c)) {
        		int num = c - '0';
        		while (i < n - 1 && Character.isDigit(s.charAt(i + 1))) {
        			i++;
        			num = num * 10 + s.charAt(i) - '0';
        		}
       
        		l2 = (o2 == 1 ? l2 * num : l2 / num);
        	} else if (c == '(') {
        		
        	}
        	
        	
        }
    }
    
    public static void main(String[] args) {
		System.out.println(calculate("1 + 1"));
		System.out.println(calculate(" 6-4 / 2 "));
		System.out.println(calculate("2*(5+5*2)/3+(6/2+8)"));
		System.out.println(calculate("(2+6* 3+5- (3*14/7+2)*5)+3"));
	}
}
