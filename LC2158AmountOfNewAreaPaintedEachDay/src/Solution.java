import java.util.Map;
import java.util.TreeMap;

public class Solution {
    /**
     * There is a long and thin painting that can be represented by a number line. You are given a 0-indexed 2D integer array paint of length n,
     * where paint[i] = [starti, endi]. This means that on the ith day you need to paint the area between starti and endi.
     *
     * Painting the same area multiple times will create an uneven painting so you only want to paint each area of the painting at most once.
     *
     * Return an integer array worklog of length n, where worklog[i] is the amount of new area that you painted on the ith day.
     *
     *
     * Example 1:
     *
     * Input: paint = [[1,4],[4,7],[5,8]]
     * Output: [3,3,1]
     * Explanation:
     * On day 0, paint everything between 1 and 4.
     * The amount of new area painted on day 0 is 4 - 1 = 3.
     * On day 1, paint everything between 4 and 7.
     * The amount of new area painted on day 1 is 7 - 4 = 3.
     * On day 2, paint everything between 7 and 8.
     * Everything between 5 and 7 was already painted on day 1.
     * The amount of new area painted on day 2 is 8 - 7 = 1.
     *
     * Example 2:
     *
     * Input: paint = [[1,4],[5,8],[4,7]]
     * Output: [3,3,1]
     * Explanation:
     * On day 0, paint everything between 1 and 4.
     * The amount of new area painted on day 0 is 4 - 1 = 3.
     * On day 1, paint everything between 5 and 8.
     * The amount of new area painted on day 1 is 8 - 5 = 3.
     * On day 2, paint everything between 4 and 5.
     * Everything between 5 and 7 was already painted on day 1.
     * The amount of new area painted on day 2 is 5 - 4 = 1.
     *
     * Example 3:
     *
     * Input: paint = [[1,5],[2,4]]
     * Output: [4,0]
     * Explanation:
     * On day 0, paint everything between 1 and 5.
     * The amount of new area painted on day 0 is 5 - 1 = 4.
     * On day 1, paint nothing because everything between 2 and 4 was already painted on day 0.
     * The amount of new area painted on day 1 is 0.
     *
     *
     * Constraints:
     *
     * 1 <= paint.length <= 10^5
     * paint[i].length == 2
     * 0 <= starti < endi <= 5 * 10^4
     */

    // Solution 1: Jump Line
    // https://leetcode.com/problems/amount-of-new-area-painted-each-day/solutions/1751389/jump-line/
    // line[] - line to be painted
    // 在第i天，尝试将paint[i]范围内的line[]上色：
    //    如果一个点没有被上过色，则将这个点设置为paint[i][1]，并在当天上色的结果上加1
    //    如果一个点已经被上过色，则根据这个点的值，直接跳到已经上色过的区域的结尾（e.g. 之前上色1~3, 则这次跳到4）
    // 以[1, 4][5, 9][4, 10]为例, line[50000]初始值为0 (50000根据Constraints：0 <= starti < endi <= 5 * 10^4)，result[]记录结果
    // 第一步给[1, 4]上色，这片区域都没有被上过色，所以line更新为[0, 4, 4, 4, 0, 0, ...], result更新为[3, 0, 0, ...]
    // 第二步给[5, 9]上色，这片区域都没有被上过色，所以line更新为[0, 4, 4, 4, 0, 9， 9，9，9， 0，0 ，...], result更新为[3, 4, 0, ...]
    // 第三步给[4, 10]上色，4没有被上过色，result[i]+1, 5在第二步被上色，记录为9，即第二步上色到了8，这次上色可以跳过5-8，直接检查9
    //    9没有被上过色，result[i]+1，所以line更新为[0, 4, 4, 4, 10, 9， 9，9，9， 10，0 ，...], result更新为[3, 4, 2]

    // Time: O(m + n): m - length of the fence, n - length of the paint
//    public static int[] amountPainted(int[][] paint) {
//        if (paint == null || paint.length == 0) {
//            return new int[]{};
//        }
//
//        int[] line = new int[50000];
//        int n = paint.length;
//        int[] result = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            int start = paint[i][0];
//            int end = paint[i][1];
//
//            while (start < end) {
//                // jump表示下一步检查哪个点：
//                // 如果这个点没有被上过色，即line[start] == 0， 则下一步检查start后的下一个点，即start+1，同时因为这个点要上色，result[i]也加1
//                // 如果这个点已经被上过色，则下一步直接跳到line[start]去检查，这个点不需要上色，所以result[i]不变
//                int jump = Math.max(start + 1, line[start]);
//                result[i] += line[start] == 0 ? 1 : 0;
//                line[start] = Math.max(line[start], end);
//                start = jump;
//            }
//        }
//
//        return result;
//    }

    // Solution 2: Merge Intervals
    // Time: O(n*logm): m - length of the fence, n - length of the paint
    // https://leetcode.com/problems/amount-of-new-area-painted-each-day/solutions/1784268/java-treemap-merge-intervals-easy-to-understand-beats-95-83/
    public static int[] amountPainted(int[][] paint) {
        int n = paint.length;
        int[] result = new int[n];
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            int start = paint[i][0];
            int end = paint[i][1];
            int areaToPaint = end - start;

            Map.Entry<Integer, Integer> floor = treeMap.floorEntry(paint[i][0]);

            if (floor != null) {
                if (floor.getValue() >= end) {
                    // [start, end] 完全被floor覆盖
                    continue;
                }

                if (floor.getValue() >= start) {
                    // [start, end] 和floor有overlap
                    areaToPaint -= floor.getValue() - start;
                    treeMap.remove(floor.getKey());
                    start = floor.getKey();
                }
            }

            Map.Entry<Integer, Integer> ceiling = treeMap.ceilingEntry(paint[i][0]);

            while (ceiling != null && end >= ceiling.getKey()) {
                areaToPaint -= Math.min(end, ceiling.getValue()) - ceiling.getKey();
                treeMap.remove(ceiling.getKey());
                end = Math.max(end, ceiling.getValue());
                ceiling = treeMap.ceilingEntry(paint[i][0]);
            }

            result[i] = areaToPaint;
            treeMap.put(start, end);
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 4}, {4, 7}, {5, 8}};
        int[][] test2 = {{1, 4}, {5, 8}, {4, 7}};
        int[][] test3 = {{1, 5}, {2, 4}};

        int[] result1 = amountPainted(test1);
        int[] result2 = amountPainted(test2);
        int[] result3 = amountPainted(test3);

        printArray(result1);
        printArray(result2);
        printArray(result3);
    }

    private static void printArray(int[] array) {
        for (int a: array) {
            System.out.print(a + ", ");
        }

        System.out.println();
    }
}
