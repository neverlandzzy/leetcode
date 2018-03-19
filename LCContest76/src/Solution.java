import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {
	
	
    public static String similarRGB(String color) {
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("#");
    	
    	String r = color.substring(1, 3);
    	String g = color.substring(3, 5);
    	String b = color.substring(5, 7);
    	
    	sb.append(next(r));
    	sb.append(next(g));
    	sb.append(next(b));
    	return sb.toString();
    }
    
    private static String next(String r) {
    	if (r.charAt(0) == r.charAt(1)) {
    		return r;
    	} else {
    		char c = r.charAt(0);
    		String nextR;
    		
    		StringBuilder tmp1 = new StringBuilder();
    		tmp1.append(c).append(c);
    		nextR = tmp1.toString();

    		StringBuilder tmp2 = new StringBuilder();
    		StringBuilder tmp3 = new StringBuilder();
    		
    		if (c + 1 <= 'f') { 
    			char nextC;
    			if (c == '9') {
    				nextC = 'a';
    			} else {
    				nextC = (char) (c + 1);
    			}
        		tmp2.append(nextC).append(nextC);
    		}
    		
    		if (c - 1 >= '0') {
    			char preC;
    			if (c == 'a') {
    				preC = '9';
    			} else {
    				preC = (char) (c - 1);
    			}
    			tmp3.append(preC).append(preC);
    		}

    		int min = different(tmp1.toString(), r);
    		
    		if (tmp2.length() > 0 && different(tmp2.toString(), r) < min) {
    			min = different(tmp2.toString(), r);
    			nextR = tmp2.toString();
    			
    		}

    		if (tmp3.length() > 0 && different(tmp3.toString(), r) < min) {
    			min = different(tmp3.toString(), r);
    			nextR = tmp3.toString();   			
    		}
    		
    		return nextR;

    	}
    }
    
    private static int different(String a, String b) {
    	return Math.abs(Integer.parseInt(a, 16) - Integer.parseInt(b, 16));
    }
    
    
    /*
    public static int minSwap(int[] A, int[] B) {
        int n = A.length;
        //int[][] dp = new int[n][n];
        
        //dp[0][0] = 0;
        int max1 = A[0];
        int max2 = B[0];
        
        int count = 0;
        
        for (int i = 1; i < n; i++) {
       	
        	if (A[i] <= max1 || B[i] <= max2) {
        		count++;
        		max1 = B[i];
        		max2 = A[i];
        	} else {
            	max1 = Math.max(A[i], max1);
            	max2 = Math.max(B[i], max2);
        	}
        }
        
        return count;
    }

    
    static int min = Integer.MAX_VALUE;
   
    public static int minSwap(int[] A, int[] B) {
    	min = Integer.MAX_VALUE;
    	
    	dfs(A, B, 0, 0, Integer.MIN_VALUE, Integer.MIN_VALUE);
    	
    	return min;
    }
    
    private static void dfs(int[] A, int[] B, int pos, int count, int maxA, int maxB) {
    	if (pos == A.length) {
    		min = Math.min(count, min);
    		return;
    	}
    	
    	if (A[pos] <= maxA || B[pos] <= maxB) {
    		return;
    	}
    	
    	for (int i = pos; i < A.length; i++) {
    		dfs(A, B, i + 1, count, A[i], B[i]);
    		swap(A, B, i);
    		dfs(A, B, i + 1, count + 1, B[i], A[i]);
    		swap(A, B, i);
    	}
    	
    }
    
    private static void swap(int[] A, int[] B, int i) {
    	int tmp = A[i];
    	A[i] = B[i];
    	B[i] = tmp;
    }
    */
    
    static class UnionFind {
    	Map<Integer, Integer> map;
    	
    	public UnionFind(int[][] graph) {
    		map = new HashMap<>();
    		
    		for (int i = 0; i < graph.length; i++) {
    			map.put(i, i);
    		}
    	}
    	
    	public int find(int x) {
    		if (map.get(x) == x) {
    			return x;
    		}
    		
    		int pX = find(map.get(x));
    		map.put(x, pX);
    		return pX;
    	}
    	
    	public void union(int x, int y) {
    		 int pX = find(x);
    		 int pY = find(y);
    		 
    		 if (pX != pY) {
    			 map.put(pX, pY);
    		 }
    	}
    }
    
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> result = new ArrayList<>();
        UnionFind uf = new UnionFind(graph);
        
        Set<Integer> set = new HashSet<>();
        
        for (int i = 0; i < graph.length; i++) {
        	set.add(i);
        }
        
        for (int i = 0; i < graph.length; i++) {
        	for (int j: graph[i]) {
        		System.out.println("j = " + j);
        		System.out.println(uf.find(j));
        		if (uf.find(i) == j) {
        			System.out.println("removed" + i + ", " + j);
        			set.remove(i);
        			set.remove(j);
        		}
        		uf.union(j, uf.find(i));
        	}
        	System.out.println(uf.map);
        	System.out.println();
        }
        
        System.out.println(set);
        return result;
    }
    
	public static void main(String[] args) {
		/*
		String test11 = "#09f166";
		System.out.println(similarRGB(test11));
		
		String test12 = "#65d09f";
		System.out.println(similarRGB(test12));
		*/
		
		int[] test211 = {1, 3, 5, 4};
		int[] test212 = {1, 2, 3, 7};
		
		int[] test221 = {3, 3, 8, 9, 10};
		int[] test222 = {1, 7, 4, 6, 8};
		
		int[] test231 = {0,4,4,5,9};
		int[] test232 = {0,1,6,8,10};
				
		//System.out.println(minSwap(test211, test212));	
		//System.out.println(minSwap(test221, test222));
		//System.out.println(minSwap(test231, test232));
		
		int[][] test31 = {{1,2},{2,3},{5},{0},{5},{},{}};
		System.out.println(eventualSafeNodes(test31));
	}
}
