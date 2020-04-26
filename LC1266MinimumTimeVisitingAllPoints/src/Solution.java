public class Solution {

    /**
     * On a plane there are n points with integer coordinates points[i] = [xi, yi]. Your task is to find the minimum time in seconds to visit all points.
     *
     * You can move according to the next rules:
     *
     * In one second always you can either move vertically, horizontally by one unit or diagonally (it means to move one unit vertically and one unit horizontally in one second).
     * You have to visit the points in the same order as they appear in the array.
     *
     * Example 1:
     * Input: points = [[1,1],[3,4],[-1,0]]
     * Output: 7
     * Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
     * Time from [1,1] to [3,4] = 3 seconds
     * Time from [3,4] to [-1,0] = 4 seconds
     * Total time = 7 seconds
     *
     * Example 2:
     *
     * Input: points = [[3,2],[-2,2]]
     * Output: 5
     *
     *
     * Constraints:
     *
     * points.length == n
     * 1 <= n <= 100
     * points[i].length == 2
     * -1000 <= points[i][0], points[i][1] <= 1000
     */
    public static int minTimeToVisitAllPoints(int[][] points) {
        int time = 0;

        for (int i = 0; i < points.length - 1; i++) {
            time += minSteps(points[i], points[i + 1]);
        }

        return time;
    }

    private static int minSteps(int[] point1, int[] point2) {
        return Math.max(Math.abs(point1[0] - point2[0]), Math.abs(point1[1] - point2[1]));
    }

    public static void main(String[] args) {
        int[][] points1 = new int[][] {{1, 1}, {3, 4}, {-1, 0}};
        int[][] points2 = new int[][] {{3, 2}, {-2, 2}};

        System.out.println(minTimeToVisitAllPoints(points1));
        System.out.println(minTimeToVisitAllPoints(points2));
    }
}
