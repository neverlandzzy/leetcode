
public class Solution {
	/**
	 * Given a 2D board and a word, find if the word exists in the grid.
	 * 
	 * The word can be constructed from letters of sequentially adjacent cell, 
	 * where "adjacent" cells are those horizontally or vertically neighboring. 
	 * The same letter cell may not be used more than once.
	 * 
	 * For example,
	 * Given board =
	 * 	[
	 *   ['A','B','C','E'],
	 *   ['S','F','C','S'],
	 *   ['A','D','E','E']
	 *  ]
	 *  
	 *  word = "ABCCED", -> returns true,
	 *  word = "SEE", -> returns true,
	 *  word = "ABCB", -> returns false.
	 */
	
	// Time: O(4^k * m * n) -- need confirm
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (dfs(board, visited, word, 0, i, j)) {
        			return true;
        		}
        	}
        }
        
        return false;
    }
    
    private static boolean dfs(char[][] board, boolean[][] visited, String word, int index, int i, int j) {
    	if (index == word.length()) {
    		return true;
    	}
    	
    	if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) {
    		return false;
    	}
    	
    	if (visited[i][j]) {
    		return false;
    	}
    	
    	if (board[i][j] != word.charAt(index)) {
    		return false;
    	}
    	
    	visited[i][j] = true;
    	boolean match =  dfs(board, visited, word, index + 1, i - 1, j) 
    				  || dfs(board, visited, word, index + 1, i + 1, j)
    			      || dfs(board, visited, word, index + 1, i, j - 1) 
    			      || dfs(board, visited, word, index + 1, i, j + 1) ;
    	
    	visited[i][j] = false;
    	
    	return match;
    	
    }
    
    public static void main(String[] args) {
		char[][] board = {{'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'}};
		
		System.out.println(exist(board, "ABCCED"));
		System.out.println(exist(board, "SEE"));
		System.out.println(exist(board, "ABCB"));
	}
    
}
