
public class Solution {
	/*
	 * Implement atoi to convert a string to an integer.
	 * 
	 * Hint: Carefully consider all possible input cases. 
	 * 
	 * If you want a challenge, please do not see below and ask yourself 
	 * what are the possible input cases.
	 * 
	 * Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). 
	 * You are responsible to gather all the input requirements up front.
	 * 
	 * Requirements for atoi:
	 * 1. The function first discards as many whitespace characters as necessary until the first 
	 * non-whitespace character is found. 
	 * 
	 * 2. Then, starting from this character, takes an optional initial plus or minus sign followed by 
	 * as many numerical digits as possible, and interprets them as a numerical value.
	 * 
	 * 3. The string can contain additional characters after those that form the integral number, 
	 * which are ignored and have no effect on the behavior of this function.
	 * 
	 * 4. If the first sequence of non-whitespace characters in str is not a valid integral number, 
	 * or if no such sequence exists because either str is empty or it contains only whitespace 
	 * characters, no conversion is performed.
	 * 
	 * 5. If no valid conversion could be performed, a zero value is returned. If the correct value is out
	 * of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
	 */
	
    public static int myAtoi(String str) {
        int i = 0;
        int sign = 1;
        long result = 0;
        
        if (str.length() == 0) {
        	return 0;
        }
        
        while (str.charAt(i) == ' ' && i < str.length()) {
        	i++;
        }
        
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
        	sign = str.charAt(i) == '+' ? 1 : -1;
        	i++;
        }
        
        while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
        	result = 10*result + ((int)str.charAt(i)-'0');
        	
            if (result*sign > Integer.MAX_VALUE) {
            	result = Integer.MAX_VALUE;
            	break;
            } else if (result*sign < Integer.MIN_VALUE) {
            	result = Integer.MIN_VALUE;
            	break;
            }
            
        	i++;
        			
        }
        
        return (int)result*sign;
    }
    
    public static void main(String[] args) {
    	String test1 = "123abc";
    	String test2 = "1";
    	
    	System.out.println(myAtoi(test1));
    	System.out.println(myAtoi(test2));
    }
}
