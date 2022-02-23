public class Solution {
    /**
     * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     *
     * Example 1:
     *
     * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
     * Output: 6
     * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
     *
     * Example 2:
     *
     * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
     * Output: 10
     * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 105
     * nums[i] is either 0 or 1.
     * 0 <= k <= nums.length
     */

    // Find the longest subarray with at most K zeros.

    public static int longestOnes(int[] nums, int k) {
        int j = 0;
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                k--;
            }

            if (k < 0) {
                if(nums[j] == 0) {
                    k++;
                }
                j++;
            }

            if (k >= 0) {
                max = Math.max(max, i - j + 1);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] test1 = {1,1,1,0,0,0,1,1,1,1,0};
        int[] test2 = {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        System.out.println(longestOnes(test1, 2));
        System.out.println(longestOnes(test2, 3));
    }
}
