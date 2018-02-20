import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Solution {
	/*
	 * Given a non-empty binary search tree and a target value, find k values in the BST 
	 * that are closest to the target.
	 * 
	 * 
	 * Note:
	 * Given target value is a floating point.
	 * You may assume k is always valid, that is: k ≤ total nodes.
	 * You are guaranteed to have only one unique set of k values in the BST that are 
	 * closest to the target.
	 * 
	 * 
	 * Follow up:
	 * Assume that the BST is balanced, could you solve it in less than O(n) runtime 
	 * (where n = total nodes)?
	 */
	
	/* https://discuss.leetcode.com/topic/23151/o-logn-java-solution-with-two-stacks-following-hint
	 * 
	 * Time Complexity O(log(n) + k)
	 * Building each of the stacks takes O(log(n)) assuming BST is balanced.
	 * Each call to getNextPred/Succ has an amortized cost of O(1), since every node is 
	 * pushed and popped at most once.
	 */
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> successor = new Stack<TreeNode>();
        Stack<TreeNode> predecessor = new Stack<TreeNode>();
        
        initializeSuccessorStack(root, target, successor);
        initializePredecessorStack(root, target, predecessor);
        /*
        for (TreeNode node: successor) {
        	System.out.print(node.val + " -> ");
        }
        System.out.println();
        System.out.println("=====");
        
        for (TreeNode node: predecessor) {
        	System.out.print(node.val + " -> ");
        }
        System.out.println();
        */
        // 避免successor 与 predecessor中有相同的node
        if (!successor.isEmpty() && !predecessor.isEmpty() && successor.peek().val == predecessor.peek().val) {
            getNextPredecessor(predecessor);
        }
        
        for (int i = 0; i < k; i++) {
        	if (successor.isEmpty()) {
        		result.add(getNextPredecessor(predecessor));
        	} else if (predecessor.isEmpty()) {
        		result.add(getNextSuccessor(successor));
        	} else {
        		double succDiff = Math.abs((double)successor.peek().val - target);
        		double predDiff = Math.abs((double)predecessor.peek().val - target);
                if(succDiff < predDiff) {
                	result.add(getNextSuccessor(successor));
                } else {
                	result.add(getNextPredecessor(predecessor));
                }
        	}
        }
        
       return result;
        
    }
    
    private static void initializeSuccessorStack(TreeNode root, double target, Stack<TreeNode> successor) {
    	while (root != null) {
    		if (root.val == target) {
    			successor.push(root);
    			break;
    		} else if (root.val < target){
    			root = root.right;
    		} else {
    			successor.push(root);
    			root = root.left;
    		}
    	}
    }
    
    private static void initializePredecessorStack(TreeNode root, double target, Stack<TreeNode> predecessor) {
    	while (root != null) {
    		if (root.val == target) {
    			predecessor.push(root);
    			break;
    		} else if (root.val < target) {
    			predecessor.push(root);
    			root =root.right;
    		} else {
    			root = root.left;
    		}
    	}
    }
    
    private static int getNextSuccessor(Stack<TreeNode> successor) {
    	TreeNode node = successor.pop();
    	int result = node.val;
    	
    	node = node.right;
    	while (node != null) {
    		successor.push(node);
    		node = node.left;
    	}
    		
    	return result;
    }
    
    private static int getNextPredecessor(Stack<TreeNode> predecessor) {
    	TreeNode node = predecessor.pop();
    	int result = node.val;
    	
    	node = node.left;
    	while(node != null) {
    		predecessor.push(node);
    		node = node.right;
    	}
    		
    	return result;
    }
    
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
 		
 		System.out.println(closestKValues(node1, 4, 3));
 	}
}
