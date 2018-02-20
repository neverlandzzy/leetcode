
public class Solution {
	public static void main(String[] args) {
		 MaxStack maxStack = new MaxStack();
		 maxStack.push(5);
		 maxStack.push(1);
		 maxStack.push(-5);

		 //System.out.println(maxStack.top());
		 System.out.println(maxStack.popMax());
		 System.out.println(maxStack.popMax());
		 System.out.println(maxStack.top());
		 //System.out.println(maxStack.peekMax());
		 //System.out.println(maxStack.pop());
		 //System.out.println(maxStack.top());
	}
}
