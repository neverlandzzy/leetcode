import java.util.HashSet;
import java.util.Set;


public class Solution {
	
	
    public static int[] numberOfLines(int[] widths, String S) {
    	int[] result = new int[2];
    	int total = 1;
    	int len = 0;
    	int i = 0;
    	
    	while (i < S.length()) {
    		if (len + widths[S.charAt(i) - 'a'] <= 100) {
    			len += widths[S.charAt(i) - 'a'];
    			i++;
    		} else {
    			total++;
    			len = widths[S.charAt(i) - 'a'];
    			i++;
    		}
    	}
    	
    	result[0] = total;
    	result[1] = len;
    	
    	return result;
    }
    
    public static int uniqueMorseRepresentations(String[] words) {
        String[] code = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.",
        				 "--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        
        Set<String> set = new HashSet<>();
        
        for (String word: words) {
        	StringBuilder sb = new StringBuilder();
        	for (int i = 0; i < word.length(); i++) {
        		char c = word.charAt(i);
        		sb.append(code[c - 'a']);
        	}
        	set.add(sb.toString());
        }
        
        return set.size();
    }
    
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
    	if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
    		return 0;
    	}
    	
    	int m = grid.length;
    	int n = grid[0].length;
    	
        int[] rowMax = new int[m];
        int[] colMax = new int[n];
        
        int result = 0;
        
        for (int i = 0; i < m; i++) {       	
        	for (int j = 0; j < n; j++) {
        		rowMax[i] = Math.max(rowMax[i], grid[i][j]);
        		colMax[j] = Math.max(colMax[j], grid[i][j]);
        	}
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		result += Math.min(rowMax[i], colMax[j]) - grid[i][j];
        	}
        }
        
        return result;
    }
    
    public static boolean splitArraySameAverage(int[] A) {
    	int sum = 0;
    	for (int i: A) {
    		sum += i;
    	}
    	
    	return helper(A, 0, 0, 0, sum);
    }
    
    private static boolean helper(int[] A, int subsum, int count, int pos, int sum) {
    	if (pos == A.length) {
    		if (subsum != 0 && subsum != sum && count != 0 && count != A.length && (subsum * (A.length - count) == (sum - subsum) * (count))) {
    			System.out.println("subsum = " + subsum + " (A.length - count) " + (A.length - count) + " count = " + count + " sub - subsum " + (sum - subsum));
    			return true;
    		}
    		
    		return false;
    	}
    	

    	return (helper(A, subsum + A[pos], count++, pos + 1, sum) || helper(A, subsum, count, pos + 1, sum));
    }
    
	public static void main(String[] args) {
		
		/*
		int[] test11 = {10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};
		int[] result11 = numberOfLines(test11, "abcdefghijklmnopqrstuvwxyz");
		int[] test12 = {4,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10};	
		int[] result12 = numberOfLines(test12, "bbbcccdddaaa");
		
		for (int i: result11) {
			System.out.print(i + ",");
		}
		System.out.println();
		for (int i: result12) {
			System.out.print(i + ",");
		}
		System.out.println();
		
		
		String[] test21 = {"gin", "zen", "gig", "msg"};
		System.out.println(uniqueMorseRepresentations(test21));
		
		int[][] test31 = {{3,0,8,4}, {2,4,5,7}, {9,2,6,3}, {0,3,1,0}};
		
		System.out.println(maxIncreaseKeepingSkyline(test31));
		*/
		
		
		int[] test41 = {1,2,3,4,5,6,7,8};
		int[] test42 = {1,3};
		int[] test43 = {1,6, 1};
		int[] test44 = {17,3,7,12,1};
		
		System.out.println(splitArraySameAverage(test41));
		System.out.println(splitArraySameAverage(test42));
		System.out.println(splitArraySameAverage(test43));
		System.out.println(splitArraySameAverage(test44));
	}
	

}
