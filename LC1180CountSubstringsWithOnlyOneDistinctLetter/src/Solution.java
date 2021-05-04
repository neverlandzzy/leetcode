public class Solution {

    /**
     * Given a string S, return the number of substrings that have only one distinct letter.
     *
     *
     *
     * Example 1:
     *
     * Input: S = "aaaba"
     * Output: 8
     * Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
     * "aaa" occurs 1 time.
     * "aa" occurs 2 times.
     * "a" occurs 4 times.
     * "b" occurs 1 time.
     * So the answer is 1 + 2 + 4 + 1 = 8.
     * Example 2:
     *
     * Input: S = "aaaaaaaaaa"
     * Output: 55
     *
     *
     * Constraints:
     *
     * 1 <= S.length <= 1000
     * S[i] consists of only lowercase English letters.
     */

    // Solution 1: DP Time: O(n), Space: O(1)
    public static int countLetters(String S) {
        int count = 1;
        int result = 1;

        for (int i = 1; i < S.length(); i++) {
            if (S.charAt(i) == S.charAt(i - 1)) {
                count += 1;
            } else {
                count = 1;
            }
            result += count;
        }

        return result;
    }

    // Solution 2: DP Time: O(n), Space: O(n)
//    public static int countLetters(String S) {
//        int[] dp = new int[S.length()];
//        int result = 0;
//        dp[0] = 1;
//
//        for (int i = 1; i < S.length(); i++) {
//            if (S.charAt(i) == S.charAt(i - 1)) {
//                dp[i] = dp[i - 1] + 1;
//            } else {
//                dp[i] = 1;
//            }
//        }
//
//        for (int d: dp) {
//            result += d;
//        }
//
//        return result;
//    }

    public static void main(String[] args) {
        System.out.println(countLetters("aaaba"));
        System.out.println(countLetters("aaaaaaaaaa"));
    }
}
