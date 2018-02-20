import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/*
	 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, 
	 * and the length of an increasing subsequence should be at least 2 .
	 * 
	 * Example:
	 * Input: [4, 6, 7, 7]
	 * Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
	 * 
	 * Note:
	 * The length of the given array will not exceed 15.
	 * The range of integer in the given array is [-100,100].
	 * The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.
	 */
	
	
	// 序列不是sorted，所以必须用HashSet去重
	
    public static List<List<Integer>> findSubsequences(int[] nums) {
    	Set<List<Integer>> res= new HashSet<List<Integer>>();
    	List<Integer> list = new ArrayList<>();
      
    	helper(res, list, nums, 0);
    	return new ArrayList<>(res);
    }
    
    private static void helper(Set<List<Integer>> res, List<Integer> list, int[] nums, int start) {
    	if (list.size() >= 2) {
    		res.add(new ArrayList<>(list));
    	}
    	
    	for (int i = start; i < nums.length; i++) {
    		if (list.size() == 0 || (nums[i] >= list.get(list.size() - 1))) {
    			list.add(nums[i]);
    			helper(res, list, nums, i + 1);
    			list.remove(list.size() - 1);	
    		} 
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(findSubsequences(new int[] {4, 6, 7, 7}));
	}
}
