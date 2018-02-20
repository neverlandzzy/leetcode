
public class Solution {
	/*
	 * Given a picture consisting of black and white pixels, find the number of black lonely pixels.
	 * 
	 * The picture is represented by a 2D char array consisting of 'B' and 'W', which means black and white pixels respectively.
	 * 
	 * A black lonely pixel is character 'B' that located at a specific position where the same row and same column don't have any 
	 * other black pixels.
	 * 
	 * Example:
	 * 
	 * Input: 
	 * [['W', 'W', 'B'],
	 *  ['W', 'B', 'W'],
	 *  ['B', 'W', 'W']]
	 *  
	 * Output: 3
	 * Explanation: All the three 'B's are black lonely pixels.
	 * 
	 * Note:
	 * The range of width and height of the input 2D array is [1,500].
	 */
	
	// Solution 1: Time: O(mn), Space: O(m + n)
	/*
    public static int findLonelyPixel(char[][] picture) {
        int m = picture.length;
        int n = picture[0].length;
        
        int[] rowCounter = new int[m];
        int[] colCounter = new int[n];
        int result = 0;
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (picture[i][j] == 'B') {
        			rowCounter[i]++;
        			colCounter[j]++;
        		}
        	}
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (picture[i][j] == 'B' && rowCounter[i] == 1 && colCounter[j] == 1) {
        			result++;
        		}
        	}
        }
        
        return result;
    }
    */
	
	// Solution 2: Time: O(mn), Space: O(1)
    public static int findLonelyPixel(char[][] picture) {
        int m = picture.length;
        int n = picture[0].length;
        
        int firstRowCounter = 0;
        int result = 0;
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (picture[i][j] == 'B') {
        			if (picture[0][j] != 'Y' && picture[0][j] != 'V') {
        				// 若第一行有B，从B开始加有可能加到恰好W。 所以加到V时就停止。
        				picture[0][j]++;
        			}
        			
        			if (i == 0) {
        				firstRowCounter++;
        			} else {
        				if (picture[i][0] != 'Y' && picture[i][0] != 'V') {
        					picture[i][0]++;
        				}
        			}
        		}
        	}
        }
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (picture[i][j] < 'W' && (picture[0][j] == 'C' || picture[0][j] == 'X')) {
        			if (i == 0) {
        				result += firstRowCounter == 1 ? 1 : 0;
        			} else if (picture[i][0] == 'C' || picture[i][0] == 'X') {
        				result++;
        			}
        		}
        	}
        }
        
        
        return result;
    }
    
    public static void main(String[] args) {
		char[][] test = {{'W', 'W', 'B'}, {'W', 'B', 'W'}, {'B', 'W', 'W'}};
		System.out.println(findLonelyPixel(test));
	}
}
