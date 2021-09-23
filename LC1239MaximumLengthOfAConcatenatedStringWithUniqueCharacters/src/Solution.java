import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    /**
     * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
     *
     * Return the maximum possible length of s.
     *
     *
     * Example 1:
     *
     * Input: arr = ["un","iq","ue"]
     * Output: 4
     * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
     * Maximum length is 4.
     * Example 2:
     *
     * Input: arr = ["cha","r","act","ers"]
     * Output: 6
     * Explanation: Possible solutions are "chaers" and "acters".
     * Example 3:
     *
     * Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
     * Output: 26
     *
     *
     * Constraints:
     *
     * 1 <= arr.length <= 16
     * 1 <= arr[i].length <= 26
     * arr[i] contains only lower case English letters.
     */

    // Time Complexity: O(2^n)
    private static int count;
    public static int maxLength(List<String> arr) {
        int[] map = new int[26];
        count = 0;
        StringBuilder sb = new StringBuilder();
        helper(arr, map, sb, 0);

        return count;
    }

    private static void helper(List<String> arr, int[] map, StringBuilder sb, int pos) {
        int length = sb.length();

        for (int i = pos; i < arr.size(); i++) {
            String word = arr.get(i);

            if (isValid(word) && isUnique(word, map)) {
                addWordToMap(word, map);
                sb.append(word);
                count = Math.max(count, sb.length());
                helper(arr, map, sb, i + 1);

                removeWordToMap(word, map);
                sb.setLength(length);
            }
        }
    }

    private static boolean isValid(String word) {
        int[] map = new int[26];
        for (char c: word.toCharArray()) {
            if (map[c - 'a'] != 0) {
                return false;
            }
            map[c - 'a']++;
        }

        return true;
    }
    private static boolean isUnique(String word, int[] map) {
        for (char c: word.toCharArray()) {
            if (map[c - 'a'] != 0) {
                return false;
            }
        }

        return true;
    }

    private static void addWordToMap(String word, int[] map) {
        for (char c: word.toCharArray()) {
            map[c - 'a']++;
        }
    }

    private static void removeWordToMap(String word, int[] map) {
        for (char c: word.toCharArray()) {
            map[c - 'a']--;
        }
    }

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("un", "iq", "ue");
        List<String> list2 = Arrays.asList("cha","r","act","ers");
        List<String> list3 = Arrays.asList("abcdefghijklmnopqrstuvwxyz");
        List<String> list4 = Arrays.asList("yy","bkhwmpbiisbldzknpm");

        System.out.println(maxLength(list1));
        System.out.println(maxLength(list2));
        System.out.println(maxLength(list3));
        System.out.println(maxLength(list4));
    }
}
