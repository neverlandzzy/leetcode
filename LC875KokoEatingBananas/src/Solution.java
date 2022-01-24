public class Solution {
    /**
     * Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.
     *
     * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has
     * less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
     *
     * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
     *
     * Return the minimum integer k such that she can eat all the bananas within h hours.
     *
     *
     *
     * Example 1:
     *
     * Input: piles = [3,6,7,11], h = 8
     * Output: 4
     * Example 2:
     *
     * Input: piles = [30,11,23,4,20], h = 5
     * Output: 30
     * Example 3:
     *
     * Input: piles = [30,11,23,4,20], h = 6
     * Output: 23
     *
     *
     * Constraints:
     *
     * 1 <= piles.length <= 104
     * piles.length <= h <= 109
     * 1 <= piles[i] <= 109
     */

    // Time: O(n*logm) n: piles.length, m: max in piles
    public static int minEatingSpeed(int[] piles, int h) {
        int min = 1;
        int max = 0;
        for (int p: piles) {
            max = Math.max(max,p);
        }

        while (min < max) {
            int mid = min + (max - min) / 2;

            if (!canEatAll(piles, h, mid)) {
                min = mid + 1;
            } else {
                max = mid;
            }
        }

        return min;
    }

    private static boolean canEatAll(int[] piles, int h, int k) {
        int count = 0;

        for (int p: piles) {
            count += p / k;
            if (p % k != 0) {
                count++;
            }
            if (count > h) {
                return false;
            }
        }

        return count <= h;
    }

    public static void main(String[] args) {
        int[] test1 = {3,6,7,11};
        int[] test2 = {30,11,23,4,20};
        int[] test3 = {30,11,23,4,20};

        System.out.println(minEatingSpeed(test1, 8));
        System.out.println(minEatingSpeed(test2, 5));
        System.out.println(minEatingSpeed(test3, 6));
    }
}
