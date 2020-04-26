import java.util.*;

public class Solution {
    /**
     * Given two arrays of integers nums and index. Your task is to create target array under the following rules:
     *
     * Initially target array is empty.
     * From left to right read nums[i] and index[i], insert at index index[i] the value nums[i] in target array.
     * Repeat the previous step until there are no elements to read in nums and index.
     * Return the target array.
     *
     * It is guaranteed that the insertion operations will be valid.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [0,1,2,3,4], index = [0,1,2,2,1]
     * Output: [0,4,1,3,2]
     * Explanation:
     * nums       index     target
     * 0            0        [0]
     * 1            1        [0,1]
     * 2            2        [0,1,2]
     * 3            2        [0,1,3,2]
     * 4            1        [0,4,1,3,2]
     * Example 2:
     *
     * Input: nums = [1,2,3,4,0], index = [0,1,2,3,0]
     * Output: [0,1,2,3,4]
     * Explanation:
     * nums       index     target
     * 1            0        [1]
     * 2            1        [1,2]
     * 3            2        [1,2,3]
     * 4            3        [1,2,3,4]
     * 0            0        [0,1,2,3,4]
     * Example 3:
     *
     * Input: nums = [1], index = [0]
     * Output: [1]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length, index.length <= 100
     * nums.length == index.length
     * 0 <= nums[i] <= 100
     * 0 <= index[i] <= i
     */

    // My solution: O(n^2)
    public static int[] createTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            if (index[i] == i) {
                result[i] = nums[i];
            } else {
                for (int j = i; j > index[i]; j--) {
                    result[j] = result[j - 1];
                }
                result[index[i]] = nums[i];
            }
        }
        return result;
    }

    // MergeSort O(n*logn)
    // https://leetcode.com/problems/create-target-array-in-the-given-order/discuss/549583/O(nlogn)-based-on-%22smaller-elements-after-self%22.

    public static void main(String[] args) {
        int[] nums1 = new int[] {0, 1, 2, 3, 4};
        int[] index1 = new int[] {0, 1, 2, 2, 1};
        int[] nums2 = new int[] {1, 2, 3, 4, 0};
        int[] index2 = new int[] {0, 1, 2, 3, 0};
        int[] nums3 = new int[] {1};
        int[] index3 = new int[] {0};
        int[] nums4 = new int[] {4, 2, 4, 3, 2};
        int[] index4 = new int[] {0, 0, 1, 3, 1};

        int[] res1 = createTargetArray(nums1, index1);
        int[] res2 = createTargetArray(nums2, index2);
        int[] res3 = createTargetArray(nums3, index3);
        int[] res4 = createTargetArray(nums4, index4);

        printArray(res1);
        printArray(res2);
        printArray(res3);
        printArray(res4); // [2,2,4,4,3]
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
