public class Solution {
    /**
     * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
     *
     * You can use each character in text at most once. Return the maximum number of instances that can be formed.
     *
     * Example 1:
     *
     * Input: text = "nlaebolko"
     * Output: 1
     * Example 2:
     *
     * Input: text = "loonbalxballpoon"
     * Output: 2
     * Example 3:
     *
     * Input: text = "leetcode"
     * Output: 0
     *
     * Constraints:
     *
     * 1 <= text.length <= 104
     * text consists of lower case English letters only.
     */

    public static int maxNumberOfBalloons(String text) {
        int[] map = new int[26];

        for (char c: text.toCharArray())
        {
            map[c - 'a']++;
        }
        int result = map['b' - 'a'];

        if (map[0] < result) {
            result = map[0];
        }
        if (map['n' - 'a'] < result) {
            result = map['n' - 'a'];
        }
        if (map['l' - 'a'] / 2 < result) {
            result = map['l' - 'a'] / 2;
        }
        if (map['o' - 'a'] / 2 < result) {
            result = map['o' - 'a'] / 2;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(maxNumberOfBalloons("nlaebolko"));
        System.out.println(maxNumberOfBalloons("loonbalxballpoon"));
        System.out.println(maxNumberOfBalloons("leetcode"));
    }
}
