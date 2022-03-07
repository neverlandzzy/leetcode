import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Solution {
    /**
     * Given a root of an N-ary tree, return a deep copy (clone) of the tree.
     *
     * Each node in the n-ary tree contains a val (int) and a list (List[Node]) of its children.
     *
     * class Node {
     *     public int val;
     *     public List<Node> children;
     * }
     *
     * Nary-Tree input serialization is represented in their level order traversal, each group of children
     * is separated by the null value (See examples).
     *
     * Example 1:
     *
     * Input: root = [1,null,3,2,4,null,5,6]
     * Output: [1,null,3,2,4,null,5,6]
     * Example 2:
     *
     * Input: root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     * Output: [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
     *
     *
     * Constraints:
     *
     * The depth of the n-ary tree is less than or equal to 1000.
     * The total number of nodes is between [0, 10^4].
     *
     *
     * Follow up: Can your solution work for the graph problem?
     */

    // Same as LC133
    public static Node cloneTree(Node root) {
        if (root == null) {
            return root;
        }

        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        Node newRoot = new Node(root.val);
        map.put(root, newRoot);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (Node child: node.children) {
                if (!map.containsKey(child)) {
                    Node copy = new Node(child.val);
                    map.put(child, copy);
                    queue.offer(child);
                }

                map.get(node).children.add(map.get(child));
            }
        }

        return newRoot;
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.children.add(node2);
        node1.children.add(node3);
        node1.children.add(node4);
        node3.children.add(node5);
        node3.children.add(node6);

        Node newNode = cloneTree(node1);

        System.out.println(newNode.val);
        System.out.println(newNode.children);
    }
}
