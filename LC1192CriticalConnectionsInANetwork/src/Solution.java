import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * There are n servers numbered from 0 to n-1 connected by undirected server-to-server connections forming a network where connections[i] = [a, b] represents a connection between servers a and b. Any server can reach any other server directly or indirectly through the network.
     *
     * A critical connection is a connection that, if removed, will make some server unable to reach some other server.
     *
     * Return all critical connections in the network in any order.
     *
     * Example 1:
     *
     *     1 ----- 2
     *     | \    /
     *     |  \  /
     *     |   0
     *     | <-critical
     *     3
     * Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
     * Output: [[1,3]]
     * Explanation: [[3,1]] is also accepted.
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * n-1 <= connections.length <= 10^5
     * connections[i][0] != connections[i][1]
     * There are no repeated connections.
     */

    // 亚麻OA
    // Tarjan's algorithm
    // https://www.youtube.com/watch?v=mKUsbABiwBI
    // https://leetcode.com/problems/critical-connections-in-a-network/discuss/401340/Clean-Java-Solution-With-Explanation!!!-Great-Question!
    // Time: O(V + E)
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (List<Integer> c: connections) {
            graph.get(c.get(0)).add(c.get(1));
            graph.get(c.get(1)).add(c.get(0));
        }

        Integer[] timestamps = new Integer[n];
        List<List<Integer>> result = new ArrayList<>();

        dfs(0, -1, timestamps, 0, graph, result);

        return result;
    }

    private static int dfs(int currentNode, int parentNode, Integer[] timestamps, int currentTimestamp, List<List<Integer>> graph, List<List<Integer>> result) {
        timestamps[currentNode] = currentTimestamp + 1;

        for (int child: graph.get(currentNode)) {
            if (child == parentNode) {
                continue;
            }

            if (timestamps[child] == null) {
                int minTimestamp = dfs(child, currentNode, timestamps, currentTimestamp + 1, graph, result);
                timestamps[currentNode] = Math.min(timestamps[currentNode], minTimestamp);
            } else {
                timestamps[currentNode] = Math.min(timestamps[currentNode], timestamps[child]);
            }
        }

        if ((timestamps[currentNode] == currentTimestamp + 1) && parentNode != -1) {
            result.add(Arrays.asList(parentNode, currentNode));
        }

        return timestamps[currentNode];
    }

    public static void main(String[] args) {
        List<List<Integer>> test1 = new ArrayList<>();
        test1.add(Arrays.asList(0, 1));
        test1.add(Arrays.asList(1, 2));
        test1.add(Arrays.asList(2, 0));
        test1.add(Arrays.asList(1, 3));

        List<List<Integer>> test2 = new ArrayList<>();
        test2.add(Arrays.asList(0, 1));

        System.out.println(criticalConnections(4, test1));
        System.out.println(criticalConnections(2, test2));
    }
}
