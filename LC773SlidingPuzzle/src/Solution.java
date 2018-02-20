import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Solution {
	/*
	 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
	 * 
	 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
	 * 
	 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
	 * 
	 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
	 * 
	 * Examples:
	 * 
	 * Input: board = [[1,2,3],[4,0,5]]
	 * Output: 1
	 * Explanation: Swap the 0 and the 5 in one move.
	 * Input: board = [[1,2,3],[5,4,0]]
	 * Output: -1
	 * Explanation: No number of moves will make the board solved.
	 * 
	 * Input: board = [[4,1,2],[5,0,3]]
	 * Output: 5
	 * Explanation: 5 is the smallest number of moves that solves the board.
	 * An example path:
	 * After move 0: [[4,1,2],[5,0,3]]
	 * After move 1: [[4,1,2],[0,5,3]]
	 * After move 2: [[0,1,2],[4,5,3]]
	 * After move 3: [[1,0,2],[4,5,3]]
	 * After move 4: [[1,2,0],[4,5,3]]
	 * After move 5: [[1,2,3],[4,5,0]]
	 * 
	 * Input: board = [[3,2,4],[1,5,0]]
	 * Output: 14
	 * Note:
	 * 
	 * 1. board will be a 2 x 3 array as described above.
	 * 2. board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].
	 */
	
	// O(R*C*(R*C)!)  R = 2, C = 3 
    public static int slidingPuzzle(int[][] board) {
        String key = getKey(board);
        String target = "123450";
        
        Set<String> visited = new HashSet<>();
        Queue<int[][]> queue = new LinkedList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int result = 0;
        visited.add(key);
        queue.offer(board);
        
        while (!queue.isEmpty()) {
        	int size = queue.size();

        	for (int i = 0; i < size; i++) {
        		int[][] cur = queue.poll();

        		if (getKey(cur).equals(target)) {
        			return result;
        		}
        		
        		int x = 0;
        		int y = 0;
        		
        		for (int m = 0; m < cur.length; m++) {
        			for (int n = 0; n < cur[0].length; n++) {
        				if (cur[m][n] == 0) {
        					x = m; 
        					y = n;
        					break;
        				}
        			}
        		}
        		
        		for (int[] dir: directions) {
        			int nextX = x + dir[0];
        			int nextY = y + dir[1];
        			
        			if (nextX < 0 || nextX >= cur.length || nextY < 0 || nextY >= cur[0].length) {
        				continue;
        			}
        			int[][] newBoard = swap(cur, x, y, nextX, nextY);
        			
        			if (!visited.contains(getKey(newBoard))) {
        				queue.offer(newBoard);
        				visited.add(getKey(newBoard));
        			}

        		}
        	}
        	result++;
        }
        
        return -1;
    }
    
    private static String getKey(int[][] board) {
    	
    	StringBuilder sb = new StringBuilder();
    	
        for (int i = 0; i < board.length; i++) {
        	for (int j = 0; j < board[0].length; j++) {        		
        		sb.append(board[i][j]);
        	}
        }
        
        return sb.toString();
    }
    
    private static int[][] swap(int[][] board, int i1, int j1, int i2, int j2) {
    	
    	int[][] newBoard = new int[board.length][board[0].length];
    	
    	for (int i = 0; i < board.length; i++) {
    		for (int j = 0; j < board[0].length; j++) {
    			newBoard[i][j] = board[i][j];
    		}
    	}
    	int tmp = newBoard[i1][j1];
    	newBoard[i1][j1] = newBoard[i2][j2];
    	newBoard[i2][j2] = tmp;
    	
    	return newBoard;
    }
    
    public static void main(String[] args) {
		int[][] test31 = {{1, 2, 3}, {4, 0, 5}};
		int[][] test32 = {{1, 2, 3}, {5, 4, 0}};
		int[][] test33 = {{4, 1, 2}, {5, 0, 3}};
		int[][] test34 = {{3, 2, 4}, {1, 5, 0}};
		
		System.out.println(slidingPuzzle(test31));
		System.out.println(slidingPuzzle(test32));
		System.out.println(slidingPuzzle(test33));
		System.out.println(slidingPuzzle(test34));
	}
}
