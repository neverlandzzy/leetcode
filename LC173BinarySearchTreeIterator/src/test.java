
public class test {
	
	public static void main(String[] args) {
		
		
		TreeNode node6 = new TreeNode(6);
 		TreeNode node4 = new TreeNode(4);
 		TreeNode node9 = new TreeNode(9);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node5 = new TreeNode(5);
 		TreeNode node7 = new TreeNode(7);
 		TreeNode node10 = new TreeNode(10);
 		TreeNode node3 = new TreeNode(3);
 		
 		/*
 		 * tree:
 		 *  	    6
 		 *        /   \
 		 *  	 4     9
 		 *	    / \   / \
 		 *	   2   5 7  10 
 		 *      \   
 		 *       3   
 		 */
 	
 		node6.left  = node4;
 		node6.right = node9;
 		node4.left  = node2;
 		node4.right = node5;
 		node2.right = node3;
 		node9.left  = node7;
 		node9.right  = node10;
 		
		BSTIterator i = new BSTIterator(node6);

		while (i.hasNext()){
			System.out.println(i.next());
		}
	}
}
