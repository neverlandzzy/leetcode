
public class Solution {
	/*
	 * There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain 
	 * color is different. You have to paint all the houses such that no two adjacent houses have the same color.
	 * 
	 * The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost 
	 * of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to 
	 * paint all houses.
	 * 
	 * Note:
	 * All costs are positive integers.
	 * 
	 * Follow up:
	 * Could you solve it in O(nk) runtime?
	 */
	
	// https://discuss.leetcode.com/topic/30659/easiest-o-1-space-java-solution
	// http://www.meetqun.net/thread-10660-1-1.html
	
    public static int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) {
            return 0;
        } 

        int min1 = 0;
        int min2 = 0;
        int index = -1;
        
        for (int i = 0; i < costs.length; i++) {
        	int currentMin1 = Integer.MAX_VALUE;
        	int currentMin2 = Integer.MAX_VALUE;
        	int pos = -1;
        	
        	for (int j = 0; j < costs[0].length; j++) {
        		// 每次扫描i的时候，保留最小值和次小值
        		// 每次扫描j的时候，当j和上一轮的最小值index相同时，用次小值来比。若这个值为扫描本轮i的最小值，则更新index。
        		int cost = costs[i][j] + ((j == index) ? min2 : min1);
        		
        		if (cost < currentMin1) {
        			currentMin2 = currentMin1;
        			currentMin1 = cost;
        			pos = j;
        		} else if (cost < currentMin2){
        			currentMin2 = cost;
        		}
        	}
        	
        	min1 = currentMin1;
        	min2 = currentMin2;
        	index = pos;
        }
        
        return min1;
        
    }
    
    public static void main(String[] args) {
    	int[][] test = {{2, 4, 3, 6}, {1, 2, 5, 3}, {5, 6, 2, 7}, {1, 1, 2, 9}};
    	
    	System.out.println(minCostII(test));
	}
}
