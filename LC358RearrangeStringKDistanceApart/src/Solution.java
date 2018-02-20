import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;


public class Solution {
	/*
	 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
	 * 
	 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
	 * 
	 * Example 1:
	 * s = "aabbcc", k = 3
	 * 
	 * Result: "abcabc"
	 * 
	 * The same letters are at least distance 3 from each other.
	 * Example 2:
	 * s = "aaabc", k = 3 
	 * 
	 * Answer: ""
	 * 
	 * It is not possible to rearrange the string.
	 * Example 3:
	 * s = "aaadbbcc", k = 2
	 * 
	 * Answer: "abacabcd"
	 * 
	 * Another possible answer is: "abcabcda"
	 * 
	 * The same letters are at least distance 2 from each other.
	 */
	
	// https://discuss.leetcode.com/topic/48109/java-7-version-of-priorityqueue-o-nlogn-with-comments-and-explanations
	// Time Complexity: O(n * log26) = O(n)
	// 1. 将字符串转化为字符和频率映射的map；
	// 2. 将这个map中的每个entry加入到maxHeap中，maxHeap按map中字符的频率由高到底排序；
	// 3. 每次从maxHeap中取出频率最高的字符的entry，将字符加到输出字符串sb中，更新这个字符的频率（entry.setValue(entry.getValue() - 1);），然后将这个entry加入queue中；
	// 4. 当queue的size == k时，说明distance已经为k，这时再将entry从queue中取出，若entry的频率不为0，则将其加回maxHeap
	// 5. not possible的case是maxHeap已经empty但queue中还有entry -- 说明没有足够的distance将queue中的char分隔开，因此返回""
	
    public static String rearrangeString(String s, int k) {
    	if (s == null || s.length() == 0) {
    		return s;
    	}
    	
        StringBuilder result = new StringBuilder();
        
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
        	map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
				
				return o2.getValue() - o1.getValue();
			}
        });
        
        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());
        
        while (!maxHeap.isEmpty()) {
        	Map.Entry<Character, Integer> entry = maxHeap.poll();
        	result.append(entry.getKey());
        	entry.setValue(entry.getValue() - 1);
        	queue.offer(entry);
        	
        	if (queue.size() < k) {
        		continue;
        	}
        	
        	Map.Entry<Character, Integer> next = queue.poll();
        	if (next.getValue() > 0) {
        		maxHeap.offer(next);
        	}
        }
        
        return result.length() == s.length() ? result.toString() : "";
    }
    
    public static void main(String[] args) {
		System.out.println(rearrangeString("aabbcc", 3));
		System.out.println(rearrangeString("aaabc", 3));
		System.out.println(rearrangeString("aaadbbcc", 2));
	}
}
