import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
	/*
	 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.
	 * 
	 * If possible, output any possible result.  If not possible, return the empty string.
	 * 
	 * Example 1:
	 * 
	 * Input: S = "aab"
	 * Output: "aba"
	 * Example 2:
	 * 
	 * Input: S = "aaab"
	 * Output: ""
	 * Note:
	 * 
	 * S will consist of lowercase letters and have length in range [1, 500].
	 * 
	 */
	
	// Solution 1 O(n)
	/*
	public static String reorganizeString(String S) {
        int n = S.length();
        int[] map = new int[26];
        int max = 0;
        char cMax = S.charAt(0);
        
        for (int i = 0; i < n; i++) {
        	char c = S.charAt(i);
        	map[c - 'a']++;
        	if (max < map[c - 'a']) {
        		max = map[c - 'a'];
        		cMax = c;
        	}
        }
        
        if (2 * max - n > 1) {
        	return "";
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
        	sb.append(cMax);
        }
        map[cMax - 'a'] = 0;
        
        int offset = 1;
        for (int i = 0; i < 26; i++) {
        	for (int j = 0; j < map[i]; j++) {
        		sb.insert(offset, (char)(i + 'a'));
        		offset += 2;
            	
            	if (offset > sb.length()) {
            		offset = 0;
            	}
        	}

        }
        
        return sb.toString();
	}
	*/
	
	// Solution 2: PriorityQueue O(n)
	// 每次从PriorityQueue中取数量最多的字符
	// https://discuss.leetcode.com/topic/117851/java-solution-priorityqueue
	// 类似LC358 和 621
	public static String reorganizeString(String S) {
		Map<Character, Integer> map = new HashMap<>();
		int n = S.length();
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			char c = S.charAt(i);
			map.put(c, map.getOrDefault(c, 0) + 1);
			count = map.get(c);
			
	        if (2 * count - n > 1) {
	        	return "";
	        }
		}
		
		PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
		
		for (char c: map.keySet()) {
			heap.offer(new int[]{c, map.get(c)});
		}
		
		StringBuilder sb = new StringBuilder();
		
		while (!heap.isEmpty()) {
			int[] first = heap.poll();
			if (sb.length() == 0 || sb.charAt(sb.length() - 1) != first[0]) {
				sb.append((char)first[0]);
				if (--first[1] > 0) {
					heap.offer(first);
				}
			} else {
				int[] second = heap.poll();
				sb.append((char)second[0]);
				if (--second[1] > 0) {
					heap.offer(second);
				}
				heap.offer(first);
			}
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
    	System.out.println(reorganizeString("bfrbs"));
    	System.out.println(reorganizeString("aaaabbc"));
    	System.out.println(reorganizeString("eqpspvbpppwpgyppppe"));
	}
}
