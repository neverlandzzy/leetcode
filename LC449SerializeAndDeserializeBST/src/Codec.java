
public class Codec {
	
	/*
	 * Serialization is the process of converting a data structure or object into a sequence of bits so that it 
	 * can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later 
	 * in the same or another computer environment.
	 * 
	 * Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your 
	 * serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be serialized 
	 * to a string and this string can be deserialized to the original tree structure.
	 * 
	 * The encoded string should be as compact as possible.
	 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should 
	 * be stateless.
	 * 
	 */
	
	// Your Codec object will be instantiated and called as such:
	// Codec codec = new Codec();
	// codec.deserialize(codec.serialize(root));

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val).append(",");
            serializeHelper(root.left, sb);
            serializeHelper(root.right,sb);
        }

    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
    	
    	// tips: 此处要先判断data是否为null或长度为0，不能等到int[] nodes初始化好后去判断nodes
    	//       因为即使s的长度为0， s.split(","); 长度为1。如下：
    	// 		String s = new String();
		//      String[] a = s.split(",");
		//      System.out.println(a.length);  -- print 1
  
        if (data == null || data.length() == 0) {
            return null;
        }
        
        String[] nodeStrings = data.split(",");
        int[] nodes = new int[nodeStrings.length];
        
        for (int i = 0; i < nodes.length; i++) {
        	nodes[i] = Integer.parseInt(nodeStrings[i]);
        }
        
        return deserializeHelper(nodes, 0, nodes.length-1);
    }
    
    private TreeNode deserializeHelper(int[] nodes, int start, int end) {
        if (start > end) {
        	return null;
        }
        
        if (start == end) {
        	return new TreeNode(nodes[start]);
        }
    	
    	int leftBound = start;
    	int rightBound = start + 1;
    	
    	TreeNode root = new TreeNode(nodes[start]);
    	
    	for(int i = start + 1; i <= end; i++) {
    		if (nodes[i] > root.val) {
    			break;
    		}
    		leftBound = i;
    		rightBound = leftBound + 1;
    	}
    	
    	root.left = deserializeHelper(nodes, start + 1, leftBound);
    	root.right = deserializeHelper(nodes, rightBound, end);
    	
    	return root;
    }
}
