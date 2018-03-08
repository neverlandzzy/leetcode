package onsite;


public class SubtreeOfAnotherTree {
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
        	return false;
        }
        
        if (isSameTree(s, t)) {
        	return true;
        }
        
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private static boolean isSameTree(TreeNode s, TreeNode t) {
    	if (s == null && t == null) {
    		return true;
    	}
    	
    	if (s == null || t == null) {
    		return false;
    	}
    	
    	
    	if (s.val != t.val) {
    		return false;
    	}
    	
    	return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
    
    public static void main(String[] args) {
		TreeNode node1 = new TreeNode(3);
		TreeNode node2 = new TreeNode(4);
		TreeNode node3 = new TreeNode(5);
		TreeNode node4 = new TreeNode(1);
		TreeNode node5 = new TreeNode(2);
		
		node1.left = node2;
		node1.right = node3;
		node2.left = node4;
		node2.right = node5;
		
		TreeNode node6 = new TreeNode(4);
		TreeNode node7 = new TreeNode(1);
		TreeNode node8 = new TreeNode(2);
		
		node6.left = node7;
		node6.right = node8;
		
		System.out.println(isSubtree(node1, node6));
		
		TreeNode node9 = new TreeNode(0);
		node5.left = node9;
		System.out.println(isSubtree(node1, node6));
		
		TreeNode node10 = new TreeNode(2);
		TreeNode node11 = new TreeNode(12);
		System.out.println(isSubtree(node10, node11));

	}
}
