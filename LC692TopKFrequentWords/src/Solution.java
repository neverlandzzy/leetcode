import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
	/**
	 * Given a non-empty list of words, return the k most frequent elements.
	 * 
	 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the 
	 * lower alphabetical order comes first.
	 * 
	 * Example 1:
	 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
	 * Output: ["i", "love"]
	 * Explanation: "i" and "love" are the two most frequent words.
	 *              Note that "i" comes before "love" due to a lower alphabetical order.
	 * 
	 * Example 2:
	 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
	 * Output: ["the", "is", "sunny", "day"]
	 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
	 *              with the number of occurrence being 4, 3, 2 and 1 respectively.
	 *     
	 * Note:
	 * 	1. You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
	 * 	2. Input words contain only lowercase letters.
	 * 	3. Follow up: Try to solve it in O(n log k) time and O(n) extra space.
	 */
    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String w: words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }
        
        PriorityQueue<String> heap = new PriorityQueue<>((s1, s2) -> {
			if (map.get(s1).equals(map.get(s2))) {
				return s1.compareTo(s2);
			}
			return map.get(s2) - map.get(s1);
		});
        
        for (String key: map.keySet()) {
            heap.offer(key);
        }
        
        List<String> result = new ArrayList<>();
        
        for (int i = 0; i < k; i++) {
            result.add(heap.poll());
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		String[] test1 = {"i", "love", "leetcode", "i", "love", "coding"};
		String[] test2 = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
		
		System.out.println(topKFrequent(test1, 2));
		System.out.println(topKFrequent(test2, 4));
	}
}
