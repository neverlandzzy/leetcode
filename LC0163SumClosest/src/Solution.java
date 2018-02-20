import java.util.Arrays;


public class Solution {
	/*
	 * Given an array S of n integers, find three integers in S such that the sum is closest 
	 * to a given number, target. Return the sum of the three integers. You may assume that 
	 * each input would have exactly one solution.
	 * 
	 * For example, given array S = {-1 2 1 -4}, and target = 1.
	 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 * 
	 */
	
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length-1];
        
        for(int i = 0; i < nums.length-2; i++) {
         	
        	for(int lo = i+1, hi = nums.length-1; lo < hi; ) {
        		int sum = nums[i] + nums[lo] + nums[hi];
        		
        		if (sum == target) {
        			return target;
        		} else if (sum > target) { 
        			hi--;
        		} else {
        			lo++;
        		}
        		
            	if (Math.abs(sum-target) < Math.abs(result - target)) {
            		result = sum;
            	}
        	}
        	
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	int[] test = {-1,2,1,-4};
    	int target = 1;
    	
    	System.out.println(threeSumClosest(test, target));
    }

}
