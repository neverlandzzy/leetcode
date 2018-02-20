
public class Solution {
	/*
	 * A message containing letters from A-Z is being encoded to numbers using the following mapping way:
	 * 
	 * 'A' -> 1
	 * 'B' -> 2
	 * ...
	 * 'Z' -> 26
	 * Beyond that, now the encoded string can also contain the character '*', which can be treated as one of the numbers from 1 to 9.
	 * 
	 * Given the encoded message containing digits and the character '*', return the total number of ways to decode it.
	 * 
	 * Also, since the answer may be very large, you should return the output mod 10^9 + 7.
	 * 
	 * Example 1:
	 * Input: "*"
	 * Output: 9
	 * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
	 * 
	 * Example 2:
	 * Input: "1*"
	 * Output: 9 + 9 = 18
	 * 
	 * Note:
	 * 1. The length of the input string will fit in range [1, 10^5].
	 * 2. The input string will only contain the character '*' and digits '0' - '9'.
	 */
	
	//注意： 
	//   1. dp[]要用long[]，否则会错误
	//   2. dp[i] = (dp[i] + dp[i - 2] * 9) % M; 不能写成 dp[i] += (dp[i - 2] * 9) % M
    public static int numDecodings(String s) {
        if (s.charAt(0) == '0') {
        	return 0;
        }
        
        int M = 1000000007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        
        if (s.charAt(0) == '*') {
        	dp[1] = 9;
        } else {
        	dp[1] = 1;
        }
        
        for (int i = 2; i <= n; i++) {
        	if (s.charAt(i - 1) == '*') {
        		dp[i] = dp[i - 1] * 9;
        	
	        	if (s.charAt(i - 2) == '1') {
	        		dp[i] = (dp[i] + dp[i - 2] * 9) % M;
	        	} else if (s.charAt(i - 2) == '2') {
	        		dp[i] = (dp[i] + dp[i - 2] * 6) % M;
	        	} else if (s.charAt(i - 2) == '*') {
	        		dp[i] = (dp[i] + dp[i - 2] * 15) % M; // 11 - 19, 21 - 26
	        	}
	        	
        	} else {
        		if (s.charAt(i - 1) != '0') {
        			dp[i] = dp[i - 1];
        		} 
        		
                if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                    dp[i] = (dp[i] + dp[i - 2]) % M;
                } else if (s.charAt(i - 2) == '*') {
                	dp[i] = (dp[i] + (s.charAt(i - 1) <= '6' ? 2 : 1) * dp[i - 2]) % M;
                }
        	}
        }
        
        return (int)dp[n];
    }
    
    public static void main(String[] args) {
    	System.out.println(numDecodings("*"));
    	System.out.println(numDecodings("1*"));
    	System.out.println(numDecodings("*********"));
    	System.out.println(numDecodings("**********1111111111"));
	}
}
