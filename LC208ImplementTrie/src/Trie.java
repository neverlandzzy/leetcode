// Solution 1: HashMap

/*
 * class TrieNode {
 *		
 *	HashMap<Character, TrieNode> children;
 *	boolean isLeaf;
 *    
 *	public TrieNode() {
 *		children = new HashMap<Character, TrieNode>();
 *		isLeaf = false;
 *    }
 * }
 */

public class Trie {
	
	/*
	 * Implement a trie with insert, search, and startsWith methods.
	 */
	
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
        	Character c = word.charAt(i);
        	
        	if (!cur.children.containsKey(c)) {
        		cur.children.put(c, new TrieNode());
        	}
        	
        	cur = cur.children.get(c);
        }
        
        cur.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode cur = root;
        
        for(int i = 0; i < word.length(); i++) {
        	Character c = word.charAt(i);
        	
        	if (!cur.children.containsKey(c)) {
        		return false;
        	} 
        	cur = cur.children.get(c);
        }
        
        return cur.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        
        for(int i = 0; i < prefix.length(); i++) {
        	Character c = prefix.charAt(i);
        	
        	if (!cur.children.containsKey(c)) {
        		return false;
        	} 
        	cur = cur.children.get(c);
        }
        
        return true;
    }
}
