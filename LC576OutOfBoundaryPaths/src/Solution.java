public class Solution {
    /**
     * There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move
     * the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply
     * at most maxMove moves to the ball.
     *
     * Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid
     * boundary. Since the answer can be very large, return it modulo 10^9 + 7.
     *
     * Example 1:
     *
     * Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
     * Output: 6
     *
     * Example 2:
     *
     * Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
     * Output: 12
     *
     *
     * Constraints:
     *
     * 1 <= m, n <= 50
     * 0 <= maxMove <= 50
     * 0 <= startRow < m
     * 0 <= startColumn < n
     */

    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        Integer[][][] cache = new Integer[m][n][maxMove + 1];

        return helper(m, n, maxMove, startRow, startColumn, cache);
    }

    private static int helper(int m, int n, int maxMove, int startRow, int startColumn, Integer[][][] cache) {
        int modulo = 1_000_000_007;

        if (startRow == m || startRow < 0 || startColumn == n || startColumn < 0) {
            return 1;
        }

        if (maxMove == 0) {
            return 0;
        }

        if (cache[startRow][startColumn][maxMove] != null) {
            return cache[startRow][startColumn][maxMove];
        }
        // Why not %M on each individual findPaths(i,j)
        // - the sum of two ((findPaths(i-1, j) %M + findPaths(i+1,j) %M ) can exceed MAX_INTEGER. So % M on the sum is necessary.
        cache[startRow][startColumn][maxMove] = ((helper(m, n, maxMove - 1, startRow + 1, startColumn, cache)
                + helper(m, n, maxMove - 1, startRow - 1, startColumn, cache)) % modulo
                + (helper(m, n, maxMove - 1, startRow, startColumn + 1, cache)
                + helper(m, n, maxMove - 1, startRow, startColumn - 1, cache)) % modulo) % modulo;

        return cache[startRow][startColumn][maxMove];
    }

    public static void main(String[] args) {
        System.out.println(findPaths(2, 2, 2,0, 0));
        System.out.println(findPaths(1, 3, 3, 0, 1));
    }
}
