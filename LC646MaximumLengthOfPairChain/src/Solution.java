import java.util.Arrays;
import java.util.Comparator;


public class Solution {
	/*
	 * You are given n pairs of numbers. In every pair, the first number is always smaller than the second number.
	 * 
	 * Now, we define a pair (c, d) can follow another pair (a, b) if and only if b < c. Chain of pairs can be formed in this fashion.
	 * 
	 * Given a set of pairs, find the length longest chain which can be formed. You needn't use up all the given pairs. You can select pairs in any order.
	 * 
	 * Example 1:
	 * Input: [[1,2], [2,3], [3,4]]
	 * Output: 2
	 * Explanation: The longest chain is [1,2] -> [3,4]
	 * 
	 * Note:
	 * The number of given pairs will be in the range [1, 1000].
	 */
	
	// https://leetcode.com/problems/maximum-length-of-pair-chain/solution/
	// Solution 1: DP Time: O(n^2) 
	/*
    public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
        	public int compare(int[] p1, int[] p2) {
        		return p1[0] - p2[0];
        	}
        });
        
        int n = pairs.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        int result = dp[0];
        
        for (int i = 1; i < n; i++) {
        	for (int j = 0; j < i; j++) {
        		if (pairs[i][0] > pairs[j][1]) {
        			dp[i] = Math.max(dp[i], dp[j] + 1);
        		}
        	}
        	result = Math.max(result, dp[i]);
        }
        
        return result;
    }
    */
	
    // Solution 2: Greedy Time: O(nlogn) -- sorting
	// 按ending 排序后，一个个加就行
	// 不能按starting 排序： 反例[[-6,9],[1,6],[8,10],[-1,4],[-6,-2],[-9,8],[-5,3],[0,3]]
	public static int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
        	public int compare(int[] p1, int[] p2) {
        		return p1[1] - p2[1];
        	}
        });
        
        int result = 0;
        int cur = Integer.MIN_VALUE;
        
        for (int[] pair: pairs) {
        	if (cur < pair[0]) {
        		cur = pair[1];
        		result++;
        	}
        }
        
        return result;
	}
	
    public static void main(String[] args) {
		int[][] test1 = {{1, 2}, {2, 3}, {3, 4}};
		
		System.out.println(findLongestChain(test1));
	}
}
