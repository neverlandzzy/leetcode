
public class Solution {
	/*
	 * Given two strings s and t which consist of only lowercase letters.
	 * 
	 * String t is generated by random shuffling string s and then add one more letter at a random position.
	 * 
	 * Find the letter that was added in t.
	 * 
	 * Example:
	 * 
	 * Input:
	 * s = "abcd"
	 * t = "abcde"
	 * 
	 * Output:
	 * e
	 * 
	 * Explanation:
	 * 'e' is the letter that was added.
	 */
	
    public static char findTheDifference(String s, String t) {
        
        int[] map = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            map[s.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < t.length(); i++) {
            map[t.charAt(i) - 'a']--;
            if (map[t.charAt(i) - 'a'] < 0) {
                return t.charAt(i);
            }        
        }
        
        return ' ';
    }
    
    public static void main(String[] args) {
		System.out.println(findTheDifference("abcd", "abcde"));
		System.out.println(findTheDifference("abc", "acbe"));
		System.out.println(findTheDifference("", "e"));
	}
}
