import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/**
	 * Given a set of distinct integers, nums, return all possible subsets.
	 * 
	 * Note:
	 * 
	 * Elements in a subset must be in non-descending order.
	 * 
	 * The solution set must not contain duplicate subsets.
	 * 
	 * For example,
	 * 	If nums = [1,2,3], a solution is:
	 * 
	 * [
	 *   [3],
	 *   [1],
	 *   [2],
	 *   [1,2,3],
	 *   [1,3],
	 *   [2,3],
	 *   [1,2],
	 *   []
	 * ]
	 */
	
	// Solution 1: Recursion
	// Time: O(2^n * k) == 执行(2^n)次，每次要把list copy到result里，花费k，k为list重元素的个数。
	// http://www.1point3acres.com/bbs/thread-117602-1-1.html
    public static List<List<Integer>> subsets(int[] nums) {
        //Arrays.sort(nums);
        List<Integer> list = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        subsetsHelper(nums, result, list, 0);
        
        return result;
    }
    
    public static void subsetsHelper(int[] nums, List<List<Integer>> result, List<Integer> list, int pos) {
    	result.add(new ArrayList<Integer>(list));
    	//System.out.println("pos = " + pos + " " + list);
    	for (int i = pos; i < nums.length; i++) {
    		list.add(nums[i]);
    		subsetsHelper(nums, result, list, i+1);
    		list.remove(list.size()-1);
    	}
    }
    
    // Solution 2: Iteration
    public static List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        result.add(new ArrayList<Integer>());
        
        for (int i = 0; i < nums.length; i++) {
            int size = result.size();
            for (int j = 0; j < size; j++) {
                List<Integer> list = new ArrayList<>(result.get(j));
                list.add(nums[i]);
                result.add(list);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test = {1,2,3};
		
		System.out.println(subsets(test));
	}
}
