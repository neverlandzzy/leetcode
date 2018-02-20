import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Solution 1: DFS
/*
class Digraph {
	private int V;
	private int E;
	private List<ArrayList<Integer>> adj;
	private boolean hasCycle;
	private boolean[] marked;
	private boolean[] onStack;
	private Stack<Integer> postorder;
	
	public Digraph(int n, int[][] edges) {
		this.V = n;
		this.E = edges.length;
		adj = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < V; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			adj.add(list);
		}
		
		for (int i = 0; i < E; i++) {
			int v = edges[i][1];
			int w = edges[i][0];
			adj.get(v).add(w);
		}
		
	}
	
	public int[] topologicalOrders() {
		marked = new boolean[V];
		onStack = new boolean[V];
		postorder = new Stack<Integer>();
		
		for (int i = 0; i < V; i++) {
			if (!marked[i]) dfs(i);
		}
		
		if (hasCycle) return new int[0];
		else {
			int[] topoloicalOrder = new int[V];
			for (int i = 0; i < V; i++) {
				topoloicalOrder[i] = postorder.pop();
			}
			return topoloicalOrder;
		}
	}
	
	
	private void dfs(int v) {
		marked[v] = true;
		onStack[v] = true;
		
		for (int w: adj.get(v)) {
			if (hasCycle) return;
			else if (!marked[w]) dfs(w);
			else if (onStack[w]) hasCycle = true;
		}
		postorder.push(v);
		onStack[v] = false;
	}
	
}
*/

// Solution 2: BFS

class Digraph {
	private int V; 
	private int E;
	private List<ArrayList<Integer>> adj;
	private int[] indegree;
	private Queue<Integer> queue;
	
	public Digraph(int n, int[][] edges) {
		this.V = n;
		this.E = edges.length;
		indegree = new int[V];
		adj = new ArrayList<ArrayList<Integer>>();
		
		for (int i = 0; i < V; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			adj.add(list);
		}
		
		for (int i = 0; i < E; i++) {
			int v = edges[i][1];
			int w = edges[i][0];
			
			adj.get(v).add(w);
			indegree[w]++;
		}
	}
	
	public int[] topologicalOrders() {
		queue = new LinkedList<Integer>();
		int[] topologicalOrder = new int[V];
		
		for (int i = 0; i < V; i++) {
			if (indegree[i] == 0) {
				queue.add(i);
			}
		}
		
		int counter = 0;
		
		while (!queue.isEmpty()) {
			int v = queue.poll();
			topologicalOrder[counter] = v;
			counter++;
			
			for (int w: adj.get(v)) {
				if (--indegree[w] == 0) {
					queue.add(w);
				}
			}
		}
		
		if (counter < V) return new int[0];
		else return topologicalOrder;
	}
	
	
}

public class Solution {
	/*
	 * There are a total of n courses you have to take, labeled from 0 to n - 1.
	 * 
	 * Some courses may have prerequisites, for example to take course 0 you have to 
	 * first take course 1, which is expressed as a pair: [0,1]
	 * 
	 * Given the total number of courses and a list of prerequisite pairs, 
	 * return the ordering of courses you should take to finish all courses.
	 * 
	 * There may be multiple correct orders, you just need to return one of them.
	 * If it is impossible to finish all courses, return an empty array.
	 * 
	 * For example:
	 * 
	 * 2, [[1,0]]
	 * There are a total of 2 courses to take. To take course 1 you should have 
	 * finished course 0. So the correct course order is [0,1]
	 * 
	 * 4, [[1,0],[2,0],[3,1],[3,2]]
	 * There are a total of 4 courses to take. To take course 3 you should have finished 
	 * both courses 1 and 2. Both courses 1 and 2 should be taken after you finished 
	 * course 0. So one correct course order is [0,1,2,3]. Another correct ordering 
	 * is[0,2,1,3].
	 */
	
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Digraph G = new Digraph(numCourses, prerequisites);
        return G.topologicalOrders();
    }
    
    public static void main(String[] args) {
    	int [][] test1 = {{1,0}};
    	int [][] test2 = {{1,0}, {0,1}};
		System.out.println(findOrder(2, test1));
		System.out.println(findOrder(2, test2));
	}
    
}
