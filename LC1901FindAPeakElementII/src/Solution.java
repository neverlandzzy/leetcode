public class Solution {
    /**
     * A peak element in a 2D grid is an element that is strictly greater than all of its adjacent neighbors to the left, right, top, and bottom.
     *
     * Given a 0-indexed m x n matrix mat where no two adjacent cells are equal, find any peak element mat[i][j] and return the length
     * 2 array [i,j].
     *
     * You may assume that the entire matrix is surrounded by an outer perimeter with the value -1 in each cell.
     *
     * You must write an algorithm that runs in O(m log(n)) or O(n log(m)) time.
     *
     * Example 1:
     *
     * Input: mat = [[1,4],[3,2]]
     * Output: [0,1]
     * Explanation: Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
     *
     * Example 2:
     *
     * Input: mat = [[10,20,15],[21,30,14],[7,16,32]]
     * Output: [1,1]
     * Explanation: Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
     *
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 1 <= m, n <= 500
     * 1 <= mat[i][j] <= 10^5
     * No two adjacent cells are equal.
     */

    public static int[] findPeakGrid(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int maxRow = 0;
            for (int i = 0; i < m; i++) {
                if (mat[i][mid] > mat[maxRow][mid]) {
                    maxRow = i;
                }
            }

            int curElement = mat[maxRow][mid];
            int leftElement = mid == 0 ? -1 : mat[maxRow][mid - 1];
            int rightElement = mid == n - 1 ? -1 : mat[maxRow][mid + 1];

            if (curElement > leftElement && curElement > rightElement) {
                return new int[]{maxRow, mid};
            }

            if (curElement < leftElement) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 4}, {3, 2}};
        int[][] test2 = {{10, 20, 15}, {21, 30, 14}, {7, 16, 32}};

        int[] result1 = findPeakGrid(test1);
        int[] result2 = findPeakGrid(test2);

        printArray(result1);
        printArray(result2);
    }

    private static void printArray(int[] nums) {
        for (int i: nums) {
            System.out.print(i + ", ");
        }

        System.out.println();
    }
}
