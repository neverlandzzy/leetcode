import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	 * 
	 * For example,
	 * 
	 * If n = 4 and k = 2, a solution is:
	 * [
	 *   [2,4],
	 *   [3,4],
	 *   [2,3],
	 *   [1,2],
	 *   [1,3],
	 *   [1,4],
	 *]
	 */
	
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        combineHelper(result, list, n, k, 1);
        
        return result;
    }
    
    public static void combineHelper(List<List<Integer>> result, List<Integer> list, int n, int k, int start) {
    	if(list.size() == k) {
    		result.add(new ArrayList<Integer>(list));
    	} else {
    		for(int i = start; i <= n; i++) {
    			list.add(i);
    			combineHelper(result, list, n, k, i + 1);
    			list.remove(list.size()-1);
    		}
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(combine(4,2));
	}
}
