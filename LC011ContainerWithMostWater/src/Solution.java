
public class Solution {
	/**
	 * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). 
	 * n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). 
	 * Find two lines, which together with x-axis forms a container, such that the container contains 
	 * the most water.
	 * 
	 * Note: You may not slant the container.
	 */
	
    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        
        int max = 0;
        
        while (i < j) {
            if (height[i] < height[j]) {
                max = Math.max(max, (j - i) * height[i]);
                i++;
            } else {
                max = Math.max(max, (j - i) * height[j]);
                j--;
            }
        }
        
        return max;
    }
    
    /*
    public static int maxArea(int[] height) {
    	
    	int area = 0;
    	int tmp;
        
    	for(int i = 0, j = height.length - 1; i < j; ) {
    		tmp = Math.min(height[i], height[j]) * ( j - i);
    		if (area <  tmp) {
    			area = tmp;
    		}
    		
    		if (height[i] >= height[j]) {
    			j--;
    		} else {
    			i++;
    		}
    	
    	}
    	return area;	
    	
    	
    }
    */
    public static void main(String[] args) {
    	int[] test = {10,5,6,24,1,0,21};
    	
    	System.out.println("maxArea = "+ maxArea(test));
    }
}
