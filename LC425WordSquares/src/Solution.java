import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	
	/**
	 * Given a set of words (without duplicates), find all word squares you can build from them.
	 * 
	 * A sequence of words forms a valid word square if the kth row and column read the exact same string, 
	 * where 0 ≤ k < max(numRows, numColumns).
	 * 
	 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads 
	 * the same both horizontally and vertically.
	 * 
	 * b a l l
	 * a r e a
	 * l e a d
	 * l a d y
	 * 
	 * Note:
	 * 1. There are at least 1 and at most 1000 words.
	 * 2. All words will have the exact same length.
	 * 3. Word length is at least 1 and at most 5.
	 * 4. Each word contains only lowercase English alphabet a-z.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * ["area","lead","wall","lady","ball"]
	 * 
	 * Output:
	 * [
	 * 	[ "wall",
	 * 	  "area",
	 *    "lead",
	 *    "lady"
	 *  ],
	 *  [ "ball",
	 *    "area",
	 *    "lead",
	 *    "lady"
	 *  ]
	 * ]
	 * 
	 * Explanation:
	 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * ["abat","baba","atan","atal"]
	 * 
	 * Output:
	 * [
	 *  [ "baba",
	 *    "abat",
	 *    "baba",
	 *    "atan"
	 *  ],
	 *  [ "baba",
	 *    "abat",
	 *    "baba",
	 *    "atal"
	 *  ]
	 * ]
	 * 
	 * Explanation:
	 * The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
	 */
	
	// Solution 1: common and easy understand
	// https://discuss.leetcode.com/topic/63516/explained-my-java-solution-using-trie-126ms-16-16/2
    static class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        public TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }
    
    static class Trie {
    	TrieNode root;
    	
    	public Trie(String[] words) {
    		root = new TrieNode();
    		for (String w: words) {
    			TrieNode cur = root;
    			for (int i = 0; i < w.length(); i++) {
    				char c = w.charAt(i);
    				if (cur.children[c - 'a'] == null) {
    					cur.children[c - 'a'] = new TrieNode();
    				}
    				cur = cur.children[c - 'a'];
    				cur.startWith.add(w);
    			}
    		}
    	}
    	
    	public List<String> findByPrefix(String prefix) {
    		List<String> result = new ArrayList<>();
    		TrieNode cur = root;
    		
    		for (int i = 0; i < prefix.length(); i++) {
    			char c = prefix.charAt(i);
    			if (cur.children[c - 'a'] == null) {
    				return result;
    			}
    			cur = cur.children[c - 'a'];
    		}
    		result.addAll(cur.startWith);
    		return result;
    	}
    }
	
    public static List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        
        if (words == null || words.length == 0) {
        	return result;
        }
        int len = words[0].length();
        Trie root = new Trie(words);
        
        for (String w: words) {
        	list.add(w);
        	helper(len, root, result, list);
        	list.remove(list.size() - 1);
        }
        
        return result;
    }
    
    private static void helper(int len, Trie root, List<List<String>> result, List<String> list) {
    	if (list.size() == len) {
    		result.add(new ArrayList<>(list));
    		return;
    	}
    	
    	int index = list.size();
    	StringBuilder sb = new StringBuilder();
    	for (String s: list) {
    		sb.append(s.charAt(index));
    	}
    	//System.out.println(sb.toString() + "---" + list);
    	List<String> startWith = root.findByPrefix(sb.toString());
    	for (String s: startWith) {
    		list.add(s);
    		helper(len, root, result, list);
    		list.remove(list.size() - 1);
    	}
    }
	// Solution 2: faster but complicated
	//https://discuss.leetcode.com/topic/64770/java-dfs-trie-54-ms-98-so-far
	/*
	static class TrieNode {
		TrieNode[] children;
		String word;
		
		public TrieNode() {
			children = new TrieNode[26];
			word = null;
		}
	}
	
    public static List<List<String>> wordSquares(String[] words) {
        TrieNode root = new TrieNode();
        
        for (String s: words) {
        	
        	TrieNode cur = root;
        	
        	for (int i = 0; i < s.length(); i++) {
        		char c = s.charAt(i);
        		if (cur.children[c - 'a'] == null) {
        			cur.children[c - 'a'] = new TrieNode();
        		}
        		cur = cur.children[c - 'a'];
        	}
        	cur.word = s;
        }
        
        List<List<String>> result = new ArrayList<>();
        TrieNode[] rows = new TrieNode[words[0].length()];
        Arrays.fill(rows, root);

        helper(result, rows, 0, 0);
        return result;
    }
    
    private static void helper(List<List<String>> result, TrieNode[] rows, int col, int row) {
    	if (row == rows.length) {
    		  List<String> list = new ArrayList<>();
    		  for (int i = 0; i < rows.length; i++) {
    			  list.add(rows[i].word);
    		  }
    		  result.add(list);
    	} else if (col < rows.length) {
    		TrieNode currow = rows[row];
    		TrieNode curcol = rows[col];
    		
    		for (int i = 0; i < 26; i++) {
	    		if (currow.children[i] != null && curcol.children[i] != null) {
	    			rows[row] = currow.children[i];
	    			rows[col] = curcol.children[i];
	    			helper(result, rows, col + 1, row);
	    		}
    		}
    		rows[row] = currow;
    		rows[col] = curcol;
    		//}
    	} else {
    		helper(result, rows, row + 1, row + 1);
    	}
    	
    }
    */
    
    public static void main(String[] args) {
		String[] test1 = {"area","lead","wall","lady","ball"};
		String[] test2 = {"abat","baba","atan","atal"};
		
		System.out.println(wordSquares(test1));
		//System.out.println(wordSquares(test2));
	}
}
