
public class Solution {
	
	/*
	 * Given an integer array, find three numbers whose product is maximum and output the maximum product.
	 * 
	 * Example 1:
	 * Input: [1,2,3]
	 * Output: 6
	 * 
	 * Example 2:
	 * Input: [1,2,3,4]
	 * Output: 24
	 * Note:
	 * The length of the given array will be in range [3,104] and all elements are in the range [-1000, 1000].
	 * Multiplication of any three numbers in the input won't exceed the range of 32-bit signed integer.
	 */
	
	// 只需比较
	// 1. 最大的三个数乘积
	// 2. 最小的两个数 x 最大的数
	
	// Time: O(n) Space: O(1)
    public static int maximumProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        
        for (int i: nums) {
        	if (max1 < i) {
        		max3 = max2;
        		max2 = max1;
        		max1 = i;
        	} else if (max2 < i) {
        		max3 = max2;
        		max2 = i;
        	} else if (max3 < i) {
        		max3 = i;
        	}
        	
        	if (min1 > i) {
        		min2 = min1;
        		min1 = i;
        	} else if (min2 > i) {
        		min2 = i;
        	}
        }
        
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 2, 3};
		int[] test2 = {1, 2, 3, 4};
		
		System.out.println(maximumProduct(test1));
		System.out.println(maximumProduct(test2));
		
	}
}
