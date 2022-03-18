public class Solution {

    /**
     * Given an array of positive integers arr, calculate the sum of all possible odd-length subarrays.
     *
     * A subarray is a contiguous subsequence of the array.
     *
     * Return the sum of all odd-length subarrays of arr.
     *
     * Example 1:
     *
     * Input: arr = [1,4,2,5,3]
     * Output: 58
     * Explanation: The odd-length subarrays of arr and their sums are:
     * [1] = 1
     * [4] = 4
     * [2] = 2
     * [5] = 5
     * [3] = 3
     * [1,4,2] = 7
     * [4,2,5] = 11
     * [2,5,3] = 10
     * [1,4,2,5,3] = 15
     * If we add all these together we get 1 + 4 + 2 + 5 + 3 + 7 + 11 + 10 + 15 = 58
     *
     * Example 2:
     *
     * Input: arr = [1,2]
     * Output: 3
     * Explanation: There are only 2 subarrays of odd length, [1] and [2]. Their sum is 3.
     *
     * Example 3:
     *
     * Input: arr = [10,11,12]
     * Output: 66
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 100
     * 1 <= arr[i] <= 1000
     */

    // https://leetcode.com/problems/sum-of-all-odd-length-subarrays/discuss/867779/Java-O(n)-time-with-Video-explanation
    // https://www.youtube.com/watch?v=J5IIH35EBVE
    // e.g. arr = [1,4,2,5,3]
    //     num:   1,  4,  2,  5, 3
    //     times: 3,  4,  5,  4, 3
    //     total: 3, 16, 10, 20, 9 = 58
    // 对于位置在i的元素来说：从它start和在它end的subarray分别有 n - i和 i + 1个，因此其出现在subarray中的总次数为
    // total = (n - i) * (i - 1)
    // 其中，subarray长度为奇数的次数为 total / 2 (or total / 2 + 1 if total is odd)
    public static int sumOddLengthSubarrays(int[] arr) {
        int result = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int startHere = n - i;
            int endHere = i + 1;
            int totalSubArrays = startHere * endHere;
            int oddSubArrays = (totalSubArrays % 2 == 0) ? totalSubArrays / 2 : totalSubArrays / 2 + 1;
            result += arr[i] * oddSubArrays;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] test1 = {1,4,2,5,3};
        int[] test2 = {1,2};
        int[] test3 = {10,11,12};

        System.out.println(sumOddLengthSubarrays(test1));
        System.out.println(sumOddLengthSubarrays(test2));
        System.out.println(sumOddLengthSubarrays(test3));
    }
}
