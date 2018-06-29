
public class Solution {
	/*
	 * Follow up for "Remove Duplicates":
	 * 
	 * What if duplicates are allowed at most twice?
	 * 
	 * For example,
	 * Given sorted array nums = [1,1,1,2,2,3],
	 * Your function should return length = 5, with the first five elements of nums 
	 * being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
	 */
	
	
    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }
        
        if (nums.length <= 2) {
            return nums.length;
        }
        
        int pos = 2;
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] != nums[pos - 2]) {
                nums[pos++] = nums[i];
            }
        }
        
        return pos;
    }
    
    public static void main(String[] args) {
    	
    	int[] test = {1,1,1,2,2,3};
		System.out.println(removeDuplicates(test));
		
    	int[] test2 = {1,1,1};
		//System.out.println(removeDuplicates(test2));
		
    	int[] test3 = {1,1,2};
		//System.out.println(removeDuplicates(test3));
	}
}
