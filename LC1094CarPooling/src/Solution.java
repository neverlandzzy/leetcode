import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    /**
     * There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).
     *
     * You are given the integer capacity and an array trips where trip[i] = [numPassengersi, fromi, toi] indicates that the ith trip has
     * numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given
     * as the number of kilometers due east from the car's initial location.
     *
     * Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
     * Output: false
     *
     * Example 2:
     *
     * Input: trips = [[2,1,5],[3,3,7]], capacity = 5
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= trips.length <= 1000
     * trips[i].length == 3
     * 1 <= numPassengersi <= 100
     * 0 <= fromi < toi <= 1000
     * 1 <= capacity <= 10^5
     */

    // Solution 1: similar to LC253, Time: O(n*logn)
//    public static boolean carPooling(int[][] trips, int capacity) {
//        if (trips == null || trips.length == 0) {
//            return false;
//        }
//
//        Arrays.sort(trips, Comparator.comparingInt((int[] i) -> i[1]));
//        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt((int[] i) -> i[2]));
//        int load = 0;
//
//        for (int i = 0; i < trips.length; i++) {
//            while (!heap.isEmpty() && heap.peek()[2] <= trips[i][1]) {
//                int[] off = heap.poll();
//                load -= off[0];
//            }
//
//            heap.offer(trips[i]);
//            load += trips[i][0];
//
//            if (load > capacity) {
//                return false;
//            }
//        }
//
//        return true;
//    }

    // Solution 2: Bucket Sort, Time: O(max(n, 1001)) (since 0 <= fromi < toi <= 1000)
    public static boolean carPooling(int[][] trips, int capacity) {
        // because 0 <= fromi < toi <= 1000
        int[] timestamp = new int[1001];

        for (int[] trip: trips) {
            timestamp[trip[1]] += trip[0];
            timestamp[trip[2]] -= trip[0];
        }

        int load = 0;
        for (int t: timestamp) {
            load += t;

            if (load > capacity) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] test1 = {{2, 1, 5}, {3, 3, 7}};
        int[][] test2 = {{2, 1, 5}, {3, 3, 7}};

        System.out.println(carPooling(test1, 4));
        System.out.println(carPooling(test2, 5));
    }
}
