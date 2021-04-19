public class Solution {

    /**
     * Given a valid (IPv4) IP address, return a defanged version of that IP address.
     *
     * A defanged IP address replaces every period "." with "[.]".
     *
     * Example 1:
     *
     * Input: address = "1.1.1.1"
     * Output: "1[.]1[.]1[.]1"
     * Example 2:
     *
     * Input: address = "255.100.50.0"
     * Output: "255[.]100[.]50[.]0"
     *
     *
     * Constraints:
     *
     * The given address is a valid IPv4 address.
     */

    private static String defangIPaddr(String address) {
        // Solution 1:
        // return address.replaceAll("\\.", "[.]");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.') {
                sb.append("[.]");
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(defangIPaddr("1.1.1.1"));
        System.out.println(defangIPaddr("255.100.50.0"));
    }
}
