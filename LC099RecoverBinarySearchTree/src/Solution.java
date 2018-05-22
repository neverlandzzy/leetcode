
public class Solution {
	/*
	 * Two elements of a binary search tree (BST) are swapped by mistake.
	 * Recover the tree without changing its structure.
	 * 
	 * Note:
	 * A solution using O(n) space is pretty straight forward. 
	 * Could you devise a constant space solution?
	 */
	
	// Solution 1: O(n) space:
	/*
    TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = null;
    
    public void recoverTree(TreeNode root) {
        helper(root);
        
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    
    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        
        helper(root.left);
        if (pre != null && pre.val >= root.val) {
            if (first == null) {
                first = pre;
            } 
            
            // 此处不能有else
            if (first != null) {
                second = root;
            }
        }
        
        pre = root;
        
        helper(root.right);
    }
    */
	
    public static void recoverTree(TreeNode root) {
        
    	if (root == null) {
    		return;
    	}
    	
    	TreeNode curr = root;
       	// prev 与 LC094中的定义不一样
    	TreeNode prev = null;
    	/*
    	 * When they are not consecutive, the first time we meet pre.val > root.val ensure us the first node is the pre node, 
    	 * since root should be traversal ahead of pre, pre should be at least at small as root. 
    	 * The second time we meet pre.val > root.val ensure us the second node is the root node, since we are now looking 
    	 * for a node to replace with out first node, which is found before.
    	 * 
    	 * When they are consecutive, which means the case pre.val > cur.val will appear only once. We need to take care this 
    	 * case without destroy the previous analysis. So the first node will still be pre, and the second will be just set to root. 
    	 * Once we meet this case again, the first node will not be affected.
    	 */
    	TreeNode first = null;
    	TreeNode second = null;
    	
    	while (curr != null) {
    		if (curr.left == null) {
    			if (prev != null && prev.val > curr.val) {
    				if (first == null) {
    					first = prev;
    				}
    				second = curr;
    			}
    			
    			prev = curr;
    			curr = curr.right;
    		} else {
    			// LC094中的prev
    			TreeNode tmpP = curr.left;
    			while (tmpP.right != null && tmpP.right != curr) {
    				tmpP = tmpP.right;
    			}
    			
    			if (tmpP.right == null) {
    				tmpP.right = curr;
    				curr = curr.left;
    			} else {
    				tmpP.right = null;
    				if (prev != null && prev.val > curr.val) {
        				if (first == null) {
        					first = prev;
        				}
        				second = curr;
    				}
    				
        			prev = curr;
        			curr = curr.right;
    			}
    			
    		}
    	}
    	
    	swap(first, second);

    }
    
    private static void swap (TreeNode a, TreeNode b) {
    	if (a == null || b == null) {
    		return;
    	}
    	
    	int tmp = a.val;
    	a.val = b.val;
    	b.val = tmp;
    }
    
    public static void main(String[] args) {
 		TreeNode node1 = new TreeNode(1);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(3);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node5 = new TreeNode(5);
 		TreeNode node6 = new TreeNode(6);
 		TreeNode node7 = new TreeNode(7);
 		
 		/*
 		 * tree:
 		 *  	    5
 		 *        /   \
 		 *   	 /     \
 		 *  	6       7
 		 *	   / \     /
 		 *	  1   3   2
 		 *         \
 		 *          4
 		 */
 	
 		node5.left  = node6;
 		node5.right = node7;
 		node6.left  = node1;
 		node6.right = node3;
 		node3.right = node4;
 		node7.left  = node2;
 		
 		recoverTree(node5);
	}
}	
