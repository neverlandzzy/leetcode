import java.util.Arrays;

public class Solution {
    /**
     * Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
     *
     * To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
     *
     * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
     *
     * Example 1:
     *
     * Input: [[1,1,0],[1,0,1],[0,0,0]]
     * Output: [[1,0,0],[0,1,0],[1,1,1]]
     * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
     * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
     *
     * Example 2:
     *
     * Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
     * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
     * Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
     * Notes:
     *
     * 1 <= A.length = A[0].length <= 20
     * 0 <= A[i][j] <= 1
     */

    public static int[][] flipAndInvertImage(int[][] A) {
        if (A == null || A.length == 0 || A[0] == null) {
            return A;
        }

        int m = A.length;
        int n = A[0].length;

        for (int j = 0; j < n/ 2; j++) {
            for (int i = 0; i < m; i++) {
                int tmp = A[i][j];
                A[i][j] = 1 - A[i][n - j - 1];
                A[i][n - j - 1] = 1 - tmp;
            }
        }

        if (n % 2 != 0) {
            for (int i = 0; i < m; i++) {
                A[i][n / 2] = 1 - A[i][n / 2];
            }
        }

        return A;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}};
        int[][] test2 = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 1, 1, 1}, {1, 0, 1, 0}};

        int[][] result1 = flipAndInvertImage(test1);
        int[][] result2 = flipAndInvertImage(test2);

        for (int[] r: result1) {
            printArray(r);
        }

        System.out.println("*********");

        for (int[] r: result2) {
            printArray(r);
        }
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
