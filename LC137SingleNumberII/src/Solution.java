
public class Solution {
	/*
	 * Given an array of integers, every element appears three times except for one. 
	 * Find that single one.
	 * 
	 * Your algorithm should have a linear runtime complexity. 
	 * Could you implement it without using extra memory?
	 */
	
	
	 // https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
	
    public static int singleNumber(int[] nums) {
        int x1 = 0;
        int x2 = 0;
        
        for (int i: nums) {
        	x2 ^= x1 & i;
        	x1 ^= i;
       	
        	int mask = ~(x1 & x2);
        	
        	x2 = x2 & mask;
        	x1 = x1 & mask;
        	
        }
        
        return x1;
    }
    
    public static void main(String[] args) {
		int[] test = {2,3,5,2,4,3,2,5,3,6,6,6,5};
		
		System.out.println(singleNumber(test));
	}
}
