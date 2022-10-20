import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * An integer array original is transformed into a doubled array changed by appending twice the value of every element in original, and then randomly shuffling
     * the resulting array.
     *
     * Given an array changed, return original if changed is a doubled array. If changed is not a doubled array, return an empty array. The elements in original may
     * be returned in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: changed = [1,3,4,2,6,8]
     * Output: [1,3,4]
     * Explanation: One possible original array could be [1,3,4]:
     * - Twice the value of 1 is 1 * 2 = 2.
     * - Twice the value of 3 is 3 * 2 = 6.
     * - Twice the value of 4 is 4 * 2 = 8.
     * Other original arrays could be [4,3,1] or [3,1,4].
     *
     * Example 2:
     *
     * Input: changed = [6,3,0,1]
     * Output: []
     * Explanation: changed is not a doubled array.
     *
     * Example 3:
     *
     * Input: changed = [1]
     * Output: []
     * Explanation: changed is not a doubled array.
     *
     *
     * Constraints:
     *
     * 1 <= changed.length <= 10^5
     * 0 <= changed[i] <= 10^5
     */

    // Solution 1: O(n * logn)
    public static int[] findOriginalArray(int[] changed) {
        int n = changed.length;
        if (n % 2 != 0) {
            return new int[]{};
        }

        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(changed);
        int[] result = new int[n / 2];

        for (int num: changed) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int index = 0;

        for (int num: changed) {
            if (map.get(num) > 0) {
                int doubled = num * 2;
                map.put(num, map.get(num) - 1);
                if (!map.containsKey(doubled) || map.get(doubled) == 0) {
                    return new int[]{};
                }

                result[index++] = num;
                map.put(doubled, map.get(doubled) - 1);
            }
        }

        return result;
    }

    // Solution 2: Counting sort O(n + k)

    public static void main(String[] args) {
        int[] test1 = {1, 3, 4, 2, 6, 8};
        int[] test2 = {6, 3, 0, 1};
        int[] test3 = {1};

        int[] result1 = findOriginalArray(test1);
        int[] result2 = findOriginalArray(test2);
        int[] result3 = findOriginalArray(test3);

        printArray(result1);
        printArray(result2);
        printArray(result3);
    }

    private static void printArray(int[] nums) {
        for (int i: nums) {
            System.out.print(i + ", ");
        }

        System.out.println();
    }
}
