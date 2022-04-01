import java.util.PriorityQueue;

public class Solution {
    /**
     * You are given an m x n binary matrix mat of 1's (representing soldiers) and 0's (representing civilians).
     * The soldiers are positioned in front of the civilians. That is, all the 1's will appear to the left of all
     * the 0's in each row.
     *
     * A row i is weaker than a row j if one of the following is true:
     *
     * The number of soldiers in row i is less than the number of soldiers in row j.
     * Both rows have the same number of soldiers and i < j.
     * Return the indices of the k weakest rows in the matrix ordered from weakest to strongest.
     *
     * Example 1:
     *
     * Input: mat =
     * [[1,1,0,0,0],
     *  [1,1,1,1,0],
     *  [1,0,0,0,0],
     *  [1,1,0,0,0],
     *  [1,1,1,1,1]],
     * k = 3
     * Output: [2,0,3]
     * Explanation:
     * The number of soldiers in each row is:
     * - Row 0: 2
     * - Row 1: 4
     * - Row 2: 1
     * - Row 3: 2
     * - Row 4: 5
     * The rows ordered from weakest to strongest are [2,0,3,1,4].
     *
     * Example 2:
     *
     * Input: mat =
     * [[1,0,0,0],
     *  [1,1,1,1],
     *  [1,0,0,0],
     *  [1,0,0,0]],
     * k = 2
     * Output: [0,2]
     * Explanation:
     * The number of soldiers in each row is:
     * - Row 0: 1
     * - Row 1: 4
     * - Row 2: 1
     * - Row 3: 1
     * The rows ordered from weakest to strongest are [0,2,3,1].
     *
     *
     * Constraints:
     *
     * m == mat.length
     * n == mat[i].length
     * 2 <= n, m <= 100
     * 1 <= k <= m
     * matrix[i][j] is either 0 or 1.
     */

    // Solution 1: Binary Search + Priority Queue
    // Time: O(m lognk), Space: O(k)
    public static int[] kWeakestRows(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>((a1, a2) -> {
            if (a1[0] == a2[0]) {
                return a2[1] - a1[1];
            }

            return a2[0] - a1[0];
        });

        for (int i = 0; i < m; i++) {
            int strength = findFirstZero(mat[i]);
            priorityQueue.offer(new int[]{strength, i});

            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        int[] result = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            result[i] = priorityQueue.poll()[1];
        }
        return result;
    }

    private static int findFirstZero(int[] mat) {
        int start = 0;
        int end = mat.length;  // mat 可能全部是1，所以end 要在最后一个元素之后。

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (mat[mid] == 1) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }

    // Solution 2: Vertical Iteration
    // Time: O(m*n), Space: O(1) 时间比Solution1慢，但节省空间
//    public static int[] kWeakestRows(int[][] mat, int k) {
//        int[] result = new int[k];
//
//        int m = mat.length;
//        int n = mat[0].length;
//        int index = 0;
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                if (index == k) {
//                    break;
//                }
//
//                if (mat[j][i] == 0 && (i == 0 || mat[j][i - 1] == 1)) {
//                    result[index] = j;
//                    index++;
//                }
//            }
//        }
//
//        for (int i = 0; i < m; i++) {
//            if (index == k) {
//                break;
//            }
//
//            if (mat[i][n - 1] == 1) {
//                result[index] = i;
//                index++;
//            }
//        }
//
//        return result;
//    }

    public static void main(String[] args) {
        int[][] mat1 = {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 1, 1, 1}};
        int[][] mat2 = {{1, 0, 0, 0}, {1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}};

        int[] res1 = kWeakestRows(mat1, 3);
        int[] res2 = kWeakestRows(mat2, 2);

        printArray(res1);
        printArray(res2);
    }

    private static void printArray(int[] array) {
        for (int a: array) {
            System.out.print(a + ", ");
        }
        System.out.println();
    }
}
