import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/*
	 * There is a brick wall in front of you. The wall is rectangular and has several rows of bricks. The bricks have the same height but 
	 * different width. You want to draw a vertical line from the top to the bottom and cross the least bricks.
	 * 
	 * The brick wall is represented by a list of rows. Each row is a list of integers representing the width of each brick in this row from 
	 * left to right.
	 * 
	 * If your line go through the edge of a brick, then the brick is not considered as crossed. You need to find out how to draw the line to 
	 * cross the least bricks and return the number of crossed bricks.
	 * 
	 * You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
	 * 
	 * Example:
	 * Input: 
	 * [[1,2,2,1],
	 *  [3,1,2],
	 *  [1,3,2],
	 *  [2,4],
	 *  [3,1,2],
	 *  [1,3,1,1]]
	 * Output: 2
	 * Explanation: 
	 * 
	 * Note:
	 * 1. The width sum of bricks in different rows are the same and won't exceed INT_MAX.
	 * 2. The number of bricks in each row is in range [1,10,000]. The height of wall is in range [1,10,000]. Total number of bricks of the 
	 * wall won't exceed 20,000.
	 */
	
	// https://leetcode.com/problems/brick-wall/solution/
	// 用HashMap记录在每一行砖宽的累积和(key)，当某两行有相同累积和时，说明此处有个缝隙，可以不用穿过砖头劈开墙。因此累积和的最大值，即为缝隙最多的地方。
	
    public static int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        
        for (List<Integer> list: wall) {
            int sum = 0;
            for (int i = 0; i < list.size() - 1; i++) {
                sum += list.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                max = Math.max(max, map.get(sum));
            }
        }
        
        return wall.size() - max;
    }
    /*
    public static int leastBricks(List<List<Integer>> wall) {
    	Map<Integer, Integer> map = new HashMap<>();
    	
    	for (List<Integer> row: wall) {
    		int sum = 0;
    		for (int i = 0; i < row.size() - 1; i++) {
    			sum += row.get(i);
    			map.put(sum, map.getOrDefault(sum, 0) + 1);
    		}
    	}
    	
    	int result = wall.size();
    	
    	for (int key: map.keySet()) {
    		result = Math.min(result, wall.size() - map.get(key));
    	}
    		
    	return result;
    }
    */
    public static void main(String[] args) {
		List<List<Integer>> test1 = new ArrayList<>(Arrays.asList(Arrays.asList(1, 2, 2, 1), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 2),
																  Arrays.asList(2, 4), Arrays.asList(3, 1, 2), Arrays.asList(1, 3, 1, 1)));
		System.out.println(leastBricks(test1));
	}
}
