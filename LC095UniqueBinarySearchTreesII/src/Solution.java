import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
	 * 
	 * For example,
	 * Given n = 3, your program should return all 5 unique BST's shown below.
	 *
     *   1         3     3      2      1
     *    \       /     /      / \      \
     *     3     2     1      1   3      2
     *    /     /       \                 \
     *   2     1         2                 3
	 */
	
    public static List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<TreeNode>();
        }
        
    	return generate(1, n);
    }
    
    
    private static List<TreeNode> generate(int start, int end) {
    	List<TreeNode> result = new ArrayList<TreeNode>();
    	
    	if (start > end) {
    	    result.add(null);    		
    		return result;
    	}

    	for (int i = start; i <= end; i++) {
    		List<TreeNode> leftList = generate(start, i-1); 
    		List<TreeNode> rightList = generate(i+1, end); 
    		
    		for (TreeNode left: leftList){
    			for (TreeNode right: rightList) {
    				TreeNode node = new TreeNode(i);
    				node.left = left;
    				node.right = right;
    				result.add(node);
    			}
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		System.out.println(generateTrees(5));
	}
}
