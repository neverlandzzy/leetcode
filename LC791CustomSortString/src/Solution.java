import java.util.Comparator;
import java.util.PriorityQueue;



public class Solution {
	/*
	 * S and T are strings composed of lowercase letters. In S, no letter occurs more than once.
	 * 
	 * S was sorted in some custom order previously. We want to permute the characters of T so that they match the order that S was sorted. 
	 * More specifically, if x occurs before y in S, then x should occur before y in the returned string.
	 * 
	 * Return any permutation of T (as a string) that satisfies this property.
	 * 
	 * Example :
	 * Input: 
	 * S = "cba"
	 * T = "abcd"
	 * Output: "cbad"
	 * 
	 * Explanation: 
	 * "a", "b", "c" appear in S, so the order of "a", "b", "c" should be "c", "b", and "a". 
	 * Since "d" does not appear in S, it can be at any position in T. "dcba", "cdba", "cbda" are also valid outputs. 
	 * 
	 * Note:
	 * 
	 * 1. S has length at most 26, and no character is repeated in S.
	 * 2. T has length at most 200.
	 * 3. S and T consist of lowercase letters only.
	 */
	
	
	// Solution 1: O(n)
	public static String customSortString(String S, String T) {
		int[] map = new int[26];
		
		for (char c: T.toCharArray()) {
			map[c - 'a']++;
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (char c: S.toCharArray()) {
			for (int i = 0; i < map[c - 'a']; i++) {
				sb.append(c);
			}
			
			map[c - 'a'] = 0;
		}
		
		for (char c = 'a'; c <= 'z'; c++) {
			if (map[c - 'a'] > 0) {
				for (int i = 0; i < map[c - 'a']; i++) {
					sb.append(c);
				}
			}
		}
		
		return sb.toString();
	}
	
	// Solution 2: O(nlogn)
	/*
    public static String customSortString(String S, String T) {
        int[] map = new int[26];
        for (int i = 0; i < S.length(); i++) {
        	char c = S.charAt(i);
        	map[c - 'a'] = i;
        }
        
        PriorityQueue<Character> heap = new PriorityQueue<>(new Comparator<Character>() {
        	public int compare(Character c1, Character c2) {
        		return map[c1 - 'a'] - map[c2 - 'a'];
        	}
        });
        
        for (char c: T.toCharArray()) {
        	heap.offer(c);
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!heap.isEmpty()) {
        	sb.append(heap.poll());
        }
        
        return sb.toString();
    }
    */
	
    public static void main(String[] args) {
		System.out.println(customSortString("cba", "abcd"));
	}
}
