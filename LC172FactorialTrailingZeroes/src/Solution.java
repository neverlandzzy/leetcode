
public class Solution {
	/*
	 * Given an integer n, return the number of trailing zeroes in n!.
	 * 
	 * Note: Your solution should be in logarithmic time complexity.
	 */
	
    public static int trailingZeroes(int n) {
        int counter = 0;
        
        while (n > 0) {
        	n = n / 5;
        	counter += n;
        }
        
        return counter;
    }
    
    public static void main(String[] args) {
		System.out.println(trailingZeroes(5));
	}
}
