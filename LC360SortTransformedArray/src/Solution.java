
public class Solution {
	/*
	 * Given a sorted array of integers nums and integer values a, b and c. Apply a quadratic function of the 
	 * form f(x) = ax^2 + bx + c to each element x in the array.
	 * 
	 * The returned array must be in sorted order.
	 * 
	 * Expected time complexity: O(n)
	 * 
	 * Example:
	 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
	 * 
	 * Result: [3, 9, 15, 33]
	 * 
	 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
	 * 
	 * Result: [-23, -5, 1, 7]
	 */
	
    public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        
    }
    
    public static void main(String[] args) {
		int[] test1 = {-4, -2, 2, 4};
		int[] result1 = sortTransformedArray(test1, 1, 3, 5);
		int[] result2 = sortTransformedArray(test1, -1, 3, 5);
		
		print(result1);
		print(result2);
	}
    
    private static void print(int[] nums) {
    	for (int i: nums) {
    		System.out.print(i + ", ");
    	}
    	
    	System.out.println();
    }
}
