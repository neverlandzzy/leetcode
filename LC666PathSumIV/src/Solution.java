import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * If the depth of a tree is smaller than 5, then this tree can be represented by a list of three-digits integers.
	 * 
	 * For each integer in this list:
	 * The hundreds digit represents the depth D of this node, 1 <= D <= 4.
	 * The tens digit represents the position P of this node in the level it belongs to, 1 <= P <= 8. The position is the same as that in a 
	 * full binary tree.
	 * The units digit represents the value V of this node, 0 <= V <= 9.
	 * Given a list of ascending three-digits integers representing a binary with the depth smaller than 5. You need to return the sum of all 
	 * paths from the root towards the leaves.
	 * 
	 * Example 1:
	 * Input: [113, 215, 221]
	 * Output: 12
	 * Explanation: 
	 * The tree that the list represents is:
	 *     3
	 *    / \
	 *   5   1
	 * 
	 * The path sum is (3 + 5) + (3 + 1) = 12.
	 * 
	 * Example 2:
	 * Input: [113, 221]
	 * Output: 4
	 * Explanation: 
	 * The tree that the list represents is: 
	 *     3
	 *      \
	 *       1
	 * 
	 * The path sum is (3 + 1) = 4.
	 */
	
    public static int pathSum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int num: nums) {
        	map.put(num / 10, num % 10);
        }
        
        int[] result = new int[1];
        helper(map, nums[0] / 10, 0, result);
        
        return result[0];
    }
    
    private static void helper(Map<Integer, Integer> map, int root, int sum, int[] result) {
    	if (!map.containsKey(root)) {
    		return;
    	}
    	
    	int level = root / 10;
    	int pos = root % 10;
    	
    	int left = (level + 1) * 10 + pos * 2 - 1;
    	int right = (level + 1) * 10 + pos * 2;
    	
    	sum += map.get(root);
    	
    	if (!map.containsKey(left) && !map.containsKey(right)) {
    		result[0] += sum;
    		return;
    	}
    	
    	helper(map, left, sum, result);
    	helper(map, right, sum, result);
    }
    
    public static void main(String[] args) {
		int[] test1 = {113, 215, 221};
		int[] test2 = {113, 221};
		
		System.out.println(pathSum(test1));
		System.out.println(pathSum(test2));
	}
}
