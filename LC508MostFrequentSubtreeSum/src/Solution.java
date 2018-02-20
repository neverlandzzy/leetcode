import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a 
	 * node is defined as the sum of all the node values formed by the subtree rooted at that node (including the node itself). 
	 * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
	 * 
	 * Examples 1
	 * 
	 * Input:
     *     5
     *   /  \ 
     *  2   -3
     *  
     * return [2, -3, 4], since all the values happen only once, return all of them in any order.
     * Examples 2
     * 
     * Input:
     *    5
     *  /  \
     * 2   -5
     * 
     * return [2], since 2 happens twice, however -5 only occur once.
     * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
	 */
	
	
	/*
	private static int max = 0; // 最大和发生的次数
	private static int maxCounter = 0; // 最大和的个数
	
    public static int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();  // key: 最大和， val:最大和发生的次数
        
        helper(root, map);

        int[] result = new int[maxCounter];
        int i = 0;
        
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == max) {
            	result[i++] = entry.getKey();
            }
        }
        
        System.out.println("maxCounter = " + maxCounter);
        System.out.println("max = " + max);
        return result;
    }
    
    private static int helper(TreeNode root, HashMap<Integer, Integer> map) {
    	if (root == null) {
    		return 0;
    	}
    	
    	int left = helper(root.left, map);
    	int right = helper(root.right, map);
    	
    	int sum = left + right + root.val;
    	
    	if (!map.containsKey(sum)) {
    		map.put(sum, 1);
    	} else {
    		map.put(sum, map.get(sum) + 1);
    	}

    	if (map.get(sum) > max) {
    		max = map.get(sum);
    		maxCounter = 1;
    	} else if (map.get(sum) == max) {
    		maxCounter++;
    	}
    	return sum;
    }
    
    */
	
	// remove instance variables
    public static int[] findFrequentTreeSum(TreeNode root) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] max = new int[1];
        int[] maxCounter = new int[1];
        
        helper(root, map, max, maxCounter);
        int[] result = new int[maxCounter[0]];
        int i = 0;
        
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() == max[0]) {
                result[i++] = entry.getKey();
            }
        }
        
        return result;
    }
    
    private static int helper(TreeNode root, HashMap<Integer, Integer> map, int[] max, int[] maxCounter) {
        if (root == null) {
            return 0;
        }
        
        int left = helper(root.left, map, max, maxCounter);
        int right = helper(root.right, map, max, maxCounter);
        
        int sum = left + right + root.val;
        
        if (!map.containsKey(sum)) {
            map.put(sum, 1);
        } else {
            map.put(sum, map.get(sum) + 1);
        }
        
        if (map.get(sum) > max[0]) {
            max[0] = map.get(sum);
            maxCounter[0] = 1;
        } else if (map.get(sum) == max[0]) {
            maxCounter[0]++;
        }
        
        return sum;
    }
    public static void main(String[] args) {
    	TreeNode node1 = new TreeNode(5);
 		TreeNode node2 = new TreeNode(2);
 		TreeNode node3 = new TreeNode(-5);

 		
 		node1.left = node2;
 		node1.right  = node3;

 		for (int n : findFrequentTreeSum(node1)) {
 			System.out.print(n + ", ");
 		}
	}
}
