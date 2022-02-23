import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    /**
     * Given an array intervals where intervals[i] = [li, ri] represent the interval [li, ri), remove all intervals that are
     * covered by another interval in the list.
     *
     * The interval [a, b) is covered by the interval [c, d) if and only if c <= a and b <= d.
     *
     * Return the number of remaining intervals.
     *
     *
     *
     * Example 1:
     *
     * Input: intervals = [[1,4],[3,6],[2,8]]
     * Output: 2
     * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
     * Example 2:
     *
     * Input: intervals = [[1,4],[2,3]]
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= intervals.length <= 1000
     * intervals[i].length == 2
     * 0 <= li <= ri <= 10^5
     * All the given intervals are unique.
     */

    public static int removeCoveredIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int count = 0;
        Arrays.sort(intervals, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        int curEnd = 0;
        int prevEnd = 0;

        for (int[] interval: intervals) {
            curEnd = interval[1];
            if (curEnd > prevEnd) {
                count++;
                prevEnd = curEnd;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 4}, {3, 6}, {2, 8}};
        int[][] test2 = {{1, 4}, {2, 3}};
        int[][] test3 = {{1, 15}, {3, 6}, {2, 8}, {4, 9}, {6, 11}};

        System.out.println(removeCoveredIntervals(test1));
        System.out.println(removeCoveredIntervals(test2));
        System.out.println(removeCoveredIntervals(test3));
    }
}
