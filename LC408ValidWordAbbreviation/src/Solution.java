
public class Solution {
	/*
	 * Given a non-empty string s and an abbreviation abbr, return whether the string matches with the given abbreviation.
	 * 
	 * A string such as "word" contains only the following valid abbreviations:
	 * 
	 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
	 * Notice that only the above abbreviations are valid abbreviations of the string "word". Any other string is not a valid abbreviation of "word".
	 * 
	 * Note:
	 * Assume s contains only lowercase letters and abbr contains only lowercase letters and digits.
	 * 
	 * Example 1:
	 * Given s = "internationalization", abbr = "i12iz4n":
	 * 
	 * Return true.
	 * 
	 * Example 2:
	 * Given s = "apple", abbr = "a2e":
	 * 
	 * Return false.
	 */
	
    public static boolean validWordAbbreviation(String word, String abbr) {
    	
    	int i = 0;
    	int j = 0;
    	
    	while (i < word.length() && j < abbr.length()) {
    		if (Character.isDigit(abbr.charAt(j))) {    			
    			int start = j;
    			while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
    				j++;
    			}
    			if (abbr.charAt(start) == '0') {
    				return false;
    			}
    			i += Integer.valueOf(abbr.substring(start, j));
    		} else {
    			if (word.charAt(i) != abbr.charAt(j)) {
    				return false;
    			}
    			i++;
    			j++;
    		}
    	}
    	
    	return (i == word.length() && j == abbr.length());
    	
    }
    
    public static void main(String[] args) {
		String s1 = "internationalization";
		String abbr1 = "i5a10io1";
		
		String s2 = "a";
		String abbr2 = "01";
		
		System.out.println(validWordAbbreviation(s1, abbr1));
		System.out.println(validWordAbbreviation(s2, abbr2));
	}
}
