import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Find all possible combinations of k numbers that add up to a number n, 
	 * given that only numbers from 1 to 9 can be used and each combination should be 
	 * a unique set of numbers.
	 * 
	 * Ensure that numbers within the set are sorted in ascending order.
	 */
	
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        
        helper(result, list, k, n, 1);
        return result;
    }
    
    private static void helper(List<List<Integer>> result, List<Integer> list, int k, int n, int pos) {
        if (n < 0) {
            return;
        }
        
        if (list.size() == k) {
            if (n == 0) {
                result.add(new ArrayList<>(list));
            }
            return;
        }
        
        for (int i = pos; i <= 9; i++) {
            list.add(i);
            helper(result, list, k, n - i, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
    public static void main(String[] args) {
    	System.out.println(combinationSum3(3, 7));
    	System.out.println(combinationSum3(3, 9));
	}
}
