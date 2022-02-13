
public class Solution {
	/**
	 * Implement next permutation, which rearranges numbers into the lexicographically next 
	 * greater permutation of numbers. If such arrangement is not possible, it must rearrange 
	 * it as the lowest possible order (ie, sorted in ascending order).
	 * 
	 * The replacement must be in-place, do not allocate extra memory.
	 * 
	 * Here are some examples. Inputs are in the left-hand column and its corresponding 
	 * outputs are in the right-hand column.
	 * 
	 * 1,2,3 → 1,3,2
	 * 3,2,1 → 1,2,3
	 * 1,1,5 → 1,5,1
	 * 
	 */

    public static void nextPermutation(int[] nums) {
        int pos = nums.length - 1;
        
        while (pos > 0 && nums[pos - 1] >= nums[pos]) {
            pos--;
        }
        
        reverse(nums, pos, nums.length - 1);
        
        if (pos > 0) {
            int next = pos;
            pos--;
            
            while (next < nums.length && nums[next] <= nums[pos]) {
                next++;
            }
            
            int tmp = nums[pos];
            nums[pos] = nums[next];
            nums[next] = tmp;
        }
    }
    
    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;
            end--;
        }
    }
    
    public static void main(String[] args) {
    	
		int[] test1 = {5,1,1};
		print(test1);
		nextPermutation(test1);
		print(test1);
		
		int[] test2 = {6, 8, 7, 4, 3, 2};
		print(test2);
		nextPermutation(test2);
		print(test2);
		
		int[] test3 = {1, 1};
		print(test3);
		nextPermutation(test3);
		print(test3);
		
		
	}
    
    private static void print(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
		System.out.println();
    }
}
