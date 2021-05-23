public class Solution {
    /**
     * In the following, every capital letter represents some hexadecimal digit from 0 to f.
     *
     * The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  For example,
     * "#15c" is shorthand for the color "#1155cc".
     *
     * Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.
     *
     * Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand
     * (that is, it can be represented as some "#XYZ"
     *
     * Example 1:
     * Input: color = "#09f166"
     * Output: "#11ee66"
     * Explanation:
     * The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
     * This is the highest among any shorthand color.
     * Note:
     *
     * color is a string of length 7.
     * color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
     * Any answer which has the same (highest) similarity as the best answer will be accepted.
     * All inputs and outputs should use lowercase letters, and the output is 7 characters.
     */

    private final static String[] shorthands = {"00", "11", "22", "33", "44", "55", "66", "77",
            "88", "99", "aa", "bb", "cc", "dd", "ee", "ff"};

    public static String similarRGB(String color) {
        StringBuilder sb = new StringBuilder("#");
        for (int i = 1; i < color.length(); i += 2) {
            String substring = color.substring(i, i + 2);
            sb.append(findMostSimilarColor(substring));
        }

        return sb.toString();
    }

    private static String findMostSimilarColor(String color) {
        int colorInNumber = Integer.parseInt(color, 16);
        int min = Integer.MAX_VALUE;
        String result = null;

        for (String shorthand: shorthands) {
            int shorthandInNumber = Integer.parseInt(shorthand, 16);
            int similarity = Math.abs(colorInNumber - shorthandInNumber);
            if (similarity < min) {
                min = similarity;
                result = shorthand;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(similarRGB("#09f166"));
    }
}
