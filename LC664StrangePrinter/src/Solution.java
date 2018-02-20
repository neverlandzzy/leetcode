
public class Solution {
	/*
	 * There is a strange printer with the following two special requirements:
	 * 
	 * The printer can only print a sequence of the same character each time.
	 * At each turn, the printer can print new characters starting from and ending at any places, and will cover the original 
	 * existing characters.
	 * 
	 * Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in 
	 * order to print it.
	 * 
	 * Example 1:
	 * Input: "aaabbb"
	 * Output: 2
	 * Explanation: Print "aaa" first and then print "bbb".
	 * 
	 * Example 2:
	 * Input: "aba"
	 * Output: 2
	 * Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
	 * 
	 * Hint: Length of the given string will not exceed 100.
	 */
	
	// Similar to LC546
	// https://discuss.leetcode.com/topic/100148/same-as-remove-boxes
	
	// Solution 1: DFS + mem top - down --直接用LC546的解法
	/*
    public static int strangePrinter(String s) {
        int n = s.length();
        int[][][] dp = new int[n][n][n];
        
        return helper(s, 0, n - 1, 0, dp);
    }
    
    private static int helper(String s, int i, int j, int k, int[][][]dp) {
    	if (i > j) {
    		return 0;
    	}
    	if (dp[i][j][k] > 0) {
    		return dp[i][j][k];
    	}
    	
		while (i + 1 <= j && s.charAt(i + 1) == s.charAt(i)) {
			i++;
			k++;
		}
		
		int result = 1 + helper(s, i + 1, j, 0, dp);
		
		for (int m = i + 1; m <= j; m++) {
			if (s.charAt(i) == s.charAt(m)) {
				result = Math.min(result, helper(s, i + 1, m - 1, 0, dp) + helper(s, m, j, k + 1, dp));
			}		
		}
		dp[i][j][k] = result;
		return dp[i][j][k];
    }
    */
	
	// Solution 2: DFS + mem top - down 优化
	// 本题不同于LC546的地方在于，每一步消除[i, j]的花费都是1，而LC546每一步消除[i, j]的得分取决于[i, j]i左边的同色box的数量，因此LC546需要k记录，而本题不用
	/*
    public static int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        
        return helper(s, 0, n - 1, dp);
    }
    
    private static int helper(String s, int i, int j, int[][]dp) {
    	if (i > j) {
    		return 0;
    	}
    	
    	if (dp[i][j] > 0) {
    		return dp[i][j];
    	}
		
		int result = 1 + helper(s, i + 1, j, dp);
		
		for (int m = i + 1; m <= j; m++) {
			if (s.charAt(i) == s.charAt(m)) {
				// 若s.charAt(i) == s.charAt(m)， 则打印[i, j]区间的步数 = 打印[i+1, m-1]的步数 + 打印[m, j]的步数
				result = Math.min(result, helper(s, i + 1, m - 1, dp) + helper(s, m, j, dp));
			}		
		}
		dp[i][j] = result;
		return dp[i][j];
    }
    */
	
    // Solution 3: DP bottom - up 同理对LC546解法优化，只需要二维数组dp[][]
    public static int strangePrinter(String s) {
    	
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	
        int n = s.length();
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
        	dp[i][i] = 1;
        }
        
        for (int len = 1; len < n; len++) {
        	for (int i = 0; i + len < n; i++) {
        		int j = i + len;

        		dp[i][j] = dp[i + 1][j] + 1;
        		
        		for (int m = i + 1; m <= j; m++) {
        			if (s.charAt(i) == s.charAt(m)) {
        				dp[i][j] = Math.min(dp[i][j], dp[i + 1][m - 1] + dp[m][j]);
        			}
        		}
        	}
        }
        
        return dp[0][n - 1];
    }
    
    
    public static void main(String[] args) {
		System.out.println(strangePrinter("aaabbb"));
		System.out.println(strangePrinter("aba"));
	}
}
