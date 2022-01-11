import java.util.Stack;


public class Solution {
	/**
	 * Convert a BST to a sorted circular doubly-linked list in-place. Think of the left and right pointers as synonymous to the previous and 
	 * next pointers in a doubly-linked list.
	 * 
	 * Let's take the following BST as an example, it may help you understand the problem better:
	 *  	4
	 *     / \
	 *    2   5
	 *   / \  
	 *  1   3
	 *  
	 *  We want to transform this BST into a circular doubly linked list. Each node in a doubly linked list has a predecessor and successor. 
	 *  For a circular doubly linked list, the predecessor of the first element is the last element, and the successor of the last element is the 
	 *  first element.
	 *  
	 *  The figure below shows the circular doubly linked list for the BST above. The "head" symbol means the node it points to is the smallest 
	 *  element of the linked list.
	 *  
	 *  head <--> 1 <---> 2 <---> 3 <---> 4 <---> 5
	 *            |                               |
	 *            |                               |
	 *            |-------------------------------|
	 *  
	 *  Specifically, we want to do the transformation in place. After the transformation, the left pointer of the tree node should point to 
	 *  its predecessor, and the right pointer should point to its successor. We should return the pointer to the first element of the linked list.
	 *  
	 *  The figure below shows the transformed BST. The solid line indicates the successor relationship, while the dashed line means the predecessor 
	 *  relationship.
	 */
	
	// Solution 1: recursion
	/*
    static Node cur;
    
    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        
        Node dummy = new Node(0, null, null);
        cur = dummy;
        
        helper(root);
        
        cur.right = dummy.right;
        dummy.right.left = cur;
        
        return dummy.right;
    }
    
    private static void helper(Node root) {
        if (root == null) {
            return;
        }
        
        helper(root.left);
        root.left = cur;
        cur.right = root;
        cur = root;
        helper(root.right);
    }
    */
    
    // Solution 2: iteration
	public static Node treeToDoublyList(Node root) {
		if (root == null) {
			return null;
		}
		
		Stack<Node> stack = new Stack<>();
		Node dummy = new Node(0, null, null);
		Node cur = dummy;
		
		stack.push(root);
		while (root.left != null) {
			root = root.left;
			stack.push(root);
		}
		
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			
			if (node.right != null) {
				Node right = node.right;
				while (right != null) {
					stack.push(right);
					right = right.left;
				}
			}
			node.left = cur;
			cur.right = node;
			cur = node;	
		}
		
		cur.right = dummy.right;
		dummy.right.left = cur;
		
		return dummy.right;
	}
	
    public static void main(String[] args) {
    	Node node1 = new Node(1, null, null);
    	Node node3 = new Node(3, null, null);
    	Node node2 = new Node(2, node1, node3);
    	Node node5 = new Node(5, null, null);
		Node node4 = new Node(4, node2, node5);
		
		Node node = treeToDoublyList(node4);
		int counter  = 0;
		
		while (node != null && counter < 5) {
			System.out.print(node + " -> ");
			node = node.right;
			counter++;
		}
	}
}
