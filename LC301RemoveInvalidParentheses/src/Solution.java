import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Solution {
	/*
	 * Remove the minimum number of invalid parentheses in order to make the input string valid. 
	 * Return all possible results.
	 * 
	 * Note: The input string may contain letters other than the parentheses ( and ).
	 * 
	 * Examples:
	 * 
	 * "()())()" -> ["()()()", "(())()"]
	 * "(a)())()" -> ["(a)()()", "(a())()"]
	 * ")(" -> [""]
	 */
	
	// https://leetcode.com/discuss/81478/easy-short-concise-and-fast-java-dfs-3-ms-solution
	// FB常考题，两种方法都要掌握
	
	// Solution 1: DFS	O(n^2)
	/*
    public static List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        
        dfs(s, result, 0, 0, new char[] {'(', ')'});
        
        return result;
    }
    
    // m: 上一次删掉')'的位置
    private static void dfs (String s, List<String> result, int pos, int m, char[] par) {
		int stack = 0;
		//System.out.println(s);
    	for (int i = pos; i < s.length(); i++) {

    		if (s.charAt(i) == par[0]) stack++;
    		if (s.charAt(i) == par[1]) stack--;
    		
    		if (stack < 0) {
    			for (int j = m; j <= i; j++) {
    				if (s.charAt(j) == par[1] && (j == m || s.charAt(j - 1) != par[1])) {
    					dfs(s.substring(0, j) + s.substring(j + 1), result, i, j, par);
    				}
    			}
    			// 对于当前的遍历，只要移除了多余的'('或者')'就已经是valid字符串，因此要返回
    			return;
    		}
    	}
    	
    	String reversed = new StringBuilder(s).reverse().toString();
    	
    	if (par[0] == '(') {
    		//System.out.println("reversed: " + reversed);
    		dfs (reversed, result, 0, 0, new char[]{')', '('});
    	} else {
    		//System.out.println("add: " + reversed);
    		result.add(reversed);
    	}
    	 	
    }
    */
	
	// Solution 2: BFS
	public static List<String> removeInvalidParentheses(String s) {
		List<String> result = new ArrayList<>();
		
		if (s == null || s.length() == 0) {
			result.add(s);
			return result;
		}
		
		Set<String> visited = new HashSet<>();
		Queue<String> queue = new LinkedList<>();
		
		visited.add(s);
		queue.offer(s);
		
		boolean found = false;
		
		while (!queue.isEmpty()) {
			
			String cur = queue.poll();
			if (isValid(cur)) {
				result.add(cur);
				found = true;
			}
			
			// 要求删掉的是minimal number of invalid parentheses，当找到一个valid的s后，不再对s进行继续的拆分，只要将当前queue中的字符检查一遍就可，
			// 因为继续拆分将不再是minimal的步数。
			if (found) {
				continue;
			}
			
			for (int i = 0; i < cur.length(); i++) {
				if (cur.charAt(i) != '(' && cur.charAt(i) != ')') {
					continue;
				}
				
				String t = cur.substring(0, i) + cur.substring(i + 1);
				
				if (!visited.contains(t)) {
					queue.offer(t);
					visited.add(t);
				}
			}
		}
		
		return result;
	}
	
	private static boolean isValid(String s) {
		int count = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				count++;
			}
			
			if (s.charAt(i) == ')') {
				count--;
			}
			
			if (count < 0) {
				return false;
			}
		}
		
		return count == 0;
	}
	
    public static void main(String[] args) {
		String test1 = "()())()";
		String test2 = "(a)())()";
		
		System.out.println(removeInvalidParentheses(test1));
		//System.out.println(removeInvalidParentheses(test2));

	}
}
