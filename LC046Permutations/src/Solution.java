import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given a collection of numbers, return all possible permutations.
	 * 
	 * For example,
	 * [1,2,3] have the following permutations:
	 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
	 * 
	 */
    
	// Solution 1: Recursion
	/*
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
    */
	
	// Solution 2: iteration
    public static List<List<Integer>> permute2(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	result.add(new ArrayList<Integer>());
    	
    	for (int i = 0; i < nums.length; i++) {
    		List<List<Integer>> tmp = new ArrayList<>();
    		for (List<Integer> list: result) {
    			for (int j = 0; j <= list.size(); j++) {
    				List<Integer> tmpList = new ArrayList<>(list);
    				tmpList.add(j, nums[i]);
    				tmp.add(tmpList);
    			}
    		}
    		
    		result = tmp;

    	}
    	
    	return result;
    }

    public static List<List<Integer>> permute(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
    	result.add(new ArrayList<>());
    	
    	for (int i = 0; i < nums.length; i++) {
    		int size = result.size();
    		for (int j = 0; j < size; j++) {
    			for (int k = result.get(j).size() - 1; k >= 0; k--) {
    				List<Integer> list = new ArrayList<>(result.get(j));
    				list.add(k, nums[i]);
    				result.add(list);
					//System.out.println("i = " + i + " j = " + j + " k = " + k + " list = " + list);
    			}
				//System.out.println("before: " + "i = " + i + " j = " + j + " result = " + result);
    			result.get(j).add(nums[i]);
				//System.out.println("after: " + "i = " + i + " j = " + j + " result = " + result);
    		}
    	}
    	
    	return result;
    }

    public static void main(String[] args) {
		int[] test1 = {1,2,3};

		System.out.println(permute(test1));
		//System.out.println(permute(test2));
		
	}
}
