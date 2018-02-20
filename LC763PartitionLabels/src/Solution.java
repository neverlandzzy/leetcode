import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * A string S of lowercase letters is given. We want to partition this string into as many parts as possible so that each letter appears 
	 * in at most one part, and return a list of integers representing the size of these parts.
	 * 
	 * Example 1:
	 * Input: S = "ababcbacadefegdehijhklij"
	 * Output: [9,7,8]
	 * 
	 * Explanation:
	 * The partition is "ababcbaca", "defegde", "hijhklij".
	 * This is a partition so that each letter appears in at most one part.
	 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
	 * Note:
	 * 
	 * S will have length in range [1, 500].
	 * S will consist of lowercase letters ('a' to 'z') only.
	 */
	
    public static List<Integer> partitionLabels(String S) {
    	// 记录S中每一个字符最后出现的位置
        int[] map = new int[26];
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < S.length(); i++) {
        	map[S.charAt(i) - 'a'] = i;
        }
        
        int last = 0;
        int start = -1;
        for (int i = 0; i < S.length(); i++) {
        	last = Math.max(last, map[S.charAt(i) - 'a']);
        	if (last == i) {
        		result.add(i - start);
        		start = i;
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String s = "ababcbacadefegdehijhklij";
		
		System.out.println(partitionLabels(s));
	}
}
