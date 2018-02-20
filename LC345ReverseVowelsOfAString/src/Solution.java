import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Write a function that takes a string as input and reverse only the vowels of a string.
	 * 
	 * Example 1:
	 * Given s = "hello", return "holle".
	 * 
	 * Example 2:
	 * Given s = "leetcode", return "leotcede".
	 * 
	 * Note:
	 * The vowels does not include the letter "y".
	 */
	
    public static String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        Set<Character> set = new HashSet<>();
        set.addAll(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        
        int i = 0;
        int j = s.length() - 1;
        char[] charArray = s.toCharArray();
        
        while (i < j) {
            while (i < j && !set.contains(charArray[i])) {
                i++;
            }
            
            while (i < j && !set.contains(charArray[j])) {
                j--;
            }
            
            if (i < j) {
                char tmp = charArray[i];
                charArray[i] = charArray[j];
                charArray[j] = tmp;
                i++;
                j--;
            }
        } 
        
        return new String(charArray);
    }
    
    public static void main(String[] args) {
		System.out.println(reverseVowels("hello"));
		System.out.println(reverseVowels("leetcode"));
	}
}
