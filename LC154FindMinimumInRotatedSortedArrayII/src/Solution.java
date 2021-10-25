
public class Solution {
	/**
	 * Follow up for "Find Minimum in Rotated Sorted Array":
	 * 
	 * What if duplicates are allowed?
	 * 
	 * Would this affect the run-time complexity? How and why?
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 * 
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * 
	 * Find the minimum element.
	 * 
	 * The array may contain duplicates.
	 */
	
	// worst case run time will be O(n)
    public static int findMin(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        
        if (nums[0] < nums[nums.length - 1]) {
            return nums[0];
        }
        
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] > nums[end]) {
                start = mid;
            } else if (nums[mid] < nums[end]) {
                end = mid;
            } else {
                end--;
            }
        }
        
        return Math.min(nums[start], nums[end]);        
    }
    
    public static void main(String[] args) {
		int[] test = {3, 3, 1};
		
		System.out.println(findMin(test));
	}
}
