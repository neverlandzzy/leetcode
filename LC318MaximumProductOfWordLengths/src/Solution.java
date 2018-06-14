
public class Solution {
	
	/* 
	 * Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. 
	 * You may assume that each word will contain only lower case letters. If no such two words exist, return 0.
	 * 
	 * Example 1:
	 * 
	 * Input: ["abcw","baz","foo","bar","xtfn","abcdef"]
	 * Output: 16 
	 * Explanation: The two words can be "abcw", "xtfn".
	 * 
	 * Example 2:
	 * 
	 * Input: ["a","ab","abc","d","cd","bcd","abcd"]
	 * Output: 4 
	 * Explanation: The two words can be "ab", "cd".
	 * 
	 * Example 3:
	 * 
	 * Input: ["a","aa","aaa","aaaa"]
	 * Output: 0 
	 * Explanation: No such pair of words.
	 * 
	 */
	
    public static int maxProduct(String[] words) {
    	if (words == null || words.length == 0) {
    		return 0;
    	}
    	int n = words.length;
    	int[] mask = new int[n];
    	
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < words[i].length(); j++) {
    			char c = words[i].charAt(j);
    			mask[i] |= 1 << (c - 'a');
    		}
    	}
    	
        int max = 0;
        
        for (int i = 0; i < n; i++) {
        	for (int j = i + 1; j < n; j++) {
        		if ((mask[i] & mask[j]) == 0) {
        			max = Math.max(max, words[i].length() * words[j].length());
        		}
        	}
        }
        
        return max;
    }
    
	public static void main(String[] args) {
		String[] test1 = {"abcw","baz","foo","bar","xtfn","abcdef"};
		String[] test2 = {"a","ab","abc","d","cd","bcd","abcd"};
		String[] test3 = {"a","aa","aaa","aaaa"};
		
		System.out.println(maxProduct(test1));
		System.out.println(maxProduct(test2));
		System.out.println(maxProduct(test3));
	}
}
