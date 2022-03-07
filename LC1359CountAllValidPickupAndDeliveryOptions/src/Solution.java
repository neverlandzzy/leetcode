public class Solution {

    /**
     * Given n orders, each order consist in pickup and delivery services.
     *
     * Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
     *
     * Since the answer may be too large, return it modulo 10^9 + 7.
     *
     * Example 1:
     *
     * Input: n = 1
     * Output: 1
     * Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
     *
     * Example 2:
     *
     * Input: n = 2
     * Output: 6
     * Explanation: All possible orders:
     * (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
     * This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
     *
     * Example 3:
     *
     * Input: n = 3
     * Output: 90
     *
     *
     * Constraints:
     *
     * 1 <= n <= 500
     */

    // e.g. 对于4个order。P1,P2,P3,P4 共有4!种(P14)可能。然后将D1,D2,D3,D4分别插入到P1~P4的间隙中。
    //      D4只能放在P4后，只有1种可能，D3有3种可能(P3后，P4后或者D4后)，D2有5种可能，D1有7种。。。
    //      因此总的概率为 4! * 7 * 5 * 3 * 1
    //      对于n个order，总的概率为 n! * (2n - 1) * (2n - 3) * ... * 3 * 1种可能
    public static int countOrders(int n) {
        long result = 1;
        int MOD = 1_000_000_000 + 7;

        for (long i = 1; i <= n; i++) {
            // n!
            result *= i;

            // (2n - 1) * (2n - 3) * ... * 3 * 1
            result *= (2 * i - 1);

            // MOD
            result %= MOD;
        }

        return (int)result;
    }

    public static void main(String[] args) {
        System.out.println(countOrders(1));
        System.out.println(countOrders(2));
        System.out.println(countOrders(3));
    }
}
