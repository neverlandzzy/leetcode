import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Solution {
	/**
	 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, 
	 * determine if two sentences are similar.
	 * 
	 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the similar word pairs are 
	 * pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
	 * 
	 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, 
	 * then "great" and "fine" are similar.
	 * 
	 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
	 * 
	 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, 
	 * even though there are no specified similar word pairs.
	 * 
	 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to 
	 * words2 = ["doubleplus","good"].
	 * 
	 * Note:
	 * 1. The length of words1 and words2 will not exceed 1000.
	 * 2. The length of pairs will not exceed 2000.
	 * 3. The length of each pairs[i] will be 2.
	 * 4. The length of each words[i] and pairs[i][j] will be in the range [1, 20].
	 */
	
	// Solution 1: DFS: Time: O(NP) where N is the maximum length of words1 and words2, and P is the length of pairs. 
	//                              Each of N searches could search the entire graph.
	//
    //                  Space: O(P), the size of pairs.
	
    public static boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
    	
    	if (words1.length != words2.length) {
    		return false;
    	}
    	
	    Map<String, Set<String>> map = new HashMap<>();
	    
	    for (String[] pair: pairs) {
		   if (!map.containsKey(pair[0])) {
			   map.put(pair[0], new HashSet<>());
		   }
		   if (!map.containsKey(pair[1])) {
			   map.put(pair[1], new HashSet<>());
		   }
		   
		   map.get(pair[0]).add(pair[1]);
		   map.get(pair[1]).add(pair[0]);
	    }
	    
	    for (int i = 0; i < words1.length; i++) {
	    	String w1 = words1[i];
	    	String w2 = words2[i];
	    	
	    	if (w1.equals(w2)) {
	    		continue;
	    	}
	    	
	    	if (!map.containsKey(w1)) {
	    		return false;
	    	}
	    	
	    	if (!dfs(w1, w2, map, new HashSet<>())) {
	    		return false;
	    	}
	    }
	    return true;
    }
    
    private static boolean dfs(String w1, String w2, Map<String, Set<String>> map, Set<String> visited) {
    	if (map.get(w1).contains(w2)) {
    		return true;
    	}
    	
    	visited.add(w1);
    	
    	for (String s: map.get(w1)) {
    		if (!visited.contains(s) && dfs(s, w2, map, visited)) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    
    
    
    public static void main(String[] args) {
		String[][] pairs = {{"great", "good"}, {"fine", "good"}, {"acting","drama"}, {"skills","talent"}};
		String[] words1 = {"great", "acting", "skills"};
		String[] words2 = {"fine", "drama", "talent"};
		
		System.out.println(areSentencesSimilarTwo(words1, words2, pairs));
	}
}
