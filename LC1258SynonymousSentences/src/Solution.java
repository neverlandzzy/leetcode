import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * Given a list of pairs of equivalent words synonyms and a sentence text, Return all possible synonymous sentences sorted lexicographically.
     *
     * Example 1:
     *
     * Input:
     * synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]],
     * text = "I am happy today but was sad yesterday"
     * Output:
     * ["I am cheerful today but was sad yesterday",
     * ​​​​​​​"I am cheerful today but was sorrow yesterday",
     * "I am happy today but was sad yesterday",
     * "I am happy today but was sorrow yesterday",
     * "I am joy today but was sad yesterday",
     * "I am joy today but was sorrow yesterday"]
     *
     *
     * Constraints:
     *
     * 0 <= synonyms.length <= 10
     * synonyms[i].length == 2
     * synonyms[0] != synonyms[1]
     * All words consist of at most 10 English letters only.
     * text is a single space separated sentence of at most 10 words.
     */

    public static List<String> generateSentences(List<List<String>> synonyms, String text) {

    }

    public static void main(String[] args) {
        List<List<String>> synonyms = new ArrayList<>();
        synonyms.add(new ArrayList<> (Arrays.asList("happy", "joy")));
        synonyms.add(new ArrayList<> (Arrays.asList("sad","sorrow")));
        synonyms.add(new ArrayList<> (Arrays.asList("joy","cheerful")));

        System.out.println(generateSentences(synonyms, "I am happy today but was sad yesterday"));
    }
}
