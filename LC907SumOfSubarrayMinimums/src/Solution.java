import java.util.Arrays;
import java.util.Stack;

public class Solution {
    /**
     * Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous)
     * subarray of arr. Since the answer may be large, return the answer modulo 10^9 + 7.
     *
     *
     * Example 1:
     *
     * Input: arr = [3,1,2,4]
     * Output: 17
     * Explanation:
     * Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
     * Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
     * Sum is 17.
     *
     * Example 2:
     *
     * Input: arr = [11,81,94,43,3]
     * Output: 444
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 3 * 10^4
     * 1 <= arr[i] <= 3 * 10^4
     */

    // 对每一个元素，找到它作为最小元素的range，将element * count of subarrays where it is smallest 加到结果中
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int sum = 0;
        int MOD = 1000000007;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {
            // arr[stack.peek()] >= arr[i]，This ensures that no contribution
            // is counted twice. rightBoundary takes equal or smaller
            // elements into account while leftBoundary takes only the
            // strictly smaller elements into account
            while (!stack.isEmpty() && (i == n || arr[stack.peek()] >= arr[i])) {
                int mid = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                int right = i;

                long count = (mid - left) * (right - mid) % MOD;
                sum += (count * arr[mid]) % MOD;
                sum %= MOD;
            }

            stack.push(i);
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] test1 = {3, 1, 2, 4};
        int[] test2 = {11, 81, 94, 43, 3};

        System.out.println(sumSubarrayMins(test1));
        System.out.println(sumSubarrayMins(test2));
    }
}
