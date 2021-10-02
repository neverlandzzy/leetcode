import java.util.HashMap;
import java.util.Map;


public class Solution {
	/**
	 * Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
	 * 
	 * Note:
	 * The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
	 * 
	 * Example 1:
	 * Given nums = [1, -1, 5, -2, 3], k = 3,
	 * return 4. (because the subarray [1, -1, 5, -2] sums to 3 and is the longest)
	 * 
	 * Example 2:
	 * Given nums = [-2, -1, 2, 1], k = 1,
	 * return 2. (because the subarray [-1, 2] sums to 1 and is the longest)
	 * 
	 * Follow Up:
	 * Can you do it in O(n) time?
	 */
	
    public static int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int result = 0;
        
        for (int i = 0; i < nums.length; i++) {
        	sum += nums[i];
        	int diff = sum - k;
        	if (map.containsKey(diff)) {
        		result = Math.max(result, i - map.get(diff));
        	}
        	if (!map.containsKey(sum)) {
        		map.put(sum, i);
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, -1, 5, -2, 3};
		int[] test2 = {-2, -1, 2, 1};
		
		System.out.println(maxSubArrayLen(test1, 3));
		System.out.println(maxSubArrayLen(test2, 1));
	}
}
