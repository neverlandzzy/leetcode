
public class Solution {
	/*
	 * Determine whether an integer is a palindrome. Do this without extra space.
	 */

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
    
    public static void main(String[] args) {
    	
    	int test = -1;
    	
    	System.out.println(isPalindrome(test));
    }
}
