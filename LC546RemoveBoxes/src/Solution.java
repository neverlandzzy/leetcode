
public class Solution {
	/*
	 * Given several boxes with different colors represented by different positive numbers. 
	 * You may experience several rounds to remove boxes until there is no box left. Each time you can choose some continuous boxes with 
	 * the same color (composed of k boxes, k >= 1), remove them and get k*k points.
	 * Find the maximum points you can get.
	 * 
	 * Example 1:
	 * Input:
	 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
	 * 
	 * Output:
	 * 23
	 * Explanation:
	 * [1, 3, 2, 2, 2, 3, 4, 3, 1] 
	 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 points) 
	 * ----> [1, 3, 3, 3, 1] (1*1=1 points) 
	 * ----> [1, 1] (3*3=9 points) 
	 * ----> [] (2*2=4 points)
	 * Note: The number of boxes n would not exceed 100.
	 */
	
	// https://discuss.leetcode.com/topic/84687/java-top-down-and-bottom-up-dp-solutions    
    // dp[i][j][k] remove掉 boxes[i ~ j]以及boxes[i]左边和其颜色相同的k个boxes所得的最大分数
    // 
    // 结果：
    // dp[0][n - 1][0] 即为最后要求的结果
    //
    // 初始条件：
    // 		1. dp[i][i - 1][k] = 0;
    // 		2. dp[i][i][k] = (k + 1) * (k + 1) boxes[i ~ i]只有一个box，但左边已经有了k个同色box，所以得分是(k + 1) * (k + 1)
    //
    // 递推关系：
    //      对于dp[i][j][k]，
    // 		1. 若remove掉boxes[i]，则获得的分数为 (k + 1) * (k + 1) + dp[i + 1][j][0]
    //         remove boxes[i]会获得(k + 1) * (k + 1)分数，boxes[i]右边剩余的箱子会获得dp[i + 1][j][0]， k = 0因为boxes[i + 1]左边已经没有相同颜色的箱子
    //      2. 若保留boxes[i]，留着与其它一些同色的箱子boxes[m]一起去除，i.e. [i, i + 1, ..., m, ...j]，则获得的分数为dp[i + 1][m - 1][0] + dp[m][j][k + 1]
    //         保留boxes[i]先remove [i + 1 ~ m - 1]获得dp[i + 1][m - 1][0]， k = 0因为boxes[i + 1]左边没有相同颜色的箱子， 剩余的部分为boxes[i]和boxes[m ~ j]
    //         剩余部分的分数为dp[m][j][k + 1], 因为对于m而言，左边有k + 1个同色箱子。
    //      3. 若boxes[i + 1 ~ j]区间中，有多个与boxes[i]同色的箱子，则取最大值，即，max(dp[i + 1][m - 1][0] +  dp[m][j][k + 1])
	
	// Similar to LC664
	
	// Solution 1: DFS + mem : top - down
	
	public static int removeBoxes(int[] boxes) {
	    int n = boxes.length;
	    int[][][] dp = new int[n][n][n];
	    return helper(boxes, 0, n - 1, 0, dp);
	}
	
	private static int helper(int[] boxes, int i, int j, int k, int[][][] dp) {
		if (i > j) {
			return 0;
		}
		if (dp[i][j][k] > 0) {
			return dp[i][j][k];
		}
		
		// optimization: all boxes of the same color counted continuously from the first box should be grouped together
		while (i + 1 <= j && boxes[i + 1] == boxes[i]) {
			i++;
			k++;
		}
		
		int result = (k + 1) * (k + 1) + helper(boxes, i + 1, j, 0, dp);
	    
		for (int m = i + 1; m <= j; m++) {
			if (boxes[i] == boxes[m]) {
				result = Math.max(result, helper(boxes, i + 1, m - 1, 0, dp) + helper(boxes, m, j, k + 1, dp));
			}		
		}
		dp[i][j][k] = result;
		return dp[i][j][k];
	}
	
	
	// Solution 2: DP: bottom - up
	/*
    public static int removeBoxes(int[] boxes) {
        if (boxes == null || boxes.length == 0) {
        	return 0;
        }
        
        int n = boxes.length;
        
        int[][][] dp = new int[n][n][n];
        
        for (int i = 0; i < n; i++) {
        	for (int k = 0; k <= i; k++) {
        		dp[i][i][k] = (k + 1) * (k + 1); 
        	}
        }

        for (int len = 1; len < n; len++) {
        	for (int i = 0; i + len < n; i++) {
        		int j = i + len;
        		for (int k = 0; k < j; k++) {
        			int result = (k + 1) * (k + 1) + dp[i + 1][j][0];
        			
        			for (int m = i + 1; m <= j; m++) {
        	            if (boxes[m] == boxes[i]) {
        	                result = Math.max(result, dp[i + 1][m - 1][0] + dp[m][j][k + 1]);
        	            }
        			}
        			dp[i][j][k] = result;
        		}
        	}
        }
        
        return dp[0][n - 1][0];
    }
    */
    public static void main(String[] args) {
		int[] test = {1, 3, 2, 2, 2, 3, 4, 3, 1};
		
		System.out.println(removeBoxes(test));
	}
}
