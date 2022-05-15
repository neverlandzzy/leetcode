public class Solution {
    /**
     * Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order,
     * then the whole array will be sorted in ascending order.
     *
     * Return the shortest such subarray and output its length.
     *
     * Example 1:
     *
     * Input: nums = [2,6,4,8,10,9,15]
     * Output: 5
     * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
     *
     * Example 2:
     *
     * Input: nums = [1,2,3,4]
     * Output: 0
     *
     * Example 3:
     *
     * Input: nums = [1]
     * Output: 0
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^4
     * -10^5 <= nums[i] <= 10^5
     *
     *
     * Follow up: Can you solve it in O(n) time complexity?
     */

    public static int findUnsortedSubarray(int[] nums) {
        if (nums.length <= 1) {
            return 0;
        }

        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        boolean flag = false;

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[i - 1]) {
                flag = true;
            }

            if (flag) {
                min = Math.min(min, nums[i]);
            }
        }

        flag = false;

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                flag = true;
            }

            if (flag) {
                max = Math.max(max, nums[i]);
            }
        }

        for (left = 0; left < n; left++) {
            if (min < nums[left]) {
                break;
            }
        }

        for (right = n - 1; right >= 0; right--) {
            if (max > nums[right]) {
                break;
            }
        }

        return right - left < 0 ? 0 : right - left + 1;
    }

    public static void main(String[] args) {
        int[] test1 = {2,6,4,8,10,9,15};
        int[] test2 = {1,2,3,4};
        int[] test3 = {1};

        System.out.println(findUnsortedSubarray(test1));
        System.out.println(findUnsortedSubarray(test2));
        System.out.println(findUnsortedSubarray(test3));
    }
}
