import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * In MATLAB, there is a handy function called reshape which can reshape an m x n matrix into a new one
     * with a different size r x c keeping its original data.
     *
     * You are given an m x n matrix mat and two integers r and c representing the number of rows and the
     * number of columns of the wanted reshaped matrix.
     *
     * The reshaped matrix should be filled with all the elements of the original matrix in the same row-traversing
     * order as they were.
     *
     * If the reshape operation with given parameters is possible and legal, output the new reshaped matrix;
     * Otherwise, output the original matrix.
     *
     *
     * Example 1:
     * Input: mat = [[1,2],[3,4]], r = 1, c = 4
     * Output: [[1,2,3,4]]
     *
     *
     * Example 2:
     * Input: mat = [[1,2],[3,4]], r = 2, c = 4
     * Output: [[1,2],[3,4]]
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 100
     * -1000 <= mat[i][j] <= 1000
     * 1 <= r, c <= 300
     */

    // Solution 1: Time O(m * n), Space O(m * n)
//    public static int[][] matrixReshape(int[][] mat, int r, int c) {
//        int m = mat.length;
//        int n = mat[0].length;
//
//        if (m * n != r * c) {
//            return mat;
//        }
//
//        int[][] result = new int[r][c];
//        List<Integer> list = new ArrayList<>();
//
//        for (int[] rows : mat) {
//            for (int num : rows) {
//                list.add(num);
//            }
//        }
//
//        int index = 0;
//
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//                result[i][j] = list.get(index++);
//            }
//        }
//
//        return result;
//    }

    // Solution 2: Time O(m * n), Space O(1)
    public static int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;

        if (m * n != r * c) {
            return mat;
        }

        int rIndex = 0;
        int cIndex = 0;

        int[][] result = new int[r][c];

        for (int[] rows : mat) {
            for (int num : rows) {
                result[rIndex][cIndex] = num;
                cIndex++;

                if (cIndex == c) {
                    cIndex = 0;
                    rIndex++;
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[][] test1 = {{1, 2}, {3, 4}};
        int[][] test2 = {{1, 2}, {3, 4}};
        int[][] test3 = {{1, 2, 3, 4}};

        int[][] result1 = matrixReshape(test1, 1, 4);
        int[][] result2 = matrixReshape(test2, 2, 4);
        int[][] result3 = matrixReshape(test3, 2, 2);

        printArray(result1);
        printArray(result2);
        printArray(result3);
    }

    private static void printArray(int[][] array) {
        for (int[] a: array) {
            for (int i: a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
    }

}
