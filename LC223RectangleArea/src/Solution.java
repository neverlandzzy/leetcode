
public class Solution {
	/*
	 * Find the total area covered by two rectilinear rectangles in a 2D plane.
	 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
	 * 
	 * Assume that the total area is never beyond the maximum possible value of int.
	 */
	
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int left = Math.max(A, E);
        int right = Math.min(C, G);
        int bottom = Math.max(B, F);
        int top = Math.min(D, H);
        
        int overlap = 0;
        int ares = (D-B) * (C-A) + (H-F) * (G-E);
        
        if ((right > left) && (bottom < top)) {
        	overlap = (top - bottom) * (right - left);
        }

        return ares - overlap;
    }
    
    public static void main(String[] args) {
		System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
		
	}
}
