
public class TreeNodeShuffle {
	/*
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=411072
	 * 
	 * 给一个二叉树，随机改变每个node的位置，新的树结构不变，要保证每个node跟以前的该位置node不一样，不能直接更换node的value。(也不能用copy node)
	 *     # eg:
	 *     #   A                     C
	 *     #  /  \                   / \
 	 *     # B   C   ==>            D   B
 	 *     #  \                      \
 	 *     #   D                      A
	 * 
	 */
	
	public static TreeNode shuffle(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		if (root.left == null && root.right == null) {
			return root;
		}
		
		TreeNode left = shuffle(root.left);
		TreeNode right = shuffle(root.right);
		
		TreeNode ll = null;
		TreeNode lr = null;
		TreeNode rl = null;
		TreeNode rr = null;
		
		if (left != null) {
			ll = left.left;
			lr = left.right;
		}
		
		if (right != null) {
			rl = right.left;
			rr = right.right;
		}
		
		if (left == null) {
			right.right = root;
			root.left = rl;
			root.right = rr;
			return right;
		}
		
		if (right == null) {
			left.left = root;
			root.left = ll;
			root.right = lr;
			return left;
		}
		
		left.left = right;
		left.right = root;
		root.left = rl;
		root.right = rr;
		right.left = ll;
		right.right = lr;
		
		return left;
	}
	
	public static void main(String[] args) {
		TreeNode node1 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		
		node1.left = node2;
		node1.right = node3;
		node2.right = node4;
		
		System.out.println(serialize(node1));
		System.out.println(serialize(shuffle(node1)));
	}
	
	
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();    
        helperS(root, sb);
        return sb.toString();
    }
    
    private static void helperS(TreeNode root, StringBuilder sb) {
    	if (root == null) {
    		sb.append("null").append(",");
    		return;
    	}
    	
    	sb.append(root.val).append(",");
    	helperS(root.left, sb);
    	helperS(root.right, sb);
    	
    }
}
