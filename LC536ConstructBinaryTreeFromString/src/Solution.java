import java.util.Stack;


public class Solution {
	/*
	 * You need to construct a binary tree from a string consisting of parenthesis and integers.
	 * 
	 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis. 
	 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
	 * 
	 * You always start to construct the left child node of the parent first if it exists.
	 * 
	 * Example:
	 * 
	 * Input: "4(2(3)(1))(6(5))"
	 * Output: return the tree root node representing the following tree:
     *      4
     *    /   \
     *   2     6
     *  / \   / 
     * 3   1 5   
     * 
     * Note:
     * There will only be '(', ')', '-' and '0' ~ '9' in the input string.
     * An empty tree is represented by "" instead of "()".
	 */
	
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        int sign = 1;
        int num = 0;
        boolean digit = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10  + (c - '0');
                digit = true;
            } else {
                if (digit) {
                    num = num * sign;
                    stack.push(new TreeNode(num));
                    digit = false;
                    sign = 1;
                    num = 0;
                }
                
                if (c == '-') {
                    sign = -1;
                }
                
                if (c == ')') {
                    TreeNode cur = stack.pop();
                    if (stack.peek().left == null) {
                        stack.peek().left = cur;
                    } else{
                        stack.peek().right = cur;
                    }       
                }
            }
        }
        
        return stack.isEmpty() ? new TreeNode(num * sign) : stack.pop();
    }
    
    public static void main(String[] args) {
		String test = "zzfefe";
		
		//System.out.println(test.indexOf("r"));
	}
}
