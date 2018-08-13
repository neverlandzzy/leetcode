import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Solution {
	
    public static String[] uncommonFromSentences(String A, String B) {
    	Map<String, Integer> map = new HashMap<>();
    	
    	for (String s: A.split(" ")) {
    		map.put(s, map.getOrDefault(s, 0) + 1);
    	}
    	
    	for (String s: B.split(" ")) {
    		map.put(s, map.getOrDefault(s, 0) + 1);
    	}
    	
    	List<String> list = new ArrayList<>();
    	
    	for (String key: map.keySet()) {
    		if (map.get(key) == 1) {
    			list.add(key);
    		}
    	}
    	
    	String[] result = new String[list.size()];
    	for (int i = 0; i < list.size(); i++) {
    		result[i] = list.get(i);
    	}
    	
    	return result;
    }
    
    public static int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] result = new int[R * C][2];
        
        int count = 1;
        int row = 1;
        int col = 1;
        
        int i = r0;
        int j = c0;
        
        result[0][0] = r0;
        result[0][1] = c0;
        
        while (true) {
        	for (int k = 0; k < col; k++) {
        		j++;
        		if (i >= 0 && i < R && j >= 0 && j < C) {
        			result[count][0] = i;
        			result[count][1] = j;
        			count++;
        		}
        	}
        	col++;
        	
        	for (int k = 0; k < row; k++) {
        		i++;
        		if (i >= 0 && i < R && j >= 0 && j < C) {
        			result[count][0] = i;
        			result[count][1] = j;
        			count++;
        		}
        	}
        	
        	row++;    
        	
        	for (int k = 0; k < col; k++) {
        		j--;
        		if (i >= 0 && i < R && j >= 0 && j < C) {
        			result[count][0] = i;
        			result[count][1] = j;
        			count++;
        		}
        	}
        	
        	col++;
        	
        	for (int k = 0; k < row; k++) {
        		i--;
        		if (i >= 0 && i < R && j >= 0 && j < C) {
        			result[count][0] = i;
        			result[count][1] = j;
        			count++;
        		}
        	}
        	
        	row++;  
        	
        	if (count == R * C) {
        		break;
        	}
        }
        
        return result;
    }

    
    public static boolean possibleBipartition(int N, int[][] dislikes) {
    	int[] color = new int[N + 1];
    	Arrays.fill(color, -1);
    	Map<Integer, Set<Integer>> map = new HashMap<>();
    	
    	for (int[] dis: dislikes) {
    		int x = dis[0];
    		int y = dis[1];
    		
    		if (!map.containsKey(x)) {
    			map.put(x, new HashSet<>());
    		}
    		
    		if (!map.containsKey(y)) {
    			map.put(y, new HashSet<>());
    		}
    		
    		map.get(x).add(y);
    		map.get(y).add(x);
    	}
    	
    	for (int i: map.keySet()) {
    		if (color[i] == -1) {
    			Queue<Integer> queue = new LinkedList<>();
				queue.offer(i);
				color[i] = 0;
				
				while (!queue.isEmpty()) {
					Integer node = queue.poll();
					for (int neighbor: map.get(node)) {
						if (color[neighbor] == -1) {
							queue.offer(neighbor);
							color[neighbor] = color[node] == 1 ? 0 : 1;
						} else if (color[neighbor] == color[node]) {
							return false;
						}
					}
				}
    		}
    	}
    	
    	return true;
    }
    
    public static int superEggDrop(int K, int N) {
        
    }
    
	public static void main(String[] args) {
		/*
		String test11A = "this apple is sweet";
		String test11B = "this apple is sour";
		String test12A = "apple apple";
		String test12B = "banana";
		
		String[] result1 = uncommonFromSentences(test11A, test11B);
		String[] result2 = uncommonFromSentences(test12A, test12B);
		
		for (String s: result1) {
			System.out.println(s + ", ");
		}
		
		System.out.println();
		for (String s: result2) {
			System.out.println(s + ", ");
		}
		System.out.println();
		*/
		
		int[][] result21 = spiralMatrixIII(1, 4, 0, 0);
		int[][] result22 = spiralMatrixIII(5, 6, 1, 4);
		
		print(result21);
		print(result22);

		/*
		int[][] test31 = {{1, 2}, {1, 3}, {2, 4}};
		int[][] test32 = {{1, 2}, {1, 3}, {2, 3}};
		int[][] test33 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
		
		System.out.println(possibleBipartition(4, test31));
		System.out.println(possibleBipartition(4, test32));
		System.out.println(possibleBipartition(5, test33));
		
		*/
		
	}
	
	private static void print(int[][] array) {
		for (int[] a: array) {
			for (int i: a) {
				System.out.print(i + ", ");
			}
			System.out.println();
		}
		
		System.out.println();
	}
}
