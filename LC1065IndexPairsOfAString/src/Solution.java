import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    /**
     * Given a text string and words (a list of strings), return all index pairs [i, j] so that the
     * substring text[i]...text[j] is in the list of words.
     *
     *
     *
     * Example 1:
     *
     * Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
     * Output: [[3,7],[9,13],[10,17]]
     * Example 2:
     *
     * Input: text = "ababa", words = ["aba","ab"]
     * Output: [[0,1],[0,2],[2,3],[2,4]]
     * Explanation:
     * Notice that matches can overlap, see "aba" is found in [0,2] and [2,4].
     *
     *
     * Note:
     *
     * All strings contains only lowercase English letters.
     * It's guaranteed that all strings in words are different.
     * 1 <= text.length <= 100
     * 1 <= words.length <= 20
     * 1 <= words[i].length <= 50
     * Return the pairs [i,j] in sorted order (i.e. sort them by their first coordinate in case of ties sort them by their second coordinate).
     */

    // Solution 1 O(n^2)
    public static int[][] indexPairs(String text, String[] words) {
        List<int[]> result = new ArrayList<>();

        for (int i = 0; i < text.length(); i++) {
            for (String word : words) {
                if (text.startsWith(word, i)) {
                    result.add(new int[] {i, i + word.length() - 1});
                }
            }
        }

        result.sort((a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        return result.toArray(new int[0][0]);
    }

    // Solution 2: Trie

    public static void main(String[] args) {
        String text1 = "thestoryofleetcodeandme";
        String[] words1 = {"story","fleet","leetcode"};

        String text2 = "ababa";
        String[] words2 = {"aba","ab"};

        int[][] result1 = indexPairs(text1, words1);
        int[][] result2 = indexPairs(text2, words2);

        printResult(result1);
        printResult(result2);
    }

    private static void printResult(int[][] result) {
        for (int[] res: result) {
            System.out.print("[");
            for (int k: res) {
                System.out.print(k + ", ");
            }
            System.out.print("], ");
        }
        System.out.println();
    }
}
