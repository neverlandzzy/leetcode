public class Solution {

    /**
     * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
     *
     * Each node will have a reference to its parent node. The definition for Node is below:
     *
     * class Node {
     *     public int val;
     *     public Node left;
     *     public Node right;
     *     public Node parent;
     * }
     * According to the definition of LCA on Wikipedia: "The lowest common ancestor of two nodes p and q in a tree T is the lowest
     * node that has both p and q as descendants (where we allow a node to be a descendant of itself)."
     *
     * Example 1:
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
     * Output: 3
     * Explanation: The LCA of nodes 5 and 1 is 3.
     *
     * Example 2:
     *
     * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
     * Output: 5
     * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
     *
     * Example 3:
     *
     * Input: root = [1,2], p = 1, q = 2
     * Output: 1
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree is in the range [2, 10^5].
     * -10^9 <= Node.val <= 10^9
     * All Node.val are unique.
     * p != q
     * p and q exist in the tree.
     */

    public static Node lowestCommonAncestor(Node p, Node q) {
        if (p == q) {
            return p;
        }

        int depthP = getDepth(p);
        int depthQ = getDepth(q);

        while (depthP > depthQ) {
            p = p.parent;
            depthP--;
        }

        while (depthP < depthQ) {
            q = q.parent;
            depthQ--;
        }

        while (p != q) {
            p = p.parent;
            q = q.parent;
        }

        return p;
    }

    private static int getDepth(Node node) {
        int depth = 0;

        while (node != null) {
            depth++;
            node = node.parent;
        }

        return depth;
    }

    public static void main(String[] args) {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node3.left = node5;
        node3.right = node1;
        node5.left = node6;
        node5.right = node2;
        node2.left = node7;
        node2.right = node4;
        node1.left = node0;
        node1.right = node8;

        node5.parent = node3;
        node1.parent = node3;
        node6.parent = node5;
        node2.parent = node5;
        node7.parent = node2;
        node4.parent = node2;
        node0.parent = node1;
        node8.parent = node1;

        Node result = lowestCommonAncestor(node5, node4);
        System.out.println(result.val);
    }
}
