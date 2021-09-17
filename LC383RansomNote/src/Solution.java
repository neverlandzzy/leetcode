public class Solution {

    /**
     * Given two stings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.
     *
     * Each letter in magazine can only be used once in ransomNote.
     *
     *
     *
     * Example 1:
     *
     * Input: ransomNote = "a", magazine = "b"
     * Output: false
     * Example 2:
     *
     * Input: ransomNote = "aa", magazine = "ab"
     * Output: false
     * Example 3:
     *
     * Input: ransomNote = "aa", magazine = "aab"
     * Output: true
     *
     *
     * Constraints:
     *
     * 1 <= ransomNote.length, magazine.length <= 105
     * ransomNote and magazine consist of lowercase English letters.
     */

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] map = new int[26];

        for (char c: magazine.toCharArray()) {
            map[c - 'a']++;
        }

        for (char c: ransomNote.toCharArray()) {
            if (map[c - 'a'] == 0) {
                return false;
            }

            map[c - 'a']--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(canConstruct("a", "b"));
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("aa", "aab"));
    }
}
