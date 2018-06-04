
public class Solution {
	/*
	 * Given an integer n, return the number of trailing zeroes in n!.
	 * 
	 * Note: Your solution should be in logarithmic time complexity.
	 */
	
	// https://leetcode.com/problems/factorial-trailing-zeroes/discuss/52367/My-explanation-of-the-Log(n)-solution
    public static int trailingZeroes(int n) {
        int result = 0;
        
        while (n > 0) {
            result += n / 5;
            n /= 5;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		System.out.println(trailingZeroes(5));
		System.out.println(trailingZeroes(25));
	}
}
