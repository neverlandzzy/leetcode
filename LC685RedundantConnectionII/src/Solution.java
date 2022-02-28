import java.util.HashMap;
import java.util.Map;


public class Solution {
	/**
	 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are 
	 * descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
	 * 
	 * The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional 
	 * directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
	 * 
	 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting 
	 * nodes u and v, where u is a parent of child v.
	 * 
	 * Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the 
	 * answer that occurs last in the given 2D-array.
	 * 
	 * Example 1:
	 * Input: [[1,2], [1,3], [2,3]]
	 * Output: [2,3]
	 * Explanation: The given directed graph will be like this:
	 *   1
	 *  / \
	 * v   v
	 * 2-->3
	 * 
	 * Example 2:
	 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
	 * Output: [4,1]
	 * Explanation: The given directed graph will be like this:
	 * 5 <- 1 -> 2
	 *      ^    |
	 *      |    v
	 *      4 <- 3
	 * Note:
	 * 	1. The size of the input 2D-array will be between 3 and 1000.
	 * 	2. Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
	 */
	
	// https://discuss.leetcode.com/topic/105100/most-posted-answers-are-wrong
	// 容易想到的一个错误解法是利用LC684的解法（检查cycle） + 检查duplicate parent，反例 [[4,2],[1,5],[5,2],[5,3],[2,4]]，按照这个方法会得到[5, 2]， 
	// 但是[4, 2]才是正确答案。原因是：cycle和duplicate parent情况不能分别独立检查。 本题需要检查下面三种情况：
	// 	1. no cycle, but duplicate parent;
	// 	2. no duplicate parent, but cycle;  --> LC684
	// 	3. has both cycle and duplicate parent --> 即反例的情景，此时，可能需要remove的是前一条边
	// 正确做法是：
	// 	1. 检查是否有node有duplicate parent，若有则将两条边记录为candidate1, candidate2, 并将第二条边(candidate2)暂时去掉
	//  2. 此时，再用常规的UnionFind解法找cycle：
	//     （1）若没有cycle，可以构成valid tree，则可以返回candidate2；
	//     （2）若第一步没有candidates（即没有duplicate parent），则找到cycle时返回当前的边
	//     （3）否则，要返回candidate1（即反例的情况）
	
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
	
    public static int[] findRedundantDirectedConnection(int[][] edges) {
        // 1. 检查是否有duplicate parent
    	int n = edges.length;
    	int[] candidate1 = new int[2];
    	int[] candidate2 = new int[2];
    	int[] parent = new int[n + 1];
    	
    	for (int[] edge: edges) {
    		if (parent[edge[1]] == 0) {
    			parent[edge[1]] = edge[0];
    		} else {
    			// 若发有duplicate parent，则将current边记为candidate2， 将之前的边记为candidate1
    			candidate2 = new int[] {edge[0], edge[1]};
    			candidate1 = new int[] {parent[edge[1]], edge[1]};
    			// 并将第二条边(current 边)暂时去掉
    			edge[1] = 0;
    		}
    	}
    	
    	//	用常规的UnionFind解法找cycle：
    	//  （1）若没有cycle，可以构成valid tree，则可以返回candidate2；
    	//  （2）若第一步没有candidates（即没有duplicate parent），则找到cycle时返回当前的边
    	//  （3）否则，要返回candidate1（即反例的情况）
    	UnionFind uf = new UnionFind(n);
    	
        for (int[] edge: edges) {
        	if (edge[1] == 0) {
        		// 上一步中去掉的边
        		continue;
        	}
        	if (!uf.union(edge[1], edge[0])) {
        		if (candidate1[0] == 0) {
        			return edge;
        		} else {
        			return candidate1;
        		}
        	}
        }
        
        return candidate2;
    }
    
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 2}, {1, 3}, {2, 3}};
		int[][] test2 = {{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}};
		int[][] test3 = {{4,2}, {1,5}, {5,2}, {5,3}, {2,4}};
		
		int[] result1 = findRedundantDirectedConnection(test1);
		int[] result2 = findRedundantDirectedConnection(test2);
		int[] result3 = findRedundantDirectedConnection(test3);
		
		for (int i: result1) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		for (int i: result2) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		for (int i: result3) {
			System.out.print(i + ", ");
		}
		System.out.println();
	}
}
