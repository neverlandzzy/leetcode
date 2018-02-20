
public class Solution {
	/*
	 * Divide two integers without using multiplication, division and mod operator.
	 * If it is overflow, return MAX_INT.
	 * 
	 */
	
    public static int divide(int dividend, int divisor) {
    	/*
    	 * Bit Manipulation: Time ~ O(logNa), Space ~ O(1) 
    	 * 
    	 * Find a N such that:
    	 * divisor * 2^N < dividen < divisor * 2^(N + 1)
    	 * 
    	 * Then,
    	 * result += 2^N;
    	 * dividen -= divisor * 2^N;
    	 * Repeat until dividen < divisor.
    	 */
        
    	int result = 0;
    	long tmpDividend = Math.abs((long)dividend);
    	long tmpDivisor = Math.abs((long)divisor);
    	
    	System.out.println(tmpDividend);
    	System.out.println(tmpDivisor);
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
    
    
    public static void main(String args[]) {
    	/*
    	System.out.println(divide(8,-2));
    	System.out.println(divide(Integer.MAX_VALUE,1));
    	System.out.println(divide(7,3));
    	System.out.println(divide(-1010369383, -2147483648));
    	*/
    	System.out.println(divide(-2147483648, -1));
    	//System.out.println(divide(3,3));
    	
    	
    }
}
