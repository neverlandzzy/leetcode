import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    /**
     * A string s is called good if there are no two different characters in s that have the same frequency.
     *
     * Given a string s, return the minimum number of characters you need to delete to make s good.
     *
     * The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency
     * of 'a' is 2, while the frequency of 'b' is 1.
     *
     * Example 1:
     *
     * Input: s = "aab"
     * Output: 0
     * Explanation: s is already good.
     *
     * Example 2:
     *
     * Input: s = "aaabbbcc"
     * Output: 2
     * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
     * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
     *
     * Example 3:
     *
     * Input: s = "ceabaacb"
     * Output: 2
     * Explanation: You can delete both 'c's resulting in the good string "eabaab".
     * Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 10^5
     * s contains only lowercase English letters.
     */


    // Solution 1: Time: O(n + k^2) - k is the maximum possible number of distinct characters in s, which is 26 here
//    public static int minDeletions(String s) {
//        int[] frequencies = new int[26];
//
//        for (char c: s.toCharArray()) {
//            frequencies[c - 'a']++;
//        }
//
//        int count = 0;
//        Set<Integer> set = new HashSet<>();
//
//        for (int i = 0; i < 26; i++) {
//            while (frequencies[i] > 0 && set.contains(frequencies[i])) {
//                frequencies[i]--;
//                count++;
//            }
//
//            set.add(frequencies[i]);
//        }
//
//        return count;
//    }

    // Solution 2: Time: O(n + k * logk) - k is the maximum possible number of distinct characters in s, which is 26 here
    public static int minDeletions(String s) {
        int[] frequencies = new int[26];

        for (char c: s.toCharArray()) {
            frequencies[c - 'a']++;
        }

        Arrays.sort(frequencies);

        int count = 0;
        int maxFreqAllowed = s.length();

        for (int i = 25; i >= 0 && frequencies[i] > 0; i--) {
            if (frequencies[i] > maxFreqAllowed) {
                count += frequencies[i] - maxFreqAllowed;
                frequencies[i] = maxFreqAllowed;
            }

            maxFreqAllowed = Math.max(0, frequencies[i] - 1);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(minDeletions("aab"));
        System.out.println(minDeletions("aaabbbcc"));
        System.out.println(minDeletions("ceabaacb"));
    }
}
