import java.util.Arrays;

public class Solution {
    /**
     * Given an m x n matrix mat where every row is sorted in strictly increasing order, return the smallest common element in all rows.
     *
     * If there is no common element, return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: mat = [[1,2,3,4,5],[2,4,5,8,10],[3,5,7,9,11],[1,3,5,7,9]]
     * Output: 5
     * Example 2:
     *
     * Input: mat = [[1,2,3],[2,3,4],[2,3,5]]
     * Output: 2
     *
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 500
     * 1 <= mat[i][j] <= 10^4
     * mat[i] is sorted in strictly increasing order.
     */

    // Solution 1: Time: O(m * n), Space: O(10^4)
//    public static int smallestCommonElement(int[][] mat) {
//        int[] count = new int[10001];
//        int m = mat.length;
//        int n = mat[0].length;
//
//        for (int col = 0; col < n; col++) {
//            for (int row = 0; row < m; row++) {
//                count[mat[row][col]]++;
//
//                if (count[mat[row][col]] == m) {
//                    return mat[row][col];
//                }
//            }
//        }
//
//        return -1;
//    }

    // Solution 2: Time: O(mn*logn), Space: O(1)
    public static int smallestCommonElement(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        for (int j = 0; j < n; j++) {
            boolean found = true;
            for (int i = 1; i < m; i++) {
                found = Arrays.binarySearch(mat[i], mat[0][j]) >= 0;
                if (!found) {
                    break;
                }
            }

            if (found) {
                return mat[0][j];
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] mat1 = {{1, 2, 3, 4, 5}, {2, 4, 5, 8, 10}, {3, 5, 7, 9, 11}, {1, 3, 5, 7, 9}};
        int[][] mat2 = {{1, 2, 3}, {2, 3, 4}, {2, 3, 5}};

        System.out.println(smallestCommonElement(mat1));
        System.out.println(smallestCommonElement(mat2));
    }
}
