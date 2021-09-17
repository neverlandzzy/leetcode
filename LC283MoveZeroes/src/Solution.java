
public class Solution {
	/**
	 * Given an array nums, write a function to move all 0's to the end of 
	 * it while maintaining the relative order of the non-zero elements.
	 * 
	 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, 
	 * nums should be [1, 3, 12, 0, 0].
	 * 
	 * Note:
	 * You must do this in-place without making a copy of the array.
	 * Minimize the total number of operations.
	 */
	
	public static void moveZeroes(int[] nums) {
        int n = nums.length;
        
        int index = 0;
        int i = 0;
        
        while (i < n) {
            if (nums[i] != 0) {
                swap(nums, i, index);
                index++;
            }
            
            // 减少swap次数的优化 ?
            while (index < i && nums[index] != 0) {
                index++;
            }
            
            i++;
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    
	}
	
	/*
	// 和LC27一样
    public static void moveZeroes(int[] nums) {
        int size = nums.length;
        
        int i = 0;
        int j = 0;
        
        while (i < size) {
        	if (nums[i] != 0) {
        		nums[j] = nums[i];
        		j++;
        		i++;
        	} else {
        		i++;
        	}
        }
        
        while (j < size) {
        	nums[j] = 0;
        	j++;
        }
    }
    */
    public static void main(String[] args) {
		int[] test = {1,2,3,0,6,0,8,0};
		
		for (int i: test) {
			System.out.print(i + " ");
		}
		
		moveZeroes(test);
		
		System.out.println();
		for (int i: test) {
			System.out.print(i + " ");
		}
	}
}
