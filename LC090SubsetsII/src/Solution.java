
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/*
	 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
	 * 
	 * Note:
	 * Elements in a subset must be in non-descending order.
	 * The solution set must not contain duplicate subsets.
	 * 
	 * For example,
	 * If nums = [1,2,2], a solution is:
	 * 
	 * [
	 * 	[2],
	 *	[1],
	 *	[1,2,2],
	 * 	[2,2],
	 *  [1,2],
	 *  []
	 * ]
	 */
	
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	List<Integer> list = new ArrayList<Integer>();
    	
    	Arrays.sort(nums);
    	
    	subsetsWithDupHelper(nums, result, list, 0);
    	
    	return result;
    }
    
    public static void subsetsWithDupHelper(int[] nums, List<List<Integer>> result, List<Integer> list, int pos) {
    	result.add(new ArrayList<Integer>(list));
    	System.out.println(list);
    	for (int i = pos; i < nums.length; i++) {
    		if(i != pos && nums[i] == nums[i-1]) {
    			continue;
    		}
    		list.add(nums[i]);

    		subsetsWithDupHelper(nums, result, list, i + 1);
    		
    		list.remove(list.size()-1);
    	}
    }
    
    public static void main(String[] args) {
		int[] test = {1,2,2};
		
		System.out.println(subsetsWithDup(test));
	}
}
