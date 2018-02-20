import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Solution {
	/*
	 * Special binary strings are binary strings with the following two properties:
	 * 
	 * The number of 0's is equal to the number of 1's.
	 * Every prefix of the binary string has at least as many 1's as 0's.
	 * 
	 * Given a special string S, a move consists of choosing two consecutive, non-empty, special 
	 * substrings of S, and swapping them. (Two strings are consecutive if the last character of the first string is exactly one 
	 * index before the first character of the second string.)
	 * 
	 * At the end of any number of moves, what is the lexicographically largest resulting string possible?
	 * 
	 * Example 1:
	 * Input: S = "11011000"
	 * Output: "11100100"
	 * Explanation:
	 * The strings "10" [occuring at S[1]] and "1100" [at S[3]] are swapped.
	 * This is the lexicographically largest string possible after some number of swaps.
	 * Note:
	 * 
	 * S has length at most 50.
	 * S is guaranteed to be a special binary string as defined above.
	 */

	// https://discuss.leetcode.com/topic/116280/easy-and-concise-solution-with-explanation-c-java-python
	
    // 将S分割成尽可能多的special substrings
	// 将分割后的substrings逆序排序，join后即为答案
	// 分割过程：
	//        11011000 --> 1 + 101100 + 0  --> 返回 11100100
	//        101100 --> 1 + 0, 1 + 10 + 0 --> 返回 110010
	//        10     --> 1 + 0   --> 返回 10
	// 每当counter == 0 时，能够保证S.substring(j + 1, i)一定是special substring. 
	// 因为输入的S是special string, 第一个字符一定是1，counter一定是从正数开始，当遇到counter == 0时，就进入下一个递归。
	// 此时扫描过的前缀中1的个数等于0的个数，剩余的后缀，一定是从1开始，否则说明输入的字符前缀0多余前缀1，不是special string
	// e.g. 101100会被分割成10, 1100 而不是1 + 0110 + 0
	
    public static String makeLargestSpecial(String S) {
    	List<String> result = new ArrayList<>();
    	int j = 0;
    	int counter = 0;
    	
    	for (int i = 0; i < S.length(); i++) {
    		if (S.charAt(i) == '1') {
    			counter++;
    		} else {
    			counter--;
    		}
    		
    		if (counter == 0) {
    			result.add("1" + makeLargestSpecial(S.substring(j + 1, i)) + "0");
    			j = i + 1;
    		}
    	}
    	//System.out.println(result);
    	Collections.sort(result, Collections.reverseOrder());
    	
    	return String.join("", result);
    }
    
    public static void main(String[] args) {
		System.out.println(makeLargestSpecial("11011000"));
		//System.out.println(makeLargestSpecial("101010"));
	}
}
