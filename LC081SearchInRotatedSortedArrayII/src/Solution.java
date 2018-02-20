
public class Solution {
	/*
	 * Follow up for "Search in Rotated Sorted Array":
	 * 
	 * What if duplicates are allowed?
	 * 
	 * Would this affect the run-time complexity? How and why?
	 * 
	 * Write a function to determine if a given target is in the array.
	 * 
	 */
	 public static boolean search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] == target) {
                return true;
            }
            
            if (nums[mid] > nums[start]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[start]) {
                if (target <= nums[end] && target > nums[mid]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                start++;
            }
        }
        
        if (nums[start] == target) {
            return true;
        }
        
        if (nums[end] == target) {
            return true;
        }
        
        return false;
	 }
	
	/*
    public static boolean search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo < hi) {
        	int mid = (lo + hi) / 2;
        	
        	if (nums[mid] == target) {
        		return true;
        	}
        	
        	if (nums[mid] > nums[lo]) {
        		if (target < nums[mid] && target >= nums[lo]) {
        			hi = mid;
        		} else {
        			lo = mid+1;
        		}
        	} else if (nums[mid] < nums[lo]) {
        		if (target <= nums[hi] && target > nums[mid]) {
        			lo = mid+1;
        		} else {
        			hi = mid;
        		}
        	} else {
        		lo++;
        	}
        }
       
        if (nums[lo] == target) {
        	return true;
        }
        return false;
    }
    
    */
    
    public static void main(String[] args) {
		
    	//int[] test = {4,5,6,7,1,1,1,1,1};
    	int[] test = {4,5,6,7,1,2};
    	int[] test2 = {3,1,1};
    	
    	System.out.println(search(test, 2));
    	System.out.println(search(test, 6));
    	System.out.println(search(test2, 3));
	}
}
