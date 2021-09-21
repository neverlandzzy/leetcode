import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    /**
     * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
     *
     * The distance between two adjacent cells is 1.
     *
     *
     * Example 1:
     *
     *  0, 0, 0
     *  0, 1, 0
     *  0, 0, 0
     *
     *
     * Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
     * Output: [[0,0,0],[0,1,0],[0,0,0]]
     *
     *
     * Example 2:
     *
     *  0, 0, 0
     *  0, 1, 0
     *  1, 1, 1
     *
     * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
     * Output: [[0,0,0],[0,1,0],[1,2,1]]
     *
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 104
     * 1 <= m * n <= 104
     * mat[i][j] is either 0 or 1.
     * There is at least one 0 in mat.
     */

    // Solution 1: BFS Time: O(n), Space: O(n)
//    public static int[][] updateMatrix(int[][] mat) {
//        int m = mat.length;
//        int n = mat[0].length;
//
//        int[][] result = new int[m][n];
//        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
//        Queue<int[]> queue = new LinkedList<>();
//
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (mat[i][j] == 0) {
//                    queue.offer(new int[]{i, j});
//                } else {
//                    result[i][j] = -1;
//                }
//            }
//        }
//
//        while (!queue.isEmpty()) {
//            int[] cell = queue.poll();
//            int i = cell[0];
//            int j = cell[1];
//
//            for (int[] dir: directions) {
//                int nextI = i + dir[0];
//                int nextJ = j + dir[1];
//
//                if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || result[nextI][nextJ] != -1) {
//                    continue;
//                }
//                queue.offer(new int[] {nextI, nextJ});
//                result[nextI][nextJ] = result[i][j] + 1;
//            }
//        }
//
//        return result;
//    }

    // Solution 2: DP Time: O(n), Space: O(1)
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int maxDistance = m + n + 1;

        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] != 0) {
                    int top = i > 0 ? result[i - 1][j] : maxDistance;
                    int left = j > 0 ? result[i][j - 1] : maxDistance;

                    result[i][j] = Math.min(left, top) + 1;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (mat[i][j] != 0) {
                    int bottom = i < m - 1 ? result[i + 1][j] : maxDistance;
                    int right = j < n - 1 ? result[i][j + 1] : maxDistance;

                    result[i][j] = Math.min(result[i][j], Math.min(right, bottom) + 1);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        int[][] test2 = {{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};

        int[][] result1 = updateMatrix(test1);
        int[][] result2 = updateMatrix(test2);

        printArray(result1);
        System.out.println();
        printArray(result2);
    }

    private static void printArray(int[][] mat) {
        for (int[] m: mat) {
            for (int i: m) {
                System.out.print(i + ", ");
            }

            System.out.println();
        }
    }
}
