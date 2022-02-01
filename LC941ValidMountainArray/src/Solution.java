public class Solution {

    /**
     * Given an array of integers arr, return true if and only if it is a valid mountain array.
     *
     * Recall that arr is a mountain array if and only if:
     *
     * arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [2,1]
     * Output: false
     *
     * Example 2:
     *
     * Input: arr = [3,5,5]
     * Output: false
     *
     * Example 3:
     *
     * Input: arr = [0,3,2,1]
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^4
     * 0 <= arr[i] <= 10^4
     */

    public static boolean validMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            return false;
        }

        int i = 1;
        int n = arr.length;

        while (i < n) {
            if (arr[i] > arr[i - 1]) {
                i++;
            } else {
                break;
            }
        }

        if (i == 1 || i == n) {
            return false;
        }

        while (i < n) {
            if (arr[i] < arr[i - 1]) {
                i++;
            } else {
                break;
            }
        }

        return i == n;
    }

    public static void main(String[] args) {
        int[] test1 = {2, 1};
        int[] test2 = {3, 5, 5};
        int[] test3 = {0, 3, 2, 1};
        int[] test4 = {1, 3, 2};

        System.out.println(validMountainArray(test1));
        System.out.println(validMountainArray(test2));
        System.out.println(validMountainArray(test3));
        System.out.println(validMountainArray(test4));
    }
}
