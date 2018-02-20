
public class Solution {
	/*
	 * Given a non-negative integer N, find the largest number that is less than or equal to N with monotone increasing digits.
	 * 
	 * (Recall that an integer has monotone increasing digits if and only if each pair of adjacent digits x and y satisfy x <= y.)
	 * 
	 * Example 1:
	 * Input: N = 10
	 * Output: 9
	 * 
	 * Example 2:
	 * Input: N = 1234
	 * Output: 1234
	 * 
	 * Example 3:
	 * Input: N = 332
	 * Output: 299
	 * 
	 * Note: N is an integer in the range [0, 10^9].
	 */
	
    public static int monotoneIncreasingDigits(int N) {
    	if (N < 10) {
    		return N;
    	}
    	
    	String s = String.valueOf(N);
    	int n = s.length();
    	int index = -1;
    	
    	for (int i = n - 2; i >= 0; i--) {
    		if (s.charAt(i) > s.charAt(i + 1) || (index != -1 && s.charAt(index) == s.charAt(i))) {
    			index = i;
    		}
    	}
    	
    	return index == -1 ? N : N - Integer.parseInt(s.substring(index + 1)) - 1;
    }
    
    public static void main(String[] args) {
    	System.out.println(monotoneIncreasingDigits(7983));
	}
}
