import java.util.Stack;


public class Solution {

	
    public static boolean validPalindrome(String s) {
    	
    	int i = 0;
    	int j = s.length() - 1;
    	
    	while (i < j) {
    		if (s.charAt(i) != s.charAt(j)) {
    			if (helper(s, i + 1, j) || helper(s, i, j - 1)) {
    				return true;
    			} else {
    				return false;
    			}
    		}
    		
    		i++;
    		j--;
    	}
    	
    	return true;
    }
    
    public static boolean helper(String s, int i, int j) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        if (i > j) {
        	return false;
        }
        
        while (i < j) {            
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }        
            i++;
            j--;
        }
        
        return true;
    }
    
    public static boolean checkValidString(String s) {
    	
    	if (s == null || s.length() == 0) {
    		return true;
    	}
    	
    	int counter = 0;
    	Stack<Character> stack = new Stack<>();
    	Stack<Character> stack2 = new Stack<>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c= s.charAt(i);
    		if (c == '(' || c == '*') {
    			stack.push(c);
    		} else {
    			while (!stack.isEmpty() && stack.peek() == '*') {
    				stack2.push(stack.pop());
    			}
    			if (!stack.isEmpty()) {
    				stack.pop();
    			} else {
    				if (!stack2.isEmpty()) {
    					stack2.pop();
    				} else {
    					return false;
    				}
    			}
    			while (!stack2.isEmpty()) {
    				stack.push(stack2.pop());
    			}
    		}
    	}
    	
    	
    	return (stack.isEmpty() || stack.size() <= counter);
    }
    
    public static void main(String[] args) {
    	/*
    	System.out.println(validPalindrome(""));
    	System.out.println(validPalindrome("aab"));
    	System.out.println(validPalindrome("abaa"));
    	System.out.println(validPalindrome("abbca"));
    	System.out.println(validPalindrome("abcbabb"));
    	*/
    	
    	/*
    	MapSum mapSum = new MapSum();
    	System.out.println(mapSum.sum("aa"));
    	mapSum.insert("aa", 3);
    	System.out.println(mapSum.sum("a"));
    	mapSum.insert("aa", 2);
    	//System.out.println(mapSum.map);
    	System.out.println(mapSum.sum("a"));
    	*/
    	/*
    	System.out.println(checkValidString(""));
    	System.out.println(checkValidString("()"));
    	System.out.println(checkValidString("()*"));
    	System.out.println(checkValidString("(*)"));
    	System.out.println(checkValidString("(*))"));
    	System.out.println(checkValidString("((*)"));
    	System.out.println(checkValidString("((*))"));
    	*/
    	System.out.println(checkValidString("*****((*"));
    	System.out.println(checkValidString(")))***"));
    	
    	
	}
}



