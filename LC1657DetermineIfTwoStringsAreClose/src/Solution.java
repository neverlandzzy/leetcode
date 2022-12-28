import java.util.Arrays;

public class Solution {
    /**
     * Two strings are considered close if you can attain one from the other using the following operations:
     *
     * Operation 1: Swap any two existing characters.
     * For example, abcde -> aecdb
     * Operation 2: Transform every occurrence of one existing character into another existing character, and do the same with the other character.
     * For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
     * You can use the operations on either string as many times as necessary.
     *
     * Given two strings, word1 and word2, return true if word1 and word2 are close, and false otherwise.
     *
     * Example 1:
     *
     * Input: word1 = "abc", word2 = "bca"
     * Output: true
     * Explanation: You can attain word2 from word1 in 2 operations.
     * Apply Operation 1: "abc" -> "acb"
     * Apply Operation 1: "acb" -> "bca"
     *
     * Example 2:
     *
     * Input: word1 = "a", word2 = "aa"
     * Output: false
     * Explanation: It is impossible to attain word2 from word1, or vice versa, in any number of operations.
     *
     * Example 3:
     *
     * Input: word1 = "cabbba", word2 = "abbccc"
     * Output: true
     * Explanation: You can attain word2 from word1 in 3 operations.
     * Apply Operation 1: "cabbba" -> "caabbb"
     * Apply Operation 2: "caabbb" -> "baaccc"
     * Apply Operation 2: "baaccc" -> "abbccc"
     *
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length <= 10^5
     * word1 and word2 contain only lowercase English letters.
     */

    // 需要满足两个条件：
    //    1. 在word1中出现的字母和在word2中出现的字母必须相同
    //    2. 字母出现的频率应该相同，e.g. word1中有2个出现1次的字母，word2中也必须要有2个出现1次的字母，反之亦然
    public static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        int[] word1Map = new int[26];
        int[] word2Map = new int[26];

        for (char c: word1.toCharArray()) {
            word1Map[c - 'a']++;
        }

        for (char c: word2.toCharArray()) {
            word2Map[c - 'a']++;
        }

        // Check condition 1:
        for (int i = 0; i < 26; i++) {
            if (word1Map[i] == 0 && word2Map[i] != 0) {
                return false;
            }
            if (word2Map[i] == 0 && word1Map[i] != 0) {
                return false;
            }
        }

        Arrays.sort(word1Map);
        Arrays.sort(word2Map);

        return Arrays.equals(word1Map, word2Map);
    }

    public static void main(String[] args) {
        System.out.println(closeStrings("abc", "bca"));
        System.out.println(closeStrings("a", "aa"));
        System.out.println(closeStrings("cabbba", "abbccc"));
    }
}
