package onsite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestWordDistance {
	// LC 243 - 245
	
	// LC 243
    public static int shortestDistance(String[] words, String word1, String word2) {
        Integer index1 = null;
        Integer index2 = null;
        int result = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
        	if (words[i].equals(word1) || words[i].equals(word2)) {
	        	if (words[i].equals(word1)) {
	        		index1 = i;
	        	}
	        	
	        	if (words[i].equals(word2)) {
	        		index2 = i;
	        	}
	        	
	        	if (index1 != null && index2 != null) {
	        		result = Math.min(result, Math.abs(index1 - index2));
	        	}
        	}
        }
        
        return result;
    }
    
    public static class WordDistance {
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
    }
    
    public static void main(String[] args) {
    	
    	System.out.println("====== LC 243 ======");
		String[] test1 = {"practice", "makes", "perfect", "coding", "makes"};
		
		System.out.println(shortestDistance(test1, "coding", "practice"));
		System.out.println(shortestDistance(test1, "makes", "coding"));
		
		System.out.println("====== LC 244 ======");
		WordDistance wd = new WordDistance(test1);
		
		System.out.println(wd.shortest("coding", "practice"));
		System.out.println(wd.shortest("makes", "coding"));
	}
    
    
}
