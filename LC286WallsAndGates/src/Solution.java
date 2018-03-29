
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
