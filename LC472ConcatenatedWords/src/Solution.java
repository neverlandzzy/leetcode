import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
	 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
	 * 
	 * Example:
	 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
	 * 
	 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
	 * 
	 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
	 *  "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
	 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
	 * 
	 * Note:
	 * The number of elements of the given array will not exceed 10,000
	 * The length sum of elements in the given array will not exceed 600,000.
	 * All the input string will only include lower case letters.
	 * The returned elements order does not matter.
	 */
	
	public static class TrieNode {
		public TrieNode[] children;
		public String word;
		
		public TrieNode() {
			children = new TrieNode[26];
		}
	}
	
	private static void insertWord(String word, TrieNode root) {
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			if (root.children[c - 'a'] == null) {
				root.children[c - 'a'] = new TrieNode();
			}
			root = root.children[c - 'a'];
		}
		root.word = word;
	}
	
	private static boolean isConcatenated(TrieNode root, String word, int pos) {
		TrieNode node = root;
		
		for (int i = pos; i < word.length(); i++) {
			char c = word.charAt(i);
			if (node.children[c - 'a'] == null) {
				return false;
			}
			node = node.children[c - 'a'];
			
			if (node.word != null && !node.word.equals(word)) {
				if (isConcatenated(root, word, i + 1)) {
					return true;
				}
			}
		}
		
		// 当pos != 0时，说明当前这个词可能是concatenated，否则这个词全部都在字典里，并不是concatenated
		return pos != 0 && node.word != null;
	
	}
	
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        
        TrieNode root = new TrieNode();
        
        for (String word: words) {
        	insertWord(word, root);
        }
        
        for (String word: words) {
        	if (isConcatenated(root, word, 0)) {
        		result.add(word);
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String[] test = {"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"};
		
		System.out.println(findAllConcatenatedWordsInADict(test));
	}
}
