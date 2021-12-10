import java.util.Stack;


public class Solution {
	/**
	 * Given n non-negative integers representing the histogram's bar height where 
	 * the width of each bar is 1, find the area of largest rectangle in the histogram.
	 * 
	 * For example,
	 * Given height = [2,1,5,6,2,3],
	 * return 10.
	 */
    
	public static int largestRectangleArea(int[] heights) {
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
    public static int largestRectangleArea(int[] height) {
    	Stack<Integer> stack = new Stack<Integer>();
    	
    	int maxArea = 0;
    	int i = 0;
    	
    	while( i <= height.length) {
    		if (stack.isEmpty() || height[stack.peek()] <= (i == height.length ? 0 : height[i])) {
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
		int[] test = {3,2,5,6,1,3};
		
		System.out.println(largestRectangleArea(test));
	}
}
