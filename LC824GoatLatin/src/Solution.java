import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * A sentence S is given, composed of words separated by spaces. Each word consists of lowercase and uppercase letters only.
	 * 
	 * We would like to convert the sentence to "Goat Latin" (a made-up language similar to Pig Latin.)
	 * 
	 * The rules of Goat Latin are as follows:
	 * 
	 * If a word begins with a vowel (a, e, i, o, or u), append "ma" to the end of the word.
	 * For example, the word 'apple' becomes 'applema'.
	 *  
	 * If a word begins with a consonant (i.e. not a vowel), remove the first letter and append it to the end, then add "ma".
	 * For example, the word "goat" becomes "oatgma".
	 *  
	 * Add one letter 'a' to the end of each word per its word index in the sentence, starting with 1.
	 * For example, the first word gets "a" added to the end, the second word gets "aa" added to the end and so on.
	 * Return the final sentence representing the conversion from S to Goat Latin. 
 	 * 
	 * Example 1:
	 * Input: "I speak Goat Latin"
	 * Output: "Imaa peaksmaaa oatGmaaaa atinLmaaaaa"
	 * 
	 * Example 2:
	 * Input: "The quick brown fox jumped over the lazy dog"
	 * Output: "heTmaa uickqmaaa rownbmaaaa oxfmaaaaa umpedjmaaaaaa overmaaaaaaa hetmaaaaaaaa azylmaaaaaaaaa ogdmaaaaaaaaaa"
	 * 
	 * Notes:
	 * S contains only uppercase, lowercase and spaces. Exactly one space between each word.
	 * 1 <= S.length <= 100.
	 */
	
    public static String toGoatLatin(String S) {
    	if (S == null || S.length() == 0) {
    		return S;
    	}
    	
    	Set<Character> vowel = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        
        String result = " ";
        String[] array = S.split(" ");
        
        for (int i = 0; i < array.length; i++) {
        	StringBuilder sb = new StringBuilder(array[i]);
        	if (!vowel.contains(Character.toLowerCase(sb.charAt(0)))) {
        		char c = sb.charAt(0);
        		sb.deleteCharAt(0);
        		sb.append(c);
        	} 
        	
        	sb.append("ma");
        	for (int k = 0; k <= i; k++) {
        		sb.append("a");
        	}
        	
        	result += sb.toString();
        	
        	result += " ";
        	
        }
        
        return result.trim();
    }
	
	public static void main(String[] args) {
		String test1 = "I speak Goat Latin";
		
		String test2 = "The quick brown fox jumped over the lazy dog";
		
		String test3 = "";
		System.out.println(toGoatLatin(test1));
		System.out.println(toGoatLatin(test2));
		System.out.println(toGoatLatin(test3));
	}
}
