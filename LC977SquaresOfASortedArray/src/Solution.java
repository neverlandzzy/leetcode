import java.util.Arrays;

public class Solution {

    /**
     * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in
     * non-decreasing order.
     *
     *
     * Example 1:
     *
     * Input: nums = [-4,-1,0,3,10]
     * Output: [0,1,9,16,100]
     * Explanation: After squaring, the array becomes [16,1,0,9,100].
     * After sorting, it becomes [0,1,9,16,100].
     * Example 2:
     *
     * Input: nums = [-7,-3,2,3,11]
     * Output: [4,9,9,49,121]
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 104
     * -104 <= nums[i] <= 104
     * nums is sorted in non-decreasing order.
     *
     *
     * Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution
     * using a different approach?
     */

    // O(n)
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int i = 0;
        int j = n - 1;
        int index = n - 1;

        while (i <= j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                result[index] = nums[i] * nums[i];
                i++;
            } else {
                result[index] = nums[j] * nums[j];
                j--;
            }

            index--;
        }

        return result;
    }

    // Trivial solution O(n*logn)
//    public static int[] sortedSquares(int[] nums) {
//
//        int n = nums.length;
//
//        int[] result = new int[n];
//
//        for (int i = 0; i < n; i++) {
//            result[i] = nums[i] * nums[i];
//        }
//
//        Arrays.sort(result);
//
//        return result;
//    }

    public static void main(String[] args) {
        int[] test1 = {-4,-1,0,3,10};
        int[] test2 = {-7,-3,2,3,11};

        int[] result1 = sortedSquares(test1);
        int[] result2 = sortedSquares(test2);

        printArray(result1);
        printArray(result2);

    }


    private static void printArray(int[]nums) {
		for (int i: nums) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
