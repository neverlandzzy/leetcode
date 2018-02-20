// Solution 2: Array

public class Trie2 {
    private TrieNode2 root;

    public Trie2() {
        root = new TrieNode2();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode2 cur = root;
        
        for (int i = 0; i < word.length(); i++) {
        	char c = word.charAt(i);
        	if (cur.children[c - 'a'] == null) {
        		cur.children[c - 'a'] = new TrieNode2();
        	}
        	
        	cur = cur.children[c - 'a'];
        }
        
        cur.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
       TrieNode2 cur = root; 
       
       for (int i = 0; i < word.length(); i++) {
    	   char c = word.charAt(i);
    	   
    	   if (cur.children[c - 'a'] == null) {
    		   return false;
    	   }
    	   
    	   cur = cur.children[c - 'a'];
       }
       
       return cur.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
    	TrieNode2 cur = root; 
    	
        for (int i = 0; i < prefix.length(); i++) {
     	   char c = prefix.charAt(i);
     	   
     	   if (cur.children[c - 'a'] == null) {
     		   return false;
     	   }
     	   
     	   cur = cur.children[c - 'a'];
        }
        
        return true;
    }
}
