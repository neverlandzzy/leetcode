
public class Solution {
	/**
	 * Write a function that takes a string as input and returns the string reversed.
	 * 
	 * Example:
	 * Given s = "hello", return "olleh".
	 */
	
	// Solution 1: Time: O(n), Space: O(n)
    public static String reverseString(String s) {
        
        if (s == null || s.length() == 0) {
            return s;
        }
        
        int n = s.length();
        char[] charArray = s.toCharArray();
        
        int i = 0;
        int j = n - 1;
        
        while (i < j) {
            char c = charArray[i];
            charArray[i] = charArray[j];
            charArray[j] = c;
            i++;
            j--;
        }
        
        return new String(charArray);
    }
    
    
    // Solution 2: Time: O(n), Space: O(n)
    /*
    public static String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));   
        }
        
        return sb.toString();
    }
    */
    
    public static void main(String[] args) {
		System.out.println(reverseString("hello"));
	}
}
