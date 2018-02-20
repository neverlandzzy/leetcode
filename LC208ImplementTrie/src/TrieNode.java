import java.util.HashMap;

//Solution 1: HashMap

class TrieNode {
		
	public HashMap<Character, TrieNode> children;
	public boolean isLeaf;
    
	public TrieNode() {
		children = new HashMap<Character, TrieNode>();
		isLeaf = false;
    }
}
