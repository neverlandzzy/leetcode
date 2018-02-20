
public class Solution {
	/*
	 * Write a program to solve a Sudoku puzzle by filling the empty cells.
	 * 
	 * Empty cells are indicated by the character '.'.
	 * 
	 * You may assume that there will be only one unique solution.
	 */
	
    public static void solveSudoku(char[][] board) {
        solvable(board);
    }
    
    private static boolean solvable(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        board[i][j] = c;
                        if (isValid(i, j, board) && solvable(board)) {
                            return true;
                        }
                        board[i][j] = '.';
                    }
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private static boolean isValid(int i, int j, char[][] board) {
        for (int k = 0; k < 9; k++) {
            if (j != k && board[i][k] == board[i][j]) {
                return false;
            }
        }
        
        for (int k = 0; k < 9; k++) {
            if (i != k && board[k][j] == board[i][j]) {
                return false;
            }
        }
        
        for (int k = 0; k < 9; k++) {
            int x = i / 3 * 3 + k / 3;
            int y = j / 3 * 3 + k % 3;
            if (x != i && y != j && board[x][y] == board[i][j]) {
                return false;
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
    	
    	solveSudoku(board);
    	
    	System.out.println("=====");
    	for (int i = 0; i < 9; i++) {
    		for (int j = 0; j < 9; j++) {
    			System.out.print(board[i][j]);
    		}
    		System.out.println();
    	}
    }
}
