import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {

    /**
     * With respect to a given puzzle string, a word is valid if both the following conditions are satisfied:
     *  - word contains the first letter of puzzle.
     *  - For each letter in word, that letter is in puzzle.
     * For example, if the puzzle is "abcdefg", then valid words are "faced", "cabbage", and "baggage", while
     * invalid words are "beefed" (does not include 'a') and "based" (includes 's' which is not in the puzzle).
     * Return an array answer, where answer[i] is the number of words in the given word list words that is valid
     * with respect to the puzzle puzzles[i].
     *
     *
     * Example 1:
     *
     * Input: words = ["aaaa","asas","able","ability","actt","actor","access"], puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
     * Output: [1,1,3,2,4,0]
     * Explanation:
     * 1 valid word for "aboveyz" : "aaaa"
     * 1 valid word for "abrodyz" : "aaaa"
     * 3 valid words for "abslute" : "aaaa", "asas", "able"
     * 2 valid words for "absoryz" : "aaaa", "asas"
     * 4 valid words for "actresz" : "aaaa", "asas", "actt", "access"
     * There are no valid words for "gaswxyz" cause none of the words in the list contains letter 'g'.
     *
     *
     * Example 2:
     *
     * Input: words = ["apple","pleas","please"], puzzles = ["aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"]
     * Output: [0,1,3,2,0]
     *
     *
     * Constraints:
     *
     * 1 <= words.length <= 105
     * 4 <= words[i].length <= 50
     * 1 <= puzzles.length <= 104
     * puzzles[i].length == 7
     * words[i] and puzzles[i] consist of lowercase English letters.
     * Each puzzles[i] does not contain repeated characters.
     */

    // https://leetcode.com/problems/number-of-valid-words-for-each-puzzle/discuss/1567314/Java-Simple-and-clean-solution-or-Bit-Manipulation-and-HashMap-or-60ms-or-TC%3A-O(NL%2BP)-SC%3AO(N)
    // 1. 将每一个word转化成bitmask，存到HashMap中，统计每一个bitmask的个数，例如："abc"和"cba"有相同的bitmask，这个bitmask对应的
    //    value为2
    // 2. 对于每一个puzzle，遍历其所有的subsetMask:
    //    (1). 第一个字母一定保留，因为word中必须要有
    //    (2). 对于其余的6位，用 subsetMask = (subsetMask - 1) & puzzleMask; 来遍历。
    //         例如：对于puzzle: "abcd"， 遍历"abcd", "abc", "ab", "a"
    //         然后在map中分别查找这几个subsetMask，如果有对应的key，则说明有valid word。
    //
    // Time Complexity: O(N * L + P * (2^6)) = O(N * L + P).
    //    N*L for generating masks for each word
    //    P*6 for generating masks for each puzzle
    //    P * 2^6 for Generating number of subsets. (Note: we are only using 6 chars for finding
    //    subsets as we have excluded the first char from subset

    public static List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        List<Integer> result = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        for (String word: words) {
            int wordMask = getMask(word, 0);
            map.put(wordMask, map.getOrDefault(wordMask, 0) + 1);
        }

        for (String puzzle: puzzles) {
            int puzzleMask = getMask(puzzle, 1);
            int firstBitMask = 1 << (puzzle.charAt(0) - 'a');
            // 对于puzzle，我们从第二位开始遍历subsetMask，firstBitMask保持不变。
            int subsetMask = puzzleMask;
            int count = map.getOrDefault(firstBitMask, 0);

            while (subsetMask != 0) {
                count += map.getOrDefault(subsetMask | firstBitMask, 0);
                subsetMask = (subsetMask - 1) & puzzleMask;
            }

            result.add(count);
        }

        return result;
    }

    private static int getMask(String s, int start) {
        int mask = 0;

        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            mask |= (1 << (c - 'a'));
        }

        return mask;
    }

    public static void main(String[] args) {

        String[] words1 = {"aaaa","asas","able","ability","actt","actor","access"};
        String[] puzzles1 = {"aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"};
        String[] words2 = {"apple","pleas","please"};
        String[] puzzles2 = {"aelwxyz","aelpxyz","aelpsxy","saelpxy","xaelpsy"};

        System.out.println(findNumOfValidWords(words1, puzzles1));
        System.out.println(findNumOfValidWords(words2, puzzles2));
    }
}
