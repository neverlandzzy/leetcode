public class Solution {
    /**
     * Given two strings s and t, find the number of ways you can choose a non-empty substring of s and replace a single character
     * by a different character such that the resulting substring is a substring of t. In other words, find the number of substrings
     * in s that differ from some substring in t by exactly one character.
     *
     * For example, the underlined substrings in "computer" and "computation" only differ by the 'e'/'a', so this is a valid way.
     *
     * Return the number of substrings that satisfy the condition above.
     *
     * A substring is a contiguous sequence of characters within a string.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "aba", t = "baba"
     * Output: 6
     * Explanation: The following are the pairs of substrings from s and t that differ by exactly 1 character:
     * ("aba", "baba")
     * ("aba", "baba")
     * ("aba", "baba")
     * ("aba", "baba")
     * ("aba", "baba")
     * ("aba", "baba")
     * The underlined portions are the substrings that are chosen from s and t.
     * ​​Example 2:
     * Input: s = "ab", t = "bb"
     * Output: 3
     * Explanation: The following are the pairs of substrings from s and t that differ by 1 character:
     * ("ab", "bb")
     * ("ab", "bb")
     * ("ab", "bb")
     * ​​​​The underlined portions are the substrings that are chosen from s and t.
     * Example 3:
     * Input: s = "a", t = "a"
     * Output: 0
     * Example 4:
     *
     * Input: s = "abe", t = "bbc"
     * Output: 10
     *
     *
     * Constraints:
     *
     * 1 <= s.length, t.length <= 100
     * s and t consist of lowercase English letters only.
     */

    /** Solution 1: DP: O(mn)
     *  https://leetcode.com/problems/count-substrings-that-differ-by-one-character/discuss/918153/Java-DP-solution-O(MN)
     *
     *  d[i + 1][j + 1][k] is the number of substring where s[0, ..., i] and t[0, ..., j] have k different characters.
     *
     *  when s[i] == t[j]:
     *      dp[i+1][j+1][0] = dp[i][j][0] + 1 - the number of substring has 0 different chars + 1
     *      dp[i+1][j+1][1] = dp[i][j][1]     - the number of substring has 1 different chars remains the same
     *  when s[i] != t[j]:
     *      dp[i+1][j+1][1] = dp[i][j][0] + 1; - 当s[i]和t[j]不同时，只有s[0, i-1] 和t[0, j-1]全部相同时， s[0, i]和t[0, j]才会只有一个不同字符
     */

    public static int countSubstrings(String s, String t) {
        int m = s.length();
        int n = t.length();
        int count = 0;
        int[][][] dp = new int[m + 1][n + 1][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i + 1][j + 1][0] = dp[i][j][0] + 1;
                    dp[i+1][j+1][1] = dp[i][j][1];
                } else {
                    dp[i+1][j+1][1] = dp[i][j][0] + 1;
                }
                count += dp[i+1][j+1][1];
            }
        }

        return count;
    }

    /** Solution 2: Brute Force - O(n^3), substring() - O(n), 2 for loop, O(n^2)
     *
     */
//    public static int countSubstrings(String s, String t) {
//        int i = 0;
//        int j = 1;
//        int count = 0;
//
//        while (i < s.length() && j < s.length() + 1 && i < j) {
//            String subS = s.substring(i, j);
//            int m = 0;
//            int n = 1;
//            while (m < t.length() && n < t.length() + 1 && m < n) {
//                String subT = t.substring(m, n);
//                if (diffByOne(subS, subT)) {
//                    count++;
//                } else {
//                }
//                n++;
//                if (n == t.length() + 1) {
//                    m++;
//                    n = m + 1;
//                }
//            }
//
//            j++;
//            if (j == s.length() + 1) {
//                i++;
//                j = i + 1;
//            }
//        }
//
//        return count;
//    }
//
//    private static boolean diffByOne(String s, String t) {
//
//        if (s.length() != t.length()) {
//            return false;
//        }
//
//        int count = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) != t.charAt(i)) {
//                count++;
//            }
//
//            if (count >= 2) {
//                return false;
//            }
//        }
//        return count == 1;
//    }


    public static void main(String[] args) {
        System.out.println(countSubstrings("aba", "baba"));
        System.out.println(countSubstrings("ab", "bb"));
        System.out.println(countSubstrings("a", "a"));
        System.out.println(countSubstrings("abe", "bbc"));
    }
}
