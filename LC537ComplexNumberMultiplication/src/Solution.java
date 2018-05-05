
public class Solution {
	/*
	 * Given two strings representing two complex numbers.
	 * 
	 * You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
	 * 
	 * Example 1:
	 * Input: "1+1i", "1+1i"
	 * Output: "0+2i"
	 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
	 * 
	 * Example 2:
	 * Input: "1+-1i", "1+-1i"
	 * Output: "0+-2i"
	 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
	 * 
	 * Note:
	 * 1. The input strings will not have extra blank.
	 * 2. The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. 
	 * And the output should be also in this form.
	 */
	
    public static String complexNumberMultiply(String a, String b) {
    	String a1 = a.split("\\+")[0];
    	String a2 = a.split("\\+")[1];  
    	String b1 = b.split("\\+")[0];
    	String b2 = b.split("\\+")[1]; 
    	
        int x1 = Integer.parseInt(a1);
        int x2 = Integer.parseInt(a2.substring(0, a2.length() - 1));
        int y1 = Integer.parseInt(b1);
        int y2 = Integer.parseInt(b2.substring(0, b2.length() - 1));
        
        int r1 = x1 * y1 - x2 * y2;
        int r2 = x1 * y2 + x2 * y1;
        
        StringBuilder result = new StringBuilder();
        
        result.append(r1).append("+").append(r2).append("i");
        
        return result.toString();
    }
    
    public static void main(String[] args) {
		String a1 = "1+1i";
		String b1 = "1+1i";
		String a2 = "1+-1i";
		String b2 = "1+-1i";
		
		System.out.println(complexNumberMultiply(a1, b1));
		System.out.println(complexNumberMultiply(a2, b2));
	}
}
