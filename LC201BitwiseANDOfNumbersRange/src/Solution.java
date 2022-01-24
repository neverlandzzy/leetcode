
public class Solution {
	/**
	 * Given a range [m, n] where 0 <= m <= n <= 2147483647, 
	 * return the bitwise AND of all numbers in this range, inclusive.
	 * 
	 * For example, given the range [5, 7], you should return 4.
	 */


	// Find the leftmost common digits of m and n.
	// Solution 1: More straightforward
	public static int rangeBitwiseAnd(int left, int right) {
		while (left < right) {
			// Turn off the rightmost 1-bit
			right = right & (right - 1);
		}

		return left & right;
	}

	// Solution 2:
	/*
	 * The idea is to use a mask to find the leftmost common digits of m and n.
	 * Example: m=1110001, n=1110111, and you just need to find 1110000
	 * and it will be the answer.
	 */
	/*
    public static int rangeBitwiseAnd(int m, int n) {

        int x = m ^ n;
        int s = x >> 1;
        
        while (s > 0) {
        	x = x | s;
        	s = s >> 1;
        }
        
        return m & (~x);
    }
    */
    public static void main(String[] args) {
    	System.out.println(rangeBitwiseAnd(3, 5));
	}
}
