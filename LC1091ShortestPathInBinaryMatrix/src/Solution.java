import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
     * If there is no clear path, return -1.
     *
     * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right
     * cell (i.e., (n - 1, n - 1)) such that:
     *
     * All the visited cells of the path are 0.
     * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and
     * they share an edge or a corner).
     * The length of a clear path is the number of visited cells of this path.
     *
     *
     *
     * Example 1:
     *
     * Input: grid = [[0,1],[1,0]]
     * Output: 2
     *
     * Example 2:
     *
     * Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
     * Output: 4
     *
     * Example 3:
     *
     * Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
     * Output: -1
     *
     *
     * Constraints:
     *
     * n == grid.length
     * n == grid[i].length
     * 1 <= n <= 100
     * grid[i][j] is 0 or 1
     */

    public static int shortestPathBinaryMatrix(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        int result = 0;
        int m = grid.length;
        int n = grid[0].length;

        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {0, 0});
        grid[0][0] = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            result++;
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                int i = cell[0];
                int j = cell[1];

                if (i == m - 1 && j == n - 1) {
                    return result;
                }

                for (int[] dir : directions) {
                    int nextI = i + dir[0];
                    int nextJ = j + dir[1];

                    if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && grid[nextI][nextJ] == 0) {
                        queue.offer(new int[]{nextI, nextJ});
                        grid[nextI][nextJ] = -1;
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 1}, {1, 0}};
        int[][] test2 = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        int[][] test3 = {{1, 0, 0}, {1, 1, 0}, {1, 1, 0}};

        System.out.println(shortestPathBinaryMatrix(test1));
        System.out.println(shortestPathBinaryMatrix(test2));
        System.out.println(shortestPathBinaryMatrix(test3));
    }
}
