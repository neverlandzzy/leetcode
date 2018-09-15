

public class Solution {
	/*
	 * Count the number of prime numbers less than a non-negative number, n.
	 */
	
	
	// Sieve of Eratosthenes: 
	// https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
	
	// Time: O(n*logn*logn)
    public static int countPrimes(int n) {
        if (n < 2)  return 0;
        
        // Use notPrime instead of isPrime to speed up run time as we don't
        // need to fill it with true at the beginning.
        
    	boolean[] notPrime = new boolean[n];
    	
    	for (int i = 2; i <= Math.sqrt(n); i++) {
    		// 不加这个判断一样成立，加上可以优化
    		if (!notPrime[i]) {
    			for (int j = i * i; j < n; j += i) {
    				notPrime[j] = true;
    			}
    		}
    	}
    	
    	int counter = 0;
    	for (int i = 2; i < n; i++) {
    		if (!notPrime[i]) {
    			counter++;
    		}
    	}

    	return counter;
    }
    
    public static void main(String[] args) {
		System.out.println(countPrimes(30));
		System.out.println(countPrimes(2));
	}
}
