import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /**
     * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between
     * ai and bi in the graph.
     *
     * Return the number of connected components in the graph.
     *
     * Example 1:
     *
     *
     * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
     * Output: 2
     *
     * Example 2:
     *
     *
     * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= n <= 2000
     * 1 <= edges.length <= 5000
     * edges[i].length == 2
     * 0 <= ai <= bi < n
     * ai != bi
     * There are no repeated edges.
     */

    // 转化输入，然后用LC547的解法
    // Time: O(E + V) E = Number of edges, VV = Number of vertices
    public static int countComponents(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        List<List<Integer>> isConnected = new ArrayList<>(); // 比int[][] isConnected = new int[n][n]; 快而且省空间
        int count = 0;

        for (int i = 0; i < n; i++) {
            isConnected.add(new ArrayList<>());
        }
        for (int[] e: edges) {
            isConnected.get(e[0]).add(e[1]);
            isConnected.get(e[1]).add(e[0]);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // dfs(isConnected, visited, i);
                bfs(isConnected, visited, i);
                count++;
            }
        }

        return count;
    }

    private static void bfs(List<List<Integer>> isConnected, boolean[] visited, int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(i);
        visited[i] = true;
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int y: isConnected.get(x)) {
                if (!visited[y]) {
                    visited[y] = true;
                    queue.offer(y);
                }
            }
        }
    }
    private static void dfs(List<List<Integer>> isConnected, boolean[] visited, int i) {
        visited[i] = true;

        for (int j: isConnected.get(i)) {
            if (!visited[j]) {
                dfs(isConnected, visited, j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 1}, {1, 2}, {3, 4}};
        int[][] test2 = {{0, 1}, {1, 2}, {2, 3}, {3, 4}};

        System.out.println(countComponents(5, test1));
        System.out.println(countComponents(5, test2));
    }
}
