
public class Solution {
	/*
	 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
	 * 
	 * For example,
	 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	 * 
	 * Given word1 = “coding”, word2 = “practice”, return 3.
	 * Given word1 = "makes", word2 = "coding", return 1.
	 * 
	 * Note:
	 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
	 */
	
	// O(n)
    public static int shortestDistance(String[] words, String word1, String word2) {
        Integer index1 = null;
        Integer index2 = null;
        int result = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
        	if (words[i].equals(word1) || words[i].equals(word2)) {
	        	if (words[i].equals(word1)) {
	        		index1 = i;
	        	}
	        	
	        	if (words[i].equals(word2)) {
	        		index2 = i;
	        	}
	        	
	        	if (index1 != null && index2 != null) {
	        		result = Math.min(result, Math.abs(index1 - index2));
	        	}
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String[] test1 = {"practice", "makes", "perfect", "coding", "makes"};
		
		System.out.println(shortestDistance(test1, "coding", "practice"));
		System.out.println(shortestDistance(test1, "makes", "coding"));
	}
}
