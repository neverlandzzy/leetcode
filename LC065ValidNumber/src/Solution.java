
public class Solution {
	/*
	 * Validate if a given string is numeric.
	 * 
	 * Some examples:
	 * 	"0" => true
	 * 	" 0.1 " => true
	 * 	"abc" => false
	 * 	"1 a" => false
	 * 	"2e10" => true
	 * 
	 * Note: It is intended for the problem statement to be ambiguous. 
	 * You should gather all requirements up front before implementing one.
	 */
	
    public static boolean isNumber(String s) {
    	boolean isValid = false;
    	int length = s.length();
    	int i = 0;
    	
    	while(i < length && Character.isWhitespace(s.charAt(i))) {
    		i++;
    	}
    	
    	if (i < length && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
    		i++;
    	}
        
    	while(i < length && Character.isDigit(s.charAt(i))) {
    		i++;
    		isValid = true;
    	}
    	
    	if (i < length && s.charAt(i) == '.') {
    		i++;
    	}
    	
    	while(i < length && Character.isDigit(s.charAt(i))) {
    		i++;
    		isValid = true;
    
    	}

    	if (i < length && s.charAt(i) == 'e' && isValid) {
    		i++;
    		isValid = false;
        	if (i < length && (s.charAt(i) == '-' || s.charAt(i) == '+') ) {
        		i++;
        	}
    	}
	
    	while(i < length && Character.isDigit(s.charAt(i))) {
    		i++;
    		isValid = true;
    	}

    	
    	while(i < length && Character.isWhitespace(s.charAt(i))) {
    		i++;
    	}
    	
    	
    	if (i != length) {
    		isValid = false;
    	}
    	
    	return isValid;
    }
    
    public static void main(String[] args) {
		System.out.println(isNumber("0"));
		System.out.println(isNumber(" 0.1"));
		System.out.println(isNumber("abc"));
		System.out.println(isNumber("1 a"));
		System.out.println(isNumber("2e10"));
		System.out.println(isNumber("e"));
		System.out.println(isNumber(" "));
		System.out.println(isNumber("-"));
		System.out.println(isNumber("1 "));
		System.out.println(isNumber(".1 "));
		System.out.println(isNumber("e9"));
		System.out.println(isNumber("1 4"));
		System.out.println(isNumber("0e"));
		System.out.println(isNumber("6+1"));
	}
}
