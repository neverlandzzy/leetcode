import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {
	/*
	 * Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
	 * compute the volume of water it is able to trap after raining.
	 *
	 * Note:
	 * Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.
	 *
	 * Example:
	 *
	 * Given the following 3x6 height map:
	 * [
	 *  [1,4,3,1,3,2],
	 *  [3,2,1,3,2,4],
	 *  [2,3,3,2,3,1]
	 * ]
	 *
	 * Return 4.
	 */
	
	// https://discuss.leetcode.com/topic/60418/java-solution-using-priorityqueue/2
	// 九章强化第三节课
	// 从矩阵最外层开始，将每个点加入到PriorityQueue中，每次遍历poll出最矮的cell，从这个cell向内遍历，遇到比它矮的，则说明可以储水cell.h - newCell.h，遇到
	// 比它高的则将外墙高度更新为新的高度，然后将新的cell加入到queue
    // Time: O(m*n*log(m + n)): 每次poll的时候，queue的size都是m+n， 因此花费log(m + n)
	static class Cell {
        int r;
        int c;
        int h;
        
        Cell(int r, int c, int h) {
            this.r = r;
            this.c = c;
            this.h = h;
        }
     }
    
     public static int trapRainWater(int[][] heightMap) {
    	
    	if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
    		return 0;
    	}
    	
    	int m = heightMap.length;
    	int n = heightMap[0].length;
    	boolean[][] visited = new boolean[m][n];
    	
        PriorityQueue<Cell> queue = new PriorityQueue<>(new Comparator<Cell>() {
			public int compare(Cell o1, Cell o2) {
				return o1.h - o2.h;
			}       	
        });
        
        for (int i = 0; i < m; i++) {
        	queue.offer(new Cell(i, 0, heightMap[i][0]));
        	queue.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
        	visited[i][0] = true;
        	visited[i][n - 1] = true;
        }
        
        for (int i = 0; i < n; i++) {
        	queue.offer(new Cell(0, i, heightMap[0][i]));
        	queue.offer(new Cell(m - 1, i, heightMap[m - 1][i]));
        	visited[0][i] = true;
        	visited[m - 1][i] = true;
        }
        
        int result = 0;
        
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        while (!queue.isEmpty()) {
        	Cell cell = queue.poll();
        	for (int[] dir: direction) {
        		int row = cell.r + dir[0];
        		int col = cell.c + dir[1];
        		
        		if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col]) {
        			visited[row][col] = true;
        			queue.offer(new Cell(row, col, Math.max(heightMap[row][col], cell.h)));
        			result += Math.max(0, cell.h - heightMap[row][col]);
        		}
        	}
        }
        
        return result;
     }

    
     public static void main(String[] args) {
		int[][] test = {{1,4,3,1,3,2}, {3,2,1,3,2,4}, {2,3,3,2,3,1}};
		
		System.out.println(trapRainWater(test));
	 }
}
