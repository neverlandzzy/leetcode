import java.util.Stack;


public class MinStack {
	/*
	 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
	 * 
	 * push(x) -- Push element x onto stack.
	 * pop() -- Removes the element on top of the stack.
	 * top() -- Get the top element.
	 * getMin() -- Retrieve the minimum element in the stack.
	 */
		
    Stack<Integer> stack;
    Stack<Integer> minStack;
    
    
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    public void push(int x) {
        int min;
        if (minStack.isEmpty()) {
            min = x;
        } else {
            min = Math.min(x, minStack.peek());
        }
        stack.push(x);
        minStack.push(min);
    }
    
    public void pop() {
        stack.pop();
        minStack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
