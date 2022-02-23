
public class Solution {
	/**
	 * Given n non-negative integers representing an elevation map where the width of each bar is 1, 
	 * compute how much water it is able to trap after raining.
	 * 
	 * For example, 
	 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
	 */
	
    public static int trap(int[] height) {
    	
    	// 九章强化第三课
        if (height == null || height.length == 0) {
            return 0;
        }
        
        int left = 0;
        int right = height.length - 1;
        int leftHeight = height[left];
        int rightHeight = height[right];
        
        int result = 0;
        
        while (left + 1 < right) {
            if (leftHeight < rightHeight) {
                left++;
                if (leftHeight > height[left]) {
                    result += leftHeight - height[left];
                } else {
                    leftHeight = height[left];
                }
                
            } else {
                right--;
                if (rightHeight > height[right]) {
                    result += rightHeight - height[right];
                } else {
                    rightHeight = height[right];
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] test = {0,1,0,2,1,0,1,3,2,1,2,1};
	
		System.out.println(trap(test));
	}
}
