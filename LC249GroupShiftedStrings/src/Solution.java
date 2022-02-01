import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution {
    /**
     * We can shift a string by shifting each of its letters to its successive letter.
     *
     * For example, "abc" can be shifted to be "bcd".
     * We can keep shifting the string to form a sequence.
     *
     * For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
     * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer
     * in any order.
     *
     * Example 1:
     *
     * Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
     * Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]
     *
     * Example 2:
     *
     * Input: strings = ["a"]
     * Output: [["a"]]
     *
     *
     * Constraints:
     *
     * 1 <= strings.length <= 200
     * 1 <= strings[i].length <= 50
     * strings[i] consists of lowercase English letters.
     */

    public static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s: strings) {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < s.length(); i++) {
                int diff = (s.charAt(i) - s.charAt(i-1) + 26) % 26;
                sb.append(diff).append("_");
            }

            if (!map.containsKey(sb.toString())) {
                map.put(sb.toString(), new ArrayList<>());
            }

            map.get(sb.toString()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] test1 = {"abc","bcd","acef","xyz","az","ba","a","z"};
        String[] test2 = {"az"};
        System.out.println(groupStrings(test1));
        System.out.println(groupStrings(test2));
    }
}
