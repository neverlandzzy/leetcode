
public class Solution {
	/**
	 * Determine whether an integer is a palindrome. Do this without extra space.
	 */

	/* faster
    public static boolean isPalindrome(int x) {
        int rev = 0;
        
        if (x%10 == 0 && x!=0) {
        	return false;
        }
        
        while(x > rev) {
        	
        	rev = rev*10 + x%10;
        	x = x/10;
        }

        return (rev == x || rev/10 == x);
    }
    */
    
	// more straightforward
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }   
        
        if (x % 10 == 0 && x != 0) {
            return false;
        }
        
        int orig = x;
        int reverse = 0;

        
        while (orig > 0) {
            reverse = reverse * 10 + orig % 10;
            orig /= 10;
        }
        
        return x == reverse;
    }
    
    public static void main(String[] args) {
    	
    	int test = 121;
    	
    	System.out.println(isPalindrome(test));
    }
}
