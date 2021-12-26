import java.util.Arrays;
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
    // Solution 1: Priority Queue, O(n*logn)
//    public static int[][] kClosest(int[][] points, int K) {
//        PriorityQueue<int[]> heap = new PriorityQueue<>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
//
//        for (int[] pos: points) {
//            heap.offer(pos);
//            if (heap.size() > K) {
//                heap.poll();
//            }
//        }
//
//        int[][] result = new int[K][2];
//
//        for (int i = 0; i < K; i++) {
//            result[i] = heap.poll();
//        }
//
//        return result;
//    }

    // Solution 2: Quick Select, Time(average):O(n)
    public static int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        quickSelect(points, 0, n - 1, K);

        return Arrays.copyOf(points, K);
    }

    private static void quickSelect(int[][] points, int low, int high, int k){
        int left = low;
        int right = high;

        int[] pivot = points[(left + high) / 2];
        int pivotDistance = squaredDistance(pivot);

        swap(points, (left + high) / 2, high);

        while (left < right) {
            if (squaredDistance(points[left]) > pivotDistance) {
                right--;
                swap(points, left, right);
            } else {
                left++;
            }
        }
        swap(points, left, high);

        int count = left - low + 1;

        if (count == k) {
            return;
        }

        if (count < k) {
            // <= pivot的数不够，从右面继续找 k - count个
            quickSelect(points, left + 1, high, k - count);
        } else {
            // <= pivot的数太多，从左边找k个
            quickSelect(points, low, left - 1, k);
        }
    }

    private static int squaredDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private static void swap(int[][] points, int i, int j) {
        int[] tmp = points[i];

        points[i] = points[j];
        points[j] = tmp;
    }

    public static void main(String[] args) {
        int[][] points1 = {{1, 3}, {-2, 2}};
        int[][] points2 = {{3, 3}, {5, -1}, {-2, 4}};
        int[][] points3 = {{2, 2}, {2, 2}, {2, 2}, {2, 2}, {2, 2}, {2, 2}, {1, 1}};

        print(kClosest(points1, 1));
        print(kClosest(points2, 2));
        print(kClosest(points3, 1));
    }

    private static void print(int[][] points) {
        for (int[] point: points) {
            System.out.print("[" +  point[0] + ", " + point[1] + "]" + ", ");
        }
        System.out.println();
    }
}
