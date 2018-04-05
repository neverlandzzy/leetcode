import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Solution {
	/*
	 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
	 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
	 * Derive the order of letters in this language.
	 * Example 1:
	 * Given the following words in dictionary,
	 * 
	 * [
	 *   "wrt",
	 *   "wrf",
	 *   "er",
	 *   "ett",
	 *   "rftt"
	 * ]
	 * The correct order is: "wertf".
	 * 
	 * Example 2:
	 * Given the following words in dictionary,
	 * 
	 * [
	 *   "z",
	 *   "x"
	 * ]
	 * The correct order is: "zx".
	 * 
	 * Example 3:
	 * Given the following words in dictionary,
	 * 
	 * [
	 *   "z",
	 *   "x",
	 *   "z"
	 * ]
	 * The order is invalid, so return "".
	 * 
	 * Note:
	 * 1. You may assume all letters are in lowercase.
	 * 2. You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
	 * 3. If the order is invalid, return an empty string.
	 * 4. There may be multiple valid order of letters, return any one of them is fine.
	 */
	
	// FB 面经，两种方法都要掌握
	// Solution 1: BFS
	// https://discuss.leetcode.com/topic/28308/java-ac-solution-using-bfs
	
    public static String alienOrder(String[] words) {
    	 
        Map<Character, Set<Character>> map = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        
        String result = "";
        if (words == null || words.length == 0) {
        	return result;
        }
        
        // build graph
        for (String w: words) {
        	for (char c: w.toCharArray()) {
        		indegree.put(c, 0);
        	}
        }
        
        for (int i = 0; i < words.length - 1; i++) {
        	String cur = words[i];
        	String next = words[i + 1];
        	
        	int min = Math.min(cur.length(), next.length());
        	for (int j = 0; j < min; j++) {
        		char c1 = cur.charAt(j);
        		char c2 = next.charAt(j);
        		
        		if (c1 != c2) {
        			
        			Set<Character> set = new HashSet<>();
        			if (map.containsKey(c1)) {
        				set = map.get(c1);
        			}
        			
        			if (!set.contains(c2)) {
                        set.add(c2);
                        map.put(c1, set);
                        indegree.put(c2, indegree.get(c2) + 1);
        			}
        			
        			break;
        		}
        	}
        }
        // Topological sorting with BFS
        Queue<Character> queue = new LinkedList<>();
        
        for (char c: indegree.keySet()) {
        	if (indegree.get(c) == 0) {
        		queue.add(c);
        	}
        }
        
        while (!queue.isEmpty()) {
        	char c = queue.poll();
        	result += c;
        	
        	if (map.containsKey(c)) {
        		for (char c2: map.get(c)) {
        			indegree.put(c2,  indegree.get(c2) - 1);
        			if (indegree.get(c2) == 0) {
        				queue.add(c2);
        			}
        		}
        	}
        }
        
        if (result.length() != indegree.size()) {
        	return "";
        }
        
        return result;
    }
    
	
    // Solution 2: DFS
	// https://discuss.leetcode.com/topic/33565/3ms-clean-java-solution-dfs/8
    
	/*
	public static String alienOrder(String[] words) {
		boolean[][] adj = new boolean[26][26];
		int[] visited = new int[26];
		
		 //visited[i] = -1. Not even exist.
		 //visited[i] = 0. Exist. Non-visited.
		 //visited[i] = 1. Visiting.
		 //visited[i] = 2. Visited.
		 
		buildGraph(words, adj, visited);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < 26; i++) {
			if (visited[i] == 0) {
				if (!dfs(adj, visited, sb, i)) {
					return "";
				}
			}
		}
		
		return sb.reverse().toString();
	}
	
	private static void buildGraph(String[] words, boolean[][] adj, int[] visited) {
		Arrays.fill(visited, -1);
        
		for (String w: words) {
        	for (char c: w.toCharArray()) {
        		visited[c -'a'] = 0;
        	}
        }
        
		for (int i = 0; i < words.length - 1; i++) {
			String cur = words[i];
			String next = words[i + 1];
			
			int min = Math.min(cur.length(), next.length());
			
			for (int j = 0; j < min; j++) {
        		char c1 = cur.charAt(j);
        		char c2 = next.charAt(j);
        		if (c1 != c2) {
        			adj[c1 - 'a'][c2 - 'a'] = true;
        			break;
        		}
			}
		}
	}
	
	private static boolean dfs(boolean[][] adj, int[] visited, StringBuilder sb, int i) {
		visited[i] = 1;
		
		for (int j = 0; j < 26; j++) {
			if (adj[i][j]) {
				if (visited[j] == 1) {
					// there is cycle
					return false;
				}
				
				if (visited[j] == 0) {
					if (!dfs(adj, visited, sb, j)) {
						return false;
					}
				}
			}
		}
		
		visited[i] = 2;
		
		sb.append((char)(i + 'a'));
		return true;
	}
	*/

    public static void main(String[] args) {
		String[] test1 = {"wrt", "wrf", "er", "ett", "rftt"};
		String[] test2 = {"z", "x"};
		String[] test3 = {"z", "x", "z"};
		String[] test4 = {"z", "z"};
		String[] test5 = {"ac","ab","b"};
		String[] test6 = {"za","zb","ca","cb"};
		
		System.out.println(alienOrder(test1));
		System.out.println(alienOrder(test2));
		System.out.println(alienOrder(test3));
		System.out.println(alienOrder(test4));
		System.out.println(alienOrder(test5));
		System.out.println(alienOrder(test6));
	}
}
