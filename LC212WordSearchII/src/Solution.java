import java.util.ArrayList;
import java.util.List;

class TrieNode {
    public String result;
    public TrieNode[] children;
    
    public TrieNode() {
        children = new TrieNode[26];
        result = null;
    }
}

public class Solution {
	/*
	 * Given a 2D board and a list of words from the dictionary, find all words in the board.
	 * 
	 * Each word must be constructed from letters of sequentially adjacent cell, 
	 * where "adjacent" cells are those horizontally or vertically neighboring. The same 
	 * letter cell may not be used more than once in a word.
	 * 
	 * For example,
	 * Given words = ["oath","pea","eat","rain"] and board =
	 * 
	 * [
	 *   ['o','a','a','n'],
	 *   ['e','t','a','e'],
	 *   ['i','h','k','r'],
	 *   ['i','f','l','v']
	 *   
	 * ]
	 * 
	 * Return ["eat","oath"].
	 */
		
    public static List<String> findWords(char[][] board, String[] words) {
    	
        TrieNode root = new TrieNode();
    	
    	for (String word: words) {
    		TrieNode cur = root;
    		for (int i = 0; i < word.length(); i ++) {
    			char c = word.charAt(i);
    			if (cur.children[c - 'a'] == null) {
    				cur.children[c - 'a'] = new TrieNode();
    			}
    			cur = cur.children[c - 'a'];
    		}
    		
    		cur.result = word;
    	}
    	
    	List<String> result = new ArrayList<String>();
    	
        int m = board.length;
        int n = board[0].length;
        
        if (m == 0) {
        	return result;
        }
     
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		dfs(board, visited, i, j, root,result);
        	}
        }
    	
        return result;
    }
    
    private static void dfs(char[][] board, boolean[][] visited, int i, int j, TrieNode root, List<String> result) {
    	
    	if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) return;
    	
    	char c = board[i][j];
    	
    	if (root.children[c - 'a'] == null) return;

    	if (visited[i][j]) return;
        
        if (root.children[c - 'a'].result != null && !result.contains(root.children[c - 'a'].result)) {
            result.add(root.children[c - 'a'].result);
            
            //这里不能有return， 因为对于'eat', 'eate'， 遍历完eat后，要继续遍历下一位，如果此处return，会miss掉eate
            //return;
        }


        visited[i][j] = true;
        dfs(board, visited, i - 1, j, root.children[c - 'a'], result);
        dfs(board, visited, i + 1, j, root.children[c - 'a'], result);
        dfs(board, visited, i, j - 1, root.children[c - 'a'], result);
        dfs(board, visited, i, j + 1, root.children[c - 'a'], result);
        
        visited[i][j] = false;
    }
    
    public static void main(String[] args) {
		String[] words = {"oath","pea","eat","rain", "eate"};
		char[][] board = {
				{'o','a','a','n'},
				{'e','t','a','e'},
				{'i','h','k','r'},
				{'i','f','l','v'}
		};
		
		String[] words2 = {"a"};
		char[][] board2 = {{'a'}};
		System.out.println(findWords(board, words));
		System.out.println(findWords(board2, words2));
	}
}
