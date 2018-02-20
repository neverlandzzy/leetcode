import java.util.Arrays;


public class Solution {
	
    public static int minCostClimbingStairs(int[] cost) {
    	/*
    	if (cost == null || cost.length == 0) {
    		return 0;
    	}
    	
    	if (cost.length == 1) {
    		return cost[0];
    	}
    	*/
    	
    	int n = cost.length;
    	int[] dp = new int[n + 1];
    	dp[0] = 0;
    	dp[1] = 0;
    	
    	for (int i = 2; i <= n; i++) {
    		dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
    	}

    	return dp[n];
    }
    
    public static String shortestCompletingWord(String licensePlate, String[] words) {
        String lowerCase = licensePlate.toLowerCase();
        int[] map = new int[26];
        
        for (int i = 0; i < lowerCase.length(); i++) {
        	char c = lowerCase.charAt(i);
        	if (Character.isLetter(c)) {
        		map[c - 'a']++;
        	}
        }
        
        int length = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < words.length; i++) {
        	String word = words[i];
        	if (isValid(map, word)) {
        		if (word.length() < length) {
        			index = i;
        			length = word.length();
        		}
        	}
        }
        	
        return words[index];
    }
    
    private static boolean isValid(int[] map, String word) {
    	int[] tmp = Arrays.copyOf(map, map.length);
    	for (int i = 0; i < word.length(); i++) {
    		char c = word.charAt(i);
    		tmp[c - 'a']--;
    	}
    	
    	for (int i = 0; i < 26; i++) {
    		if (tmp[i] > 0) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public static  int countCornerRectangles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int counter = 0;
        
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1) {
        			for (int l = i + 1; l < m; l++) {
        				if (grid[l][j] == 1) {
        					for (int k = j + 1; k < n; k++) {
        						if (grid[i][k] == 1) {
        							if (grid[l][k] == 1) {
        								counter++;
        							}
        						}
        					}
        				}
        			}
        		}
        	}
        }
        
        return counter;
    }
    
    public static int containVirus(int[][] grid) {
        
    }
    
	public static void main(String[] args) {
		
		int[][] test41 = {{0,1,0,0,0,0,0,1}, {0,1,0,0,0,0,0,1}, {0,0,0,0,0,0,0,1}, {0,0,0,0,0,0,0,0}};
		int[][] test42 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
		int[][] test43 = {{1,1,1,0,0,0,0,0,0}, {1,0,1,0,1,1,1,1,1}, {1,1,1,0,0,0,0,0,0}};
		
		System.out.println(containVirus(test41));
		System.out.println(containVirus(test42));
		System.out.println(containVirus(test43));
		/*
		
		int[][] test31 = {{1, 0, 0, 1, 0}, {0, 0, 1, 0, 1}, {0, 0, 0, 1, 0}, {1, 0, 1, 0, 1}};
		int[][] test32 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		int[][] test33 = {{1, 1, 1, 1}};
		
		System.out.println(countCornerRectangles(test31));
		System.out.println(countCornerRectangles(test32));
		System.out.println(countCornerRectangles(test33));
		
				
		int[] test11 = {10, 15, 20};
		int[] test12 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
		
		System.out.println(minCostClimbingStairs(test11));
		System.out.println(minCostClimbingStairs(test12));
		

		String licensePlate1 = "1s3 PSt";
		String[] words1 = {"step", "steps", "stripe", "stepple"};
		String licensePlate2 = "1s3 456";
		String[] words2 = {"looks", "pest", "stew", "show"};
		
		
		System.out.println(shortestCompletingWord(licensePlate1, words1));
		System.out.println(shortestCompletingWord(licensePlate2, words2));
		*/
	}
}
