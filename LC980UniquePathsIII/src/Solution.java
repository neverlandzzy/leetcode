public class Solution {
    /**
     * You are given an m x n integer array grid where grid[i][j] could be:
     *
     * 1 representing the starting square. There is exactly one starting square.
     * 2 representing the ending square. There is exactly one ending square.
     * 0 representing empty squares we can walk over.
     * -1 representing obstacles that we cannot walk over.
     *
     * Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle
     * square exactly once.
     *
     *
     *
     * Example 1:
     *
     * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
     * Output: 2
     * Explanation: We have the following two paths:
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
     * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
     *
     *
     * Example 2:
     *
     * Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
     * Output: 4
     * Explanation: We have the following four paths:
     * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
     * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
     * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
     * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
     *
     *
     * Example 3:
     *
     * Input: grid = [[0,1],[2,0]]
     * Output: 0
     * Explanation: There is no path that walks over every empty square exactly once.
     * Note that the starting and ending square can be anywhere in the grid.
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 20
     * 1 <= m * n <= 20
     * -1 <= grid[i][j] <= 2
     * There is exactly one starting cell and one ending cell.
     */

    public static int uniquePathsIII(int[][] grid) {
        int[] count = new int[1];

        int m = grid.length;
        int n = grid[0].length;

        int startingX = 0;
        int startingY = 0;

        int obstacles = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == -1) {
                    obstacles++;
                } else if (grid[i][j] == 1) {
                    startingX = i;
                    startingY = j;
                }
            }
        }

        dfs(grid, startingX, startingY, m * n - obstacles, count);

        return count[0];
    }

    private static void dfs(int[][] grid, int x, int y, int remaining, int[] count) {
        int m = grid.length;
        int n = grid[0].length;

        if (x < 0 || x >= m || y < 0 || y >= n) {
            return;
        }

        if (grid[x][y] == 2 && remaining == 1) {
            count[0]++;
            return;
        }

        if (grid[x][y] != 0 && grid[x][y] != 1) {
            return;
        }

        int cache = grid[x][y];
        grid[x][y] = 3;

        dfs(grid, x + 1, y, remaining - 1, count);
        dfs(grid, x - 1, y, remaining - 1, count);
        dfs(grid, x, y + 1, remaining - 1, count);
        dfs(grid, x, y - 1, remaining - 1, count);

        grid[x][y] = cache;
    }


    public static void main(String[] args) {
        int[][] test1 = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        int[][] test2 = {{1,0,0,0},{0,0,0,0},{0,0,0,2}};
        int[][] test3 = {{0,1},{2,0}};

        System.out.println(uniquePathsIII(test1));
        System.out.println(uniquePathsIII(test2));
        System.out.println(uniquePathsIII(test3));
    }
}
