import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/**
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
    	
    	// Solution 1: O(n * L) (L = 10):  substring() takes O(n) time in Java 7 or later
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
    	
    	// Solution 2: Time: O(n)
		// Map: A->00  C->01  G->10  T->11
    	List<String> result = new ArrayList<>();
    	Set<Integer> firstVisited = new HashSet<>();
    	Set<Integer> secondVisited = new HashSet<>();
    	
    	int l = 10;
    	int bitmask = 0;
    
    	char[] map = new char[26];
    	map['A' - 'A'] = 0;
    	map['C' - 'A'] = 1;
    	map['G' - 'A'] = 2;
    	map['T' - 'A'] = 3;
    	
    	for (int i = 0; i < s.length(); i++) {
			bitmask = bitmask << 2;
			bitmask = bitmask | map[s.charAt(i) - 'A'];
    		if (i == l - 1) {
    			firstVisited.add(bitmask);
    		} else if (i > l - 1){
    			// set first 2 bits to 0
				bitmask &= ~(3 << 2 * l);
    			if (!firstVisited.add(bitmask) && secondVisited.add(bitmask)) {
    				result.add(s.substring(i - l + 1, i + 1));
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
