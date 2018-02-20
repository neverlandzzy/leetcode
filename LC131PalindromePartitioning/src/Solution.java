import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a string s, partition s such that every substring of the partition is a palindrome.
	 * 
	 * Return all possible palindrome partitioning of s.
	 * 
	 * For example, given s = "aab",
	 * 
	 * Return
	 * [
	 *    ["aa","b"],
	 *    ["a","a","b"]
	 * ]
	 */
	
	
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> list = new ArrayList<>();
        
        helper(result, list, s, 0);
        
        return result;
    }
    
    
    private static void helper(List<List<String>> result, List<String> list, String s, int pos) {
        if (pos == s.length()) {
            result.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = pos; i < s.length(); i++) {
            if (isValid(s, pos, i)) {
                list.add(s.substring(pos, i + 1));
                helper(result, list, s, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
    
    private static boolean isValid(String s, int i, int j) {
        if (s == null || s.length() <= 1) {
            return true;
        }
        
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            
            i++;
            j--;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
		String s = "aab";
		String s2 = "aaeeaa";
		System.out.println(partition(s));
		
		//System.out.println(isPalindrome(s, 0, s.length()-1));
		//System.out.println(isPalindrome(s2, 0, s2.length()-1));
	}
}
