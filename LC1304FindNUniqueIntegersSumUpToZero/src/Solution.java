import java.util.Arrays;

public class Solution {
    /**
     * Given an integer n, return any array containing n unique integers such that they add up to 0.
     *
     * Example 1:
     *
     * Input: n = 5
     * Output: [-7,-1,1,3,4]
     * Explanation: These arrays also are accepted [-5,-1,1,2,3] , [-3,-1,2,-2,4].
     * Example 2:
     *
     * Input: n = 3
     * Output: [-1,0,1]
     * Example 3:
     *
     * Input: n = 1
     * Output: [0]
     *
     *
     * Constraints:
     *
     * 1 <= n <= 1000
     */

    public static int[] sumZero(int n) {
        int[] result = new int[n];
        for (int i = 0; i < n / 2; i++) {
            result[i] = i - n;
        }

        for (int i = (n % 2 == 0) ? n / 2 : n / 2 + 1; i < n; i++) {
            result[i] = i + 1;
        }

        return result;
    }

    public static void main(String[] args) {
        printArray(sumZero(5));
        printArray(sumZero(3));
        printArray(sumZero(4));
        printArray(sumZero(1));
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
