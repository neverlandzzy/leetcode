import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {
    /**
     * You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries
     * that you can collect from the (i, j) cell.
     *
     * You have two robots that can collect cherries for you:
     *
     * Robot #1 is located at the top-left corner (0, 0), and
     * Robot #2 is located at the top-right corner (0, cols - 1).
     * Return the maximum number of cherries collection using both robots by following the rules below:
     *
     * From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
     * When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
     * When both robots stay in the same cell, only one takes the cherries.
     * Both robots cannot move outside of the grid at any moment.
     * Both robots should reach the bottom row in grid.
     *
     *
     * Example 1:
     * Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
     * Output: 24
     * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
     * Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
     * Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
     * Total of cherries: 12 + 12 = 24.
     *
     * Example 2:
     * Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
     * Output: 28
     * Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
     * Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
     * Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
     * Total of cherries: 17 + 11 = 28.
     *
     *
     * Constraints:
     *
     * rows == grid.length
     * cols == grid[i].length
     * 2 <= rows, cols <= 70
     * 0 <= grid[i][j] <= 100
     */

    // https://leetcode.com/problems/cherry-pickup-ii/discuss/1674112/Well-Detailed-Explaination-JAVA-C%2B%2B-oror-Easy-for-mind-to-Accept-it
    // 1. Each time, move robots row by row
    // 2. At each row, robot moves have 3 choices in columns: {-1, 0, 1}. For 2 robots, we have 3*3 = 9 choices
    // 3. If both robots land in the same cube, just count once
    public static int cherryPickup(int[][] grid) {
        int[] dir = {-1, 0, 1};
        int row = grid.length;
        int col = grid[0].length;
        int[][][] dp = new int[row][col][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int k = 0; k < col; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        // 初始状态时，两个机器人分别在(0, 0)和(0, col - 1)的位置上
        dp[0][0][col - 1] = grid[0][0] + grid[0][col - 1];
        int max = dp[0][0][col - 1];

        for (int i = 1; i < row; i++) {
            for (int c1 = 0; c1 < col; c1++) {
                for (int c2 = 0; c2 < col; c2++) {
                    int lastStep = dp[i - 1][c1][c2];

                    if (lastStep >= 0) {
                        for (int d1: dir) {
                            int col1 = c1 + d1;
                            for (int d2: dir) {
                                int col2 = c2 + d2;

                                if (col1 >= 0 && col1 < col && col2 >= 0 && col2 < col) {
                                    dp[i][col1][col2] = Math.max(dp[i][col1][col2], lastStep + ((col1 == col2) ? grid[i][col1] : (grid[i][col1] + grid[i][col2])));
                                    max = Math.max(max, dp[i][col1][col2]);
                                }
                            }
                        }
                    }
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] test1 = {{3, 1, 1}, {2, 5, 1}, {1, 5, 5}, {2, 1, 1}};
        int[][] test2 = {{1, 0, 0, 0, 0, 0, 1}, {2, 0, 0, 0, 0, 3, 0}, {2, 0, 9, 0, 0, 0, 0}, {0, 3, 0, 5, 4, 0, 0},
                {1, 0, 2, 3, 0, 0, 6}};

        System.out.println(cherryPickup(test1));
        System.out.println(cherryPickup(test2));
    }
}
