
public class Solution {
	/*
	 * Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has 
	 * the maximum average value. And you need to output the maximum average value.
	 * 
	 * Example 1:
	 * Input: [1,12,-5,-6,50,3], k = 4
	 * Output: 12.75
	 * Explanation:
	 * when length is 5, maximum average value is 10.8,
	 * when length is 6, maximum average value is 9.16667.
	 * Thus return 12.75.
	 * Note:
	 * 1 <= k <= n <= 10,000.
	 * Elements of the given array will be in range [-10,000, 10,000].
	 * The answer with the calculation error less than 10^-5 will be accepted.
	 */
	
	// https://leetcode.com/articles/maximum-average-subarray-ii/
	// 1. 二分区间的上下边界分别是数组中的min, max；
	// 2. 在[min, max]中进行二分查找。当有长度至少为k的子数组和为mid时， 说明mid取小了，因此min = mid，否则mid取大了，因此，max = mid；不断逼近，直到mid的误差不超过1e-5；
	// 3. 我们要检查的是是否存在这样的子数组，使得 (a1+a2+a3...+aj)/j ≥ mid --> (a1+a2+a3...+aj)≥j∗mid --> (a1−mid)+(a2−mid)+(a3−mid)...+(aj−mid)≥0；
	// 4. 因此遍历nums时，检查sum += nums[i] - mid是否 >= 0; 当检查到前k个元素时，若sum >= 0，说明前k个元素满足上面公式，因此返回true，也就是mid取小了；
	// 5. 否则，继续检查剩下的元素。在剩下的元素中，我们希望检查是否存在一个区间[i, j] (j - i >= k)使得sumj−sumi≥0， 这样就说明i~j之间的元素（大于等于k个）和满足上面公式；
	// 6. 在检查时，用preSum记录前 i - k个元素的和，也就是距离当前元素k个距离的元素之和，用min记录preSum的最小值。只要当前sum - min >= 0， 则说明从j向前存在一个区间（大于
	//    k个元素），这个区间的和满足上面公式，因此返回true，否则返回false
	  
    public static double findMaxAverage(int[] nums, int k) {
        double max = Double.NEGATIVE_INFINITY;
        double min = Double.POSITIVE_INFINITY;
        
        for (int n: nums) {
        	max = Math.max(max, n);
            min = Math.min(min, n);
        }
        
        double mid = max;
        double preMid = min;
        
        while (Math.abs(mid - preMid) > 0.00001) {
        	preMid = mid;
        	mid = (min + max) / 2;
        	if (binaryCheck(nums, k, mid)) {
        		min = mid;
        	} else {
        		max = mid;
        	}
        }
        
        return mid;
    }
    
    private static boolean binaryCheck(int[] nums, int k, double mid) {
    	double sum = 0;
    	double preSum = 0;
    	double min = 0;
    	
    	for (int i = 0; i < k; i++) {
    		sum += nums[i] - mid;
    	}
    	
    	if (sum >= 0) {
    		return true;
    	}
    	
    	for (int i = k; i < nums.length; i++) {
    		sum += nums[i] - mid;
    		preSum += nums[i - k] - mid;
    		min = Math.min(min, preSum);
    		
    		if (sum >= min) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1,12,-5,-6,50,3};
		//int[] test2 = {0, 1, 1, 3, 3};
		
		System.out.println(findMaxAverage(test1, 4));
		//System.out.println(findMaxAverage(test2, 4));
	}
}
