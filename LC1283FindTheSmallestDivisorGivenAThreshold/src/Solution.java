public class Solution {

    /**
     * Given an array of integers nums and an integer threshold, we will choose a positive integer divisor, divide all the array by it,
     * and sum the division's result. Find the smallest divisor such that the result mentioned above is less than or equal to threshold.
     *
     * Each result of the division is rounded to the nearest integer greater than or equal to that element.
     * (For example: 7/3 = 3 and 10/2 = 5).
     *
     * It is guaranteed that there will be an answer.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,5,9], threshold = 6
     * Output: 5
     * Explanation: We can get a sum to 17 (1+2+5+9) if the divisor is 1.
     * If the divisor is 4 we can get a sum of 7 (1+1+2+3) and if the divisor is 5 the sum will be 5 (1+1+1+2).
     *
     * Example 2:
     *
     * Input: nums = [44,22,33,11,1], threshold = 5
     * Output: 44
     *
     * Example 3:
     *
     * Input: nums = [21212,10101,12121], threshold = 1000000
     * Output: 1
     *
     * Example 4:
     *
     * Input: nums = [2,3,5,7,11], threshold = 11
     * Output: 3
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 10^6
     * nums.length <= threshold <= 10^6
     */

    public static int smallestDivisor(int[] nums, int threshold) {
        int start = 1;
        int end = 1_000_000;

        while (start < end) {
            int mid = start + (end - start) / 2;
            int sum = 0;

            for (int n: nums) {
                if (n % mid == 0) {
                    sum += n / mid;
                } else {
                    sum += n / mid + 1;
                }
            }

            if (sum > threshold) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,5,9};
        int[] test2 = {44,22,33,11,1};
        int[] test3 = {21212,10101,12121};
        int[] test4 = {2,3,5,7,11};

        System.out.println(smallestDivisor(test1, 6));
        System.out.println(smallestDivisor(test2, 5));
        System.out.println(smallestDivisor(test3, 1000000));
        System.out.println(smallestDivisor(test4, 11));
    }
}
