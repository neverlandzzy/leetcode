import java.util.Arrays;

public class Solution {

    /**
     * Given a palindromic string of lowercase English letters palindrome, replace exactly one character
     * with any lowercase English letter so that the resulting string is not a palindrome and that it is
     * the lexicographically smallest one possible.
     *
     * Return the resulting string. If there is no way to replace a character to make it not a palindrome,
     * return an empty string.
     *
     * A string a is lexicographically smaller than a string b (of the same length) if in the first position
     * where a and b differ, a has a character strictly smaller than the corresponding character in b.
     * For example, "abcc" is lexicographically smaller than "abcd" because the first position they differ
     * is at the fourth character, and 'c' is smaller than 'd'.
     *
     * Example 1:
     *
     * Input: palindrome = "abccba"
     * Output: "aaccba"
     * Explanation: There are many ways to make "abccba" not a palindrome, such as "zbccba", "aaccba", and "abacba".
     * Of all the ways, "aaccba" is the lexicographically smallest.
     *
     * Example 2:
     *
     * Input: palindrome = "a"
     * Output: ""
     * Explanation: There is no way to replace a single character to make "a" not a palindrome, so return an empty string.
     *
     * Example 3:
     *
     * Input: palindrome = "aa"
     * Output: "ab"
     *
     * Example 4:
     *
     * Input: palindrome = "aba"
     * Output: "abb"
     *
     *
     * Constraints:
     *
     * 1 <= palindrome.length <= 1000
     * palindrome consists of only lowercase English letters.
     */

    // 贪心算法：对于除了"aaaa" 或者 "aaacaaa"这类的字符串，只要把最靠前的不是a的字符换成a，即可break palindrome
    //         并且保证lexicographically smallest. 而对于那两类特殊情况，只需把最末尾的a换成b即可满足条件
    public static String breakPalindrome(String palindrome) {
        if (palindrome == null || palindrome.length() <= 1) {
            return "";
        }

        char[] chars = palindrome.toCharArray();

        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != 'a') {
                chars[i] = 'a';
                return String.valueOf(chars);
            }
        }

        chars[chars.length - 1] = 'b';
        return String.valueOf(chars);
    }


    public static void main(String[] args) {
        System.out.println(breakPalindrome("abccba"));
        System.out.println(breakPalindrome("a"));
        System.out.println(breakPalindrome("aa"));
        System.out.println(breakPalindrome("aba"));
    }
}
