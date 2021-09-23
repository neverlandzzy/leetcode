public class Solution {

    /**
     * Given a binary array nums, return the maximum number of consecutive 1's in the array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,0,1,1,1]
     * Output: 3
     * Explanation: The first two digits or the last three digits are consecutive 1s.
     * The maximum number of consecutive 1s is 3.
     *
     * Example 2:
     *
     * Input: nums = [1,0,1,1,0,1]
     * Output: 2
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 105
     * nums[i] is either 0 or 1.
     */

    // A better solution - same complexity, but clean
    public static int findMaxConsecutiveOnes(int[] nums) {
        int count = 0;
        int result = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            } else {
                result = Math.max(result, count);
                count = 0;
            }
        }
        result = Math.max(result, count);
        return result;
    }

    /*
    public static int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int j = 0;
        int n = nums.length;
        int count = 0;

        while (i < n && j < n) {
            if (nums[i] == 0) {
                i++;
                continue;
            }

            j = i;
            while (j < n && nums[j] == 1) {
                j++;
            }

            count = Math.max(count, j - i);
            j++;
            i = j;
        }

        return count;
    }
    */
    public static void main(String[] args) {
        int[] test1 = {1,1,0,1,1,1};
        int[] test2 = {1,0,1,1,0,1};

        System.out.println(findMaxConsecutiveOnes(test1));
        System.out.println(findMaxConsecutiveOnes(test2));
    }
}
