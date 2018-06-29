
public class Solution {
	/*
	 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.
	 * 
	 * Note:
	 * 
	 * 1. The length of both num1 and num2 is < 110.
	 * 2. Both num1 and num2 contains only digits 0-9.
	 * 3. Both num1 and num2 does not contain any leading zero.
	 * 4. You must not use any built-in BigInteger library or convert the inputs to integer directly.
	 */
	
	public static String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		
		StringBuilder sb = new StringBuilder();
		int n1 = num1.length(); 
		int n2 = num2.length();
		int[] d = new int[n1 + n2 - 1];
		char[] str1 = num1.toCharArray();
		char[] str2 = num2.toCharArray();
		
		for (int i = n1 - 1; i >= 0; i--) {
			for (int j = n2 - 1; j >= 0; j--) {
				d[i + j] += (str1[i] - '0') * (str2[j] - '0');
			}
		}
		
        int carry = 0;
        
        for (int i = d.length - 1; i >= 0; i--) {
        	int sum = (d[i] + carry) % 10;

        	carry = (d[i] + carry) / 10;
        	sb.append(sum);
        }
        
        if (carry != 0) {
        	sb.append(carry);
        }
        
        return sb.reverse().toString();
	}
	

	/*
    public static String multiplyII(String num1, String num2) {
        
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        
    	StringBuilder result = new StringBuilder();
        
        char[] str1 = new StringBuffer(num1).reverse().toString().toCharArray();	
        char[] str2 = new StringBuffer(num2).reverse().toString().toCharArray();
        
        int[] d = new int[num1.length()+num2.length()];
        
        for (int i = 0 ; i < str1.length; i++) {
        	for (int j = 0; j <str2.length; j++) {
        		d[i+j] += (str1[i]-'0') * (str2[j]-'0');
        	}
        }
        for (int k: d) {
        	System.out.print(k + ", ");
        }
        System.out.println();
        int carry = 0;
        
        for (int i = 0; i < d.length; i++) {
        	
        	int digit = (d[i]+ carry)%10;
        	System.out.println(d[i] + carry);
        	result.append(digit);
        	carry = (d[i]+carry)/10;

        }
        
        result.reverse();

        if (result.charAt(0) == '0') {
        	
        	result.deleteCharAt(0);
        }
        
         return result.toString();
    }
	*/
    public static void main(String[] args) {
		String str1 = "382";
		String str2 = "67";
		
		String str3 = "408";
		String str4 = "5";
		
		//System.out.println(multiply(str1, str2));
		System.out.println(multiply(str3, str4));
	}
}
