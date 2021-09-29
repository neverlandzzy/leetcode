import java.util.Stack;


public class Solution {
	/**
	 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
	 * 
	 * Example 1:
	 * 
	 * Input: S = "ab#c", T = "ad#c"
	 * Output: true
	 * Explanation: Both S and T become "ac".
	 * 
	 * Example 2:
	 * 
	 * Input: S = "ab##", T = "c#d#"
	 * Output: true
	 * Explanation: Both S and T become "".
	 * 
	 * Example 3:
	 * 
	 * Input: S = "a##c", T = "#a#c"
	 * Output: true
	 * Explanation: Both S and T become "c".
	 * 
	 * Example 4:
	 * 
	 * Input: S = "a#c", T = "b"
	 * Output: false
	 * Explanation: S becomes "c" while T becomes "b".
	 * 
	 * Note:
	 * 
	 * 1 <= S.length <= 200
	 * 1 <= T.length <= 200
	 * S and T only contain lowercase letters and '#' characters.
	 */
	
	// Solution 1: Time: O(m + n), Space: O(m + n)
/*
    public static boolean backspaceCompare(String S, String T) {
        Stack<Character> s = new Stack<>();
        Stack<Character> t = new Stack<>();
        
        for (int i = 0; i < S.length(); i++) {
        	char c = S.charAt(i);
        	
        	if (c == '#') {
        		if (!s.isEmpty()) {
        			s.pop();
        		}
        	} else {
        		s.push(c);
        	}
        }
        
        for (int i = 0; i < T.length(); i++) {
        	char c = T.charAt(i);
        	
        	if (c == '#') {
        		if (!t.isEmpty()) {
        			t.pop();
        		}
        	} else {
        		t.push(c);
        	}
        }
        
        while (!s.isEmpty() && !t.isEmpty()) {
        	if (s.pop() != t.pop()) {
        		return false;
        	}
        }
        
        if (s.isEmpty() && t.isEmpty()) {
        	return true;
        }
        
        return false;
    }
*/

	// Solution 2: Time: O(m + n), Space: O(1)
	public static boolean backspaceCompare(String s, String t) {
		int i = s.length() - 1;
		int j = t.length() - 1;

		int counter1 = 0;
		int counter2 = 0;

		while (i >= 0 || j >= 0) {
			while (i >= 0) {
				if (s.charAt(i) == '#') {
					counter1++;
					i--;
				} else if (counter1 > 0) {
					counter1--;
					i--;
				} else {
					break;
				}
			}

			while (j >= 0) {
				if (t.charAt(j) == '#') {
					counter2++;
					j--;
				} else if (counter2 > 0) {
					counter2--;
					j--;
				} else {
					break;
				}
			}

			if (i >= 0 && j >= 0) {
				if (s.charAt(i) != t.charAt(j)) {
					return false;
				}
			}

			if (i < 0 && j < 0) {
				return true;
			}

			if (i < 0 || j < 0) {
				return false;
			}

			i--;
			j--;
		}

		return true;
	}
    
    public static void main(String[] args) {
		String s1 = "ab#c";
		String t1 = "ad#c";
		
		String s2 = "ab##";
		String t2 = "c#d#";
		
		String s3 = "a##c";
		String t3 = "#a#c";
		
		String s4 = "a#c";
		String t4 = "b";
		
		System.out.println(backspaceCompare(s1, t1));
		System.out.println(backspaceCompare(s2, t2));
		System.out.println(backspaceCompare(s3, t3));
		System.out.println(backspaceCompare(s4, t4));
	}
}
