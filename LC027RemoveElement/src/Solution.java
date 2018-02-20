
public class Solution {
	/*
	 * Given an array and a value, remove all instances of that value in place and return the new length.
	 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
	 * 
	 */
	
	// 和LC283一样
	public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        int i = 0;
        int j = 0;
        
        while (i < n) {
            if (nums[i] != val) {
                nums[j] = nums[i];
                i++;
                j++;
            } else {
                i++;
            }
        }
        
        return j;
	}

    public static void main(String[] args) {
    	int[] test1 = {1,2,3,3,4,4};
    	int[] test2 = {10,4,7,21,13,14,4,5,21,9};
    	int[] test3 = {4,2,0,2,2,1,4,4,1,4,3,2};
    	int[] test4 = {1};
    	int[] test5 = {3,3};
    	int[] test6 = {};
    	
    	System.out.println(removeElement(test1, 4));
    	System.out.println(removeElement(test2, 21));
    	System.out.println(removeElement(test3, 4));
    	System.out.println(removeElement(test4, 1));
    	System.out.println(removeElement(test5, 3));
    	System.out.println(removeElement(test6, 0));

    }
    
}
