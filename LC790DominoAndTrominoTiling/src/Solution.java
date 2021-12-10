public class Solution {

    /**
     * You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
     *
     * Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large,
     * return it modulo 10^9 + 7.
     *
     * In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are
     * two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied
     * by a tile.
     *
     *
     * Example 1:
     *
     * Input: n = 3
     * Output: 5
     * Explanation: The five different ways are show above.
     *
     * Example 2:
     *
     * Input: n = 1
     * Output: 1
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     */


    // https://leetcode.com/problems/domino-and-tromino-tiling/discuss/116612/Easy-to-understand-O(n)-solution-with-Drawing-Picture-Explanation!
    // f(n) = f(n - 1) + f(n - 2) + 2*p(n - 2)
    // p(n) = f(n - 1) + p(n - 1)
    // f(0) = 0, f(1) = 1, f(2) = 2
    // p(0) = 0, p(1) = 1, p(2) = 2
    // (f: fully covered = g, p: partially covered = u)

    public static int numTilings(int n) {
        if (n <= 2) {
            return n;
        }

        int mod = 1_000_000_007;

        long[] f = new long[n + 1];
        long[] p = new long[n + 1];

        f[1] = 1;
        f[2] = 2;
        p[1] = 1;
        p[2] = 2;

        for (int i = 3; i <= n; i++) {
            f[i] = (f[i - 1] + f[i - 2] + 2 * p[i - 2]) % mod;
            p[i] = (f[i - 1] + p[i - 1]) % mod;
        }

        return (int) f[n];
    }

    public static void main(String[] args) {
        System.out.println(numTilings(3));
        System.out.println(numTilings(1));
    }
}
