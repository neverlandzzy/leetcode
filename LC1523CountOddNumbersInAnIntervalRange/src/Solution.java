public class Solution {

    /**
     * Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
     *
     *
     *
     * Example 1:
     *
     * Input: low = 3, high = 7
     * Output: 3
     * Explanation: The odd numbers between 3 and 7 are [3,5,7].
     * Example 2:
     *
     * Input: low = 8, high = 10
     * Output: 1
     * Explanation: The odd numbers between 8 and 10 are [9].
     *
     *
     * Constraints:
     *
     * 0 <= low <= high <= 10^9
     */

    public static int countOdds(int low, int high) {
        int count = 0;

        if (low % 2 == 1) {
            low++;
            count++;
        }

        if (low <= high && high % 2 == 1) {
            high--;
            count++;
        }

        if (low <= high) {
            count += (high - low) / 2;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println((countOdds(3, 7)));
        System.out.println((countOdds(8, 10)));
    }

}
