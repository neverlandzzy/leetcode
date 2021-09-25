
public class Solution {
	
	/**
	 * Given an array of integers, every element appears twice except for one. Find that single one.
	 * 
	 * Note:
	 * 
	 * Your algorithm should have a linear runtime complexity. 
	 * Could you implement it without using extra memory?
	 */
	
    public static int singleNumber(int[] nums) {
    	
    	int k = 0;
    	
    	for (int i : nums) {
    		k = k^i;
    	}
        
    	return k;
    }
    
    public static void main(String[] args) {
    	int[] inputArray = {1,2,3,3,4,4,2,1,5,5,6};
    	int k;
    	
    	k = singleNumber(inputArray);
    	
    	System.out.println(k);
   	
    	
    }
}