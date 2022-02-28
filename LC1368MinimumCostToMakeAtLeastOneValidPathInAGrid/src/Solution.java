import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /**
     * Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently
     * in this cell. The sign of grid[i][j] can be:
     *
     * 1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
     * 2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
     * 3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
     * 4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
     * Notice that there could be some signs on the cells of the grid that point outside the grid.
     *
     * You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper
     * left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does
     * not have to be the shortest.
     *
     * You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.
     *
     * Return the minimum cost to make the grid have at least one valid path.
     *
     *
     * Example 1:
     *
     * Input: grid = [[1,1,1,1],[2,2,2,2],[1,1,1,1],[2,2,2,2]]
     * Output: 3
     * Explanation: You will start at point (0, 0).
     * The path to (3, 3) is as follows. (0, 0) --> (0, 1) --> (0, 2) --> (0, 3) change the arrow to down with
     * cost = 1 --> (1, 3) --> (1, 2) --> (1, 1) --> (1, 0) change the arrow to down with
     * cost = 1 --> (2, 0) --> (2, 1) --> (2, 2) --> (2, 3) change the arrow to down with cost = 1 --> (3, 3)
     * The total cost = 3.
     *
     *
     * Example 2:
     *
     * Input: grid = [[1,1,3],[3,2,2],[1,1,4]]
     * Output: 0
     * Explanation: You can follow the path from (0, 0) to (2, 2).
     *
     *
     * Example 3:
     *
     * Input: grid = [[1,2],[4,3]]
     * Output: 1
     *
     * Constraints:
     *
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * 1 <= grid[i][j] <= 4
     */

    // 用DFS 搜索当前点通过sign 可以到达的点，cost为0;
    // 用BFS 搜索当前点相邻但是不能通过sign到达的点，cost为1；
    private final static int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 顺序要和题目一致
    public static int minCost(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        Integer[][] costs = new Integer[m][n];
        Queue<int[]> queue = new LinkedList<>();
        int cost = 0;
        dfs(grid, 0, 0, costs, queue, 0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            cost++;
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int[] dir: DIRECTIONS) {
                    int nextR = cell[0] + dir[0];
                    int nextC = cell[1] + dir[1];
                    dfs(grid, nextR, nextC, costs, queue, cost);
                }
            }
        }

        return costs[m - 1][n - 1];
    }

    private static void dfs(int[][] grid, int r, int c, Integer[][] costs, Queue<int[]> queue, int cost) {
        int m = grid.length;
        int n = grid[0].length;

        if (r < 0 || r >= m || c < 0 || c>= n || costs[r][c] != null) {
            return;
        }

        costs[r][c] = cost;
        queue.offer(new int[]{r, c});

        int next = grid[r][c] - 1;

        dfs(grid, r + DIRECTIONS[next][0], c + DIRECTIONS[next][1], costs, queue, cost);
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 1, 1, 1, }, {2, 2, 2, 2}, {1, 1, 1, 1, }, {2, 2, 2, 2}};
        int[][] test2 = {{1, 1, 3}, {3, 2, 2}, {1, 1, 4}};
        int[][] test3 = {{1, 2}, {4, 3}};

        System.out.println(minCost(test1));
        System.out.println(minCost(test2));
        System.out.println(minCost(test3));
    }
}
