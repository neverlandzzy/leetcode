
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
	
	// https://leetcode.com/problems/string-to-integer-atoi/discuss/4643/Java-Solution-with-4-steps-explanations
	// 分别处理四种情况：
    public static int myAtoi(String str) {
    	
    	// 1.输入为空
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        
        long result = 0;
        int sign = 1;
        
        int i = 0;
        int n = str.length();
        
        // 2.去掉起始的空白字符
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }
        
        // 3.处理符号
        if (i < n && (str.charAt(i) == '+' || str.charAt(i) == '-')) {
            sign = str.charAt(i) == '+' ? 1 : -1;
            i++;
        }
        
        // 4.计算数字
        while (i < n) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                break;
            }
            int digit = str.charAt(i) - '0';
            
            result = result * 10 + digit;
            
            if (result > Integer.MAX_VALUE) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }           
           
            i++;
        }
        
        return (int)result * sign;
    }
    
    /*
    public static int myAtoi2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        StringBuilder sb = new StringBuilder();
        int n = str.length();
        int i = 0;
        int sign = 1;
        int LIMIT = 10;
        boolean firstDigit = true;
        
        while (i < n && str.charAt(i) == ' ') {
            i++;
        }

        if (i >= n || (str.charAt(i) != '+' && str.charAt(i) != '-' && (str.charAt(i) < '0' || str.charAt(i) > '9'))) {
            return 0;
        }
        
        if (str.charAt(i) == '+' || str.charAt(i) == '-') {
            sign = str.charAt(i) == '-' ? -1 : 1;
            i++;
        }
        
        if (i >= n || (str.charAt(i) < '0' && str.charAt(i) > '9')) {
            return 0;
        }
        
        while (i < n && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
        	if (i < n && firstDigit && str.charAt(i) == '0') {
        		i++;
        		continue;
        	} 
            sb.append(str.charAt(i));
            i++;
            firstDigit = false;
            if (sb.length() > LIMIT) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
        }
        
        if (sb.length() == 0) {
        	return 0;
        }
        long result = sign * Long.parseLong(sb.toString());
        
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else {
            return (int)result;
        }       
    }
    */
	   
    
    public static void main(String[] args) {
    	String test1 = "123abc";
    	String test2 = "1";
    	String test3 = "words and 987";
    	String test4 = "+-2";
    	String test5 = "  0000000000012345678";
    	String test6 = "   +0 123";
    	String test7 = "2147483648";
    	

    	System.out.println(myAtoi(test1));
    	System.out.println(myAtoi(test2));
    	System.out.println(myAtoi(test3));
    	System.out.println(myAtoi(test4));
    	System.out.println(myAtoi(test5));
    	System.out.println(myAtoi(test6));
    	System.out.println(myAtoi(test7));
    }
}
