import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Solution {
	/*
	 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
	 * add spaces in s to construct a sentence where each word is a valid dictionary word. 
	 * You may assume the dictionary does not contain duplicate words.
	 * 
	 * Return all such possible sentences.
	 * 
	 * For example, given
	 * s = "catsanddog",
	 * 
	 * dict = ["cat", "cats", "and", "sand", "dog"].
	 * A solution is ["cats and dog", "cat sand dog"].
	 */

	// https://www.youtube.com/watch?v=pYKGRZwbuzs
   public static List<String> wordBreak(String s, List<String> wordDict) {

        HashMap<String,List<String>> map = new HashMap<String,List<String>>(); // 直接DFS会 TLE, 加map 剪枝 --> o(n^2)
        
        return helper(s, wordDict, map);

    }
    
    private static List<String> helper(String s, List<String> wordDict, HashMap<String,List<String>> map) {
    	if (map.containsKey(s)) {
    		return map.get(s);
    	}
    	
    	List<String> result = new ArrayList<>();
    	
    	if (s == null || s.length() == 0) {
    		return result;
    	}
    	
    	if (wordDict.contains(s)) {
    		result.add(s);
    	}
    	
    	for (int i = 1; i < s.length(); i++) {
    		 String t = s.substring(i);
    		 if (wordDict.contains(t)) {
    			 List<String> list = helper(s.substring(0, i), wordDict, map);
    			 //System.out.println("list = " + list);
    			 //System.out.println("before result = " + result);
    			 //System.out.println(" t = " + t);
    			 for (String word: list) {
    				 result.add(word + " " + t);
    			 }
    			 //System.out.println("after result = " + result);
    			 //System.out.println();
    		 }
    	}
    	//System.out.println("s = " + s + "  " + result);
    	map.put(s, result);
    	return result;

    }
	/* Loop 字典的方法不太好，因为字典往往会很大，应该loop字符串
    public static List<String> wordBreak(String s, List<String> wordDict) {
       
        HashMap<String, List<String>> map = new  HashMap<String, List<String>>();
        
        return helper(s, wordDict, map);
    }
    
    private static List<String> helper(String s, List<String> wordDict, HashMap<String, List<String>> map) {    
    	
    	if (map.containsKey(s)) {
    		return map.get(s);
    	}
    	
    	List<String> result = new ArrayList<String>();
    	
    	if (s.length() == 0) {

    		result.add("");
    		return result;
    	}
    	
    	for (String word: wordDict) {
    		if (s.startsWith(word)) {
    			List<String> list = helper(s.substring(word.length()), wordDict, map);
    			for (String sub : list) 
    				result.add(word + (sub.length() == 0 ? "" : " ") + sub);             
    		}
    	}
    	
    	map.put(s, result);    	
    	
    	return result;
    }
    
	*/
    public static void main(String[] args) {
    	List<String> wordDict = new ArrayList<String>();
    	List<String> wordDict2 = new ArrayList<String>();
    	
    	String s = "catsanddog";
    	wordDict.add("cat");
    	wordDict.add("cats");
    	wordDict.add("and");
    	wordDict.add("sand");
    	wordDict.add("dog");
    	
    	String s2 = "aaaaaaa";
    	wordDict2.add("a");
    	wordDict2.add("aa");
    	wordDict2.add("aaaa");
    	//wordDict2.add("bbbb");
    	
		//System.out.println(wordBreak(s, wordDict));
    	System.out.println(wordBreak(s2, wordDict2));
	}
}
