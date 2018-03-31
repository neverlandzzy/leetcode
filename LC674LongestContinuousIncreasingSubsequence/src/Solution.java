
public class Solution {
	/*
	 * Given an unsorted array of integers, find the length of longest continuous increasing subsequence (subarray).
	 * 
	 * Example 1:
	 * Input: [1,3,5,4,7]
	 * Output: 3
	 * Explanation: The longest continuous increasing subsequence is [1,3,5], its length is 3. 
	 * Even though [1,3,5,7] is also an increasing subsequence, it's not a continuous one where 5 and 7 are separated by 4. 
	 * 
	 * Example 2:
	 * Input: [2,2,2,2,2]
	 * Output: 1
	 * Explanation: The longest continuous increasing subsequence is [2], its length is 1. 
	 * Note: Length of the array will not exceed 10,000.
	 */
	
    public static int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return 0;
        }
        
        int result = 1;
        int i = 0; 
        int j = 1;
        int n = nums.length;
        
        while (j < n) {
        	if (nums[j] > nums[j - 1]) {
        		result = Math.max(result, j - i + 1);
        		j++;
        	} else {
        		i = j;
        		j++;
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1, 3, 5, 4, 7};
		int[] test2 = {2, 2, 2, 2, 2};
		
		System.out.println(findLengthOfLCIS(test1));
		System.out.println(findLengthOfLCIS(test2));
	}
}
