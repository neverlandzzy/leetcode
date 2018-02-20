
public class Solution {
	
	public static void main(String[] args) {
		MyStack myStack = new MyStack();
		
		myStack.push(1);
		myStack.push(2);
		myStack.push(3);
		myStack.push(4);
		
		
		System.out.println(myStack.top());
		myStack.pop();
		System.out.println(myStack.top());
		myStack.pop();
		System.out.println(myStack.top());
	}

}
