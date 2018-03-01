package phone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Rectangle {
	/*
	 * 【基本题】给一个矩阵，矩阵中有且只有一个由0组成的长方体，其他全是1.
	 *         找出这个长方体的左上角左边，长方体的长和高。
	 *  input:
	 *  [
	 * 		[1,1,1,1,1,1],
	 * 		[1,0,0,0,1,1],
	 * 		[1,0,0,0,1,1],
	 * 		[1,1,1,1,1,1],
	 * 		[1,1,1,1,1,1]
	 *  ]
	 *  那么返回{1,1,3,2}  坐标为（1,1）长为3，高为2
	 * 
	 * 【Follow up 1】给一个矩阵，矩阵里的每个元素是1，但是其中分布着一些长方形区域， 这些长方形区域中的元素为0. （前提每两个长方体之间是不会连接的）
	 *  要求输出每个长方形的位置（用长方形的左上角元素坐标和右下角元素坐标表示）。
	 *  input:
	 *  [
	 *  	[1,1,1,1,1,1], 
	 *  	[0,0,1,0,1,1],
	 *  	[0,0,1,0,1,0],
	 *  	[1,1,1,0,1,0],
	 *  	[1,0,0,1,1,1]
	 *  ]
	 *  output:
	 *  [
	 *  	[1,0,2,1],
	 *  	[1,3,3,3],
	 *  	[2,5,3,5],
	 *  	[4,1,4,2]
	 *  ]
	 *  
	 * 【Follow up 2】返回listoflist， 每一个list是矩阵中相连的元素为0的点的坐标，类似LC200 number of island
	 */
	
	// 基本题 O(m * n)
	public static int[] findRectangle(int[][] grid) {
		int[] result = new int[4];
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return result;
		}
		
		int left   = Integer.MAX_VALUE;
		int right  = 0;
		int top  = Integer.MAX_VALUE;
		int bottom = 0;
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0) {
					left   = Math.min(left, j);
					right  = Math.max(right, j);
					top  = Math.min(top, i);
					bottom = Math.max(bottom, i);
				}
			}
		}
		
		
		// 返回[左上角x, 左上角y， 长， 宽]
		result[0] = top;
		result[1] = left;
		result[2] = right - left + 1;
		result[3] = bottom - top + 1;
		
		/*
		// 返回[左上角x, 左上角y， 右下角x， 右下角y]
		result[0] = top;
		result[1] = left;
		result[2] = bottom;
		result[3] = right;
		*/
		return result;
	}
	
	// Follow up 1: O(m * n)
	public static List<List<Integer>> findRectangle2(int[][] grid) {
		List<List<Integer>> result = new ArrayList<>();
		
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return result;
		}
		
		int m = grid.length;
		int n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0 && !visited[i][j]) {
					//System.out.println(" i = " + i + " j = " + j);
					result.add(bfs(grid, visited, i, j));
				}
			}
		}
		
		return result;
	}
	private static List<Integer> bfs(int[][] grid, boolean[][] visited, int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{i, j});
		visited[i][j] = true;
		int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		List<Integer> list = new ArrayList<>();
		
		int left   = Integer.MAX_VALUE;
		int right  = 0;
		int top  = Integer.MAX_VALUE;
		int bottom = 0;
		
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			int x = cell[0];
			int y = cell[1];
			
			
			left   = Math.min(left, y);
			right  = Math.max(right, y);
			top  = Math.min(top, x);
			bottom = Math.max(bottom, x);
			
			for (int[] dir: direction) {
				int nextX = x + dir[0];
				int nextY = y + dir[1];
				
				if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length && !visited[nextX][nextY] && grid[nextX][nextY] == 0) {
					queue.offer(new int[]{nextX, nextY});
					visited[nextX][nextY] = true;
				}
			}
		}
		
		list.addAll(Arrays.asList(top, left, bottom, right));
		return list;
	}
	
	// Follow up 2: O(m*n)
	public static List<List<int[]>> findRectangle3(int[][] grid) {
		List<List<int[]>> result = new ArrayList<>();
		
		if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
			return result;
		}
		
		int m = grid.length;
		int n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 0 && !visited[i][j]) {
					result.add(bfs3(grid, visited, i, j));
				}
			}
		}
		
		return result;
	}
	private static List<int[]> bfs3(int[][] grid, boolean[][] visited, int i, int j) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{i, j});
		visited[i][j] = true;
		int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		List<int[]> list = new ArrayList<>();
		
		while (!queue.isEmpty()) {
			int[] cell = queue.poll();
			int x = cell[0];
			int y = cell[1];
			
			
			list.add(new int[]{x, y});
			
			for (int[] dir: direction) {
				int nextX = x + dir[0];
				int nextY = y + dir[1];
				
				if (nextX >= 0 && nextX < grid.length && nextY >= 0 && nextY < grid[0].length && !visited[nextX][nextY] && grid[nextX][nextY] == 0) {
					queue.offer(new int[]{nextX, nextY});
					visited[nextX][nextY] = true;
				}
			}
		}
		
		return list;
	}
	
	public static void main(String[] args) {
		
		System.out.println("*********** 【基本题】找出有且仅有的一个矩形 ***********");
		int[][] test11 = {{1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1,}};
		int[][] test12 = {{1, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 1, 1}, {1, 1, 1, 0, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1,}};
		int[][] test13 = {{1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 1, 1,}};
		int[][] test14 = {{1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 0, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1,}};
		
		int[] result11 = findRectangle(test11);
		int[] result12 = findRectangle(test12);
		int[] result13 = findRectangle(test13);
		int[] result14 = findRectangle(test14);

		printArray(result11);
		printArray(result12);
		printArray(result13);
		printArray(result14);
		
		System.out.println("*********** 【Follow up 1】找出多个不相邻矩形 ***********");
		int[][] test21 = {{1, 1, 1, 1, 1, 1}, {0, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0}, {1, 1, 1, 0, 1, 0}, {1, 0, 0, 1, 1, 1,}};
		int[][] test22 = {{1, 1, 1, 1, 1, 1}, {0, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0}, {1, 1, 1, 0, 1, 0}, {0, 1, 1, 1, 1, 1,}};
		int[][] test23 = {{1, 1, 1, 1, 1, 1}, {1, 0, 0, 0, 1, 1}, {1, 0, 0, 0, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1,}};
		int[][] test24 = {{1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1}, {1, 1, 1, 1, 1, 1,}};
		
		System.out.println(findRectangle2(test21));
		System.out.println(findRectangle2(test22));
		System.out.println(findRectangle2(test23));
		System.out.println(findRectangle2(test24));
		System.out.println("*********** 【Follow up 2】找出相邻岛屿(grid[i][j] = 0) ***********");
		
		int[][] test31 = {{1, 1, 1, 1, 1, 1}, {0, 0, 1, 0, 1, 1}, {0, 0, 1, 0, 1, 0}, {1, 1, 1, 0, 1, 0}, {1, 0, 0, 1, 1, 1}};
		int[][] test32 = {{1, 1, 1, 1, 0}, {1, 1, 0, 1, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 0, 0}};
		int[][] test33 = {{1, 0, 1, 0, 1, 0}, {1, 0, 0, 0, 1, 0}, {1, 1, 1, 1, 0, 1}, {0, 0, 1, 1, 0, 1}, {1, 0, 0, 0, 0, 1}};
		
		List<List<int[]>> result31 = findRectangle3(test31);
		List<List<int[]>> result32 = findRectangle3(test32);
		List<List<int[]>> result33 = findRectangle3(test33);
		
		printList(result31);
		printList(result32);
		printList(result33);
	}
	
	private static void printArray(int[] array) {
		for (int k: array) {
			System.out.print(k + ", ");
		}
		
		System.out.println();
	}
	
	private static void printList(List<List<int[]>> list) {
		for (List<int[]> l: list) {
			System.out.print("{");
			for (int[] array: l) {
				System.out.print("[" + array[0] + ", " + array[1] + "]");
			}
			System.out.print("} ");
		}
		System.out.println();
	}
}
