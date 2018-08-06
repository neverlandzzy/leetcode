import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequence of W.
	 * 
	 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length 
	 * windows, return the one with the left-most starting index.
	 * 
	 * Example 1:
	 * Input: 
	 * S = "abcdebdde", T = "bde"
	 * Output: "bcde"
	 * Explanation: 
	 * "bcde" is the answer because it occurs before "bdde" which has the same length.
	 * "deb" is not a smaller window because the elements of T in the window must occur in order.
	 * 
	 * Note:
	 * 
	 * 1. All the strings in the input will only contain lowercase letters.
	 * 2. The length of S will be in the range [1, 20000].
	 * 3. The length of T will be in the range [1, 100].
	 */
	
	// 注意描述与 LC76的区别。 若不能用类似LC76的two pointer做法。因为本题需要考虑S子串中字符的位置关系。e.g. S = bedbede T = bde 很难处理
	// 本题适合DP解法
	// https://discuss.leetcode.com/topic/110535/c-dp-with-explanation-o-st-53ms
	// https://discuss.leetcode.com/topic/110881/java-super-easy-dp-solution-o-mn
	
	// dp[i][j] is starting index k of the shortest postfix of S[0, i - 1], such that T[0, j - 1] is a subsequence of S[k, i - 1]. 
	// The goal is the substring with length of min(i-dp[i][n]) for all i < m,  where m is S.size() and n is T.size()
	// 因为dp[i][n]记录的当T是S[0, i]的subsequence时， S中最大的starting index。 因此i-dp[i][n] 就是S[0,i]中minimum substring的长度。在0 ~ n - 1
	// 中找到最小的，即为答案
	// 起始状态：dp[i][0] = i + 1, 即T为""时，可以是S中每一位字符的subsequence.
	
	// Time: O(m * n), Space: O(m * n)

	// A more straightforward way, same idea
	
	public static String minWindow(String S, String T) {
    	int m = S.length();
    	int n = T.length();
    	
    	int[][] dp = new int[m][n];
    	
    	// S, T both have 1 char, if !match, fill -1 means we can't find a substring for S(0) & T(0)
    	if (S.charAt(0) == T.charAt(0)) {
    		dp[0][0] = 0;
    	} else {
    		dp[0][0] = -1;
    	}
    	
    	for (int i = 1; i < m; i++) {
    		if (S.charAt(i) == T.charAt(0)) {
    			// largest starting index matches only first char in T
    			dp[i][0] = i;
    		} else {   				
    			dp[i][0] = dp[i - 1][0];
    		}
    	}
    	
    	// if j > i, M[i][j] is always -1, because we can't find a substring of a shorter string matches a longer string
    	for (int i = 1; i < n; i++) {
    		dp[0][i] = -1;
    	}
    	
    	for (int i = 1; i < m; i++) {
    		for (int j = 1; j < n; j++) {
    			if (S.charAt(i) == T.charAt(j)) {
    				dp[i][j] = dp[i - 1][j - 1];
    			} else {
    				dp[i][j] = dp[i - 1][j];
    			}
    		}
    	}
    	
    	int start = 0;
    	int len = m + 1;
    	
    	for (int i = 0; i < m; i++) {
    		if (dp[i][n - 1] != -1) {
    			if (i - dp[i][n - 1] + 1 < len) {
    				len = i - dp[i][n - 1] + 1;
    				start = dp[i][n - 1];
    			}
    		}
    	}
    	
    	return len == m + 1 ? "" : S.substring(start, start + len);
	}
	
	// hard to explain why initialize with dp[i][0] = i + 1; and why start = dp[i][n] - 1;
	/*
    public static String minWindow(String S, String T) {
    	int m = S.length();
    	int n = T.length();
    	
    	int[][] dp = new int[m + 1][n + 1];
    	
    	for (int i = 0; i <= m; i++) {
    		dp[i][0] = i + 1;
    	}

    	for (int i = 1; i <= m; i++) {
    		for (int j = 1; j <= n; j++) {
    			if (S.charAt(i - 1) == T.charAt(j - 1)) {
    				dp[i][j] = dp[i - 1][j - 1];
    			} else {
    				dp[i][j] = dp[i - 1][j];
    			}
    		}
    	}

    	int start = 0;
    	int len = m + 1;
    	

    	for (int i = 0; i <= m; i++) {
    		if (dp[i][n] != 0) {
    			if (i - dp[i][n] + 1 < len) {
    				len = i - dp[i][n] + 1;
    				start = dp[i][n] - 1;
    			}
    		}
    	}

    	return len == m + 1 ? "" : S.substring(start, start + len); 
    }
 	*/
    
    public static void main(String[] args) {
    	/*
    	System.out.println(minWindow("abbcdebdde", "bde"));


    	System.out.println(minWindow("jmeqksfrsdcmsiwvaovztaqenprpvnbstl", "k"));
    	
    	System.out.println(minWindow("cnhczmccqouqadqtmjjzl", "mq"));

    	System.out.println(minWindow("cnhczmccqouqadqtmjjzl", "dq"));
    	System.out.println(minWindow("cnhczmccqouqadqtmjjzl", "mm"));
    	
    	System.out.println(minWindow("hpsrhgogezyfrwfrejytjkzvgpjnqil", "ggj"));
    	*/
    	
    	System.out.println(minWindow("fgrqsqsnodwmxzkzxwqegkndaa", "fnok"));
    	
	}
}
