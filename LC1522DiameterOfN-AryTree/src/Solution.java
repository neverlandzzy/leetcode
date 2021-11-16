public class Solution {

    /**
     * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
     *
     * The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or may not pass through the root.
     *
     * (Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value.)
     *
     *
     *
     * Example 1:
     *
     * Input: root = [1,null,3,2,4,null,5,6]
     * Output: 3
     * Explanation: Diameter is shown in red color.
     *
     *
     * Example 2:
     *
     * Input: root = [1,null,2,null,3,4,null,5,null,6]
     * Output: 4
     *
     *
     * Example 3:
     *
     * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * Output: 7
     *
     *
     * Constraints:
     *
     * The depth of the n-ary tree is less than or equal to 1000.
     * The total number of nodes is between [1, 10^4].
     */

    // Similar to LC124, LC543
    public static int diameter(Node root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        helper(root, max);

        return max[0];
    }

    private static int helper(Node root, int[] max) {
        if (root == null) {
            return 0;
        }

        int maxDepth1 = 0;
        int maxDepth2 = 0;

        for (Node child: root.children) {
            int depth = helper(child, max);
            if (depth > maxDepth1) {
                maxDepth2 = maxDepth1;
                maxDepth1 = depth;
            } else if (depth > maxDepth2) {
                maxDepth2 = depth;
            }
        }

        max[0] = Math.max(max[0], maxDepth1 + maxDepth2);

        return maxDepth1 + 1;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.children.add(node2);
        node1.children.add(node4);
        node1.children.add(node3);

        node3.children.add(node5);
        node3.children.add(node6);

        System.out.println(diameter(node1));
    }
}
