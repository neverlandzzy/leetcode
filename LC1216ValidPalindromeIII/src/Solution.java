public class Solution {
    /**
     * Given a string s and an integer k, return true if s is a k-palindrome.
     *
     * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
     *
     *
     * Example 1:
     *
     * Input: s = "abcdeca", k = 2
     * Output: true
     * Explanation: Remove 'b' and 'e' characters.
     *
     * Example 2:
     *
     * Input: s = "abbababa", k = 1
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists of only lowercase English letters.
     * 1 <= k <= s.length
     */

    // Same as LC516 except line42
    public static boolean isValidPalindrome(String s, int k) {
        int[][] dp = new int[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][i] = 1;

            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][s.length() - 1] + k >= s.length();
    }

    public static void main(String[] args) {
        String test1 = "abcdeca";
        String test2 = "abbababa";

        System.out.println(isValidPalindrome(test1, 2));
        System.out.println(isValidPalindrome(test2, 1));
    }
}
