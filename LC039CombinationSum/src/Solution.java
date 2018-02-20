import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/*
	 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations 
	 * in C where the candidate numbers sums to T.
	 * 
	 * The same repeated number may be chosen from C unlimited number of times.
	 * 
	 * Note:
	 * All numbers (including target) will be positive integers.
	 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
	 * The solution set must not contain duplicate combinations.
	 * 
	 * For example, given candidate set 2,3,6,7 and target 7, 
	 * 
	 * A solution set is: 
	 * [7] 
	 * [2, 2, 3]
	 * 
	 */
	//private static List<List<Integer>> list = new ArrayList<List<Integer>>();
	//private static List<Integer> res = new ArrayList<Integer>();
	
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    	List<List<Integer>> list = new ArrayList<List<Integer>>();
    	List<Integer> res = new ArrayList<Integer>();
    	
    	Arrays.sort(candidates);
    	
    	add(0, target, candidates, res, list);
    	
    	return list;
    }
    
    public static void add(int start, int target, int[] candidates, List<Integer> res, List<List<Integer>> list) {
    	//System.out.println(res);
    	if (target == 0) {
    		//if (!list.contains(res)) {

    			list.add(new ArrayList<Integer>(res));
    		//}
    	}
    	
    	if (target < 0) {
    		return;
    	} else {

    		for (int i = start; i < candidates.length; i++) {
    			if(i > start && candidates[i] == candidates[i-1]) {
    				continue;
    			}
				res.add(candidates[i]);
				add(i, target-candidates[i], candidates, res, list);
				res.remove(res.size()-1);
    			
    		}
    	}

    	
    }
    
    public static void main(String[] args) {
    	
    	//int[] test = {1,2,5,10};
    	//System.out.println(combinationSum(test, 7));
    	
    	int[] test2 = {8,7,4,3,3};
    	System.out.println(combinationSum(test2, 10));
    	
	}
}
