import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/*
	 * Given a collection of numbers that might contain duplicates, return all possible 
	 * unique permutations.
	 * 
	 * For example,
	 * [1,1,2] have the following unique permutations:
	 * [1,1,2], [1,2,1], and [2,1,1].
	 * 
	 */
	
	// Solution 1: recursion
	/*
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        boolean[] visited = new boolean[nums.length];
        
        Arrays.sort(nums);
        helper(result, list, visited, nums);
        return result;
    }
    
    private static void helper(List<List<Integer>> result, List<Integer> list, boolean[] visited, int[] nums) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
        } else {
            for (int i = 0; i < nums.length; i++) {
                
                if (i > 0 && !visited[i - 1] && nums[i - 1] == nums[i]) {
                    continue;
                }
                
                if (!visited[i]) {
                    visited[i] = true;
                    list.add(nums[i]);
                    helper(result, list, visited, nums);
                    visited[i] = false;
                    list.remove(list.size() - 1);
                }
            }
        }
    }
    */
    // Solution 2: iteration	
	public static List<List<Integer>> permuteUnique(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	result.add(new ArrayList<Integer>());
    	
    	for (int i = 0; i < nums.length; i++) {
    		int size = result.size();
    		for (int j = 0; j < size; j++) {
    			for (int k = result.get(j).size() - 1; k >= 0; k--) {
    				List<Integer> list = new ArrayList<>(result.get(j));
    				if (nums[i] != result.get(j).get(k)) {
    					list.add(k, nums[i]);
    					result.add(list);
    				} else {
    					break;
    				}
    			}
    			//System.out.println("before: " + result);
    			result.get(j).add(nums[i]);
    			//System.out.println("after: " + result);
    		}
    	}
    	
    	return result;
	}
	
    public static void main(String[] args) {
		int[] test1 = {1,2, 1};
		
		
		System.out.println(permuteUnique(test1));
		


		
	}
}
