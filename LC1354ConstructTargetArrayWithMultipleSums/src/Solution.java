import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    /**
     * You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :
     *
     * let x be the sum of all elements currently in your array.
     * choose index i, such that 0 <= i < n and set the value of arr at index i to x.
     * You may repeat this procedure as many times as needed.
     * Return true if it is possible to construct the target array from arr, otherwise, return false.
     *
     *
     *
     * Example 1:
     *
     * Input: target = [9,3,5]
     * Output: true
     * Explanation: Start with arr = [1, 1, 1]
     * [1, 1, 1], sum = 3 choose index 1
     * [1, 3, 1], sum = 5 choose index 2
     * [1, 3, 5], sum = 9 choose index 0
     * [9, 3, 5] Done
     *
     * Example 2:
     *
     * Input: target = [1,1,1,2]
     * Output: false
     * Explanation: Impossible to create target array from [1,1,1,1].
     *
     * Example 3:
     *
     * Input: target = [8,5]
     * Output: true
     *
     *
     * Constraints:
     *
     * n == target.length
     * 1 <= n <= 5 * 10^4
     * 1 <= target[i] <= 10^9
     */

    
    public static boolean isPossible(int[] target) {
        if (target.length == 1) {
            return target[0] == 1;
        }

        int totalSum = 0;
        Queue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

        for (int i: target) {
            totalSum += i;
            heap.offer(i);
        }

        while (heap.peek() > 1) {
            int largest = heap.poll();

            int restSum = totalSum - largest;

            if (restSum == 1) {
                return true;
            }

            int updated = largest % restSum;

            if (updated == 0 || updated == largest) {
                return false;
            }

            totalSum = restSum + updated;
            heap.offer(updated);
        }

        return true;
    }

    public static void main(String[] args) {
        int[] test1 = {9, 3, 5};
        int[] test2 = {1, 1, 1, 2};
        int[] test3 = {8, 5};

        System.out.println(isPossible(test1));
        System.out.println(isPossible(test2));
        System.out.println(isPossible(test3));
    }
}
