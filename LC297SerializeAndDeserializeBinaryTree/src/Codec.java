import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class Codec {
	/*
	 * Serialization is the process of converting a data structure or object into a sequence of bits 
	 * so that it can be stored in a file or memory buffer, or transmitted across a network 
	 * connection link to be reconstructed later in the same or another computer environment.
	 * 
	 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on 
	 * how your serialization/deserialization algorithm should work. You just need to ensure that 
	 * a binary tree can be serialized to a string and this string can be deserialized to the original 
	 * tree structure.
	 * 
	 * For example, you may serialize the following tree
	 *     1
	 *    / \
	 *   2   3
	 *  / \
	 * 4   5
	 * 
	 * as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do 
	 * not necessarily need to follow this format, so please be creative and come up with different 
	 * approaches yourself.
	 * 
	 * Note: Do not use class member/global/static variables to store states. Your serialize and 
	 * deserialize algorithms should be stateless.
	 */
	
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();    
        helperS(root, sb);
        return sb.toString();
    }
    
    private void helperS(TreeNode root, StringBuilder sb) {
    	if (root == null) {
    		sb.append("null").append(",");
    		return;
    	}
    	
    	sb.append(root.val).append(",");
    	helperS(root.left, sb);
    	helperS(root.right, sb);
    	
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	Queue<String> nodes = new LinkedList<String>();
		nodes.addAll(Arrays.asList(data.split(",")));
		
		return helperD(nodes);		
    }
    
    private TreeNode helperD(Queue<String> queue) {
    	String val = queue.poll();
    	
    	if (val.equals("null")) {
    		return null;
    	}
    	
    	TreeNode node = new TreeNode(Integer.valueOf(val));
    	node.left = helperD(queue);
    	node.right = helperD(queue);
    	
    	return node;
    }
}
