import java.util.Arrays;

public class Solution {
    /**
     * Given an array A of non-negative integers, return an array consisting of all the even elements of A, followed by all the odd elements of A.
     *
     * You may return any answer array that satisfies this condition.
     *
     * Example 1:
     *
     * Input: [3,1,2,4]
     * Output: [2,4,3,1]
     * The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
     *
     *
     * Note:
     *
     * 1 <= A.length <= 5000
     * 0 <= A[i] <= 5000
     */

    public static int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) {
            return A;
        }

        int i = 0, j = A.length - 1;
        while (i < j) {
            while (i < j && A[i] % 2 == 0) {
                i++;
            }

            while (i < j && A[j] % 2 != 0) {
                j--;
            }

            swap(A, i, j);
            i++;
            j--;
        }

        return A;
    }

    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        int[] test1 = {3, 1, 2, 4};
        printArray(sortArrayByParity(test1));
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
