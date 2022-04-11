import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    /**
     * Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
     *
     * In one shift operation:
     *
     * Element at grid[i][j] moves to grid[i][j + 1].
     * Element at grid[i][n - 1] moves to grid[i + 1][0].
     * Element at grid[m - 1][n - 1] moves to grid[0][0].
     * Return the 2D grid after applying shift operation k times.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
     * Output: [[9,1,2],[3,4,5],[6,7,8]]
     *
     * Example 2:
     *
     *
     * Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
     * Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
     * Example 3:
     *
     * Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
     * Output: [[1,2,3],[4,5,6],[7,8,9]]
     *
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m <= 50
     * 1 <= n <= 50
     * -1000 <= grid[i][j] <= 1000
     * 0 <= k <= 100
     */

    // Solution 1 - Time: O(m * n)
    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        k %= total;
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            result.add(list);
            for (int j = 0; j < n; j++) {
                // i * n + j: The original place of (i, j) in 1D format
                // i * n + j - k: after k steps, i * n + j - k moved to (i * n + j)
                int index = ((i * n + j) - k + total) % total;
                list.add(grid[index / n][index % n]);
            }
        }

        return result;
    }

    // Solution 2 - Time: O(m * n * k)
//    public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
//        int m = grid.length;
//        int n = grid[0].length;
//
//        k %= (m * n);
//        while (k > 0) {
//            int previous = grid[m - 1][n - 1];
//
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    int temp = grid[i][j];
//                    grid[i][j] = previous;
//                    previous = temp;
//                }
//            }
//            k--;
//        }
//
//        List<List<Integer>> result = new ArrayList<>();
//
//        for (int i = 0; i < m; i++) {
//            List<Integer> list = new ArrayList<>();
//            for (int j = 0; j < n; j++) {
//                list.add(grid[i][j]);
//            }
//            result.add(list);
//        }
//
//        return result;
//    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] test2 = {{3, 8, 1, 9}, {19, 7, 2, 5}, {4, 6, 11, 10}, {12, 0, 21, 13}};
        int[][] test3 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println(shiftGrid(test1, 1));
        System.out.println(shiftGrid(test2, 4));
        System.out.println(shiftGrid(test3, 9));
    }
}
