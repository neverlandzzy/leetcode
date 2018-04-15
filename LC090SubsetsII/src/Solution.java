
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
	
	/*
	// Solution 1: Recursion
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
    */
	
	// Solution 2: Iteration
    public static List<List<Integer>> subsetsWithDup2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<Integer>());
        
        int start = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            if (i == 0 || nums[i - 1] != nums[i]) {
                start = 0;
            }
            
            for (int k = start; k < size; k++) {
                List<Integer> list = new ArrayList<>(result.get(k));
                list.add(nums[i]);
                result.add(list);
            }
            start = size;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test = {1,2,2};
		
		System.out.println(subsetsWithDup2(test));
	}
}
