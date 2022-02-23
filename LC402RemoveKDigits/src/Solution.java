import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Solution {
    /**
     * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer
     * after removing k digits from num.
     *
     *
     *
     * Example 1:
     *
     * Input: num = "1432219", k = 3
     * Output: "1219"
     * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
     * Example 2:
     *
     * Input: num = "10200", k = 1
     * Output: "200"
     * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
     * Example 3:
     *
     * Input: num = "10", k = 2
     * Output: "0"
     * Explanation: Remove all the digits from the number and it is left with nothing which is 0.
     *
     *
     * Constraints:
     *
     * 1 <= k <= num.length <= 10^5
     * num consists of only digits.
     * num does not have any leading zeros except for the zero itself.
     */

    public static String removeKdigits(String num, int k) {
        Deque<Character> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (char c: num.toCharArray()) {
            while (k > 0 && !deque.isEmpty() && deque.peekLast() > c) {
                deque.pollLast();
                k--;
            }
            deque.offer(c);
        }

        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }

        while (!deque.isEmpty() && deque.peekFirst() == '0') {
            deque.pollFirst();
        }

        if (deque.isEmpty()) {
            return "0";
        }

        while (!deque.isEmpty()) {
            sb.append(deque.pollFirst());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeKdigits("1432219", 3));
        System.out.println(removeKdigits("10200", 1));
        System.out.println(removeKdigits("10", 2));
    }
}
