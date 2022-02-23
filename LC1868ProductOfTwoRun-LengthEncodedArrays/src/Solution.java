import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    /**
     * Run-length encoding is a compression algorithm that allows for an integer array nums with many segments
     * of consecutive repeated numbers to be represented by a (generally smaller) 2D array encoded. Each
     * encoded[i} = [vali, freqi] describes the ith segment of repeated numbers in nums where vali is the
     * value that is repeated freqi times.
     *
     * For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length encoded array encoded = [[1,3],[2,5]].
     * Another way to read this is "three 1's followed by five 2's".
     * The product of two run-length encoded arrays encoded1 and encoded2 can be calculated using the following steps:
     *
     * Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
     * Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
     * Compress prodNums into a run-length encoded array and return it.
     * You are given two run-length encoded arrays encoded1 and encoded2 representing full arrays nums1 and nums2
     * respectively. Both nums1 and nums2 have the same length. Each encoded1[i] = [vali, freqi] describes the
     * ith segment of nums1, and each encoded2[j] = [valj, freqj] describes the jth segment of nums2.
     *
     * Return the product of encoded1 and encoded2.
     *
     * Note: Compression should be done such that the run-length encoded array has the minimum possible length.
     *
     * Example 1:
     *
     * Input: encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
     * Output: [[6,6]]
     * Explanation: encoded1 expands to [1,1,1,2,2,2] and encoded2 expands to [6,6,6,3,3,3].
     * prodNums = [6,6,6,6,6,6], which is compressed into the run-length encoded array [[6,6]].
     *
     * Example 2:
     *
     * Input: encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
     * Output: [[2,3],[6,1],[9,2]]
     * Explanation: encoded1 expands to [1,1,1,2,3,3] and encoded2 expands to [2,2,2,3,3,3].
     * prodNums = [2,2,2,6,9,9], which is compressed into the run-length encoded array [[2,3],[6,1],[9,2]].
     *
     *
     * Constraints:
     *
     * 1 <= encoded1.length, encoded2.length <= 10^5
     * encoded1[i].length == 2
     * encoded2[j].length == 2
     * 1 <= vali, freqi <= 10^4 for each encoded1[i].
     * 1 <= valj, freqj <= 10^4 for each encoded2[j].
     * The full arrays that encoded1 and encoded2 represent are the same length.
     */

    // https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/discuss/1228261/Python3-Clean-two-pointers-solution
    // https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/discuss/1392403/Short-and-Easy-Java-Solution
    public static List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> result = new ArrayList<>();

        int n1 = encoded1.length;
        int n2 = encoded2.length;

        int i = 0;
        int j = 0;

        // Avoid changing input
        int remainingFrequency1 = encoded1[0][1];
        int remainingFrequency2 = encoded2[0][1];

        while(i < n1 && j < n2) {
            int[] pair1 = encoded1[i];
            int[] pair2 = encoded2[j];

            int product = pair1[0] * pair2[0];
            int frequency = Math.min(remainingFrequency1, remainingFrequency2);

            remainingFrequency1 -= frequency;
            remainingFrequency2 -= frequency;

            if (remainingFrequency1 == 0) {
                i++;
                if (i < n1) {
                    remainingFrequency1 = encoded1[i][1];
                }
            }
            if (remainingFrequency2 == 0) {
                j++;
                if (j < n2) {
                    remainingFrequency2 = encoded2[j][1];
                }
            }

            if (result.isEmpty() || result.get(result.size() - 1).get(0) != product) {
                result.add(Arrays.asList(product, frequency));
            } else {
                int lastFrequency = result.get(result.size() - 1).get(1);
                result.get(result.size() - 1).set(1, lastFrequency + frequency);
            }

        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test11 = {{1, 3}, {2, 3}};
        int[][] test12 = {{6, 3}, {3, 3}};

        int[][] test21 = {{1, 3}, {2, 1}, {3, 2}};
        int[][] test22 = {{2, 3}, {3, 3}};

        System.out.println(findRLEArray(test11, test12));
        System.out.println(findRLEArray(test21, test22));
    }

    private static void printArray(int[][] nums) {
        for (int[] n: nums) {
            for (int k: n) {
                System.out.print(k + ", ");
            }
            System.out.println();
        }
    }

}
