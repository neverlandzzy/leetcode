public class Solution {
    /**
     * Given a string s of '(' , ')' and lowercase English characters.
     *
     * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions )
     * so that the resulting parentheses string is valid and return any valid string.
     *
     * Formally, a parentheses string is valid if and only if:
     *
     * It is the empty string, contains only lowercase characters, or
     * It can be written as AB (A concatenated with B), where A and B are valid strings, or
     * It can be written as (A), where A is a valid string.
     *
     *
     * Example 1:
     *
     * Input: s = "lee(t(c)o)de)"
     * Output: "lee(t(c)o)de"
     * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
     * Example 2:
     *
     * Input: s = "a)b(c)d"
     * Output: "ab(c)d"
     * Example 3:
     *
     * Input: s = "))(("
     * Output: ""
     * Explanation: An empty string is also valid.
     * Example 4:
     *
     * Input: s = "(a(b(c)d)"
     * Output: "a(b(c)d)"
     *
     *
     * Constraints:
     *
     * 1 <= s.length <= 105
     * s[i] is either'(' , ')', or lowercase English letter.
     */

    public static String minRemoveToMakeValid(String s) {
        StringBuilder sb = new StringBuilder();
        int counter = 0;

        for (char c: s.toCharArray()) {
            if (c != '(' && c != ')') {
                sb.append(c);
            } else if (c == '(') {
                sb.append(c);
                counter++;
            } else {
                if (counter > 0) {
                    sb.append(c);
                    counter--;
                }
            }
        }

        counter = 0;

        for (int i = sb.length() - 1; i >= 0; i--) {
            char c = sb.charAt(i);
            if (c == ')') {
                counter++;
            } else if (c == '(') {
                if (counter > 0) {
                    counter--;
                } else {
                    sb.deleteCharAt(i);
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("a)b(c)d"));
        System.out.println(minRemoveToMakeValid("))(("));
        System.out.println(minRemoveToMakeValid("(a(b(c)d)"));
    }
}
