import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {

    /**
     * Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it, and return the new string.
     *
     *
     * Example 1:
     *
     * Input: s = "leetcodeisacommunityforcoders"
     * Output: "ltcdscmmntyfrcdrs"
     * Example 2:
     *
     * Input: s = "aeiou"
     * Output: ""
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 1000
     * s consists of only lowercase English letters.
     */

    private static String removeVowels(String s) {
        StringBuilder sb = new StringBuilder();
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!vowels.contains(c)) {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeVowels("leetcodeisacommunityforcoders"));
        System.out.println(removeVowels("aeiou"));
    }
}
