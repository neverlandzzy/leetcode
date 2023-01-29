import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Alice and Bob take turns playing a game with Alice starting first.
     *
     * In this game, there are n piles of stones. On each player's turn, the player should remove any positive number of stones from a
     * non-empty pile of his or her choice. The first player who cannot make a move loses, and the other player wins.
     *
     * Given an integer array piles, where piles[i] is the number of stones in the ith pile, return true if Alice wins, or false if
     * Bob wins.
     *
     * Both Alice and Bob play optimally.
     *
     * Example 1:
     *
     * Input: piles = [1]
     * Output: true
     * Explanation: There is only one possible scenario:
     * - On the first turn, Alice removes one stone from the first pile. piles = [0].
     * - On the second turn, there are no stones left for Bob to remove. Alice wins.
     *
     * Example 2:
     *
     * Input: piles = [1,1]
     * Output: false
     * Explanation: It can be proven that Bob will always win. One possible scenario is:
     * - On the first turn, Alice removes one stone from the first pile. piles = [0,1].
     * - On the second turn, Bob removes one stone from the second pile. piles = [0,0].
     * - On the third turn, there are no stones left for Alice to remove. Bob wins.
     *
     * Example 3:
     *
     * Input: piles = [1,2,3]
     * Output: false
     * Explanation: It can be proven that Bob will always win. One possible scenario is:
     * - On the first turn, Alice removes three stones from the third pile. piles = [1,2,0].
     * - On the second turn, Bob removes one stone from the second pile. piles = [1,1,0].
     * - On the third turn, Alice removes one stone from the first pile. piles = [0,1,0].
     * - On the fourth turn, Bob removes one stone from the second pile. piles = [0,0,0].
     * - On the fifth turn, there are no stones left for Alice to remove. Bob wins.
     *
     *
     * Constraints:
     *
     * n == piles.length
     * 1 <= n <= 7
     * 1 <= piles[i] <= 7
     *
     *
     * Follow-up: Could you find a linear time solution? Although the linear time solution may be beyond the scope of an interview, it could be interesting to know.
     * @param piles
     * @return
     */

    // Solution 1: DP
    /*
    public static boolean nimGame(int[] piles) {
        int pilesSum = Arrays.stream(piles).sum();

        // Stones in piles[] <---> result
        Map<String, Boolean> map = new HashMap<>();

        return isCurrentPlayerWinner(piles, pilesSum, map);
    }

    private static boolean isCurrentPlayerWinner(int[] piles, int pilesSum, Map<String, Boolean> map) {
        // Generate a key by concatenating the count of stones in piles
        // e.g. [2, 3, 1, 4] -> "2-3-1-4"
        String key = generateKey(piles);

        if (map.containsKey(key)) {
            return map.get(key);
        }

        // Current player loses
        if (pilesSum == 0) {
            return false;
        }

        for (int i = 0; i < piles.length; i++) {
            for (int j = 1; j <= piles[i]; j++) {
                piles[i] -= j;

                int[] nextState = piles.clone();
                Arrays.sort(nextState);

                // If next player loses, the current player wins
                if (!isCurrentPlayerWinner(nextState, pilesSum, map)) {
                    map.put(key, true);
                    return true;
                }

                piles[i] += j;
            }
        }

        map.put(key, false);
        return false;
    }

    private static String generateKey(int[] piles) {
        StringBuilder key = new StringBuilder();
        for (int height: piles) {
            key.append(height).append("-");
        }
        return key.toString();
    }
*/

    // Solution 2:
    // https://leetcode.com/problems/game-of-nim/solutions/1295123/c-3-liners-concise-o-n-solution-with-explanation-classic-problem/
    public static boolean nimGame(int[] piles) {
        int nimSum = 0;
        for (int p : piles) {
            nimSum ^= p;
        }
        return nimSum != 0;
    }

    public static void main(String[] args) {
        int[] piles1 = {1};
        int[] piles2 = {1, 1};
        int[] piles3 = {1, 2, 3};

        System.out.println(nimGame(piles1));
        System.out.println(nimGame(piles2));
        System.out.println(nimGame(piles3));
    }
}
