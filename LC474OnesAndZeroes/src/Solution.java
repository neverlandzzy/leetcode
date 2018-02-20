
public class Solution {
	/*
	 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
	 * 
	 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting 
	 * of only 0s and 1s.
	 * 
	 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be 
	 * used at most once.
	 * 
	 * Note:
	 * The given numbers of 0s and 1s will both not exceed 100
	 * The size of given string array won't exceed 600.
	 * 
	 * Example 1:
	 * Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
	 * Output: 4
	 * 
	 * Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
	 * 
	 * Example 2:
	 * Input: Array = {"10", "0", "1"}, m = 1, n = 1
	 * Output: 2
	 * 
	 * Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
	 */
	
	// https://leetcode.com/problems/ones-and-zeroes/#/solution
	// https://discuss.leetcode.com/topic/76103/0-1-knapsack-detailed-explanation
	// 0/1背包：http://love-oriented.com/pack/P01.html
	
	/*
    public static int findMaxForm(String[] strs, int m, int n) {
        if (strs == null || strs.length == 0) {
            return 0;
        }
        
        int l = strs.length;
        
        int[][][] dp = new int[l + 1][m + 1][n + 1];  //dp[k][i][j]: using strs[0]...strs[k - 1], i 0s and j 1s, the max number
                                                      //             of strings we could get
        
        dp[0][0][0] = 0;
        
        for (int k = 1; k <= l; k++) {
            
            int[] count = countZerosAndOnes(strs[k - 1]);
            
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i - count[0] >= 0 && j - count[1] >= 0) {
                        dp[k][i][j] = Math.max(dp[k - 1][i][j], dp[k - 1][i - count[0]][j - count[1]] + 1);
                    } else {
                        dp[k][i][j] = dp[k - 1][i][j];
                    }
                }
            }
        }
        
        return dp[l][m][n];
    }
    */
	
	// 空间优化
    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];  // dp[i][j] : 用i个0和j个1可以实现的最大组合数
        
        for (String s: strs) {
        	int[] count =  countZerosAndOnes(s);
        	// 01背包 空间优化 -- 倒序
        	for (int i = m; i >= count[0]; i--) {
        		for (int j = n; j >= count[1]; j--) {
        			dp[i][j] = Math.max(dp[i][j], 1 + dp[i - count[0]][j - count[1]]);
        		}
        	}
        	
        	/*
        	for(int[] k: dp) {
        		for (int l: k) {
        			System.out.print(l +", ");
        		}
        		System.out.println();
        	}
        	System.out.println("=====");
        	*/
        }
        
        return dp[m][n];
    }
    
    private static int[] countZerosAndOnes(String s) {
    	int[] result = new int[2];
    	
    	for (int i = 0; i < s.length(); i++) {
    		result[s.charAt(i) - '0']++;
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		String[] test1 = {"10", "0001", "111001", "1", "0"}; 
		String[] test2 = {"10", "0", "1"}; 
		
		System.out.println(findMaxForm(test1, 5, 3));
		//System.out.println(findMaxForm(test2, 1, 1));
	}
}
