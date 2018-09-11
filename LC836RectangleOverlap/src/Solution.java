
public class Solution {
	/*
	 * A rectangle is represented as a list [x1, y1, x2, y2], where (x1, y1) are the coordinates of its bottom-left corner, and (x2, y2) 
	 * are the coordinates of its top-right corner.
	 * 
	 * Two rectangles overlap if the area of their intersection is positive.  To be clear, two rectangles that only touch at the corner or 
	 * edges do not overlap.
	 * 
	 * Given two (axis-aligned) rectangles, return whether they overlap.
	 * 
	 * Example 1:
	 * 
	 * Input: rec1 = [0,0,2,2], rec2 = [1,1,3,3]
	 * Output: true
	 * 
	 * Example 2:
	 * 
	 * Input: rec1 = [0,0,1,1], rec2 = [1,0,2,1]
	 * Output: false
	 * 
	 * Notes:
	 * 
	 * Both rectangles rec1 and rec2 are lists of 4 integers.
	 * All coordinates in rectangles will be between -10^9 and 10^9.
	 */
	
	// 排除四种相交的情况即可
    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
    	return !(rec1[0] >= rec2[2] || rec1[1] >= rec2[3] || rec1[2] <= rec2[0] || rec1[3] <= rec2[1]);
    }
    
    public static void main(String[] args) {
		int[] test11 = {0, 0, 2, 2};
		int[] test12 = {1, 1, 3, 3};
		System.out.println(isRectangleOverlap(test11, test12));
		
		int[] test21 = {0, 0, 1, 1};
		int[] test22 = {1, 0, 2, 1};
		System.out.println(isRectangleOverlap(test21, test22));
	}
}
