import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * There are n buildings in a line. You are given an integer array heights of size n that represents
     * the heights of the buildings in the line.
     *
     * The ocean is to the right of the buildings. A building has an ocean view if the building can see
     * the ocean without obstructions. Formally, a building has an ocean view if all the buildings to
     * its right have a smaller height.
     *
     * Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.
     *
     * Example 1:
     *
     * Input: heights = [4,2,3,1]
     * Output: [0,2,3]
     * Explanation: Building 1 (0-indexed) does not have an ocean view because building 2 is taller.
     *
     *
     * Example 2:
     *
     * Input: heights = [4,3,2,1]
     * Output: [0,1,2,3]
     * Explanation: All the buildings have an ocean view.
     * Example 3:
     *
     * Input: heights = [1,3,2,4]
     * Output: [3]
     * Explanation: Only building 3 has an ocean view.
     * Example 4:
     *
     * Input: heights = [2,2,2,2]
     * Output: [3]
     * Explanation: Buildings cannot see the ocean if there are buildings of the same height to its right.
     *
     *
     * Constraints:
     *
     * 1 <= heights.length <= 105
     * 1 <= heights[i] <= 109
     */

    public static int[] findBuildings(int[] heights) {
        List<Integer> list = new ArrayList<>();
        int n = heights.length;
        int max = heights[n - 1];

        list.add(n - 1);

        for (int i = n - 2; i >= 0; i--) {
            if (heights[i] > max) {
                max = heights[i];
                list.add(i);
            }
        }

        Collections.reverse(list);
        return list.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {
        int[] test1 = {4,2,3,1};
        int[] test2 = {4,3,2,1};
        int[] test3 = {1,3,2,4};
        int[] test4 = {2,2,2,2};

        int[] result1 = findBuildings(test1);
        int[] result2 = findBuildings(test2);
        int[] result3 = findBuildings(test3);
        int[] result4 = findBuildings(test4);

        printArray(result1);
        printArray(result2);
        printArray(result3);
        printArray(result4);
    }

    private static void printArray(int[] result) {
        for (int i: result) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
