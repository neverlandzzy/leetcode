import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
    /**
     * The product sum of two equal-length arrays a and b is equal to the sum of a[i] * b[i] for all 0 <= i < a.length (0-indexed).
     *
     * For example, if a = [1,2,3,4] and b = [5,2,3,1], the product sum would be 1*5 + 2*2 + 3*3 + 4*1 = 22.
     * Given two arrays nums1 and nums2 of length n, return the minimum product sum if you are allowed to rearrange the order
     * of the elements in nums1.
     *
     *
     *
     * Example 1:
     *
     * Input: nums1 = [5,3,4,2], nums2 = [4,2,2,5]
     * Output: 40
     * Explanation: We can rearrange nums1 to become [3,5,4,2]. The product sum of [3,5,4,2]
     * and [4,2,2,5] is 3*4 + 5*2 + 4*2 + 2*5 = 40.
     *
     * Example 2:
     *
     * Input: nums1 = [2,1,4,5,7], nums2 = [3,2,4,8,6]
     * Output: 65
     * Explanation: We can rearrange nums1 to become [5,7,4,1,2]. The product sum of [5,7,4,1,2]
     * and [3,2,4,8,6] is 5*3 + 7*2 + 4*4 + 1*8 + 2*6 = 65.
     *
     *
     * Constraints:
     *
     * n == nums1.length == nums2.length
     * 1 <= n <= 10^5
     * 1 <= nums1[i], nums2[i] <= 100
     */

    // 假设nums1按升序排列，nums2 按降序排列, ai < aj in nums1, bi < bj in nums2
    // (ai * bi + aj * bj) - (ai * bj + aj * bi) = (bi - bj) (aj - ai)
    // 由于nums1, nums2一个升序排列，一个降序排列，所以(bi - bj) (aj - ai) >= 0
    // 也就是说明一个升序排列，一个降序排列后 a1*b1 + a2*b2 + ... ai*bi + ... + an*bn 最小
    // 但题目限制不能打乱nums2的顺序，因此可以用PriorityQueue(Solution 1) 或者bucket sorting(Solution 2)处理

    // Solution 1: Priority Queue: Time: O(n⋅logn), Space: O(n)
//    public static int minProductSum(int[] nums1, int[] nums2) {
//        Arrays.sort(nums1);
//        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
//        int result = 0;
//
//        for (int i: nums2) {
//            heap.offer(i);
//        }
//
//        for (int i = 0; i < nums1.length; i++) {
//            result += nums1[i] * heap.poll();
//        }
//
//        return result;
//    }

    // Solution 2: Counting sort: Time: O(n + k), Space: O(k) k: the range of values in nums1 or nums2
    //             in this problem k <= 100 since 1 <= nums1[i], nums2[i] <= 100
    public static int minProductSum(int[] nums1, int[] nums2) {
        int[] counter1 = new int[101]; // 1 <= nums1[i], nums2[i] <= 100
        int[] counter2 = new int[101];

        for (int i: nums1) {
            counter1[i]++;
        }

        for (int i: nums2) {
            counter2[i]++;
        }

        int index1 = 0;
        int index2 = counter2.length - 1;
        int result = 0;

        while (index1 < counter1.length && index2 >= 0) {
            while (index1 < counter1.length && counter1[index1] == 0) {
                index1++;
            }

            while (index2 >= 0 && counter2[index2] == 0) {
                index2--;
            }

            if (index1 == counter1.length || index2 == 0) {
                break;
            }

            int minCount = Math.min(counter1[index1], counter2[index2]);
            result += minCount * index1 * index2;
            counter1[index1] -= minCount;
            counter2[index2] -= minCount;
        }

        return result;
    }


    public static void main(String[] args) {
        int[] test11 = {5, 3, 4, 2};
        int[] test12 = {4, 2, 2, 5};
        int[] test21 = {2, 1, 4, 5, 7};
        int[] test22 = {3, 2, 4, 8, 6};

        System.out.println(minProductSum(test11, test12));
        System.out.println(minProductSum(test21, test22));
    }
}
