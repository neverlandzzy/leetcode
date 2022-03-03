import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * A sequence of numbers is called an arithmetic progression if the difference between any two consecutive elements is the same.
     *
     * Given an array of numbers arr, return true if the array can be rearranged to form an arithmetic progression. Otherwise, return false.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [3,5,1]
     * Output: true
     * Explanation: We can reorder the elements as [1,3,5] or [5,3,1] with differences 2 and -2 respectively, between each consecutive elements.
     * Example 2:
     *
     * Input: arr = [1,2,4]
     * Output: false
     * Explanation: There is no way to reorder the elements to obtain an arithmetic progression.
     *
     *
     * Constraints:
     *
     * 2 <= arr.length <= 1000
     * -10^6 <= arr[i] <= 10^6
     */

    // Solution 1: Time: O(n), Space: O(n)
    public static boolean canMakeArithmeticProgression(int[] arr) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int n = arr.length;
        Set<Integer> set = new HashSet<>();

        for (int a: arr) {
            min = Math.min(min, a);
            max = Math.max(max, a);
            set.add(a);
        }

        int range = max - min;

        if (range % (n - 1) != 0) {
            return false;
        }

        int step = range / (n - 1);

        for (int i = 0; i < n; i++) {
            if (!set.contains(min)) {
                return false;
            }

            min += step;
        }

        return true;
    }

    // Solution 2: Time: O(n * logn), Space: O(1)
    /*
    public static boolean canMakeArithmeticProgression(int[] arr) {
        if (arr == null || arr.length < 2) {
            return false;
        }

        if (arr.length == 2) {
            return true;
        }

        Arrays.sort(arr);
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != arr[i - 1] - arr[i - 2]) {
                return false;
            }
        }

        return true;
    }
    */
    public static void main(String[] args) {
        int[] test1 = {3, 5, 1};
        int[] test2 = {1, 2, 4};

        System.out.println(canMakeArithmeticProgression(test1));
        System.out.println(canMakeArithmeticProgression(test2));
    }
}
