import java.util.Arrays;

public class Solution {
    /**
     * You have a set of tiles, where each tile has one letter tiles[i] printed on it.  Return the number of possible non-empty sequences of letters you can make.
     *
     * Example 1:
     *
     * Input: "AAB"
     * Output: 8
     * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
     *
     * Example 2:
     *
     * Input: "AAABBC"
     * Output: 188
     *
     * Note:
     * 1. 1 <= tiles.length <= 7
     * 2. tiles consists of uppercase English letters.
     */

    public static int numTilePossibilities(String tiles) {
        boolean[] visited = new boolean[tiles.length()];
        char[] chars = tiles.toCharArray();
        Arrays.sort(chars);
        int[] count = new int[1];

        helper(chars, visited, count);

        return count[0];
    }

    private static void helper(char[] chars, boolean[] visited, int[] count) {
        for (int i = 0; i < chars.length; i++) {
            if (i > 0 && !visited[i - 1] && chars[i] == chars[i - 1]) {
                continue;
            }

            if (!visited[i]) {
                visited[i]  = true;
                count[0]++;
                helper(chars, visited, count);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(numTilePossibilities("AAB"));
        System.out.println(numTilePossibilities("AAABBC"));
    }
}
