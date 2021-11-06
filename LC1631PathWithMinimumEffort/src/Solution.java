import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns,
     * where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0),
     * and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up,
     * down, left, or right, and you wish to find a route that requires the minimum effort.
     *
     * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
     *
     * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
     *
     *
     *
     * Example 1:
     *
     * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
     * Output: 2
     * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
     * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
     *
     * Example 2:
     *
     * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
     * Output: 1
     * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is
     * better than route [1,3,5,3,5].
     *
     * Example 3:
     *
     * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
     * Output: 0
     * Explanation: This route does not require any effort.
     *
     *
     * Constraints:
     *
     * rows == heights.length
     * columns == heights[i].length
     * 1 <= rows, columns <= 100
     * 1 <= heights[i][j] <= 10^6
     */

    // Time: O(m * n * log 10^6) = O(m * n)
    public static int minimumEffortPath(int[][] heights) {
        int low = 0;
        int high = 1_000_000;

        while (low < high) {
            int mid = (low + high) / 2;

            if (canReachDestination(heights, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static boolean canReachDestination(int[][] heights, int effort) {
        int m = heights.length;
        int n = heights[0].length;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int x = cell[0];
            int y = cell[1];

            if (x == m - 1 && y == n - 1) {
                return true;
            }

            int curHeight = heights[x][y];

            for (int[] dir: directions) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];

                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY]) {
                    continue;
                }

                int nextHeight = heights[nextX][nextY];

                if (Math.abs(curHeight - nextHeight) <= effort) {
                    queue.offer(new int[] {nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int[][] test2 = {{1, 2, 3}, {3, 8, 4}, {5, 3, 5}};
        int[][] test3 = {{1, 2, 1, 1, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 2, 1, 2, 1}, {1, 1, 1, 2, 1}};

        System.out.println(minimumEffortPath(test1));
        System.out.println(minimumEffortPath(test2));
        System.out.println(minimumEffortPath(test3));
    }
}
