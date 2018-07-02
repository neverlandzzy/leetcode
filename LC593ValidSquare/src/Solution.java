import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * Given the coordinates of four points in 2D space, return whether the four points could construct a square.
	 * 
	 * The coordinate (x,y) of a point is represented by an integer array with two integers.
	 * 
	 * Example:
	 * Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
	 * Output: True
	 * 
	 * Note:
	 * 	1. All the input integers are in the range [-10000, 10000].
	 * 	2. A valid square has four equal sides with positive length and four equal angles (90-degree angles).
	 * 	3. Input points have no order.
	 */
	
	// Solution 1: 计算每两个点之间的距离，只有每个距离不为0，且只有两个不同值（边和对角线）时，为true
	/*
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int[] dist = {getDist(p1, p2), getDist(p1, p3), getDist(p1, p4), getDist(p2, p3), getDist(p2, p4), getDist(p3, p4)};
        
        for (int d: dist) {
        	map.put(d, map.getOrDefault(d, 0) + 1);
        }
        
        return !map.containsKey(0) && map.size() == 2;
    }
    */
    
    // Solution 2: 计算每两个点之间的距离，排序后检查0~3 是否相同，4和5是否相同
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
    	int[] dist = {getDist(p1, p2), getDist(p1, p3), getDist(p1, p4), getDist(p2, p3), getDist(p2, p4), getDist(p3, p4)};
    	Arrays.sort(dist);
    	
    	return dist[0] == dist[3] && dist[4] == dist[5] && dist[4] > dist[0];
    }
    
    private static int getDist(int[] p1, int[] p2) {
    	return (p1[0] - p2[0]) * (p1[0] - p2[0]) +  (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
    
    
    
    public static void main(String[] args) {
    	int[]p1 = {0, 0};
    	int[]p2 = {1, 1};
    	int[]p3 = {1, 0};
    	int[]p4 = {0, 1};
    	
    	System.out.println(validSquare(p1, p2, p3, p4));
	}
}
