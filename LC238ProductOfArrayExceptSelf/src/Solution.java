
public class Solution {
	/**
	 * Given an array of n integers where n > 1, nums, return an array output such that 
	 * output[i] is equal to the product of all the elements of nums except nums[i].
	 * 
	 * Solve it without division and in O(n).
	 * 
	 * For example, given [1,2,3,4], return [24,12,8,6].
	 * 
	 * Follow up:
	 * Could you solve it with constant space complexity? 
	 * (Note: The output array does not count as extra space for the purpose of space complexity 
	 * analysis.)


	 */
	
    public static int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] result = new int[n];

		int prod = 1;
		result[0] = 1;

		for (int i = 1; i < n; i++) {
			prod *= nums[i - 1];
			result[i] = prod;
		}

		prod = 1;

		for (int i = n - 2; i >= 0; i--) {
			prod *= nums[i + 1];
			result[i] *= prod;
		}

		return result;
    }
    
    public static void main(String[] args) {
		//int[] test = {1,2,3,4};
		int[] test = {9, 0, -2};
		
		int[] res = productExceptSelf(test);
		
		for (int i: res) {
			System.out.print(i + ",");
		}
	}
}
