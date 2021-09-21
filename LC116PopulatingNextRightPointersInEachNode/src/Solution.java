import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given a binary tree
	 * 
	 * struct TreeLinkNode {
	 *       TreeLinkNode *left;
	 *       TreeLinkNode *right;
	 *       TreeLinkNode *next;
	 * }
	 * 
	 * Populate each next pointer to point to its next right node. 
	 * If there is no next right node, the next pointer should be set to NULL.
	 * 
	 * Initially, all next pointers are set to NULL.
	 * 
	 * Note:
	 * 
	 * You may only use constant extra space.
	 * 
	 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, 
	 * and every parent has two children).
	 * 
	 * For example,
	 * Given the following perfect binary tree,
	 *          1
	 *         /  \
	 *        2    3
	 *       / \  / \
	 *      4  5  6  7
	 * 
	 * After calling your function, the tree should look like:
	 *          1 -> NULL
	 *        /  \
	 *       2 -> 3 -> NULL
	 *      / \  / \
	 *     4->5->6->7 -> NULL
	 */

	// 这个解法对于非perfect binary tree一样适用。对于perfect binary tree，可以不用检查 if (cur.left != null)(line 57) 和 if (cur.right != null)(line67)
    public static void connect(TreeLinkNode root) {
    	TreeLinkNode head  = root;   //The leftmost node in the lower level (head of the next level)
    	TreeLinkNode prev  = null;   //The previous node in the lower level (the leading node on the next level)
    	TreeLinkNode cur   = null;   //The current node in the upper level
    	
    	while (head != null) {
    		cur = head;
    		prev = null;
    		head = null;
    		
    		// iterate on current level
    		while (cur != null) {
    			// 对于perfect binary tree，可以不用检查 if (cur.left != null)
    			if (cur.left != null) {
    				if (prev != null) {
    					prev.next = cur.left;
    				} else {
    					head = cur.left;
    				}
					prev = cur.left;
    			}

				// 对于perfect binary tree，可以不用检查 if (cur.right != null)
    			if (cur.right != null) {
    				if (prev != null) {
    					prev.next = cur.right;
    				} else {
    					head = cur.right;
    				}
    				
    				prev = cur.right;
    			}
    			
    			cur = cur.next;
    		}
    	}
    }
    
    // Solution 2: recursion

	/* 新版带返回值
	    public Node connect(Node root) {
        if (root == null || root.left == null && root.right == null) {
            return root;
        }

        root.left.next = root.right;

        if (root.next != null) {
            root.right.next = root.next.left;
        }

        root.left = connect(root.left);
        root.right = connect(root.right);

        return root;
    }
	 */

    /*
    public static void connect(TreeLinkNode root) {
        if (root == null || root.left == null && root.right == null) {
            return;
        }
        
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        
        connect(root.left);
        connect(root.right);
    }
    */




    public static void main(String[] args) {
    	TreeLinkNode node1 = new TreeLinkNode(1);
    	TreeLinkNode node2 = new TreeLinkNode(2);
    	TreeLinkNode node3 = new TreeLinkNode(3);
    	TreeLinkNode node4 = new TreeLinkNode(4);
    	TreeLinkNode node5 = new TreeLinkNode(5);
    	TreeLinkNode node6 = new TreeLinkNode(6);
    	TreeLinkNode node7 = new TreeLinkNode(7);
    	
    	node1.left  = node2;
    	node1.right = node3;
    	node2.left  = node4;
    	node2.right = node5;
    	node3.left  = node6;
    	node3.right = node7;
    	
    	connect(node1);
    	
    	List<TreeLinkNode> list = new ArrayList<TreeLinkNode>();
    	
    	TreeLinkNode cur = node1;
    	
    	while(cur != null) {
    		list.add(cur);
    		cur = cur.left;
    	}
    	
    	for (TreeLinkNode node: list) {
    		while (node != null) {
    			System.out.print(node.val + "->");
    			node = node.next;
    		}
			System.out.println();
    	}
	}

}
