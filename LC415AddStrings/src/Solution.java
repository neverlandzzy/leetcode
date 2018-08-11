
public class Solution {
	/*
	 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.
	 * 
	 * Note:
	 * 
	 * The length of both num1 and num2 is < 5100.
	 * 1. Both num1 and num2 contains only digits 0-9.
	 * 2. Both num1 and num2 does not contain any leading zero.
	 * 3. You must not use any built-in BigInteger library or convert the inputs to integer directly.
	 */
	
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        
        int n1 = num1.length();
        int n2 = num2.length();
        int sum = 0;
        int carry = 0;
        
        int i = n1 - 1;
        int j = n2 - 1;
        
        while (i >= 0 || j >= 0) {
        	int a = i >= 0 ? num1.charAt(i) - '0' : 0;
        	int b = j >= 0 ? num2.charAt(j) - '0' : 0;
        	
        	sum = a + b + carry;
        	carry = sum / 10;
        	sum %= 10;
        	
        	sb.append(sum);
        	i--;
        	j--;
        }
        
        if (carry != 0) {
        	sb.append(carry);
        }
        
        return sb.reverse().toString();
    }
    
    public static void main(String[] args) {
		System.out.println(addStrings("1234", "567")); // 1801
		System.out.println(addStrings("999999", "1")); // 1000000
		System.out.println(addStrings("12345", "876"));// 13221
	}
}
