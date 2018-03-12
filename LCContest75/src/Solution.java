import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
	
	
    public static boolean rotateString(String A, String B) {
        if (A.length() != B.length()) {
        	return false;
        }
        
        int n = A.length();
        
        for (int i = 0; i < n; i++) {
        	if (A.charAt(i) == B.charAt(0)) {
        		if (A.substring(i).equals(B.substring(0, n - i)) && A.substring(0, i).equals(B.substring(n - i))) {
        			return true;
        		}
        	}
        }
        
        return false;
    }
    
    static class Graph {
    	private int V; 
    	private List<List<Integer>> adj;
    	private int[] indegree;
    	private Queue<Integer> queue;
    	
    	public Graph(int[][] graph) {
    		this.V = graph.length;
    		indegree = new int[V];
    		adj = new ArrayList<>();
    		queue = new LinkedList<>();
    		
    		for (int i = 0; i < V; i++) {
    			ArrayList<Integer> list = new ArrayList<>();
    			adj.add(list);
    		}
    		
    		for (int i = 0; i < V; i++) {
    			for (int j: graph[i]) {
    				adj.get(i).add(j);
    				indegree[j]++;
    			}
    		}
    	}
    	
    	public List<List<Integer>> topologicalOrder() {
    		List<List<Integer>> result = new ArrayList<>();
    		Queue<List<Integer>> path = new LinkedList<>();
    		for (int i = 0; i < V; i++) {
    			if (indegree[i] == 0) {
    				queue.offer(i);
    				path.offer(new ArrayList<>());
    			}
    		}
    		
    		while (!queue.isEmpty()) {

    			int size = queue.size();
    			
    			for (int i = 0; i < size; i++) {
	    			int node = queue.poll();
	    		
	    			List<Integer> list = path.poll();
	    			list.add(node);

	    			if (adj.get(node).size() == 0) {
	    				result.add(new ArrayList<>(list));
	    			}
	    			for (int neighbor: adj.get(node)) {
	    				queue.offer(neighbor);
	    				path.offer(new ArrayList<>(list));
	    				
	    			}

    			}
    		}

    		
    		return result;
    	}
    }
    
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Graph g = new Graph(graph);
        return g.topologicalOrder();
    }
    
    public static double champagneTower(int poured, int query_row, int query_glass) {
    	if (poured == 0) {
    		return 0;
    	}
    	
    	if (query_row == 0) {
    		return (double)poured;
    	}
    	
        int n = 100;
        int[] dp = new int[n];
        
        dp[0] = 1;
        
        for (int i = 1; i < n; i++) {
        	dp[i] = dp[i - 1] + i + 1;
        }
        
        int level = 0;
        
        for (; level < n; level++) {
        	if (poured <= dp[level]) {
        		break;
        	}
        }
        
        System.out.println("level = " + level);
        if (query_row > level) {
        	return 0;
        }

        if (query_row < level) {
        	return 1;
        }
        
        int left = level > 0 ? poured - dp[level - 1] : poured;
        double avg = (double)left / level;

        if (query_glass == 0 || query_glass == level) {
        	return avg / 2;
        }
        
        return avg;
    }
    
	public static void main(String[] args) {
		/*
		System.out.println(rotateString("abcde", "cdeab"));
		System.out.println(rotateString("abcde", "abced"));
		
		int[][] test21 = {{1, 2}, {3}, {3}, {}};
		System.out.println(allPathsSourceTarget(test21));
		*/
		
		//System.out.println(champagneTower(1, 1, 1));
		//System.out.println(champagneTower(2, 1, 1));
		//System.out.println(champagneTower(0, 0, 0));
		//System.out.println(champagneTower(1, 0, 0));
		//System.out.println(champagneTower(4, 2, 0));
		System.out.println(champagneTower(6, 2, 1));
	}
}
