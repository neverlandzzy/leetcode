
public class Solution {
	/*
	 * Implement pow(x, n).
	 */
	
	// 对于brute-force的优化：求x^2n时，知道x^n， 则只需x^2n = (x^n) * (x^n)
	// Solution 1: recursion O(logn)
	/*
	public static double myPow(double x, int n) {
		// // n = -2147483648 时会overflow, 因此用long
		long N = n;
		
		if (N < 0) {
			x = 1 / x;
			N = -N;
		}
		
		return helper(x, N);
	}
	
	private static double helper(double x, long n) {
		if (n == 0) {
			return 1;
		}
		
		double half = helper(x, n / 2);
		
		if (n % 2 == 0) {
			return half * half;
		} else {
			return half * half * x;
		}
	}
	*/
	
	// Solution 2: iteration O(logn)
	public static double myPow(double x, int n) {
        if (n == 0) {
			return 1;		
		}
        // n = -2147483648 时会overflow, 因此用long
        long N = n;
        
		if (N < 0) {            
			x = 1 / x;
			N = -N;
		}
		
		double result = 1;
		double tmp = x;
		
		for (long i = N; i > 0; i /= 2) {
			if (i % 2 == 1) {
				result *= tmp;
			}
			tmp = tmp * tmp;
		}
		
		return result;
	}
	
	
	///////////////////////////////////////////////
	/*
    public static double myPow(double x, int n) {
    	double tmp = x;
    	
    	if (n == 0) {
    		return 1;
    	}
    	
    	tmp = myPow(x, n/2);
    	
    	if (n%2 == 0) {
    		return tmp * tmp;
    	} else {
    		if(n > 0) {
    			return tmp*tmp*x;
    		} else {
    			return tmp*tmp/x;
    			
    		}
    	}

    }
	*/
    
    
    
    public static void main(String[] args) {
		System.out.println(myPow(3,-4));
		System.out.println("----");
		System.out.println(myPow(2,0));
		System.out.println("----");
		System.out.println(myPow(3,-7));
		System.out.println("----");
		System.out.println(myPow(0.00001, 2147483647)); // TLE if O(n), thus O(log(n)) algorithm is required.
	}
}
