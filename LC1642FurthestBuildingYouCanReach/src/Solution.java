import java.util.*;

public class Solution {

    /**
     * You are given an integer array heights representing the heights of buildings, some bricks, and some ladders.
     *
     * You start your journey from building 0 and move to the next building by possibly using bricks or ladders.
     *
     * While moving from building i to building i+1 (0-indexed),
     *
     * If the current building's height is greater than or equal to the next building's height, you do not need a ladder or bricks.
     * If the current building's height is less than the next building's height, you can either use one ladder or (h[i+1] - h[i]) bricks.
     * Return the furthest building index (0-indexed) you can reach if you use the given ladders and bricks optimally.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: heights = [4,2,7,6,9,14,12], bricks = 5, ladders = 1
     * Output: 4
     * Explanation: Starting at building 0, you can follow these steps:
     * - Go to building 1 without using ladders nor bricks since 4 >= 2.
     * - Go to building 2 using 5 bricks. You must use either bricks or ladders because 2 < 7.
     * - Go to building 3 without using ladders nor bricks since 7 >= 6.
     * - Go to building 4 using your only ladder. You must use either bricks or ladders because 6 < 9.
     * It is impossible to go beyond building 4 because you do not have any more bricks or ladders.
     *
     * Example 2:
     *
     * Input: heights = [4,12,2,7,3,18,20,3,19], bricks = 10, ladders = 2
     * Output: 7
     *
     * Example 3:
     *
     * Input: heights = [14,3,19,3], bricks = 17, ladders = 0
     * Output: 3
     *
     *
     * Constraints:
     *
     * 1 <= heights.length <= 10^5
     * 1 <= heights[i] <= 10^6
     * 0 <= bricks <= 10^9
     * 0 <= ladders <= heights.length
     */

    // At all times, we should ensure ladders have been allocated to the longest climbs seen so far and bricks to the shortest.
    // Use a ladder if we have one available. If we're out of ladders, we'll replace the most wasteful ladder allocation with bricks.

    // Solution 1: min heap - Time: O(NlogL) - N is the length of the heights array. L is the number of ladders available.
//    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
//        Queue<Integer> minHeap = new PriorityQueue<>();
//
//        for (int i = 0; i < heights.length - 1; i++) {
//            int diff = heights[i + 1] - heights[i];
//
//            if (diff > 0) {
//                minHeap.offer(diff);
//
//                if (minHeap.size() > ladders) {
//                    bricks -= minHeap.poll();
//                }
//
//                if (bricks < 0) {
//                    return i;
//                }
//            }
//        }
//
//        return heights.length - 1;
//    }

    // Solution 2: Binary Search - Time: O(NlogL)
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        List<int[]> sortedClimbs = new ArrayList<>();
        for (int i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
                sortedClimbs.add(new int[]{diff, i + 1});
            }
        }

        sortedClimbs.sort(Comparator.comparingInt(a -> a[0]));

        int start = 0;
        int end = heights.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2 + 1; // LC441

            if (isReachable(mid, sortedClimbs, bricks, ladders)) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }

    private static boolean isReachable(int buildingIndex, List<int[]> climbs, int bricks, int ladders) {
        for (int[] climbEntry: climbs) {
            int climb = climbEntry[0];
            int index = climbEntry[1];

            if (index > buildingIndex) {
                continue;
            }

            if (climb <= bricks) {
                bricks -= climb;
            } else if (ladders >= 1) {
                ladders--;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] test1 = {4,2,7,6,9,14,12};
        int[] test2 = {4,12,2,7,3,18,20,3,19};
        int[] test3 = {14,3,19,3};

        System.out.println(furthestBuilding(test1, 5, 1));
        System.out.println(furthestBuilding(test2, 10, 2));
        System.out.println(furthestBuilding(test3, 17, 0));
    }
}
