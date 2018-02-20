
public class Solution {
	/*
	 * Given a string containing just the characters '(' and ')', 
	 * find the length of the longest valid (well-formed) parentheses substring.
	 * 
	 * For "(()", the longest valid parentheses substring is "()", which has length = 2.
	 * 
	 * Another example is ")()())", where the longest valid parentheses substring is "()()", 
	 * which has length = 4.
	 */
    public static int longestValidParentheses(String s) {
       
    	char[] str = s.toCharArray();  
    	int[] num = new int[str.length];
        int max = 0;
        int open = 0;
        
        for (int i = 0; i < str.length; i++) {
        	if (str[i] == '(') {
        		open++;
        	} 
        	if (str[i] == ')' && open > 0) {
        		num[i] = num[i-1] + 2;
        		
        		// e.g. '"()()()"' 对于第二个()， num[3] = num[2] + 2 = 2 --> 只统计了第二个()的长度，需要加上第一个的长度
        		// i - num[i] = 3 - 2 = 1 --> num[i - num[i]] = num[1] --> 第一个()的长度 
        
        		if (i - num[i] > 0) {
        			num[i] += num[i-num[i]];
        		}
        		open--;
        	}
        	
        	if( num[i] > max) {
        		max = num[i];
        	}
        }
        
        return max;
    }
    
    public static void main(String[] args) {
		System.out.println(longestValidParentheses("())"));
		System.out.println(longestValidParentheses("))()())"));
		System.out.println(longestValidParentheses("())"));
		System.out.println(longestValidParentheses("(())"));
		System.out.println(longestValidParentheses(""));
		System.out.println(longestValidParentheses("((()))"));
	}
}
