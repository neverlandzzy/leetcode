import java.util.*;

public class Solution {
    /**
     * A valid encoding of an array of words is any reference string s and array of indices indices such that:
     *
     * words.length == indices.length
     * The reference string s ends with the '#' character.
     * For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character
     * is equal to words[i].
     * Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.
     *
     *
     * Example 1:
     *
     * Input: words = ["time", "me", "bell"]
     * Output: 10
     * Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
     * words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
     * words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
     * words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
     *
     * Example 2:
     *
     * Input: words = ["t"]
     * Output: 2
     * Explanation: A valid encoding would be s = "t#" and indices = [0].
     *
     *
     * Constraints:
     *
     * 1 <= words.length <= 2000
     * 1 <= words[i].length <= 7
     * words[i] consists of only lowercase letters.
     */

    // Solution 1: Trie - Time: w1 + w2 + ... + wn
    static class TrieNode {
        Map<Character, TrieNode> children;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public static int minimumLengthEncoding(String[] words) {
        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> map = new HashMap<>();
        int result = 0;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode cur = root;
            for (int j = word.length() - 1; j >= 0; j--) {
                char c = word.charAt(j);
                if (!cur.children.containsKey(c)) {
                    cur.children.put(c, new TrieNode());
                }
                cur = cur.children.get(c);
            }

            map.put(cur, i);
        }

        for (TrieNode node: map.keySet()) {
            if (node.children.isEmpty()) {
                result += words[map.get(node)].length() + 1;
            }
        }

        return result;
    }

    // Solution 2: Time: w1^2 + w2^2 + ... + wn^2 --- 1 <= words[i].length <= 7
//    public static int minimumLengthEncoding(String[] words) {
//        Set<String> set = new HashSet<>(Arrays.asList(words));
//        int result = 0;
//        for (String word: words) {
//            for (int i = 1; i < word.length(); i++) {
//                set.remove(word.substring(i));
//            }
//        }
//
//        for (String word: set) {
//            result += word.length() + 1;
//        }
//
//        return result;
//    }

    public static void main(String[] args) {
        String[] test1 = {"time", "me", "bell"};
        String[] test2 = {"t"};

        System.out.println(minimumLengthEncoding(test1));
        System.out.println(minimumLengthEncoding(test2));
    }
}
