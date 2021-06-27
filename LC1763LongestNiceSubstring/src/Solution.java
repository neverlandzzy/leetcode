import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * A string s is nice if, for every letter of the alphabet that s contains, it appears both in uppercase and lowercase.
     * For example, "abABB" is nice because 'A' and 'a' appear, and 'B' and 'b' appear. However, "abA" is not because 'b' appears,
     * but 'B' does not.
     *
     * Given a string s, return the longest substring of s that is nice. If there are multiple, return the substring of the earliest occurrence.
     * If there are none, return an empty string.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "YazaAay"
     * Output: "aAa"
     * Explanation: "aAa" is a nice string because 'A/a' is the only letter of the alphabet in s, and both 'A' and 'a' appear.
     * "aAa" is the longest nice substring.
     * Example 2:
     *
     * Input: s = "Bb"
     * Output: "Bb"
     * Explanation: "Bb" is a nice string because both 'B' and 'b' appear. The whole string is a substring.
     * Example 3:
     *
     * Input: s = "c"
     * Output: ""
     * Explanation: There are no nice substrings.
     * Example 4:
     *
     * Input: s = "dDzeE"
     * Output: "dD"
     * Explanation: Both "dD" and "eE" are the longest nice substrings.
     * As there are multiple longest nice substrings, return "dD" since it occurs earlier.
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 100
     * s consists of uppercase and lowercase English letters.
     */

    // https://leetcode.com/problems/longest-nice-substring/discuss/1074589/JavaStraightforward-Divide-and-Conquer
    public static String longestNiceSubstring(String s) {
        if (s.length() < 2) {
            return "";
        }
        Set<Character> set = new HashSet<>();
        for (char c: s.toCharArray()) {
            set.add(c);
        }

        for (int i = 0; i < s.length(); i++) {
            System.out.println("s = " + s + " i = " + i);
            char c = s.charAt(i);
            if (set.contains(Character.toLowerCase(c)) && set.contains(Character.toUpperCase(c))) {
                continue;
            }
            String firstPart = longestNiceSubstring(s.substring(0, i));
            String lastPart = longestNiceSubstring(s.substring(i + 1));

            return firstPart.length() >= lastPart.length() ? firstPart : lastPart;
        }

        return s;
    }

    public static void main(String[] args) {
        System.out.println(longestNiceSubstring("YazaAay"));
//        System.out.println(longestNiceSubstring("Bb"));
//        System.out.println(longestNiceSubstring("c"));
//        System.out.println(longestNiceSubstring("dDzeE"));
    }
}
