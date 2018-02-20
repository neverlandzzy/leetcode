
public class Solution {
	/*
	 * Given a positive integer num, write a function which returns True if num is a 
	 * perfect square else False.
	 * 
	 * Note: Do not use any built-in library function such as sqrt.
	 * 
	 * Example 1:
	 * 
	 * Input: 16
	 * Returns: True
	 * Example 2:
	 *
	 * Input: 14
	 * Returns: False
	 */
	
    public static boolean isPerfectSquare(int num) {
        long lo = 1; 
        long hi = num;
        
        while (lo + 1 < hi) {
        	long mid = lo + (hi - lo) / 2;
        	
        	if (mid * mid < num) {
        		lo = mid;
        	} else if (mid * mid > num) {
        		hi = mid;
        	} else {
        		return true;
        	}
        }
        
        if (lo * lo == num) {
        	return true;
        } 
        
        if (hi * hi == num) {
        	return true;
        }
        
        return false;
    }

    public static void main(String[] args) {
		System.out.println(isPerfectSquare(808201));
		System.out.println(isPerfectSquare(14));
		System.out.println(isPerfectSquare(1));
	}
}
