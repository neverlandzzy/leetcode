public class Solution {

    /**
     * In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order.
     * The order of the alphabet is some permutation of lowercase letters.
     *
     * Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are
     * sorted lexicographically in this alien language.
     *
     *
     *
     * Example 1:
     *
     * Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
     * Output: true
     * Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
     *
     * Example 2:
     *
     * Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
     * Output: false
     * Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
     *
     * Example 3:
     *
     * Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
     * Output: false
     * Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical
     * rules "apple" > "app", because 'l' > '∅', where '∅' is defined as the blank character which is less than any other character (More info).
     *
     *
     * Constraints:
     *
     * 1 <= words.length <= 100
     * 1 <= words[i].length <= 20
     * order.length == 26
     * All characters in words[i] and order are English lowercase letters.
     */

    public static boolean isAlienSorted(String[] words, String order) {
        int[] map = new int[26];

        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);

            map[c - 'a'] = i;
        }

        for (int j = 0; j < words.length - 1; j++) {
            String word1 = words[j];
            String word2 = words[j + 1];
            //System.out.println("word1: " + word1 + " word2: " + word2);

            int i = 0;
            boolean currentRound = false;

            while (i < word1.length() && i < word2.length()) {
                char c1 = word1.charAt(i);
                char c2 = word2.charAt(i);

                if (c1 == c2) {
                    i++;
                    continue;
                }

                if (map[c1 - 'a'] > map[c2 - 'a']) {
                    return false;
                } else {
                    currentRound = true;
                    break;
                }
            }

            if (!currentRound && word1.length() > word2.length()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String[] words1 = {"hello","leetcode"};
        String[] words2 = {"word","world","row"};
        String[] words3 = {"apple","app"};
        String[] words4 = {"fxasxpc","dfbdrifhp","nwzgs","cmwqriv","ebulyfyve","miracx","sxckdwzv","dtijzluhts","wwbmnge","qmjwymmyox"};

        System.out.println(isAlienSorted(words1, "hlabcdefgijkmnopqrstuvwxyz"));
        System.out.println(isAlienSorted(words2, "worldabcefghijkmnpqstuvxyz"));
        System.out.println(isAlienSorted(words3, "abcdefghijklmnopqrstuvwxyz"));
        System.out.println(isAlienSorted(words4, "zkgwaverfimqxbnctdplsjyohu"));
    }
}
