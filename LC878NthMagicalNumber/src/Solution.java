public class Solution {
    /**
     * A positive integer is magical if it is divisible by either a or b.
     *
     * Given the three integers n, a, and b, return the nth magical number. Since the answer may be very large,
     * return it modulo 10^9 + 7.
     *
     * Example 1:
     *
     * Input: n = 1, a = 2, b = 3
     * Output: 2
     *
     * Example 2:
     *
     * Input: n = 4, a = 2, b = 3
     * Output: 6
     *
     * Example 3:
     *
     * Input: n = 5, a = 2, b = 4
     * Output: 10
     *
     * Example 4:
     *
     * Input: n = 3, a = 6, b = 4
     * Output: 8
     *
     *
     * Constraints:
     *
     * 1 <= n <= 10^9
     * 2 <= a, b <= 4 * 10^4
     */

    // 1. lcm(A, B), 最小公倍数, gcd(A, B), 最大公约数 --> L = A*B/gcd(A, B) (公式： https://en.wikipedia.org/wiki/Least_common_multiple)
    // 2. f(x)：小于或等于x的magical number的个数 --> f(x) = x/A + x/B - x/L --> 可以被A整除的数加上可以被B整除的数，再减去可以同时被A，B整除的数
    // 3. 答案应该在min(A,B)和 N*min(A,B)之间
    // 4. 所以通过binary search找最小的x满足x/A + x/B - x/L >= N
    // Time: O(log(N * min(A,B))).

    public static int nthMagicalNumber(int n, int a, int b) {
        int mod = 1_000_000_007;
        int lcm = a / gcd(a, b) * b;

        long start = Math.min(a, b);
        long end = (long) Math.min(a, b) * n;

        while (start < end) {
            long mid = start + (end - start) / 2;

            if (mid / a + mid / b - mid / lcm < n) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return (int)(start % mod);
    }
    public static int gcd(int x, int y) {
        if (x == 0) return y;
        return gcd(y % x, x);
    }
    public static void main(String[] args) {
        System.out.println(nthMagicalNumber(1, 2, 3));
        System.out.println(nthMagicalNumber(4, 2, 3));
        System.out.println(nthMagicalNumber(5, 2, 4));
        System.out.println(nthMagicalNumber(3, 6, 4));

    }
}
