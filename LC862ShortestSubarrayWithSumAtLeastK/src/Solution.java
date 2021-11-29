import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    /**
     * Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k.
     * If there is no such subarray, return -1.
     *
     * A subarray is a contiguous part of an array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1], k = 1
     * Output: 1
     *
     *
     * Example 2:
     *
     * Input: nums = [1,2], k = 4
     * Output: -1
     *
     *
     * Example 3:
     *
     * Input: nums = [2,-1,2], k = 3
     * Output: 3
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 10^5
     * -10^5 <= nums[i] <= 10^5
     * 1 <= k <= 10^9
     */

    // Similar to LC239
    // nums的前n项和 preSum[i] = nums[0] + nums[1] + ... + nums[i - 1]
    // 在preSum中，找最小的j - i,满足j > i && preSum[j] - preSum[i] >= k
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        int result = n + 1;

        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        for (int i = 0; i <= n; i++) {
            while (!deque.isEmpty() && preSum[i] - preSum[deque.peek()] >= k) {
                result = Math.min(result, i - deque.poll());
            }

            // 当preSum[i] <= preSum[deque.peekLast()时，说明nums[i - 1]是负数或0（否则preSum严格单调递增）
            // 遇到负数时，应该从subarray中减去
            while (!deque.isEmpty() && preSum[i] <= preSum[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offer(i);
        }

        return result <= n ? result : -1;
    }

    public static void main(String[] args) {
        int[] test1 = {1};
        int[] test2 = {1, 2};
        int[] test3 = {2, -1, 2};

        System.out.println(shortestSubarray(test1, 1));
        System.out.println(shortestSubarray(test2, 4));
        System.out.println(shortestSubarray(test3, 3));
    }
}
