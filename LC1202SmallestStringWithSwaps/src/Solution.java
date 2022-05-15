import java.util.*;

public class Solution {
    /**
     * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2
     * indices(0-indexed) of the string.
     *
     * You can swap the characters at any pair of indices in the given pairs any number of times.
     *
     * Return the lexicographically smallest string that s can be changed to after using the swaps.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "dcab", pairs = [[0,3],[1,2]]
     * Output: "bacd"
     * Explaination:
     * Swap s[0] and s[3], s = "bcad"
     * Swap s[1] and s[2], s = "bacd"
     *
     * Example 2:
     *
     * Input: s = "dcab", pairs = [[0,3],[1,2],[0,2]]
     * Output: "abcd"
     * Explaination:
     * Swap s[0] and s[3], s = "bcad"
     * Swap s[0] and s[2], s = "acbd"
     * Swap s[1] and s[2], s = "abcd"
     *
     * Example 3:
     *
     * Input: s = "cba", pairs = [[0,1],[1,2]]
     * Output: "abc"
     * Explaination:
     * Swap s[0] and s[1], s = "bca"
     * Swap s[1] and s[2], s = "bac"
     * Swap s[0] and s[1], s = "abc"
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * 0 <= pairs.length <= 10^5
     * 0 <= pairs[i][0], pairs[i][1] < s.length
     * s only contains lower case English letters.
     */

    // https://leetcode.com/problems/smallest-string-with-swaps/discuss/388055/Java-Union-find-%2B-PriorityQueue.-Easy-to-understand.
    static class UnionFind {
        int[] parents;

        public UnionFind(int size) {
            parents = new int[size];

            for (int i = 0; i < size; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (parents[x] == x) {
                return x;
            }

            parents[x] = find(parents[x]);
            return parents[x];
        }

        public void union(int x, int y) {
            int pX = find(x);
            int pY = find(y);
            if (pX < pY) {
                parents[pY] = pX;
            } else {
                parents[pX] = pY;
            }
        }
    }
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() == 0) {
            return null;
        }

        int n = s.length();
        UnionFind uf = new UnionFind(n);

        for (List<Integer> pair: pairs) {
            uf.union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int parent = uf.find(i);
            map.putIfAbsent(parent, new PriorityQueue<>());
            map.get(parent).offer(s.charAt(i));
        }
        System.out.println(map);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(map.get(uf.find(i)).poll());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        List<List<Integer>> test1 = new ArrayList<>();
        test1.add(Arrays.asList(0, 3));
        test1.add(Arrays.asList(1, 2));

        System.out.println(smallestStringWithSwaps("dcab", test1));

        List<List<Integer>> test2 = new ArrayList<>();
        test2.add(Arrays.asList(0, 3));
        test2.add(Arrays.asList(1, 2));
        test2.add(Arrays.asList(0, 2));

        System.out.println(smallestStringWithSwaps("dcab", test2));

        List<List<Integer>> test3 = new ArrayList<>();
        test3.add(Arrays.asList(0, 1));
        test3.add(Arrays.asList(1, 2));

        System.out.println(smallestStringWithSwaps("cba", test3));
    }
}
