
public class Solution {
	/*
	 * Given an integer n, count the total number of digit 1 appearing in all non-negative 
	 * integers less than or equal to n.
	 * 
	 * For example:
	 * Given n = 13,
	 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
	 */
	
	
	// https://discuss.leetcode.com/topic/27565/java-python-one-pass-solution-easy-to-understand
	
	// easy understand solution
	//
	// if n = xyzdabc
	// we are considering the occurrence of one on thousand, it should be:
	// (1) xyz * 1000                     if d == 0
	// (2) xyz * 1000 + abc + 1           if d == 1
	// (3) xyz * 1000 + 1000              if d > 1
	public static int countDigitOne(int n) {
		if (n <= 0) {
			return 0;
		}
		
		int num = n;
		int result = 0;
		int bit = 1;

		while (num > 0) {
			
			int digit = num % 10;
			num /= 10;
			result += num * bit;
			if (digit == 1) {
				result += n % bit + 1;
			}
			
			if (digit > 1) {
				result += bit;
			}
			
			bit *= 10;
		}

		return result;
	}
	
	/*
    public static int countDigitOne(int n) {
    	  int count = 0;

    	  for (long k = 1; k <= n; k *= 10) {
    	    long r = n / k, m = n % k;
    	    count += (r + 8) / 10 * k + (r % 10 == 1 ? m + 1 : 0);
    	  }

    	  return count; 
    }
    */
    public static void main(String[] args) {
    	System.out.println(countDigitOne(13));
	}
}
