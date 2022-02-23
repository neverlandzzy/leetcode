/**
 * 
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 *     
 * For example:
 *   A -> 1
 *   B -> 2
 *   C -> 3
 *   ...
 *   Z -> 26
 *   AA -> 27
 *   AB -> 28 
 *   
 */


public class App {
	public static void main(String[] args) {
		String test1 = "A";
		String test2 = "B";
		String test3 = "Z";
		String test4 = "AA";
		String test5 = "AB";
		String test6 = "AZ";
		String test7 = "BA";
		String test8 = "BB";
		String test9 = "ZA";
		String test10 = "ZZ";
		
		
		System.out.println(titleToNumber(test1));
		System.out.println(titleToNumber(test2));
		System.out.println(titleToNumber(test3));
		System.out.println(titleToNumber(test4));
		System.out.println(titleToNumber(test5));
		System.out.println(titleToNumber(test6));
		System.out.println(titleToNumber(test7));
		System.out.println(titleToNumber(test8));
		System.out.println(titleToNumber(test9));
		System.out.println(titleToNumber(test10));
		
		
	}
	
    public static int titleToNumber(String columnTitle) {
		int result = 0;

		for (char c: columnTitle.toCharArray()) {
			result = result * 26 + (c - 'A' + 1);
		}

		return result;
    }
    
    /*
	public static int titleToNumber(String s) {
		
		int colNumber = 0;
        
		for (int i = 0; i < s.length(); i++) {
			colNumber = colNumber*26 + ((int)s.charAt(i)-64);
	    }
        
        return colNumber;
    }
	*/
	
}
