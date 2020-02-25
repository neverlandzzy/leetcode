import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/**
	 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
	 * 
	 * Return a list of all possible full binary trees with N nodes.  Each element of the answer is the root node of one possible tree.
	 * 
	 * Each node of each tree in the answer must have node.val = 0.
	 * 
	 * You may return the final list of trees in any order.
	 * 
	 * Example 1:
	 * 
	 * Input: 7
	 * Output: [[0,0,0,null,null,0,0,null,null,0,0],[0,0,0,null,null,0,0,0,0],[0,0,0,0,0,0,0],[0,0,0,0,0,null,null,null,null,0,0],[0,0,0,0,0,null,null,0,0]]
	 * 
	 * Note:
	 * 1 <= N <= 20
	 */
	
	// Time: O(2^n), Space: O(2^n)
	static Map<Integer, List<TreeNode>> cache = new HashMap<>();
	
    public static List<TreeNode> allPossibleFBT(int N) {
    	if (cache.containsKey(N)) {
    		return cache.get(N);
    	}
    	
    	List<TreeNode> result = new ArrayList<>();
        
    	if (N == 1) {
   			TreeNode root = new TreeNode(0);
   			result.add(root);
   			return result;
   		}
       
    	for (int i = 1; i < N; i += 2) {
    		List<TreeNode> leftList = allPossibleFBT(i);
    		List<TreeNode> rightList = allPossibleFBT(N - i - 1);

    		for (TreeNode left: leftList) {
    			for (TreeNode right: rightList) {
    				TreeNode root = new TreeNode(0);
    				root.left = left;
    				root.right = right;
    				result.add(root);
    			}     		
    		}  	
    	}
    	
    	cache.put(N, result);   	
    	return result;        
    }
    
    public static void main(String[] args) {
    	System.out.println(allPossibleFBT(7));
	}
}
