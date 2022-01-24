public class Solution {
    /**
     * Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
     *
     * In one step, you can delete exactly one character in either string.
     *
     * Example 1:
     *
     * Input: word1 = "sea", word2 = "eat"
     * Output: 2
     * Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
     *
     * Example 2:
     *
     * Input: word1 = "leetcode", word2 = "etco"
     * Output: 4
     *
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length <= 500
     * word1 and word2 consist of only lowercase English letters.
     */

    // Similar to LC1143
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m][n];

        if (word1.charAt(0) == word2.charAt(0)) {
            dp[0][0] = 1;
        }

        for (int i = 1; i < m; i++) {
            dp[i][0] = word1.charAt(i) == word2.charAt(0) ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < n; i++) {
            dp[0][i] = word1.charAt(0) == word2.charAt(i) ? 1 : dp[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return word1.length() + word2.length() - 2 * dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("sea", "eat"));
        System.out.println(minDistance("leetcode", "etco"));
    }
}
