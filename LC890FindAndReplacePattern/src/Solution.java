import java.util.*;

public class Solution {

    /**
     * You have a list of words and a pattern, and you want to know which words in words matches the pattern.
     *
     * A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x),
     * we get the desired word.
     *
     * (Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter,
     * and no two letters map to the same letter.)
     *
     * Return a list of the words in words that match the given pattern.
     *
     * You may return the answer in any order.
     *
     *
     *
     * Example 1:
     *
     * Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
     * Output: ["mee","aqq"]
     * Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
     * "ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
     * since a and b map to the same letter.
     *
     *
     * Note:
     *
     * 1 <= words.length <= 50
     * 1 <= pattern.length = words[i].length <= 20
     */

    public static List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<>();

        for (String word: words) {
            Map<Character, Character> map = new HashMap<>();
            Set<Character> set = new HashSet<>();
            int i = 0;
            while (i < word.length()) {
                char w = word.charAt(i);
                char p = pattern.charAt(i);

                if (!map.containsKey(w)) {
                    if (set.contains(p)) {
                        break;
                    }
                    map.put(w, p);
                    set.add(p);
                } else {
                    if (map.get(w) != p) {
                        break;
                    }
                }
                i++;
            }
            if (i == word.length()) {
                result.add(word);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String[] words = {"abc","deq","mee","aqq","dkd","ccc"};
        System.out.println(findAndReplacePattern(words, "abb"));
    }
}
