import java.util.*;

public class Solution {
    /**
     * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
     *
     * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes
     * ai and bi in the tree.
     *
     * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and
     * all other nodes.
     *
     * Example 1:
     *
     * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
     * Output: [8,12,6,10,10,10]
     * Explanation: The tree is shown above.
     * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
     * equals 1 + 1 + 2 + 2 + 2 = 8.
     * Hence, answer[0] = 8, and so on.
     *
     * Example 2:
     *
     * Input: n = 1, edges = []
     * Output: [0]
     *
     * Example 3:
     *
     * Input: n = 2, edges = [[1,0]]
     * Output: [1,1]
     *
     *
     * Constraints:
     *
     * 1 <= n <= 3 * 10^4
     * edges.length == n - 1
     * edges[i].length == 2
     * 0 <= ai, bi < n
     * ai != bi
     * The given input represents a valid tree.
     */

    // https://www.youtube.com/watch?v=gi2maECPOB0
    // https://leetcode.com/problems/sum-of-distances-in-tree/solutions/130611/sum-of-distances-in-tree/?orderBy=most_votes
    // 如上面链接中的图，设x点的答案为ans[x], y点的答案为ans[y]，x,y相邻，x@X为以x为root的子树中所有点的距离， #X为以x为root的子树中点的个数包括x
    // ans[x] = x@X + y@Y + #Y
    // ans[y] = x@X + y@Y + #X
    // 两式相减，ans[x] = ans[y] + #y + #x
    // 用int[] results 记录结果(即ans[])
    // 用List<Set<Integer>> graph 记录图
    // 用count[]记录以x为root的子树中点的个数，即#x, #y, ...初始值为1，因为每个点至少包含它自己
    // 进行两次DFS：
    //     第一次做post-order DFS，用来更新count[]，即查找每个点有多少个子节点，并且求出每个点到其所有字节点的距离之和，对于root, ans[0]即为答案
    //     第二次做pre-order DFS，根据ans[0]，求出0的两个child的ans，再求每一个child的child...

    public static int[] sumOfDistancesInTree(int n, int[][] edges) {
        List<Set<Integer>> tree = new ArrayList<>();
        int[] result = new int[n];
        int[] count = new int[n];
        Arrays.fill(count, 1);

        for (int i = 0; i < n; i++) {
            tree.add(new HashSet<>());
        }

        for (int[] edge: edges) {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }

        postOrderDfs(0, -1, tree, result, count);
        preOrderDfs(0, -1, tree, result, count, n);

        return result;
    }

    private static void postOrderDfs(int node, int parent, List<Set<Integer>> tree, int[] result, int[] count) {
        for (int child: tree.get(node)) {
            if (child != parent) {
                postOrderDfs(child, node, tree, result, count);
                count[node] += count[child];
                result[node] += result[child] + count[child];
            }
        }

        printArray(result);
    }

    private static void preOrderDfs(int node, int parent, List<Set<Integer>> tree, int[] result, int[] count, int n) {
        for (int child: tree.get(node)) {
            if (child != parent) {
                result[child] = result[node] - count[child] + n - count[child];
                preOrderDfs(child, node, tree, result, count, n);
            }
        }
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
        int[][] test2 = {};
        int[][] test3 = {{1, 0}};

        int[] result1 = sumOfDistancesInTree(6, test1);
        //int[] result2 = sumOfDistancesInTree(1, test2);
       // int[] result3 = sumOfDistancesInTree(2, test3);

        printArray(result1);
       // printArray(result2);
        //printArray(result3);
    }

    private static void printArray(int[] nums) {
        for (int i: nums) {
            System.out.print(i + ", ");
        }
        System.out.println();
    }
}
