public class Solution {

    /**
     * You are given the root node of a binary search tree (BST) and a value to insert into the tree.
     * Return the root node of the BST after the insertion. It is guaranteed that the new value does
     * not exist in the original BST.
     *
     * Notice that there may exist multiple valid ways for the insertion, as long as the tree remains
     * a BST after insertion. You can return any of them.
     *
     *
     * Example 1:
     *
     *
     * Input: root = [4,2,7,1,3], val = 5
     * Output: [4,2,7,1,3,5]
     * Explanation: Another accepted tree is:
     *
     * Example 2:
     *
     * Input: root = [40,20,60,10,30,50,70], val = 25
     * Output: [40,20,60,10,30,50,70,null,null,25]
     *
     * Example 3:
     *
     * Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
     * Output: [4,2,7,1,3,5]
     *
     *
     * Constraints:
     *
     * The number of nodes in the tree will be in the range [0, 104].
     * -108 <= Node.val <= 108
     * All the values Node.val are unique.
     * -108 <= val <= 108
     * It's guaranteed that val does not exist in the original BST.
     */

    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        TreeNode pre = root;
        TreeNode cur = root;
        while (cur != null) {
            pre = cur;
            if (cur.val > val) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        if (pre.val > val) {
            pre.left = new TreeNode(val);
        } else {
            pre.right = new TreeNode(val);
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);

        node4.left = node2;
        node4.right = node7;
        node2.left = node1;
        node2.right = node3;

        TreeNode result = insertIntoBST(node4, 5);
        System.out.println(result.right.left.val);
    }
}
