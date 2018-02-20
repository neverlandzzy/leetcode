import java.util.LinkedList;
import java.util.Queue;


public class MyStack {
	
	/*
	 * Implement the following operations of a stack using queues.
	 * 
	 * push(x) -- Push element x onto stack.
	 * pop() -- Removes the element on top of the stack.
	 * top() -- Get the top element.
	 * empty() -- Return whether the stack is empty.
	 * 
	 * Notes:
	 * You must use only standard operations of a queue -- which means only push to back, 
	 * peek/pop from front, size, and is empty operations are valid.
	 * 
	 * Depending on your language, queue may not be supported natively. You may simulate a 
	 * queue by using a list or deque (double-ended queue), as long as you use only standard 
	 * operations of a queue.
	 * 
	 * You may assume that all operations are valid (for example, no pop or top operations will 
	 * be called on an empty stack).
	 */
	
	
    // Push element x onto stack.
	
	private Queue<Integer> queue;
	
	public MyStack() {
		queue = new LinkedList<Integer>();
	}
	
    public void push(int x) {
        queue.add(x);

        for (int i = 0; i < queue.size() - 1; i++) {
        	queue.add(queue.poll());
        }
    }

    // Removes the element on top of the stack.
    public void pop() {
    	queue.poll();
    }

    // Get the top element.
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return queue.isEmpty();
    }
}
