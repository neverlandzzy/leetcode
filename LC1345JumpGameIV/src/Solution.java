import java.util.*;

public class Solution {

    /**
     * Given an array of integers arr, you are initially positioned at the first index of the array.
     *
     * In one step you can jump from index i to index:
     *
     * i + 1 where: i + 1 < arr.length.
     * i - 1 where: i - 1 >= 0.
     * j where: arr[i] == arr[j] and i != j.
     * Return the minimum number of steps to reach the last index of the array.
     *
     * Notice that you can not jump outside of the array at any time.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
     * Output: 3
     * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
     * Example 2:
     *
     * Input: arr = [7]
     * Output: 0
     * Explanation: Start index is the last index. You do not need to jump.
     * Example 3:
     *
     * Input: arr = [7,6,9,6,9,6,9,7]
     * Output: 1
     * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 5 * 10^4
     * -10^8 <= arr[i] <= 10^8
     */

    public static int minJumps(int[] arr) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = arr.length;
        int step = 0;

        for (int i = 0; i < n; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], new ArrayList<>());
            }

            map.get(arr[i]).add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int pos = queue.poll();

                if (pos == n - 1) {
                    return step;
                }

                if (pos - 1 >= 0 && !visited[pos - 1]) {
                    queue.offer(pos - 1);
                    visited[pos - 1] = true;
                }

                if (pos + 1 < n && !visited[pos + 1]) {
                    queue.offer(pos + 1);
                    visited[pos + 1] = true;
                }

                for (int next: map.get(arr[pos])) {
                    if (!visited[next]) {
                        queue.offer(next);
                    }
                }

                // 优化，对于 [7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,11] 避免重复将7加入queue
                map.get(arr[pos]).clear();
            }
            step++;
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] test1 = {100,-23,-23,404,100,23,23,23,3,404};
        int[] test2 = {7};
        int[] test3 = {7,6,9,6,9,6,9,7};


        System.out.println(minJumps(test1));
        System.out.println(minJumps(test2));
        System.out.println(minJumps(test3));
    }
}
