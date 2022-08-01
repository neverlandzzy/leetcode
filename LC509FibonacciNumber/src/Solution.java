public class Solution {
    /**
     * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number
     * is the sum of the two preceding ones, starting from 0 and 1. That is,
     *
     * F(0) = 0, F(1) = 1
     * F(n) = F(n - 1) + F(n - 2), for n > 1.
     * Given n, calculate F(n).
     *
     *
     *
     * Example 1:
     *
     * Input: n = 2
     * Output: 1
     * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
     *
     * Example 2:
     *
     * Input: n = 3
     * Output: 2
     * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
     *
     * Example 3:
     *
     * Input: n = 4
     * Output: 3
     * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
     *
     *
     * Constraints:
     *
     * 0 <= n <= 30
     */

    // Solution 1: Time: O(n), Space: O(1)
    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }

        int fib0 = 0;
        int fib1 = 1;
        int fib = 0;

        for (int i = 2; i <= n; i++) {
            fib = fib0 + fib1;
            fib0 = fib1;
            fib1 = fib;
        }

        return fib;
    }

    // Solution 2: Time: O(n), Space: O(n)
//    public static int fib(int n) {
//        if (n <= 1) {
//            return n;
//        }
//
//        int[] fibs = new int[n + 1];
//        fibs[0] = 0;
//        fibs[1] = 1;
//
//        for (int i = 2; i <= n; i++) {
//            fibs[i] = fibs[i - 1] + fibs[i - 2];
//        }
//
//        return fibs[n];
//    }

    public static void main(String[] args) {
        System.out.println(fib(2));
        System.out.println(fib(3));
        System.out.println(fib(4));
    }
}
