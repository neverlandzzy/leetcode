import java.util.HashMap;
import java.util.Map;


public class Solution {
	
	/*
	 * Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a 
	 * function to check whether these edges make up a valid tree.
	 * 
	 * For example:
	 * 
	 * Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
	 * 
	 * Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
	 * 
	 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the 
	 * same as [1, 0] and thus will not appear together in edges.
	 */
	
	// http://qkxue.net/info/67583/Leetcode-Graph

	// Solution 1: DFS
	/*
    public static boolean validTree(int n, int[][] edges) {

    	List<List<Integer>> adjList = new ArrayList<>();
    	boolean[] visited = new boolean[n];
    	
    	for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<Integer>());
    	}
    	
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        // check cycle
        if (hasCycle(adjList, 0, -1, visited)) {
        	return false;
        }
        
        // check if all vertices are visited
        for (boolean v: visited) {
        	if (!v) {
        		return false;
        	}
        }
        
        return true;
    }
    
    // check if an undirected graph has cycle started from vertex u
    // v is the next vertex from u
    private static boolean hasCycle(List<List<Integer>> adjList, int u, int pre, boolean[] visited) {
    	visited[u] = true;
    	
    	for (int i = 0; i < adjList.get(u).size(); i++) {
    		int v = adjList.get(u).get(i);
    		
    		if ((visited[v] && pre != v) || (!visited[v] && hasCycle(adjList, v, u, visited))) {
    			return true;
    		}
    	}
    	
    	return false;
    }
    */
    
    // Solution 2: BFS
	/*
    public static boolean validTree(int n, int[][] edges) {
    	
    	// 初始化模板，将输入转化为adjList
    	List<Set<Integer>> adjList = new ArrayList<>();
    	boolean[] visited = new boolean[n];
    	
    	for (int i = 0; i < n; i++) {
            adjList.add(new HashSet<Integer>());
    	}
    	
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        
        // check cycle
        while (!queue.isEmpty()) {
        	int node = queue.poll();
        	
        	if (visited[node]) {
        		return false;
        	}
        	
        	visited[node] = true;
        	
        	for (int neighbor: adjList.get(node)) {
        		queue.offer(neighbor);
        			// 从当前node的neighbor里删掉当前node，否则会重复visit
        		adjList.get(neighbor).remove(node);
        			// 若用ArrayList, 需要将node转化为Integer, 否则删的是index
        			// remove效率：ArrayList:O(n), HashSet: O(1)
        		//adjList.get(neighbor).remove((Integer)node);
        	}
        }
        
        // check if all vertices are visited
        for (boolean v: visited) {
        	if (!v) {
        		return false;
        	}
        }
        
        return true;
    }
    */
    
    // Solution 3: Union-Find
    static class UnionFind {
        Map<Integer, Integer> parents;
        
        public UnionFind(int n) {
            parents = new HashMap<>();
            for (int i = 0; i < n; i++) {
                parents.put(i, i);
            }
        }
        
        public int find(int x) {
            int pX = parents.get(x);
            if (pX != x) {
                pX = find(pX);
                parents.put(x, pX);
            }
            
            return pX;
        }
        
        public void union(int x, int y) {
            int pX = find(x);
            int pY = find(y);
            
            if (pX != pY) {
                parents.put(pX, pY);
            }
        }
    }
    
    public static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        
        UnionFind uf = new UnionFind(n);
        
        for (int i = 0; i < edges.length; i++) {
            if (uf.find(edges[i][0]) == uf.find(edges[i][1])) {
                return false;
            }
            uf.union(edges[i][0], edges[i][1]);
        }
        
        return true;
    }
	
    public static void main(String[] args) {
    	int[][] edges1 = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};
    	int[][] edges2 = {{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}};
    	
    	System.out.println(validTree(5, edges1));
    	System.out.println(validTree(5, edges2));
	}
}
