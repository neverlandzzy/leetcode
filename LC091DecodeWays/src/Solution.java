
public class Solution {
	/*
	 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
	 * 
	 * 'A' -> 1
	 * 'B' -> 2
	 * ...
	 * 'Z' -> 26
	 * 
	 * Given an encoded message containing digits, determine the total number of ways to decode it.
	 * 
	 * For example,
	 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
	 * The number of ways decoding "12" is 2.
	 */
	
	
    public static int numDecodings(String s) {
    	// Solution 1: Time: O(n) Space: O(n)
    	/*
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            
            if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                dp[i] += dp[i - 2];
            }
        }
        
        return dp[n];
    	*/
    	
    	// Solution 2: Time: O(n) Space: O(1)
    	
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        
        int n = s.length();
        
        if (n == 1) {
            return 1;
        }
        
        int pre = 1;
        int cur = 1;
        int result = 0;
        
        for (int i = 2; i <= n; i++) {
            
            result = 0; // *** 容易忘！
            
            if (s.charAt(i - 1) != '0') {
                result += cur;
            }
            
            if (s.charAt(i - 2) == '1' || (s.charAt(i - 2) == '2' && s.charAt(i - 1) <= '6')) {
                result += pre;
            }
            
            pre = cur;
            cur = result;
        }
        
        return result;
    }
    

    
    public static void main(String[] args) {
		System.out.println(numDecodings("301"));
		System.out.println(numDecodings("3129159"));
	}
}
