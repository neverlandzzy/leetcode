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
        if (nums == null || nums.length <= 2) {
            return 0;
        }
        
        int n = nums.length;
        int result = nums[0] + nums[1] + nums[n - 1];
        
        for (int i = 0; i < n - 2; i++) {            
            int low = i + 1;
            int high = n - 1;
            
            while (low < high) {
                int sum = nums[i] + nums[low] + nums[high];
                if (sum == target) {
                    return target;
                } else if (sum > target) {
                    high--;
                } else {
                    low++;
                }

                if (Math.abs(sum - target) < Math.abs(result - target)) {
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
