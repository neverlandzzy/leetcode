import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/**
	 * You are given an array A of strings.
	 * 
	 * Two strings S and T are special-equivalent if after any number of moves, S == T.
	 * 
	 * A move consists of choosing two indices i and j with i % 2 == j % 2, and swapping S[i] with S[j].
	 * 
	 * Now, a group of special-equivalent strings from A is a non-empty subset S of A such that any string not in S is not special-equivalent 
	 * with any string in S.
	 * 
	 * Return the number of groups of special-equivalent strings from A. 
	 * 
	 * Example 1:
	 * 
	 * Input: ["a","b","c","a","c","c"]
	 * Output: 3
	 * Explanation: 3 groups ["a","a"], ["b"], ["c","c","c"]
	 * 
	 * Example 2:
	 * 
	 * Input: ["aa","bb","ab","ba"]
	 * Output: 4
	 * Explanation: 4 groups ["aa"], ["bb"], ["ab"], ["ba"]
	 * 
	 * Example 3:
	 * 	 
	 * Input: ["abc","acb","bac","bca","cab","cba"]
	 * Output: 3
	 * Explanation: 3 groups ["abc","cba"], ["acb","bca"], ["bac","cab"]
	 * 
	 * Example 4:
	 * 
	 * Input: ["abcd","cdab","adcb","cbad"]
	 * Output: 1
	 * Explanation: 1 group ["abcd","cdab","adcb","cbad"] 
	 * 
	 * Note:
	 * 	1. 1 <= A.length <= 1000
	 * 	2. 1 <= A[i].length <= 20
	 * 	3. All A[i] have the same length.
	 *  4. All A[i] consist of only lowercase letters.
	 */
	
    public static int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        
        for (String s: A) {
        	set.add(swap(s));
        }
        
        return set.size();
    }
    
    private static String swap(String s) {
    	char[] strs = s.toCharArray();
    	List<Character> even = new ArrayList<>();
    	List<Character> odd  = new ArrayList<>(); 
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < strs.length; i += 2) {
    		even.add(strs[i]);
    	}
    	for (int i = 1; i < strs.length; i += 2) {
    		odd.add(strs[i]);
    	}
    	
    	Collections.sort(even);
    	Collections.sort(odd);

    	even.forEach(sb::append);
    	odd.forEach(sb::append);

    	return sb.toString();
    }
    
    // Another Solution, similar idea:
    /*
    public static int numSpecialEquivGroups(String[] A) {
        Set<String> seen = new HashSet();
        for (String S: A) {
            int[] count = new int[52];
            for (int i = 0; i < S.length(); ++i)
                count[S.charAt(i) - 'a' + 26 * (i % 2)]++;
            seen.add(Arrays.toString(count));
        }
        return seen.size();
    }
    */
    
    public static void main(String[] args) {
		String[] test21 = {"a","b","c","a","c","c"};
		String[] test22 = {"aa","bb","ab","ba"};
		String[] test23 = {"abc","acb","bac","bca","cab","cba"};
		String[] test24 = {"abcd","cdab","adcb","cbad"};
		
		System.out.println(numSpecialEquivGroups(test21));
		System.out.println(numSpecialEquivGroups(test22));
		System.out.println(numSpecialEquivGroups(test23));
		System.out.println(numSpecialEquivGroups(test24));
	}
}
