import java.util.Stack;


public class Solution {
	/**
	 * Given a string which contains only lowercase letters, remove duplicate letters so that 
	 * every letter appear once and only once. You must make sure your result is the smallest in 
	 * lexicographical order among all possible results.
	 * 
	 * Example:
	 * Given "bcabc"
	 * Return "abc"
	 * 
	 * Given "cbacdcbc"
	 * Return "acdb"
	 */
	
	// https://leetcode.com/problems/remove-duplicate-letters/discuss/76769/Java-solution-using-Stack-with-comments
	// Same as LC1081
    public static String removeDuplicateLetters(String s) {
    	int[] bitMap = new int[26];
    	boolean[] isVisited = new boolean[26];
    	
    	for (int i = 0; i < s.length(); i++) {
    		bitMap[s.charAt(i) - 'a']++;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	Stack<Character> stack = new Stack<>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		
    		bitMap[c - 'a']--;
    		
    		if (isVisited[c - 'a']) {
    			continue;
    		}

    		// e.g. 当stack中有 b, c, 剩余的s有abc， 则在a时，可以弹出b, c
    		while (!stack.isEmpty() && c < stack.peek() && bitMap[stack.peek() - 'a'] != 0) {
    			isVisited[stack.pop() - 'a'] = false;
    		}
    		
    		isVisited[c - 'a'] = true;
    		stack.push(c);
    	}

		while (!stack.isEmpty()) {
			sb.append(stack.pop());
		}

		return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
		String test1 = "bcabc";
		String test2 = "cbacdcbc";
		
		System.out.println(removeDuplicateLetters(test1));
		System.out.println(removeDuplicateLetters(test2));
	}
}


