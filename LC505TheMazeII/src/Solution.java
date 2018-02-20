import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
	 * but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
	 * 
	 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
	 * The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) 
	 * to the destination (included). If the ball cannot stop at the destination, return -1.
	 * 
	 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders 
	 * of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
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
	 * Output: 12
	 * 
	 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
	 * 				The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
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
	 * Output: -1
	 * Explanation: There is no way for the ball to stop at the destination.
	 * 
	 * Note:
	 * 1. There is only one ball and one destination in the maze.
	 * 2. Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
	 * 3. The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the 
	 *    border of the maze are all walls.
	 * 4. The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
	 */
	
	private static class Point {
		int x, y, d;
		private Point(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;		
		}
	}
	
	// BFS
	/*
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<Point> queue = new LinkedList<Point>();
        
        int m = maze.length;
        int n = maze[0].length;
        
        int[][] distance = new int[m][n];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        for (int i = 0; i < m; i ++) {
        	Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        
        queue.offer(new Point(start[0], start[1], 0));
        
        while (!queue.isEmpty()) {
        	Point p = queue.poll();
        	
        	for (int i = 0; i < dir.length; i++) {
            	int x = p.x;
            	int y = p.y;
            	int d = p.d;
            	
        		while (x >= 0 && x < m && y >=0 && y < n && maze[x][y] == 0) {
        			x += dir[i][0];
        			y += dir[i][1];
        			d++;
        		}
        		
        		x -= dir[i][0];
        		y -= dir[i][1];
        		d--;
        		
        		if (d > distance[destination[0]][destination[1]]) {
        			continue;
        		}
        		
        		if (d < distance[x][y]) {
        			distance[x][y] = d;
        			queue.offer(new Point(x, y, d));
        		}
        	}	
        }
        
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
    }
	*/
	
	// DFS
	public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
		int m = maze.length;
		int n = maze[0].length;
		
        int[][] distance = new int[m][n];
        
        for (int i = 0; i < m; i ++) {
        	Arrays.fill(distance[i], Integer.MAX_VALUE);
        }
        
        helper(maze, new Point(start[0], start[1], 0), distance, destination);
        
        return distance[destination[0]][destination[1]] == Integer.MAX_VALUE ? -1 : distance[destination[0]][destination[1]];
	}
	
	private static void helper(int[][] maze, Point start, int[][] distance, int[] destination) {
		int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int m = maze.length;
		int n = maze[0].length;
		
		for (int[] dir: direction) {
			int x = start.x;
			int y = start.y;
			int d = start.d;
			
			while (x >= 0 && x < m && y >= 0 && y < n && maze[x][y] == 0) {
				x += dir[0];
				y += dir[1];
				d++;
			}
			
			x -= dir[0];
			y -= dir[1];
			d--;
			
			if (d > distance[destination[0]][destination[1]]) {
				continue;
			}
			
			if (d < distance[x][y]) {
				distance[x][y] = d;
				helper(maze, new Point(x, y, d), distance, destination);
			}
		}
	}
	
    public static void main(String[] args) {
		int[][] maze = {{0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 1, 0}, {1, 1, 0, 1, 1}, {0, 0, 0, 0, 0}};
		
		System.out.println(shortestDistance(maze, new int[] {0, 4}, new int[] {4, 4}));
		System.out.println(shortestDistance(maze, new int[] {0, 4}, new int[] {3, 2}));
	}
}
