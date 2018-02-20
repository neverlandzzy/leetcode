
public class Solution {
	/*
	 * Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.
	 * 
	 * A subsequence of a string S is obtained by deleting 0 or more characters from S.
	 * 
	 * A sequence is palindromic if it is equal to the sequence reversed.
	 * 
	 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
	 * 
	 * Example 1:
	 * Input: 
	 * S = 'bccb'
	 * Output: 6
	 * Explanation: 
	 * The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
	 * Note that 'bcb' is counted only once, even though it occurs twice.
	 * 
	 * Example 2:
	 * Input: 
	 * S = 'abcdabcdabcdabcdabcdabcdabcdabcddcbadcbadcbadcbadcbadcbadcbadcba'
	 * Output: 104860361
	 * Explanation: 
	 * There are 3104860382 different non-empty palindromic subsequences, which is 104860361 modulo 10^9 + 7.
	 * 
	 * Note:
	 * 
	 * 1. The length of S will be in the range [1, 1000].
	 * 2. Each character S[i] will be in the set {'a', 'b', 'c', 'd'}.
	 */
	
	// https://discuss.leetcode.com/topic/111483/java-96ms-dp-solution-with-detailed-explanation
	
    public static int countPalindromicSubsequences(String S) {
        int n = S.length();
        int M = 1000000007;
        
        // dp[i][j] to record in substring from i to j(included), the number of palindrome without duplicate.
        int[][] dp = new int[n][n];
        char[] chs = S.toCharArray();
        
        for (int i = 0; i < n; i++) {
        	dp[i][i] = 1;
        }
        
        // dp array从对角线填起 (0, 1), (1, 2), (2, 3) ...
        for (int len = 1; len < n; len++) {
        	for (int i = 0; i + len < n; i++) {
        		int j = i + len;
        		if (chs[i] == chs[j]) {
        			//当首尾字符相同时：
        			int low = i + 1;
        			int high = j - 1;
        			
        			while (low <= high && chs[low] != chs[j]) {
        				low++;
        			}
        			
        			while (low <= high && chs[high] != chs[i]) {
        				high--;
        			}
        			
        			if (low > high) {
        				// 在chs[i] ... chs[j]之间，没有字符和首尾字符相同 dp[i][j] = dp[i + 1][j - 1] * 2 + 2;  
        				// e.g. chs[0 - 2] = "aba", i = 0, j = 2
        				// dp[i + 1][j - 1] = dp[1][1] = 1 -> 'b', dp[i + 1][j - 1] * 2 --> 'b' and 'aba' 
        				// -->  即若chs[i]~[j]之间的字符构成palindrome个数为n, 则加上chs[i]和chs[j]后，构成palindrome个数为2n
        				// 另外，还要加上chs[i]和chs[j]本身可以构成的palindrome数， 2, 即'a'和'aa';
        				dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
        			} else if (low == high) {
        				// 在chs[i] ... chs[j]之间，只有一个字符和首尾字符相同 dp[i][j] = dp[i + 1][j - 1] * 2 + 1;  
        				// e.g. chs[0 - 2] = "aaa", i = 0, j = 2;
        				// dp[i + 1][j - 1] = dp[1][1] = 1 -> 'a', dp[i + 1][j - 1] * 2 --> 'a' and 'aaa' 
        				// 另外，还要加上chs[i]和chs[j]本身可以构成的palindrome数， 1, 此时，只有'aa', 因为中间有'a'，'a'已经被计算过
        				dp[i][j] = dp[i + 1][j - 1] * 2 + 1; 
        			} else {
        				// 在chs[i] ... chs[j]之间，有两个或以上的字符和首尾字符相同 dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1]; 
        				// e.g. chs[0 - 4] = "aacaa", i = 0, j = 4;
        				// dp[i + 1][j - 1] * 2和上述情况原因一样，除首位字符外，有{'a', 'c', 'aa', 'aca'}， 加上首尾字符后，新加的有{'aaa', 'aca', 'aaaa', 'aacaa'}；
        				// 此处有重复'aca'要减去
        				// chs[i]和chs[j]本身可以构成的palindrome数， 0, 此时，因为'a'和'aa', 因为中间有'a'，'aa'都已经被计算过
        				dp[i][j] = dp[i + 1][j - 1] * 2  - dp[low + 1][high - 1]; 
        			}
        		} else {
        			// 当首尾字符不同时：
        			// e.g. chs[0 - 2] = "abc", i = 0, j = 2 --> dp('ab') + 'c' 和'a' + dp('bc') 其中，'b'被重复计算，要减去 
        			dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
        		}

        		// 因为每级的dp[i][j]都不会超过M, 负数出现可能是取模后造成的。e.g. 10 - 4 = 6  10 % 10 - 4 % 10 = -4 所以负数加上M，即为正确的结果
        		dp[i][j] = dp[i][j] < 0 ? dp[i][j] + M : dp[i][j] % M;
        	}
        }
        
    	return dp[0][n - 1];
    }
    
    public static void main(String[] args) {
		//System.out.println(countPalindromicSubsequences("bccb"));
		System.out.println(countPalindromicSubsequences("bddaabdbbccdcdcbbdbddccbaaccabbcacbadbdadbccddccdbdbdbdabdbddcccadddaaddbcbcbabdcaccaacabdbdaccbaacc"));
		System.out.println(countPalindromicSubsequences("bcbacbabdcbcbdcbddcaaccdcbbcdbcabbcdddadaadddbdbbbdacbabaabdddcaccccdccdbabcddbdcccabccbbcdbcdbdaada"));
	}
}
