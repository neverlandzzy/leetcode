import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WordDistance {
	/*
	 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your 
	 * method will be called repeatedly many times with different parameters. How would you optimize it?
	 * 
	 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 
	 * and return the shortest distance between these two words in the list.
	 * 
	 * For example,
	 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	 * 
	 * Given word1 = “coding”, word2 = “practice”, return 3.
	 * Given word1 = "makes", word2 = "coding", return 1.
	 * 
	 * Note:
	 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
	 */
	
	Map<String, List<Integer>> map;
	
    public WordDistance(String[] words) {
    	map = new HashMap<>();
    	
    	for (int i = 0; i < words.length; i++) {
    		if (!map.containsKey(words[i])) {
    			map.put(words[i], new ArrayList<>());
    		}
    		map.get(words[i]).add(i);
    	}
    }
    
    // O(m + n)
    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);
        
        int result = Integer.MAX_VALUE;
        
        int index1 = 0;
        int index2 = 0;
        
        while (index1 < l1.size() && index2 < l2.size()) {
        	int i1 = l1.get(index1);
        	int i2 = l2.get(index2);
        	
        	result = Math.min(result, Math.abs(i1 - i2));
        	
        	if (i1 > i2) {
        		index2++;
        	} else {
        		index1++;
        	}
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String[] test1 = {"practice", "makes", "perfect", "coding", "makes"};
		WordDistance wd = new WordDistance(test1);
		
		System.out.println(wd.shortest("coding", "practice"));
		System.out.println(wd.shortest("makes", "coding"));
	}
}
