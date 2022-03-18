import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    /**
     * You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations
     * where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and
     * course nextCoursei: course prevCoursei has to be taken before course nextCoursei.
     *
     * In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the
     * courses you are taking.
     *
     * Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.
     *
     * Example 1:
     *
     *
     * Input: n = 3, relations = [[1,3],[2,3]]
     * Output: 2
     * Explanation: The figure above represents the given graph.
     * In the first semester, you can take courses 1 and 2.
     * In the second semester, you can take course 3.
     *
     * Example 2:
     *
     * Input: n = 3, relations = [[1,2],[2,3],[3,1]]
     * Output: -1
     * Explanation: No course can be studied because they are prerequisites of each other.
     *
     * Constraints:
     *
     * 1 <= n <= 5000
     * 1 <= relations.length <= 5000
     * relations[i].length == 2
     * 1 <= prevCoursei, nextCoursei <= n
     * prevCoursei != nextCoursei
     * All the pairs [prevCoursei, nextCoursei] are unique.
     */

    // Very similar to LC207 and LC210
    static class  Digraph {
        private int V;
        private int E;
        private List<List<Integer>> neighbors;
        private int[] indegree;

        public Digraph(int V, int[][] edges) {
            this.V = V;
            this.E = edges.length;
            indegree = new int[V + 1];
            neighbors = new ArrayList<>();

            for (int i = 0; i <= V; i++) {
                ArrayList<Integer> list = new ArrayList<>();
                neighbors.add(list);
            }

            for (int i = 0; i < E; i++) {
                int m = edges[i][1];
                int n = edges[i][0];

                neighbors.get(m).add(n);
                indegree[n]++;
            }
        }

        public int topologicalOrders() {

            Queue<Integer> queue = new LinkedList<>();

            for (int i = 1; i <= V; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                }
            }

            int counter = 0;
            int level = 0;

            while (!queue.isEmpty()) {
                int size = queue.size();
                level++;
                for (int i = 0; i < size; i++) {
                    int v = queue.poll();

                    counter++;

                    for (int w : neighbors.get(v)) {
                        if (--indegree[w] == 0) {
                            queue.add(w);
                        }
                    }
                }
            }

            if (counter < V) return -1;
            else return level;
        }
    }

    public static int minimumSemesters(int n, int[][] relations) {
        Digraph G = new Digraph(n, relations);

        return G.topologicalOrders();
    }

    public static void main(String[] args) {
        int[][] test1 = {{1, 3}, {2, 3}};
        int[][] test2 = {{1, 3}, {2, 3}, {3, 1}};

        System.out.println(minimumSemesters(3, test1));
        System.out.println(minimumSemesters(3, test2));
    }
}
