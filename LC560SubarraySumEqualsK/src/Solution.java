import java.util.HashMap;
import java.util.Map;


public class Solution {
	
	/**
	 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.
	 * 
	 * Example 1:
	 * Input:nums = [1,1,1], k = 2
	 * Output: 2
	 * Note:
	 * 	1. The length of the array is in range [1, 20,000].
	 * 	2. The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
	 */
	
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        
        int sum = 0;
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
        	sum += nums[i];
        	if (map.containsKey(sum - k)) {
        		result += map.get(sum - k);
        	}
        	
        	map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        	
        return result;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 1, 1};
		int[] test2 = {3, 4, 7, 2, -3, 1, 4, 2};
		System.out.println(subarraySum(test1, 2));
		System.out.println(subarraySum(test2, 7));
	}
}
