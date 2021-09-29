import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /**
     * You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle).
     * You can move up, down, left, or right from and to an empty cell in one step.
     *
     * Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right
     * corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to
     * find such walk return -1.
     *
     *
     *
     * Example 1:
     *
     * Input:
     * grid =
     * [[0,0,0],
     *  [1,1,0],
     *  [0,0,0],
     *  [0,1,1],
     *  [0,0,0]],
     * k = 1
     * Output: 6
     * Explanation:
     * The shortest path without eliminating any obstacle is 10.
     * The shortest path with one obstacle elimination at position (3,2) is 6.
     * Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).
     *
     * Example 2:
     *
     * Input:
     * grid =
     * [[0,1,1],
     *  [1,1,1],
     *  [1,0,0]],
     * k = 1
     * Output: -1
     * Explanation:
     * We need to eliminate at least two obstacles to find such a walk.
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 40
     * 1 <= k <= m * n
     * grid[i][j] == 0 or 1
     * grid[0][0] == grid[m - 1][n - 1] == 0
     */

    // 同样是BFS，但除了坐标信息外，还要加上quota信息，所以是对int[r][c][quota]这个三维数组进行BFS
    public static int shortestPath(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][][] visited = new boolean[m][n][k + 1];
        visited[0][0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0, 0});
        int result = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int r = cell[0];
                int c = cell[1];
                int quota = cell[2];

                if (r == m -1 && c == n - 1) {
                    return result;
                }

                for (int[] dir: directions) {
                    int nextR = r + dir[0];
                    int nextC = c + dir[1];
                    int nextQ = quota;

                    if (nextR >= 0 && nextR < m && nextC >= 0 && nextC < n) {
                        if (grid[nextR][nextC] == 1) {
                            nextQ++;
                        }

                        if (nextQ <= k && !visited[nextR][nextC][nextQ]) {
                            queue.offer(new int[] {nextR, nextC, nextQ});
                            visited[nextR][nextC][nextQ] = true;
                        }
                    }
                }
            }
            result++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 0, 0}, {1, 1, 0}, {0, 0, 0}, {0, 1, 1}, {0, 0, 0}};
        int[][] test2 = {{0, 1, 1}, {1, 1, 1}, {1, 0, 0}};

        System.out.println(shortestPath(test1, 1));
        System.out.println(shortestPath(test2, 1));
    }
}
