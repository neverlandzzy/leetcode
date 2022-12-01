import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.
     *
     * Example 1:
     *
     * Input: arr = [1,2,2,1,1,3]
     * Output: true
     * Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.
     *
     * Example 2:
     *
     * Input: arr = [1,2]
     * Output: false
     *
     * Example 3:
     *
     * Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 1000
     * -1000 <= arr[i] <= 1000
     */

    public static boolean uniqueOccurrences(int[] arr) {
        int[] count = new int[2001];
        Set<Integer> set = new HashSet<>();

        for (int a: arr) {
            count[a + 1000]++;
        }

        for (int c: count) {
            if (c != 0) {
                if (set.contains(c)) {
                    return false;
                }

                set.add(c);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] test1 = {1, 2 ,2, 1, 1, 3};
        int[] test2 = {1, 2};
        int[] test3 = {-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};

        System.out.println(uniqueOccurrences(test1));
        System.out.println(uniqueOccurrences(test2));
        System.out.println(uniqueOccurrences(test3));
    }
}
