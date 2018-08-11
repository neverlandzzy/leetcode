import java.util.LinkedList;
import java.util.Queue;


public class Solution {
	/*
	 * An image is represented by a 2-D array of integers, each integer representing the pixel value of the image (from 0 to 65535).
	 * 
	 * Given a coordinate (sr, sc) representing the starting pixel (row and column) of the flood fill, and a pixel value newColor, "flood fill" the image.
	 * 
	 * To perform a "flood fill", consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same 
	 * color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color as the starting pixel), 
	 * and so on. Replace the color of all of the aforementioned pixels with the newColor.
	 * 
	 * At the end, return the modified image.
	 * 
	 * Example 1:
	 * Input: 
	 * image = [[1,1,1],[1,1,0],[1,0,1]]
	 * sr = 1, sc = 1, newColor = 2
	 * Output: [[2,2,2],[2,2,0],[2,0,1]]
	 * 
	 * Explanation: 
	 * From the center of the image (with position (sr, sc) = (1, 1)), all pixels connected 
	 * by a path of the same color as the starting pixel are colored with the new color.
	 * Note the bottom corner is not colored 2, because it is not 4-directionally connected
	 * to the starting pixel.
	 * 
	 * Note:
	 * 
	 * 1. The length of image and image[0] will be in the range [1, 50].
	 * 2. The given starting pixel will satisfy 0 <= sr < image.length and 0 <= sc < image[0].length.
	 * 3. The value of each color in image[i][j] and newColor will be an integer in [0, 65535].
	 */
	
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
        	return image;
        }
        
        int[][] direction = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {sr, sc});
        int color = image[sr][sc];
        
        int m = image.length;
        int n = image[0].length;
        
        while (!queue.isEmpty()) {
        	int[] point = queue.poll();
        	int x = point[0];
        	int y = point[1];
        	image[x][y] = newColor;
        	
        	for (int[] dir: direction) {
        		int nextX = x + dir[0];
        		int nextY = y + dir[1];
        		
        		if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && image[nextX][nextY] == color) {
        			queue.offer(new int[]{nextX, nextY});
        		}
        		
        	}
        }
        
        return image;
    }
    
    public static void main(String[] args) {
		int[][] test1 = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
		int[][] result1 = floodFill(test1, 1, 1, 2);
		
		print(result1);
	}
    
    private static void print(int[][] image) {
    	for (int[] d: image) {
    		for (int i: d) {
    			System.out.print(i + ", ");
    		}
    		System.out.println();
    	}
    }
}
