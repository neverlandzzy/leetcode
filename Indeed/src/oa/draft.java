package oa;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class draft {

	/************************************************************
	 * Solution 2: DFS
	 * 参考了http://www.1point3acres.com/bbs/thread-296273-1-1.html
	 * 
	 * 思路：
	 * For each paper:
	 * 1. We'll use 2D int array, grid[][], to store darkness of each cell on the grid;
	 * 2. We'll use a PriorityQueue, heap, to store the dots by descending order of their darkness; 
	 * 3. We'll use array, result[], to store the total darkness of the paper;
	 * 4. For each dot, we'll use Depth-first Search(DFS) to update the grid[][] and result[]: 
	 *    If its coordinates, (i, j) are within the grid([0, H) and [0, W))and if its darkness is greater than grid[i][j], 
	 *    we'll update grid[i][j], update the result and continue searching is upper, lower, left and right neighbors. Otherwise, 
	 *    we'll stop current search and return.
	 * 5. With the use of heap, we'll start DFS from the darker dots and we can skipp the dots whose darkness is equal or less than the darkness of
	 *    the starting cell.
	 * 
	 * Time Complexity: 
	 *                  For each paper, the worst case will take:
	 *                  O(H * W * N) + O(NlogN) 
	 *                  where H is the height of the paper, W is the width of the paper and N is the number of dots.
	 *                  As for each dot, each cell will be visited once.
	 *                  
	 * Space Complexity:
	 *                  O(H * W) + O(N)
	 *                  The 'int[][] grid' will take O(H * W) space. And we'll also need O(N) for heap and O(3*N) for 'int[][] dots'
	 *                  
	 * Optimizations:
	 * 1. Before traverse with DFS, we sort the dots by descending order of darkness. Therefore, we'll start DFS from the darker dots and we can 
	 *    skip the dots whose darkness is equal or less than the darkness of starting cell.
	 * 2. In DFS, for each dot, we'll stop traverse if darkness reaches 0. Thus, for each dot, the time of traverse is
	 *    O(min(darkness, H) * min(darkness, W))
	***********************************************************/
	public static int totalDarkness(int H, int W, int[][] dots) {
		int[][] grid = new int[H][W];
		int[] result = new int[1];
		
		// Sort the dots by descending order of darkness. 
		PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o2[2] - o1[2];
			}
		});
		
		for (int[] dot: dots) {
			heap.add(dot);
		}
		
		while (!heap.isEmpty()) {
			int[] dot = heap.poll();
			int x = dot[0];
			int y = dot[1];
			int darkness = dot[2];
			
			if (grid[x][y] >= darkness) {
				continue;
			}
			
			dfs(grid, x, y, darkness, result);
		}

		return result[0];
	}
	
	private static void dfs(int[][] grid, int x, int y, int darkness, int[] result) {
		if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && darkness > grid[x][y]) {
			result[0] += darkness - grid[x][y];
			grid[x][y] = darkness;
		} else {
			return;
		}
		
		dfs(grid, x + 1, y, darkness - 1, result);
		dfs(grid, x - 1, y, darkness - 1, result);
		dfs(grid, x, y + 1, darkness - 1, result);
		dfs(grid, x, y - 1, darkness - 1, result);
	}
	
	public static void main(String[] args) {
		
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // N papers
        
        for (int i = 0; i < N; i++) {
        	int H = in.nextInt(); // the height of the i-th paper
        	int W = in.nextInt(); // the width of the i-th paper
        	int numDots = in.nextInt(); // the number of dots on the i-th paper
        	int[][] dots = new int[numDots][3];
        	
        	for (int j = 0; j < numDots; j++) {
        		for (int k = 0; k <= 2; k++) {
        			dots[j][k] = in.nextInt();
        		}
        	}
        	
        	int result = totalDarkness(H, W, dots);
        	System.out.println(result);
        }

        in.close();
        
     }
	
	
	/*
	 * 	  For each dot, we'll use Depth-first Search(DFS) to update the grid[][] and result[]: 
	 *    If its coordinates, (i, j), are within the grid([0, H) and [0, W))and if its darkness is greater than grid[i][j], 
	 *    we'll update grid[i][j] and the result.And we'll also check and update the cells on the same column and row. Then, 
	 *    we'll continue searching is upper, lower, left and right neighbors. Otherwise, we'll stop current search and return.
	 */
	private static void dfs2(int[][] grid, int x, int y, int darkness, int[] result) {
		if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && darkness > grid[x][y]) {
		
			for (int i = 0; i < grid.length; i++) {
				if (darkness - Math.abs(i - x) > 0) {
					int cur = darkness - Math.abs(i - x);
					if (cur > grid[i][y]) {
						result[0] += cur - grid[i][y];
						grid[i][y] = cur;
					}
				}
			}
			
			for (int j = 0; j < grid[0].length; j++) {
				if (darkness - Math.abs(j - y) > 0) {
					int cur = darkness - Math.abs(j - y);
					if (cur > grid[x][j]) {
						result[0] += cur - grid[x][j];
						grid[x][j] = cur;
					}
				}
			}
		} else {
			return;
		}
		
		dfs2(grid, x + 1, y + 1, darkness - 2, result);
		dfs2(grid, x - 1, y - 1, darkness - 2, result);
		dfs2(grid, x + 1, y - 1, darkness - 2, result);
		dfs2(grid, x - 1, y + 1, darkness - 2, result);
	}
}
