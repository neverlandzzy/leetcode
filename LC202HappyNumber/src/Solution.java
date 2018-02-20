import java.util.HashSet;


public class Solution {
	/*
	 * Write an algorithm to determine if a number is "happy".
	 * 
	 * A happy number is a number defined by the following process: 
	 * Starting with any positive integer, replace the number by the sum of the squares of its digits, 
	 * and repeat the process until the number equals 1 (where it will stay), or it loops endlessly 
	 * in a cycle which does not include 1. Those numbers for which this process ends in 1 
	 * are happy numbers.
	 * 
	 * Example: 19 is a happy number
	 * 
	 * 1^2 + 9^2 = 82
	 * 8^2 + 2^2 = 68
	 * 6^2 + 8^2 = 100
	 * 1^2 + 0^2 + 0^2 = 1
	 */
	
	//Solution 1: HashTable,  space : not O(1)
	/*
    public static boolean isHappy(int n) {

        HashSet<Integer> set = new HashSet<Integer>();
        
        int sum = 0;
        
        while (sum != 1) {
            
        	sum = 0;
	        
            while (n > 0) {
	        	int lastDigit = n % 10;
	        	sum += lastDigit * lastDigit;
	        	n = n / 10;
	        }
	        
            n = sum;

	        if (!set.add(sum)) {
	        	return false;
	        }
        }
        
        return true;
        
    }
    
    */

	
	// Solution 2: slow and fast number, space: O(1), also faster
    
    public static boolean isHappy(int n) {
    	
    	int slow = n;
    	int fast = n;
    	
    	while (true) {
    		slow = digitsSquareSum(slow);
    		fast = digitsSquareSum(fast);
    		fast = digitsSquareSum(fast);
    		
    		if (slow == fast) {
    			break;
    		}
    	}
    	
    	return slow == 1;
    }
    
	
	private static int digitsSquareSum (int n){
		int sum = 0;
		
		while (n > 0) {
			int lastDigit = n % 10;
			sum += lastDigit * lastDigit;
			n = n / 10;
		}
		
		return sum;
	}
	
    public static void main(String[] args) {
		System.out.println(isHappy(19));
		System.out.println(isHappy(101));
	}
}
