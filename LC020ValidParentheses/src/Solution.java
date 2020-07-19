import java.util.Stack;


public class Solution {
	/**
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if 
	 * the input string is valid.
	 * 
	 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" 
	 * are not.
	 */
	
    public static boolean isValid(String s) {
        Stack<Character> stringStack = new Stack<Character>();
        
        for (int i = 0; i < s.length(); i++) {
        	if(s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{' ) {
        		stringStack.push(s.charAt(i));
        	} else if (s.charAt(i) == ')') {
        		if (stringStack.isEmpty() || stringStack.pop() != '(') {
        			return false;
        		}
        	} else if (s.charAt(i) == ']') {
        		if (stringStack.isEmpty() || stringStack.pop() != '[') {
        			return false;
        		}
        	} else if (s.charAt(i) == '}') {
        		if (stringStack.isEmpty() || stringStack.pop() != '{') {
        			return false;
        		}
        	}
        }
        
        return stringStack.isEmpty();
        
    }
    
    public static void main(String[] args) {
    	String test1 = "()[]{}";
    	String test2 = "({[]})";
    	String test3 = "(]";
    	String test4 = "([)]";
    	String test5 = "C]]";
    	String test6 = "]]";
    	String test7 = "{";
    	
    	System.out.println(isValid(test1));
    	System.out.println(isValid(test2));
    	System.out.println(isValid(test3));
    	System.out.println(isValid(test4));
    	System.out.println(isValid(test5));
    	System.out.println(isValid(test6));
    	System.out.println(isValid(test7));

    }
}
