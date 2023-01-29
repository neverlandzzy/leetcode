import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
     *
     * We can rotate digits of a number by 180 degrees to form new digits.
     *
     * When 0, 1, 6, 8, and 9 are rotated 180 degrees, they become 0, 1, 9, 8, and 6 respectively.
     * When 2, 3, 4, 5, and 7 are rotated 180 degrees, they become invalid.
     * Note that after rotating a number, we can ignore leading zeros.
     *
     * For example, after rotating 8000, we have 0008 which is considered as just 8.
     * Given an integer n, return the number of confusing numbers in the inclusive range [1, n].
     *
     *
     *
     * Example 1:
     *
     * Input: n = 20
     * Output: 6
     * Explanation: The confusing numbers are [6,9,10,16,18,19].
     * 6 converts to 9.
     * 9 converts to 6.
     * 10 converts to 01 which is just 1.
     * 16 converts to 91.
     * 18 converts to 81.
     * 19 converts to 61.
     *
     *
     * Example 2:
     *
     * Input: n = 100
     * Output: 19
     * Explanation: The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
     *
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     */

    // https://leetcode.com/problems/confusing-number-ii/solutions/1365889/python-backtracking-solution-clear-explanation-clean-concise/
    public static int confusingNumberII(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);

        return dfs(map, 0, 0, 1, n);
    }

    // 用map中的digit和其对应的rotatedDigit 来构造confusingNumber
    // num <---> rotateNum
    // 当生成一个 num * 10 + digit， 对应的rotateNum应该是rotatedDigit * unit + rotatedNum e.g. 18 <---> 81  1*10 + 8 <--> 8*10 + 1
    private static int dfs(Map<Integer, Integer> map, long num, long rotatedNum, int unit, int n) {
        int count = 0;

        if (num != rotatedNum) {
            count++;
        }

        for (int digit: map.keySet()) {
            int rotatedDigit = map.get(digit);

            if (digit == 0 && num == 0) {
                continue;
            }

            if (num * 10 + digit > n) {
                break;
            }

            count += dfs(map, num * 10 + digit, rotatedDigit * unit + rotatedNum, unit * 10, n);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(confusingNumberII(20));
        System.out.println(confusingNumberII(100));
    }
}
