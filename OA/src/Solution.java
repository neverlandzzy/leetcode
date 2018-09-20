import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
	
	public static int zombieCluster(List<String> zombies) {
		if (zombies == null || zombies.size() == 0) {
			return 0;
		}
		
		int n = zombies.size();
		int[][] grid = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = zombies.get(i).charAt(j) == '0' ? 0 : 1;
			}
		}
		
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    count++;
                    bfs(grid, i, j);
                }				
			}
		}
		
		return count;
	}
	
    private static void bfs(int[][] grid, int i, int j) {
        int n = grid.length;
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        grid[i][j] = 2;
        
        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];
            
            
            for (int[] dir: direction) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
                    queue.offer(new int[]{nextX, nextY});
                    grid[nextX][nextY] = 2;
                }
            }
        }
    }	
	
	public static void main(String[] args) {
		List<String> test1 = new ArrayList<>(Arrays.asList("1100", "1110", "0110", "0001"));
		List<String> test2 = new ArrayList<>(Arrays.asList("10000", "01000", "00100", "00010", "00001"));

		System.out.println(zombieCluster(test1));
		System.out.println(zombieCluster(test2));
	}

}
