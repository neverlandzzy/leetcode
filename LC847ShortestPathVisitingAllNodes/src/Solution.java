import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    /**
     * You have an undirected, connected graph of n nodes labeled from 0 to n - 1. You are given an array graph where graph[i] is a
     * list of all the nodes connected with node i by an edge.
     *
     * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times,
     * and you may reuse edges.
     *
     * Example 1:
     *
     * Input: graph = [[1,2,3],[0],[0],[0]]
     * Output: 4
     * Explanation: One possible path is [1,0,2,0,3]
     *
     *
     * Example 2:
     *
     * Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
     * Output: 4
     * Explanation: One possible path is [0,1,4,2,3]
     *
     *
     * Constraints:
     *
     * n == graph.length
     * 1 <= n <= 12
     * 0 <= graph[i].length < n
     * graph[i] does not contain i.
     * If graph[a] contains b, then graph[b] contains a.
     * The input graph is always connected.
     */

    // https://www.youtube.com/watch?v=fzFaqBpDXy8
    // https://www.youtube.com/watch?v=92zh6XvqEgc
    //
    // 1. 用BFS搜索最短的路径，用bitmask记录每一个节点是否被访问，当bitmask == (1 << n) - 1时，说明所有节点被访问到，返回当前步长。
    // 2. 与传统BFS不同，同一个节点可以被访问多次，为了避免死循环，用bitmask记录每一个节点访问时的状态，当状态重复时就不访问，e.g.:
    //    e.g.  其中一条最短路径是 1 -> 0 -> 2 -> 0 -> 3   用visited[0][bitmask] 记录到达0节点时的状态 当第一次到0时，bitmask为 '0011'（只有0和1被访问过）
    //          当第二次到达0节点时，bitmask为'0111'，（0，1，2被访问过），与上一次不同，因此可以访问。如果发生 1->0->1，两次到达1的状态相同，则不再访问1
    //       0 --- 1
    //       | \
    //       |  \
    //       3   2
    // 3. 因为可以从任一节点出发，所以要将所有节点加入到queue中，对n个节点同时进行BFS
    // 4. 每个节点访问与未访问状态共2^n种可能性，从n个节点同时出发，因此时间复杂度为 O(n * 2^n)
    public static int shortestPathLength(int[][] graph) {
        if (graph.length == 1) {
            return 0;
        }

        int n = graph.length;
        int target = (1 << n) - 1;
        boolean[][] visited = new boolean[n][1 << n];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            queue.offer(new int[] {i, 1 << i});
            visited[i][1 << i] = true;
        }

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pair = queue.poll();
                int node = pair[0];
                int state = pair[1];

                if (state == target) {
                    return steps;
                }

                for (int next: graph[node]) {
                    int nextState = state | (1 << next);
                    if (!visited[next][nextState]) {
                        visited[next][nextState] = true;
                        queue.offer(new int[] {next, nextState});
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 2, 3}, {0}, {0}, {0}};
        int[][] test2 = {{1}, {0, 2, 4}, {1, 3, 4}, {2}, {1, 2}};

        System.out.println(shortestPathLength(test1));
        System.out.println(shortestPathLength(test2));
    }
}
