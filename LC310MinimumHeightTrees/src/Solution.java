import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/**
	 * For a undirected graph with tree characteristics, we can choose any node as the root. 
	 * The result graph is then a rooted tree. Among all possible rooted trees, those with minimum 
	 * height are called minimum height trees (MHTs). Given such a graph, write a function to find 
	 * all the MHTs and return a list of their root labels.
	 * 
	 * Format
	 * 
	 * The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number 
	 * n and a list of undirected edges (each edge is a pair of labels).
	 * 
	 * You can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
	 * [0, 1] is the same as [1, 0] and thus will not appear together in edges.
	 * 
	 * Example 1:
	 * 
	 * Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]
	 * 
     *   	0
     *   	|
     *   	1
     *     / \
     * 	  2   3
	 * 
	 * return [1]
	 * 
	 * Example 2:
	 * 
	 * Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]
     *	0  1  2
     *   \ | /
     *     3
     *     |
     *     4
     *     |
     *     5
     *     
     * return [3, 4]
     * 
     * Hint:
     * 
     * How many MHTs can a graph have at most?
     * 
     * Note:
	 * (1) According to the definition of tree on Wikipedia: “a tree is an undirected 
	 * graph in which any two vertices are connected by exactly one path. In other words, 
	 * any connected graph without simple cycles is a tree.”
	 * 
	 * (2) The height of a rooted tree is the number of edges on the longest downward 
	 * path between the root and a leaf.
	 */
	
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) {
    	
    	List<Integer> leaves = new ArrayList<>();
    	if (n == 1) {
    		leaves.add(0);
    		return leaves;
    	}
    	
    	List<Set<Integer>> neighbors = new ArrayList<>();
    	
    	for (int i = 0; i < n; i++) {
			neighbors.add(new HashSet<>());
    	}
    	
    	for (int[] edge: edges) {
			neighbors.get(edge[0]).add(edge[1]);
			neighbors.get(edge[1]).add(edge[0]);
    	}

    	for (int i = 0; i < n; i++) {
    		if (neighbors.get(i).size() == 1) {
    			leaves.add(i);
    		}
    	}

    	// MHT root最多只能有2个，如果有三个，1-2-3, 则可以消掉1,3
    	while (n > 2) {
    		n -= leaves.size();
//    		System.out.println("leaves ---- " + leaves);
//    		System.out.println("adj ---- " + neighbors);
    		List<Integer> newLeaves = new ArrayList<>();
    		
    		// 将所有的leaves去掉，并从与leaves相邻的node的邻居里减去leaves -- 一层层去掉leaves，直到剩下最后的1、2个node
	        for (int i : leaves) {
	        	// 得到的是与i相邻的node (e.g. i = 0, 1, 2, 5  相邻的node分别是3, 3, 3, 4)
	        	int j = neighbors.get(i).iterator().next();
	        	// 从相邻node的所有邻居里，减去i(e.g. node3的邻居为(0, 1, 2, 4), 从中减去0, 1, 2)
				neighbors.get(j).remove(i);
	        	
	        	if (neighbors.get(j).size() == 1) {
	        		newLeaves.add(j);
	        	}
	        }
	        leaves = newLeaves;
    	}
    	
    	return leaves;
        
    }
    
    public static void main(String[] args) {
		int[][] edges = {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
		int[][] edges2 = {};
		
		System.out.println(findMinHeightTrees(6, edges));
	}
}
