import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/*
	 * Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word 
	 * following rules below.
	 * 
	 * Begin with the first character and then the number of characters abbreviated, which followed by the last character.
	 * If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of 
	 * only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation 
	 * cannot map to more than one original words.
	 * 
	 * If the abbreviation doesn't make the word shorter, then keep it as original.
	 * 
	 * Example:
	 * Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
	 * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
	 * 
	 * Note:
	 * 1. Both n and the length of each word will not exceed 400.
	 * 2. The length of each word is greater than 1.
	 * 3. The words consist of lowercase English letters only.
	 * 4. The return answers should be in the same order as the original array.
	 */
	
    public static List<String> wordsAbbreviation(List<String> dict) {
        int size = dict.size();
        String[] result = new String[size];
        int[] prefix = new int[size];
        for (int i = 0; i < size; i++) {
        	prefix[i] = 1;
        	result[i] = (abbreviation(dict.get(i), 1));
        }
        
        for (int i = 0; i < size; i++) {
        	
        	while (true) {
	        	Set<Integer> set = new HashSet<>();
	        	for (int j = i + 1; j < size; j++) {
	        		if (result[i].equals(result[j])) {
	        			set.add(j);
	        		}
	        	}
	        	
	        	if (set.isEmpty()) {
	        		break;
	        	}
	        	
	        	set.add(i);
	        	for (int k: set) {
	        		result[k] = abbreviation(dict.get(k), ++prefix[k]);
	        	}
        	}
        }
        
        return Arrays.asList(result);
        
    }
    
    private static String abbreviation(String s, int k) {
    	if (k >= s.length() - 2) {
    		return s;
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(s.substring(0, k));
    	sb.append(s.length() - k - 1);
    	sb.append(s.charAt(s.length() - 1));
    	
    	return sb.toString();
    	
    }
    
    public static void main(String[] args) {
		List<String> test = new ArrayList<>(Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"));
		System.out.println(wordsAbbreviation(test));
	}
}
