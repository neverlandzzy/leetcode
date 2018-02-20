import java.util.HashMap;


public class Solution {
	/*
	 * Given a picture consisting of black and white pixels, and a positive integer N, find the number of black pixels 
	 * located at some specific row R and column C that align with all the following rules:
	 * 
	 * Row R and column C both contain exactly N black pixels.
	 * For all rows that have a black pixel at column C, they should be exactly the same as row R
	 * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
	 * 
	 * Example:
	 * Input:                                            
	 * [['W', 'B', 'W', 'B', 'B', 'W'],    
	 *  ['W', 'B', 'W', 'B', 'B', 'W'],    
	 *  ['W', 'B', 'W', 'B', 'B', 'W'],    
	 *  ['W', 'W', 'B', 'W', 'B', 'W']] 
	 * 
	 * N = 3
	 * Output: 6
	 * Explanation: All the bold 'B' are the black pixels we need (all 'B's at column 1 and 3).
     *             0    1    2    3    4    5         column index                                            
     *     0    [['W', 'B', 'W', 'B', 'B', 'W'],    
     *     1     ['W', 'B', 'W', 'B', 'B', 'W'],    
     *     2     ['W', 'B', 'W', 'B', 'B', 'W'],    
     *     3     ['W', 'W', 'B', 'W', 'B', 'W']]    
     *  row index
     *  
     * Take 'B' at row R = 0 and column C = 1 as an example:
     * Rule 1, row R = 0 and column C = 1 both have exactly N = 3 black pixels. 
     * Rule 2, the rows have black pixel at column C = 1 are row 0, row 1 and row 2. They are exactly the same as row R = 0.
     * 
     * Note:
     * The range of width and height of the input 2D array is [1,200].
	 */
	
	// https://discuss.leetcode.com/topic/81686/verbose-java-o-m-n-solution-hashmap/4
	
    public static int findBlackPixel(char[][] picture, int N) {
        if (picture == null || picture.length == 0 || picture[0].length == 0) {
        	return 0;
        }
        
        int m = picture.length;
        int n = picture[0].length;      
    	HashMap<String, Integer> map = new HashMap<>();
    	int[] colCounter = new int[n];
    	int result = 0;
    	
    	for (int i = 0; i < m; i++) {
    		StringBuilder sb = new StringBuilder();
    		int counter = 0;
    		
    		for (int j = 0; j < n; j++) {
    			sb.append(picture[i][j]);
    			if (picture[i][j] == 'B') {
    				counter++;
    				colCounter[j]++;
    			}
    		}
    		
    		if (counter == N) {
    			if (map.containsKey(sb.toString())) {
    				map.put(sb.toString(), map.get(sb.toString()) + 1);
    			} else {
    				map.put(sb.toString(), 1);
    			}
    		}
    	}
    	
    	for (String key : map.keySet()) {
    		if (map.get(key) != N) {
    			continue;
    		}
    		
    		for (int i = 0; i < n; i++) {
    			if (key.charAt(i) == 'B' && colCounter[i] == N) {
    				result += N;
    			}
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
		char[][] test = {{'W', 'B', 'W', 'B', 'B', 'W'}, 
						 {'W', 'B', 'W', 'B', 'B', 'W'}, 
						 {'W', 'B', 'W', 'B', 'B', 'W'}, 
						 {'W', 'W', 'B', 'W', 'B', 'W'}};
		
		System.out.println(findBlackPixel(test, 3));
	}
}
