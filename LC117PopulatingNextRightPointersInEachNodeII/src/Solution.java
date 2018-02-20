import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Follow up for problem "Populating Next Right Pointers in Each Node".
	 * 
	 * What if the given tree could be any binary tree? Would your previous solution still work?
	 * 
	 * Note:
	 * 
	 * You may only use constant extra space.
	 * 
	 * For example,
	 * Given the following binary tree,
	 *          1
	 *         /  \
	 *        2    3
	 *       / \    \
	 *      4  5     7
	 * After calling your function, the tree should look like:
	 *          1 -> NULL
	 *        /  \
	 *       2 -> 3 -> NULL
	 *      / \    \
	 *     4->5->  7 -> NULL
	 */
	public static void connect(TreeLinkNode root) {
		
		TreeLinkNode head = root;
		TreeLinkNode curr = null;
		TreeLinkNode prev = null;
		
		while (head != null) {
			curr = head;
			head = null;
			prev = null;
			
			while (curr != null) {
				if (curr.left != null) {
					if (prev != null) {
						prev.next = curr.left;
					} else {
						head = curr.left;
					}
					
					prev = curr.left;
				}
				
				if (curr.right != null) {
					if(prev != null) {
						prev.next = curr.right;
					} else {
						head = curr.right;
					}
					
					prev = curr.right;
				}
				
				curr = curr.next;
			}
		}
	}
	
	
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
