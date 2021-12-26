import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
     *
     * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
     *
     * a, b are from arr
     * a < b
     * b - a equals to the minimum absolute difference of any two elements in arr
     *
     *
     * Example 1:
     *
     * Input: arr = [4,2,1,3]
     * Output: [[1,2],[2,3],[3,4]]
     * Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
     *
     * Example 2:
     *
     * Input: arr = [1,3,6,10,15]
     * Output: [[1,3]]
     *
     * Example 3:
     *
     * Input: arr = [3,8,-10,23,19,-4,-14,27]
     * Output: [[-14,-10],[19,23],[23,27]]
     *
     *
     * Constraints:
     *
     * 2 <= arr.length <= 10^5
     * -10^6 <= arr[i] <= 10^6
     */

    // Solution 1: Sort - Time: O(nlogn) - Straightforward
//    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
//        List<List<Integer>> result = new ArrayList<>();
//
//        if (arr == null || arr.length == 0) {
//            return result;
//        }
//
//        int n = arr.length;
//
//        Arrays.sort(arr);
//        int miniDiff = Integer.MAX_VALUE;
//
//        for (int i = 1; i < n; i++) {
//            int diff = arr[i] - arr[i - 1];
//
//            miniDiff = Math.min(miniDiff, diff);
//        }
//
//        for (int i = 1; i < n; i++) {
//            int diff = arr[i] - arr[i - 1];
//
//            if (diff == miniDiff) {
//                result.add(Arrays.asList(arr[i - 1], arr[i]));
//            }
//        }
//
//        return result;
//    }

    // Solution 2: Counting Sort - Time: O(m+n) (m: the range of values in arr)
    public static List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();

        if (arr == null || arr.length == 0) {
            return result;
        }

        // Step 1: Find min and max in arr: O(n)
        int min = arr[0];
        int max = arr[0];

        for (int a: arr) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }

        // Step 2: Initialize the auxiliary array 'counting' of size max-min+1 and
        //         set shift = -min --> the smallest element maps to index 0 in 'counting'
        //         and the largest element maps to the last index in 'counting'  - O(n)

        int[] counting = new int[max - min + 1];
        int shift = -min;

        for (int a: arr) {
            counting[a + shift] = 1;
        }

        // Step 3: traverse 'counting' array and compare the difference between
        //         adjacent elements in it - O(m)

        int minDiff = max - min;
        int prev = 0;

        for (int i = 1; i < counting.length; i++) {
            if (counting[i] == 0) {
                continue;
            }

            if (i - prev == minDiff) {
                result.add(Arrays.asList(prev - shift, i - shift));
            } else if (i - prev < minDiff) {
                minDiff = i - prev;
                result.clear();
                result.add(Arrays.asList(prev - shift, i - shift));
            }

            prev = i;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test1 = {4, 2, 1, 3};
        int[] test2 = {1, 3, 6, 10, 15};
        int[] test3 = {3, 8, -10, 23, 19, -4, -14, 27};

        System.out.println(minimumAbsDifference(test1));
        System.out.println(minimumAbsDifference(test2));
        System.out.println(minimumAbsDifference(test3));
    }
}
