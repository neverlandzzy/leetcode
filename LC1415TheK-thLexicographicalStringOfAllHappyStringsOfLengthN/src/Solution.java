import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    /**
     * A happy string is a string that:
     *
     * consists only of letters of the set ['a', 'b', 'c'].
     * s[i] != s[i + 1] for all values of i from 1 to s.length - 1 (string is 1-indexed).
     * For example, strings "abc", "ac", "b" and "abcbabcbcb" are all happy strings and strings "aa", "baa" and "ababbc" are not happy strings.
     *
     * Given two integers n and k, consider a list of all happy strings of length n sorted in lexicographical order.
     *
     * Return the kth string of this list or return an empty string if there are less than k happy strings of length n.
     *
     * Example 1:
     *
     * Input: n = 1, k = 3
     * Output: "c"
     * Explanation: The list ["a", "b", "c"] contains all happy strings of length 1. The third string is "c".
     *
     * Example 2:
     *
     * Input: n = 1, k = 4
     * Output: ""
     * Explanation: There are only 3 happy strings of length 1.
     *
     * Example 3:
     *
     * Input: n = 3, k = 9
     * Output: "cab"
     * Explanation: There are 12 different happy string of length 3 ["aba", "abc", "aca", "acb", "bab", "bac", "bca", "bcb", "cab", "cac", "cba", "cbc"]. You will find the 9th string = "cab"
     *
     * Example 4:
     *
     * Input: n = 2, k = 7
     * Output: ""
     *
     * Example 5:
     *
     * Input: n = 10, k = 100
     * Output: "abacbabacb"
     *
     * Constraints:
     *
     * 1 <= n <= 10
     * 1 <= k <= 100
     */

    // Solution 1: My Solution - Backtracking - O(3^n)
    /*
    public static String getHappyString(int n, int k) {
        List<String> list = allHappyString(n);

        return k > list.size() ? "" : list.get(k - 1);
    }

    private static List<String> allHappyString(int n) {
        List<String> result = new ArrayList<>();
        char[] chars = {'a', 'b', 'c'};

        helper(result, chars, new StringBuilder(), n);

        Collections.sort(result);
        return result;
    }

    private static void helper(List<String> result, char[] chars, StringBuilder sb, int n) {
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }

        for (char aChar : chars) {
            if (sb.length() == 0 || sb.charAt(sb.length() - 1) != aChar) {
                sb.append(aChar);
                helper(result, chars, sb, n);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
    */

    // Solution 2: Math - O(n)
    // https://leetcode.com/problems/the-k-th-lexicographical-string-of-all-happy-strings-of-length-n/discuss/585590/C%2B%2BJava-DFS-and-Math
    // For the string of size n, we can build 3 * pow(2, n - 1) strings.
    // So, if k <= pow(2, n - 1), then the first letter is a, k <= 2 * pow(2, n - 1) - then b, otherwise c.
    // We can also return empty string right away if k > 3 * pow(2, n - 1).
    // 因为除了第一位有3种选择，后面n-1位每位只有2种选择(不能和前一位的字符重复)
    public static String getHappyString(int n, int k) {

    }

    public static void main(String[] args) {
        System.out.println(getHappyString(1, 3));
        System.out.println(getHappyString(1, 4));
        System.out.println(getHappyString(3, 9));
        System.out.println(getHappyString(2, 7));
        System.out.println(getHappyString(10, 100));
    }
}
