
public class Solution {
	/*
	 * Given an array consisting of n integers, find the contiguous subarray of given length k that has the maximum average value. 
	 * And you need to output the maximum average value.
	 * 
	 * Example 1:
	 * Input: [1,12,-5,-6,50,3], k = 4
	 * Output: 12.75
	 * Explanation: Maximum average is (12-5-6+50)/4 = 51/4 = 12.75
	 * Note:
	 * 1 <= k <= n <= 30,000.
	 * Elements of the given array will be in the range [-10,000, 10,000].
	 */
	
    public static double findMaxAverage(int[] nums, int k) {
    	double result = Double.NEGATIVE_INFINITY;
    	
    	int i = 0;
    	int j = 0;
    	int sum = 0;
    	int n = nums.length;
    	
    	while (j < n) {
    		while (j < k && j < n) {
    			sum += nums[j];
    			j++;
    			continue;
    		}
    		
    		result = Math.max(result, (double)sum / k);
    		if (j < n) {
	    		sum -= nums[i];
	    		sum += nums[j];
	    		i++;
	    		j++;
    		}
    	}
    	result = Math.max(result, (double)sum / k);
    	return result;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1,12,-5,-6,50,3};
		int[] test2 = {0, 1, 1, 3, 3};
		
		System.out.println(findMaxAverage(test1, 4));
		System.out.println(findMaxAverage(test2, 4));
	}
}
