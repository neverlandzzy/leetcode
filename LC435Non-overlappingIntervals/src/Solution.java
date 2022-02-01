import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    /**
     * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to
     * make the rest of the intervals non-overlapping.
     *
     *
     *
     * Example 1:
     *
     * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
     * Output: 1
     * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
     *
     * Example 2:
     *
     * Input: intervals = [[1,2],[1,2],[1,2]]
     * Output: 2
     * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
     *
     * Example 3:
     *
     * Input: intervals = [[1,2],[2,3]]
     * Output: 0
     * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
     *
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 10^5
     * intervals[i].length == 2
     * -5 * 10^4 <= starti < endi <= 5 * 10^4
     */

    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Arrays.sort(intervals, Comparator.comparingInt((int[] i) -> i[0]));
        int counter = 0;
        int[] newInterval = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < newInterval[1]) {
                counter++;
                newInterval[1] = Math.min(intervals[i][1], newInterval[1]);
            } else {
                newInterval = intervals[i];
            }
        }

        return counter;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        int[][] test2 = {{1, 2}, {1, 2}, {1, 2}};
        int[][] test3 = {{1, 2}, {2, 3}};

        System.out.println(eraseOverlapIntervals(test1));
        System.out.println(eraseOverlapIntervals(test2));
        System.out.println(eraseOverlapIntervals(test3));
    }
}
