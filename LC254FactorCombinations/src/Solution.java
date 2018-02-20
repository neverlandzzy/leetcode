import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Numbers can be regarded as product of its factors. For example,
	 * 
	 * 8 = 2 x 2 x 2;
	 *   = 2 x 4.
	 *   
	 * Write a function that takes an integer n and return all possible combinations of its factors.
	 * 
	 * Note: 
	 * You may assume that n is always positive.
	 * Factors should be greater than 1 and less than n.
	 * 
	 * Examples: 
	 * input: 1
	 * output: 
	 * []
	 * 
	 * input: 37
	 * output: 
	 * []
	 * 
	 * input: 12
	 * output:
	 * [
	 *   [2, 6],
	 *   [2, 2, 3],
	 *   [3, 4]
	 * ]
	 * 
	 * input: 32
	 * output:
	 * [
     *   [2, 16],
  	 *   [2, 2, 8],
  	 *   [2, 2, 2, 4],
  	 *   [2, 2, 2, 2, 2],
  	 *   [2, 4, 4],
  	 *   [4, 8]
  	 * ]
	 */
	// Solution 1: slow
	/*
    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        helper(result, list, 2, n);
        return result;
    }
    
    private static void helper(List<List<Integer>> result, List<Integer> list, int start, int n) {
    	if (n == 1 && list.size() > 1) {
    		result.add(new ArrayList<Integer>(list));
    		return;
    	}
    	
    	for (int i = start; i <= n; i++) {
    		if (n % i == 0) {
    			list.add(i);
    			helper(result, list, i, n / i);
    			list.remove(list.size() - 1);
    		}
    	}
    }
    */
	
	//Solution 2:
    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        
        helper(result, list, 2, n);
        return result;
    }
	
    private static void helper(List<List<Integer>> result, List<Integer> list, int start, int n) {

    	for (int i = start; i <= Math.sqrt(n); i++) {
    		if (n % i == 0) {
    			list.add(i);
    			list.add(n / i);
    			result.add(new ArrayList<Integer>(list));
    			list.remove(list.size() - 1);
    			helper(result, list, i, n / i);
    			list.remove(list.size() - 1);
    		}
    	}
    }
    public static void main(String[] args) {
    	System.out.println(getFactors(12));
		//System.out.println(getFactors(37));
		//System.out.println(getFactors(32));
	}
}
