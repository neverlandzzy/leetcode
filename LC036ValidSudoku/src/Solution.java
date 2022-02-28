
public class Solution {
	/**
	 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
	 * 
	 * 1.Each row must have the numbers 1-9 occuring just once.
	 * 2.Each column must have the numbers 1-9 occuring just once.
	 * 3.And the numbers 1-9 must occur just once in each of the 9 sub-boxes of the grid.
	 * 
	 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
	 * 
	 * Note:
	 * A valid Sudoku board (partially filled) is not necessarily solvable. 
	 * Only the filled cells need to be validated.
	 * 
	 */
	
    public static boolean isValidSudoku(char[][] board) {
        /*
         * Solution 1: Time - O(3n^2), Space - O(n) 
         */
    	/*
    	// 1. scan col
    	for (int i = 0; i < 9; i++) {
    		boolean[] valid = new boolean[9];
    		for (int j = 0; j < 9; j++) {
    			 
    			if (board[i][j] != '.') {
    				if (valid[board[i][j] - '1'] == true) {
    					return false;
    				} else {
    					valid[board[i][j] - '1'] = true;    					
    					 
    				}
    			}
    		}
    	}
    	// 2. scan row
    	for (int i = 0; i < 9; i++) {
			boolean[] valid = new boolean[9];
    		for (int j = 0; j < 9; j++) {
    			if (board[j][i] != '.') {
    				if (valid[board[j][i] - '1'] == true) {
    					return false;
    				} else {
    					valid[board[j][i] - '1'] = true;
    				}
    			}
    		}
    	}
    	
    	// 3. scan box
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
				boolean[] valid = new boolean[9];
    			for (int k = 0; k < 9; k++) {
    				int x = i*3 + k/3;
    				int y = j*3 + k%3;
	    			if (board[x][y] != '.') {
	    				if (valid[board[x][y] - '1'] == true) {
	    					return false;
	    				} else {
	    					valid[board[x][y] - '1'] = true;
	    				}
	    			}
    			}
    		}
    	}
    	
    	
    	return true;
    	*/
    	/*
    	 * Solution 2: Time - O(n^2), Space - O(3n^2)
    	 */
    	/*
    	boolean[][] col = new boolean[9][9];
    	boolean[][] row = new boolean[9][9];
    	boolean[][] box = new boolean[9][9];
    	
    	for (int i = 0; i < 9; i++) {
    		for(int j = 0; j < 9; j++) {
    			if (board[i][j] != '.') {
    				if (col[i][board[i][j]-'1'] == true) {
    					return false;
    				} else {
    					col[i][board[i][j]-'1'] = true;
    				}
    				
    				if (row[j][board[i][j]-'1'] == true) {
    					return false;
    				} else {
    					row[j][board[i][j]-'1'] = true;
    				}
    				
    				if (box[i/3*3+j/3][board[i][j]-'1'] == true) {
    					return false;
    				} else {
    					box[i/3*3+j/3][board[i][j]-'1'] = true;
    				}
    			}
    		}
    	}
    	return true;
    	*/
    	
    	// Solution 3: Time - O(n^2), Space - O(3n) -- faster
        int[] row = new int[9];
        int[] col = new int[9];
        int[] box = new int[9];
        
        for (int i = 0; i < 9; i++) {
        	for (int j = 0; j < 9; j++) {
        		if (board[i][j] != '.') {
					int mask = 1 << (board[i][j] - '0');

					if ((row[i] & mask) != 0 || (col[j] & mask) != 0 || (box[i / 3 * 3 + j / 3] & mask) != 0) {
						return false;
					}

					row[i] |= mask;
					col[j] |= mask;
					box[i / 3 * 3 + j / 3] |= mask;
        		}

        	}
        }
       
        return true;
    }
    
    public static void main(String[] args) {
    	String[] test = {"..5.....6","....14...",".........",".....92..","5....2...",".......3.","...54....","3.....42.","...27.6.."};
    	char[][] board = new char[9][9];
    	
    	for (int i = 0; i <9; i++) {
    		board[i] = test[i].toCharArray();
    	}
    	
    	for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++) {
    			System.out.print(board[i][j]);
    		}
    		System.out.println();
    	}
    	
    	System.out.println(isValidSudoku(board));
    }
    
}
