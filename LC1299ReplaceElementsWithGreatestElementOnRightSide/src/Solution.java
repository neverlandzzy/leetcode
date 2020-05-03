import java.util.Arrays;

public class Solution {

    /**
     * Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.
     *
     * After doing so, return the array.
     *
     *
     *
     * Example 1:
     *
     * Input: arr = [17,18,5,4,6,1]
     * Output: [18,6,6,6,1,-1]
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 10^4
     * 1 <= arr[i] <= 10^5
     */

    public static int[] replaceElements(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int rightMax = -1;
        int n = arr.length;

        for (int i = n - 1; i >= 0; i--) {
            int cur = arr[i];
            arr[i] = rightMax;
            rightMax = Math.max(cur, rightMax);
        }

        return arr;
    }

    public static void main(String[] args) {
        int[] test = {17,18,5,4,6,1};
        int[] result1 = replaceElements(test);

        printArray(result1);
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
