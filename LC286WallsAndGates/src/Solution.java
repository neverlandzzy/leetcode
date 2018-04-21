import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * You are given a m x n 2D grid initialized with these three possible values.
	 * 
	 * -1 - A wall or an obstacle.
	 * 0 - A gate.
	 * INF - Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance 
	 * to a gate is less than 2147483647.
	 * 
	 * Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
	 * 
	 * For example, given the 2D grid:
	 * INF  -1  0  INF
	 * INF INF INF  -1
	 * INF  -1 INF  -1
	 *   0  -1 INF INF
	 *   
	 * After running your function, the 2D grid should be:
	 *   3  -1   0   1
	 *   2   2   1  -1
	 *   1  -1   2  -1
	 *   0  -1   3   4
	 */
	
    public static void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }
        
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    bfs(rooms, i, j);
                }
            }
        }
    }
    
    private static void bfs(int[][] rooms, int x, int y) {
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        
        int distance = 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] point = queue.poll();
                
                for (int[] dir: directions) {
                    int nextX = point[0] + dir[0];
                    int nextY = point[1] + dir[1];
                    
                    if (nextX < 0 || nextX >= rooms.length || nextY < 0 || nextY >= rooms[0].length) {
                        continue;
                    }
                    
                    if (rooms[nextX][nextY] == Integer.MAX_VALUE || distance < rooms[nextX][nextY]) {
                        rooms[nextX][nextY] = distance;
                        queue.offer(new int[]{nextX, nextY});
                    }
                }
            }
            distance++;
        }
    }
    
    public static void main(String[] args) {
		int inf = Integer.MAX_VALUE;
		
		int[][] test1 = {{inf, -1, 0, inf}, {inf, inf, inf, -1}, {inf, -1, inf, -1}, {0, -1, inf, inf}};
		wallsAndGates(test1);
		
		for (int[] a: test1) {
			for (int i: a) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
	}
}
