
public class Solution {
	/*
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

}
