import java.util.HashMap;
import java.util.Map;


public class Solution {
	
	/*
	 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
	 * 
	 * Example 1:
	 * Input: [0,1]
	 * Output: 2
	 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
	 * 
	 * Example 2:
	 * Input: [0,1,0]
	 * Output: 2
	 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
	 * 
	 * Note: The length of the given binary array will not exceed 50,000.
	 */
    public static int findMaxLength(int[] nums) {
    	
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	
        Map<Integer, Integer> map = new HashMap<>();
        
        int result = 0;
        int sum = 0;
        map.put(0, -1);
        
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] == 0) {
        		sum -= 1;
        	} else {
        		sum += 1;
        	}
        	
        	if (!map.containsKey(sum)) {
        		map.put(sum, i);
        	} else {
        		result = Math.max(result, i - map.get(sum));
        	}
        	
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test1 = {0, 1};
		int[] test2 = {0, 1, 0};
		
		System.out.println(findMaxLength(test1));
		System.out.println(findMaxLength(test2));
	}
}
