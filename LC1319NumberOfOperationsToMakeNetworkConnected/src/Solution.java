import java.util.*;

public class Solution {

    /**
     * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where
     * connections[i] = [ai, bi] represents a connection between computers ai and bi. Any computer can reach any other computer
     * directly or indirectly through the network.
     *
     * You are given an initial computer network connections. You can extract certain cables between two directly connected computers,
     * and place them between any pair of disconnected computers to make them directly connected.
     *
     * Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible,
     * return -1.
     *
     * Example 1:
     *
     * Input: n = 4, connections = [[0,1],[0,2],[1,2]]
     * Output: 1
     * Explanation: Remove cable between computer 1 and 2 and place between computers 1 and 3.
     *
     * Example 2:
     *
     * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
     * Output: 2
     *
     * Example 3:
     *
     * Input: n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
     * Output: -1
     * Explanation: There are not enough cables.
     *
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * 1 <= connections.length <= min(n * (n - 1) / 2, 10^5)
     * connections[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * There are no repeated connections.
     * No two computers are connected by more than one cable.
     */

    // 类似LC323
    public static int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        boolean[] visited = new boolean[n];
        List<List<Integer>> isConnected = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            isConnected.add(new ArrayList<>());
        }

        for (int[] c: connections) {
            isConnected.get(c[0]).add(c[1]);
            isConnected.get(c[1]).add(c[0]);
        }

        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(isConnected, visited, i);
                count++;
            }
        }

        return count - 1;
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
        int[][] test1 = {{0, 1}, {0, 2}, {1, 2}};
        int[][] test2 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}, {1, 3}};
        int[][] test3 = {{0, 1}, {0, 2}, {0, 3}, {1, 2}};

        System.out.println(makeConnected(4, test1));
        System.out.println(makeConnected(6, test2));
        System.out.println(makeConnected(6, test3));
    }
}
