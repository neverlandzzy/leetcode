
public class Solution {
   /*
    * Reverse digits of an integer.
    * 
    * 	Example1: x = 123, return 321
    * 	Example2: x = -123, return -321
    * 
    * For the purpose of this problem, assume that your function returns 0 
    * when the reversed integer overflows.
    * 
    * Did you notice that the reversed integer might overflow? Assume the input is a 32-bit integer, 
    * then the reverse of 1000000003 overflows. How should you handle such cases?
    */
	
	public static int reverse(int x) {
		
		long result = 0;
		
		while (x != 0) {
			int remainder = x%10;
			result = result*10 + remainder;
			x = x/10;
		}
		
		if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
			result = 0;
		}
		return (int)result; 
    }
	
	public static void main(String[] args) {
		int a = 2147483647;
		int b = -2147483648;
		System.out.println(Integer.MIN_VALUE);
		System.out.println(a + " ---> " + reverse(a));
		System.out.println(b + " ---> " + reverse(b));
		

	}
	
}
