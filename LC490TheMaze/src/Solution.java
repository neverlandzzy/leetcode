import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
	 * but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
	 * 
	 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.
	 * 
	 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the 
	 * maze are all walls. The start and destination coordinates are represented by row and column indexes.
	 * 
	 * Example 1
	 * 
	 * Input 1: a maze represented by a 2D array
	 * 
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 0 0 0 1 0
	 * 1 1 0 1 1
	 * 0 0 0 0 0
	 * 
	 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
	 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
	 * 
	 * Output: true
	 * 
	 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
	 * 
	 * Example 2
	 * 
	 * Input 1: a maze represented by a 2D array
	 * 
	 * 0 0 1 0 0
	 * 0 0 0 0 0
	 * 0 0 0 1 0
	 * 1 1 0 1 1
	 * 0 0 0 0 0
	 * 
	 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
	 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
	 * 
	 * Output: false
	 * 
	 * Explanation: There is no way for the ball to stop at the destination.
	 * 
	 * Note:
	 * 
	 * 1. There is only one ball and one destination in the maze.
	 * 2. Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
	 * 3. The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the 
	 *    border of the maze are all walls.
	 * 4. The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
     *
	 */

	/*
    public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
    	Queue<int[]> queue = new LinkedList<int[]>();
    	queue.offer(start);
    	
    	int m = maze.length;
    	int n = maze[0].length;
    	
    	boolean[][] visited = new boolean[m][n];
    	visited[start[0]][start[1]] = true;
    	
    	int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    	
    	while (!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		
    		for (int i = 0; i < dir.length; i++) {
        		int x = cur[0];
        		int y = cur[1];
        		
        		// 也可以这样，就不用x -= dir[i][0];y -= dir[i][1];
                // while (x + dir[0] >= 0 && y + dir[1] >= 0 && x + dir[0] < m && y + dir[1] < n && maze[x + dir[0]][y + dir[1]] == 0) {
                //     x += dir[0];
                //     y += dir[1];
                // }
                
	    		while(x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
	    			x += dir[i][0];
	    			y += dir[i][1];
	    		}
	    		
	    		x -= dir[i][0];
	            y -= dir[i][1];
	            
	    		if (visited[x][y]) {
	    			continue;
	    		}
	    		visited[x][y] = true;
	    		
	            if (x == destination[0] && y == destination[1]) {
	            	return true;
	            }
	            
	            queue.offer(new int[] {x, y});
    		}
    	}
        return false;
    }
    */
	
	
	// DFS
	static int[][] direction = {{0, 1}, {1, 0}, {0 ,-1}, {-1, 0}};
	public static boolean hasPath(int[][] maze, int[] start, int[] destination) {
    	int m = maze.length;
    	int n = maze[0].length;
    	
    	boolean[][] visited = new boolean[m][n];
    	
    	return helper(maze, destination, visited, start[0], start[1]);
	}
	
	private static boolean helper(int[][] maze, int[] destination, boolean[][] visited, int i, int j) {
		if (visited[i][j]) {
			return false;
		}
		
		if (i == destination[0] && j == destination[1]) {
			return true;
		}
		
    	int m = maze.length;
    	int n = maze[0].length;
    	
		visited[i][j] = true;
		for (int[] dir: direction) {
			int x = i; 
			int y = j;
            while (x + dir[0] >= 0 && y + dir[1] >= 0 && x + dir[0] < m && y + dir[1] < n && maze[x + dir[0]][y + dir[1]] == 0) {
                 x += dir[0];
                 y += dir[1];
            }
            
            if (helper(maze, destination, visited, x, y)) {
            	return true;
            }
		}
		
		
		return false;
	}
	
    public static void main(String[] args) {
		int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
		
		System.out.println(hasPath(maze, new int[] {0, 4}, new int[] {4, 4}));
		System.out.println(hasPath(maze, new int[] {0, 4}, new int[] {3, 2}));
	}
}
