
public class Solution {
	
	public static boolean validTicTacToe(String[] board) {
		int n = board.length;
        char[][] grid = new char[n][n];
        
        for (int i = 0; i < n; i++) {
        	grid[i] = board[i].toCharArray();
        }
        
		int counter = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'X') {
					counter++;
				}
				
				if (grid[i][j] == 'O') {
					counter--;
				}
			}
		}
		
		if (counter > 1 || counter < 0) {
			return false;
		}
		
		if (isEnd(grid, 'X') && isEnd(grid, 'O')) {
			return false;
		}
		
		
		if (isEnd(grid, 'X') && counter != 1) {
			return false;
		}
		
		if (isEnd(grid, 'O') && counter != 0) {
			return false;
		}
		
		return true;
	}
	/*
    public static boolean validTicTacToe(String[] board) {
    	int n = board.length;
        char[][] grid = new char[n][n];
        
        for (int i = 0; i < n; i++) {
        	grid[i] = board[i].toCharArray();
        }
        
        if (isFull(grid)) {
        	return true;
        }
        
        if (isEnd(grid)) {
        	return false;
        }
        
        
        return true;
    }
    

    private static boolean dfs(char[][] grid, int i, int j, char c) {
    	grid[i][j] = c;
    	
    	if (isFull(grid)) {
    		return true;
    	}
    	
    	if (isEnd(grid)) {
    		return false;
    	}
    	
    	int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    	int n = grid.length;
    	
    	for (int[] dir: direction) {
    		int nextI = i + dir[0];
    		int nextJ = j + dir[1];
    		
    		if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < n && grid[nextI][nextJ] == ' ') {
    			if (c == 'X') {
    				if (!dfs(grid, nextI, nextJ, 'O')) {
    					return false;
    				}
    			} else {
    				if (!dfs(grid, nextI, nextJ, 'X')) {
    					return false;
    				}
    			}
    		}
    	}
    	
    	grid[i][j] = ' ';
    	return true;
    }
    */
    private static boolean isEnd(char[][] board, char c) {
    	int m = board.length;
    	int n = board[0].length;
    	
    	for (int i = 0; i < m; i++) {
    		int j = 1;
    		while (j < n) {
    			if (board[i][j] == board[i][j - 1] && board[i][j] == c) {
    				j++;
    			} else {
    				break;
    			}
    		}
    		
    		if (j == n) {
    			return true;
    		}
    	}
    	
    	for (int j = 0; j < n; j++) {
    		int i = 1;
    		while (i < m) {
    			if (board[i][j] == board[i - 1][j] && board[i][j] == c) {
    				i++;
    			} else {
    				break;
    			}
    		}
    		
    		if (i == m) {
    			return true;
    		}
    	}
    	
    	int i = 1;
    	while (i < m) {
    		if (board[i][i] == board[i - 1][i - 1] && board[i][i] == c) {
    			i++;
    		} else {
    			break;
    		}
    	}
    	
    	if (i == m) {
    		return true;
    	}
    	
    	i = 1;
    	
    	while (i < n) {
    		if (board[i][n - i - 1] == board[i - 1][n - i] && board[i][n - i - 1] == c) {
    			i++;
    		} else {
    			break;
    		}
    	}
    	if (i == n) {
    		return true;
    	}
    	
    	return false;
    }
    
    private static boolean isFull(char[][] grid) {
    	int n = grid.length;
    	for (int i = 0; i < n; i++) {
    		for (int j = 0; j < n; j++) {
    			if (grid[i][j] == ' ') {
    				return false;
    			}
    		}
    	}
    	
    	return true;
    }
    
	
    public static int numMatchingSubseq(String S, String[] words) {
    	int counter = 0;
    	
        for (int i = 0; i < words.length; i++) {
        	if (isSubseq(S, words[i])) {
        		counter++;
        	}
        }
        
        return counter;
    }
    
    private static boolean isSubseq(String S, String T) {
    	int index = -1;
    	
    	for (int i = 0; i < T.length(); i++) {
    		char c = T.charAt(i);
    		index = S.indexOf(c, index + 1);
    	
    		if (index < 0) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    /*
    public static int numSubarrayBoundedMax(int[] A, int L, int R) {
        int i = 0;
        int j = 0;
        int result = 0;
        int sum = 0;
        int n = A.length;
        		
        while (j < n) {
        	while (sum <= R )
        }
    }
    */
    public static void main(String[] args) {
    	
		String[] test11 = {"O  ", "   ", "   "};
		String[] test12 = {"XOX", " X ", "   "};
		String[] test13 = {"XXX", "   ", "OOO"};
		String[] test14 = {"XOX", "O O", "XOX"};
		String[] test15 = {"   ", "   ", "   "};
		String[] test16 = {"XXX", "XOO", "OO "};
		
		System.out.println(validTicTacToe(test11));
		System.out.println(validTicTacToe(test12));
		System.out.println(validTicTacToe(test13));
		System.out.println(validTicTacToe(test14));
		System.out.println(validTicTacToe(test15));
		System.out.println(validTicTacToe(test16));
		
		
    	/*
    	String[] word21 = {"a", "bb", "acd", "ace"};
    	String test21 = "abcde";
    	
    	System.out.println(numMatchingSubseq(test21, word21));
    	
    	
    	int[] test23 = {2, 1, 4, 3};
    	System.out.println(numSubarrayBoundedMax(test23, 2, 3));
    	*/
	}
}
