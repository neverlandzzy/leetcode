import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
	/**
	 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  
	 * Return a list of all possible strings we could create.
	 * 
	 * Examples:
	 * Input: S = "a1b2"
	 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
	 * 
	 * Input: S = "3z4"
	 * Output: ["3z4", "3Z4"]
	 * 
	 * Input: S = "12345"
	 * Output: ["12345"]
	 * 
	 * Note:
	 * 	1. S will be a string with length at most 12.
	 * 	2. S will consist only of letters or digits.
	 */
	
	// Solution 1: BFS Time: O(2^N * N)
	/*
    public static List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        if (S == null || S.length() == 0) {
            result.add("");
        	return result;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(S);
        
        for (int i = 0; i < S.length(); i++) {
        	if (Character.isLetter(S.charAt(i))) {
        		int size = queue.size();
        		for (int j = 0; j < size; j++) {
        			String cur = queue.poll();
        			char[] chs = cur.toCharArray();
        			
                    chs[i] = Character.toUpperCase(chs[i]);
                    queue.offer(String.valueOf(chs));
                    
                    chs[i] = Character.toLowerCase(chs[i]);
                    queue.offer(String.valueOf(chs));        			
        		}
        	}
        }
        
        while (!queue.isEmpty()) {
            result.add(queue.poll());
        }   
        
        return result;
    }
    */
	
	// Solution 2: DFS Time: O(2^N * N)
	public static List<String> letterCasePermutation(String S) {
		List<String> result = new ArrayList<>();
		if (S == null || S.length() == 0) {
			result.add(S);
			return result;
		}
		
		helper(S, result, 0);
		return result;
	}
	
	private static void helper(String s, List<String> result, int pos) {
		if (pos == s.length()) {
			result.add(s);
			return;
		}
		
		if (Character.isDigit(s.charAt(pos))) {
			helper(s, result, pos + 1);
			return;
		}
		
		char[] chs = s.toCharArray();
		
		chs[pos] = Character.toLowerCase(chs[pos]);
		helper(String.valueOf(chs), result, pos + 1);
		chs[pos] = Character.toUpperCase(chs[pos]);
		helper(String.valueOf(chs), result, pos + 1);
	}

	// Solution 3: similar to solution 2
	/*
	public static List<String> letterCasePermutation(String S) {
		List<String> result = new ArrayList<>();
		helper(result, S, 0, new StringBuilder());

		return result;
	}

	private static void helper(List<String> result, String S, int pos, StringBuilder sb) {
		if (sb.length() == S.length()) {
			result.add(sb.toString());
			return;
		}

		for (int i = pos; i < S.length(); i++) {
			char c = S.charAt(i);

			if (Character.isLetter(c)) {
				sb.append(Character.toLowerCase(c));
				helper(result, S, i + 1, sb);
				sb.deleteCharAt(sb.length() - 1);

				sb.append(Character.toUpperCase(c));
				helper(result, S, i + 1, sb);
				sb.deleteCharAt(sb.length() - 1);
			} else {
				sb.append(c);
				helper(result, S, i + 1, sb);
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}
	*/
	
    public static void main(String[] args) {
		System.out.println(letterCasePermutation("a1b2"));
		System.out.println(letterCasePermutation("3z4"));
		System.out.println(letterCasePermutation("12345"));
	}
}
