package phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PalindromePermutation {
	/*
	 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=307737
	 * 
	 * Write an efficient function that checks whether any permutation of an input string is a palindrome
	 * Follow up比较多
	 * 
	 * LC266
	 * 
	 * E.g.
	 * "civic"  --> true;
	 * "ivicc"  --> true;
	 * "civil"  --> false;
	 * "livci"  --> false;
	 *
	 * Follow up 练习：
	 * LC267, LC5, LC409, LC214, LC336, LC647
	 */
	
	// LC266: 给一个字符串，看它的各种排列组合能否构成一个palindrome，也就是字母两两消掉后，剩下的字母不能多于1个
    public static boolean canPermutePalindrome(String s) {
        Set<Character> set = new HashSet<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (set.contains(c)) {
                set.remove(c);
            } else {
                set.add(c);
            }
        }
        
        return set.size() <= 1;
    }
    
    // LC267: 给一个字符串，求其能够成palindrome的全部全排列
    // Given s = "aabb", return ["abba", "baab"].
    public static List<String> generatePalindromes(String s) {
        Map<Character, Integer> map = new HashMap<>();
        List<Character> list = new ArrayList<>();
        List<String> result = new ArrayList<>();
    
        int odd = 0;
        String mid = "";
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            odd += map.get(c) % 2 == 0 ? -1 : 1;
        }
        
        if (odd > 1) {
            return result;
        }
        
        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            char c = entry.getKey();
            int val = entry.getValue();
            
            if (val % 2 != 0) {
                mid += c;
            }
            
            for (int i = 0; i < val / 2; i++) {
                list.add(c);
            }
        }
        
        boolean[] visited = new boolean[list.size()];
        StringBuilder sb = new StringBuilder();
        helper(result, list, visited, sb, mid);
        
        return result;
    }
    
    private static void helper(List<String> result, List<Character> list, boolean[] visited, StringBuilder sb, String mid) {
        if (sb.length() == list.size()) {
            result.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }
        
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i - 1) == list.get(i) && !visited[i - 1]) {
                continue;
            }
            
            if (!visited[i]) {
                sb.append(list.get(i));
                visited[i] = true;
                helper(result, list, visited, sb, mid);
                sb.deleteCharAt(sb.length() - 1);
                visited[i] = false;
            }
        }
    }
    
    // LC5 返回一个字符串中的最长palindrome子字符串 
    // Input: "babad"
    // Output: "bab"
    // Solution 1: Time: O(n^2), Space: O(n^2)
    public static String longestPalindromeI(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        int n = s.length();
        int start = 0;
        int end = 0;
        int max = 0;
        boolean[][] dp = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[j][i] = (s.charAt(j) == s.charAt(i) && ((i - j <= 2) || (dp[j + 1][i - 1])));
                
                if (dp[j][i] && max < i - j + 1) {
                    max = i - j + 1;
                    start = j;
                    end = i;
                }
            }
        }
        
        return s.substring(start, end + 1);
    }
    
    // LC5: Solution 2 -- Time: O(n^2), Space: O(1)
	public static String longestPalindromeII(String s) {
		int start = 0;
		int end = 0;
		
		if (s == null || s.length() == 0) {
			return s;
		}
		
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			
			int len = Math.max(len1, len2);
			
			if (len > end - start + 1) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2;
			}
		}
		
		return s.substring(start, end + 1);
	}
	
	
	private static int expandAroundCenter(String s, int left, int right) {
		
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		
		return right - left - 1;
	}
	
	// LC 409: 给一个字符串，求其中字母可以组成的最长palindrome的字符串长度
	// Input: "abccccdd"   Output: 7
    public static int longestPalindromeIII(String s) {
    	int[] count = new int[128];
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		count[c]++;
    	}
    	
    	int sum = 0;
    	int odd = 0;
    	
    	for (int i = 0; i < 128; i++) {
    		if (count[i] % 2 == 0) {
    			sum += count[i];
    		} else {
    			sum += count[i] - 1;
    			odd = 1;
    		}
    	}
    	
    	return sum + odd;
    }
    
    // LC 214 给一个字符串，可以在其前面加上一些字符使其成为Palindrome，求可以构成的最短字符串
    // Given "aacecaaa", return "aaacecaaa".
    // Given "abcd", return "dcbabcd".
    // 利用LC5的方法找从第一个字符开始的Palindrome，再把后面剩余的子串翻转后加在开头
    
    
    // LC 336 找出list中所有可以拼成Palindrome的两个字符串
    // Given words = ["bat", "tab", "cat"]
	// Return [[0, 1], [1, 0]]
    
    // LC 647 找出一个字符串里有多少个palindromic的子字符串
    // Input: "abc"
    // Output: 3
    // 解法： 与LC5一样，expandAroundCenter，每遇到一个Palindrome则counter++

    public static void main(String[] args) {
    	
    	System.out.println("===== LC 266 ======");
		String test11 = "civic";
		String test12 = "ivicc";
		String test13 = "civil";
		String test14 = "livci";
		
		System.out.println(canPermutePalindrome(test11));
		System.out.println(canPermutePalindrome(test12));
		System.out.println(canPermutePalindrome(test13));
		System.out.println(canPermutePalindrome(test14));
		System.out.println();
		
    	System.out.println("===== LC 267 ======");
		String test21 = "civic";
		String test22 = "ivicc";
		String test23 = "civil";
		String test24 = "aabb";
		
		System.out.println(generatePalindromes(test21));
		System.out.println(generatePalindromes(test22));
		System.out.println(generatePalindromes(test23));
		System.out.println(generatePalindromes(test24));
		System.out.println();
		
		
    	System.out.println("===== LC 5 ======");
		String test31 = "babad";
		String test32 = "ivicc";
		String test33 = "civil";
		
		System.out.println(longestPalindromeI(test31));
		System.out.println(longestPalindromeI(test32));
		System.out.println(longestPalindromeI(test33));
		System.out.println(longestPalindromeII(test31));
		System.out.println(longestPalindromeII(test32));
		System.out.println(longestPalindromeII(test33));		
		System.out.println();
		
		System.out.println("===== LC 409 ======");
		String test41 = "abccccdd";
		System.out.println(longestPalindromeIII(test41));
	}
}
