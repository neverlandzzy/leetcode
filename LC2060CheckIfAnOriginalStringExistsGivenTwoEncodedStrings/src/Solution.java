public class Solution {
    /**
     * An original string, consisting of lowercase English letters, can be encoded by the following steps:
     *
     * Arbitrarily split it into a sequence of some number of non-empty substrings.
     * Arbitrarily choose some elements (possibly none) of the sequence, and replace each with its length (as a numeric string).
     * Concatenate the sequence as the encoded string.
     * For example, one way to encode an original string "abcdefghijklmnop" might be:
     *
     * Split it as a sequence: ["ab", "cdefghijklmn", "o", "p"].
     * Choose the second and third elements to be replaced by their lengths, respectively. The sequence becomes ["ab", "12", "1", "p"].
     * Concatenate the elements of the sequence to get the encoded string: "ab121p".
     * Given two encoded strings s1 and s2, consisting of lowercase English letters and digits 1-9 (inclusive), return true if there exists an original string that could be encoded as both s1 and s2. Otherwise, return false.
     *
     * Note: The test cases are generated such that the number of consecutive digits in s1 and s2 does not exceed 3.
     *
     *
     *
     * Example 1:
     *
     * Input: s1 = "internationalization", s2 = "i18n"
     * Output: true
     * Explanation: It is possible that "internationalization" was the original string.
     * - "internationalization"
     *   -> Split:       ["internationalization"]
     *   -> Do not replace any element
     *   -> Concatenate:  "internationalization", which is s1.
     * - "internationalization"
     *   -> Split:       ["i", "nternationalizatio", "n"]
     *   -> Replace:     ["i", "18",                 "n"]
     *   -> Concatenate:  "i18n", which is s2
     *
     * Example 2:
     *
     * Input: s1 = "l123e", s2 = "44"
     * Output: true
     * Explanation: It is possible that "leetcode" was the original string.
     * - "leetcode"
     *   -> Split:      ["l", "e", "et", "cod", "e"]
     *   -> Replace:    ["l", "1", "2",  "3",   "e"]
     *   -> Concatenate: "l123e", which is s1.
     * - "leetcode"
     *   -> Split:      ["leet", "code"]
     *   -> Replace:    ["4",    "4"]
     *   -> Concatenate: "44", which is s2.
     *
     * Example 3:
     *
     * Input: s1 = "a5b", s2 = "c5b"
     * Output: false
     * Explanation: It is impossible.
     * - The original string encoded as s1 must start with the letter 'a'.
     * - The original string encoded as s2 must start with the letter 'c'.
     *
     *
     * Constraints:
     *
     * 1 <= s1.length, s2.length <= 40
     * s1 and s2 consist of digits 1-9 (inclusive), and lowercase English letters only.
     * The number of consecutive digits in s1 and s2 does not exceed 3.
     */

    // https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings/solutions/1550342/java-clean-dfs-memo/
    // dp[i][j][diff]: the diff at s1[i] and s2[j]
    // Since "the number of consecutive digits in s1 and s2 does not exceed 3", max 3 digits can be place 999
    // so difference between s1[i] and s2[j] can be -999 to 999 == 2000.
    // diff > 0 meaning we need to pick more chars in s1
    // diff < 0 meaning we need to pick more chars in s2

    public static boolean possiblyEquals(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int MAX_DIFF = 2000;

        Boolean[][][] dp = new Boolean[n1 + 1][n2 + 1][MAX_DIFF];
        return dfs(s1.toCharArray(), s2.toCharArray(), dp, 0, 0, 0);
    }

    private static boolean dfs(char[] s1, char[] s2, Boolean[][][] dp, int i, int j, int diff) {
        int n1 = s1.length;
        int n2 = s2.length;

        if (i == n1 && j == n2) {
            return diff == 0;
        }

        if (dp[i][j][diff + 1000] != null) {
            return dp[i][j][diff + 1000];
        }

        // s1[i] 和s2[j]都是字母且相等
        if (i < n1 && j < n2 && diff == 0 && s1[i] == s2[j]) {
            if (dfs(s1, s2, dp, i + 1, j + 1, diff)) {
                dp[i][j][1000] = true;
                return true;
            }
        }

        // s1[i]是字母
        if (i < n1 && !Character.isDigit(s1[i]) && diff > 0) {
            if (dfs(s1, s2, dp, i + 1, j, diff - 1)) {
                dp[i][j][diff + 1000] = true;
                return true;
            }
        }

        // s2[j]是字母
        if (j < n2 && !Character.isDigit(s2[j]) && diff < 0) {
            if (dfs(s1, s2, dp, i, j + 1, diff + 1)) {
                dp[i][j][diff + 1000] = true;
                return true;
            }
        }

        int digits = 0;

        // s1[i]是数字，wildcard matching
        for (int k = i; k < n1 && Character.isDigit(s1[k]); k++) {
            digits = digits * 10 + (s1[k] - '0');
            if (dfs(s1, s2, dp, k + 1, j, diff - digits)) {
                dp[i][j][diff + 1000] = true;
                return true;
            }
        }

        digits = 0;
        // s2[j]是数字，wildcard matching
        for (int k = j; k < n2 && Character.isDigit(s2[k]); k++) {
            digits = digits * 10 + (s2[k] - '0');
            if (dfs(s1, s2, dp, i, k + 1, diff + digits)) {
                dp[i][j][diff + 1000] = true;
                return true;
            }
        }

        dp[i][j][diff + 1000] = false;
        return false;
    }

    public static void main(String[] args) {
        String s11 = "internationalization";
        String s12 = "i18n";
        String s21 = "l123e";
        String s22 = "44";
        String s31 = "a5b";
        String s32 = "c5b";

        System.out.println(possiblyEquals(s11, s12));
        System.out.println(possiblyEquals(s21, s22));
        System.out.println(possiblyEquals(s31, s32));
    }
}
