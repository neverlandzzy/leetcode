import java.util.HashMap;
import java.util.Map;


public class Solution {
	/**
	 * Given a list of non-negative numbers and a target integer k, write a function to check if the array has 
	 * a continuous subarray of size at least 2 that sums up to the multiple of k, that is, sums up to n*k where n is also an integer.
	 * 
	 * Example 1:
	 * Input: [23, 2, 4, 6, 7],  k=6
	 * Output: True
	 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
	 * 
	 * Example 2:
	 * Input: [23, 2, 6, 4, 7],  k=6
	 * Output: True
	 * Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
	 * 
	 * Note:
	 * 1. The length of the array won't exceed 10,000.
	 * 2. You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
	 */
	
	// https://leetcode.com/problems/continuous-subarray-sum/solution/
	public static boolean checkSubarraySum(int[] nums, int k) {
        // map的key是当前sum%k
		// 当遍历数组过程中，发现两个相同的key时，说明有两个不同位置的sum有相同的sum%k，那么这两个sum位置之间的数字之和肯定可以被k整除，因此返回true
		int sum = 0;
		Map<Integer, Integer> map = new HashMap<>();
		
		// for case: nums = [0, 0] k = 0
		map.put(0, -1);
		
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			if (k != 0) {
				sum = sum % k;
			}

			if (map.containsKey(sum)) {
				
				//a continuous subarray of size at least 2 
				if (i - map.get(sum) > 1) {
					//System.out.println(map);
					return true;
				}
			} else {
				map.put(sum, i);
			}
		}
		//System.out.println(map);
		return false;
	}
	
	public static void main(String[] args) {
		int[] test = {23, 2, 4, 6, 7};
		//int[] test = {0, 1, 0};
		System.out.println(checkSubarraySum(test, 6));
	}
}
