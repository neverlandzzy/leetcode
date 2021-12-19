public class Solution {

    /**
     * (This problem is an interactive problem.)
     *
     * You may recall that an array arr is a mountain array if and only if:
     *
     * arr.length >= 3
     * There exists some i with 0 < i < arr.length - 1 such that:
     * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
     * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
     * Given a mountain array mountainArr, return the minimum index such that mountainArr.get(index) == target. If such an index does not exist, return -1.
     *
     * You cannot access the mountain array directly. You may only access the array using a MountainArray interface:
     *
     * MountainArray.get(k) returns the element of the array at index k (0-indexed).
     * MountainArray.length() returns the length of the array.
     * Submissions making more than 100 calls to MountainArray.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.
     *
     *
     *
     * Example 1:
     *
     * Input: array = [1,2,3,4,5,3,1], target = 3
     * Output: 2
     * Explanation: 3 exists in the array, at index=2 and index=5. Return the minimum index, which is 2.
     *
     * Example 2:
     *
     * Input: array = [0,1,2,4,2,1], target = 3
     * Output: -1
     * Explanation: 3 does not exist in the array, so we return -1.
     *
     *
     * Constraints:
     *
     * 3 <= mountain_arr.length() <= 10^4
     * 0 <= target <= 10^9
     * 0 <= mountain_arr.get(index) <= 10^9
     */

    // Related to LC852
    // https://leetcode.com/problems/find-in-mountain-array/discuss/317607/JavaC%2B%2BPython-Triple-Binary-Search
    // 3 steps：
    //     1. Binary search to find peak in the mountain array (LC852)
    //     2. Binary search to find target in the increasing array (left)
    //     3. Binary search to find target in the decreasing array (right)
    public int findInMountainArray(int target, MountainArray mountainArr) {
        // 1. find the peak

        int start = 0;
        int end = mountainArr.length() - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        int peak = start;

        // 2. binary search in the left part

        start  = 0;
        end = peak;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (mountainArr.get(mid) == target) {
                return mid;
            }

            if (mountainArr.get(mid) < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (mountainArr.get(start) == target) {
            return start;
        }

        // 3. binary search in the right part
        start = peak;
        end = mountainArr.length() - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;

            if (mountainArr.get(mid) == target) {
                return mid;
            }

            if (mountainArr.get(mid) > target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return mountainArr.get(start) == target ? start : -1;
    }

}
