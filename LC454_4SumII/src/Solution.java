import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples
     * (i, j, k, l) such that:
     *
     * 0 <= i, j, k, l < n
     * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
     *
     *
     * Example 1:
     *
     * Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
     * Output: 2
     * Explanation:
     * The two tuples are:
     * 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
     * 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
     * Example 2:
     *
     * Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
     * Output: 1
     *
     *
     * Constraints:
     *
     * n == nums1.length
     * n == nums2.length
     * n == nums3.length
     * n == nums4.length
     * 1 <= n <= 200
     * -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
     */

    // Solution 1: HashMap, Time: O(n^2)
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        int counter = 0;

        for (int n1: nums1) {
            for (int n2: nums2) {
                int sum = n1 + n2;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int n3: nums3) {
            for (int n4: nums4) {
                int sum = n3 + n4;

                if (map.containsKey(-sum)) {
                    counter += map.get(-sum);
                }
            }
        }
        return counter;
    }


    public static void main(String[] args) {
        int[] test11 = {1, 2}, test12 = {-2, -1}, test13 = {-1, 2}, test14 = {0, 2};
        System.out.println(fourSumCount(test11, test12, test13, test14));

        int[] test21 = {0}, test22 = {0}, test23 = {0}, test24 = {0};
        System.out.println(fourSumCount(test21, test22, test23, test24));
    }
}
