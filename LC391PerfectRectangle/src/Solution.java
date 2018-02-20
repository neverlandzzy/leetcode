import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Given N axis-aligned rectangles where N > 0, determine if they all together form an exact cover of a rectangular region.
	 * 
	 * Each rectangle is represented as a bottom-left point and a top-right point. For example, a unit square is represented as [1,1,2,2]. 
	 * (coordinate of bottom-left point is (1, 1) and top-right point is (2, 2)).
	 * 
	 * Example 1:
	 * 
	 * rectangles = [
	 *   [1,1,3,3],
	 *   [3,1,4,2],
	 *   [3,2,4,4],
	 *   [1,3,2,4],
	 *   [2,3,3,4]
	 * ]
	 * 
	 * Return true. All 5 rectangles together form an exact cover of a rectangular region.
	 * 
	 * Example 2:
	 * 
	 * rectangles = [
	 *   [1,1,2,3],
	 *   [1,3,2,4],
	 *   [3,1,4,2],
	 *   [3,2,4,4]
	 * ]
	 * 
	 * Return false. Because there is a gap between the two rectangular regions.
	 * 
	 * Example 3:
	 * 
	 * rectangles = [
	 *   [1,1,3,3],
	 *   [3,1,4,2],
	 *   [1,3,2,4],
	 *   [3,2,4,4]
	 * ]
	 * 
	 * Return false. Because there is a gap in the top center.
	 * 
	 * Example 4:
	 * 
	 * rectangles = [
	 *   [1,1,3,3],
	 *   [3,1,4,2],
	 *   [1,3,2,4],
	 *   [2,2,4,4]
	 * ]
	 * Return false. Because two of the rectangles overlap with each other.
	 */
	
	// https://discuss.leetcode.com/topic/56052/really-easy-understanding-solution-o-n-java
	// 同时满足两个条件：
	// 1. 所有的点（每个小矩形的四个顶点）都是两两配对出现，除了四个点（大矩形最外的四个点，每个只出现一次）
	// 2. 根据1找到的最外四个点，算出大矩形的面积，这个面积应该等于所有小矩形面积之和
	
    public static boolean isRectangleCover(int[][] rectangles) {
    	
    	/* 本题不需要， 因为N > 0.
    	if (rectangles == null || rectangles.length == 0 || rectangles[0] == null || rectangles[0].length == 0) {
    		return false;
    	}
    	*/
    	// x1, x2, y1, y2 分别为大矩形的左、右、下、上四条边的坐标
        int x1 = Integer.MAX_VALUE;
        int x2 = Integer.MIN_VALUE;
        int y1 = Integer.MAX_VALUE;
        int y2 = Integer.MIN_VALUE;
        Set<String> set = new HashSet<>();
        
        int area = 0;
        
        for (int[] rec: rectangles) {
        	x1 = Math.min(x1, rec[0]);
        	x2 = Math.max(x2, rec[2]);
        	y1 = Math.min(y1, rec[1]);
        	y2 = Math.max(y2, rec[3]);
        	
        	area += (rec[2] - rec[0]) * (rec[3] - rec[1]);
        	
        	// p1, p2, p3, p4分别是当前小矩形的四个顶点
        	String p1 = rec[0] + ", " + rec[1];
        	String p2 = rec[0] + ", " + rec[3];
        	String p3 = rec[2] + ", " + rec[1];
        	String p4 = rec[2] + ", " + rec[3];
        	
        	if (!set.add(p1)) set.remove(p1);
        	if (!set.add(p2)) set.remove(p2);
        	if (!set.add(p3)) set.remove(p3);
        	if (!set.add(p4)) set.remove(p4);       		
      
        }
        
        if (set.size() != 4 || !set.contains(x1 + ", " + y1) || !set.contains(x1 + ", " + y2) || !set.contains(x2 + ", " + y1) || !set.contains(x2 + ", " + y2)) {
        	return false;
        }
        
        return area == (y2 - y1) * (x2 - x1);
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
		int[][] test2 = {{1, 1, 2, 3}, {1, 3, 2, 4}, {3, 1, 4, 2}, {3, 2, 4, 4}};
		int[][] test3 = {{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {3, 2, 4, 4}};
		int[][] test4 = {{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {2, 2, 4, 4}};
		
		System.out.println(isRectangleCover(test1));
		System.out.println(isRectangleCover(test2));
		System.out.println(isRectangleCover(test3));
		System.out.println(isRectangleCover(test4));
	}

}
