
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
	
    public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        
    }
    
    public static void main(String[] args) {
    	int[]p1 = {0, 0};
    	int[]p2 = {1, 1};
    	int[]p3 = {1, 0};
    	int[]p4 = {0, 1};
    	
    	System.out.println(validSquare(p1, p2, p3, p4));
	}
}
