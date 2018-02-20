import java.util.Arrays;


public class Solution {
	/*
	 * In a 2D grid from (0, 0) to (N-1, N-1), every cell contains a 1, except those cells in the given list mines which are 0. What is the 
	 * largest axis-aligned plus sign of 1s contained in the grid? Return the order of the plus sign. If there is none, return 0.
	 * 
	 * An "axis-aligned plus sign of 1s of order k" has some center grid[x][y] = 1 along with 4 arms of length k-1 going up, down, left, 
	 * and right, and made of 1s. This is demonstrated in the diagrams below. Note that there could be 0s or 1s beyond the arms of the plus sign, 
	 * only the relevant area of the plus sign is checked for 1s.
	 * 
	 * Examples of Axis-Aligned Plus Signs of Order k:
	 * 
	 * Order 1:
	 * 000
	 * 010
	 * 000
	 * 
	 * Order 2:
	 * 00000
	 * 00100
	 * 01110
	 * 00100
	 * 00000
	 * 
	 * Order 3:
	 * 0000000
	 * 0001000
	 * 0001000
	 * 0111110
	 * 0001000
	 * 0001000
	 * 0000000
	 * 
	 * Example 1:
	 * 
	 * Input: N = 5, mines = [[4, 2]] 
	 * Output: 2
	 * Explanation:
	 * 11111
	 * 11111
	 * 11111
	 * 11111
	 * 11011
	 * In the above grid, the largest plus sign can only be order 2.  One of them is marked in bold.
	 * 
	 * Example 2:
	 * 
	 * Input: N = 2, mines = []
	 * Output: 1
	 * Explanation:
	 * There is no plus sign of order 2, but there is of order 1.
	 * 
	 * Example 3:
	 * 
	 * Input: N = 1, mines = [[0, 0]]
	 * Output: 0
	 * Explanation:
	 * There is no plus sign, so return 0.
	 * 
	 * Note:
	 * 1. N will be an integer in the range [1, 500].
	 * 2. mines will have length at most 5000.
	 * 3. mines[i] will be length 2 and consist of integers in the range [0, N-1].
	 * 4. (Additionally, programs submitted in C, C++, or C# will be judged with a slightly smaller time limit.)
	 */
	
    public static int orderOfLargestPlusSign(int N, int[][] mines) {
    	int max = 0;
    	int count = 0;
    	
        int[][] dp = new int[N][N];
        
        for (int[] d: dp) {
        	Arrays.fill(d, 1);
        }
        
        if (mines.length > 0 && mines[0].length > 0) {
	        for (int[] mine: mines) {
	        	dp[mine[0]][mine[1]] = 0;
	        }
        }
        
        // 从左向右扫，从右向左扫
        for (int i = 0; i < N; i++) {
        	count = 0;
        	for (int j = 0; j < N; j++) {
        		count = dp[i][j] == 0 ? 0 : count + 1;
        		dp[i][j] = count;
        	}
        	
        	count = 0;
        	for (int j = N - 1; j >= 0; j--) {
        		count = dp[i][j] == 0 ? 0 : count + 1;
        		dp[i][j] = Math.min(dp[i][j], count);
        	}
        }
        
        // 从上向下，从下向上扫
        for (int j = 0; j < N; j++) {
        	count = 0;
        	for (int i = 0; i < N; i++) {
        		count = dp[i][j] == 0 ? 0 : count + 1;
        		dp[i][j] = Math.min(dp[i][j], count);
        	}
        	
        	count = 0;
        	for (int i = N - 1; i >= 0; i--) {
        		count = dp[i][j] == 0 ? 0 : count + 1;
        		dp[i][j] = Math.min(dp[i][j], count);
        		max = Math.max(max, dp[i][j]);
        	}
        }
        
        /*
        for (int[] d: dp) {
        	for (int k: d) {
        		System.out.print(k + ", ");
        	}
        	System.out.println();
        }
        */
        return max;
    }
    
    
    public static void main(String[] args) {
		int[][] test1 = {{4, 2}};
		int[][] test2 = {{}};
		int[][] test3 = {{0, 0}};
		int[][] test4 = {{3, 0}, {3, 3}};
		
		System.out.println(orderOfLargestPlusSign(5, test1));
		System.out.println(orderOfLargestPlusSign(2, test2));
		System.out.println(orderOfLargestPlusSign(1, test3));
		System.out.println(orderOfLargestPlusSign(5, test4));
	}
}
