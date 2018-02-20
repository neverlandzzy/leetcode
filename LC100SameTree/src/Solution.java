
public class Solution {
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
        	return true;
        }
        
        if (p == null || q == null || p.val != q.val) {
        	return false;
        }
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
    
    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(2);
 		TreeNode node4 = new TreeNode(3);
 		TreeNode node5 = new TreeNode(3);
 		TreeNode node6 = new TreeNode(4);
 		TreeNode node7 = new TreeNode(4);
 		TreeNode node8 = node1;
 		
 		/*
 		 * tree:
 		 *  	    1
 		 *        /   \
 		 *   	 /     \
 		 *  	2       2
 		 *	   / \     / \
 		 *	  3   4   4   3
 		 *         
 		 *          
 		 */
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node6;
 		node3.right = node5;
 		node3.left  = node7;
 		
 		System.out.println(isSameTree(node1, node8));
 	}
}
