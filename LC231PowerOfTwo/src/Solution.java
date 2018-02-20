
public class Solution {
	/*
	 * Given an integer, write a function to determine if it is a power of two.
	 */
	
    public static boolean isPowerOfTwo(int n) {
   	
    	// Solution 1: Time: O(n)  Space: O(n)
    	/*
    	if (n <= 0)  return false;
    	
    	while (n > 2) {
    		if (n % 2 != 0) {
    			return false;
    		}
    		n = n >> 1;
    	}
    	
    	return true;
    	*/
    	
    	// Solution 2: Time: O(1)  Space: O(1)
    	// A power of two number must be 100...000 (one follows by all zeros).
    	// So n = 1000, n - 1 = 111, 1000 & 0111 = 0000.
    	
    	if (n <= 0) return false;
    	
    	return (n & (n - 1) )== 0;
    
    }
    
    public static void main(String[] args) {
    	System.out.println(isPowerOfTwo(0));
		System.out.println(isPowerOfTwo(1));
		System.out.println(isPowerOfTwo(2));
		System.out.println(isPowerOfTwo(3));
		System.out.println(isPowerOfTwo(4));
		System.out.println(isPowerOfTwo(8));
		System.out.println(isPowerOfTwo(10));
	}
}
