import java.util.Stack;

public class Solution {
    /**
     * Given two integer arrays pushed and popped each with distinct values, return true if this could have been
     * the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.
     *
     *
     * Example 1:
     *
     * Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * Output: true
     * Explanation: We might do the following sequence:
     * push(1), push(2), push(3), push(4),
     * pop() -> 4,
     * push(5),
     * pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     *
     * Example 2:
     *
     * Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * Output: false
     * Explanation: 1 cannot be popped before 2.
     *
     *
     * Constraints:
     *
     * 1 <= pushed.length <= 1000
     * 0 <= pushed[i] <= 1000
     * All the elements of pushed are unique.
     * popped.length == pushed.length
     * popped is a permutation of pushed.
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int n = pushed.length;

        for (int p: pushed) {
            stack.push(p);

            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }

        return i == n;
    }

    public static void main(String[] args) {
        int[] pushed1 = {1, 2, 3, 4, 5};
        int[] popped1 = {4, 5, 3, 2, 1};
        int[] pushed2 = {1, 2, 3, 4, 5};
        int[] popped2 = {4, 3, 5, 1, 2};
        int[] pushed3 = {2, 1, 0};
        int[] popped3 = {1, 2, 0};
        System.out.println(validateStackSequences(pushed1, popped1));
        System.out.println(validateStackSequences(pushed2, popped2));
        System.out.println(validateStackSequences(pushed3, popped3));
    }
}
