public class Solution {
    /**
     * Given an array A of positive integers, let S be the sum of the digits of the minimal element of A.
     *
     * Return 0 if S is odd, otherwise return 1.
     *
     * Example 1:
     *
     * Input: [34,23,1,24,75,33,54,8]
     * Output: 0
     * Explanation:
     * The minimal element is 1, and the sum of those digits is S = 1 which is odd, so the answer is 0.
     *
     * Example 2:
     *
     * Input: [99,77,33,66,55]
     * Output: 1
     * Explanation:
     * The minimal element is 33, and the sum of those digits is S = 3 + 3 = 6 which is even, so the answer is 1.
     *
     *
     * Note:
     *
     * 1 <= A.length <= 100
     * 1 <= A[i].length <= 100
     */

    public static int sumOfDigits(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int a: A) {
            min = Math.min(min, a);
        }

        int sum = 0;
        while (min > 0) {
            int digit = min % 10;
            sum += digit;
            min /= 10;
        }

        return 1 - sum % 2;
    }

    public static void main(String[] args) {
        int[] test1 = {34,23,1,24,75,33,54,8};
        int[] test2 = {99,77,33,66,55};

        System.out.println(sumOfDigits(test1));
        System.out.println(sumOfDigits(test2));
    }
}
