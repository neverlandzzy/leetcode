import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
     *
     * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
     *
     *
     *
     * Example 1:
     *
     *
     * Input: graph = [[1,2],[3],[3],[]]
     * Output: [[0,1,3],[0,2,3]]
     * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
     * Example 2:
     *
     *
     * Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
     * Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
     * Example 3:
     *
     * Input: graph = [[1],[]]
     * Output: [[0,1]]
     * Example 4:
     *
     * Input: graph = [[1,2,3],[2],[3],[]]
     * Output: [[0,1,2,3],[0,2,3],[0,3]]
     * Example 5:
     *
     * Input: graph = [[1,3],[2],[3],[]]
     * Output: [[0,1,2,3],[0,3]]
     *
     *
     * Constraints:
     *
     * n == graph.length
     * 2 <= n <= 15
     * 0 <= graph[i][j] < n
     * graph[i][j] != i (i.e., there will be no self-loops).
     * All the elements of graph[i] are unique.
     * The input graph is guaranteed to be a DAG.
     */

    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        int n = graph.length - 1;

        dfs(result, list, graph, 0, n);

        return result;
    }

    private static void dfs(List<List<Integer>> result, List<Integer> list, int[][] graph, int node, int target) {
        if (node == target) {
            list.add(node);
            result.add(new ArrayList<>(list));
            return;
        }

        list.add(node);
        int[] connectedNodes = graph[node];

        for (int next: connectedNodes) {
            dfs(result, list, graph, next, target);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 2}, {3}, {3}, {}};
        int[][] test2 = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        int[][] test3 = {{1}, {}};
        int[][] test4 = {{1, 2, 3}, {2}, {3}, {}};

        System.out.println(allPathsSourceTarget(test1));
        System.out.println(allPathsSourceTarget(test2));
        System.out.println(allPathsSourceTarget(test3));
        System.out.println(allPathsSourceTarget(test4));
    }
}
