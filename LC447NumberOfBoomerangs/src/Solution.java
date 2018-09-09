import java.util.HashMap;
import java.util.Map;


public class Solution {
	/*
	 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between 
	 * i and j equals the distance between i and k (the order of the tuple matters).
	 * 
	 * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] (inclusive).
	 * 
	 * Example:
	 * Input:
	 * [[0,0],[1,0],[2,0]]
	 * 
	 * Output:
	 * 2
	 * 
	 * Explanation:
	 * The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
	 */
	
    public static int numberOfBoomerangs(int[][] points) {       
        int result = 0;
        
        for (int i = 0; i < points.length; i++) {
        	Map<Integer, Integer> map = new HashMap<>();
        	for (int j = 0; j < points.length; j++) {
        		if (i == j) {
        			continue;
        		}
        		int dist = getDistance(points[i], points[j]);
        		map.put(dist, map.getOrDefault(dist, 0) + 1);
        	}
        	
        	for (int val: map.values()) {
        		result += val * (val - 1);
        	}
        }
        
        return result;
    }
    
    private static int getDistance(int[] p1, int[] p2) {
    	return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{0, 0}, {1, 0}, {2, 0}};
		System.out.println(numberOfBoomerangs(test1));
		
		int[][] test2 = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		System.out.println(numberOfBoomerangs(test2));
	}
}
