public class Solution {

    /**
     * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
     * Find the two elements that appear only once. You can return the answer in any order.
     *
     * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
     *
     *
     *
     * Example 1:
     *
     * Input: nums = [1,2,1,3,2,5]
     * Output: [3,5]
     * Explanation:  [5, 3] is also a valid answer.
     *
     * Example 2:
     *
     * Input: nums = [-1,0]
     * Output: [-1,0]
     *
     * Example 3:
     *
     * Input: nums = [0,1]
     * Output: [1,0]
     *
     *
     * Constraints:
     *
     * 2 <= nums.length <= 3 * 104
     * -231 <= nums[i] <= 231 - 1
     * Each integer in nums will appear twice, only two integers will appear once.
     */

    // 1. 将所有的元素XOR得到一个bitmask，这个bitmask中只保留了2个只出现一次的元素，设为x和y
    // 2. 在bitmask中取最右一位为1的bit，这个bit代表了x和y的不同，也就是这位只能是x和y其中一个数的一个bit，假设为x
    // 3. 再将nums中所有这个bit为1的数进行XOR，这些数中包含x但不包含y，得到一个新的bitmask，则这个bitmask即为x
    // 4. 再用x与第一步得到的bitmask（只保留了x和y）进行XOR，这样得到y
    public static int[] singleNumber(int[] nums) {
        int bitmask = 0;

        for (int i: nums) {
            bitmask ^= i;
        }

        int diff = bitmask & (-bitmask);

        int x = 0;

        for (int i: nums) {
            if ((i & diff) != 0) {
                x ^= i;
            }
        }

        int y = x ^ bitmask;

        return new int[] {x, y};
    }

    public static void main(String[] args) {
        int[] test1 = {1,2,1,3,2,5};
        int[] test2 = {-1, 0};
        int[] test3 = {0, -1};

        int[] result1 = singleNumber(test1);
        int[] result2 = singleNumber(test2);
        int[] result3 = singleNumber(test3);
    }

    private static void printResult(int[] nums) {

        for (int i: nums) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
