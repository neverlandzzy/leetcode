
public class Solution {
	
	/*
	 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
	 * 
	 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
	 * 
	 * word1 and word2 may be the same and they represent two individual words in the list.
	 * 
	 * For example,
	 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	 * 
	 * Given word1 = “makes”, word2 = “coding”, return 1.
	 * Given word1 = "makes", word2 = "makes", return 3.
	 * 
	 * Note:
	 * You may assume word1 and word2 are both in the list.
	 */
	
    public static int shortestWordDistance(String[] words, String word1, String word2) {
        Integer index1 = null;
        Integer index2 = null;
        int result = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {

        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String[] test1 = {"practice", "makes", "perfect", "coding", "makes"};
		
		System.out.println(shortestWordDistance(test1, "makes", "coding"));
		System.out.println(shortestWordDistance(test1, "makes", "makes"));
	}
}
