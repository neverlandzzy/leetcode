import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * Let's play the minesweeper game (Wikipedia, online game)!
	 * 
	 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents an unrevealed 
	 * empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left, right, and all 4 diagonals) mines, 
	 * digit ('1' to '8') represents how many mines are adjacent to this revealed square, and finally 'X' represents a revealed mine.
	 * 
	 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), return the board after 
	 * revealing this position according to the following rules:
	 * 
	 * 1. If a mine ('M') is revealed, then the game is over - change it to 'X'.
	 * 2. If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent 
	 *    unrevealed squares should be revealed recursively.
	 * 3. If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the 
	 *    number of adjacent mines.
	 * 4. Return the board when no more squares will be revealed.
	 * 
	 * Example 1:
	 * Input: 
	 * [['E', 'E', 'E', 'E', 'E'],
	 *  ['E', 'E', 'M', 'E', 'E'],
	 *  ['E', 'E', 'E', 'E', 'E'],
	 *  ['E', 'E', 'E', 'E', 'E']]
	 * 
	 * Click : [3,0]
	 * Output: 
	 * [['B', '1', 'E', '1', 'B'],
	 *  ['B', '1', 'M', '1', 'B'],
	 *  ['B', '1', '1', '1', 'B'],
	 *  ['B', 'B', 'B', 'B', 'B']]
	 *  
	 * Example 2:
	 * Input: 
	 * [['B', '1', 'E', '1', 'B'],
	 *  ['B', '1', 'M', '1', 'B'],
	 *  ['B', '1', '1', '1', 'B'],
	 *  ['B', 'B', 'B', 'B', 'B']]
	 * 
	 * Click : [1,2]
	 * Output: 
	 * [['B', '1', 'E', '1', 'B'],
	 *  ['B', '1', 'X', '1', 'B'],
	 *  ['B', '1', '1', '1', 'B'],
	 *  ['B', 'B', 'B', 'B', 'B']]
	 *  
	 *  Note:
	 *  1. The range of the input matrix's height and width is [1,50].
	 *  2. The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least 
	 *     one clickable square.
	 *  3. The input board won't be a stage when game is over (some mines have been revealed).
	 *  4. For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the 
	 *     unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
	 */
	
	// Solution 1: DFS
	/*
    public static char[][] updateBoard(char[][] board, int[] click) {
    	int m = board.length;
    	int n = board[0].length;
    	
    	int row = click[0];
    	int col = click[1];
    	
    	//注意方向和Maze的区别
    	int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}}; 
    	
    	if (board[row][col] == 'M') {
    		board[row][col] = 'X';
    	} else {
    		int counter = 0;
    		// 这里不能像 Maze一样用while，因为只是搜索周围8个点，而不是一直走
    		for (int i = 0; i < dir.length; i++) {
    			int r = row;
    			int c = col;
    			
    			r += dir[i][0];
    			c += dir[i][1];
    			
    			if (r >= 0 && r < m && c >= 0 && c < n) {
    				if (board[r][c] == 'M' || board[r][c] == 'X') {
    					counter++;
    				}
    			}
    		}
    		
    		if (counter > 0) { 			
    			board[row][col] = (char)(counter + '0');
    		} else {
    			board[row][col] = 'B';
        		for (int i = 0; i < dir.length; i++) {
        			int r = row;
        			int c = col;
        			
    				r += dir[i][0];
    				c += dir[i][1];
    				
        			if (r >= 0 && r < m && c >= 0 && c < n) {
        				if (board[r][c] == 'E') {
        					updateBoard(board, new int[] {r, c});
        				}
        			}
        		}
    		}
    	}
    	
    	return board;
    }
    */
	
	// Solution 2: BFS
	public static char[][] updateBoard(char[][] board, int[] click) {
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        
        queue.offer(click);
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1} ,{1, -1}, {-1, 1}, {-1, -1}};
        
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            int i = point[0];
            int j = point[1];
            int count = 0;
            
            for (int[] dir: direction) {
                int nextI = i + dir[0];
                int nextJ = j + dir[1];
                
                if (nextI < 0 || nextJ < 0 || nextI >= m || nextJ >= n) {
                    continue;
                }
                
                if (board[nextI][nextJ] == 'M') {
                    count++;
                } 
            }
            
            if (count > 0) {
                board[i][j] = (char)(count + '0');
            } else {
                board[i][j] = 'B';
                for (int[] dir: direction) {
                    int nextI = i + dir[0];
                    int nextJ = j + dir[1];

                    if (nextI < 0 || nextJ < 0 || nextI >= m || nextJ >= n) {
                        continue;
                    }

                    if (board[nextI][nextJ] == 'E') {
                        queue.offer(new int[] {nextI, nextJ});
                        board[nextI][nextJ] = 'B';
                    } 
                }
            }
        }
        
        return board;
	}
    
    public static void main(String[] args) {
		char[][] test = new char[][] {{'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'M', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}, {'E', 'E', 'E', 'E', 'E'}};
		
		char[][] result = updateBoard(test, new int[] {3, 0});
		
		for (char[] charArray: result) {
			for (char c: charArray) {
				System.out.print(c + ", ");
			}
			System.out.println();
		}
	}
}
