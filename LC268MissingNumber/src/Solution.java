
public class Solution {
	/*
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
	
	public static  int missingNumber(int[] nums) {
		int xor = 0;
		int i = 0;
		
		for (; i < nums.length; i++) {
			xor = xor ^ i ^ nums[i];
		}
		
		return xor ^ i;
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
