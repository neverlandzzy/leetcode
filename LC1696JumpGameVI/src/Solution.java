import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

    /**
     * You are given a 0-indexed integer array nums and an integer k.
     *
     * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries
     * of the array. That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
     *
     * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited
     * in the array.
     *
     * Return the maximum score you can get.
     *
     * Example 1:
     *
     * Input: nums = [1,-1,-2,4,-7,3], k = 2
     * Output: 7
     * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3] (underlined above). The sum is 7.
     *
     * Example 2:
     *
     * Input: nums = [10,-5,-2,4,0,3], k = 3
     * Output: 17
     * Explanation: You can choose your jumps forming the subsequence [10,4,3] (underlined above). The sum is 17.
     *
     * Example 3:
     *
     * Input: nums = [1,-5,-20,4,-1,3,-6,-3], k = 2
     * Output: 0
     *
     *
     * Constraints:
     *
     * 1 <= nums.length, k <= 10^5
     * -10^4 <= nums[i] <= 10^4
     */

    public static int maxResult(int[] nums, int k) {
        int n = nums.length;
        // score[i] represents the max score starting at nums[0] and ending at nums[i].
        int[] scores = new int[n];
        scores[0] = nums[0];

        // deque stores indexes of nums such that the corresponding values are monotonically decreasing.
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(0);

        for (int i = 1; i < n; i++) {
            while (deque.peekFirst() != null && deque.peekFirst() < i - k) {
                deque.pollFirst();
            }

            scores[i] = scores[deque.peek()] + nums[i];

            while (deque.peekLast() != null && scores[i] >= scores[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offer(i);
        }

        return scores[n - 1];
    }

    public static void main(String[] args) {
        int[] test1 = {1,-1,-2,4,-7,3};
        int[] test2 = {10,-5,-2,4,0,3};

        System.out.println(maxResult(test1, 2));
        System.out.println(maxResult(test2, 3));
    }
}
