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

    // https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/discuss/512165/Java-binary-search-beats-100-explained
    // Time: O(m * logn), Space: O(1)
    public static int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;

        for (int i = 0; i < m; i++) {
            if (grid[i][0] < 0) {
                result += n;
                continue;
            }

            if (grid[i][n - 1] >= 0) {
                continue;
            }

            int start = 0;
            int end = n - 1;

            while (start + 1 < end) {
                int mid = start + (end - start) / 2;
                if (grid[i][mid] < 0) {
                    end = mid;
                } else {
                    start = mid;
                }
            }

            result += grid[i][end] < 0 ? (n - end) : (n - end - 1);
            // with optimization at line #50-52, line #66 can be:
            // result += (n - end);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{4, 3, 2, -1}, {3, 2, 1, -1}, {1, 1, -1, -2}, {-1, -1, -2, -3}};
        int[][] test2 = {{3, 2}, {1, 0}};
        int[][] test3 = {{1, -1}, {-1, -1}};
        int[][] test4 = {{-1}};
        int[][] test5 = {{3, 0, 0}, {2, 0, 0}};

        System.out.println(countNegatives(test1));
        System.out.println(countNegatives(test2));
        System.out.println(countNegatives(test3));
        System.out.println(countNegatives(test4));
        System.out.println(countNegatives(test5));
    }
}
