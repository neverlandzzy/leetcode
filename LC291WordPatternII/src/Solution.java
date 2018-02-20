import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Given a pattern and a string str, find if str follows the same pattern.
	 * 
	 * Here follow means a full match, such that there is a bijection between a letter in 
	 * pattern and a non-empty substring in str.
	 * 
	 * Examples:
	 * 
	 * pattern = "abab", str = "redblueredblue" should return true.
	 * pattern = "aaaa", str = "asdasdasdasd" should return true.
	 * pattern = "aabb", str = "xyzabcxzyabc" should return false.
	 * 
	 * Notes:
	 * You may assume both pattern and str contains only lowercase letters.
	 */
	
	//https://discuss.leetcode.com/topic/36076/java-hashset-backtracking-2ms-beats-100
	
	// a little slower, but clean and easy to understand
    public static boolean wordPatternMatch(String pattern, String str) {
        String[] map = new String[26];
        Set<String> set = new HashSet<String>();
        return helper(str, pattern, map, set, 0, 0);
    }
    
    private static boolean helper(String str, String pattern, String[] map, Set<String> set, 
    		int strIndex, int patternIndex){
    	
    	// 当pattern和str同时到头，说明match
    	if (strIndex == str.length() && patternIndex == pattern.length()) {
    		return true;
    	}
    	
    	// 当pattern和str只有一个到头，说明不match
        if (strIndex == str.length() || patternIndex == pattern.length()) {
            return false;
        }
    	
    	char c = pattern.charAt(patternIndex);
    	String matched = map[c -'a'];
    	
        

    	if (matched != null) {
            
            if (!str.startsWith(matched, strIndex)) {
                return false;
            }
            
            return helper(str, pattern, map, set, strIndex + matched.length(), patternIndex + 1);

    	} else {
  		
    		for (int i = strIndex; i < str.length(); i++) {
    			matched = str.substring(strIndex, i + 1);
    			
    			// 对于同一段字符串，不能有不同的pattern去match
    			if (set.contains(matched)) {
    				continue;
    			}
    			
    			map[c - 'a'] = matched;
    			set.add(matched);
    			
                if (helper(str, pattern, map, set, i + 1, patternIndex + 1)) {
                    return true;
                }
                
                map[c - 'a'] = null;
                set.remove(matched);
    		}
    	}
    	return false;   	
    }
    
    // faster with optimization
	/*
    public static boolean wordPatternMatch(String pattern, String str) {
        String[] map = new String[26];
        Set<String> set = new HashSet<String>();
        return helper(pattern, str, map, set, 0, str.length() - 1, 0, pattern.length() - 1);
    }
    
    private static boolean helper(String pattern, String str, String[] map, Set<String> set, 
    		int start, int end, int startPattern, int endPattern){
    	
    	// 当pattern和str同时到头，说明match
    	if (start == end + 1 && startPattern == endPattern + 1) {
    		return true;
    	}
    	
    	// 当pattern和str只有一个到头，说明不match
        if (start == end + 1 || startPattern == endPattern + 1) {
            return false;
        }
    	
    	char c = pattern.charAt(startPattern);
    	String matched = map[c -'a'];
    	
    	if (matched != null) {
    		// 此处要与end + 1比较， e.g. abab redblueredblue 第二个b的start是10， length是4
    		return (start + matched.length() <= end + 1) && matched.equals(str.substring(start, start + matched.length()))
    				&& helper(pattern, str, map, set, start + matched.length(), end, startPattern + 1, endPattern);
    	} else {
    		// 省略不必要的搜索
    		int endPoint = end;
    		for (int i = endPattern; i > startPattern; i--) {
    			if (map[pattern.charAt(i)-'a'] != null) {
    				//当末位Pattern有match的时候，从str中减去match的长度再搜索
    				endPoint -= map[pattern.charAt(i)-'a'].length();
    			} else {
    				//当末位Pattern没有match的时候，i每往前挪一位，从str中减1，因为str中至少要留1位与pattern最后1位match
    				endPoint -= 1;
    			}
    		}
    		
    		for (int i = start; i <= endPoint; i++) {
    			matched = str.substring(start, i + 1);
    			
    			// 对于同一段字符串，不能有不同的pattern去match
    			if (set.contains(matched)) {
    				continue;
    			}
    			
    			map[c - 'a'] = matched;
    			set.add(matched);
    			
                if (helper(pattern, str, map, set, i + 1, end, startPattern + 1, endPattern)) {
                    return true;
                }
                
                map[c - 'a'] = null;
                set.remove(matched);
    		}
    	}
    	return false;   	
    }
    
    */
    public static void main(String[] args) {
    	System.out.println(wordPatternMatch("abab", "redblueredblue"));
    	System.out.println(wordPatternMatch("aaaa", "asdasdasdasd"));
    	System.out.println(wordPatternMatch("aabb", "xyzabcxzyabc"));    	
	}
}
