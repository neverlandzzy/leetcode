import java.util.Arrays;


public class MinimumIncrements {
	/*
	 * unique integer in array and return sum of them
	 * 给一个int[], 找出所有之中dup的数++，直到所有的数都不一样了，求最小的sum值
	 */
	
	public static int minSum(int[] nums) {
		Arrays.sort(nums);
		
		int sum = nums[0];
		int pre = nums[0];
		
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] <= pre) {
				pre += 1;
				sum += pre;
			} else {
				sum += nums[i];
				pre = nums[i];
			}
		}
		
		return sum;
	}
	
	public static void main(String[] args) {
		int[] nums1 = {2, 2, 3, 5, 6};
		int[] nums2 = {20, 20};
		int[] nums3 = {3, 4, 6, 8};
		System.out.println(minSum(nums1));
		System.out.println(minSum(nums2));
		System.out.println(minSum(nums3));
	}
}
