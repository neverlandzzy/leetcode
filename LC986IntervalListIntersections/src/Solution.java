import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * You are given two lists of closed intervals, firstList and secondList, where firstList[i] = [starti, endi] and
     * secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
     *
     * Return the intersection of these two interval lists.
     *
     * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
     *
     * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
     * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
     *
     * Example 1:
     *
     *
     * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
     * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
     *
     * Example 2:
     *
     * Input: firstList = [[1,3],[5,9]], secondList = []
     * Output: []
     *
     * Example 3:
     *
     * Input: firstList = [], secondList = [[4,8],[10,12]]
     * Output: []
     *
     * Example 4:
     *
     * Input: firstList = [[1,7]], secondList = [[3,10]]
     * Output: [[3,7]]
     *
     *
     * Constraints:
     *
     * 0 <= firstList.length, secondList.length <= 1000
     * firstList.length + secondList.length >= 1
     * 0 <= starti < endi <= 109
     * endi < starti+1
     * 0 <= startj < endj <= 109
     * endj < startj+1
     */

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        int n1 = firstList.length;
        int n2 = secondList.length;

        while (i < n1 && j < n2) {
            int[] interval1 = firstList[i];
            int[] interval2 = secondList[j];

            int low = Math.max(interval1[0], interval2[0]);
            int high = Math.min(interval1[1], interval2[1]);

            if (low <= high ) {
                list.add(new int[]{low, high});
            }

            if (interval1[1] > interval2[1]) {
                j++;
            } else {
                i++;
            }
        }

        return list.toArray(new int[list.size()][]);
    }

    public static void main(String[] args) {
        int[][] firstList1 = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList1 = {{1, 5}, {8, 12}, {15, 24}, {25, 25}};

        int[][] result1 = intervalIntersection(firstList1, secondList1);
        printArray(result1);

        int[][] firstList2 = {{1, 3}, {5, 9}};
        int[][] secondList2 = {};
        int[][] result2 = intervalIntersection(firstList2, secondList2);
        printArray(result2);

        int[][] firstList3 = {};
        int[][] secondList3 = {{4, 8}, {10, 12}};
        int[][] result3 = intervalIntersection(firstList3, secondList3);
        printArray(result3);

        int[][] firstList4 = {{1, 7}};
        int[][] secondList4 = {{3, 10}};
        int[][] result4 = intervalIntersection(firstList4, secondList4);
        printArray(result4);
    }

    private static void printArray(int[][] arr) {
        for (int[] nums: arr) {
            System.out.print("[");
            for (int i: nums) {
                System.out.print(i + ", ");
            }
            System.out.print("], ");
        }
        System.out.println();
    }
}
