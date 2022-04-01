import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

    /**
     * Given an m x n integer matrix grid, return the maximum score of a path starting at (0, 0) and ending at (m - 1, n - 1) moving
     * in the 4 cardinal directions.
     *
     * The score of a path is the minimum value in that path.
     *
     * For example, the score of the path 8 → 4 → 5 → 9 is 4.
     *
     *
     * Example 1:
     *
     * Input: grid = [[5,4,5],[1,2,6],[7,4,6]]
     * Output: 4
     * Explanation: The path with the maximum score is highlighted in yellow.
     *
     * Example 2:
     *
     * Input: grid = [[2,2,1,2,2,2],[1,2,2,2,1,2]]
     * Output: 2
     *
     * Example 3:
     *
     * Input: grid = [[3,4,6,3,4],[0,2,1,1,7],[8,8,3,2,7],[3,2,4,9,8],[4,1,2,0,0],[4,6,5,4,3]]
     * Output: 3
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * 0 <= grid[i][j] <= 10^9
     */

    // Time: O(n⋅m⋅log(n⋅m)), Space: O(n⋅m)
    public static int maximumMinimumPath(int[][] grid) {
        Queue<int[]> priorityQueue = new PriorityQueue<>((a, b) -> (grid[b[0]][b[1]] - grid[a[0]][a[1]]));
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        priorityQueue.offer(new int[] {0, 0});
        visited[0][0] = true;

        int result = grid[0][0];

        while (!priorityQueue.isEmpty()) {

            int[] cell = priorityQueue.poll();

            int x = cell[0];
            int y = cell[1];
            result = Math.min(result, grid[x][y]);

            if (x == m - 1 && y == n - 1) {
                break;
            }

            for (int[] dir: directions) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];

                if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    priorityQueue.offer(new int[]{nextX, nextY});
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{5, 4, 5}, {1, 2, 6}, {7, 4, 6}};
        int[][] grid2 = {{2, 2, 1, 2, 2, 2}, {1, 2, 2, 2, 1, 2}};

        System.out.println(maximumMinimumPath(grid1));
        System.out.println(maximumMinimumPath(grid2));
    }
}
