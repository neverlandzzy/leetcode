import java.util.*;

public class Solution {
    /**
     * Given two binary search trees root1 and root2, return a list containing all the integers from both trees sorted in ascending order.
     *
     * Example 1:
     *
     *
     * Input: root1 = [2,1,4], root2 = [1,0,3]
     * Output: [0,1,1,2,3,4]
     * Example 2:
     *
     *
     * Input: root1 = [1,null,8], root2 = [8,1]
     * Output: [1,1,8,8]
     *
     *
     * Constraints:
     *
     * The number of nodes in each tree is in the range [0, 5000].
     * -10^5 <= Node.val <= 10^5
     */

    // Time: O(m + n)
    public static List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> result = new ArrayList<>();

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        TreeNode node1 = root1;
        TreeNode node2 = root2;

        while (node1 != null) {
            stack1.push(node1);
            node1 = node1.left;
        }

        while (node2 != null) {
            stack2.push(node2);
            node2 = node2.left;
        }

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (stack2.isEmpty() || (!stack1.isEmpty() && stack1.peek().val < stack2.peek().val)) {
                node1 = stack1.pop();
                result.add(node1.val);
                node1 =node1.right;
                while (node1 != null) {
                    stack1.push(node1);
                    node1 = node1.left;
                }
            } else {
                node2 = stack2.pop();
                result.add(node2.val);
                node2 =node2.right;
                while (node2 != null) {
                    stack2.push(node2);
                    node2 = node2.left;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);

        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;

        node4.left = node5;
        node4.right = node6;

        System.out.println(getAllElements(node1, node4));
    }
}
