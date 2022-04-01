import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * A string S represents a list of words.
     *
     * Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].
     *
     * For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].
     *
     * Return all words that can be formed in this manner, in lexicographical order.
     *
     * Example 1:
     *
     * Input: "{a,b}c{d,e}f"
     * Output: ["acdf","acef","bcdf","bcef"]
     *
     * Example 2:
     *
     * Input: "abcd"
     * Output: ["abcd"]
     *
     * Note:
     *
     * 1 <= S.length <= 50
     * There are no nested curly brackets.
     * All characters inside a pair of consecutive opening and ending curly brackets are different.
     */

    // Time: O(n * 3^(n / 7))
    public static String[] expand(String S) {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);
            if (c == '{') {
                StringBuilder sb = new StringBuilder();
                int j = i + 1;
                while (j < S.length() && S.charAt(j) != '}') {
                    if (Character.isLetter(S.charAt(j))) {
                        sb.append(S.charAt(j));
                    }
                    j++;
                }
                list.add(sb.toString());
                i = j;
            } else {
                if (Character.isLetter(c)) {
                    list.add(String.valueOf(c));
                }
            }
        }

        List<String> result = new ArrayList<>();
        helper(list, result, 0, new StringBuilder());
        Collections.sort(result);
        return result.stream().toArray(String[]::new);
    }

    private static void helper(List<String> list, List<String> result, int pos, StringBuilder sb) {
        if (sb.length() == list.size()) {
            result.add(sb.toString());
            return;
        }

        for (char c: list.get(pos).toCharArray()) {
            sb.append(c);
            helper(list, result, pos + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
//        printArray(expand("{a,b}c{d,e}f"));
//        printArray(expand("{a,b}c, d, e{f,g}"));
//        printArray(expand("abcd"));
        printArray(expand("{a,b}{z,x,y}"));
    }

    public static void printArray(String[] array) {
        Arrays.stream(array).forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
