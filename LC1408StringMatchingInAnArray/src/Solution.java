import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {
    /**
     * Given an array of string words. Return all strings in words which is substring of another word in any order.
     *
     * String words[i] is substring of words[j], if can be obtained removing some characters to left and/or right side of words[j].
     *
     *
     *
     * Example 1:
     *
     * Input: words = ["mass","as","hero","superhero"]
     * Output: ["as","hero"]
     * Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
     * ["hero","as"] is also a valid answer.
     * Example 2:
     *
     * Input: words = ["leetcode","et","code"]
     * Output: ["et","code"]
     * Explanation: "et", "code" are substring of "leetcode".
     * Example 3:
     *
     * Input: words = ["blue","green","bu"]
     * Output: []
     *
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 30
     * words[i] contains only lowercase English letters.
     * It's guaranteed that words[i] will be unique.
     */

    public static List<String> stringMatching(String[] words) {
        List<String> result = new ArrayList<>();

        // Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        Arrays.sort(words, Comparator.comparingInt(String::length));

        for (int i = 0; i < words.length - 1; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (words[j].contains(words[i])) {
                    result.add(words[i]);
                    break;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words1 = {"mass","as","hero","superhero"};
        String[] words2 = {"leetcode","et","code"};
        String[] words3 = {"blue","green","bu"};

        System.out.println(stringMatching(words1));
        System.out.println(stringMatching(words2));
        System.out.println(stringMatching(words3));
    }
}
