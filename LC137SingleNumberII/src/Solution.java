
public class Solution {
	/**
	 * Given an array of integers, every element appears three times except for one. 
	 * Find that single one.
	 * 
	 * Your algorithm should have a linear runtime complexity. 
	 * Could you implement it without using extra memory?
	 */
				
	// Solution 1: O(32n), 因为除了single number外，每一个数字都出现了3次，因此统计32bits的每一个bits出现的次数，发现不能被3整除的，即为single number的某一位
	//             可以扩展到任意次
	
	public static int singleNumber(int[] nums) {		
		int result = 0;
		
		for (int i = 0; i < 32; i++) {
			
			int bit = (1 << i);
			int count = 0;
			
			for (int n: nums) {
				if ((bit & n) != 0) {
					count++;
				}
			}
			
			if (count % 3 != 0) {
				result |= bit;
			}
		}
		
		return result;
	}
	
	// https://leetcode.com/problems/single-number-ii/discuss/43295/Detailed-explanation-and-generalization-of-the-bitwise-operation-method-for-single-numbers
	// https://www.cnblogs.com/bjwu/p/9323808.html	
	/*
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
    */
    public static void main(String[] args) {
		int[] test = {2,3,5,2,4,3,2,5,3,6,6,6,5};
		
		System.out.println(singleNumber(test));
	}
}
