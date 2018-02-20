
public class Solution {
	/*
	 * Given an array nums of integers, you can perform operations on the array.
	 * 
	 * In each operation, you pick any nums[i] and delete it to earn nums[i] points. After, you must delete every element equal to nums[i] - 1 or nums[i] + 1.
	 * 
	 * You start with 0 points. Return the maximum number of points you can earn by applying such operations.
	 * 
	 * Example 1:
	 * Input: nums = [3, 4, 2]
	 * Output: 6
	 * Explanation: 
	 * Delete 4 to earn 4 points, consequently 3 is also deleted.
	 * Then, delete 2 to earn 2 points. 6 total points are earned.
	 * 
	 * Example 2:
	 * Input: nums = [2, 2, 3, 3, 3, 4]
	 * Output: 9
	 * Explanation: 
	 * Delete 3 to earn 3 points, deleting both 2's and the 4.
	 * Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
	 * 9 total points are earned.
	 * 
	 * Note:
	 * 
	 * The length of nums is at most 20000.
	 * Each element nums[i] is an integer in the range [1, 10000].
	 */
	
	// Similar to LC198
	// Solution 1: Time: O(n + m), Space: O(2m) -- n: length of nums, m: range of element
	/*
    public static int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int[] count = new int[10001];
        int[] dp = new int[10001];
        
        for (int i: nums) {
        	count[i]++;
        }       
        
        dp[1] = count[1];
        		
        for (int i = 2; i < 10001; i++) {
        	dp[i] = Math.max(dp[i - 1], dp[i - 2] + i * count[i]);
        }
        
        return dp[10000];
    }
    */
    // Solution 2: 空间优化 -- 本题意义不大 Time: O(n + m), Space: O(m)
	public static int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int[] count = new int[10001];
        
        for (int i: nums) {
        	count[i]++;
        }       
        
        int pre = 0;
        int cur = count[1];
        
        for (int i = 2; i < 10001; i++) {
        	int tmp = cur;
        	cur = Math.max(cur, pre + i * count[i]);
        	pre = tmp;
        }
        
        return cur;
	}
    
    public static void main(String[] args) {
		int[] test1 = {3, 4, 2};
		int[] test2 = {2, 2, 3, 3, 3, 4};
		int[] test3 = {3, 9997, 10000};
		
		System.out.println(deleteAndEarn(test1));
		System.out.println(deleteAndEarn(test2));
		System.out.println(deleteAndEarn(test3));
	}
}
