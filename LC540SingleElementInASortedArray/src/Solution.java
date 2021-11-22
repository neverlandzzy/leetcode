public class Solution {

    /**
     * You are given a sorted array consisting of only integers where every element appears exactly twice,
     * except for one element which appears exactly once.
     *
     * Return the single element that appears only once.
     *
     * Your solution must run in O(log n) time and O(1) space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,1,2,3,3,4,4,8,8]
     * Output: 2
     * Example 2:
     *
     * Input: nums = [3,3,7,7,10,11,11]
     * Output: 10
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^5
     */

    public static int singleNonDuplicate(int[] nums) {
        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if((mid % 2 == 0 && nums[mid] == nums[mid + 1]) ||(mid % 2 == 1 && nums[mid] == nums[mid - 1])) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return nums[start];
    }

    public static void main(String[] args) {
        int[] test1 = {1,1,2,3,3,4,4,8,8};
        int[] test2 = {3,3,7,7,10,11,11};

        System.out.println(singleNonDuplicate(test1));
        System.out.println(singleNonDuplicate(test2));
    }
}
