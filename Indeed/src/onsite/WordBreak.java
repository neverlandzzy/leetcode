package onsite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
	
	// http://www.1point3acres.com/bbs/thread-207049-1-1.html
	// 只输出一种情况
	
	
	// Solution 1:
	public static List<String> wordBreak(String s, Set<String> set) {
		List<String> result = new ArrayList<>();
		helper(s, result, set, 0);

		return result;
	}
	
	private static boolean helper(String s, List<String> result, Set<String> set, int index) {
		if (index == s.length()) {
			return true;
		}	
		
		for (int i = index + 1; i <= s.length(); i++) {
			String sub = s.substring(index, i);
			if (set.contains(sub)) {
				result.add(sub);
				if (helper(s, result, set, i)) {
					return true;
				} else {
					result.remove(result.size() - 1);
				}
			}
		}
		
		return false;
		
	}
	
	// Solution 2: Better, easy implementation and understanding
	public static List<String> wordBreak2(String s, Set<String> set) {
		int n = s.length();
		boolean[] dp = new boolean[n + 1];
		String[] word = new String[n + 1];
		List<String> result = new ArrayList<>();
		
		word[0] = "";
		
        for (int i = 0; i <= n; i++) {
        	if (set.contains(s.substring(0, i))) {
        		dp[i] = true;
        		word[i] = s.substring(0, i) + " ";
        	} else {
	            for (int j = 0; j < i; j++) {
	            	if (dp[j] && set.contains(s.substring(j, i))) {	                
	                    dp[i] = true;
	                    word[i] = word[j] + s.substring(j, i) + " ";
	                    break;
	                }
	            }
        	}
        }
		
		if (dp[n]) {
			result.addAll(Arrays.asList(word[n].split(" ")));
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		Set<String> set = new HashSet<>(Arrays.asList("i", "am", "cow", "boy", "cowboy"));
		Set<String> set2 = new HashSet<>(Arrays.asList("cat", "cats", "and", "dog"));
		System.out.println(wordBreak("iamcowboy", set));
		System.out.println(wordBreak("catsanddog", set2));
		
		System.out.println(wordBreak2("iamcowboy", set));
		System.out.println(wordBreak2("catsanddog", set2));
	}
	
}
