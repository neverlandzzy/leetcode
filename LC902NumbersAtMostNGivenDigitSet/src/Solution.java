public class Solution {
    /**
     * Given an array of digits which is sorted in non-decreasing order. You can write numbers using each
     * digits[i] as many times as we want. For example, if digits = ['1','3','5'], we may write numbers
     * such as '13', '551', and '1351315'.
     *
     * Return the number of positive integers that can be generated that are less than or equal to a given
     * integer n.
     *
     * Example 1:
     *
     * Input: digits = ["1","3","5","7"], n = 100
     * Output: 20
     * Explanation:
     * The 20 numbers that can be written are:
     * 1, 3, 5, 7, 11, 13, 15, 17, 31, 33, 35, 37, 51, 53, 55, 57, 71, 73, 75, 77.
     *
     * Example 2:
     *
     * Input: digits = ["1","4","9"], n = 1000000000
     * Output: 29523
     * Explanation:
     * We can write 3 one digit numbers, 9 two digit numbers, 27 three digit numbers,
     * 81 four digit numbers, 243 five digit numbers, 729 six digit numbers,
     * 2187 seven digit numbers, 6561 eight digit numbers, and 19683 nine digit numbers.
     * In total, this is 29523 integers that can be written using the digits array.
     *
     * Example 3:
     *
     * Input: digits = ["7"], n = 8
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= digits.length <= 9
     * digits[i].length == 1
     * digits[i] is a digit from '1' to '9'.
     * All the values in digits are unique.
     * digits is sorted in non-decreasing order.
     * 1 <= n <= 10^9
     */

    // 以 digits = {1, 3, 5, 7}, n = 324 为例：
    // 情况1：当valid 数字位数k小于3时，可以有4^k种可能
    // 情况2：当valid 数字位数k等于3时，令dp[i]=从第i位起valid的数，dp[0] = 3位满足小于等于324的数，dp[1] = 2位满足小于等于24的数，
    //       dp[2] = 1位满足小于等于4的数，dp[3] = 0位满足小于等于0的数，为1
    //       (1) 当第一位小于3时，可以有4^(k-1)种可能，即第二位起可以是任何数：dp[i] = 4^(k-1)
    //       (2) 当第一位等于3时，则从下一位开始比较: dp[i] = dp[i + 1]
    //       (3) 当第一位大于3时，没有valid的数: dp[i] = 0
    //       dp[0] 为要求的值，base： dp[k] = 1

    public static int atMostNGivenDigitSet(String[] digits, int n) {
        String s = String.valueOf(n);
        int k = s.length();
        int[] dp = new int[k + 1];
        dp[k] = 1;

        // 情况2：
        for (int i = k - 1; i >= 0; i--) {
            int si = s.charAt(i) - '0';

            for (String d: digits) {
                if (Integer.parseInt(d) < si) {
                    dp[i] += Math.pow(digits.length, k - i - 1);
                } else if (Integer.parseInt(d) == si) {
                    dp[i] += dp[i + 1];
                }
            }
        }

        // 情况1：
        for (int i = 1; i < k; i++) {
            dp[0] += Math.pow(digits.length, i);
        }

        return dp[0];
    }

    public static void main(String[] args) {
        String[] test1 = {"1","3","5","7"};
        String[] test2 = {"1","4","9"};
        String[] test3 = {"7"};

        System.out.println(atMostNGivenDigitSet(test1, 100));
        System.out.println(atMostNGivenDigitSet(test2, 1000000000));
        System.out.println(atMostNGivenDigitSet(test3, 8));
    }
}
