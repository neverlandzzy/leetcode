import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Write a function to generate the generalized abbreviations of a word.
	 * 
	 * Example:
	 * 
	 * Given word = "word", return the following list (order does not matter):
	 * 
	 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", 
	 * 	"1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
	 */
	
    public static List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<String>();
        char[] chars = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        
        helper(result, chars, sb, 0, 0);
        
        
        return result;
    }
    
    private static void helper(List<String> result, char[] chars, StringBuilder sb, int counter, int num) {
    	int len = sb.length();
    	
    	if (counter == chars.length) {
    		if (num != 0) {
    			sb.append(num);
    		}
    		result.add(sb.toString());
    		sb.setLength(len);
    		return;
    	} 
    	
    	helper(result, chars, sb, counter + 1, num + 1); 
    	if (num != 0) {
    		sb.append(num);
    	}
    	sb.append(chars[counter]);
    	helper(result, chars, sb, counter + 1, 0);    	
    	sb.setLength(len);
    }
    
    
    public static void main(String[] args) {
		System.out.println(generateAbbreviations("word"));
	}

}
