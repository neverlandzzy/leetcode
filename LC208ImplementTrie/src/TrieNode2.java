// Solution 2: Array

public class TrieNode2 {
    public TrieNode2[] children;
    public boolean isLeaf;
    
    public TrieNode2() {
        children = new TrieNode2[26];
        isLeaf = false;
    }
}
