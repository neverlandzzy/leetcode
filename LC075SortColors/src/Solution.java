
public class Solution {
	/**
	 * Given an array with n objects colored red, white or blue, sort them so that objects of 
	 * the same color are adjacent, with the colors in the order red, white and blue.
	 * 
	 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue 
	 * respectively.
	 * 
	 * Note:
	 * You are not suppose to use the library's sort function for this problem.
	 * 
	 * Follow up:
	 * A rather straight forward solution is a two-pass algorithm using counting sort.
	 * First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with 
	 * total number of 0's, then 1's and followed by 2's.
	 * 
	 * Could you come up with an one-pass algorithm using only constant space?
	 */
	
    public static void sortColors(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int i = 0;
        
        while (i <= right) {
        	if (nums[i] == 0) {
        		if (i != left) {
        			swap(nums, i, left);
        		}
        		i++;
        		left++;
        	} else if(nums[i] == 2){
        		swap(nums, i, right);
        		right--;
        	} else {
        		i++;
        	}
        }
    }
    
    public static void swap(int[] nums, int i, int j) {
    	int tmp = nums[i];
    	nums[i] = nums[j];
    	nums[j] = tmp;
    }
    
    public static void main(String[] args) {
    	int [] test = {0, 2, 1, 1, 1, 2, 2, 0, 0, 2, 1, 0};
    	
    	for (int n: test) {
        	System.out.print(n + " ");
    	}
    	System.out.println();
    	
    	sortColors(test);
    	for (int n: test) {
        	System.out.print(n + " ");
    	}
	}
}
