import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /**
     * In a given grid, each cell can have one of three values:
     *
     * the value 0 representing an empty cell;
     * the value 1 representing a fresh orange;
     * the value 2 representing a rotten orange.
     * Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.
     *
     * Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.
     *
     * Example 1:
     * Input: [[2,1,1],[1,1,0],[0,1,1]]
     * Output: 4
     *
     * Example 2:
     * Input: [[2,1,1],[0,1,1],[1,0,1]]
     * Output: -1
     * Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
     *
     * Example 3:
     * Input: [[0,2]]
     * Output: 0
     * Explanation:  Since there are already no fresh oranges at minute 0, the answer is just 0.
     *
     * Note:
     *
     * 1. 1 <= grid.length <= 10
     * 2. 1 <= grid[0].length <= 10
     * 3. grid[i][j] is only 0, 1, or 2.
     */

    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        Queue<int[]> queue = new LinkedList<>();
        int freshOrangesCount = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] {i, j});
                } else if (grid[i][j] == 1) {
                    freshOrangesCount++;
                }
            }
        }
        if (freshOrangesCount == 0) {
            return 0;
        }
        return bfs(grid, queue, freshOrangesCount);
    }

    private static int bfs(int[][] grid, Queue<int[]> queue, int freshOrangesCount) {
        int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int step = 0;
        int m = grid.length;
        int n = grid[0].length;

        while (! queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] orange = queue.poll();
                int x = orange[0];
                int y = orange[1];

                for (int[] dir: direction) {
                    int nextX = x + dir[0];
                    int nextY = y + dir[1];

                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
                        grid[nextX][nextY] = 2;
                        queue.offer(new int[] {nextX, nextY});
                        freshOrangesCount--;
                    }
                }
            }
            step++;
        }

        return freshOrangesCount == 0 ? step - 1 : -1;
    }

    public static void main(String[] args) {
        int[][] grid1 = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        int[][] grid2 = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        int[][] grid3 = {{0, 2}};

        System.out.println(orangesRotting(grid1));
        System.out.println(orangesRotting(grid2));
        System.out.println(orangesRotting(grid3));
    }
}
