import java.util.Stack;

public class Solution {

    /**
     * Given a string s, return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
     *
     * Example 1:
     *
     * Input: s = "bcabc"
     * Output: "abc"
     *
     * Example 2:
     *
     * Input: s = "cbacdcbc"
     * Output: "acdb"
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists of lowercase English letters.
     */

    // Same as LC316
    public static String smallestSubsequence(String s) {
        int[] bitMap = new int[26];
        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (char c: s.toCharArray()) {
            bitMap[c - 'a']++;
        }

        for (char c: s.toCharArray()) {
            bitMap[c - 'a']--;

            if (visited[c - 'a']) {
                continue;
            }

            while (!stack.isEmpty() && c < stack.peek() && bitMap[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            visited[c - 'a'] = true;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(smallestSubsequence("bcabc"));
        System.out.println(smallestSubsequence("cbacdcbc"));
    }
}
