import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c],
     * nums[d]] such that:
     *
     * 0 <= a, b, c, d < n
     * a, b, c, and d are distinct.
     * nums[a] + nums[b] + nums[c] + nums[d] == target
     * You may return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,0,-1,0,-2,2], target = 0
     * Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
     *
     * Example 2:
     *
     * Input: nums = [2,2,2,2,2], target = 8
     * Output: [[2,2,2,2]]
     *
     * Constraints:
     *
     * 1 <= nums.length <= 200
     * -10^9 <= nums[i] <= 10^9
     * -10^9 <= target <= 10^9
     */

    // kSum 模板
    // Time: O(n^k-1) 对于本题是O(n^3)
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target,0, 4);
    }

    private static List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        if (start == n) {
            return result;
        }

        if (k == 2) {
            return twoSum(nums, target, start);
        }

        for (int i = start; i < n - k + 1; i++) {
            if (i == start || nums[i] != nums[i - 1]) {
               List<List<Integer>> lists = kSum(nums, target - nums[i], i + 1, k - 1);
               if (lists.size() > 0) {
                   for (List<Integer> l: lists) {
                       l.add(0, nums[i]);
                   }

                   result.addAll(lists);
               }
            }
        }

        return result;
    }

    // 参考LC15 3Sum
    private static List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();

        int left = start;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                result.add(new ArrayList<>(Arrays.asList(nums[left], nums[right])));

                left++;
                right--;

                while (left < right && nums[left - 1] == nums[left]) {
                    left++;
                }

                while (left < right && nums[right + 1] == nums[right]) {
                    right--;
                }
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 0, -1, 0, -2, 2};
        int[] test2 = {2, 2, 2, 2, 2};

        System.out.println(fourSum(test1, 0));
        System.out.println(fourSum(test2, 8));
    }
}
