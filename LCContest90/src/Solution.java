
public class Solution {
	
	
    public static boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
        	return false;
        }
        
        if (A.length() <= 1) {
        	return false;
        }
        
        if (A.equals(B)) {
        	int[] map = new int[26];
        	for (int i = 0; i < A.length(); i++) {
        		char c = A.charAt(i);
        		map[c - 'a']++;
        	}
        	
        	for (int i = 0; i < map.length; i++) {
        		if (map[i] > 0 && map[i] % 2 == 0) {
        			return true;
        		}
        	}
        	
        	return false;
        }
        
        Integer index1 = null;
        Integer index2 = null;
        
        for (int i = 0; i < A.length(); i++) {
        	if (A.charAt(i) != B.charAt(i)) {
        		if (index1 == null) {
        			index1 = i;
        		} else if (index2 == null) {
        			index2 = i;
        			break;
        		}
        	}
        }
        
        if (index1 == null || index2 == null) {
        	return false;
        }
        
        char[] arr = A.toCharArray();
        swap(arr, index1, index2);
        
        if (String.valueOf(arr).equals(B)) {
        	return true;
        }
        
        return false;
    }
    
    private static void swap(char[] arr, int i, int j) {
    	char c = arr[i];
    	arr[i] = arr[j];
    	arr[j] = c;
    }
    

    public static int scoreOfParentheses(String S) {
        return helper(S, 0, S.length() - 1);
    }
    
    private static int helper(String s, int start, int end) {
    	int i = start;
    	int open = 0;
    	
    	if (start >= end) {
    		return 0;
    	}
    	
    	if (start + 1 == end) {
    		return 1;
    	}
    	
    	while (i <= end) {
    		if (s.charAt(i) == '(') {
    			open++;
    		} else {
    			open--;
    		}
    		
    		if (open == 0) {
    			if (i == end) {
    				return 2 * helper(s, start + 1, end -1);
    			} else {
    				return helper(s, start, i) + helper(s, i + 1, end);
    			}
    		}
    		i++;
    	}
    	
    	return 0;
    }

    public static int mirrorReflection(int p, int q) {
        
    }
    
	public static void main(String[] args) {
		/*
		String A1 = "aaaaaaabc";
		String B1 = "aaaaaaacb";		
		System.out.println(buddyStrings(A1, B1));
		
		String A2 = "ab";
		String B2 = "ab";
		System.out.println(buddyStrings(A2, B2));
		
		String A3 = "aa";
		String B3 = "aa";
		System.out.println(buddyStrings(A3, B3));
		
		String A4 = "";
		String B4 = "";
		System.out.println(buddyStrings(A4, B4));
		
		String A5 = "abab";
		String B5 = "abab";
		System.out.println(buddyStrings(A5, B5));
		
		String test21 = "()()";
		System.out.println(scoreOfParentheses(test21));
		String test22 = "(())";
		System.out.println(scoreOfParentheses(test22));
		String test23 = "(()(()))";
		System.out.println(scoreOfParentheses(test23));
		String test24 = "(())()";
		System.out.println(scoreOfParentheses(test24));
		
		String test25 = "()";
		System.out.println(scoreOfParentheses(test25));
		*/
		
		System.out.println(mirrorReflection(2, 1));

	}
}
