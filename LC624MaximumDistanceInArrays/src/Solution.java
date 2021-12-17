import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * You are given m arrays, where each array is sorted in ascending order.
     *
     * You can pick up two integers from two different arrays (each array picks one) and calculate the distance.
     * We define the distance between two integers a and b to be their absolute difference |a - b|.
     *
     * Return the maximum distance.
     *
     *
     *
     * Example 1:
     *
     * Input: arrays = [[1,2,3],[4,5],[1,2,3]]
     * Output: 4
     * Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
     * Example 2:
     *
     * Input: arrays = [[1],[1]]
     * Output: 0
     * Example 3:
     *
     * Input: arrays = [[1],[2]]
     * Output: 1
     * Example 4:
     *
     * Input: arrays = [[1,4],[0,5]]
     * Output: 4
     *
     *
     * Constraints:
     *
     * m == arrays.length
     * 2 <= m <= 10^5
     * 1 <= arrays[i].length <= 500
     * -10^4 <= arrays[i][j] <= 10^4
     * arrays[i] is sorted in ascending order.
     * There will be at most 105 integers in all the arrays.
     */

    public static int maxDistance(List<List<Integer>> arrays) {
        int result = Integer.MIN_VALUE;

        int max = arrays.get(0).get(arrays.get(0).size() - 1);
        int min = arrays.get(0).get(0);

        for (int i = 1; i < arrays.size(); i++) {
            int n = arrays.get(i).size();
            result = Math.max(result, Math.max(Math.abs(max - arrays.get(i).get(0)),
                    Math.abs(arrays.get(i).get(n - 1) - min)));

            max = Math.max(max, arrays.get(i).get(n - 1));
            min = Math.min(min, arrays.get(i).get(0));
        }

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> test1 = new ArrayList<>(Arrays.asList(Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5), Arrays.asList(1, 2, 3)));

        List<List<Integer>> test2 = new ArrayList<>(Arrays.asList(Arrays.asList(1),
                Arrays.asList(1)));

        List<List<Integer>> test3 = new ArrayList<>(Arrays.asList(Arrays.asList(1),
                Arrays.asList(2)));

        List<List<Integer>> test4 = new ArrayList<>(Arrays.asList(Arrays.asList(1, 4),
                Arrays.asList(0, 5)));

        System.out.println(maxDistance(test1));
        System.out.println(maxDistance(test2));
        System.out.println(maxDistance(test3));
        System.out.println(maxDistance(test4));
    }
}
