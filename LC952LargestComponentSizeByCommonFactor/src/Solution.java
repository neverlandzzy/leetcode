import java.util.HashMap;
import java.util.Map;

public class Solution {

    /**
     * You are given an integer array of unique positive integers nums. Consider the following graph:
     *
     * There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
     * There is an undirected edge between nums[i] and nums[j] if nums[i] and nums[j] share a common factor greater than 1.
     * Return the size of the largest connected component in the graph.
     *
     * Example 1:
     *
     * Input: nums = [4,6,15,35]
     * Output: 4
     *
     *
     * Example 2:
     *
     * Input: nums = [20,50,9,63]
     * Output: 2
     *
     * Example 3:
     *
     * Input: nums = [2,3,6,7,4,12,21,39]
     * Output: 8
     *
     *
     * Constraints:
     *
     * 1 <= nums.length <= 2 * 10^4
     * 1 <= nums[i] <= 105
     * All the values of nums are unique.
     */

    static class UnionFind {
        int[] parent;
        int[] size;
        int maxSize;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            maxSize = 1;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] == x) {
                return x;
            }

            parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX != parentY) {
                parent[parentX] = parentY;
                size[parentY] += size[parentX];
                this.maxSize = Math.max(this.maxSize, size[parentY]);
            }
        }
    }

    public static int largestComponentSize(int[] nums) {
        int n = nums.length;

        // Key: factor, Value: index
        Map<Integer, Integer> map = new HashMap<>();

        UnionFind uf = new UnionFind(n);

        for (int i = 0; i < n; i++) {
            int num = nums[i];

            for (int j = 2; j * j <= num; j++) {
                if (num % j == 0) {
                    if (!map.containsKey(j)) {
                        map.put(j, i);
                    } else {
                        uf.union(i, map.get(j));
                    }

                    if (!map.containsKey(num / j)) {
                        map.put(num / j, i);
                    } else {
                        uf.union(i, map.get(num / j));
                    }
                }
            }

            if (!map.containsKey(num)) {
                map.put(num, i);
            } else {
                uf.union(i, map.get(num));
            }
        }

        return uf.maxSize;
    }

    public static void main(String[] args) {
        int[] test1 = {4,6,15,35};
        int[] test2 = {20,50,9,63};
        int[] test3 = {2,3,6,7,4,12,21,39};

        System.out.println(largestComponentSize(test1));
        System.out.println(largestComponentSize(test2));
        System.out.println(largestComponentSize(test3));
    }
}
