import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Solution {
	
    public static int surfaceArea(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        int result = 0;
        
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] > 0) {
        			dp[i][j] = 2;
        		}
        	}
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		for (int[] dir: direction) {
        			int nextI = i + dir[0];
        			int nextJ = j + dir[1];
        			
        			if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) {
        				dp[i][j] += grid[i][j];
        			} else if (grid[nextI][nextJ] > grid[i][j]) {
        				dp[i][j] += grid[nextI][nextJ] - grid[i][j];
        			}
        			
        		}
        	}
        }
        
        for (int[] d: dp) {
        	for (int k: d) {
        		result += k;
        	}
        }
        
        return result;
    }
    
    
    public static int numSpecialEquivGroups(String[] A) {
        Set<String> set = new HashSet<>();
        
        for (String s: A) {
        	set.add(swap(s));
        }
        
        return set.size();
    }
    
    private static String swap(String s) {
    	char[] strs = s.toCharArray();
    	List<Character> even = new ArrayList<>();
    	List<Character> odd  = new ArrayList<>(); 
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < strs.length; i += 2) {
    		even.add(strs[i]);
    	}
    	for (int i = 1; i < strs.length; i += 2) {
    		odd.add(strs[i]);
    	}
    	
    	Collections.sort(even);
    	Collections.sort(odd);
    	
    	for (char c: even) {
    		sb.append(c);
    	}
    	
    	for (char c: odd) {
    		sb.append(c);
    	}
    	
    	return sb.toString();
    }
    

    public static List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> result = new ArrayList<>();
        
        
        if (N == 1) {
        	TreeNode root = new TreeNode(0);
        	result.add(root);
        	return result;
        }
        
        for (int i = 1; i < N; i += 2) {
        	List<TreeNode> leftList = allPossibleFBT(i);
        	List<TreeNode> rightList = allPossibleFBT(N - i - 1);
        	
        	for (TreeNode left: leftList) {
        		for (TreeNode right: rightList) {
        			TreeNode root = new TreeNode(0);
        			root.left = left;
        			root.right = right;
        			result.add(root);
        		}     		
        	}  	
        }
        return result;
    }    

    
    
	public static void main(String[] args) {
		/*
		int[][] test11 = {{2}};
		int[][] test12 = {{1,2},{3, 4}};
		int[][] test13 = {{1,0},{0,2}};
		int[][] test14 = {{1,1,1},{1,0,1},{1,1,1}};
		int[][] test15 = {{2,2,2},{2,1,2},{2,2,2}};
		
		System.out.println(surfaceArea(test11));
		System.out.println(surfaceArea(test12));
		System.out.println(surfaceArea(test13));
		System.out.println(surfaceArea(test14));
		System.out.println(surfaceArea(test15));

		
		String[] test21 = {"a","b","c","a","c","c"};
		String[] test22 = {"aa","bb","ab","ba"};
		String[] test23 = {"abc","acb","bac","bca","cab","cba"};
		String[] test24 = {"abcd","cdab","adcb","cbad"};
		
		System.out.println(numSpecialEquivGroups(test21));
		System.out.println(numSpecialEquivGroups(test22));
		System.out.println(numSpecialEquivGroups(test23));
		System.out.println(numSpecialEquivGroups(test24));
		*/
		
		System.out.println(allPossibleFBT(7));

	}
}
