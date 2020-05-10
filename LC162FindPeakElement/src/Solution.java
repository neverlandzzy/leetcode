
public class Solution {
	/**
	 * A peak element is an element that is greater than its neighbors.
	 * 
	 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
	 * 
	 * The array may contain multiple peaks, in that case return the index to any one of 
	 * the peaks is fine.
	 * 
	 * You may imagine that num[-1] = num[n] = -∞.
	 * 
	 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function 
	 * should return the index number 2.
	 */

	public static int findPeakElement(int[] nums) {
		if (nums == null || nums.length <= 1) {
			return 0;
		}

		int start = 0;
		int end = nums.length - 1;

		while (start + 1 < end) {
			int mid = start + (end - start) / 2;

			if (nums[mid + 1] > nums[mid]) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (nums[start] > nums[end]) {
			return start;
		} else {
			return end;
		}
	}

	/*
    public static int findPeakElement(int[] nums) {
    	int lo = 0;
    	int hi = nums.length - 1;
    	
    	while (lo <= hi) {
    		int mid = lo + (hi - lo) / 2;
    		
    		if (mid != 0 && nums[mid] < nums[mid - 1]) {
    			hi = mid;
    		} else if (mid != nums.length - 1 && nums[mid] < nums[mid + 1]) {
    			lo = mid + 1;
    		} else {
    			return mid;
    		}
    	}
    	
    	return -1;
        
    }
    */
	/*
    public static int findPeakElement(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            
            if ((mid == 0 || nums[mid] > nums[mid - 1]) && (mid == n - 1 || nums[mid] > nums[mid + 1])) {
                return mid;
            }
            
            if (mid == 0 || nums[mid] > nums[mid - 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        
        return start;
    }
    */
    /*
    public static int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2; 
            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                end = mid;
            } else if (mid < nums.length - 1 && nums[mid] < nums[mid + 1]) {
                start = mid; 
            } else {
                return mid;
            }
        }
        
        return nums[start] < nums[end] ? end : start;
    }
    */
    public static void main(String[] args) {
		int[] test1 = {1,2,3,1};
		int[] test2 = {1,2,3,4};
		int[] test3 = {4,3,2,1};
		
		System.out.println(findPeakElement(test1));
		System.out.println(findPeakElement(test2));
		System.out.println(findPeakElement(test3));
		
	}
}
