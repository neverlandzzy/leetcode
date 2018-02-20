import java.util.HashMap;


public class Solution {
	/*
	 * Given two integers representing the numerator and denominator of a fraction, 
	 * return the fraction in string format.
	 * 
	 * If the fractional part is repeating, enclose the repeating part in parentheses.
	 * 
	 * For example,
	 * 
	 * Given numerator = 1, denominator = 2, return "0.5".
	 * Given numerator = 2, denominator = 1, return "2".
	 * Given numerator = 2, denominator = 3, return "0.(6)".
	 */
	
    public static String fractionToDecimal(int numerator, int denominator) {
    	StringBuilder result = new StringBuilder();
    	
        if (numerator == 0) {
            return "0";
        }
        
    	if (numerator < 0 ^ denominator < 0) {
    		result.append("-");
    	}
    	
		long n = numerator;
		long d = denominator;
		
		n = Math.abs(n);
		d = Math.abs(d);
    	
    	result.append(n / d);
    	
    	if (n % d != 0) {
    		result.append(".");
    	}
        
    	long remainder = n % d;
    	
    	HashMap<Long, Integer> map = new HashMap<Long, Integer>();
    	
    	while (remainder != 0) {  		
    		if (map.containsKey(remainder)) {
    			result.insert(map.get(remainder), "(");
    			result.append(")");
    			break;
    		} else {
    			map.put(remainder, result.length());
    			n = remainder * 10;
    			remainder = n % d;
    			result.append(n/d);
    		}  		
    		
    	}
  	
        return result.toString();
    }
    
    public static void main(String[] args) {
		System.out.println(fractionToDecimal(1, 99));
		System.out.println(fractionToDecimal(2,1));
		System.out.println(fractionToDecimal(1,3));
		System.out.println(fractionToDecimal(2,3));

	}
}
