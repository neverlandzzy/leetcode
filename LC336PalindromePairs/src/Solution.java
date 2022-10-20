import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/**
	 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, 
	 * i.e. words[i] + words[j] is a palindrome.
	 *
	 * Example 1:
	 * Given words = ["bat", "tab", "cat"]
	 *  Return [[0, 1], [1, 0]]
	 * The palindromes are ["battab", "tabbat"]
	 *
	 * Example 2:
	 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
	 *  Return [[0, 1], [1, 0], [3, 2], [2, 4]]
	 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
	 */
	
	// https://leetcode.com/problems/palindrome-pairs/discuss/79195/onk2-java-solution-with-trie-structure
	// http://www.allenlipeng47.com/blog/index.php/2016/03/15/palindrome-pairs/
	// Both building and searching the Trie structure take O(n*k^2)
	public static class TrieNode {
		TrieNode[] children;
		// index: the index of the word in words array
		int index;
		// list: indices of words 同时满足下面两个条件:
		//       (1) 当前的TrieNode表示word的后缀；
		//       (2) word的其余部分（除去后缀以外）是palindrome -- 
		//           对于["a", "aaa"]的例子，当检查到"a"时，"a"与"aaa"的第一个后缀可以组成palindrome，并且"aaa"除去"a"的部分，"aa"，也是palindrome，因此
		//           "a"与"aaa"可以组成palindrome
		//                                  
		List<Integer> list;
		
		public TrieNode() {
			children = new TrieNode[26];
			index = -1;
			list = new ArrayList<>();
		}
	}
	
    public static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        if (words == null || words.length == 0) {
        	return result;
        }
        
        TrieNode root = new TrieNode();
        
        for (int i = 0; i < words.length; i++) {
        	addWord(root, words[i], i);
        }
        
        for (int i = 0; i < words.length; i++) {
        	search(root, result, words[i], i);
        }
        
        return result;
    }
    
    private static void addWord(TrieNode root, String word, int index) {
    	
    	TrieNode cur = root;
    	
    	for (int i = word.length() - 1; i >= 0; i--) {
    		char c = word.charAt(i);
    		if (cur.children[c - 'a'] == null) {
    			cur.children[c - 'a'] = new TrieNode();
    		}
    		if (isPalindrome(word, 0, i)) {

    			cur.list.add(index);
    		}
    		cur = cur.children[c - 'a'];
    	}
    	
    	cur.index = index;
    	cur.list.add(index);
    }
    
    /*
    private static void search(TrieNode root, List<List<Integer>> result, String[] words, int i) {
    	for (int j = 0; j < words[i].length(); j++) {
    		// 对于当前检查的单词words[i]，若从j 到结尾的substring是palindrome，则当前单词和当前节点代表的单词可以组成一个答案。
    		// 因为当前节点是倒序插入的单词，说明对于words[j]从0到j-1的子串，Trie上有刚好和其字符顺序相反的单词，拼起来可以构成palindrome
    		// 拼的时候注意顺序，应该是当前检查的单词在前，当前node代表的单词在后
    		// e.g [sll, s]
    		if (root.index >= 0 && i != root.index && isPalindrome(words[i], j, words[i].length() - 1)) {
    			result.add(Arrays.asList(i, root.index));
    			//System.out.println("i = " + i + " index = " + root.index);
    		}
    		
    		root = root.children[words[i].charAt(j) - 'a'];
    		if (root == null) {
    			return;
    		}
    	}
    	
    	for (int j: root.list) {
    		// e.g. [lls, s]
    		if (i == j) {
    			continue;
    		}
    		//System.out.println("i = " + i + " j = " + j + " root.index = " +  root.index + " root.list: " + root.list);
    		result.add(Arrays.asList(i, j));
    	}
    }
    */
    private static void search(TrieNode root, List<List<Integer>> result, String word, int index) {
        TrieNode node = root;
		// 对于当前检查的单词words[i]，若从j 到结尾的substring是palindrome，则当前单词和当前节点代表的单词可以组成一个答案。
		// 因为当前节点是倒序插入的单词，说明对于words[j]从0到j-1的子串，Trie上有刚好和其字符顺序相反的单词，拼起来可以构成palindrome
		// 拼的时候注意顺序，应该是当前检查的单词在前，当前node代表的单词在后
		// e.g [sll, s]
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (node.index >= 0 && index != node.index && isPalindrome(word, i, word.length() - 1)) {
                result.add(Arrays.asList(index, node.index));
            }
            
            node = node.children[c - 'a'];
            if (node == null) {
                return;
            }
        }

        for (int i: node.list) {
        	// e.g. [lls, s]
            if (index != i) {
                result.add(Arrays.asList(index, i));
            }
        }
    }
    
    private static boolean isPalindrome(String word, int i, int j) {
    	while (i < j) {
    		if (word.charAt(i) != word.charAt(j)) {
    			return false;
    		}
    		i++;
    		j--;
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
		String[] test1 = {"bat", "tab", "cat"};
		String[] test2 = {"abcd", "dcba", "lls", "s", "sssll"};
		String[] test3 = {"a", "aaa", "aa"};
		String[] test4 = {"lls", "s",};
		System.out.println(palindromePairs(test1));
		System.out.println(palindromePairs(test2));
		System.out.println(palindromePairs(test3));
		System.out.println(palindromePairs(test4));
	}
}
