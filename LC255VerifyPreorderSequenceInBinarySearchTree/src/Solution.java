import java.util.Stack;


public class Solution {
	/*
	 * Given an array of numbers, verify whether it is the correct preorder 
	 * traversal sequence of a binary search tree.
	 * 
	 * You may assume each number in the sequence is unique.
	 * 
	 * Follow up:
	 * Could you do it using only constant space complexity?
	 */
	
    public static boolean verifyPreorder(int[] preorder) {
    	
    	// O(n) space
    	/*
    	int low = Integer.MIN_VALUE;
    	Stack<Integer> stack = new Stack<Integer>();
    	
    	for (int p: preorder) {
    		if (p < low) {
    			return false;
    		}
    		while (!stack.isEmpty() && p > stack.peek()) {
    			low = stack.pop();
    		}
    		
    		stack.push(p);
    	}
    	
    	return true;
    	*/
    	
    	// O(1) space
    	int low = Integer.MIN_VALUE;
    	int i = -1;
    	
    	for (int p: preorder) {
    		if (p < low) {
    			return false;
    		}
    		
    		while (i >= 0 && p > preorder[i]) {
    			System.out.println("i = " + i);
    			low = preorder[i--];
    			System.out.println("low = " + low);
    			
    		}
    		preorder[++i] = p;
    		/*
    		System.out.println("i = " + i);
    		for (int k: preorder) {
    			System.out.print(k + ", ");
    		}
    		System.out.println();
    		*/
    	}
    	
    	return true;
    }
    
    public static void main(String[] args) {
    	
    	int[] test1 = {10, 7, 3, 8, 18, 16};  // true
    	int[] test2 = {3, 9, 20, 15, 7}; //false
    	
    	System.out.println(verifyPreorder(test1));
    	//System.out.println(verifyPreorder(test2));
	}
}
