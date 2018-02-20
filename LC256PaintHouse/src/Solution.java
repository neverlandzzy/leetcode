
public class Solution {
	/*
	 * There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
	 * The cost of painting each house with a certain color is different. You have to paint all the houses such that no two 
	 * adjacent houses have the same color.
	 * 
	 * The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is 
	 * the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... 
	 * Find the minimum cost to paint all houses.
	 * 
	 * Note:
	 * All costs are positive integers.
	 */
	
    public static int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) {
            return 0;
        } 
        
        int cost0 = costs[0][0]; // 当前房子涂0的时候，最小开销
        int cost1 = costs[0][1]; // 当前房子涂1的时候，最小开销
        int cost2 = costs[0][2]; // 当前房子涂2的时候，最小开销
        
        for (int i = 1; i < costs.length; i++) {

            int tmp0 = Math.min(cost2, cost1) + costs[i][0]; 
            int tmp1 = Math.min(cost0, cost2) + costs[i][1]; 
            int tmp2 = Math.min(cost0, cost1) + costs[i][2]; 
            
            cost0 = tmp0;
            cost1 = tmp1;
            cost2 = tmp2;
    	
        }
        
        return Math.min(Math.min(cost0, cost1), cost2);
    }
    
    public static void main(String[] args) {
		int[][] test = {{2, 4, 3}, {1, 2, 5}, {5, 6, 2}, {1, 1, 2}};
		
		System.out.println(minCost(test));
	}
}
