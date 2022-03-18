public class Solution {
    /**
     * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also an integer k,
     * return the kth missing number starting from the leftmost number of the array.
     *
     * Example 1:
     *
     * Input: nums = [4,7,9,10], k = 1
     * Output: 5
     * Explanation: The first missing number is 5.
     *
     * Example 2:
     *
     * Input: nums = [4,7,9,10], k = 3
     * Output: 8
     * Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.
     *
     * Example 3:
     *
     * Input: nums = [1,2,4], k = 3
     * Output: 6
     * Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 5 * 10^4
     * 1 <= nums[i] <= 10^7
     * nums is sorted in ascending order, and all the elements are unique.
     * 1 <= k <= 10^8
     *
     *
     * Follow up: Can you find a logarithmic time complexity (i.e., O(log(n))) solution?
     */

    // Solution 1: one pass - O(n)
    /*
    public static int missingElement(int[] nums, int k) {
        int n = nums.length;
        int diff = 0;

        for (int i = 1; i < n; i++) {
            diff = nums[i] - nums[i - 1] - 1;

            if (diff >= k) {
                return nums[i - 1] + k;
            }

            k -= diff;
        }

        return nums[n - 1] + k;
    }
    */

    // Solution 2: Binary Search - O(logn)
    // Find the leftmost element such that the number of missing numbers until this element is less or equal to k.
    public static int missingElement(int[] nums, int k) {
        int n = nums.length;

        int missingAtLastElement = missingElementAtIndex(nums, n - 1);
        if (k > missingAtLastElement) {
            return nums[n - 1] + k - missingAtLastElement;
        }

        int start = 0;
        int end = n - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (missingElementAtIndex(nums, mid) < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return nums[start - 1] + k - missingElementAtIndex(nums, start - 1);
    }

    // Return how many numbers are missing until nums[index]
    private static int missingElementAtIndex(int[] nums, int index) {
        return nums[index] - nums[0] - index;
    }

    public static void main(String[] args) {
        int[] test1 = {4, 7, 9, 10};
        int[] test2 = {4, 7, 9, 10};
        int[] test3 = {1, 2, 4};

        System.out.println(missingElement(test1, 1));
        System.out.println(missingElement(test2, 3));
        System.out.println(missingElement(test3, 3));
    }
}
