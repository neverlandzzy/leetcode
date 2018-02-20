
public class Solution {
	/*
	 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. 
	 * If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, 
	 * then reverse the first k characters and left the other as original.
	 * 
	 * Example:
	 * Input: s = "abcdefg", k = 2
	 * Output: "bacdfeg"
	 * 
	 * Restrictions:
	 * The string consists of lower English letters only.
	 * Length of the given string and k will in the range [1, 10000]
	 */
	
    public static String reverseStr(String s, int k) {
        boolean flag = true;
        
        char[] charArray = s.toCharArray();
        int n = s.length();
        
        int i = 0;
        int j = k - 1;
        
        while (j < n) {
        	if (flag) {
        		reverse(charArray, i, j);
        		i += k;
        		j += k;
        		flag = false;
        	} else {
        		flag = true;
        		i += k;
        		j += k;
        	}
        }
        
        if (flag && i < n) {
        	reverse(charArray, i, n - 1);
        }
        
        return new String(charArray);
    }
    
    private static void reverse(char[] charArray, int start, int end) {
    	while (start < end) {
    		char tmp = charArray[start];
    		charArray[start] = charArray[end];
    		charArray[end] = tmp;
    		start++;
    		end--;
    	}
    }
    
    public static void main(String[] args) {
		String s = "abcdefg";
		
		System.out.println(reverseStr(s, 2));
	}
}
