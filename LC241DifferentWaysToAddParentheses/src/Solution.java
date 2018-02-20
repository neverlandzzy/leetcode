import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	
	/*
	 * Given a string of numbers and operators, return all possible results 
	 * from computing all the different possible ways to group numbers and operators. 
	 * The valid operators are +, - and *.
	 * 
	 * Example 1
	 * Input: "2-1-1".
	 * ((2-1)-1) = 0
	 * (2-(1-1)) = 2
	 * Output: [0, 2]
	 * 
	 * Example 2
	 * Input: "2*3-4*5"
	 * (2*(3-(4*5))) = -34
	 * ((2*3)-(4*5)) = -14
	 * ((2*(3-4))*5) = -10
	 * (2*((3-4)*5)) = -10
	 * (((2*3)-4)*5) = 10
	 * Output: [-34, -14, -10, -10, 10]
	 */

	static Set<Character> operators = new HashSet<Character>();
	static {operators.add('+'); operators.add('-'); operators.add('*');}
	static HashMap<String, List<Integer>> cache = new HashMap<String, List<Integer>>();
	
    public static List<Integer> diffWaysToCompute(String input) {
        // https://leetcode.com/discuss/51850/divide-and-conquer-cache-___java__260ms
    	
    	if (cache.containsKey(input)) {
    		return cache.get(input);
    	}
    	
    	List<Integer> result = new ArrayList<Integer>();
    	
    	for (int i = 0; i < input.length(); i++) {
    		char c = input.charAt(i);
    		
    		if (operators.contains(c)) {
    			List<Integer> left = diffWaysToCompute(input.substring(0, i));
    			List<Integer> right = diffWaysToCompute(input.substring(i + 1));
    			
				int sum = 0;
				
        		for (int m : left) {
        			for(int n: right) {      				
        				switch(c) {
	        				case '+': sum = m + n; break;
	        				case '-': sum = m - n; break;
	        				case '*': sum = m * n; break;
        				}
        				
        				result.add(sum);
        			}
        		}
    		}

    	}
    	
    	if (result.isEmpty()) {
    		result.add(Integer.valueOf(input));
    	}
    	
    	cache.put(input, result);
    	return result;
    	
    }
    
    public static void main(String[] args) {
		String test = "2*3-4*5";
		
		System.out.println(diffWaysToCompute(test));
	}
}
