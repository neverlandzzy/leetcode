import java.util.Arrays;

public class Solution {
    /**
     * Given an array nums of integers and integer k, return the maximum sum such that there exists i < j with nums[i] + nums[j] = sum and sum < k. If no i, j
     * exist satisfying this equation, return -1.
     *
     *
     * Example 1:
     *
     * Input: nums = [34,23,1,24,75,33,54,8], k = 60
     * Output: 58
     * Explanation: We can use 34 and 24 to sum 58 which is less than 60.
     *
     * Example 2:
     *
     * Input: nums = [10,20,30], k = 15
     * Output: -1
     * Explanation: In this case it is not possible to get a pair sum less that 15.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 100
     * 1 <= nums[i] <= 1000
     * 1 <= k <= 2000
     */

    // Solution 1: O(n * logn)
//    public static int twoSumLessThanK(int[] nums, int k) {
//        Arrays.sort(nums);
//
//        int i = 0;
//        int j = nums.length - 1;
//        int result = -1;
//
//        while (i < j) {
//            int sum = nums[i] + nums[j];
//
//            if (sum < k) {
//                result = Math.max(result, sum);
//                i++;
//            } else {
//                j--;
//            }
//        }
//
//        return result;
//    }

    // Solution 2: Counting sort: O(m + n), where m is to the range of values in the input array. (1000)

    public static int twoSumLessThanK(int[] nums, int k) {
        int[] count = new int[1001];

        for (int n: nums) {
            count[n]++;
        }

        int result = -1;

        int low = 1;
        int high = 1000;

        while (low <= high) {
            if (low + high >= k || count[high] == 0) {
                high--;
            } else {
                if (count[low] > (low == high ? 1 : 0)) {
                    result = Math.max(result, low + high);
                }
                low++;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[] test1 = {34, 23, 1, 24, 75, 33, 54, 8};
        int[] test2 = {10, 20, 30};

        System.out.println(twoSumLessThanK(test1, 60));
        System.out.println(twoSumLessThanK(test2, 15));
    }
}
