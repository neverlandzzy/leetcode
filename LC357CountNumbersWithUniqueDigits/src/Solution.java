
public class Solution {
	/*
	 * Given a non-negative integer n, count all numbers with unique digits, x, where 0 ≤ x < 10n.
	 * 
	 * Example:
	 * Given n = 2, return 91. (The answer should be the total numbers in the range of 0 ≤ x < 100, 
	 * excluding [11,22,33,44,55,66,77,88,99])
	 */
	
    public static int countNumbersWithUniqueDigits(int n) {
        if (n > 10) {
        	return countNumbersWithUniqueDigits(10);
        }
        
        if (n == 0) {
        	return 1;
        }
        
        int result = 10; //when n == 1;
        int base = 9;
        
        for  (int i = 2; i <= n; i++) {
        	base *= (9 - i + 2);
        	result += base;
        }
        
        // n = 1 -- 10,
        // n = 2 -- 10 + 9*9
        // n = 3 -- 10 + 9*9 + 9*9*8;
        return result;
    }
    
    
    public static void main(String[] args) {
		System.out.println(countNumbersWithUniqueDigits(2));
		System.out.println(countNumbersWithUniqueDigits(3));
		System.out.println(countNumbersWithUniqueDigits(4));
		System.out.println(countNumbersWithUniqueDigits(5));
	}
}
