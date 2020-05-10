
public class Solution {
	/**
	 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	 * You are given a target value to search. If found in the array return its index, 
	 * otherwise return -1.
	 * You may assume no duplicate exists in the array.
	 * 
	 */

    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        
        if (target == nums[start]) {
            return start;
        }
        if (target == nums[end]) {
            return end;
        }
        
        return -1;
    }
	
	/*
    public static int search(int[] nums, int target) {
        int result = -1;
        int lo = 0, hi = nums.length-1;
        
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (target == nums[mid]) 
                return mid;

            if (nums[mid] > nums[lo]) {
                if (target <= nums[mid] && target >= nums[lo]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            } else {
                if (target >= nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid;
                }
            }
        }
        
        if (target == nums[lo]) 
            return lo;
            
        if (target == nums[hi]) 
            return hi;

       return result;
    }
    */
    public static void main(String[] args) {
		
    	int[] test = {4,5,6,7,1,2,3};
    	int[] test2 = {3,1};
    	
    	System.out.println(search(test, 3));
    	System.out.println(search(test2, 1));
	}
}
