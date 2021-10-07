import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * Given an integer array nums of length n where all the integers of nums are in the range [1, n]
     * and each integer appears once or twice, return an array of all the integers that appears twice.
     *
     * You must write an algorithm that runs in O(n) time and uses only constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [4,3,2,7,8,2,3,1]
     * Output: [2,3]
     * Example 2:
     *
     * Input: nums = [1,1,2]
     * Output: [1]
     * Example 3:
     *
     * Input: nums = [1]
     * Output: []
     *
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 105
     * 1 <= nums[i] <= n
     * Each element in nums appears once or twice.
     */

    public static List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();

        for (int i: nums) {
            if (nums[Math.abs(i) - 1] < 0) {
                result.add(Math.abs(i));
            } else {
                nums[Math.abs(i) - 1] *= -1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test1 = {4,3,2,7,8,2,3,1};
        int[] test2 = {1,1,2};
        int[] test3 = {1};

        System.out.println(findDuplicates(test1));
        System.out.println(findDuplicates(test2));
        System.out.println(findDuplicates(test3));
    }
}
