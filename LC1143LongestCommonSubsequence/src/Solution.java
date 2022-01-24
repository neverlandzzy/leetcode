public class Solution {
    /**
     * Given two strings text1 and text2, return the length of their longest common subsequence.
     * If there is no common subsequence, return 0.
     *
     * A subsequence of a string is a new string generated from the original string with some characters
     * (can be none) deleted without changing the relative order of the remaining characters.
     *
     * For example, "ace" is a subsequence of "abcde".
     * A common subsequence of two strings is a subsequence that is common to both strings.
     *
     *
     *
     * Example 1:
     *
     * Input: text1 = "abcde", text2 = "ace"
     * Output: 3
     * Explanation: The longest common subsequence is "ace" and its length is 3.
     * Example 2:
     *
     * Input: text1 = "abc", text2 = "abc"
     * Output: 3
     * Explanation: The longest common subsequence is "abc" and its length is 3.
     * Example 3:
     *
     * Input: text1 = "abc", text2 = "def"
     * Output: 0
     * Explanation: There is no such common subsequence, so the result is 0.
     *
     *
     * Constraints:
     *
     * 1 <= text1.length, text2.length <= 1000
     * text1 and text2 consist of only lowercase English characters.
     */

    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        int[][] dp = new int[m][n];

        if (text1.charAt(0) == text2.charAt(0)) {
            dp[0][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] = text1.charAt(0) == text2.charAt(i) ? 1 : dp[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "ace"));
        System.out.println(longestCommonSubsequence("abc", "abc"));
        System.out.println(longestCommonSubsequence("abc", "def"));
        System.out.println(longestCommonSubsequence("caab", "aa"));
    }
}
