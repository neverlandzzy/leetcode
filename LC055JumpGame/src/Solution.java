
public class Solution {
	
	/**
	 * Given an array of non-negative integers, you are initially positioned at the first 
	 * index of the array.
	 * 
	 * Each element in the array represents your maximum jump length at that position.
	 * Determine if you are able to reach the last index.
	 * 
	 * For example:
	 * 	A = [2,3,1,1,4], return true.
	 * 	A = [3,2,1,0,4], return false.
	 */
	
	// Solution 1: Time: O(n), Space O(1)
	/*
    public static boolean canJump(int[] nums) {
    	
        int n = nums.length;
        int max = 0;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i] + i);

            if (max >= n - 1) {
                return true;
            }

            if (max <= i) {
                return false;
            }
        }

        return true;
    }
    */
	
	// Solution 2: DP: Time: O(n), Space O(1)
    public static boolean canJump(int[] nums) {
    	int maxReach = nums[0];
    	
    	for (int i = 1; i <nums.length; i++) {
    		
    		if (maxReach < i) {
    			return false;
    		}
    		
    		maxReach = Math.max(maxReach, i + nums[i]);
    		
    		if (maxReach >= nums.length-1) {
    			return true;
    		}
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
    	
    	int[] test1 = {2,3,1,1,4};
    	int[] test2 = {3,2,1,0,4};
    	int[] test3 = {2,0,0};
    	
		System.out.println(canJump(test1));
		System.out.println(canJump(test2));
		System.out.println(canJump(test3));
		
	}
}
