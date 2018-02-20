
public class Solution {
	/*
	 * Given a range [m, n] where 0 <= m <= n <= 2147483647, 
	 * return the bitwise AND of all numbers in this range, inclusive.
	 * 
	 * For example, given the range [5, 7], you should return 4.
	 */
	
    public static int rangeBitwiseAnd(int m, int n) {
    	
    	/*
    	 * The idea is to use a mask to find the leftmost common digits of m and n. 
    	 * Example: m=1110001, n=1110111, and you just need to find 1110000 
    	 * and it will be the answer.
    	 */
    	
        int x = m ^ n;
        
        int s = x >> 1;
        
        while (s > 0) {
        	x = x | s;
        	s = s >> 1;
        }
        
        return m & (~x);
    }
    
    public static void main(String[] args) {
    	System.out.println(rangeBitwiseAnd(5, 7));
	}
}
