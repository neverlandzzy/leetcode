import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * You are given an array colors, in which there are three colors: 1, 2 and 3.
     *
     * You are also given some queries. Each query consists of two integers i and c, return the shortest distance between the given
     * index i and the target color c. If there is no solution return -1.
     *
     *
     *
     * Example 1:
     *
     * Input: colors = [1,1,2,1,3,2,2,3,3], queries = [[1,3],[2,2],[6,1]]
     * Output: [3,0,3]
     * Explanation:
     * The nearest 3 from index 1 is at index 4 (3 steps away).
     * The nearest 2 from index 2 is at index 2 itself (0 steps away).
     * The nearest 1 from index 6 is at index 3 (3 steps away).
     *
     * Example 2:
     *
     * Input: colors = [1,2], queries = [[0,3]]
     * Output: [-1]
     * Explanation: There is no 3 in the array.
     *
     *
     * Constraints:
     *
     * 1 <= colors.length <= 5*10^4
     * 1 <= colors[i] <= 3
     * 1 <= queries.length <= 5*10^4
     * queries[i].length == 2
     * 0 <= queries[i][0] < colors.length
     * 1 <= queries[i][1] <= 3
     */

    // Solution 1: Pre-computed - Time: O(Q+N) - Q: length of queries and N: length of colors.
    public static List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        int n = colors.length;
        int[] rightmost = {0, 0, 0};
        int[] leftmost = {n - 1, n - 1, n - 1};
        int[][] distances = new int[3][n];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = -1;
            }
        }

        // for each index i, look for its nearest color c on its right (i - j)
        for (int i = 0; i < n; i++) {
            int color = colors[i] - 1;
            for (int j = rightmost[color]; j < i + 1; j++) {
                distances[color][j] = i - j;
            }
            rightmost[color] = i + 1;
        }

        // for each index i, look for its nearest color c on its left (i - j)
        for (int i = n - 1; i >= 0; i--) {
            int color = colors[i] - 1;
            for (int j = leftmost[color]; j > i - 1; j--) {
                if (distances[color][j] == -1 || distances[color][j] > j - i) {
                    distances[color][j] = j - i;
                }
            }
            leftmost[color] = i - 1;
        }

        List<Integer> result = new ArrayList<>();
        for (int[] query: queries) {
            int index = query[0];
            int color = query[1];

            result.add(distances[color - 1][index]);
        }

        return result;
    }

    // Solution 2: Binary Search - Time: O(QlogN+N) - Q: length of queries and N: length of colors.
    /*
    public static List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < colors.length; i++) {
            if (!map.containsKey(colors[i])) {
                map.put(colors[i], new ArrayList<>());
            }

            map.get(colors[i]).add(i);
        }

        for (int[] query: queries) {
            int index = query[0];
            int color = query[1];

            if (!map.containsKey(color)) {
                result.add(-1);
            } else {
                result.add(getShortestDistance(map.get(color), index));
            }
        }

        return result;
    }

    private static int getShortestDistance(List<Integer> list, int index) {
        int start = 0;
        int end = list.size() - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (list.get(mid) == index) {
                return 0;
            }

            if (list.get(mid) < index) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (list.get(start) > index) {
            return list.get(start) - index;
        } else if (list.get(end) < index) {
            return index - list.get(end);
        } else {
            return Math.min(index - list.get(start), list.get(end) - index);
        }
    }
    */
    public static void main(String[] args) {
        int[] color1 = {1, 1, 2, 1, 3, 2, 2, 3, 3};
        int[][] queries1 = {{1, 3}, {2, 2}, {6, 1}};

        int[] color2 = {1, 2};
        int[][] queries2 = {{0, 3}};

        System.out.println(shortestDistanceColor(color1, queries1));
        System.out.println(shortestDistanceColor(color2, queries2));
    }
}
