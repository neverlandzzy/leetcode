
public class Solution {
	/**
	 * Given a sorted array of integers, find the starting and ending position of a given target value.
	 * 
	 * Your algorithm's runtime complexity must be in the order of O(log n).
	 * If the target is not found in the array, return [-1, -1].
	 * 
	 * For example,
	 * Given [5, 7, 7, 8, 8, 10] and target value 8,
	 * return [3, 4]
	 * 
	 */
	
    public static int[] searchRange(int[] nums, int target) {
       /* Solution 1. Worst case, O(n) = n;
    	int lo = 0, hi = nums.length - 1;
        int[] result = {-1, -1};
        
        if (nums.length == 1) {
        	if (nums[0] == target) {
        		result[0] = 0;
        		result[1] = 0;
        		return result;
        	} else {
        		result[0] = -1;
        		result[1] = -1;
        		return result;
        	}
        }
        while (lo < hi) {
        	int mid = (lo + hi)/2;
        	
        	if (nums[mid] == target) {
        		int left = mid;
        		int right = mid;
        		while (left >= 0 && nums[left] == target) {
        			left--;
        		}
        		while (right <= nums.length-1 && nums[right] == target) {
        			right++;
        		}
        		
        		result[0] = left+1;
        		result[1] = right-1;
        		return result;
        	}
        	
        	if (nums[mid] > target) {
        		hi = mid;
        	} else {
        		lo = mid + 1;
        	}
        }
        
        if (nums[lo] == target) {
        	result[0] = lo;
        	result[1] = lo;
        	
        }
        return result;
        */
    	
    	/*
    	 * Solution 2 O(logN)
    	 */
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }
        
        
        int start = 0; 
        int end = nums.length - 1;
        int left = -1;
        int right = -1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[start] == target) {
            left = start;
        } else if (nums[end] == target) {
            left = end;
        }  else {
            return new int[] {-1, -1};
        }
        
        start = 0;
        end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[end] == target) {
            right = end;
        } else if (nums[start] == target) {
            right = start;
        }
        
        return new int[]{left, right};
		
    }
    
    public static void main(String[] args) {
    	int[] test = {5, 7, 7, 8, 8, 10};
		//int[] test = {5, 7, 7, 8, 8, 10};
    	//int[] test = {5, 7, 7, 8, 8};
    	//int[] test = {8, 8};
    	//int[] test = {1};
    	//int[] test = {1, 4};
		int[] result = searchRange(test, 8);
    	
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
}
