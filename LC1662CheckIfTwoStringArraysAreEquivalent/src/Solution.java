public class Solution {
    /**
     * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
     *
     * A string is represented by an array if the array elements concatenated in order forms the string.
     *
     *
     *
     * Example 1:
     *
     * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
     * Output: true
     * Explanation:
     * word1 represents string "ab" + "c" -> "abc"
     * word2 represents string "a" + "bc" -> "abc"
     * The strings are the same, so return true.
     * Example 2:
     *
     * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
     * Output: false
     * Example 3:
     *
     * Input: word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= word1.length, word2.length <= 103
     * 1 <= word1[i].length, word2[i].length <= 103
     * 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
     * word1[i] and word2[i] consist of lowercase letters.
     */

    // Time O(n), Space O(1)
    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int wordIndex1 = 0, wordIndex2 = 0, arrayIndex1 = 0 , arrayIndex2 = 0;

        while(arrayIndex1 < word1.length && arrayIndex2 < word2.length) {
            if (word1[arrayIndex1].charAt(wordIndex1) != word2[arrayIndex2].charAt(wordIndex2)) {
                return false;
            }
            wordIndex1++;
            wordIndex2++;

            if (wordIndex1 == word1[arrayIndex1].length()) {
                wordIndex1 = 0;
                arrayIndex1++;
            }

            if (wordIndex2 == word2[arrayIndex2].length()) {
                wordIndex2 = 0;
                arrayIndex2++;
            }
        }

        return arrayIndex1 == word1.length && arrayIndex2 == word2.length;
    }

    public static void main(String[] args) {
        String[] word11 = {"ab", "c"};
        String[] word12 = {"a", "bc"};
        String[] word21 = {"a", "cb"};
        String[] word22 = {"ab", "c"};
        String[] word31 = {"abc", "d", "defg"};
        String[] word32 = {"abcddefg"};

        System.out.println(arrayStringsAreEqual(word11, word12));
        System.out.println(arrayStringsAreEqual(word21, word22));
        System.out.println(arrayStringsAreEqual(word31, word32));
    }
}
