import java.util.Arrays;

public class Solution {

    /**
     * You are given a string s and an integer k. You can choose one of the first k letters of s and append it at the end of the string..
     *
     * Return the lexicographically smallest string you could have after applying the mentioned step any number of moves.
     *
     *
     *
     * Example 1:
     *
     * Input: s = "cba", k = 1
     * Output: "acb"
     * Explanation:
     * In the first move, we move the 1st character 'c' to the end, obtaining the string "bac".
     * In the second move, we move the 1st character 'b' to the end, obtaining the final result "acb".
     * Example 2:
     *
     * Input: s = "baaca", k = 3
     * Output: "aaabc"
     * Explanation:
     * In the first move, we move the 1st character 'b' to the end, obtaining the string "aacab".
     * In the second move, we move the 3rd character 'c' to the end, obtaining the final result "aaabc".
     *
     *
     * Constraints:
     *
     * 1 <= k <= s.length <= 1000
     * s consist of lowercase English letters.
     */

    // 当k = 1 时，相当于只允许对s进行rotation，当k > 1时，相当于允许对s中的所有字符全排列
    public static String orderlyQueue(String s, int k) {
        String result = s;

        if (k == 1) {
            for (int i = 0; i < s.length(); i++) {
                String temp = s.substring(i) + s.substring(0, i);
                if (temp.compareTo(result) < 0) {
                    result = temp;
                }
            }
        } else {
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            result = new String(charArray);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(orderlyQueue("cba", 1));
        System.out.println(orderlyQueue("baaca", 3));
    }
}
