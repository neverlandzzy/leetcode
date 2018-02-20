import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
	/*
	 * On an infinite number line (x-axis), we drop given squares in the order they are given.
	 * 
	 * The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being positions[i][0] and sidelength 
	 * positions[i][1].
	 * 
	 * The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently landed squares. 
	 * We wait for each square to stick before dropping the next.
	 * 
	 * The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they touch (either the number 
	 * line or another square). Squares dropped adjacent to each other will not stick together prematurely.
	 * 
	 * 
	 * Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped, after dropping squares 
	 * represented by positions[0], positions[1], ..., positions[i].
	 * 
	 * Example 1:
	 * Input: [[1, 2], [2, 3], [6, 1]]
	 * Output: [2, 5, 5]
	 * Explanation:
	 * 
	 * After the first drop of positions[0] = [1, 2]:
	 * _aa
	 * _aa
	 * -------
	 * The maximum height of any square is 2.
	 * 
	 * 
	 * After the second drop of positions[1] = [2, 3]:
	 * __aaa
	 * __aaa
	 * __aaa
	 * _aa__
	 * _aa__
	 * --------------
	 * The maximum height of any square is 5.  
	 * The larger square stays on top of the smaller square despite where its center
	 * of gravity is, because squares are infinitely sticky on their bottom edge.
	 * 
	 * 
	 * After the third drop of positions[1] = [6, 1]:
	 * __aaa
	 * __aaa
	 * __aaa
	 * _aa
	 * _aa___a
	 * --------------
	 * The maximum height of any square is still 5.
	 * 
	 * Thus, we return an answer of [2, 5, 5].
	 * 
	 * 
	 * Example 2:
	 * Input: [[100, 100], [200, 100]]
	 * Output: [100, 100]
	 * Explanation: Adjacent squares don't get stuck prematurely - only their bottom edge can stick to surfaces.
	 * 
	 * Note:
	 * 
	 * 1 <= positions.length <= 1000.
	 * 1 <= positions[i][0] <= 10^8.
	 * 1 <= positions[i][1] <= 10^6.
	 */
	
	// https://leetcode.com/problems/falling-squares/solution/
	// 1. 坐标压缩：将离散的坐标用升序排列后的序数代替，即去掉坐标间的gap，从而降低坐标范围。e.g. 5, 27, 65 --> 0, 1, 2
	//    https://www.quora.com/What-is-coordinate-compression-and-what-is-it-used-for/answer/Brian-Bi?srid=pnBg
	//    通过坐标压缩，把复杂度从 positions[i][0]的范围(10^8) 降低到positions.length * 2 (1000 * 2);
	//
	// 2. Solution 1: Brute - Force O(n^2)   n <= positions.length * 2
	//    Solution 2: Segment Tree O(n * log n) n <= positions.length * 2
	
	// Solution 1: Brute - Force
	/*
	static int[] heights;
	
    public static List<Integer> fallingSquares(int[][] positions) {
        // 坐标压缩
    	Set<Integer> set = new HashSet<>();
    	for (int[] pos: positions) {
    		set.add(pos[0]); // 左端点
    		set.add(pos[0] + pos[1] - 1); // 右端点
    	}
    	
    	List<Integer> list = new ArrayList<>(set);
    	Collections.sort(list);
    	
    	Map<Integer, Integer> map = new HashMap<>();
    	int t = 0;
    	for (int i: list) {
    		map.put(i, t++);
    	}
    	
    	// 2. Brute - Force O(n^2)
    	heights = new int[t];  // --> 坐标的范围降低为t （最大为 2 * positions.length）
    	int max = 0;
    	List<Integer> result = new ArrayList<>();
    	
    	for (int[] pos: positions) {
    		int L = map.get(pos[0]);
    		int R = map.get(pos[0] + pos[1] - 1);
    		int h = query(L, R) + pos[1];
    		
    		update(L, R, h);
    		max = Math.max(max, h);
    		result.add(max);
    	}
    	
    	return result;
    }
    
    private static void update(int L, int R, int h) {
    	for (int i = L; i <= R; i++) {
    		heights[i] = Math.max(heights[i], h);
    	}
    }
    
    private static int query(int L, int R) {
    	int result = 0;
    	for (int i = L; i <= R; i++) {
    		result = Math.max(result, heights[i]);
    	}
    	
    	return result;
    }
    */
	
	// Solution 2: Segment Tree
	static class SegmentTreeNode {
		int max;
		int start;
		int end;
		SegmentTreeNode left, right;
		
		SegmentTreeNode(int start, int end) {
			this.start = start;
			this.end = end;
			this.max = 0;
			this.left = null;
			this.right = null;
		}
		
		
	}
	
	private static SegmentTreeNode buildTree(int[] nums, int start, int end) {
		
		SegmentTreeNode node = new SegmentTreeNode(start, end);
		
		if (start > end) {
			return null;
		} else {
			if (start == end) {
				node.max = nums[start];
			} else {
				int mid = start + (end -start)/2;
				node.left = buildTree(nums, start, mid);
				node.right = buildTree(nums, mid+1, end);
				node.max = Math.max(node.left.max, node.right.max);
			}
			return node;
		}
	}
	
    private static void update(SegmentTreeNode root, int start, int end, int h) {
    	
    	if (start < root.start || end > root.end) {
    		return;
    	}   	
    	
    	if (root.start == start && root.end == end) {
    		root.max = Math.max(root.max, h);
    		// 这里要注意和RangeSumQuery的区别，这道题更新的是区间，所以区间下面的子节点也要更新，因此只有到达了最底层子节点时候才返回
    		// 而那道题更新的是最底层子节点
    		if (root.start == root.end) {
    			return;
    		}
    	} 
    	
    	int mid = root.start + (root.end - root.start) / 2;
    	
    	if (end <= mid) {
    		root.max = Math.max(root.max, h);
    		update(root.left, start, end, h);
    	} else if (start >= mid + 1) {
    		root.max = Math.max(root.max, h);
    		update(root.right, start, end, h);
    	} else {
    		root.max = Math.max(root.max, h);
    		update(root.left, start, mid, h);
    		update(root.right, mid + 1, end, h);
    	}
    }
	
    private static int query(SegmentTreeNode root, int start, int end) {
    	
    	if (start < root.start || end > root.end) {
    		return 0;
    	}   	
    	
    	if (root.start == start && root.end == end) {
    		return root.max;
    	} 
    	
    	int mid = root.start + (root.end - root.start) / 2;
    	
    	if (end <= mid) {
    		return query(root.left, start, end);
    	} else if (start >= mid + 1) {
    		return query(root.right, start, end);
    	} else {
    		return Math.max(query(root.left, start, mid),  query(root.right, mid + 1, end));
    	}
    }
    
    /* for debug only
    private static void traverse(SegmentTreeNode root) {
    	if (root == null) {
    		return;
    	}
    	
    	System.out.println("root.start = " + root.start + " root.end = " + root.end + " root.max = " + root.max);
    	traverse(root.left);
    	traverse(root.right);
    }
    */
    public static List<Integer> fallingSquares(int[][] positions) {
        // 坐标压缩
    	Set<Integer> set = new HashSet<>();
    	for (int[] pos: positions) {
    		set.add(pos[0]); // 左端点
    		set.add(pos[0] + pos[1] - 1); // 右端点
    	}
    	
    	List<Integer> list = new ArrayList<>(set);
    	Collections.sort(list);
    	
    	Map<Integer, Integer> map = new HashMap<>();
    	int t = 0;
    	for (int i: list) {
    		map.put(i, t++);
    	}
    	
    	SegmentTreeNode root = buildTree(new int[t], 0, t - 1);
    	
    	int max = 0;
    	List<Integer> result = new ArrayList<>();
    	//System.out.println(map);
    	for (int[] pos: positions) {
    		int L = map.get(pos[0]);
    		int R = map.get(pos[0] + pos[1] - 1);
    		int h = query(root, L, R) + pos[1];
    		//System.out.print("L = " +  L + " R = " + R + " h = " + h);
    		//System.out.println();
    		update(root, L, R, h);
        	//System.out.println("##############");
        	//traverse(root);
        	//System.out.println("##############");
    		max = Math.max(max, h);
    		result.add(max);
    	}
    	

    	return result; 	
    }
    public static void main(String[] args) {
		int[][] test1 = {{1, 2}, {2, 3}, {6, 1}};
		int[][] test2 = {{100, 100}, {200, 100}};
		
		System.out.println(fallingSquares(test1));
		System.out.println(fallingSquares(test2));
	}
}
