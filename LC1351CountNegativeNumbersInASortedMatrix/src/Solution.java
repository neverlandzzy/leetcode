public class Solution {

    /**
     * Given a m * n matrix grid which is sorted in non-increasing order both row-wise and column-wise.
     *
     * Return the number of negative numbers in grid.
     *
     *
     *
     * Example 1:
     *
     * Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
     * Output: 8
     * Explanation: There are 8 negatives number in the matrix.
     * Example 2:
     *
     * Input: grid = [[3,2],[1,0]]
     * Output: 0
     * Example 3:
     *
     * Input: grid = [[1,-1],[-1,-1]]
     * Output: 3
     * Example 4:
     *
     * Input: grid = [[-1]]
     * Output: 1
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * -100 <= grid[i][j] <= 100
     */

    public static int countNegatives(int[][] grid) {

    }

    public static void main(String[] args) {
        int[][] test1 = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        int[][] test2 = {{3, 2}, {1, 0}};
        int[][] test3 = {{1, -1}, {-1, -1}};
        int[][] test4 = {{-1}};

        System.out.println(countNegatives(test1));
        System.out.println(countNegatives(test2));
        System.out.println(countNegatives(test3));
        System.out.println(countNegatives(test4));
    }
}
