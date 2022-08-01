public class Solution {
    /**
     * There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1 to n), some houses that have been
     * painted last summer should not be painted again.
     *
     * A neighborhood is a maximal group of continuous houses that are painted with the same color.
     *
     * For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
     * Given an array houses, an m x n matrix cost and an integer target where:
     *
     * houses[i]: is the color of the house i, and 0 if the house is not painted yet.
     * cost[i][j]: is the cost of paint the house i with the color j + 1.
     * Return the minimum cost of painting all the remaining houses in such a way that there are exactly target neighborhoods.
     * If it is not possible, return -1.
     *
     *
     * Example 1:
     *
     * Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
     * Output: 9
     * Explanation: Paint houses of this way [1,2,2,1,1]
     * This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
     * Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
     *
     * Example 2:
     *
     * Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
     * Output: 11
     * Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
     * This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}].
     * Cost of paint the first and last house (10 + 1) = 11.
     *
     * Example 3:
     *
     * Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
     * Output: -1
     * Explanation: Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.
     *
     *
     * Constraints:
     *
     * m == houses.length == cost.length
     * n == cost[i].length
     * 1 <= m <= 100
     * 1 <= n <= 20
     * 1 <= target <= m
     * 0 <= houses[i] <= n
     * 1 <= cost[i][j] <= 10^4
     */

    // Solution 1: Top-down dp, mem DFS

    final static int MAX_COST = 1000001;

    private static int findMinCost(int[] houses, int[][] cost, int targetCount, int currIndex,
                                   int neighborhoodCount, int prevHouseColor, Integer[][][] cache) {

        if (currIndex == houses.length) {
            return neighborhoodCount == targetCount ? 0 : MAX_COST;
        }

        if (neighborhoodCount > targetCount) {
            return MAX_COST;
        }

        if (cache[currIndex][neighborhoodCount][prevHouseColor] != null) {
            return cache[currIndex][neighborhoodCount][prevHouseColor];
        }

        int minCost = MAX_COST;

        if (houses[currIndex] != 0) {
            int newNeighborhoodCount = neighborhoodCount + (houses[currIndex] != prevHouseColor ? 1 : 0);
            minCost = findMinCost(houses, cost, targetCount, currIndex + 1, newNeighborhoodCount, houses[currIndex], cache);
        } else {
            int totalColors = cost[0].length;

            for (int color = 1; color <= totalColors; color++) {
                int newNeighborhoodCount = neighborhoodCount + (color != prevHouseColor ? 1 : 0);
                int currCost = cost[currIndex][color - 1]
                        + findMinCost(houses, cost, targetCount, currIndex + 1, newNeighborhoodCount, color, cache);

                minCost = Math.min(minCost, currCost);
            }
        }

        cache[currIndex][neighborhoodCount][prevHouseColor] = minCost;
        return  minCost;

    }
    public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        Integer[][][] cache = new Integer[100][100][21];
        int result = findMinCost(houses, cost, target, 0, 0, 0, cache);

        return result == MAX_COST ? -1 : result;
    }

    public static void main(String[] args) {
        int[] houses1 = {0, 0, 0, 0, 0};
        int[] houses2 = {0, 2, 1, 2, 0};
        int[] houses3 = {3, 1, 2, 3};

        int[][] cost1 = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        int[][] cost2 = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        int[][] cost3 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};

        System.out.println(minCost(houses1, cost1, 5, 2, 3));
        System.out.println(minCost(houses2, cost2, 5, 2, 3));
        System.out.println(minCost(houses3, cost3, 4, 3, 3));
    }


}
