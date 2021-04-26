public class Solution {
    /**
     * Implement function ToLowerCase() that has a string parameter str, and returns the same string in lowercase.
     *
     *
     *
     * Example 1:
     *
     * Input: "Hello"
     * Output: "hello"
     * Example 2:
     *
     * Input: "here"
     * Output: "here"
     * Example 3:
     *
     * Input: "LOVELY"
     * Output: "lovely"
     */

    public static String toLowerCase(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }

        StringBuilder sb = new StringBuilder();

        for (char c: str.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                c += 32;
            }

            sb.append(c);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(toLowerCase("Hello"));
        System.out.println(toLowerCase("here"));
        System.out.println(toLowerCase("LOVELY"));
        System.out.println(toLowerCase("Hell+12o"));
    }
}
