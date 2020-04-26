import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * We are given a list nums of integers representing a list compressed with run-length encoding.
     *
     * Consider each adjacent pair of elements [freq, val] = [nums[2*i], nums[2*i+1]] (with i >= 0).  For each such pair,
     * there are freq elements with value val concatenated in a sublist.
     * Concatenate all the sublists from left to right to generate the decompressed list.
     *
     * Return the decompressed list.
     *
     * Example 1:
     *
     * Input: nums = [1,2,3,4]
     * Output: [2,4,4,4]
     * Explanation: The first pair [1,2] means we have freq = 1 and val = 2 so we generate the array [2].
     * The second pair [3,4] means we have freq = 3 and val = 4 so we generate [4,4,4].
     * At the end the concatenation [2] + [4,4,4] is [2,4,4,4].
     * Example 2:
     *
     * Input: nums = [1,1,2,3]
     * Output: [1,3,3]
     *
     *
     * Constraints:
     *
     * 2 <= nums.length <= 100
     * nums.length % 2 == 0
     * 1 <= nums[i] <= 100
     */

    // My solution
//    public static int[] decompressRLElist(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return nums;
//        }
//
//        List<Integer> list = new ArrayList<>();
//        for (int i = 0; i < nums.length; i += 2) {
//            int freq = nums[i];
//            int val = nums[i +1 ];
//
//            for (int j = 0; j < freq; j++) {
//                list.add(val);
//            }
//        }
//
//        return list.stream().mapToInt(i -> i).toArray();
//    }

    // More efficient solution. No extra space.
    // https://leetcode.com/problems/decompress-run-length-encoded-list/discuss/597276/Java-simple-95.75-Time-100-Space
    public static int[] decompressRLElist(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        int n = 0;
        for (int i = 0; i < nums.length; i += 2) {
            n += nums[i];
        }

        int[] result = new int[n];
        int pos =  0;

        for (int i = 0; i < nums.length; i += 2) {
            int freq = nums[i];
            int val = nums[i + 1];
            for (int j = pos; j < pos + freq; j++) {
                result[j] = val;
            }
            pos += freq;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {1, 2, 3, 4};
        int[] nums2 = new int[] {1, 1, 2, 3};

        int[] res1 = decompressRLElist(nums1);
        int[] res2 = decompressRLElist(nums2);

        printArray(res1);
        printArray(res2);
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
