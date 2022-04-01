import java.util.*;

public class Solution {
    /**
     * On a campus represented on the X-Y plane, there are n workers and m bikes, with n <= m.
     *
     * You are given an array workers of length n where workers[i] = [xi, yi] is the position of the ith worker. You are also
     * given an array bikes of length m where bikes[j] = [xj, yj] is the position of the jth bike. All the given positions are
     * unique.
     *
     * Assign a bike to each worker. Among the available bikes and workers, we choose the (workeri, bikej) pair with the shortest
     * Manhattan distance between each other and assign the bike to that worker.
     *
     * If there are multiple (workeri, bikej) pairs with the same shortest Manhattan distance, we choose the pair with the smallest
     * worker index. If there are multiple ways to do that, we choose the pair with the smallest bike index. Repeat this process
     * until there are no available workers.
     *
     * Return an array answer of length n, where answer[i] is the index (0-indexed) of the bike that the ith worker is assigned to.
     *
     * The Manhattan distance between two points p1 and p2 is Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
     *
     *
     *
     * Example 1:
     *
     *
     * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
     * Output: [1,0]
     * Explanation: Worker 1 grabs Bike 0 as they are closest (without ties), and Worker 0 is assigned Bike 1. So the output is [1, 0].
     *
     * Example 2:
     *
     *
     * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
     * Output: [0,2,1]
     * Explanation: Worker 0 grabs Bike 0 at first. Worker 1 and Worker 2 share the same distance to Bike 2, thus Worker 1 is
     * assigned to Bike 2, and Worker 2 will take Bike 1. So the output is [0,2,1].
     *
     *
     * Constraints:
     *
     * n == workers.length
     * m == bikes.length
     * 1 <= n <= m <= 1000
     * workers[i].length == bikes[j].length == 2
     * 0 <= xi, yi < 1000
     * 0 <= xj, yj < 1000
     * All worker and bike locations are unique.
     */

    // Solution 1: Priority Queue - Time: O(m*n*log(m)): n is the number of workers, and m is the number of bikes.
//    public static int[] assignBikes(int[][] workers, int[][] bikes) {
//        int m = bikes.length;
//        int n = workers.length;
//
//        // For each worker, store a list of bikes along with their distance int[] --> [distance, bike index]
//        // The list of bikes is sorted by distance
//        List<List<int[]>> workerToBikeList = new ArrayList<>();
//
//        // Stores the closets bike index for each worker
//        int[] closestBikeIndex = new int[n];
//
//        // int[] --> [distance, workerIndex, bikeIndex]
//        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] != o2[0]) {
//                    // Sort by distance first
//                    return o1[0] - o2[0];
//                } else if (o1[1] != o2[1]) {
//                    // If there is tie in distance, sort by worker index
//                    return o1[1] - o2[1];
//                } else {
//                    // If there is still tie, sort by bike index
//                    return o1[2] - o2[2];
//                }
//            }
//        });
//
//        // iterate on workers
//        for (int i = 0; i < n; i++) {
//            // add all bikes along with their distances for each worker
//            List<int[]> bikeList = new ArrayList<>(); // int[] --> [distance, bike]
//
//            // iterate on bikes
//            for (int j = 0; j < m; j++) {
//                int[] bike = bikes[j];
//                int[] worker = workers[i];
//                int distance = Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]);
//                bikeList.add(new int[]{distance, j});
//            }
//
//            // For each worker, sort his/her bikeList by distance
//            Collections.sort(bikeList, Comparator.comparingInt((int[] a) -> a[0]));
//
//            workerToBikeList.add(bikeList);
//
//            addClosestBikeToHeap(heap, workerToBikeList, closestBikeIndex, i);
//        }
//
//        boolean[] occupied = new boolean[m];
//        int[] assigned = new int[n];
//
//        Arrays.fill(assigned, -1);
//
//        while (!heap.isEmpty()) {
//            int[] pair = heap.poll();
//
//            int worker = pair[1];
//            int bike = pair[2];
//
//            if (assigned[worker] == -1 && !occupied[bike]) {
//                assigned[worker] = bike;
//                occupied[bike] = true;
//            } else {
//                addClosestBikeToHeap(heap, workerToBikeList, closestBikeIndex, worker);
//            }
//        }
//
//        return assigned;
//    }
//
//    private static void addClosestBikeToHeap(PriorityQueue<int[]> heap, List<List<int[]>> workerToBikeList, int[] closestBikeIndex, int worker) {
//        int[] closestBike = workerToBikeList.get(worker).get(closestBikeIndex[worker]); //[distance, bikeIndex]
//        closestBikeIndex[worker]++;
//
//        heap.offer(new int[]{closestBike[0], worker, closestBike[1]});
//    }

    // Solution 2: Bucket sort - Time: O(m * n + K) (K is the range (2000))
    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        int range = 2001; // since  0 <= xi, yi < 1000  0 <= xj, yj < 1000
        int m = bikes.length;
        int n = workers.length;
        int[] result = new int[n];
        boolean[] assigned = new boolean[n];
        boolean[] occupied = new boolean[m];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int[] bike = bikes[i];
                int[] worker = workers[j];
                int distance = Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]);

                if (!map.containsKey(distance)) {
                    map.put(distance, new ArrayList<>());
                }

                map.get(distance).add(new int[]{j, i}); // [w, b]
            }
        }

        for (int i = 0; i < range; i++) {
            if (map.containsKey(i)) {
                List<int[]> list = map.get(i);

                for (int[] pair : list) {
                    int w = pair[0];
                    int b = pair[1];

                    if (!assigned[w] && !occupied[b]) {
                        result[w] = b;
                        assigned[w] = true;
                        occupied[b] = true;
                    }
                }
            }
        }

        return result;
    }


    public static void main(String[] args) {
        int[][] workers1 = {{0, 0}, {2, 1}};
        int[][] bikes1 = {{1, 2}, {3, 3}};

        int[][] workers2 = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes2 = {{1, 0}, {2, 2}, {2, 1}};

        int[] result1 = assignBikes(workers1, bikes1);
        int[] result2 = assignBikes(workers2, bikes2);

        printArray(result1);
        printArray(result2);
    }

    private static void printArray(int[] array) {
        for (int a: array) {
            System.out.print(a + ", ");
        }

        System.out.println();
    }
}
