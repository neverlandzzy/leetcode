import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Solution {
    /**
     * Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or
     * more conversions.
     *
     * In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
     *
     * Return true if and only if you can transform str1 into str2.
     *
     *
     *
     * Example 1:
     *
     * Input: str1 = "aabcc", str2 = "ccdee"
     * Output: true
     * Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
     *
     * Example 2:
     *
     * Input: str1 = "leetcode", str2 = "codeleet"
     * Output: false
     * Explanation: There is no way to transform str1 to str2.
     *
     *
     * Constraints:
     *
     * 1 <= str1.length == str2.length <= 10^4
     * str1 and str2 contain only lowercase English letters.
     */

    public static boolean canConvert(String str1, String str2) {

        if (str1.length() != str2.length()) {
            return false;
        }

        if (str1.equals(str2)) {
            return true;
        }

        Map<Character, Character> map = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);

            if (map.getOrDefault(c1, c2) != c2) {
                return false;
            }
            map.put(c1, c2);
        }

        // 特殊情况：str1和str2中不同字符的个数要小于26，因为当str1和str2在转换过程中出现相同字符时，需要一个额外的字符作为tmp
        //         当str2中用尽26个字符时，无法完成交互。参考第三个测试例子
        return new HashSet<>(map.values()).size() < 26;
    }

    public static void main(String[] args) {
        System.out.println(canConvert("aabcc", "ccdee"));
        System.out.println(canConvert("leetcode", "codeleet"));
        System.out.println(canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"));
    }
}
