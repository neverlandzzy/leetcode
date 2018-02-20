import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	/*
	 * A virus is spreading rapidly, and your task is to quarantine the infected area by installing walls.
	 * 
	 * The world is modeled as a 2-D array of cells, where 0 represents uninfected cells, and 1 represents cells contaminated with the virus. 
	 * A wall (and only one wall) can be installed between any two 4-directionally adjacent cells, on the shared boundary.
	 * 
	 * Every night, the virus spreads to all neighboring cells in all four directions unless blocked by a wall. Resources are limited. 
	 * Each day, you can install walls around only one region -- the affected area (continuous block of infected cells) that threatens the 
	 * most uninfected cells the following night. There will never be a tie.
	 * 
	 * Can you save the day? If so, what is the number of walls required? If not, and the world becomes fully infected, return the number of 
	 * walls used.
	 * 
	 * Example 1:
	 * Input: grid = 
	 * [[0,1,0,0,0,0,0,1],
	 *  [0,1,0,0,0,0,0,1],
	 *  [0,0,0,0,0,0,0,1],
	 *  [0,0,0,0,0,0,0,0]]
	 * Output: 10
	 * Explanation:
	 * There are 2 contaminated regions.
	 * On the first day, add 5 walls to quarantine the viral region on the left. The board after the virus spreads is:
	 * 
	 * [[0,1,0,0,0,0,1,1],
	 *  [0,1,0,0,0,0,1,1],
	 *  [0,0,0,0,0,0,1,1],
	 *  [0,0,0,0,0,0,0,1]]
	 * 
	 * On the second day, add 5 walls to quarantine the viral region on the right. The virus is fully contained.
	 * 
	 * Example 2:
	 * Input: grid = 
	 * [[1,1,1],
	 *  [1,0,1],
	 *  [1,1,1]]
	 * Output: 4
	 * Explanation: Even though there is only one cell saved, there are 4 walls built.
	 * Notice that walls are only built on the shared boundary of two different cells.
	 * 
	 * Example 3:
	 * Input: grid = 
	 * [[1,1,1,0,0,0,0,0,0],
	 *  [1,0,1,0,1,1,1,1,1],
	 *  [1,1,1,0,0,0,0,0,0]]
	 * Output: 13
	 * Explanation: The region on the left only builds two new walls.
	 * 
	 * Note:
	 * 1. The number of rows and columns of grid will each be in the range [1, 50].
	 * 2. Each grid[i][j] will be either 0 or 1.
	 * 3. Throughout the described process, there is always a contiguous viral region that will infect strictly more uncontaminated squares 
	 *    in the next round.
	 */
	
	// https://leetcode.com/problems/contain-virus/solution/
	// 注意坐标变换的技巧！
	
	static Set<Integer> seen;
	static List<Set<Integer>> regions;
	static List<Set<Integer>> frontiers;
    static List<Integer> perimeters;
    static int R, C;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    
    public static int containVirus(int[][] grid) {
        R = grid.length;
        C = grid[0].length;
        int result = 0;
        
        while (true) {
        	seen = new HashSet<>();
        	regions = new ArrayList<>();
        	frontiers = new ArrayList<>();
        	perimeters = new ArrayList<>();
        	
        	// 对当前的矩阵进行DFS，找出frontier以及其对应的perimeter
        	for (int r = 0; r < R; ++r) {
        		for (int c = 0; c < C; ++c) {
        			if (grid[r][c] == 1 && !seen.contains(r * C + c)) {
        				regions.add(new HashSet<>());
        				frontiers.add(new HashSet<>());
        				perimeters.add(0);
        				helper(r, c, grid);
        			}
        		}
        	}
        	
        	if (regions.isEmpty()) {
        		break;
        	}
        	
        	// 找到当前最大的frontiers，并将其perimeter加入到结果
        	int index = 0;
        	for (int i = 0; i < frontiers.size(); i++) {
        		if (frontiers.get(index).size() < frontiers.get(i).size()) {
        			index = i;
        		}
        	}
        	
        	result += perimeters.get(index);
        	
        	for (int i = 0; i < regions.size(); i++) {
        		// 在当前dfs找出的region中，将frontier最大(i == index)的region隔离，即置为-1
        		// 其余的region向外扩散1步 
        		if (i == index) {
        			for (int coordinate: regions.get(i)) {
        				grid[coordinate/ C][coordinate % C] = -1;
        			}
        		} else {
        			for (int coordinate: regions.get(i)) {
        				int r = coordinate / C;
        				int c = coordinate % C;
        				
        				for (int k = 0; k < 4; k++) {
        					int nextR = r + dr[k];
        					int nextC = c + dc[k];
        					if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C && grid[nextR][nextC] == 0) {
        						grid[nextR][nextC] = 1;
        					}
        				}
        			}
        		}
        	}
        }
        
        return result;
    }
    
    // 对一个cell(r, c) == 1， 若其邻居 == 1，则加入region，若其邻居 == 0则加入frontiers
    private static void helper(int r, int c, int[][] grid) {
    	if (!seen.contains(r * C + c)) {
    		seen.add(r * C + c);
    		
    		int N = regions.size();
    		regions.get(N - 1).add(r * C + c);
    		
    		for (int i = 0; i < 4; i++) {
    			int nextR = r + dr[i];
    			int nextC = c + dc[i];
    			
    			if (nextR >= 0 && nextR < R && nextC >= 0 && nextC < C) {
    				if (grid[nextR][nextC] == 1) {
    					helper(nextR, nextC, grid);
    				} else if (grid[nextR][nextC] == 0) {
    					frontiers.get(N - 1).add(nextR * C + nextC);
    					perimeters.set(N - 1, perimeters.get(N - 1) + 1);
    				}
    			}
    		}
    	}
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{0,1,0,0,0,0,0,1}, {0,1,0,0,0,0,0,1}, {0,0,0,0,0,0,0,1}, {0,0,0,0,0,0,0,0}};
		int[][] test2 = {{1,1,1}, {1,0,1}, {1,1,1}};
		int[][] test3 = {{1,1,1,0,0,0,0,0,01}, {1,0,1,0,1,1,1,1,1}, {1,1,1,0,0,0,0,0,0}};
		
		System.out.println(containVirus(test1));
		System.out.println(containVirus(test2));
		System.out.println(containVirus(test3));
	}
}
