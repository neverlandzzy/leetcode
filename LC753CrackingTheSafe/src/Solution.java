import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
	 * 
	 * You can keep inputting the password, the password will automatically be matched against the last n digits entered.
	 * 
	 * For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
	 * 
	 * Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
	 * 
	 * Example 1:
	 * Input: n = 1, k = 2
	 * Output: "01"
	 * Note: "10" will be accepted too.
	 * 
	 * Example 2:
	 * Input: n = 2, k = 2
	 * Output: "00110"
	 * Note: "01100", "10011", "11001" will be accepted too.
	 * 
	 * Note:
	 * 1. n will be in the range [1, 4].
	 * 2. k will be in the range [1, 10].
	 * 3. k^n will be at most 4096.
	 */
	
	// https://leetcode.com/problems/cracking-the-safe/solution/
	// https://www.youtube.com/watch?v=iPLQgXUiU14
    public static String crackSafe(int n, int k) {
        if (n == 1 && k == 1) {
        	return "0";
        }
        
        Set<String> visited = new HashSet<>();
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < n - 1; i++) {
        	sb.append("0");
        }
        
        String start = sb.toString();
        helper(start, k, visited, result);
        result.append(start);
        
        return result.toString();
    }
    
    private static void helper(String s, int k, Set<String> visited, StringBuilder result) {
    	for (int i = 0; i < k; i++) {
    		String next = s + i;// edge
    		if (!visited.contains(next)) {
    			visited.add(next);
    			helper(next.substring(1), k, visited, result);
    			result.append(i);
    		}
    	}
    }
    
    public static void main(String[] args) {
		System.out.println(crackSafe(2, 3));
		//System.out.println(crackSafe(2, 2));
	}
}
