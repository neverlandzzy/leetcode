
public class Solution {
	/**
	 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous 
	 * subarrays. Write an algorithm to minimize the largest sum among these m subarrays.
	 * 
	 * Note:
	 * If n is the length of array, assume the following constraints are satisfied:
	 * 
	 * 1 ≤ n ≤ 1000
	 * 1 ≤ m ≤ min(50, n)
	 * 
	 * Examples:
	 * 
	 * Input:
	 * nums = [7,2,5,10,8]
	 * m = 2
	 * 
	 * Output:
	 * 18
	 * 
	 * Explanation:
	 * There are four ways to split nums into two subarrays.
	 * The best way is to split it into [7,2,5] and [10,8],
	 * where the largest sum among the two subarrays is only 18.
	 */
	
	// https://discuss.leetcode.com/topic/61324/clear-explanation-8ms-binary-search-java
    public static int splitArray(int[] nums, int m) {
        long max = 0;
        long sum = 0;
        
        for (int n: nums) {
        	max = Math.max(max, n);
        	sum += n;
        }
        
        if (m == 1) {
        	return (int)sum;
        }
        // 结果应该在最大值max和全部数字之和sum之间
        // 以max、sum为左右边界，用binary search 不断逼近这个值
        // valid() -- 若以mid为和， 是否可以将nums分成m个subarrays
        // 若valid返回false，说明若以mid为和，需要将nums分成多于m份，也就是mid取值偏小，因此将左边界右移: max = mid + 1;
        // 若valid返回true， 说明若以mid为最大和，nums分成m或小于m份，也就是mid可能偏大，因此将右边界左移: sum = mid;
        
        while (max < sum) {
        	long mid = max + (sum - max) / 2;
			// System.out.println(" mid = " + mid + " valid(nums, m, mid) = " + valid(nums, m, mid));
        	if (valid(nums, m, mid)) {
        		sum = mid;
        	} else {
        		max = mid + 1;
        	}
        }
        
        return (int)max;
    }
    
    private static boolean valid(int[] nums, int m, long sum) {
    	int counter = 1;
    	long total = 0;
    	
    	for (int n: nums) {
    		total += n;
    		if (total > sum) {
    			total = n;
    			counter++;
    			if (counter > m) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static void main(String[] args) {
		int[] test = {7,2,5,10,8};
		System.out.println(splitArray(test, 2));
	}
}
