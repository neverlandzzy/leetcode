import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/*
	 * All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, 
	 * for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify 
	 * repeated sequences within the DNA.
	 * 
	 * Write a function to find all the 10-letter-long sequences (substrings) that occur 
	 * more than once in a DNA molecule.
	 * 
	 * For example,
	 * Given s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT",
	 * Return:
	 * ["AAAAACCCCC", "CCCCCAAAAA"].
	 */
	
    public static List<String> findRepeatedDnaSequences(String s) {
    	
    	// Solution 1: O(n^2):  substring() takes O(n) time in Java 7 or later
    	/*
        int k = 10;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        
        List<String> result = new ArrayList<String>();
        
        for (int i = 0; i <= s.length() - k; i++) {
        	if (!map.containsKey(s.substring(i, i + k))) {
        		map.put(s.substring(i, i + k), 1);
        	} else {
        		map.put(s.substring(i, i + k), map.get(s.substring(i, i + k)) + 1);
        		
        		if (map.get(s.substring(i, i + k)) == 2) {
        			result.add(s.substring(i, i + k));
        		}
        	}
        }
        
        return result;
        */
    	
    	// Solution 2:
    	List<String> result = new ArrayList<String>();
    	Set<Integer> firstVisited = new HashSet<Integer>();
    	Set<Integer> secondVisited = new HashSet<Integer>();
    	
    	int k = 10;
    	int v = 0;
    
    	char[] map = new char[26];
    	map['A' - 'A'] = 0;
    	map['C' - 'A'] = 1;
    	map['G' - 'A'] = 2;
    	map['T' - 'A'] = 3;
    	
    	for (int i = 0; i < s.length(); i++) {
    		v = v << 2; 
    		v = v | map[s.charAt(i) - 'A'];
    		if (i == k - 1) {
    			firstVisited.add(v);
    		} else if (i > k - 1){
    			v &= ~(3 << 2*k);
    			if (!firstVisited.add(v) && secondVisited.add(v)) {
    				result.add(s.substring(i - k + 1, i + 1));
    			}
    		}
    		  		
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		String test = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
		String test2 = "AAAAAAAAAAAA";
		
		System.out.println(findRepeatedDnaSequences(test));
		//System.out.println(findRepeatedDnaSequences(test2));
	}
}
