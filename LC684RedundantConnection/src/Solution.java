import java.util.HashMap;
import java.util.Map;


public class Solution {
	/**
	 * In this problem, a tree is an undirected graph that is connected and has no cycles.
	 * 
	 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. 
	 * The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
	 * 
	 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected 
	 * edge connecting nodes u and v.
	 * 
	 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer 
	 * that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
	 * 
	 * Example 1:
	 * Input: [[1,2], [1,3], [2,3]]
	 * Output: [2,3]
	 * Explanation: The given undirected graph will be like this:
	 *   1
	 *  / \
	 * 2 - 3
	 * 
	 * Example 2:
	 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
	 * Output: [1,4]
	 * Explanation: The given undirected graph will be like this:
	 * 5 - 1 - 2
	 *     |   |
	 *     4 - 3
	 * Note:
	 * 1. The size of the input 2D-array will be between 3 and 1000.
	 * 2. Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
	 */
	
	static class UnionFind {
		Map<Integer, Integer> map;
		
		public UnionFind(int n) {
			map = new HashMap<>();
			for (int i = 1; i <= n; i++) {
				map.put(i, i);
			}
		}
		
		public int find(int x) {
			if (map.get(x) == x) {
				return x;
			}
			int parent = find(map.get(x));
			map.put(x, parent);
			return parent;
		}
		
		// 这里在模板基础上做简单修改，若发现x和y有共同的parent，则说明当前加入的(x, y)是冗余的边
		public boolean union(int x, int y) {
			int faX = find(x);
			int faY = find(y);
			
            if(faX != faY) {
                map.put(faX, faY);
                return true;
            }
            
            return false;
		}
	}
	
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n);
        
        for (int[] edge: edges) {
        	if (!uf.union(edge[0], edge[1])) {
        		return edge;
        	}
        }
        
        return new int[] {};
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 2}, {1, 3}, {2, 3}};
		int[][] test2 = {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}};
		
		int[] result1 = findRedundantConnection(test1);
		int[] result2 = findRedundantConnection(test2);
		
		for (int i: result1) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		for (int i: result2) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
