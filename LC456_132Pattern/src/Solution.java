import java.util.Stack;

public class Solution {
    /**
     * Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and
     * nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
     *
     * Return true if there is a 132 pattern in nums, otherwise, return false.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,4]
     * Output: false
     * Explanation: There is no 132 pattern in the sequence.
     *
     * Example 2:
     *
     * Input: nums = [3,1,4,2]
     * Output: true
     * Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
     *
     * Example 3:
     *
     * Input: nums = [-1,3,2,0]
     * Output: true
     * Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
     *
     *
     * Constraints:
     *
     * n == nums.length
     * 1 <= n <= 2 * 10^5
     * -10^9 <= nums[i] <= 10^9
     */

    public static boolean find132pattern(int[] nums) {
        int n = nums.length;

        if (n < 3) {
            return false;
        }

        int[] min = new int[n]; // 记录从左面开始遇到的最小值，即找pattern 132 中的'1'
        Stack<Integer> stack = new Stack<>();
        min[0] = nums[0];
        for (int i = 1; i < n; i++) {
            min[i] = Math.min(min[i - 1], nums[i]);
        }

        for (int i = n - 1; i >= 0; i--) {
            // 找到pattern 132中的13，用stack找2
            if (nums[i] > min[i]) {
                while (!stack.isEmpty() && stack.peek() <= min[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty() && stack.peek() < nums[i]) {
                    return true;
                }
                stack.push(nums[i]);
            }
        }

        return  false;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4};
        int[] test2 = {3, 1, 4, 2};
        int[] test3 = {-1, 3, 2, 0};

        System.out.println(find132pattern(test1));
        System.out.println(find132pattern(test2));
        System.out.println(find132pattern(test3));
    }
}
