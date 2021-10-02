
public class Solution {
	/**
	 * The demons had captured the princess (P) and imprisoned her in the bottom-right 
	 * corner of a dungeon. The dungeon consists of M x N rooms laid out in a 2D grid. 
	 * Our valiant knight (K) was initially positioned in the top-left room and must fight his way 
	 * through the dungeon to rescue the princess.
	 * 
	 * The knight has an initial health point represented by a positive integer. 
	 * If at any point his health point drops to 0 or below, he dies immediately.
	 * 
	 * Some of the rooms are guarded by demons, so the knight loses health 
	 * (negative integers) upon entering these rooms; other rooms are either empty (0's) or 
	 * contain magic orbs that increase the knight's health (positive integers).
	 * 
	 * In order to reach the princess as quickly as possible, the knight decides to move 
	 * only rightward or downward in each step.
	 * 
	 * Write a function to determine the knight's minimum initial health so that 
	 * he is able to rescue the princess.
	 * 
	 * For example, given the dungeon below, the initial health of the knight must be at least 
	 * 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
	 * 
	 *  -2(K)	-3		3
	 *  -5	    -10		1
	 *	10	    30		-5(P)
	 */
	
    public static int calculateMinimumHP(int[][] dungeon) {
        
    	// Solution 1: 2d - DP 
    	/*
    	int m = dungeon.length;
    	int n = dungeon[0].length;
    	
    	int[][]health = new int[m][n];
    	
    	health[m-1][n-1] = Math.max(1-dungeon[m-1][n-1], 1);
    	
    	for (int i = m - 2; i >= 0; i--) {
    		health[i][n-1] = Math.max(health[i+1][n-1] - dungeon[i][n-1], 1);
    	}
    	
    	for (int i = n - 2; i >= 0; i--) {
    		health[m-1][i] = Math.max(health[m-1][i+1] - dungeon[m-1][i], 1);
    	}
    	
    	for (int i = m - 2; i >= 0; i--) {
    		for (int j = n - 2; j >= 0; j--) {
    			health[i][j] = Math.max(Math.min(health[i+1][j], health[i][j+1]) - dungeon[i][j], 1);
    		}
    	}
    	

    	return health[0][0];
    	*/
    	
    	// Solution 2: 滚动数组 2d - DP
    	
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        
        int m = dungeon.length;
        int n = dungeon[0].length;
        
        int min = Math.min(m, n);
        int max = Math.max(m, n);
        
        int[] d = new int[min];
        
        d[min - 1] = Math.max(1, 1 - dungeon[m - 1][n - 1]);
        
        for (int i = min - 2; i >= 0; i--) {
            if (m > n) {
                d[i] = Math.max(1, d[i + 1] - dungeon[m - 1][i]);
            } else {
                d[i] = Math.max(1, d[i + 1] - dungeon[i][n - 1]);
            }
        }
        
        for (int i = max - 2; i >= 0; i--) {
            
            if (m > n) {
            	d[min - 1] = Math.max(1, d[min - 1] - dungeon[i][n - 1]);
        	} else {
                d[min - 1] = Math.max(1, d[min - 1] - dungeon[m - 1][i]);
            }
            
            for (int j = min - 2; j >= 0; j--) {

                if (m > n) {
                    d[j] = Math.max(1, Math.min(d[j], d[j + 1]) - dungeon[i][j]);
                } else {
                    d[j] = Math.max(1, Math.min(d[j], d[j + 1]) - dungeon[j][i]);
                }
                
            }
        }
        
        return d[0];
        /*
    	int m = dungeon.length;
    	int n = dungeon[0].length;
    	
    	int min = Math.min(m, n);
    	int max = Math.max(m, n);
    	
    	int[] health = new int[min];
    	
    	int life;
    	
    	for (int i = max - 1; i >= 0; i--) {
    		for (int j = min - 1; j >= 0; j--) {
    			if (m == min) {
    				life = dungeon[j][i];
    			} else {
    				life = dungeon[i][j];
    			}
    			
    			if (j == min - 1 && i == max - 1) {
    				health[j] = Math.max(1 - life, 1);
    			} else if (j == min - 1) {
    				health[j] = Math.max(health[j] - life, 1);
    			} else if (i == max - 1) {
    				health[j] = Math.max(health[j+1] - life, 1);
    			} else {
    				health[j] = Math.max(Math.min(health[j], health[j+1]) - life, 1);
    			}
    			 		 
    		}
    	}
    	
    	return health[0];
    	*/
    }
    
    public static void main(String[] args) {
		int[][] test = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
		
		System.out.println(calculateMinimumHP(test));
	}
}
