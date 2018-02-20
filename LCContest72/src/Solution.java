import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Solution {

    public static List<String> letterCasePermutation(String S) {
        char[] charArray = S.toCharArray();
        Set<String> set = new HashSet<>();
        
        helper(charArray, set, 0);
        List<String> result = new ArrayList<>();
        result.addAll(set);
        return result;
    }
	
    private static void helper(char[] charArray, Set<String> result, int pos) {  
    	
    	
    	result.add(new String(charArray));

    	
    	for (int i = pos; i < charArray.length; i++) {
    		char c = charArray[i];
			if (Character.isLowerCase(c)) {
				charArray[i] = Character.toUpperCase(c);

			} else if (Character.isUpperCase(c)) {
				charArray[i] = Character.toLowerCase(c);				
			} 
			helper(charArray, result, i + 1);
			charArray[i] = c;
    	}
    }
    
    public static boolean isBipartite(int[][] graph) {
        Set<Integer> A = new HashSet<>();
        Set<Integer> B = new HashSet<>();
        
        
        for (int i = 0; i < graph.length; i++) {
        	if (A.contains(i)) {
        		for (int j: graph[i]) {
        			if (A.contains(j)) {
        				return false;
        			}
        			B.add(j);
        		}
        	} else if (B.contains(i)){
        		for (int j: graph[i]) {
        			if (B.contains(j)) {
        				return false;
        			}
        			A.add(j);
        		}
        	} else {
        		A.add(i);
        		for (int j: graph[i]) {
        			B.add(j);
        		}
        		
        	}

        }
        

        return true;
    }
    
    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    	List<ArrayList<Integer>> adj = new ArrayList<>();
    	
    	for (int i = 0; i < n; i++) {
    		ArrayList<Integer> list = new ArrayList<Integer>();
    		adj.add(list);
    	}
    	
    	for (int i = 0; i < flights.length; i++) {
    		adj.get(flights[i][0]).add(flights[i][1]);
    	}

    	int level = 0;
    	int cost = 0;
    	int min = Integer.MAX_VALUE;
    	
    	Queue<Integer> queue = new LinkedList<Integer>();
    	queue.offer(src);
    	
    	while (!queue.isEmpty()) {
    		int size = queue.size();
    		for (int i = 0; i < size; i++) {
    			int cur = queue.poll();
    			for (int neighbor: adj.get(cur)) {
    				
    			}
    			
    		}
    		
    		
    	}
    	
    	return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    
	public static void main(String[] args) {
		/*
		String test11 = "a1b2";
		String test12 = "3z4";
		String test13 = "12345";
		String test14 = "a";
		String test15 = "aa";
		
		System.out.println(letterCasePermutation(test11));
		System.out.println(letterCasePermutation(test12));
		System.out.println(letterCasePermutation(test13));
		System.out.println(letterCasePermutation(test14));
		System.out.println(letterCasePermutation(test15));
		
		int[][] test21 = {{1,3}, {0,2}, {1, 3}, {0, 2}};
		int[][] test22 = {{1, 2, 3}, {0,2}, {0, 1, 3}, {0, 2}};
		
		System.out.println(isBipartite(test21));
		System.out.println(isBipartite(test22));
		*/
		int[][] test31 = {{0, 1, 100}, {1, 2, 100}, {0, 2, 500}};
		System.out.println(findCheapestPrice(3, test31, 0, 2, 1));
		System.out.println(findCheapestPrice(3, test31, 0, 2, 0));		
	}
}
