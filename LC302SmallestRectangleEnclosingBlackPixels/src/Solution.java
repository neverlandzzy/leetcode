
public class Solution {
	/**
	 * An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. 
	 * The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. 
	 * Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that 
	 * encloses all black pixels.
	 * 
	 * For example, given the following image:
	 * 
	 * [
	 *   "0010",
	 *   "0110",
	 *   "0100"
	 * ]
	 * and x = 0, y = 2,
	 * Return 6.
	 */
	
	// Solution 1: Binary search: O(m * log n + n * log m), 最优，不好想
	/*
    public static int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
        	return 0;
        }
        
        int m = image.length;
        int n = image[0].length;
        
        int left = searchColumns(image, 0, y, 0, m, true);
        int right = searchColumns(image, y + 1, n, 0, m, false);
        int top = searchRows(image, 0, x, 0, n, true);
        int bottom = searchRows(image, x + 1, m, 0, n, false);
        
        return (right - left) * (bottom - top);
    }
    
    private static int searchColumns(char[][] image, int i, int j, int top, int bottom, boolean whiteToBlack) {
    	while (i != j) {
    		int k = top;
    		int mid = i + (j - i) / 2;
    		
    		// 对于纵坐标为mid的col，用k从top到bottom扫描是否有black pixel(image[k][mid] == '1')
    		// 若扫到底，k == bottom，则说明此col没有black pixel:
    		//    若此时查找的是第一个black pixel(whiteToBlack == true)  则i = mid + 1
    		//    若此时查找的是第一个white pixel(whiteToBlack == false) 则j = mid
    		// 若k < bottom时就跳出while loop，则说明此col有black pixel:
    		//    若此时查找的是第一个black pixel(whiteToBlack == true)  则j = mid
    		//    若此时查找的是第一个white pixel(whiteToBlack == false) 则i = mid + 1
    		while (k < bottom && image[k][mid] == '0') {
    			k++;
    		}
    		
    		if (k < bottom == whiteToBlack) {
    			j = mid;
    		} else {
    			i = mid + 1;
    		}
    	}
    	
    	return i;
    }
    
    private static int searchRows(char[][] image, int i, int j, int left, int right, boolean whiteToBlack) {
    	while (i != j) {
    		int k = left;
    		int mid = i + (j - i)  / 2;
    		
    		while (k < right && image[mid][k] == '0') {
    			k++;
    		}
    		
    		if (k < right == whiteToBlack) {
    			j = mid;
    		} else {
    			i = mid + 1;
    		}
    	}
    	
    	return i;
    }
    */
	
	// Solution 2: DFS: O(mn) 不是最优，但容易想到
	private static int left, right, top, bottom;
	
	public static int minArea(char[][] image, int x, int y) {
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
        	return 0;
        }
        left = right = y;
        top = bottom = x;
        
        helper(image, x, y);
        
        return (right - left) * (bottom - top);
	}
	
	private static void helper(char[][] image, int x, int y) {
		if (x < 0 || x >= image.length || y < 0 || y >= image[0].length || image[x][y] == '0') {
			return;
		}
		
		// 用image本身记录visited, 遍历过的black pixel置为0即可。
		// 因为只有一个入口，只遍历一次，所以不需要重置
		image[x][y] = '0';
		left = Math.min(left, y);
		right = Math.max(right, y + 1);
		top = Math.min(top, x);
		bottom = Math.max(bottom, x + 1);
		
		helper(image, x + 1, y);
		helper(image, x - 1, y);
		helper(image, x, y + 1);
		helper(image, x, y - 1);
	}
	
    public static void main(String[] args) {
		char[][] test = {{'0', '0', '1', '0'}, {'0', '1', '1', '0'}, {'0', '1', '0', '0'}};
		
		System.out.println(minArea(test, 0, 2));
	}
}
