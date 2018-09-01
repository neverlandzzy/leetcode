
public class LCA {
	/*
	 * 给定一个integer数组（无序）和2个node值，求构建bst(不可调整树）后，两个node的距离。
     * Given a list of numbers, construct a BST from it and find the distance between two nodes.
     * int bstDistance(int[] values, int node1, int node2)
     * 第一个判断invalid的node1 and node2, 第二个判断一下node1或者node2是不是等于root，尝试一下testcase{1}，1，1，1这样。。.
     * 我刚开始没有考虑到node1或者node2可能是root，所以只pass了8 out 11，后来加了这个就过了。。
     * 
     * 给一个数组A[5，6，3，1，2，4]，先建立BST，然后搜索两个node之间的距离。已知第一个元素5是root，剩下的是无序的！！注意后面有可能是右子树先出现（6，对应root-5），
     * 也有可能是左子树先出现（1，2，对应root-3）如果有node不存在的话，返回-1；要求实现的函数长这样：int distance（int* values, int n，int node1, int node2）
     * 注：题目中没说，但数组好像是没有重复的。Recursion建树，while loop 找距离
	*/
	
    public static TreeNode LCA(TreeNode root, TreeNode node1,TreeNode node2){
    	if (root == null|| node1 == root || node2 == root){
    		return root;
    	}
    	if (node1.val < root.val && root.val < node2.val){
    		return root;
    	}else if (node1.val < root.val && node2.val < root.val){
    		return LCA(root.left,node1,node2);
    	}else if (node1.val > root.val && node2.val > root.val){
    		return LCA(root.right,node1,node2);
    	}else {
    		return null;
    	}
    }
    public static int findlen(TreeNode root,int in){
    	return finddistance(root,in)-1;
    }
    public static int finddistance(TreeNode root,int in){
    	if (root == null){
    		return 0;
    	}
    	int dis = 0;
    	if (root.val == in){
    		return dis + 1;
    	}else if (root.val < in ){
    		dis = finddistance(root.right,in);
    	}else {
    		dis = finddistance(root.left, in);
    	}
    	if (dis > 0){
    		return dis + 1;
    	}else{
    		return 0;
    	}
    }
    public static void createbst(TreeNode root, int val){
    	if (val < root.val){
    		if (root.left == null){
    			root.left = new TreeNode(val);
    		}else {
    			createbst(root.left,val);
    		}
    	}else {
    		if (root.right == null){
    			root.right = new TreeNode(val);
    		}else{
    			createbst(root.right,val);
    		}
    	}
    }
    public static int bstdistance(int[] vals,int n , int node1, int node2){
    	if (vals == null || vals.length == 0){
    		return 0;
    	}
    	TreeNode root = new TreeNode(vals[0]);
    	for (int i =1 ;i<vals.length;i++){
    		createbst(root,vals[i]);
    	}
    	int len1 = findlen(root,node1);
    	int len2 = findlen(root,node2);
    	if (len1 == -1 || len2 == -1){
    		return -1;
    	}
    	int lowca = LCAval(root,node1,node2).val;
    	int mid = findlen(root,lowca);
    	return len1+len2-2*mid;
    
    }
    public static TreeNode LCAval (TreeNode root,int val1,int val2){
    	if (root == null || val1 == root.val || val2 == root.val){
    		return root;
    	}
    	if ((root.val > val1 && root.val < val2)|| (root.val == val1 || root.val == val2)){
    		return root;
    	}else if (root.val > val1 && root.val > val2){
    		return LCAval(root.left,val1,val2);
    	}else if (root.val < val1 && root.val < val2){
    		return LCAval(root.right,val1,val2);
    	}else {
    		return null;
    	}
    }
////////
    
    public static int bstDist(int[] a,int n , int p, int q) {
        if (a == null || a.length == 0){
                return 0;
        }
        int res = 0; 
          // build BST
        TreeNode root = buildBST(a);
        // check if p, q exist in BST
        if (bstSearch(root, p) && bstSearch(root, q)) {
            // find LCA of p and q
            TreeNode lca = bstLca(root, p, q);
            // find length between LCA - p and LCA - q
            res += findDist(lca, p);
            res += findDist(lca, q);
        } else {
                res = -1;
        }
        return res;
    }    
    public static boolean bstSearch(TreeNode root, int x) {
        boolean res = false;
        while (root != null) {
                if (root.val == x) {
                        return true;
                } else if (root.val > x) {
                        root = root.left;
                } else {
                        root = root.right;
                }
        }
        return res;
    }
	// find LCA in BST
	public static TreeNode bstLca(TreeNode root, int p, int q) {
	    if (root == null) {
	        return null;
	    }
	    while (true) {
	        if (root.val > Math.max(p, q)) {
	            // p, q both in left child
	            root = root.left;
	        } else if (root.val < Math.min(p, q)) {
	            // both in right child
	            root = root.right;
	        }
	        else {
	            // cur root is LCA
	            break;
	        }
	    }
	    return root;
	}
	// calculate dist between root and target
	public static int findDist(TreeNode root, int x) {
	        if (root == null) {
	                return 0;
	        }
	        int res = 0;
	        while (root != null) {
	                if (root.val == x) {
	                        break;
	                } else if (root.val > x) {
	                        root = root.left;
	                        res += 1;
	                } else {
	                        root = root.right;
	                        res += 1;
	                }
	        }
	        return res;
	}    // build BST with insertion
	public static TreeNode buildBST(int[] a) {
	    TreeNode root = new TreeNode(a[0]);
	    for (int i = 1; i<a.length; ++i) {
	        createbst2(root, a[i]);
	    }
	    
	    return root;
	}
	public static void createbst2(TreeNode root, int val) {
	    if (val < root.val) {
	        if (root.left == null) {
	            root.left = new TreeNode(val);
	        } else {
	            createbst(root.left,val);
	        }
	    }else {
	        if (root.right == null){
	            root.right = new TreeNode(val);
	        }else{
	            createbst(root.right,val);
	        }
	    }
	}
	    // find LCA in binary tree
	public TreeNode binaryTreeLCA(TreeNode root, TreeNode p, TreeNode q) {
	    if (root == null || root == p || root == q) {
	        return root;
	    }
	    TreeNode left = binaryTreeLCA(root.left, p, q);
	    TreeNode right = binaryTreeLCA(root.right, p, q);
	    if (left == null && right == null) {
	        // not found
	        return null;
	    } else if (left == null) {
	        // both on right side, and right is LCA
	        return right;
	    } else if (right == null) {
	        return left;
	    } else {
	        // one of left the other on right
	        return root;
	    }
	}
    public static void main(String[] args){
    	
    	int[] input = {5,6,3,1,2,4};
    	System.out.println(bstdistance(input,6,2,6));
    	
    	System.out.println(bstDist(input, 6, 2, 6));
    }
}