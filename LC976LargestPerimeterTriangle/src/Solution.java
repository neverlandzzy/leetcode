import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    /**
     * Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths.
     * If it is impossible to form any triangle of a non-zero area, return 0.
     *
     * Example 1:
     *
     * Input: nums = [2,1,2]
     * Output: 5
     * Example 2:
     *
     * Input: nums = [1,2,1]
     * Output: 0
     *
     *
     * Constraints:
     *
     * 3 <= nums.length <= 10^4
     * 1 <= nums[i] <= 10^6
     */

    public static int largestPerimeter(int[] nums) {
        Arrays.sort(nums);

        for (int i = nums.length - 1; i >= 2; i--) {
            int first = nums[i];
            int second = nums[i - 1];
            int third = nums[i - 2];

            if (first > second - third && first < second + third) {
                return first + second + third;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 1, 2};
        int[] test2 = {1, 2, 1};

        System.out.println(largestPerimeter(test1));
        System.out.println(largestPerimeter(test2));
    }
}
