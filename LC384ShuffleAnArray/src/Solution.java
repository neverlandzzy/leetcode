
public class Solution {
	/*
	 * Shuffle a set of numbers without duplicates.
	 * 
	 * Example:
	 * 
	 * // Init an array with set 1, 2, and 3.
	 * int[] nums = {1,2,3};
	 * Solution solution = new Solution(nums);
	 * 
	 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
	 * solution.shuffle();
	 * 
	 * // Resets the array back to its original configuration [1,2,3].
	 * solution.reset();
	 * 
	 * // Returns the random shuffling of array [1,2,3].
	 * solution.shuffle();
	 */
	
    public Solution(int[] nums) {
        
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        
    }
    
    public static void main(String[] args) {
		int[] test = {1, 2, 3};
		
		Solution solution = new Solution(test);
		
		int[] reset1 = solution.reset();		
		int[] shuffle1 = solution.reset();
		
		int[] reset2 = solution.reset();		
		int[] shuffle2 = solution.reset();
		
		print(reset1);
		print(shuffle1);
		print(reset2);
		print(shuffle2);
	}
    
    private static void print(int[] nums) {
    	for (int i: nums) {
    		System.out.print(i + ", ");
    	}
    	System.out.println();
    }
}
