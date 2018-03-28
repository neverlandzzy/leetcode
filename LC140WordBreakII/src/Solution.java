import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


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
    	
    	for (int i = 0; i < s.length(); i++) {
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
    
    /*
    // 解法3：重新剪枝。
    */
    // 我们用DFS来解决这个问题吧 
    public static List<String> wordBreak3(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null) {
            return null;
        }
        
        List<String> ret = new ArrayList<String>();
        
        // 记录切割过程中生成的字母
        List<String> path = new ArrayList<String>();
        
        int len = s.length();
        
        // 注意：一定要分配 Len+1 否则会爆哦.
        boolean canBreak[] = new boolean[len + 1];
        for (int i = 0; i < len + 1; i++) {
            canBreak[i] = true;
        }
            
        dfs3(s, wordDict, path, ret, 0, canBreak);
        
        return ret;
    }

    // 我们用DFS模板来解决这个问题吧 
    public static void dfs3(String s, List<String> wordDict, 
            List<String> path, List<String> ret, int index,
            boolean canBreak[]) {
        int len = s.length();
        if (index == len) {
            // 结束了。index到了末尾
            StringBuilder sb = new StringBuilder();
            for (String str: path) {
                sb.append(str);
                sb.append(" ");
            }
            // remove the last " "
            sb.deleteCharAt(sb.length() - 1);
            ret.add(sb.toString());
            return;
        }
        
        // if can't break, we exit directly.
        if (!canBreak[index]) {
            return;
        }

        for (int i =  index; i < len; i++) {
            // 注意这些索引的取值。左字符串的长度从0到len
            String left = s.substring(index, i + 1);
            if (!wordDict.contains(left) || !canBreak[i + 1]) {
                // 如果左字符串不在字典中，不需要继续递归
                continue;
            }
            
            // if can't find any solution, return false, other set it 
            // to be true;
            path.add(left);
            
            int beforeChange = ret.size();
            dfs3(s, wordDict, path, ret, i + 1, canBreak);
            // 注意这些剪枝的代码. 关键在于此以减少复杂度
            if (ret.size() == beforeChange) {
                canBreak[i + 1] = false;    
            }
            path.remove(path.size() - 1);
        }
    }
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
    	
    	System.out.println(wordBreak(s, wordDict));
    	//System.out.println(wordBreak(s2, wordDict2));
	}
}
