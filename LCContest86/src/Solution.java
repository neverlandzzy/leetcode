import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Solution {
    public static int numMagicSquaresInside(int[][] grid) {
        int counter = 0;
        
        int m = grid.length;
        int n = grid[0].length;
        
        for (int i = 0; i < m - 2; i++) {
    		//System.out.println(i);
        	for (int j = 0; j < n - 2; j++) {
        
        		if (valid(grid, i, j)) {       			
        			counter++;
        		}
        	}
        }
        
        return counter;
    }
    
    private static boolean valid(int[][] grid, int x, int y) {
    	int[] visited = new int[10];
    	int row = 0;

    	for (int i = y; i < y + 3; i++) {
    		row += grid[x][i];
    	}

    	for (int i = y; i < y + 3; i++) {
    		int colSum = 0;
    		for (int j = x; j < x + 3; j++) {
    			colSum += grid[j][i];
    		}
    		
    		if (colSum != row) {
    			return false;
    		}
    	}
    	
    	for (int i = x + 1; i < x + 3; i++) {
    		int rowSum = 0;
    		for (int j = y; j < y + 3; j++) {
    			rowSum += grid[i][j];
    		}

    		if (rowSum != row) {
    			return false;
    		}
    	}
    	
    	int sum1 = grid[x][y] + grid[x + 1][y + 1] + grid[x + 2][y + 2];
    	
    	
    	if (sum1 != row) {
    		return false;
    	}
    	
    	int sum2 = grid[x][y + 2] + grid[x + 1][y + 1] + grid[x + 2][y];
    	
    	if (sum2 != row) {
    		return false;
    	}
		
    	for (int i = x; i < x + 3; i++) {
    		for (int j = y; j < y + 3; j++) {
    			if (grid[i][j] <= 0 || grid[i][j] > 9) {
    				return false;
    			}
    			
    			if (visited[grid[i][j]] == 1) {
    				return false;
    			}
    			
    			visited[grid[i][j]] = 1;
    		}
    	}
    	
    	return true;
    }
    
    
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        if (rooms == null || rooms.size() == 0) {
            return true;
        }
        
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        int counter = 1;
        visited[0] = true;
        for (int i: rooms.get(0)) {
        	if (!visited[i]) {
        		queue.offer(i);
        		visited[i] = true;
        		counter++;
        	}
        }
        //System.out.println(queue);
        while (!queue.isEmpty()) {
        	//System.out.println(queue);
        	int room = queue.poll();
        	
        	for (int nextRoom: rooms.get(room)) {
        		if (nextRoom != room && !visited[nextRoom]) {
        			queue.offer(nextRoom);
        			visited[nextRoom] = true;
        			counter++;
        		}
        	}
        }
        //System.out.println(counter);
        return counter == n;
    }
    

    public static List<Integer> splitIntoFibonacci(String S) {
        List<Integer> result = new ArrayList<>();
        
        helper(result, S, 0);
        return result;
    }
    
    private static boolean helper(List<Integer> result, String S, int pos) {

    	if (pos == S.length()) {
    		if (result.size() > 2) {
    			return true;
    		}
    	}
    	
    	for (int i = pos + 1; i <= S.length(); i++) {
    		String sub = S.substring(pos, i);
    		Long subL = Long.parseLong(sub);
    		if (subL >= Integer.MAX_VALUE) {
    			break;
    		}
    		
    		if (sub.startsWith("0") && Integer.parseInt(sub) != 0) {
    			continue;
    		}
    		if (result.size() < 2) {
    			result.add(Integer.parseInt(sub));
    			if (helper (result, S, i)) {
    				return true;
    			}
    			result.remove(result.size() - 1);
    		} else {
    			int sum = result.get(result.size() - 1) + result.get(result.size() - 2);
    			String sumStr = String.valueOf(sum);
    			//System.out.println("sumStr = " + sumStr + " sub = " + sub);
    			if (sumStr.equals(sub)) {
    				result.add(sum);
    				if (helper(result, S, i)) {
    					return true;
    				}
    				result.remove(result.size() - 1);
    			}
    		}    		
    	}
    	return false;
    }
    
    public static void main(String[] args) {
    	/*
		int[][] test11 = {{4,3,8,4}, {9,5,1,9}, {2,7,6,2}};
		int[][] test12 = {{10, 3, 5}, {1, 6, 11}, {7, 9, 2}};
		int[][] test13 = {{3, 2, 9, 2, 7}, {6, 1, 8, 4, 2}, {7, 5, 3, 2, 7}, {2, 9, 4, 9, 6}, {4, 3, 8, 2, 5}};

		System.out.println(numMagicSquaresInside(test11));
		System.out.println(numMagicSquaresInside(test12));
		System.out.println(numMagicSquaresInside(test13));
		
		List<List<Integer>> test21 = new ArrayList<>();
		List<Integer> l1 = new ArrayList<>();
		List<Integer> l2 = new ArrayList<>();
		List<Integer> l3 = new ArrayList<>();
		List<Integer> l4 = new ArrayList<>();
		l1.add(1);
		l2.add(2);
		l3.add(3);
		test21.add(l1);
		test21.add(l2);
		test21.add(l3);
		test21.add(l4);
		System.out.println(canVisitAllRooms(test21));
		
		
		List<List<Integer>> test22 = new ArrayList<>();
		List<Integer> l5 = new ArrayList<>();
		List<Integer> l6 = new ArrayList<>();
		List<Integer> l7 = new ArrayList<>();
		List<Integer> l8 = new ArrayList<>();
		l5.add(1);
		l5.add(3);
		l6.add(3);
		l6.add(0);
		l6.add(1);
		l7.add(2);
		l8.add(0);
		test22.add(l5);
		test22.add(l6);
		test22.add(l7);
		test22.add(l8);
		System.out.println(canVisitAllRooms(test22));

		
		List<List<Integer>> test23 = new ArrayList<>();
		List<Integer> l9 = new ArrayList<>();
		List<Integer> l10 = new ArrayList<>();
		List<Integer> l11 = new ArrayList<>();
		l9.add(1);
		l9.add(2);
		l10.add(2);
		l10.add(2);
		l10.add(1);
		l11.add(1);
		test23.add(l9);
		test23.add(l10);
		test23.add(l11);
		System.out.println(canVisitAllRooms(test23));
		*/
    	
    	System.out.println(splitIntoFibonacci("11235813"));
    	System.out.println(splitIntoFibonacci("112358130"));
    	System.out.println(splitIntoFibonacci("1101111"));
    	System.out.println(splitIntoFibonacci("0123"));
    	
	}
    
    
}
