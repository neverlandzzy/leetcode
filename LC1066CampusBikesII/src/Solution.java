public class Solution {

    /**
     * On a campus represented as a 2D grid, there are n workers and m bikes, with n <= m. Each worker
     * and bike is a 2D coordinate on this grid.
     *
     * We assign one unique bike to each worker so that the sum of the Manhattan distances between each
     * worker and their assigned bike is minimized.
     *
     * Return the minimum possible sum of Manhattan distances between each worker and their assigned bike.
     *
     * The Manhattan distance between two points p1 and p2 is
     * Manhattan(p1, p2) = |p1.x - p2.x| + |p1.y - p2.y|.
     *
     *
     *
     * Example 1:
     *
     * Input: workers = [[0,0],[2,1]], bikes = [[1,2],[3,3]]
     * Output: 6
     * Explanation:
     * We assign bike 0 to worker 0, bike 1 to worker 1. The Manhattan distance of both assignments is 3,
     * so the output is 6.
     *
     * Example 2:
     *
     * Input: workers = [[0,0],[1,1],[2,0]], bikes = [[1,0],[2,2],[2,1]]
     * Output: 4
     * Explanation:
     * We first assign bike 0 to worker 0, then assign bike 1 to worker 1 or worker 2,
     * bike 2 to worker 2 or worker 1. Both assignments lead to sum of the Manhattan distances as 4.
     *
     * Example 3:
     *
     * Input: workers = [[0,0],[1,0],[2,0],[3,0],[4,0]], bikes = [[0,999],[1,999],[2,999],[3,999],[4,999]]
     * Output: 4995
     *
     *
     * Constraints:
     *
     * n == workers.length
     * m == bikes.length
     * 1 <= n <= m <= 10
     * workers[i].length == 2
     * bikes[i].length == 2
     * 0 <= workers[i][0], workers[i][1], bikes[i][0], bikes[i][1] < 1000
     * All the workers and the bikes locations are unique.
     */

    // Solution 1: DFS, brute force
//    public static int assignBikes(int[][] workers, int[][] bikes) {
//        int n = bikes.length;
//        boolean[] visited = new boolean[n];
//        int[] min = new int[1];
//        min[0] = Integer.MAX_VALUE;
//
//        dfs(workers, bikes, visited, 0, 0, min);
//        return min[0];
//    }
//
//    private static void dfs(int[][] workers, int[][] bikes, boolean[] visited, int index, int distance, int[] min) {
//        if (index == workers.length) {
//            min[0] = Math.min(min[0], distance);
//            return;
//        }
//
//        if (distance > min[0]) {
//            return;
//        }
//
//        for (int i = 0; i < bikes.length; i++) {
//            if (!visited[i]) {
//                visited[i] = true;
//                dfs(workers, bikes, visited, index + 1, distance + distance(workers[index], bikes[i]), min);
//                visited[i] = false;
//            }
//        }
//
//    }
//
//    private static int distance(int[] worker, int[] bike) {
//        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
//    }

    // Solution 2: DFS + memorization
    // https://leetcode.com/problems/campus-bikes-ii/discuss/360037/Step-by-Step-solution-from-400ms-to-1ms-(beating-100)
    public static int assignBikes(int[][] workers, int[][] bikes) {
        // Using an array to memorize distances that we have calculated.
        // when visiting ith bike, we mark the ith bit in mask to 1.
        // The bikes.length <= 10, we need maximum of 10 bits for the mask (2^10).
        int [] cache = new int[1 << bikes.length];
        return dfs(workers, bikes, 0, 0, cache);
    }

    private static int dfs(int[][] workers, int[][] bikes, int index, int mask, int[] cache) {
        if (index == workers.length) {
            return 0 ;
        }

        if (cache[mask] > 0) {
            return cache[mask];
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < bikes.length; i++) {
            if ((mask & (1 << i)) == 0) {
                mask |= 1 << i; // set ith bit
                min = Math.min(min, dfs(workers, bikes, index + 1, mask, cache) + distance(workers[index], bikes[i]));
                mask &= ~(1 << i); // unset ith bit
            }
        }

        cache[mask] = min;
        return min;
    }

    private static int distance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    public static void main(String[] args) {
        int[][] workers1 = {{0, 0}, {2, 1}};
        int[][] bikes1 = {{1, 2}, {3, 3}};

        int[][] workers2 = {{0, 0}, {1, 1}, {2, 0}};
        int[][] bikes2 = {{1, 0}, {2, 2}, {2, 1}};

        int[][] workers3 = {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}};
        int[][] bikes3 = {{0,999}, {1,999}, {2,999}, {3, 999}, {4, 999}};


        System.out.println(assignBikes(workers1, bikes1));
        System.out.println(assignBikes(workers2, bikes2));
        System.out.println(assignBikes(workers3, bikes3));
    }
}
