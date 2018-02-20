import java.util.HashMap;


public class Solution {
	/*
	 * Given an integer array with all positive numbers and no duplicates, find the number of possible combinations 
	 * that add up to a positive integer target.
	 * 
	 * Example:
	 * 
	 * nums = [1, 2, 3]
	 * target = 4
	 * 
	 * The possible combination ways are:
	 * (1, 1, 1, 1)
	 * (1, 1, 2)
	 * (1, 2, 1)
	 * (1, 3)
	 * (2, 1, 1)
	 * (2, 2)
	 * (3, 1)
	 * 
	 * Note that different sequences are counted as different combinations.
	 * 
	 * Therefore the output is 7.
	 * 
	 * Follow up:
	 * What if negative numbers are allowed in the given array?
	 * How does it change the problem?
	 * What limitation we need to add to the question to allow negative numbers? 
	 */
	
	// Follow up: we need to limit the length of combination, otherwise the loop will not stop. 
	// e.g. [1, -1, 2] target = 2
	// we can have: [1, -1, 2], [1, -1, 1, -1, 2], [1, -1, 1, -1, 1, -1, 2]...
	// we need to use another HashMap to record the length of combination 
	// https://discuss.leetcode.com/topic/52290/java-follow-up-using-recursion-and-memorization
	

    public static int combinationSum4(int[] nums, int target) {
    	HashMap<Integer, Integer> map = new HashMap<>();
    	
    	return helper(nums, target, map);
    }
    
    private static int helper(int[] nums, int target, HashMap<Integer, Integer> map) {
    	if (nums == null || target < 0) {
    		return 0;
    	}
        
        if (target == 0) {
        	return 1;
        }
        
        if (map.containsKey(target)) {
        	return map.get(target);
        }
        
        int result = 0;
        
        for (int i = 0; i < nums.length; i++) {
        	result += helper(nums, target - nums[i], map);
        }
        
        map.put(target, result);
        return result;
    }
    
    // brute-force
	/*
    public static int combinationSum4(int[] nums, int target) {
        return helper(nums, target);
    }
    
    private static int helper(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        
        if (target < 0) {
            return 0;
        }
        
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result += helper(nums, target - nums[i]);
        }
        
        return result;
    }
	*/
   
    public static void main(String[] args) {
		int[] test = {1, 2, 3};
		System.out.println(combinationSum4(test, 32));
	}
}
