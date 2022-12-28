import java.util.*;

public class Solution {
    /**
     * We want to split a group of n people (labeled from 1 to n) into two groups of any size. Each person may dislike some other people, and
     * they should not go into the same group.
     *
     * Given the integer n and the array dislikes where dislikes[i] = [ai, bi] indicates that the person labeled ai does not like the person
     * labeled bi, return true if it is possible to split everyone into two groups in this way.
     *
     * Example 1:
     *
     * Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
     * Output: true
     * Explanation: group1 [1,4] and group2 [2,3].
     *
     * Example 2:
     *
     * Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
     * Output: false
     *
     * Example 3:
     *
     * Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
     * Output: false
     *
     *
     * Constraints:
     *
     * 1 <= n <= 2000
     * 0 <= dislikes.length <= 10^4
     * dislikes[i].length == 2
     * 1 <= dislikes[i][j] <= n
     * ai < bi
     * All the pairs of dislikes are unique.
     */

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] dislike: dislikes) {
            map.get(dislike[0]).add(dislike[1]);
            map.get(dislike[1]).add(dislike[0]);
        }

        int[] colors = new int[n + 1];  // 1: blue, -1: red, 0: uncolored

        for (int i = 1; i <= n; i++) {
            if (colors[i] == 0) {
                if (!bfs(map, i, colors)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean bfs(Map<Integer, List<Integer>> map, int start, int[] colors) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        colors[start] = 1;

        while(!queue.isEmpty()) {
            int node = queue.poll();
            if (!map.containsKey(node)) {
                continue;
            }

            for (int neighbor: map.get(node)) {
                if (colors[neighbor] == colors[node]) {
                    return false;
                }

                if (colors[neighbor] == 0) {
                    colors[neighbor] = colors[node] * -1;
                    queue.offer(neighbor);
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 2}, {1, 3}, {2, 4}};
        int[][] test2 = {{1, 2}, {1, 3}, {2, 3}};
        int[][] test3 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};

        System.out.println(possibleBipartition(4, test1));
        System.out.println(possibleBipartition(3, test2));
        System.out.println(possibleBipartition(5, test3));
    }
}
