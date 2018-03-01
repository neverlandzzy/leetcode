import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;


public class Solution {
    public static int rotatedDigits(int N) {
    	int counter = 0;
    	
        for (int i = 1; i <= N; i++) {
        	if (isValid(i)) {
        		counter++;
        	}
        }
        
        return counter;
    }
    
    private static boolean isValid(int num) {
    	Set<Character> exclusion = new HashSet<>();
    	exclusion.add('3');
    	exclusion.add('4');
    	exclusion.add('7');
    	
    	Set<Character> self = new HashSet<>();
    	self.add('2');
    	self.add('5');
    	self.add('6');
    	self.add('9');
    	
    	String s = String.valueOf(num);
    	boolean result = false;
    	
    	for (int i = 0; i < s.length(); i++) {
    		char c = s.charAt(i);
    		if (exclusion.contains(c)) {
    			return false;
    		}
    		
    		if (self.contains(c)) {
    			result = true;
    		}
    	}
    	
    	return result;
    }
    
    public static boolean escapeGhosts(int[][] ghosts, int[] target) {
    	int[] start = {0, 0};
    	int step = Math.abs(target[1] - start[1]) + Math.abs(target[0] - start[0]);
    	
    	for (int[] g: ghosts) {
    		int gStep = Math.abs(target[1] - g[1]) + Math.abs(target[0] - g[0]);
    		//System.out.println("gStep = " + gStep + " step = " + step);
    		if (gStep <= step) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    

    public static String customSortString(String S, String T) {
        Map<Character, Integer> map = new HashMap<>(); 
        
        for (int i = 0; i < T.length(); i++) {
        	char c = T.charAt(i);
        	map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < S.length(); i++) {
        	char c = S.charAt(i);
        	if (map.containsKey(c)) {
        		int val = map.get(c);
        		while (val > 0) {
        			sb.append(c);
        			val--;
        		}
        		
        		map.put(c, 0);
        	}
        }
        
        for (char c: map.keySet()) {
        	int val = map.get(c);
        	if (val > 0) {
        		while (val > 0) {
        			sb.append(c);
        			val--;
        		}
        	}
        }
        
        return sb.toString();
    }
    
    public static int numTilings(int N) {
        
    }
    
    public static void main(String[] args) {
    	/*
    	System.out.println(rotatedDigits(10));
    	System.out.println(rotatedDigits(10000));
    	

    	int[][] test21 = {{1, 0}, {0, 3}};
    	int[] target21 = {0, 1};
    	int[][] test22 = {{1, 0}};
    	int[] target22 = {2, 0};
    	int[][] test23 = {{2, 0}};
    	int[] target23 = {1, 0};
    	
    	int[][] test24 = {{-10, -9}, {5, 0}, {-7, -3}, {-6, -1}, {-4, -7}};
    	int[] target24 = {7, -2};
    	System.out.println(escapeGhosts(test21, target21));
    	System.out.println(escapeGhosts(test22, target22));
    	System.out.println(escapeGhosts(test23, target23));
    	System.out.println(escapeGhosts(test24, target24));
    	
    	System.out.println(bfs(new int[] {0, 3}, new int[] {0, 1}));
    	
    	System.out.println(customSortString("cba", "abcd"));
    	
    	*/
    	
    	System.out.println(numTilings(3));
	}
}
