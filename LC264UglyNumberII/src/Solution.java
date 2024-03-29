
public class Solution {
	/**
	 * Write a program to find the n-th ugly number.
	 * 
	 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
	 * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
	 * Note that 1 is typically treated as an ugly number.
	 */
	
    public static int nthUglyNumber(int n) {
    	int[] result = new int[n];
    	result[0] = 1;
    	
    	int index2  = 0, index3  = 0, index5  = 0;
    	int factor2 = 2, factor3 = 3, factor5 = 5;
    	
    	for (int i = 1; i < n; i++) {
    		int min = Math.min(Math.min(factor2, factor3), factor5);
    		result[i] = min;
    		
    		if (min == factor2) {
    			factor2 = 2 * result[++index2];
    		}
    		
    		if (min == factor3) {
    			factor3 = 3 * result[++index3];
    		}
    		
    		if (min == factor5) {
    			factor5 = 5 * result[++index5];
    		}
       	}
       
    	return result[n-1];
    }
    
    public static void main(String[] args) {
		System.out.println(nthUglyNumber(11));
		System.out.println(nthUglyNumber(1));
		System.out.println(nthUglyNumber(5));
	}
}
