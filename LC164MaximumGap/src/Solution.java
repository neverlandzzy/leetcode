import java.util.Arrays;


public class Solution {
	/*
	 * Given an unsorted array, find the maximum difference between the successive 
	 * elements in its sorted form.
	 * 
	 * Try to solve it in linear time/space.
	 * 
	 * Return 0 if the array contains less than 2 elements.
	 * 
	 * You may assume all elements in the array are non-negative integers and fit in the 32-bit 
	 * signed integer range.
	 */
	
	// https://discuss.leetcode.com/topic/5999/bucket-sort-java-solution-with-explanation-o-n-time-and-space 
	// 桶排序
	// 对于一个N个元素的数组, 最大的gap >=  ceiling[(max - min) / (N - 1)].
	// 因此，建立N - 1个桶，每个桶的size = ceiling[(max - min) / (N - 1)] ---> 这样保证了桶内元素的gap小于要求的最大gap，也就是最大gap只会发生在两个不同桶里的元素之间
	// 因此，只需记录每个桶里最大和最小的元素即可
    public static int maximumGap(int[] nums) {
    	if (nums == null || nums.length < 2) {
    		return 0;
    	}
    	
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for (int i: nums) {
        	max = Math.max(max, i);
        	min = Math.min(min, i);
        }
        
        if (min == max) {
        	return 0;
        }
        
        int stepSize = (int) Math.ceil((double)(max - min) / (n - 1));
        
        int[] maxBucket = new int[n];
        int[] minBucket = new int[n];
        
        Arrays.fill(maxBucket, Integer.MIN_VALUE);
        Arrays.fill(minBucket, Integer.MAX_VALUE);
        
        for (int i: nums) {
        	int index = (i - min) / stepSize;
        	maxBucket[index] = Math.max(maxBucket[index], i);
        	minBucket[index] = Math.min(minBucket[index], i);
        }
       
        int prev = min;
        int maxGap = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
        	if (maxBucket[i] != Integer.MIN_VALUE) {
        		maxGap = Math.max(maxGap, minBucket[i] - prev);
            	prev = maxBucket[i];
        	}
        	
        }
        
        maxGap = Math.max(maxGap, max - prev);
        
        return maxGap;
    }
    
    public static void main(String[] args) {
		//int[] test = {4,7};
		int[] test = {6, 1, 8, 4, 11, 3};
		
		System.out.println(maximumGap(test));
	}
}
