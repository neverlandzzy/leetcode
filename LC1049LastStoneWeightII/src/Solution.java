public class Solution {
    /**
     * You are given an array of integers stones where stones[i] is the weight of the ith stone.
     *
     * We are playing a game with the stones. On each turn, we choose any two stones and smash them together. Suppose the stones have weights x and y with x <= y.
     * The result of this smash is:
     *
     * If x == y, both stones are destroyed, and
     * If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
     * At the end of the game, there is at most one stone left.
     *
     * Return the smallest possible weight of the left stone. If there are no stones left, return 0.
     *
     *
     * Example 1:
     *
     * Input: stones = [2,7,4,1,8,1]
     * Output: 1
     * Explanation:
     * We can combine 2 and 4 to get 2, so the array converts to [2,7,1,8,1] then,
     * we can combine 7 and 8 to get 1, so the array converts to [2,1,1,1] then,
     * we can combine 2 and 1 to get 1, so the array converts to [1,1,1] then,
     * we can combine 1 and 1 to get 0, so the array converts to [1], then that's the optimal value.
     *
     * Example 2:
     *
     * Input: stones = [31,26,33,21,40]
     * Output: 5
     *
     *
     * Constraints:
     *
     * 1 <= stones.length <= 30
     * 1 <= stones[i] <= 100
     */

    // Similar to LC494
    // https://leetcode.com/problems/last-stone-weight-ii/discuss/295167/Java-beat-100-with-nice-explanation
    // 1. Partition the array into 2 subsets S1(sum) and S2(sum): (S is the total sum)
    //    S1 + S2 = S
    //    S1 - S2 = diff
    //    diff = S - 2 * S2
    // 2. So we want to find the max S2 so that diff is minimal
    // 3. S2 range is from 0 to S/2
    // 4. dp[i][j] - 在index = i时，是否可以组成和为j(True or False)
    public static int lastStoneWeightII(int[] stones) {
        int S = 0;
        int S2 = 0;

        for (int s: stones) {
            S += s;
        }

        int n = stones.length;

        boolean[][] dp = new boolean[n + 1][S / 2 + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= S / 2; j++) {
                if (dp[i - 1][j] || (j - stones[i - 1] >= 0 && dp[i - 1][j - stones[i - 1]])) {
                    dp[i][j] = true;
                    S2 = Math.max(S2, j);
                }
            }
        }

        return S - 2 * S2;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 7, 4, 1, 8, 1};
        int[] test2 = {31, 26, 33, 21, 40};

        System.out.println(lastStoneWeightII(test1));
        System.out.println(lastStoneWeightII(test2));
    }
}
