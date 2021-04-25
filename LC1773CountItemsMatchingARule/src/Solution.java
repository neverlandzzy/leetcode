import java.util.Arrays;
import java.util.List;

public class Solution {
    /**
     * You are given an array items, where each items[i] = [typei, colori, namei] describes the type, color,
     * and name of the ith item. You are also given a rule represented by two strings, ruleKey and ruleValue.
     *
     * The ith item is said to match the rule if one of the following is true:
     *
     * ruleKey == "type" and ruleValue == typei.
     * ruleKey == "color" and ruleValue == colori.
     * ruleKey == "name" and ruleValue == namei.
     * Return the number of items that match the given rule.
     *
     *
     *
     * Example 1:
     *
     * Input: items = [["phone","blue","pixel"],["computer","silver","lenovo"],["phone","gold","iphone"]], ruleKey = "color", ruleValue = "silver"
     * Output: 1
     * Explanation: There is only one item matching the given rule, which is ["computer","silver","lenovo"].
     * Example 2:
     *
     * Input: items = [["phone","blue","pixel"],["computer","silver","phone"],["phone","gold","iphone"]], ruleKey = "type", ruleValue = "phone"
     * Output: 2
     * Explanation: There are only two items matching the given rule, which are ["phone","blue","pixel"] and ["phone","gold","iphone"]. Note that the item ["computer","silver","phone"] does not match.
     *
     *
     * Constraints:
     *
     * 1 <= items.length <= 104
     * 1 <= typei.length, colori.length, namei.length, ruleValue.length <= 10
     * ruleKey is equal to either "type", "color", or "name".
     * All strings consist only of lowercase letters.
     */

    public static int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0;
        int index = 0;

        switch (ruleKey) {
            case "type":
                index = 0;
                break;
            case "color":
                index = 1;
                break;
            case "name":
                index = 2;
                break;
        }

        for (List<String> list: items) {
            if (list.get(index).equals(ruleValue)) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        List<List<String>> items = Arrays.asList(Arrays.asList("phone","blue","pixel"), Arrays.asList("computer","silver","lenovo"), Arrays.asList("phone","gold","iphone"));
        System.out.println(countMatches(items, "color", "silver"));
        System.out.println(countMatches(items, "type", "phone"));
    }
}
