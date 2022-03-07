import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {

    /**
     * Given the root of an n-ary tree, return the preorder traversal of its nodes' values.
     *
     * Nary-Tree input serialization is represented in their level order traversal. Each group of children
     * is separated by the null value (See examples)
     *
     * Example 1:
     *
     * Input: root = [1,null,3,2,4,null,5,6]
     * Output: [1,3,5,6,2,4]
     *
     * Example 2:
     *
     * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * Output: [1,2,3,6,7,11,14,4,8,12,5,9,13,10]
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [0, 10^4].
     * 0 <= Node.val <= 10^4
     * The height of the n-ary tree is less than or equal to 1000.
     *
     *
     * Follow up: Recursive solution is trivial, could you do it iteratively?
     */

    // Solution 1: Recursion
    /*
    public static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();

        preorderUtil(result, root);
        return result;
    }

    private static void preorderUtil(List<Integer> result, Node root) {
        if (root == null) {
            return;
        }

        result.add(root.val);
        for (Node node: root.children) {
            preorderUtil(result, node);
        }
    }
    */

    // Solution 2: Iteration
    public static List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        if (root == null) {
            return result;
        }

        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            result.add(node.val);

            for (int i = node.children.size() - 1; i >= 0; i--) {
                stack.push(node.children.get(i));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.children.add(node3);
        node1.children.add(node2);
        node1.children.add(node4);

        node3.children.add(node5);
        node3.children.add(node6);

        System.out.println(preorder(node1));
    }
}
