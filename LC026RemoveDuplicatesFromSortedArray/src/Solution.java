
public class Solution {
	/**
	 * Given a sorted array, remove the duplicates in place such that each element appear only once 
	 * and return the new length.
	 * 
	 * Do not allocate extra space for another array, you must do this in place with constant memory.
	 * 
	 * For example,
	 * Given input array A = [1,1,2],
	 * Your function should return length = 2, and A is now [1,2].
	 * 
	 */
	public static void main(String[] args) {
		int[] test = {1,2,3,3};
		System.out.println(removeDuplicates(test));		
	}
	
	// Solution 1:
	/* 
	public static int removeDuplicates(int[] A) {
		
	
		if (A.length == 0) {
			return 0;
		}
		
		int i = 0;
		int j = 1;
		int tmp = 1;
		
		while (j <= A.length-1) {
			while (j <= A.length-1 && A[i] == A[j]) {
				j++;
				i++;
			}
			
			if(j == A.length) {
				break;
			}
			
			A[tmp] = A[j];
			tmp++;
			i++;
			j++;
		}
		
		return tmp;
    }
    */
	// Solution 2:
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int pos = 1;
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[pos - 1]) {
                nums[pos++] = nums[i];
            }
        }
        
        return pos;
    }
    
    // Solution 3:
    /*
    public static int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        while (j < n) {
            nums[i] = nums[j];
            j++;
            
            while (j < n && nums[j] == nums[i]) {
                j++;
            }  
            
            i++;
        }
        
        return i;
    }
    */
    
    // Solution 4:
    /*
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }   
        
        int n = nums.length;
        int index = 0;
        int i = 0;
        
        while (i < n) {
            if (nums[i] != nums[index]) {
                index++;
                swap(nums, i, index);
            } 
            i++;
        }
        
        return index + 1;
    }
    
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    */
    
    /*
    public static int removeDuplicates(int[] nums) {
        int index = 0;
        int i = 0;
        int n = nums.length;
        
        while (i < n) {
            if (nums[index] != nums[i]) {
                index++;
                nums[index] = nums[i];
            }
            i++;
        }
        
        return index + 1;
    }
    */

}
