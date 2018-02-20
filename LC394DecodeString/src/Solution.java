import java.util.Stack;


public class Solution {
	/*
	 * Given an encoded string, return it's decoded string.
	 * 
	 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being 
	 * repeated exactly k times. Note that k is guaranteed to be a positive integer.
	 * 
	 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
	 * 
	 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for 
	 * those repeat numbers, k. For example, there won't be input like 3a or 2[4].
	 * 
	 * Examples:
	 * s = "3[a]2[bc]", return "aaabcbc".
	 * s = "3[a2[c]]", return "accaccacc".
	 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
	 */
	
    public static String decodeString(String s) {
        
        String result = "";
    	Stack<Integer> counterStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();
        int index = 0;
        
        while(index < s.length()) {
        	
        	if (Character.isDigit(s.charAt(index))) {
	        	int counter = 0;
	        	while (Character.isDigit(s.charAt(index))) {
	        		counter = counter * 10 + (s.charAt(index) - '0');
	        		index++;
	        	}
	        	counterStack.push(counter);
        	} else if (s.charAt(index) == '[') {
        		stringStack.push(result);
        		result = "";
        		index++;
        	} else if (s.charAt(index) == ']') {
        		StringBuilder sb = new StringBuilder(stringStack.pop());
        		int counter = counterStack.pop();
        		for (int i = 0; i < counter; i++) {
        			sb.append(result);
        		}
        		result = sb.toString();
        		index++;
        	} else {
        		result += s.charAt(index);
        		index++;
        	}   	
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		System.out.println(decodeString("3[a]2[bc]"));
		System.out.println(decodeString("3[a2[c]]"));
		System.out.println(decodeString("2[abc]3[cd]ef"));
		
		String test = "ww";
		System.out.println();
	}
}
