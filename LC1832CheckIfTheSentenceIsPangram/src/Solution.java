public class Solution {
    /**
     * A pangram is a sentence where every letter of the English alphabet appears at least once.
     *
     * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.
     *
     *
     *
     * Example 1:
     *
     * Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
     * Output: true
     * Explanation: sentence contains at least one of every letter of the English alphabet.
     * Example 2:
     *
     * Input: sentence = "leetcode"
     * Output: false
     *
     *
     * Constraints:
     *
     * 1 <= sentence.length <= 1000
     * sentence consists of lowercase English letters.
     */

    private static boolean checkIfPangram(String sentence) {
        int[] mem = new int[26];
        int count = 0;

        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);

            if (mem[c - 'a'] == 0) {
                mem[c - 'a'] = 1;
                count++;
            }
        }

        return count == 26;
    }

    public static void main(String[] args) {
        System.out.println(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
        System.out.println(checkIfPangram("leetcode"));
    }

}
