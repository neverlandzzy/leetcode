
public class Solution {
	/*
	 * You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. 
	 * For each integer, you should choose one from + and - as its new symbol.
	 * 
	 * Find out how many ways to assign symbols to make sum of integers equal to target S.
	 * 
	 * Example 1:
	 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
	 * Output: 5
	 * 
	 * Explanation: 
	 * -1+1+1+1+1 = 3
	 * +1-1+1+1+1 = 3
	 * +1+1-1+1+1 = 3
	 * +1+1+1-1+1 = 3
	 * +1+1+1+1-1 = 3
	 * 
	 * There are 5 ways to assign symbols to make the sum of nums be target 3.
	 * Note:
	 * 1. The length of the given array is positive and will not exceed 20.
	 * 2. The sum of elements in the given array will not exceed 1000.
	 * 3. Your output answer is guaranteed to be fitted in a 32-bit integer.
	 */
	
	// Solution 1: DFS (Brute-Force) Time - O(2^n) -- 每个元素有+/-两种情况 2*2*2*2... 
	/*
    public static int findTargetSumWays(int[] nums, int S) {
        return helper(nums, S, 0, 0);
    }
    
    private static int helper(int[] nums, int S, int sum, int pos) {
    	if (pos == nums.length) {
    		if (S == sum) {
    			return 1;
    		}
    		return 0;
    	} else {
    		return helper(nums, S, sum + nums[pos], pos + 1) + helper(nums, S, sum - nums[pos], pos + 1);
    	}
    }
    */
	
    // Solution 2: DFS + mem  Time: O(l * n) l: the range of sum 
    /*
    public static int findTargetSumWays(int[] nums, int S) {
    	Integer[][] cache = new Integer[nums.length + 1][2001];
        return helper(nums, S, 0, 0, cache);
        
    }
	
    private static int helper(int[] nums, int S, int sum, int pos, Integer[][] cache) {
    	if (cache[pos][sum + 1000] != null) {
    		return cache[pos][sum + 1000];
    	}
    	
    	if (pos == nums.length) {
    		if (S == sum) {
    			return 1;
    		}
    		return 0;
    	} else {
    		cache[pos][sum + 1000] = helper(nums, S, sum + nums[pos], pos + 1, cache) + helper(nums, S, sum - nums[pos], pos + 1, cache);
    		return cache[pos][sum + 1000];
    	}
    }
    */
	
	// Solution 3: 2D DP
	/*
	public static int findTargetSumWays(int[] nums, int S) {
		// dp[i][j] - 在index = i时，组成和为j的可能性个数。 
		int[][] dp = new int[nums.length][2001];
		
		dp[0][nums[0] + 1000] = 1;
		dp[0][-nums[0] + 1000] += 1;  // 当nums中的起始元素为0时，dp[0][1000] == 2, 因此要用+= e.g. [0,0,0,0,0,0,0,0,1]
		
		for (int i = 1; i < nums.length; i++) {
			for (int j = -1000; j <= 1000; j++) {
				if (dp[i - 1][j + 1000] > 0) {
					dp[i][j + 1000 + nums[i]] += dp[i - 1][j + 1000];
					dp[i][j + 1000 - nums[i]] += dp[i - 1][j + 1000];
				}
			}
		}
		return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
	}
	*/
	
	// Solution 4: 1D DP
	public static int findTargetSumWays(int[] nums, int S) {
		int[] dp = new int[2001];
		
		dp[nums[0] + 1000] = 1;
		dp[-nums[0] + 1000] += 1;
		
		for (int i = 1; i < nums.length; i++) {
			int[] tmp = new int[2001];
			
			for (int j = -1000; j <= 1000; j++) {
				if (dp[j + 1000] > 0) {
					tmp[j + 1000 + nums[i]] += dp[j + 1000];
					tmp[j + 1000 - nums[i]] += dp[j + 1000];
				}
			}
			dp = tmp;
		}
		
		return S > 1000 ? 0 : dp[S + 1000];
	}
	
    public static void main(String[] args) {
		int[] test = {1, 1, 1, 1, 1};
		
		System.out.println(findTargetSumWays(test, 3));
	}
}
