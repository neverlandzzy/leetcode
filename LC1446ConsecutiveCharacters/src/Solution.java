public class Solution {

    /**
     * Given a string s, the power of the string is the maximum length of a non-empty substring that contains only one unique character.
     *
     * Return the power of the string.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "leetcode"
     * Output: 2
     * Explanation: The substring "ee" is of length 2 with the character 'e' only.
     * Example 2:
     *
     * Input: s = "abbcccddddeeeeedcba"
     * Output: 5
     * Explanation: The substring "eeeee" is of length 5 with the character 'e' only.
     * Example 3:
     *
     * Input: s = "triplepillooooow"
     * Output: 5
     * Example 4:
     *
     * Input: s = "hooraaaaaaaaaaay"
     * Output: 11
     * Example 5:
     *
     * Input: s = "tourist"
     * Output: 1
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 500
     * s contains only lowercase English letters.
     */

    // Solution 1:
    public static int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int power = 1;
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                count = 1;
            }

            power = Math.max(power, count);
        }

        return power;
    }

    // Solution 2:
    /*
    public static int maxPower(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int i = 0;
        int j = 1;
        int maxPower = 1;

        while (j < n) {
            if (s.charAt(i) != s.charAt(j)) {
                i++;
                j++;
            } else {
                while (j < n && s.charAt(i) == s.charAt(j)) {
                    j++;
                }
                maxPower = Math.max(maxPower, j - i);
                i = j;
                j++;
            }
        }

        return maxPower;
    }
    */
    public static void main(String[] args) {
        System.out.println(maxPower("leetcode"));
        System.out.println(maxPower("abbcccddddeeeeedcba"));
        System.out.println(maxPower("triplepillooooow"));
        System.out.println(maxPower("hooraaaaaaaaaaay"));
        System.out.println(maxPower("tourist"));
    }
}
