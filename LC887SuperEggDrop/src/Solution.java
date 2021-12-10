public class Solution {

    /**
     * You are given k identical eggs and you have access to a building with n floors labeled from 1 to n.
     *
     * You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break,
     * and any egg dropped at or below floor f will not break.
     *
     * Each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it.
     * However, if the egg does not break, you may reuse it in future moves.
     *
     * Return the minimum number of moves that you need to determine with certainty what the value of f is.
     *
     *
     *
     * Example 1:
     *
     * Input: k = 1, n = 2
     * Output: 2
     * Explanation:
     * Drop the egg from floor 1. If it breaks, we know that f = 0.
     * Otherwise, drop the egg from floor 2. If it breaks, we know that f = 1.
     * If it does not break, then we know f = 2.
     * Hence, we need at minimum 2 moves to determine with certainty what the value of f is.
     *
     *
     * Example 2:
     *
     * Input: k = 2, n = 6
     * Output: 3
     *
     *
     * Example 3:
     *
     * Input: k = 3, n = 14
     * Output: 4
     *
     *
     * Constraints:
     *
     * 1 <= k <= 100
     * 1 <= n <= 10^4
     */

    // https://leetcode.com/problems/super-egg-drop/discuss/159055/Java-DP-solution-from-O(KN2)-to-O(KNlogN)
    // State is (K, N): K eggs and N floors left. When we drop an egg from floor i, it either survives and we have state (K, N-i),
    //                  or it breaks and we have state (K-1, i-1).
    //                  要保证找到certainty，就要保证move是max(dp[k - 1][i - 1], dp[k][n - i])，然后在所有的可能取值中选最小的
    //                  dp[k][n] = min(1 + max(dp[k - 1][i - 1], dp[k][n - i])) i = 1...n


    // Brute Force: Time: O(KN^2)
    public static int superEggDrop(int k, int n) {
        int[][] cache = new int[k + 1][n + 1];
        return helper(k, n, cache);
    }

    private static int helperBruteForce(int k, int n, int[][] cache) {
        if (n <= 1) {
            return n;
        }

        if (k == 1) {
            return n;
        }

        if (cache[k][n] > 0) {
            return cache[k][n];
        }

        int min = n;
        for (int i = 1; i <= n; i++) {
            int survives = helper(k, n - i, cache);
            int breaks = helper(k - 1, i - 1, cache);
            min = Math.min(min, Math.max(survives, breaks) + 1);
        }

        cache[k][n] = min;

        return min;
    }

    // Optimize with binary search Time: O(KNlogN)
    // when i increases, survives = helper(k, n - i, cache) goes down, breaks = helper(k - 1, i - 1, cache) goes up
    // So we can use binary search to get the minimum Math.max(survives, breaks) + 1, when survives and breaks are as close as possible
    private static int helper(int k, int n, int[][] cache) {
        if (n <= 1) {
            return n;
        }

        if (k == 1) {
            return n;
        }

        if (cache[k][n] > 0) {
            return cache[k][n];
        }

        int low = 1;
        int high = n;
        int result = n;

        while (low < high) {
            int mid = low + (high - low) / 2;

            int survives = helper(k, n - mid, cache);
            int breaks = helper(k - 1, mid - 1, cache);
            result = Math.min(result, Math.max(survives, breaks) + 1);

            if (survives == breaks) {
                break;
            } else if (survives > breaks) {
                low = mid + 1;
            } else {
                high = mid;
            }
         }

        cache[k][n] = result;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(superEggDrop(1, 2));
        System.out.println(superEggDrop(2, 6));
        System.out.println(superEggDrop(3, 14));
    }
}
