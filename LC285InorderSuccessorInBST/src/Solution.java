import java.util.Stack;


public class Solution {
	/*
	 * Given a binary search tree and a node in it, find the in-order successor of that 
	 * node in the BST.
	 * 
	 * Note: If the given node has no in-order successor in the tree, return null.
	 */
	
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    	
    	TreeNode successor = null;
        
        while (root != null) {
        	if (p.val < root.val) {
        		successor = root;
        		root = root.left;
        	} else {
        		root = root.right;
        	}
        }
        
        return successor;
    }
    
    // Solution 2: iteration
    /*
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode more = null;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode node = root.left;
        
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.val > p.val && more == null) {
                more = cur;
                break;
            }            
            
            cur = cur.right;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        }
        
        return more;
    }
    */
	public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(9);
 		TreeNode node2 = new TreeNode(3);
 		TreeNode node3 = new TreeNode(20);
 		TreeNode node4 = new TreeNode(7);
 		TreeNode node5 = new TreeNode(15);
 		TreeNode node6 = new TreeNode(2);

 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node6;
 		node2.right = node4;
 		node3.left  = node5;
 		
 		System.out.println(inorderSuccessor(node1, node5).val);
 	}
}
