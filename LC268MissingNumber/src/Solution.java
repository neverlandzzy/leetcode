
public class Solution {
	/**
	 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, 
	 * find the one that is missing from the array.
	 * 
	 * For example,
	 * Given nums = [0, 1, 3] return 2.
	 * 
	 * Note:
	 * Your algorithm should run in linear runtime complexity. Could you implement it 
	 * using only constant extra space complexity?
	 */
	
	// https://www.lijinma.com/blog/2014/05/29/amazing-xor/
	// 交换律： a ^ b = b ^ a
	// 结合律： a ^ (b ^ c) = (a ^ b) ^ c
	
	public static  int missingNumber(int[] nums) {
		int result = nums.length;
		
		for (int i = 0; i < nums.length; i++) {
			result ^= i ^ nums[i];
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] test1 = {1,2,3,4,5};
		int[] test2 = {0,1,3};
		int[] test3 = {0};
		int[] test4 = {1,0};
		
		System.out.println(missingNumber(test1));
		System.out.println(missingNumber(test2));
		System.out.println(missingNumber(test3));
		
	}
}
