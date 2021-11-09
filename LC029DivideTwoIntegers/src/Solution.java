
public class Solution {
	/**
	 * Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.
	 * 
	 * Return the quotient after dividing dividend by divisor.
	 * 
	 * The integer division should truncate toward zero.
	 * 
	 * Example 1:
	 * 
	 * Input: dividend = 10, divisor = 3
	 * Output: 3
	 * 
	 * Example 2:
	 * 
	 * Input: dividend = 7, divisor = -3
	 * Output: -2
	 * 
	 * Note:
	 * 1. Both dividend and divisor will be 32-bit signed integers.
	 * 2. The divisor will never be 0.
	 * 3. Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
	 * For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
	 */

	// https://blog.csdn.net/linhuanmars/article/details/20024907
	// 在原来方法（用long计算）上进行的改进：
	// 	1. 预处理一些corner case；
	//  2. 在右移divisor逼近结果时，原方法是判断当dividend >= divisor时，便左移divisor，这样可能导致溢出
	//     所以本方法是先判断(dividend >> 1) >= divisor 才左移divisor，从而避免溢出。
	//     例如divide(7, 3)，要确定 3*2^1 < 7 < 3 * 2^2 (N = 1)。原来的方法是直接找7 < 3 * 2^2，本方法是
	//     通过 7/2 < 3*2^1 找到7 < 3 * 2^2
	
	public static int divide(int dividend, int divisor) {
		// Bit Manipulation: Time ~ O(logNa), Space ~ O(1) 
		//    	  
		// Find a N such that:
		// divisor * 2^N < dividend < divisor * 2^(N + 1)
		//    	  
		// Then,
		// result += 2^N;
		// dividend -= divisor * 2^N;
		// Repeat until dividend < divisor.
				
		if (divisor == 0) {
			return Integer.MAX_VALUE;
		}
		
		int neg = -1;
		
		if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
			neg = 1;
		}
		
		int result = 0;
		
		if (dividend == Integer.MIN_VALUE) {
			if (divisor == -1) {
				return Integer.MAX_VALUE;
			}
			
			dividend += Math.abs(divisor);
			result++;
		}
		
		if (divisor == Integer.MIN_VALUE) {
			return result;
		}
		
		dividend = Math.abs(dividend);  
		divisor = Math.abs(divisor);  
		int n = 0;
		
		while (divisor <= (dividend >> 1)) {
			divisor = divisor << 1;
			n++;
		}
		// System.out.println("divisor = " + divisor + " n =  " + n);
		while (n >= 0) {
	        if (dividend >= divisor) {  
	            result += 1 << n;  
	            dividend -= divisor;  
	        }  
	        divisor = divisor >> 1;  
	        n--; 
		}
		
		return neg * result; 
	}


	// 题目新加了不能使用long的限定，因此不能用下面解法
	/*
    public static int divide(int dividend, int divisor) {

		// Bit Manipulation: Time ~ O(logNa), Space ~ O(1) 
		//    	  
		// Find a N such that:
		// divisor * 2^N < dividend < divisor * 2^(N + 1)
		//    	  
		// Then,
		// result += 2^N;
		// dividend -= divisor * 2^N;
		// Repeat until dividend < divisor.
    	 
        
    	int result = 0;
    	long tmpDividend = Math.abs((long)dividend);
    	long tmpDivisor = Math.abs((long)divisor);
    	
    	//System.out.println(tmpDividend);
    	//System.out.println(tmpDivisor);
    	if (divisor == 0) {
    		return 0;
    	}
    	
    	if (divisor == 1) {
    		return dividend;
    	}
    	
    	if (divisor == -1) {
    		return (dividend == Integer.MIN_VALUE) ? Integer.MAX_VALUE : -dividend;
    	}
    	
    	while (tmpDividend >= tmpDivisor) {
    		int n = 0;
    		
    		while(tmpDividend >= (tmpDivisor << n)) {
    			n++;
    		}
    
    		result += 1 << (n-1);
    		tmpDividend -= tmpDivisor << (n-1);

    	}
    	
    	if ((dividend > 0 && divisor > 0 )|| (dividend < 0 && divisor < 0 )) {
    		return (result > Integer.MAX_VALUE) ? Integer.MAX_VALUE : result;
    	} else {
    		return -result;
    	}

    }
    */
    
    
    public static void main(String args[]) {
    	
    	/*
    	System.out.println(divide(8,-2));
    	System.out.println(divide(Integer.MAX_VALUE,1));
    	System.out.println(divide(7,3));  	
    	System.out.println(divide(-2147483648, -1));
    	System.out.println(divide(3,3));    	
    	System.out.println(divide(-1010369383, -2147483648));
    	System.out.println(divide(10,3));
    	System.out.println(divide(1,-1));
    	System.out.println(divide(-2147483648, 2));
    	*/
    	
    	//System.out.println(divide(1100540749, -1090366779));
    	
    	System.out.println(divide(7,3));
    }
}
