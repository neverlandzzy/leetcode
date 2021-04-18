import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    /**
     * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
     *
     * (Here, the distance between two points on a plane is the Euclidean distance.)
     *
     * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
     *
     * Example 1:
     *
     * Input: points = [[1,3],[-2,2]], K = 1
     * Output: [[-2,2]]
     * Explanation:
     * The distance between (1, 3) and the origin is sqrt(10).
     * The distance between (-2, 2) and the origin is sqrt(8).
     * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
     * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
     *
     * Example 2:
     *
     * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
     * Output: [[3,3],[-2,4]]
     * (The answer [[-2,4],[3,3]] would also be accepted.)
     *
     *
     * Note:
     *
     * 1. 1 <= K <= points.length <= 10000
     * 2. -10000 < points[i][0] < 10000
     * 3. -10000 < points[i][1] < 10000
     */

    // Similar to LC215
    public static int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);

        for (int[] pos: points) {
            heap.offer(pos);
            if (heap.size() > K) {
                heap.poll();
            }
        }

        int[][] result = new int[K][2];

        for (int i = 0; i < K; i++) {
            result[i] = heap.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] points1 = {{1, 3}, {-2, 2}};
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};

        print(kClosest(points1, 1));
        print(kClosest(points2, 2));
    }

    private static void print(int[][] points) {
        for (int[] point: points) {
            System.out.print("[" +  point[0] + ", " + point[1] + "]" + ", ");
        }
        System.out.println();
    }
}
