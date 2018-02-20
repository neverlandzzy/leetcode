import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
	 * 
	 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
	 * 
	 * For example,
	 * X X X X
	 * X O O X
	 * X X O X
	 * X O X X
	 * 
	 * After running your function, the board should be:
	 * 
	 * X X X X
	 * X X X X
	 * X X X X
	 * X O X X
	 */
	
	// Solution 1: BFS
	/*
    public static void solve(char[][] board) {
        int m = board.length; 
        
        if (m == 0)
        	return;
        
        int n = board[0].length;
        
        if (n == 0)
        	return;
        
        for(int i = 0; i < m; i++) {
        	bfs(board, i, 0);
        	bfs(board, i, n - 1);
        }
        
        for(int i = 1; i < n - 1; i++) {
        	bfs(board, 0, i);
        	bfs(board, m - 1, i);
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (board[i][j] == 'O') board[i][j] = 'X';
        		if (board[i][j] == '#') board[i][j] = 'O';
        	}
        }
    }
    
    private static void bfs(char[][] board, int i, int j) {
    	Queue<Integer> queue = new LinkedList<Integer>();
    	
    	int n = board[0].length;
    	
    	visited(board, i, j, queue);
    	
    	while (!queue.isEmpty()) {
    		int position = queue.poll();
    		int x = position / n;
    		int y = position % n;
    		
    		visited(board, x - 1, y, queue);
    		visited(board, x + 1, y, queue);
    		visited(board, x, y - 1, queue);
    		visited(board, x, y + 1, queue);
    	}
    }
    
    private static void visited(char[][] board, int i, int j, Queue<Integer> queue) {
    	int m = board.length;
    	int n = board[0].length;
    	
    	if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != 'O') {
    		return;
    	}
    	
    	board[i][j] = '#';
    	queue.offer(i * n + j);
    }
    
    */
    // Solution 2: DFS
	
	public static void solve(char[][] board) {
        int m = board.length; 
        
        if (m == 0)
        	return;
        
        int n = board[0].length;
        
        if (n == 0)
        	return;
        
        for(int i = 0; i < m; i++) {
        	dfs(board, i, 0);
        	dfs(board, i, n - 1);
        }
        
        for(int i = 1; i < n - 1; i++) {
        	dfs(board, 0, i);
        	dfs(board, m - 1, i);
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (board[i][j] == 'O') board[i][j] = 'X';
        		if (board[i][j] == '#') board[i][j] = 'O';
        	}
        }
	}
	   
	private static void dfs(char[][] board, int i, int j) {
		if (board[i][j] == 'X') {
			return;
		}
		
		if (board[i][j] == 'O') {
			int m = board.length;
			int n = board[0].length;
			
			board[i][j] = '#';
			
			if (i - 1 >= 0) dfs(board, i - 1, j);
			if (i + 1 < m) dfs(board, i + 1, j);
			if (j - 1 >= 0) dfs(board, i, j - 1);
			if (j + 1 < n) dfs(board, i, j + 1);
		}
	}
	   
    public static void main(String[] args) {
		char[][] test = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, 
						{'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
		
		for (char[] array: test) {
			for (char c: array) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
		
		solve(test);
		
		System.out.println("======done=====");
		for (char[] array: test) {
			for (char c: array) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}
}
