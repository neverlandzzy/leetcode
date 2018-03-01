package phone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class MultiTree {
	/*
	 * 【基本题】
	 *     1     2    3
	 *    /  \  /      \
	 *   4    5         6
	 *                   \
	 *                    7
	 *  输入是int[][] input, input[0]是input[1] 的parent，比如 {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}}会形成上面的图
	 *  第一问是只有0个parent和只有1个parent的节点（Write a function that takes this data as input and returns two collections: 
	 *  one containing all individuals with zero known parents, and one containing all individuals with exactly one known parent.）
	 * 
	 * 【Follow up 1】两个指定的点有没有公共祖先，给出他们共同的ancestor节点（可以有不只一个）/或者可能要判断这两个节点是否有公共祖先
	 * 【Follow up 2】求一个点的最远祖先，如果有几个就只需要输出一个就好，举个栗子，这里5的最远祖先可以是1或者2，输出任意一个就可以
	 * 
	 * 【例子】
	 *  输入： (1, 3), (2, 3), (3, 6), (5, 6),(5, 7), (4, 5), (4, 8), (8, 9)
	 *    1  2  4
 	 *    \ /  / \
     *     3  5   8
	 *     \ / \   \
     *      6   7   9
     * 【基本题输出】       [1, 2, 4],   // Individuals with zero parents
     *                   [5, 7, 8, 9] // Individuals with exactly one parent
	 * 
	 * 【Follow up 1】    parentChildPairs, 3, 8 => false
     *                   parentChildPairs, 5, 8 => true
     *                   parentChildPairs, 6, 8 => true
	 */
	
	// 基本题
	public static List<List<Integer>> zeroAndOneParent(int[][] tree) {
		Map<Integer, List<Integer>> parents = new HashMap<>();
		for (int[] node: tree) {
			if (!parents.containsKey(node[0])) {
				parents.put(node[0], new ArrayList<>());
			}
			
			if (!parents.containsKey(node[1])) {
				parents.put(node[1], new ArrayList<>());
			}
			parents.get(node[1]).add(node[0]);
		}

		List<Integer> zeroParent = new ArrayList<>();
		List<Integer> oneParent = new ArrayList<>();
		List<List<Integer>> result = new ArrayList<>();
		
		for (Map.Entry<Integer, List<Integer>> entry: parents.entrySet()) {
			if (entry.getValue().size() == 0) {
				zeroParent.add(entry.getKey());
			}
			if (entry.getValue().size() == 1) {
				oneParent.add(entry.getKey());
			}
		}
		
		result.add(zeroParent);
		result.add(oneParent);
		
		return result;
	}
	
	// Follow up 1
	// 在基本题的map上做bfs
	public static boolean hasSameAncestor(int[][] tree, int A, int B) {		
		// 同第一问
		Map<Integer, List<Integer>> parents = new HashMap<>();
		for (int[] node: tree) {
			if (!parents.containsKey(node[0])) {
				parents.put(node[0], new ArrayList<>());
			}
			
			if (!parents.containsKey(node[1])) {
				parents.put(node[1], new ArrayList<>());
			}
			parents.get(node[1]).add(node[0]);
		}
		
		// 在parents上做bfs找出所有A的祖先
		Set<Integer> ancestorA = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(A);
		ancestorA.add(A);
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (int parent: parents.get(node)) {
				if (!ancestorA.contains(parent)) {
					ancestorA.add(parent);
					queue.offer(parent);
				}
			}
		}
		
		// 在parents上做bfs找B的祖先，遇到与A相同的则返回true，否则返回false
		queue.offer(B);
		Set<Integer> visited = new HashSet<>();
		visited.add(B);
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			if (ancestorA.contains(node)) {
				return true;
			}
			for (int parent: parents.get(node)) {			
				if (!visited.contains(parent)) {
					queue.offer(parent);
					visited.add(parent);
				} 
			}
		}
		
		return false;
	}
	
	// Follow up 2
	// 在基本题的map上做bfs
	public static int furthestAncestor(int[][] tree, int A) {	
		// 同第一问
		Map<Integer, List<Integer>> parents = new HashMap<>();
		for (int[] node: tree) {
			if (!parents.containsKey(node[0])) {
				parents.put(node[0], new ArrayList<>());
			}
			
			if (!parents.containsKey(node[1])) {
				parents.put(node[1], new ArrayList<>());
			}
			parents.get(node[1]).add(node[0]);
		}
		
		// 在parents上做level order的bfs找出所有A最远的祖先
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(A);
		int result = A;
		Set<Integer> visited = new HashSet<>();
		visited.add(A);
		
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int node = queue.poll();
				result = node;
				
				for (int parent: parents.get(node)) {
					if (!visited.contains(parent)) {
						queue.offer(parent);
						visited.add(parent);
					}
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println("*********** 【基本题】输出只有0个父节点和只有1个父节点的节点list ***********");
		int[][] test11 = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}};
		int[][] test12 = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
		System.out.println(zeroAndOneParent(test11));
		System.out.println(zeroAndOneParent(test12));
		System.out.println();
		
		System.out.println("*********** 【Follow up 1】判断两个指定的点有没有公共祖先 ***********");
		int[][] test21 = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}};
		int[][] test22 = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
		int[][] test23 = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}, {6, 10}, {7, 10}};
		System.out.println(hasSameAncestor(test21, 3, 8));
		System.out.println(hasSameAncestor(test21, 5, 8));
		System.out.println(hasSameAncestor(test21, 6, 8));
		System.out.println(hasSameAncestor(test21, 5, 6));
		System.out.println();
		System.out.println(hasSameAncestor(test22, 5, 2));
		System.out.println(hasSameAncestor(test22, 5, 4));
		System.out.println(hasSameAncestor(test22, 5, 7));
		System.out.println(hasSameAncestor(test22, 7, 7));
		System.out.println();
		System.out.println(hasSameAncestor(test23, 9, 10));
		System.out.println();
		System.out.println("*********** 【Follow up 2】求一个点的最远祖先 ***********");
		int[][] test31 = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}};
		int[][] test32 = {{1,4}, {1,5}, {2,5}, {3,6}, {6,7}};
		int[][] test33 = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5}, {4, 8}, {8, 9}, {6, 10}, {7, 10}, {3, 10}};
		
		System.out.println(furthestAncestor(test31, 6));
		System.out.println(furthestAncestor(test31, 9));
		System.out.println(furthestAncestor(test31, 1));
		System.out.println(furthestAncestor(test31, 5));
		System.out.println(furthestAncestor(test31, 3));
		System.out.println();
		System.out.println(furthestAncestor(test32, 5));
		System.out.println(furthestAncestor(test32, 7));
		System.out.println();
		System.out.println(furthestAncestor(test33, 10));
	}
}
