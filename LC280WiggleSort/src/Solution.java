
public class Solution {
	/*
	 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
	 * 
	 * Example:
	 * 
	 * Input: nums = [3,5,2,1,6,4]
	 * Output: One possible answer is [3,5,1,6,2,4]
	 */
	
    public static void wiggleSort(int[] nums) {
        boolean less = true;
        		
        for (int i = 0; i < nums.length - 1; i++) {
        	if (less) {
        		if (nums[i] > nums[i + 1]) {
        			swap(nums, i, i + 1);
        		}
        	} else {
        		if (nums[i] < nums[i + 1]) {
        			swap(nums, i, i + 1);
        		}
        	}
        	
        	less = !less;
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
    
    public static void main(String[] args) {
		int[] nums = {3,5,2,1,6,4};
		wiggleSort(nums);
		
		for (int i: nums) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
