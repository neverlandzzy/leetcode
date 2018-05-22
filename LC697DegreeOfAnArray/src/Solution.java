import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/*
	 * Given a non-empty array of non-negative integers nums, the degree of this array is defined as the maximum frequency of any one of its elements.
	 * 
	 * Your task is to find the smallest possible length of a (contiguous) subarray of nums, that has the same degree as nums.
	 * 
	 * Example 1:
	 * Input: [1, 2, 2, 3, 1]
	 * Output: 2
	 * Explanation: 
	 * The input array has a degree of 2 because both elements 1 and 2 appear twice.
	 * Of the subarrays that have the same degree:
	 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
	 * The shortest length is 2. So return 2.
	 * 
	 * Example 2:
	 * Input: [1,2,2,3,1,4,2]
	 * Output: 6
	 * Note:
	 * 
	 * 1. nums.length will be between 1 and 50,000.
	 * 2. nums[i] will be an integer between 0 and 49,999.
	 */
	
    public static int findShortestSubArray(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int max = 0;
        
        for (int i = 0; i < nums.length; i++) {
        	if (!map.containsKey(nums[i])) {
        		map.put(nums[i], new ArrayList<>());
        	}
        	
        	map.get(nums[i]).add(i);
        	max = Math.max(max, map.get(nums[i]).size());
        }
        
        int result = Integer.MAX_VALUE;
        
        for (int key: map.keySet()) {
        	if (map.get(key).size() == max) {
        		List<Integer> list = map.get(key);
        		result = Math.min(result, list.get(list.size() - 1) - list.get(0) + 1);
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 2, 2, 3, 1};
		int[] test2 = {1, 2, 2, 3, 1, 4, 2};
		
		System.out.println(findShortestSubArray(test1));
		System.out.println(findShortestSubArray(test2));
	}
}
