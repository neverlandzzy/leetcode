
public class Solution {
	/*
	 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, 
	 * is a cellular automaton devised by the British mathematician John Horton Conway in 1970."
	 * 
	 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). 
	 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the 
	 * following four rules (taken from the above Wikipedia article):
	 * 
	 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
	 * Any live cell with two or three live neighbors lives on to the next generation.
	 * Any live cell with more than three live neighbors dies, as if by over-population..
	 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
	 * 
	 * Write a function to compute the next state (after one update) of the board given its current state.
	 * 
	 * Follow up: 
	 * Could you solve it in-place? Remember that the board needs to be updated at the same time: 
	 * You cannot update some cells first and then use their updated values to update other cells.
	 * 
	 * In this question, we represent the board using a 2D array. In principle, the board is infinite, 
	 * which would cause problems when the active area encroaches the border of the array. 
	 * How would you address these problems?
	 */
	
    public static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        // 1 -> 0 : -1
        // 0 -> 1 : 2
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = liveNeighbors(board, i, j);
                if (board[i][j] == 1) {
                    if (count != 2 && count != 3) {
                        board[i][j] = -1;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                }
                
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
            }
        }
    }
    
    private static int liveNeighbors(int[][] board, int i, int j) {
        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        int counter = 0;
        
        for (int[] dir: direction) {
            int nextI = i + dir[0];
            int nextJ = j + dir[1];
            
            if (nextI >= 0 && nextI < board.length && nextJ >= 0 && nextJ < board[0].length) {
                if (board[nextI][nextJ] == 1 || board[nextI][nextJ] == -1) {
                    counter++;
                }
            }
        }
        
        return counter;
    }
    
	/*
    public static void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		int lives = lives(board, m, n, i, j);
        		
        		if (board[i][j] == 1 && (lives >= 2 && lives <= 3)) {
        			board[i][j] = 3;
        		}
        		
        		if (board[i][j] == 0 && lives == 3) {
        			board[i][j] = 2;
        		}
        	}
        }
        
        for (int i = 0; i < m; i++) {
        	for(int j = 0; j < n; j++) {
        		board[i][j] = board[i][j] >> 1;
        	}
        }
    }

    private static int lives(int[][] board, int m, int n, int i, int j) {
    	int result = 0;
    	
    	for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, m - 1); x++) {
    		for (int y = Math.max(0, j - 1); y <= Math.min(j + 1, n - 1); y++) {
    			result += board[x][y] & 1;
    		}
    	}
    	
    	result = result - (board[i][j] & 1);
    	return result;
    }
	*/
    public static void main(String[] args) {
		int[][] board = {{1, 0, 1, 0, 1}, {0, 1, 1, 1, 0}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 0, 1, 1, 0}};
		gameOfLife(board);
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
				
			}
			System.out.println();
		}
	}
}
