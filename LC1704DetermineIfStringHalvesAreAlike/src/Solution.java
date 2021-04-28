import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * You are given a string s of even length. Split this string into two halves of equal lengths,
     * and let a be the first half and b be the second half.
     *
     * Two strings are alike if they have the same number of vowels ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U').
     * Notice that s contains uppercase and lowercase letters.
     *
     * Return true if a and b are alike. Otherwise, return false.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "book"
     * Output: true
     * Explanation: a = "bo" and b = "ok". a has 1 vowel and b has 1 vowel. Therefore, they are alike.
     * Example 2:
     *
     * Input: s = "textbook"
     * Output: false
     * Explanation: a = "text" and b = "book". a has 1 vowel whereas b has 2. Therefore, they are not alike.
     * Notice that the vowel o is counted twice.
     * Example 3:
     *
     * Input: s = "MerryChristmas"
     * Output: false
     * Example 4:
     *
     * Input: s = "AbCdEfGh"
     * Output: true
     *
     *
     * Constraints:
     *
     * 2 <= s.length <= 1000
     * s.length is even.
     * s consists of uppercase and lowercase letters.
     */

    public static boolean halvesAreAlike(String s) {
        int count = 0;
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

        for (int i = 0; i < s.length() / 2; i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                count++;
            }
        }

        for (int i = s.length() / 2; i < s.length(); i++) {
            char c = s.charAt(i);
            if (set.contains(c)) {
                count--;
            }
        }

        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println(halvesAreAlike("book"));
        System.out.println(halvesAreAlike("textbook"));
        System.out.println(halvesAreAlike("MerryChristmas"));
        System.out.println(halvesAreAlike("AbCdEfGh"));
    }
}
