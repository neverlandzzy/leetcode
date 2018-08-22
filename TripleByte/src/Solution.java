import java.util.Stack;


public class Solution {
	
	public static int palindromes(String str) {
		int count = 0;
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == '(') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					count++;
				} else {
					stack.pop();
				}
			}
		}
		
		return count + stack.size();
	}
	
	
    public static int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        // dp[0][0] = 0;
        
        for (int i = 1; i <= m; i++) {
        	dp[i][0] = dp[i - 1][0] + s1.codePointAt(i - 1);
        }
        
        for (int i = 1; i <= n; i++) {
        	dp[0][i] = dp[0][i - 1] + s2.codePointAt(i - 1);
        }
        
        for (int i = 1; i <= m; i++) {
        	for (int j = 1; j <= n; j++) {
        		if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
        			dp[i][j] = dp[i - 1][j - 1];
        		} else {
        			dp[i][j] = Math.min(dp[i - 1][j] + s1.codePointAt(i - 1), dp[i][j - 1] + s2.codePointAt(j - 1));
        		}
        	}
        }
        
        return dp[m][n];
    }
    
	public static void main(String[] args) {	
		System.out.println(minimumDeleteSum("at", "cat"));
		System.out.println(minimumDeleteSum("boat", "got"));
		System.out.println(minimumDeleteSum("thought", "sloughs"));
	}
}
