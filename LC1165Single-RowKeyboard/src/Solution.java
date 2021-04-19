import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * There is a special keyboard with all keys in a single row.
     *
     * Given a string keyboard of length 26 indicating the layout of the keyboard (indexed from 0 to 25). Initially, your finger is at index 0. To type a character, you have to move your finger to the index of the desired character. The time taken to move your finger from index i to index j is |i - j|.
     *
     * You want to type a string word. Write a function to calculate how much time it takes to type it with one finger.
     *
     *
     *
     * Example 1:
     *
     * Input: keyboard = "abcdefghijklmnopqrstuvwxyz", word = "cba"
     * Output: 4
     * Explanation: The index moves from 0 to 2 to write 'c' then to 1 to write 'b' then to 0 again to write 'a'.
     * Total time = 2 + 1 + 1 = 4.
     * Example 2:
     *
     * Input: keyboard = "pqrstuvwxyzabcdefghijklmno", word = "leetcode"
     * Output: 73
     *
     *
     * Constraints:
     *
     * keyboard.length == 26
     * keyboard contains each English lowercase letter exactly once in some order.
     * 1 <= word.length <= 104
     * word[i] is an English lowercase letter.
     */

    private static int calculateTime(String keyboard, String word) {
        // Solution 1: Use Map
//        Map<Character, Integer> map = new HashMap<>();
//        for (int i = 0; i < keyboard.length(); i++) {
//            char c = keyboard.charAt(i);
//            map.put(c, i);
//        }
//
//        int time = 0;
//        time += map.get(word.charAt(0));
//
//        for (int i = 1; i < word.length(); i++) {
//            char cur = word.charAt(i);
//            char prev = word.charAt(i - 1);
//
//            time += Math.abs(map.get(cur) - map.get(prev));
//        }
//
//        return time;

        // Solution 2: Faster without map

        int[] map = new int[26];
        for (int i = 0; i < keyboard.length(); i++) {
            map[keyboard.charAt(i) - 'a'] = i;
        }

        int time = 0;
        int prev = 0;

        for (char c: word.toCharArray()) {
            time += Math.abs(map[c - 'a'] - prev);
            prev = map[c - 'a'];
        }

        return time;
    }

    public static void main(String[] args) {
        System.out.println(calculateTime("abcdefghijklmnopqrstuvwxyz", "cba"));
        System.out.println(calculateTime("pqrstuvwxyzabcdefghijklmno", "leetcode"));
    }
}
