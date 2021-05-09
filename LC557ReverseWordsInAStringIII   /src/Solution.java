public class Solution {
    /**
     * Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "Let's take LeetCode contest"
     * Output: "s'teL ekat edoCteeL tsetnoc"
     * Example 2:
     *
     * Input: s = "God Ding"
     * Output: "doG gniD"
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 5 * 104
     * s contains printable ASCII characters.
     * s does not contain any leading or trailing spaces.
     * There is at least one word in s.
     * All the words in s are separated by a single space.
     */

    public static String reverseWords(String s) {
        int i = 0, j = 0;
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        while (i < n) {
            while (j < n && s.charAt(j) != ' ') {
                j++;
            }
            StringBuilder sub = new StringBuilder(s.substring(i, j));
            sb.append(sub.reverse());
            if (j != n) {
                sb.append(" ");
            }
            j++;
            i = j;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("Let's take LeetCode contest"));
        System.out.println(reverseWords("God Ding"));
    }
}
