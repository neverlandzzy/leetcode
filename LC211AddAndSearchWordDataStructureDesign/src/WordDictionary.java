import java.util.HashMap;
import java.util.Map;


public class WordDictionary {
	
	/*
	 * Design a data structure that supports the following two operations:
	 * 
	 * void addWord(word)
	 * bool search(word)
	 * search(word) can search a literal word or a regular expression string 
	 * containing only letters a-z or .. A . means it can represent any one letter.
	 * 
	 * For example:
	 * 
	 * addWord("bad")
	 * addWord("dad")
	 * addWord("mad")
	 * search("pad") -> false
	 * search("bad") -> true
	 * search(".ad") -> true
	 * search("b..") -> true
	 * 
	 * Note:
	 * You may assume that all words are consist of lowercase letters a-z. 
	 */
	
	// Solution 1: using array to implement Trie -- faster

    public class TrieNode {
        TrieNode[] children;
        String word;
        
        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }

    TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = new TrieNode();
            }
            cur = cur.children[c - 'a'];
        }
        
        cur.word = word;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return helper(word.toCharArray(), 0, root);
    }
    
    private boolean helper(char[] word, int pos, TrieNode node) {
        if (word.length == pos) {
            return node.word != null;
        }
        
        if (word[pos] != '.') {
            return node.children[word[pos] - 'a'] != null && 
                    helper(word, pos + 1, node.children[word[pos] - 'a']);
        } else {
            for (int i = 0; i < node.children.length; i++) {
                if (node.children[i] != null) {
                    if (helper(word, pos + 1, node.children[i])) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
	
	// Solution 2: using HashMap to implement Trie -- slow
	/*
    private class TrieNode {
        
        HashMap<Character, TrieNode> children;
        boolean isLeaf;
        
        public TrieNode() {
            children = new HashMap<>();
            isLeaf = false;
        }    
    }
    
    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }
    

    public void addWord(String word) {
        TrieNode cur = root;
        
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }
        
        cur.isLeaf = true;
    }
    
    
    public boolean search(String word) {
        return searchHelper(word.toCharArray(), 0, root);
    }
    
    private boolean searchHelper(char[] words, int index, TrieNode node) {
        if (index == words.length) {
            return node.isLeaf;
        }
        
        if (words[index] != '.') {
            return (node.children.containsKey(words[index]) && searchHelper(words, index + 1, node.children.get(words[index])));
        } else {
            for (Map.Entry<Character, TrieNode> entry: node.children.entrySet()) {
                if (searchHelper(words, index + 1, entry.getValue())) {
                    return true;
                }                
            }
        }
        
        return false;
    }
    */
}
