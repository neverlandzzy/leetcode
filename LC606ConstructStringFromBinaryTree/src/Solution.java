
public class Solution {
	/*
	 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
	 * 
	 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis 
	 * pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
	 * 
	 * Example 1:
	 * Input: Binary tree: [1,2,3,4]
	 *        1
	 *      /   \
	 *     2     3
	 *    /    
	 *   4     
	 * 
	 * Output: "1(2(4))(3)"
	 * 
	 * Explanation: Originally it needs to be "1(2(4)())(3()())", 
	 * but you need to omit all the unnecessary empty parenthesis pairs. 
	 * And it will be "1(2(4))(3)".
	 * 
	 * Example 2:
	 * Input: Binary tree: [1,2,3,null,4]
	 *        1
	 *      /   \
	 *     2     3
	 *      \  
	 *       4 
	 * 
	 * Output: "1(2()(4))(3)"
	 * 
	 * Explanation: Almost the same as the first example, 
	 * except we can't omit the first parenthesis pair to break the one-to-one mapping relationship between the input and the output.
	 */
	
    public static String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        helper(t, sb);
        
        return sb.toString();
    }
    
    private static void helper(TreeNode t, StringBuilder sb) {
    	if (t == null) {
    		return;
    	}
    	
    	sb.append(t.val);
    	if (t.left == null && t.right == null) {
    		return;
    	}
    	sb.append("(");
    	helper(t.left, sb);
    	sb.append(")");
    	
    	if (t.right == null) {
    		return;
    	}
    	sb.append("(");
    	helper(t.right, sb);
    	sb.append(")");
    }
    
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		
		System.out.println(tree2str(node1));
		
		TreeNode node5 = new TreeNode(1);
		TreeNode node6 = new TreeNode(2);
		TreeNode node7 = new TreeNode(3);
		TreeNode node8 = new TreeNode(4);
		
		node5.left = node6;
		node5.right = node7;
		node6.right = node8;
		
		System.out.println(tree2str(node5));
	}
}
