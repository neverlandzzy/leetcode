import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/*
	 * Given an array A (index starts at 1) consisting of N integers: A1, A2, ..., AN and an integer B. The integer B denotes that 
	 * from any place (suppose the index is i) in the array A, you can jump to any one of the place in the array A indexed i+1, i+2, …, i+B 
	 * if this place can be jumped to. Also, if you step on the index i, you have to pay Ai coins. If Ai is -1, it means you can’t jump to 
	 * the place indexed i in the array.
	 * 
	 * Now, you start from the place indexed 1 in the array A, and your aim is to reach the place indexed N using the minimum coins. 
	 * You need to return the path of indexes (starting from 1 to N) in the array you should take to get to the place indexed N using minimum 
	 * coins.
	 * 
	 * If there are multiple paths with the same cost, return the lexicographically smallest such path.
	 * 
	 * If it's not possible to reach the place indexed N then you need to return an empty array.
	 * 
	 * Example 1:
	 * Input: [1,2,4,-1,2], 2
	 * Output: [1,3,5]
	 * 
	 * Example 2:
	 * Input: [1,2,4,-1,2], 1
	 * Output: []
	 * 
	 * Note:
	 * 1. Path Pa1, Pa2, ..., Pan is lexicographically smaller than Pb1, Pb2, ..., Pbm, if and only if at the first i where Pai and Pbi differ, 
	 *    Pai < Pbi; when no such i exists, then n < m.
	 * 2. A1 >= 0. A2, ..., AN (if exist) will in the range of [-1, 100].
	 * 3. Length of A is in the range of [1, 1000].
	 * 4. B is in the range of [1, 100].
	 */
	
	// https://leetcode.com/problems/coin-path/solution/
	//
	// Solution 1: DFS + mem Time: O(nB), Space: O(n)
	// next[i] = j -- 若从i跳到结尾，下一步跳到j会有最小cost
	/*
    public static List<Integer> cheapestJump(int[] A, int B) {
        int[] next = new int[A.length];
        long[] cache = new long[A.length];
        helper(A, B, 0, next, cache);
        
        List<Integer> result = new ArrayList<>();
        
        int i;
        for (i = 0; i < A.length && next[i] > 0; i = next[i]) {
        	result.add(i + 1);
        }
        
        if (i == A.length - 1 && A[i] >= 0) {
        	result.add(i + 1);
        } else {
        	return new ArrayList<>();
        }
        
        return result;
    }
    
    private static long helper(int[] A, int B, int i, int[] next, long[] cache) {
    	if (cache[i] > 0) {
    		return cache[i];
    	}
    	
    	if (i == A.length - 1 && A[i] >= 0) {
    		return A[i];
    	}
    	
    	long min = Integer.MAX_VALUE;
    	for (int j = i + 1; j <= i + B && j < A.length; j++) {
    		if (A[j] >= 0) {
    			long cost = A[i] + helper(A, B, j, next, cache);
    			if (cost < min) {
    				min = cost;
    				next[i] = j;
    			}
    		}
    	}
    	
    	cache[i] = min;
    	return min;
    }
    */
	
	// Solution 2: DP  Time: O(nB), Space: O(n)
	// next[i] = j -- 若从i跳到结尾，下一步跳到j会有最小cost
	// dp[i] 从i跳到结尾的最小cost --> 即solution 1中的cache[] 
	// 思路与1 一样，只是用dp[]存储1中递归的结果
	public static List<Integer> cheapestJump(int[] A, int B) {
		int n = A.length;
        int[] next = new int[n];
        long[] dp = new long[n];
        List<Integer> result = new ArrayList<>();
        
        for (int i = n - 2; i >= 0; i--) {
        	long min = Integer.MAX_VALUE;
        	
        	for (int j = i + 1; j <= i + B && j < n; j++) {
        		if (A[j] >= 0) {
        			long cost = A[i] + dp[j];
        			if (cost < min) {
        				min = cost;
        				next[i] = j;
        			}
        		}
        	}
        	dp[i] = min;
        }

        int i;
        for (i = 0; i < A.length && next[i] > 0; i = next[i]) {
        	result.add(i + 1);
        }
        
        if (i == A.length - 1 && A[i] >= 0) {
        	result.add(i + 1);
        } else {
        	return new ArrayList<>();
        }
        
        return result;
	}
	
    public static void main(String[] args) {
    	
    	int[] test = {1,2,4,-1,2};
		System.out.println(cheapestJump(test, 2));
		System.out.println(cheapestJump(test, 1));
	}
}
