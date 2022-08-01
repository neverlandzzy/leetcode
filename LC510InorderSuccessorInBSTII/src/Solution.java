public class Solution {
    /**
     * Given a node in a binary search tree, return the in-order successor of that node in the BST. If that node has no in-order
     * successor, return null.
     *
     * The successor of a node is the node with the smallest key greater than node.val.
     *
     * You will have direct access to the node but not to the root of the tree. Each node will have a reference to its parent node.
     * Below is the definition for Node:
     *
     * class Node {
     *     public int val;
     *     public Node left;
     *     public Node right;
     *     public Node parent;
     * }
     *
     *
     * Example 1:
     *
     *
     * Input: tree = [2,1,3], node = 1
     * Output: 2
     * Explanation: 1's in-order successor node is 2. Note that both the node and the return value is of Node type.
     *
     * Example 2:
     *
     *
     * Input: tree = [5,3,6,2,4,null,null,1], node = 6
     * Output: null
     * Explanation: There is no in-order successor of the current node, so the answer is null.
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [1, 10^4].
     * -10^5 <= Node.val <= 10^5
     * All Nodes will have unique values.
     *
     *
     * Follow up: Could you solve it without looking up any of the node's values?
     */

    public static Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        // If node has right child
        if (node.right != null) {
            node = node.right;
            while (node.left != null) {
                node = node.left;
            }
            return node;
        }

        // If node has no right child
        Node successor = node.parent;

        while (successor != null) {
            if (successor.left != null && successor.left == node) {
                return successor;
            }

            node = successor;
            successor = successor.parent;
        }

        return null;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;

        node1.parent = node2;
        node4.parent = node3;
        node2.parent = node3;
        node3.parent = node5;
        node6.parent = node5;

        Node result1 = inorderSuccessor(node6);
        System.out.println(result1 == null ? "null" : result1.val);
        Node result2 = inorderSuccessor(node4);
        System.out.println(result2 == null ? "null" : result2.val);
        Node result3 = inorderSuccessor(node2);
        System.out.println(result3 == null ? "null" : result3.val);
    }
}
