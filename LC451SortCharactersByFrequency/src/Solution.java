import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Solution {
	/*
	 * Given a string, sort it in decreasing order based on the frequency of characters.
	 * 
	 * Example 1:
	 * 
	 * Input:
	 * "tree"
	 * 
	 * Output:
	 * "eert"
	 * 
	 * Explanation:
	 * 'e' appears twice while 'r' and 't' both appear once.
	 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
	 * 
	 * Example 2:
	 * 
	 * Input:
	 * "cccaaa"
	 * 
	 * Output:
	 * "cccaaa"
	 * 
	 * Explanation:
	 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
	 * Note that "cacaca" is incorrect, as the same characters must be together.
	 * 
	 * Example 3:
	 * 
	 * Input:
	 * "Aabb"
	 * 
	 * Output:
	 * "bbAa"
	 * 
	 * Explanation:
	 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
	 * Note that 'A' and 'a' are treated as two different characters.
	 */
	
	static class Node {
		char c;
		int count;
		
		public Node(char c, int count) {
			this.c = c;
			this.count = count;
		}
	}
	
    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	
        	map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
        	public int compare(Node n1, Node n2) {
        		return n2.count - n1.count;
        	}
        });       
        
        for (char c: map.keySet()) {
        	Node node = new Node(c, map.get(c));
        	queue.offer(node);
        }
        
        while (!queue.isEmpty()) {
        	Node node = queue.poll();
        	
        	for (int i = 0; i < node.count; i++) {
        		sb.append(node.c);
        	}
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
		System.out.println(frequencySort("tree"));
		System.out.println(frequencySort("cccaaa"));
		System.out.println(frequencySort("Aabb"));
	}
}
