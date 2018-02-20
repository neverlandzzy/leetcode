
public class Solution {
	public static void main(String[] args) {
		MinStack minStack = new MinStack();
		
		minStack.push(0);
		minStack.push(1);
		minStack.push(0);
		//minStack.push(6);
		
		
		System.out.println(minStack.top());
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.getMin());
		minStack.pop();
		System.out.println(minStack.getMin());
		
	}
}
