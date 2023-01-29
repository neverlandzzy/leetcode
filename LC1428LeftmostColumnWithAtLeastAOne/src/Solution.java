import java.util.List;

public class Solution {
    /**
     * A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.
     *
     * Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index
     * does not exist, return -1.
     *
     * You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:
     *
     * BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
     * BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows
     * x cols.
     * Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to
     * circumvent the judge will result in disqualification.
     *
     * For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.
     *
     * Example 1:
     *
     * Input: mat = [[0,0],[1,1]]
     * Output: 0
     *
     * Example 2:
     *
     * Input: mat = [[0,0],[0,1]]
     * Output: 1
     *
     * Example 3:
     *
     * Input: mat = [[0,0],[0,0]]
     * Output: -1
     *
     *
     * Constraints:
     *
     * rows == mat.length
     * cols == mat[i].length
     * 1 <= rows, cols <= 100
     * mat[i][j] is either 0 or 1.
     * mat[i] is sorted in non-decreasing order.
     */

    public static int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimension = binaryMatrix.dimensions();
        int m = dimension.get(0);
        int n = dimension.get(1);

        int start = 0;
        int end = n;
        int result = -1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (checkColumn(binaryMatrix, mid, m)) {
                end = mid;
                result = mid;
            } else {
                start = mid + 1;
            }
        }

        return result;
    }

    // Check if there is 1 in column col
    private static boolean checkColumn(BinaryMatrix binaryMatrix, int col, int m) {
        for (int i = 0; i < m; i++) {
            if (binaryMatrix.get(i, col) == 1) {
                return true;
            }
        }

        return false;
    }
}
