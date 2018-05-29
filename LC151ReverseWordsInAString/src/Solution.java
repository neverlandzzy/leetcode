
public class Solution {
	/*
	 * Given an input string, reverse the string word by word.
	 * 
	 * For example,
	 * Given s = "the sky is blue",
	 * 
	 * return "blue is sky the".
	 * 
	 * Update (2015-02-12):
	 * For C programmers: Try to solve it in-place in O(1) space.
	 */
	
    public static String reverseWords(String s) {
    	StringBuilder sb = new StringBuilder();
    	
    	int end = s.length();
    	
    	for (int i = s.length() - 1; i >= 0; i--) {
    		if (s.charAt(i) == ' ') {
    			end = i;
    		} else if (i == 0 || s.charAt(i-1) == ' '){
    			if (sb.length() != 0) {
    				sb.append(' ');
    			}
    			sb.append(s.substring(i, end));
    		}
    	}
    	return sb.toString();
    }
    
    // Another solution
    /*
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        
        int i = n - 1;
        
        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                i--;
            } else {
                int j = i; 
                while (j >= 0 && s.charAt(j) != ' ') {
                    j--;
                }
                
                if (sb.length() == 0) {
                    sb.append(s.substring(j + 1, i + 1));
                } else {
                    sb.append(' ').append(s.substring(j + 1, i + 1));
                }
                
                i = j;
            }
            
        }
        
        return sb.toString();
    }
    */
    public static void main(String[] args) {
		String test = " the sky is blue ";
		String test2 = "  ";
		String test3 = "  1";
		
		System.out.println(reverseWords(test));
		
		//System.out.println(test);
		
		
	}
}
