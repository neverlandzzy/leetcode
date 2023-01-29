import java.util.*;

public class Solution {
    /**
     * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices.
     * You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to spend to collect
     * all apples in the tree, starting at vertex 0 and coming back to this vertex.
     *
     * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge
     * connecting the vertices ai and bi. Additionally, there is a boolean array hasApple, where hasApple[i] = true means
     * that vertex i has an apple; otherwise, it does not have any apple.
     *
     * Example 1:
     *
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
     * Output: 8
     * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect
     * all apples is shown by the green arrows.
     *
     * Example 2:
     *
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,false,true,false]
     * Output: 6
     * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect
     * all apples is shown by the green arrows.
     *
     * Example 3:
     *
     * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,false,false,false,false,false]
     * Output: 0
     *
     *
     * Constraints:
     *
     * 1 <= n <= 10^5
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai < bi <= n - 1
     * fromi < toi
     * hasApple.length == n
     */

    // https://leetcode.com/problems/minimum-time-to-collect-all-apples-in-a-tree/solutions/2864715/minimum-time-to-collect-all-apples-in-a-tree/
    // Let's say we have a node c1 which is a child of a node p. We first find the time it takes to collect all the apples in the subtree of c1, let's call it t.
    // If t = 0 and hasApple[c1] = false, that means there are no apples in this subtree and there is no point in visiting it.
    //
    // Otherwise, we must visit the subtree and collect all the apples. The cost of this will be t + 2. The + 2 is because we spend one second moving to c1,
    // then after we collect all the apples, we have to spend another second to move from c1 back to p.

    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int[] edge: edges) {
            int e0 = edge[0];
            int e1 = edge[1];

            adj.computeIfAbsent(e0, value -> new ArrayList<>()).add(e1);
            adj.computeIfAbsent(e1, value -> new ArrayList<>()).add(e0);
        }

        return dfs(0, -1, adj, hasApple);
    }

    private static int dfs(int node, int parent, Map<Integer, List<Integer>> adj, List<Boolean> hasApple) {
        if (!adj.containsKey(node)) {
            return 0;
        }

        int totalTime = 0;
        int childTime = 0;

        for (int child: adj.get(node)) {
            if (child == parent) {
                continue;
            }

            childTime = dfs(child, node, adj, hasApple);

            if (childTime > 0 || hasApple.get(child)) {
                totalTime += childTime + 2;
            }
        }

        return totalTime;
    }

    public static void main(String[] args) {
        int[][] edges1 = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        int[][] edges2 = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        int[][] edges3 = {{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};

        List<Boolean> hasApple1 = new ArrayList<>(Arrays.asList(false,false,true,false,true,true,false));
        List<Boolean> hasApple2 = new ArrayList<>(Arrays.asList(false,false,true,false,false,true,false));
        List<Boolean> hasApple3 = new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false));

        System.out.println(minTime(7, edges1, hasApple1));
        System.out.println(minTime(7, edges2, hasApple2));
        System.out.println(minTime(7, edges3, hasApple3));
    }
}
