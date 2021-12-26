import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Solution 1: DFS
/*
class Digraph {
	private int V;
	private int E;
	private List<ArrayList<Integer>> adj;
	private boolean hasCycle;
	private boolean[] marked;
	private boolean[] onStack;
	
	public Digraph(int n, int[][] edges) {
		this.V = n;
		this.E = edges.length;
		adj = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < V; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			adj.add(list);
		}
		for(int i = 0; i < E; i++) {
			int v = edges[i][1];
			int w = edges[i][0];
			
			adj.get(v).add(w);
		}
		
	}
	
	public boolean hasTopologicalOrder() {
		marked = new boolean[V];
		onStack = new boolean[V];
		
		for (int i = 0; i < V; i++) {
			if (!marked[i]) {
				dfs(i);
			}
		}
		
		return !hasCycle;
	}
	
	private void dfs(int v) {
		marked[v] = true;
		onStack[v] = true;
		
		for (int w: adj.get(v)) {
			if (hasCycle) return;
			if (!marked[w]) dfs(w);
			if (onStack[w]) hasCycle = true;
		}
		
		onStack[v] = false;
	}
}

*/

//Solution 2: BFS

class Graph {
    private int V;
    private int E;
    private int[] indegree;
    private ArrayList<List<Integer>> neighbors;
    
    public Graph(int V, int[][] edges) {
        this.V = V;
        this.E = edges.length;
        indegree = new int[V];
        neighbors = new ArrayList<>();
        
        for (int i = 0; i < V; i++) {
            neighbors.add(new ArrayList<>());
        }
        
        for (int i = 0; i < E; i++) {
            int m = edges[i][1];
            int n = edges[i][0];
            neighbors.get(m).add(n);
            indegree[n]++;
        }
    }
    
    public boolean hasTopologicalOrder() {
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int counter = 0;
        
        while (!queue.isEmpty()) {
            int v = queue.poll();
            counter++;
            
            for (int w: neighbors.get(v)) {
                if (--indegree[w] == 0) {
                    queue.offer(w);
                }
            }
        }
        
        return counter == V;
    }
}

public class Solution {
	/**
	 * There are a total of n courses you have to take, labeled from 0 to n - 1.
	 * 
	 * Some courses may have prerequisites, for example to take course 0 you have to 
	 * first take course 1, which is expressed as a pair: [0,1]
	 * 
	 * Given the total number of courses and a list of prerequisite pairs, is it possible 
	 * for you to finish all courses?
	 * 
	 * For example:
	 * 
	 * 2, [[1,0]]
	 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
	 * So it is possible.
	 * 
	 * 2, [[1,0],[0,1]]
	 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, 
	 * and to take course 0 you should also have finished course 1. So it is impossible.
	 */
	

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
    	Graph G = new Graph(numCourses, prerequisites);
    	
    	return G.hasTopologicalOrder();
    }
    
    public static void main(String[] args) {
        System.out.println("hello world");
    	int [][] test1 = {{1,0}};
    	int [][] test2 = {{1,0}, {0,1}};
		System.out.println(canFinish(2, test1));
		System.out.println(canFinish(2, test2));
	}
}
