import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/*
	 * You have 4 cards each containing a number from 1 to 9. You need to judge whether they could operated through *, /, +, -, (, ) 
	 * to get the value of 24.
	 * 
	 * Example 1:
	 * Input: [4, 1, 8, 7]
	 * Output: True
	 * Explanation: (8-4) * (7-1) = 24
	 * 
	 * Example 2:
	 * Input: [1, 2, 1, 2]
	 * Output: False
	 * 
	 * Note:
	 * 1. The division operator / represents real division, not integer division. For example, 4 / (1 - 2/3) = 12.
	 * 2. Every operation done is between two numbers. In particular, we cannot use - as a unary operator. For example, with [1, 1, 1, 1] 
	 *    as input, the expression -1 - 1 - 1 - 1 is not allowed.
	 * 3. You cannot concatenate numbers together. For example, if the input is [1, 2, 1, 2], we cannot write this as 12 + 12.
	 */
	
	// https://discuss.leetcode.com/topic/103962/java-easy-to-understand-backtracking
    public static boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        
        for (int n: nums) {
        	list.add((double) n);
        }
        
        return helper(list);
    }
    
    private static boolean helper(List<Double> list) {
    	double error = 0.00001;
    	
    	if (list.size() == 0) {
    		return false;
    	}
    	
    	if (list.size() == 1) {
    		if (Math.abs(list.get(0) - 24.0) < error) {
    			return true;
    		}
    		
    		return false;
    	}

        
    	for (int i = 0; i < list.size(); i++) {
    		for (int j = 0; j < i; j++) {
    			double n1 = list.get(i);
    			double n2 = list.get(j);
    			List<Double> next = new ArrayList<>(Arrays.asList(n1 + n2, n1 - n2, n1 * n2, n2 - n1));
    			if (Math.abs(n1) > error) {
    				next.add(n2 / n1);
    			}
    			
    			if (Math.abs(n2) > error) {
    				next.add(n1 / n2);
    			}
    			
    			list.remove(i);
    			list.remove(j);
    			
    			for (double d: next) {
    				list.add(d);
    				if (helper(list)) {
    					return true;
    				}
    				list.remove(list.size() - 1);
    			}
    			
    			// 注意先加j,再加i，因为j < i
    			list.add(j, n2);
    			list.add(i, n1);
    		}
    	}
    	
		return false;
    }
    
    public static void main(String[] args) {
		int[] test1 = {4, 1, 8, 7};
		int[] test2 = {1, 2, 1, 2};
		int[] test3 = {3, 3, 8, 8};
		
		//System.out.println(judgePoint24(test1));
		//System.out.println(judgePoint24(test2));
		System.out.println(judgePoint24(test3));
	}
}
