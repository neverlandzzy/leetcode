
public class Solution {
	/*
	 * Implement pow(x, n).
	 */
	
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
