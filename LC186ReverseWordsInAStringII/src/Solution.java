
public class Solution {
	/*
	 * Given an input string, reverse the string word by word. A word is defined as a 
	 * sequence of non-space characters.
	 * 
	 * The input string does not contain leading or trailing spaces and the words are always 
	 * separated by a single space.
	 * 
	 * For example,
	 * Given s = "the sky is blue",
	 * return "blue is sky the".
	 * 
	 * Could you do it in-place without allocating extra space?
	 */
    public static void reverseWords(char[] s) {
    	reverseCharArray(s, 0, s.length-1);
    	
    	int start = 0;
    	
    	for (int i = 0; i <= s.length; i++) {

    		if (i == s.length || s[i] == ' ') {
    			reverseCharArray(s, start, i - 1);
    			start = i + 1;
    		}
    		
    	}

    }
    
    private static void reverseCharArray(char[] s, int start, int end) {
    	
    	while (start < end) {
    		char tmp = s[start];
    		s[start] = s[end];
    		s[end] = tmp;
    		start++;
    		end--;
    	}
    }
    
    /*
    //另一种写法，和LC151一致，方便记忆
    public static void reverseWords(char[] str) {
        int n = str.length;
        
        reverse(str, 0, n - 1);
        
        int i = 0;
        while (i < n) {
            if (str[i] == ' ') {
                i++;
            } else {
                int j = i;
                while (j < n - 1 && str[j + 1] != ' ') {
                    j++;
                }
                
                reverse(str, i, j);
                i = j + 1;
            }
        }
    }
    
    private static void reverse(char[] str, int i, int j) {
        while (i < j) {
            char c = str[i];
            str[i] = str[j];
            str[j] = c;
            i++;
            j--;
        }
    }
    */
    public static void main(String[] args) {
		String test = "the sky is blue";
		
		char[] testInArray = test.toCharArray();
		
		reverseWords(testInArray);
		
		System.out.println(String.copyValueOf(testInArray));
		
	}
}
