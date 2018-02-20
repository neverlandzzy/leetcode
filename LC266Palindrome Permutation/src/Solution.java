import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Given a string, determine if a permutation of the string could form a palindrome.
	 * 
	 * For example,
	 * "code" -> False, "aab" -> True, "carerac" -> True.
	 * 
	 * Hint:
	 * 
	 * Consider the palindromes of odd vs even length. What difference do you notice?
	 * Count the frequency of each character.
	 * If each character occurs even number of times, then it must be a palindrome. 
	 * How about character which occurs odd number of times?
	 */
	
    public static boolean canPermutePalindrome(String s) {
    	//Solution 1:
    	/*
    	HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		if (charMap.containsKey(s.charAt(i))) {
    			charMap.put(s.charAt(i), charMap.get(s.charAt(i)) + 1);
    		} else {
    			charMap.put(s.charAt(i), 1);
    		}
    	}
    	
    	int counter = 0;
    	
    	for (int i: charMap.values()) {
    		if (i % 2 != 0) {
    			counter++;
    		}
    		
    		if (counter > 1) {
    			return false;
    		}
    	}
    	
    	return true;
    	*/
    	
    	//Solution 2:
    	Set<Character> set = new HashSet<Character>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		if (set.contains(s.charAt(i))) {
    			set.remove(s.charAt(i));
    		} else {
    			set.add(s.charAt(i));
    		}
    	}
    	
    	return set.size() == 0 || set.size() == 1;
    	
    	// Solution 3:
    	/*
        public boolean canPermutePalindrome(String s) {
            int[] map = new int[256];
            int counter = 0;
            
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (map[c] != 0) {
                    map[c]--;
                } else {
                    map[c]++;
                }
            }
            
            for (int i = 0; i < 256; i++) {
                if (map[i] != 0) {
                    counter++;
                }
            }
            
            return counter <= 1;
        }
        */
    }
    
    public static void main(String[] args) {
		String test1 = "aab";
		String test2 = "code";
		String test3 = "carerac";
		
		System.out.println(canPermutePalindrome(test1));
		System.out.println(canPermutePalindrome(test2));
		System.out.println(canPermutePalindrome(test3));
	}
}
