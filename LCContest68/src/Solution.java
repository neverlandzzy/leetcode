import java.util.ArrayList;
import java.util.List;


public class Solution {
	
	
    public static boolean isToeplitzMatrix(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
    		return true;
    	}
    	
    	int m = matrix.length;
    	int n = matrix[0].length;
    	
    	for (int i = 0; i < m; i++) {
    		int start = 0;
    		int end = i;
    		while (start < m - 1 && end < n - 1) {
    			
    			if (matrix[start][end] != matrix[start + 1][end + 1]) {
    				
    				return false;
    			}
    			start++;
    			end++;
    		}
    	}
    	
    	for (int i = 1; i < m; i++) {
    		int start = i;
    		int end = 0;
    		while (start < m - 1 && end < n - 1) {
    			if (matrix[start][end] != matrix[start + 1][end + 1]) {
    				
    				return false;
    			}
    			start++;
    			end++;
    		}
    		
    	}
    	
    	return true;
    }
    
    public static String reorganizeString(String S) {
        int n = S.length();
        int[] map = new int[26];
        int max = 0;
        
        for (int i = 0; i < n; i++) {
        	char c = S.charAt(i);
        	map[c - 'a']++;
        	if (max < map[c - 'a']) {
        		max = map[c - 'a'];
        	}
        }
        
        if (2 * max - n > 1) {
        	return "";
        }
        
        char[] array = new char[n];
        int start = 0;
        for (int i = 0; i < 26; i++) {
        	int tmp = start;
        	if (map[i] != 0) {
        		char c = (char)(i + 'a');
        		int interval = n / map[i];
        		while (map[i] != 0) {
        			array[tmp] = c;
        			tmp += interval;
        			map[i]--;
        		}
        	}
        	start++;
        }
        
        return new String(array);
    }
    

    
    /*
    public static int maxChunksToSorted(int[] arr) {
        
    }
    */
    public static void main(String[] args) {
    	/*
		int[][] test11 = {{1,2,3,4},{5,1,2,3},{9,5,1,2}};
		int[][] test12 = {{1, 2}, {2, 2}};
		
		System.out.println(isToeplitzMatrix(test11));
		System.out.println(isToeplitzMatrix(test12));
		*/
    	
    	System.out.println(reorganizeString("aab"));
    	System.out.println(reorganizeString("aaaabbc"));
    	System.out.println(reorganizeString("eqpspvbpppwpgyppppe"));
    	
    	/*
    	int[] test31 = {4, 3, 2, 1, 0};
    	int[] test32 = {1, 0, 2, 3, 4};
    	
    	System.out.println(maxChunksToSorted(test31));
    	System.out.println(maxChunksToSorted(test32));
    	*/

	}
}
