import java.util.Arrays;


public class Solution {
	/*
	 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
	 * 
	 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses 
	 * could be covered by those heaters.
	 * 
	 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard 
	 * of heaters.
	 * 
	 * Note:
	 * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
	 * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
	 * As long as a house is in the heaters' warm radius range, it can be warmed.
	 * All the heaters follow your radius standard and the warm radius will the same.
	 * 
	 * Example 1:
	 * Input: [1,2,3],[2]
	 * Output: 1
	 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
	 * 
	 * Example 2:
	 * Input: [1,2,3,4],[1,4]
	 * Output: 1
	 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
	 */
	
    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        
        int radius = 0;
        
        for (int n: houses) {
        	int index = Arrays.binarySearch(heaters, n);
        	
        	if (index < 0) {
        		index = -index - 1;
        		int distance1 = (index == 0 ? Integer.MAX_VALUE : n - heaters[index - 1]);
        		int distance2 = (index == heaters.length ? Integer.MAX_VALUE : heaters[index] - n);
        		radius = Math.max(radius, Math.min(distance1, distance2));
        	}
        }
        
        return radius;
    }
    
    public static void main(String[] args) {
		int[] house1 = {1, 2, 3};
		int[] house2 = {1, 2, 3, 4};
		
		int[] heater1 = {2};
		int[] heater2 = {1, 4};
		
		System.out.println(findRadius(house1, heater1));
		System.out.println(findRadius(house2, heater2));
	}
}
