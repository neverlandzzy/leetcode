
public class Solution {

	/**
	 * In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.
	 * 
	 * Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.
	 * 
	 * Return the result as a list of indices representing the starting position of each interval (0-indexed). 
	 * If there are multiple answers, return the lexicographically smallest one.
	 * 
	 * Example:
	 * Input: [1,2,1,2,6,7,5,1], 2
	 * Output: [0, 3, 5]
	 * Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
	 * We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
	 * 
	 * Note:
	 * 	1. nums.length will be between 1 and 20000.
	 * 	2. nums[i] will be between 1 and 65535.
	 * 	3. k will be between 1 and floor(nums.length / 3).
	 */
	
	// https://discuss.leetcode.com/topic/105577/c-java-dp-with-explanation-o-n/16
	// 将整个array分为三部分：[0, i - 1], [i, i + k - 1], [i + k, n - 1]; ( k <= i <= n - 2k)
	// 扫描中间部分，从而分别得到左右两个部分的最大值， 
	// left[i]:  the starting index of the max sum subarray before i
	// right[i] stores the starting index of the max sum subarray after i
	
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
    	
        int n = nums.length;
        int[] sum   = new int[n];
        
        // left[i] stores the starting index of the max sum subarray before i
        // right[i] stores the starting index of the max sum subarray after i       
        int[] left  = new int[n];
        int[] right = new int[n];
        int[] result = new int[3];
        
        sum[0] = nums[0];
        for (int i = 1; i < n; i++) {
        	sum[i] = sum[i - 1] + nums[i];
        }
        
        int max = Integer.MIN_VALUE;
        
        // 更新left[i]，即the starting index of the max sum subarray(size = k) before i, 从sum[0 ~ k-1]开始，到sum[n-k ~ n-1]截止
        for (int i = k - 1; i < n; i++) {
        	int leftSum = sum[i] - sum[i - k + 1] + nums[i - k + 1]; // sum from (i - k + 1) to i
        	if (leftSum > max) {
        		max = leftSum;
        		left[i] = i - k + 1;
        	} else {
        		left[i] = left[i - 1];
        	}
        }

        max = Integer.MIN_VALUE;
        
        // 更新right[i]，即the starting index of the max sum subarray(size = k) after i, 从sum[n-k ~ n-1]开始，到sum[0 ~ k-1]截止
        for (int i = n - k; i >= 0; i--) {
        	int rightSum = sum[i + k - 1] - sum[i] + nums[i]; // sum from i to (i + k - 1)
        	if (rightSum >= max) {
        		max = rightSum;
        		right[i] = i;
        	} else {
        		right[i] = right[i + 1];
        	}
        }
        // printArray(left);
        // printArray(right);
        max = Integer.MIN_VALUE;
        // 扫描中间部分， 即i为中间一段子数组的起始index；于是left[i - 1]和right[i + k]则为对应当前i的左右子数组最大值的起始值
        for (int i = k; i <= n - 2 * k; i++) {
        	int leftIndex = left[i - 1];
        	int rightIndex = right[i + k];
        	
        	int totalSum = sum[leftIndex + k - 1] - sum[leftIndex] + nums[leftIndex];  // 左段子数组的最大和(起始index为leftIndex)；
        	totalSum += sum[i + k - 1] - sum[i] + nums[i]; //中段子数组的最大和(起始index为i)
        	totalSum += sum[rightIndex + k - 1] - sum[rightIndex] + nums[rightIndex]; // 右段子数组的最大和(起始index为rightIndex)；
        	
        	if (totalSum > max) {
        		max = totalSum;
        		result[0] = leftIndex;
        		result[1] = i;
        		result[2] = rightIndex;
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1,2,1,2,6,7,5,1};
		int[] test2 = {1,2,1,2,1,2,1,2,1};

		int[] result1 = maxSumOfThreeSubarrays(test1, 2);
		int[] result2 = maxSumOfThreeSubarrays(test2, 2);

		printArray(result1);
		printArray(result2);
	}

	private static void printArray(int[] nums) {
    	for (int i: nums) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
