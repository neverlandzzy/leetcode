public class Solution {
    /**
     * Given a positive integer k, you need to find the length of the smallest positive integer n such that n is divisible by k, and n only contains the digit 1.
     *
     * Return the length of n. If there is no such n, return -1.
     *
     * Note: n may not fit in a 64-bit signed integer.
     *
     *
     *
     * Example 1:
     *
     * Input: k = 1
     * Output: 1
     * Explanation: The smallest answer is n = 1, which has length 1.
     *
     * Example 2:
     *
     * Input: k = 2
     * Output: -1
     * Explanation: There is no such positive integer n divisible by 2.
     *
     * Example 3:
     *
     * Input: k = 3
     * Output: 3
     *
     * Explanation: The smallest answer is n = 111, which has length 3.
     *
     * Constraints:
     *
     * 1 <= k <= 10^5
     */

    // https://leetcode.com/problems/smallest-integer-divisible-by-k/discuss/260916/Proof%3A-we-only-need-to-consider-the-first-K-candidates-in-1-11-111-1111-...

    // 1. 当k是2或5的倍数时，要求被除数是偶数或者5的倍数，111..1显然不符合
    // 2. 当k是其它数时，根据抽屉原理（鸽笼原理），在[1, 11, 111, 111..1(k个1)]中，必然有1个数能被k整除：
    //    否则，[1, 11, 111, 111..1(k个1)]有k个不同的余数，而1 ~ k-1只有k-1个数，也就是在前者的k个不同余数中，至少有两个相同的余数，假设这两个数分别是i和j。因为i和j的余数相同
    //    i-j就可以被k整除。而除数形式是1,11,111...， i-j的最后一位是0，可以被2整除，也就必然不能被k整除，结论矛盾。因此，我们只需要查找长度为1~k的数。
    public static int smallestRepunitDivByK(int k) {
        int remainder = 0;

        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }

        for (int i = 1; i <= k; i++) {
            remainder = (remainder * 10 + 1) % k;

            System.out.println(remainder);
            if (remainder == 0) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(smallestRepunitDivByK(1));
        //System.out.println(smallestRepunitDivByK(2));
        System.out.println(smallestRepunitDivByK(7));
    }
}
