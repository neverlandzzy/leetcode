
public class Solution {
	/*
	 * We have a grid of 1s and 0s; the 1s in a cell represent bricks.  A brick will not drop if and only if it is directly connected to the 
	 * top of the grid, or at least one of its (4-way) adjacent bricks will not drop.
	 * 
	 * We will do some erasures sequentially. Each time we want to do the erasure at the location (i, j), the brick (if it exists) on that 
	 * location will disappear, and then some other bricks may drop because of that erasure.
	 * 
	 * Return an array representing the number of bricks that will drop after each erasure in sequence.
	 * 
	 * Example 1:
	 * Input: 
	 * grid = [[1,0,0,0],[1,1,1,0]]
	 * hits = [[1,0]]
	 * Output: [2]
	 * Explanation: 
	 * If we erase the brick at (1, 0), the brick at (1, 1) and (1, 2) will drop. So we should return 2.
	 * 
	 * Example 2:
	 * Input: 
	 * grid = [[1,0,0,0],[1,1,0,0]]
	 * hits = [[1,1],[1,0]]
	 * Output: [0,0]
	 * Explanation: 
	 * When we erase the brick at (1, 0), the brick at (1, 1) has already disappeared due to the last move. So each erasure will 
	 * cause no bricks dropping.  Note that the erased brick (1, 0) will not be counted as a dropped brick.
	 * 
	 * Note:
	 * 	1. The number of rows and columns in the grid will be in the range [1, 200].
	 * 	2. The number of erasures will not exceed the area of the grid.
	 * 	3. It is guaranteed that each erasure will be different from any other erasure, and located inside the grid.
	 * 	4. An erasure may refer to a location with no brick - if it does, no bricks drop.
	 */
	
	// LC305 反过程
	// https://leetcode.com/problems/bricks-falling-when-hit/discuss/119829/Python-Solution-by-reversely-adding-hits-bricks-back
	// https://leetcode.com/problems/bricks-falling-when-hit/discuss/121077/Java-Union-Find-by-backwardly-adding-bricks-back-20ms
	
    public static int[] hitBricks(int[][] grid, int[][] hits) {
        
    }
    
    public static void main(String[] args) {
		int[][] grid1 = {{1, 0, 0, 0}, {1, 1, 1, 0}}; 
		int[][] hit1 = {{1, 0}};
		
		int[][] grid2 = {{1, 0, 0, 0}, {1, 1, 0, 0}}; 
		int[][] hit2 = {{1, 1}, {1, 0}};
		
		int[] result1 = hitBricks(grid1, hit1);
		int[] result2 = hitBricks(grid2, hit2);
		
		print(result1);
		print(result2);
	}
    
    private static void print(int[] arr) {
    	for (int i: arr) {
    		System.out.print(i + ", ");
    	}
    	
    	System.out.println();
    }
}
