package onsite;

public class ZEvaluateReversePolishNotation {
	
	public static int evalRPN(String[] input) {
		
	}

	public static void main(String[] args) {
		String[] test1 = {"2", "1", "+", "3", "*"};
		String[] test2 = {"4", "13", "5", "/", "+"};
		
		System.out.println(evalRPN(test1));
		System.out.println(evalRPN(test2));
	}
}
