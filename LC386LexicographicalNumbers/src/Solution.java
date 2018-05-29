import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given an integer n, return 1 - n in lexicographical order.
	 * 
	 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
	 * 
	 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
	 */
	
	
	// Solution 1: TLE O(nlogn)
	/*
    public static List<Integer> lexicalOrder(int n) {
    	List<Integer> result = new ArrayList<>();
    	
    	for (int i = 1; i <= n; i++) {
    		result.add(i);
    	}
    	
    	Collections.sort(result, new Comparator<Integer>() {
    		public int compare(Integer i1, Integer i2) {
    			String s1 = String.valueOf(i1);
    			String s2 = String.valueOf(i2);
    			int min = Math.min(s1.length(), s2.length());
    			
    			for (int i = 0; i < min; i++) {
    				if (s1.charAt(i) < s2.charAt(i)) {
    					return -1;
    				} else if (s1.charAt(i) > s2.charAt(i)) {
    					return 1;
    				} 
    			}
    			
    			if (s1.length() == min) {
    				return -1;
    			} else {
    				return 1;
    			}
    		}
    	});
    	
    	return result;
    }
    */
	
	// Solution 2: DFS
	// https://leetcode.com/problems/lexicographical-numbers/discuss/86231/Simple-Java-DFS-Solution
	//    1           2
	//   / \         / \         .....
	//  10 19       20  29
	/*
    public static List<Integer> lexicalOrder(int n) {
    	List<Integer> result = new ArrayList<>();
    	
    	for (int i = 1; i < 10; i++) {
    		dfs(i, n, result);
    	}
    	
    	return result;
    }
    
    public static void dfs(int cur, int n, List<Integer> result) {
    	if (cur > n) {
    		return;
    	}
    	
    	result.add(cur);
    	
    	for (int i = 0; i < 10; i++) {
    		if (cur * 10 + i <= n) {
    			dfs(cur * 10 + i, n, result);
    		}
    	}
    }
    */
	
    // Solution 3: Iteration
	// https://leetcode.com/problems/lexicographical-numbers/discuss/86242/Java-O(n)-time-O(1)-space-iterative-solution-130ms
	// e.g. cur = 45, 
	//        if 450 <= n then next num = 450 (condition 1)
	//        else next num = 46 (condition 2)
	//      cur = 499,
	//        next num should be 5 -- 499 / 10 until last digit is not 9
	//        
    public static List<Integer> lexicalOrder(int n) {
    	List<Integer> result = new ArrayList<>();
    	int cur = 1;
    	
    	for (int i = 1; i <= n; i++) {
    		result.add(cur);
    		
    		if (cur * 10 <= n) {
    			cur *= 10;
    		} else if (cur % 10 != 9 && cur + 1 <= n) {
    			cur += 1;
    		} else {
    			while ((cur / 10) % 10 == 9) {
    				cur /= 10;
    			}  			
    			cur = cur / 10 + 1;
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		System.out.println(lexicalOrder(13));
	}
}
