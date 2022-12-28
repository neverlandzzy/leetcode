public class Solution {
    /**
     * Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
     *
     * A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally
     * left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).
     *
     * Example 1:
     *
     *
     * Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
     * Output: 13
     * Explanation: There are two falling paths with a minimum sum as shown.
     *
     * Example 2:
     *
     *
     * Input: matrix = [[-19,57],[-40,-5]]
     * Output: -59
     * Explanation: The falling path with a minimum sum is shown.
     *
     *
     * Constraints:
     *
     * n == matrix.length == matrix[i].length
     * 1 <= n <= 100
     * -100 <= matrix[i][j] <= 100
     */

    // Solution 1: Time: O(n^2),  Space: O(n^2)
//    public static int minFallingPathSum(int[][] matrix) {
//        int m = matrix.length;
//        int n = matrix[0].length;
//
//        int[][] dp = new int[m][n];
//
//        for (int i = 0; i < n; i++) {
//            dp[0][i] = matrix[0][i];
//        }
//
//        for (int i = 1; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                int left = j == 0 ? Integer.MAX_VALUE : dp[i - 1][j - 1];
//                int mid = dp[i - 1][j];
//                int right = j == n - 1 ? Integer.MAX_VALUE : dp[i - 1][j + 1];
//
//                dp[i][j] = Math.min(Math.min(left, right), mid) + matrix[i][j];
//            }
//        }
//
//        int result = Integer.MAX_VALUE;
//
//        for (int i = 0; i < n; i++) {
//            result = Math.min(result, dp[m - 1][i]);
//        }
//
//        return result;
//    }

    // Solution 2: Time: O(n^2),  Space: O(n)
    public static int minFallingPathSum(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[] dp = new int[n];
        int result = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            dp[i] = matrix[0][i];
        }

        for (int i = 1; i < m; i++) {
            int[] currentRow = new int[n];
            for (int j = 0; j < n; j++) {
                int left = j == 0 ? Integer.MAX_VALUE : dp[j - 1];
                int mid = dp[j];
                int right = j == n - 1 ? Integer.MAX_VALUE : dp[j + 1];

                currentRow[j] = Math.min(Math.min(left, right), mid) + matrix[i][j];
            }
            dp = currentRow;
        }

        for (int i = 0; i < n; i++) {
            result = Math.min(result, dp[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{2, 1, 3}, {6, 5, 4}, {7, 8, 9}};
        int[][] test2 = {{-19, 57}, {-40, -5}};

        System.out.println(minFallingPathSum(test1));
        System.out.println(minFallingPathSum(test2));
    }
}
