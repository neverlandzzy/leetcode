
public class Solution {
	/*
	 * Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.
	 * 
	 * Example 1:
	 * Input:
	 * A: [1,2,3,2,1]
	 * B: [3,2,1,4,7]
	 * Output: 3
	 * 
	 * Explanation: 
	 * The repeated subarray with maximum length is [3, 2, 1].
	 * Note:
	 * 1 <= len(A), len(B) <= 1000
	 * 0 <= A[i], B[i] < 100
	 */
	
	// Solution 1: 2D - DP
	
    public static int findLength1(int[] A, int[] B) {
        // dp[i][j] be the longest common prefix of A[i:] and B[j:]
    	int m = A.length; 
    	int n = B.length;
    	
    	int[][] dp = new int[m][n];
    	dp[m - 1][n - 1] = A[m - 1] == B[n - 1] ? 1 : 0;
    	int result = dp[m - 1][n - 1];
    	
    	for (int i = m - 2; i >= 0; i--) {
    		dp[i][n - 1] = A[i] == B[n - 1] ? 1 :0;
    		result = Math.max(result, dp[i][n - 1]);
    	}
    	
    	for (int j = n - 2; j >= 0; j--) {
    		dp[m - 1][j] = A[m - 1] == B[j] ? 1 :0;
    		result = Math.max(result, dp[m - 1][j]);
    	}
    	
    	for (int i = m - 2; i >= 0; i--) {
    		for (int j = n - 2; j >= 0; j--) {
    			if (A[i] == B[j]) {
    				dp[i][j] = dp[i + 1][j + 1] + 1;
    			}
    			
    			result = Math.max(result, dp[i][j]);
    		}
    	}
    	
    	return result;
    }
    
    // Solution 2: 1D - DP
	
	public static int findLength(int[] A, int[] B) {
    	int m = A.length; 
    	int n = B.length;
    	if (m < n) {
    		return findLength(B, A);
    	}
    	
    	int[] dp = new int[n];
    	dp[n - 1] = A[m - 1] == B[n - 1] ? 1 : 0;
    	
    	int result = dp[n - 1];
    	
    	for (int i = n - 2; i >= 0; i--) {
    		if (A[m - 1] == B[i]) {
    			dp[i] = 1;
    			result = Math.max(result, dp[i]);
    		}
    	}

    	for (int i = m - 2; i >= 0; i--) {   
    		int pre = dp[n - 1];
    		dp[n - 1] = A[i] == B[n - 1] ? 1 : 0;  
    		
    		for (int j = n - 2; j >= 0; j--) {
    			int cur = dp[j];
    			if (A[i] == B[j]) {
    				dp[j] = pre + 1;
    			} else  {
    				dp[j] = 0;
    			}
    		
    			pre = cur;
    			result = Math.max(result, dp[j]);
    		}
    	}
    	
    	return result;
	}
	
    public static void main(String[] args) {
		int[] test1 = {1, 2, 3, 2, 1};
		int[] test2 = {3, 2, 1, 4, 7};
		
		int[] test3 = {1};
		int[] test4 = {1};
		
		System.out.println(findLength(test1, test2));
		System.out.println(findLength(test3, test4));
		
		System.out.println(findLength1(test3, test4));
	}
}
