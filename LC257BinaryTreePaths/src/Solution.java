import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Solution {
	/**
	 * Given a binary tree, return all root-to-leaf paths.
	 * 
	 * For example, given the following binary tree:
	 * 
	 *    1  
	 *  /   \
	 * 2     3
	 *  \
	 *   5
	 *   
	 *   All root-to-leaf paths are:
	 *   ["1->2->5", "1->3"]
	 */
	
	// FB面经，掌握多种方法
	// Solution 1: DFS, Recursion
	/*
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            return result;
        }
        
        helper(result, root, sb);
        
        return result;
    }
    
    private static void helper(List<String> result, TreeNode root, StringBuilder sb) {       
        sb.append(root.val);
        
        if (root.left == null && root.right == null) {
            result.add(sb.toString());
            return;
        }
        
        sb.append("->");
        int len = sb.length();

        if (root.left != null) {
            helper(result, root.left, sb);
            sb.setLength(len);
        }
        
        if (root.right != null) {
            helper(result, root.right, sb);
            sb.setLength(len);
        }     
    }
    */
	
	/*
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        
        helper(root, "" ,result);
        
        return result;
    }
    
    private static void helper(TreeNode root, String path, List<String> list) {
    	
    	if (root == null) {
    	    return;
    	}
    	
    	if (root.left == null && root.right == null) {
    		list.add(path + root.val);
    	}
    	
    	if (root.left != null) {
    		helper(root.left, path + root.val + "->", list);
    	}
    	
    	if (root.right != null) {
    		helper(root.right, path + root.val + "->", list);
    	}
    }
    */
	
    // Solution 1， 用StringBuilder，要在每次递归结束前删掉sb的最后一个字符
    /*
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        helper(root, result, sb);
        return result;
    }
    
    private static void helper(TreeNode root, List<String> result, StringBuilder sb) {
        if (root == null) {
            return;
        }
        int len = sb.length();
        
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            result.add(sb.toString());
            sb.setLength(len);
            return;
        }
        
        sb.append(root.val).append("->");
        helper(root.left, result, sb);
        helper(root.right, result, sb);
        sb.setLength(len);
    }
    */
    
	// Solution 2: BFS, iteration with Queue
	/*
	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		
		if (root == null) {
			return result;
		}
		
		Queue<TreeNode> queue = new LinkedList<>();
		Queue<String> path = new LinkedList<>();
		queue.offer(root);
		path.offer("");
		
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			String s = path.poll();
			if (node.left == null && node.right == null) {
				s += node.val;
				result.add(s);
				continue;
			}
			
			s += node.val + "->";
			
			if (node.left != null) {
				queue.offer(node.left);
				path.offer(s);
			}
			
			if (node.right != null) {
				queue.offer(node.right);
				path.offer(s);
			}
		}
		
		return result;
	}
	*/
	
	// Solution 3: DFS, iteration with Stack
	
	public static List<String> binaryTreePaths(TreeNode root) {
		List<String> result = new ArrayList<>();
		
		if (root == null) {
			return result;
		}
		
		Stack<TreeNode> stack = new Stack<>();
		Stack<String> path = new Stack<>();
		
		stack.push(root);
		path.push("");
		
		while (!stack.isEmpty()) {
			TreeNode node = stack.pop();
			String s = path.pop();
			
			if (node.left == null && node.right == null) {
				s += node.val;
				result.add(s);
				continue;
			}
			
			s += node.val + "->";
			if (node.left != null) {
				stack.push(node.left);
				path.push(s);
			}
			
			if (node.right != null) {
				stack.push(node.right);
				path.push(s);
			}
		}
		
		return result;
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
 		 *  	    1
 		 *        /   \
 		 *   	 /     \
 		 *  	2       3
 		 *	   / \     /
 		 *	  4   5   7
 		 *         \
 		 *          6
 		 */
 	
 		node1.left  = node2;
 		node1.right = node3;
 		node2.left  = node4;
 		node2.right = node5;
 		node5.right = node6;
 		node3.left  = node7;
 		
 		System.out.println(binaryTreePaths(node1));


 	}
}
