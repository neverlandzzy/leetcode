import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a collection of numbers, return all possible permutations.
	 * 
	 * For example,
	 * [1,2,3] have the following permutations:
	 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	 * 
	 */
    
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        helper(result, list, nums);
        
        return result;
    }
    
    private static void helper(List<List<Integer>> result, List<Integer> list, int[] nums) {
    	//System.out.println(list);
        if (list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                helper(result, list, nums);
                //System.out.println("~~~~" + list + " i == " + i);
                list.remove(list.size() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
		int[] test1 = {1,2,3};
		
		
		System.out.println(permute(test1));
		//System.out.println(permute(test2));
		
	}
}
