import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * Given an array of non-negative integers arr, you are initially positioned at start index of the array.
     * When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach to any index with value 0.
     *
     * Notice that you can not jump outside of the array at any time.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [4,2,3,0,3,1,2], start = 5
     * Output: true
     * Explanation:
     * All possible ways to reach at index 3 with value 0 are:
     * index 5 -> index 4 -> index 1 -> index 3
     * index 5 -> index 6 -> index 4 -> index 1 -> index 3
     *
     *
     * Example 2:
     *
     * Input: arr = [4,2,3,0,3,1,2], start = 0
     * Output: true
     * Explanation:
     * One possible way to reach at index 3 with value 0 is:
     * index 0 -> index 4 -> index 1 -> index 3
     *
     *
     * Example 3:
     *
     * Input: arr = [3,0,2,1,2], start = 2
     * Output: false
     * Explanation: There is no way to reach at index 1 with value 0.
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 5 * 10^4
     * 0 <= arr[i] < arr.length
     * 0 <= start < arr.length
     */

    public static boolean canReach(int[] arr, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int n = arr.length;
        boolean[] visited = new boolean[n];
        visited[start] = true;

        while (!queue.isEmpty()) {
            int index = queue.poll();

            if (arr[index] == 0) {
                return true;
            }

            int forward = index + arr[index];
            int backward = index - arr[index];
            if (forward >= 0 && forward < n && !visited[forward]) {
                queue.offer(forward);
                visited[forward] = true;
            }

            if (backward >= 0 && backward < n && !visited[backward]) {
                queue.offer(backward);
                visited[backward] = true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] test1 = {4,2,3,0,3,1,2};
        int[] test2 = {4,2,3,0,3,1,2};
        int[] test3 = {3,0,2,1,2};

        System.out.println(canReach(test1, 5));
        System.out.println(canReach(test2, 0));
        System.out.println(canReach(test3, 2));
    }
}
