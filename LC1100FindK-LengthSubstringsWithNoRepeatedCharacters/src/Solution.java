public class Solution {

    /**
     * Given a string S, return the number of substrings of length K with no repeated characters.
     *
     *
     *
     * Example 1:
     *
     * Input: S = "havefunonleetcode", K = 5
     * Output: 6
     * Explanation:
     * There are 6 substrings they are : 'havef','avefu','vefun','efuno','etcod','tcode'.
     * Example 2:
     *
     * Input: S = "home", K = 5
     * Output: 0
     * Explanation:
     * Notice K can be larger than the length of S. In this case is not possible to find any substring.
     *
     *
     * Note:
     *
     * 1 <= S.length <= 10^4
     * All characters of S are lowercase English letters.
     * 1 <= K <= 10^4
     */

    public static int numKLenSubstrNoRepeats(String S, int K) {
        int count = 0;

        int i = 0;
        int j = 0;
        int n = S.length();
        int[] map = new int[26];

        if (n < K) {
            return 0;
        }

        while(i < n && j < n) {
            map[S.charAt(j) - 'a'] ++;
            while (i < j && map[S.charAt(j) - 'a'] > 1) {
                map[S.charAt(i) - 'a']--;
                i++;
            }

            if (j - i + 1 == K) {
                count++;
                map[S.charAt(i) - 'a']--;
                i++;
            }

            j++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(numKLenSubstrNoRepeats("havefunonleetcode", 5));
        System.out.println(numKLenSubstrNoRepeats("home", 5));
    }
}
