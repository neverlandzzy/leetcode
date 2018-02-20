import java.util.Stack;


public class Solution {
	/*
	 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle 
	 * containing all ones and return its area.
	 */
	
	// O(m * n)
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int maxArea = 0;
        int[] heights = new int[n];
        
        for (int i = 0; i < m; i++) {         
            for (int j = 0; j < n; j++) {
                heights[j] = (matrix[i][j] == '1' ? heights[j] + 1 : 0);
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        
        return maxArea;
    }
    
    private static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        
        int maxArea = 0;
        
        for (int i = 0; i < heights.length; i++) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i]) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            stack.push(i);
        }
        
        while (stack.peek() != -1) {
            maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
        }
        
        return maxArea;                       
    }
	/*
    public static int maximalRectangle(char[][] matrix) {
    	
    	int m = matrix.length;

    	
    	if( m == 0 ) {
    		return 0;
    	}
    	
    	int n = matrix[0].length;

    	int maxArea = 0;
    	
    	int[] rect = new int[n];
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			rect[j] = (matrix[i][j] == '0') ? 0 : rect[j]+1;
    		}
    		
    		maxArea = Math.max(largestRectangleArea(rect), maxArea);
    	}
    	
    	return maxArea;
    	
    }
    
    public static int largestRectangleArea(int[] height) {
    	Stack<Integer> stack = new Stack<Integer>();
    	
    	int maxArea = 0;
    	int i = 0;
    	
    	while (i <= height.length) {
    		if (stack.isEmpty() || height[stack.peek()] <= ((i == height.length) ? 0: height[i])) {
    			stack.push(i);
    			i++;
    		} else {
    			int t = stack.pop();
    			if (stack.isEmpty()) {
    				maxArea = Math.max(maxArea, height[t] * i);
    			} else {
    				maxArea = Math.max(maxArea, height[t] * (i - stack.peek() - 1));
    			}
    		}
    	}
    	
    	return maxArea;
    }
    */
    public static void main(String[] args) {
    	char[][] test = {{'1','1','0','1','0','1'}, {'0','1','0','0','1','1'}, {'1','1','1','1','0','1'}, {'1','1','1','1','0','1'}};
    	
    	System.out.println(maximalRectangle(test));
    	
    	char[][] test2 = {{}, {}};
    	
    	System.out.println(maximalRectangle(test2));
	}
}
