public class Solution {
    /**
     * A permutation perm of n integers of all the integers in the range [1, n] can be represented as a string s of length n - 1 where:
     *
     * s[i] == 'I' if perm[i] < perm[i + 1], and
     * s[i] == 'D' if perm[i] > perm[i + 1].
     * Given a string s, reconstruct the lexicographically smallest permutation perm and return it.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "I"
     * Output: [1,2]
     * Explanation: [1,2] is the only legal permutation that can represented by s, where the number 1 and 2 construct an increasing
     * relationship.
     *
     * Example 2:
     *
     * Input: s = "DI"
     * Output: [2,1,3]
     * Explanation: Both [2,1,3] and [3,1,2] can be represented as "DI", but since we want to find the smallest lexicographical
     * permutation, you should return [2,1,3]
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s[i] is either 'I' or 'D'.
     */

    // we can summarize that, to generate the required arrangement, we can start off with the min number that
    // can be formed for the given n. Then, to satisfy the given pattern s, we need to reverse only those subsections of the min
    // array which have a D in the pattern at their corresponding positions.
    public static int[] findPermutation(String s) {
        int n = s.length();
        int[] result = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            result[i] = i + 1;
        }

        int index = 0;
        int i = 0;
        int len = 0;

        while (i < n) {
            if (s.charAt(i) == 'I') {
                if (len > 0) {
                    reverse(result, index + 1, i - 1);
                }
                len = 0;
                index = i;
            } else {
                len++;
            }
        }

        return result;
    }

    private static void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        printArray(findPermutation("I"));
        printArray(findPermutation("DI"));
    }

    private static void printArray(int[] array) {
        for (int a: array) {
            System.out.print(a + ", ");
        }

        System.out.println();
    }
}
