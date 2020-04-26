import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].
     *
     * Return the answer in an array.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [8,1,2,2,3]
     * Output: [4,0,1,1,3]
     * Explanation:
     * For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3).
     * For nums[1]=1 does not exist any smaller number than it.
     * For nums[2]=2 there exist one smaller number than it (1).
     * For nums[3]=2 there exist one smaller number than it (1).
     * For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
     * Example 2:
     *
     * Input: nums = [6,5,4,8]
     * Output: [2,1,0,3]
     * Example 3:
     *
     * Input: nums = [7,7,7,7]
     * Output: [0,0,0,0]
     *
     *
     * Constraints:
     *
     * 2 <= nums.length <= 500
     * 0 <= nums[i] <= 100
     */

    // My Solution: O(n*logn)
//    public static int[] smallerNumbersThanCurrent(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return nums;
//        }
//
//        int n = nums.length;
//        int[] result = Arrays.copyOf(nums, n);
//        Arrays.sort(result);
//        Map<Integer, Integer> map = new HashMap<>();
//
//        for (int i = 0; i < n; i++) {
//            if (!map.containsKey(result[i])) {
//                map.put(result[i], i);
//            }
//        }
//
//        for (int i = 0; i < n; i++) {
//            result[i] = map.get(nums[i]);
//        }
//
//        return result;
//    }

    // More efficient solution (O(n)): bucket count - use Constraints 0 <= nums[i] <= 100
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        // per constraints 2 <= nums.length <= 500
        int[] bucket = new int[101];
        int n = nums.length;

        for (int num: nums) {
            bucket[num]++;
        }

        int[] result = new int[n];

        int count = 0;
        for (int i = 0; i < 101; i++) {
            if (bucket[i] != 0) {
                int tmp = bucket[i];
                bucket[i] = count;
                count += tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            result[i] = bucket[nums[i]];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[] {8, 1, 2, 2, 3};
        int[] nums2 = new int[] {6, 5, 4, 8};
        int[] nums3 = new int[] {7, 7, 7, 7};

        int[] res1 = smallerNumbersThanCurrent(nums1);
        int[] res2 = smallerNumbersThanCurrent(nums2);
        int[] res3 = smallerNumbersThanCurrent(nums3);

        printArray(res1);
        printArray(res2);
        printArray(res3);
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }

}
