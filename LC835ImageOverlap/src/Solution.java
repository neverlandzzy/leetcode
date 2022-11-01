import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.
     *
     * We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top
     * of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.
     *
     * Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.
     *
     * Return the largest possible overlap.
     *
     *
     * Example 1:
     *
     *
     * Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
     * Output: 3
     * Explanation: We translate img1 to right by 1 unit and down by 1 unit.
     *
     * The number of positions that have a 1 in both images is 3 (shown in red).
     *
     * Example 2:
     *
     * Input: img1 = [[1]], img2 = [[1]]
     * Output: 1
     *
     * Example 3:
     *
     * Input: img1 = [[0]], img2 = [[0]]
     * Output: 0
     *
     *
     * Constraints:
     *
     * n == img1.length == img1[i].length
     * n == img2.length == img2[i].length
     * 1 <= n <= 30
     * img1[i][j] is either 0 or 1.
     * img2[i][j] is either 0 or 1.
     */

    // https://leetcode.com/problems/image-overlap/solutions/138976/a-generic-and-easy-to-understand-method/
    // Time: O(n^4)
    public static int largestOverlap(int[][] img1, int[][] img2) {
        List<int[]> list1 = new ArrayList<>();
        List<int[]> list2 = new ArrayList<>();

        int n = img1.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    list1.add(new int[]{i, j});
                }

                if (img2[i][j] == 1) {
                    list2.add(new int[]{i, j});
                }
            }
        }

        Map<String, Integer> map = new HashMap<>();

        // each list may have up to n^2 pairs, so time complexity here is O(n^4)
        for (int[] p1: list1) {
            for (int[] p2: list2) {
                String key = ((p1[0] - p2[0]) + "," + (p1[1] - p2[1]));
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }

        int largestOverlap = 0;

        for (int count: map.values()) {
            largestOverlap = Math.max(largestOverlap, count);
        }

        return largestOverlap;
    }

    public static void main(String[] args) {
        int[][] test11 = {{1, 1, 0}, {0, 1, 0}, {0, 1, 0}};
        int[][] test12 = {{0, 0, 0}, {0, 1, 1}, {0, 0, 1}};

        int[][] test21 = {{1}};
        int[][] test22 = {{1}};

        int[][] test31 = {{0}};
        int[][] test32 = {{0}};

        System.out.println(largestOverlap(test11, test12));
        System.out.println(largestOverlap(test21, test22));
        System.out.println(largestOverlap(test31, test32));
    }
}
