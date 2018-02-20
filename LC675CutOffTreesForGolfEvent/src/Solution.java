import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


public class Solution {
	
	/*
	 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map, in this map:
	 * 
	 * 0 represents the obstacle can't be reached.
	 * 1 represents the ground can be walked through.
	 * 
	 * The place with number bigger than 1 represents a tree can be walked through, and this positive number represents the tree's height.
	 * You are asked to cut off all the trees in this forest in the order of tree's height - always cut off the tree with lowest height first. 
	 * And after cutting, the original place has the tree will become a grass (value 1).
	 * 
	 * You will start from the point (0, 0) and you should output the minimum steps you need to walk to cut off all the trees. If you can't cut 
	 * off all the trees, output -1 in that situation.
	 * 
	 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
	 * 
	 * Example 1:
	 * Input: 
	 * [
	 *  [1,2,3],
	 *  [0,0,4],
	 *  [7,6,5]
	 * ]
	 * Output: 6
	 * 
	 * Example 2:
	 * Input: 
	 * [
	 *  [1,2,3],
	 *  [0,0,0],
	 *  [7,6,5]
	 * ]
	 * Output: -1
	 * 
	 * Example 3:
	 * Input: 
	 * [
	 *  [2,3,4],
	 *  [0,0,5],
	 *  [8,7,6]
	 * ]
	 * Output: 6
	 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
	 * 
	 * Hint: size of the given matrix will not exceed 50x50.
	 */
	
	// https://leetcode.com/problems/cut-off-trees-for-golf-event/discuss/107404
	// 将int[]{row, col, height}按height排序存入heap，每次poll的都是最小height；
	// 从(0, 0)开始，每次用bfs计算起点到终点（每次poll出的新的坐标）的最小步数
    public static int cutOffTree(List<List<Integer>> forest) {
    	
    	if (forest == null || forest.size() == 0 || forest.get(0) == null || forest.get(0).size() == 0) {
    		return 0;
    	}
    	
    	int m = forest.size();
    	int n = forest.get(0).size();
    	
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>() {
        	public int compare(int[] o1, int[] o2) {
        		return o1[2] - o2[2];
        	}
        });
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (forest.get(i).get(j) > 1) {
        			heap.offer(new int[] {i, j, forest.get(i).get(j)});
        		}
        	}
        }
        
        int result = 0;
        int[] start = new int[2]; // 从start(0, 0)开始
        
        while (!heap.isEmpty()) {
        	int[] tree = heap.poll();
        	int step = bfs(forest, start, tree);
        	
        	if (step == -1) {
        		return -1;
        	}
        	
        	result += step;
        	start[0] = tree[0];
        	start[1] = tree[1];
        }
        
        return result;
    }
    
    private static int bfs(List<List<Integer>> forest, int[] start, int[] tree) {
    	int[][] direction = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    	int m = forest.size();
    	int n = forest.get(0).size();
    	boolean[][] visited = new boolean[m][n];
    	Queue<int[]> queue = new LinkedList<>();
    	int step = 0;
    	
    	queue.offer(start);
    	visited[start[0]][start[1]] = true;
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			int[] pos = queue.poll();
    			
    			if (pos[0] == tree[0] && pos[1] == tree[1]) {
    				return step;
    			}
    			
    			for (int[] dir: direction) {
    				int r = pos[0] + dir[0];
    				int c = pos[1] + dir[1];
    				
    				if (r >= 0 && r < m && c >= 0 && c < n && forest.get(r).get(c) != 0 && !visited[r][c]) {
    					queue.offer(new int[] {r, c});
    					visited[r][c] = true;
    				}
    			}
    		}
    		
    		step++;
    	}
    	
    	return -1;
    }
    
    public static void main(String[] args) {
		List<List<Integer>> test1 = new ArrayList<>();
		test1.add(Arrays.asList(1, 2, 3));
		test1.add(Arrays.asList(0, 0, 4));
		test1.add(Arrays.asList(7, 6, 5));
		
		List<List<Integer>> test2 = new ArrayList<>();
		test2.add(Arrays.asList(1, 2, 3));
		test2.add(Arrays.asList(0, 0, 0));
		test2.add(Arrays.asList(7, 6, 5));
		
		List<List<Integer>> test3 = new ArrayList<>();
		test3.add(Arrays.asList(2, 3, 4));
		test3.add(Arrays.asList(0, 0, 5));
		test3.add(Arrays.asList(8, 7, 6));
		
		System.out.println(cutOffTree(test1));
		System.out.println(cutOffTree(test2));
		System.out.println(cutOffTree(test3));
	}
}
