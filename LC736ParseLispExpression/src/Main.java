
public class Main {
    
    public static void main(String[] args) {
    	
    	Solution solution = new Solution();
    	
		System.out.println(solution.evaluate("(add 1 2)"));
		System.out.println(solution.evaluate("(mult 3 (add 2 3))"));
		System.out.println(solution.evaluate("(let x 2 (mult x 5))"));
		System.out.println(solution.evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))"));
		System.out.println(solution.evaluate("(let x 3 x 2 x)"));
		System.out.println(solution.evaluate("(let x 1 y 2 x (add x y) (add x y))"));
	}
}
