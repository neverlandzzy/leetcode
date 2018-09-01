import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


public class Solution {
	
	public static int match(List<String> grid1, List<String> grid2) {
		int m1 = grid1.size();
		int m2 = grid2.size();
		
		int n1 = 0, n2 = 0;
		for (String s: grid1) {
			n1 = Math.max(n1, s.length());
		}
		
		for (String s: grid2) {
			n2 = Math.max(n2, s.length());
		}
		
		int m = Math.max(m1, m2);
		int n = Math.max(n1, n2);
		int[][] image1 = new int[m][n];
		int[][] image2 = new int[m][n];

		int result = 0;
		
		for (int i = 0; i < grid1.size(); i++) {
			String s = grid1.get(i);
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '1') {
					image1[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < grid2.size(); i++) {
			String s = grid2.get(i);
			for (int j = 0; j < s.length(); j++) {
				if (s.charAt(j) == '1') {
					image2[i][j] = 1;
				}
			}
		}
		
		Map<String, String> map = new HashMap<>();
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (image1[i][j] == 1) {
					StringBuilder sb= new StringBuilder();
					helper(image1, i, j, sb);
					map.put(i + "," + j, sb.toString());
				}
			}
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (image2[i][j] == 1) {
					StringBuilder sb= new StringBuilder();
					helper(image2, i, j, sb);
					String key = i + "," + j;
					if (map.containsKey(key)) {
						if (map.get(key).equals(sb.toString())) {
							result++;
						}
					}
				}
			}
		}		
		
		return result;
	}
	
    private static void helper(int[][] grid, int i, int j, StringBuilder sb) {
    	Queue<int[]> queue = new LinkedList<>();
    	queue.offer(new int[]{i, j});
    	grid[i][j] = 0;
    	int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    	String[] flag = {"u", "d", "l", "r"};
    	
    	while (!queue.isEmpty()) {
    		int[] cur = queue.poll();
    		for (int k = 0; k < direction.length; k++) {
    			int x = cur[0] + direction[k][0];
    			int y = cur[1] + direction[k][1];
    			
    			if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
    				queue.offer(new int[]{x, y});
    				sb.append(flag[k]);
    				grid[x][y] = 0;
    			}
    		}
    		
    		sb.append("b");
    	}
    }
    
	
	
	public static void main(String[] args) {
		List<String> test11 = new ArrayList<>(Arrays.asList("0100", "1001", "0011", "0011"));
		List<String> test12 = new ArrayList<>(Arrays.asList("0101", "1001", "0011", "0011"));
		
		System.out.println(match(test11, test12));
		
		List<String> test21 = new ArrayList<>(Arrays.asList("0010", "0111", "0100", "1111"));
		List<String> test22 = new ArrayList<>(Arrays.asList("0010", "0111", "0110", "1111"));
		System.out.println(match(test21, test22));
		
		List<String> test31 = new ArrayList<>(Arrays.asList("001", "011", "100"));
		List<String> test32 = new ArrayList<>(Arrays.asList("001", "011", "101"));
		System.out.println(match(test31, test32));
		
	}
}
