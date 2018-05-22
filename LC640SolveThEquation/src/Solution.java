public class Solution {
	
	/*
	 * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, 
	 * the variable x and its coefficient.
	 * 
	 * If there is no solution for the equation, return "No solution".
	 * 
	 * If there are infinite solutions for the equation, return "Infinite solutions".
	 * 
	 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
	 * 
	 * Example 1:
	 * Input: "x+5-3+x=6+x-2"
	 * Output: "x=2"
	 * 
	 * Example 2:
	 * Input: "x=x"
	 * Output: "Infinite solutions"
	 * 
	 * Example 3:
	 * Input: "2x=x"
	 * Output: "x=0"
	 * 
	 * Example 4:
	 * Input: "2x+3x-6x=x+2"
	 * Output: "x=-1"
	 * 
	 * Example 5:
	 * Input: "x=x+2"
	 * Output: "No solution"
	 */
    public static String solveEquation(String equation) {
        int index = 0;
        int sign = 1;
        int num = 0;
        int val = 0;
        
        for (int i = 0; i < equation.length(); i++) {
        	char c = equation.charAt(i);
        	if (c == '+' || c == '-') {
        		if (num != 0) {
        			val += sign * num;
        		}
        		sign = c == '+' ? 1 : -1;
        		num = 0;
        	} else if (c == 'x') {
        		// for corner case: 0x = 0;
                if (i > 0 && equation.charAt(i - 1) == '0') {
                    index += 0;
                    continue;
                }
        		index += num == 0 ? sign : sign * num; 
        		num = 0;
        	} else if (c >= '0' && c <= '9') {
        		num = num * 10 +  (c - '0');
        	} else if (c == '=') {
        		val += sign * num;
        		val = -val;
        		index = -index;
        		sign = 1;
        		num = 0;
        	}   
        	
        	//System.out.println("index = " + index + " val = " + val + " c = " + c + " num = " + num);
        }
        
		if (num != 0) {
			val += sign * num;
		}
		
		if (index == 0) {
			if (val == 0) {
				return "Infinite solutions";
			} else {
				return "No solution";
			}
		}
        
        return "x=" + String.valueOf(-val / index);
    }
    
    public static void main(String[] args) {
    	

		System.out.println(solveEquation("x+5-3+x=6+x-2"));
		System.out.println(solveEquation("x=x"));
		System.out.println(solveEquation("2x=x"));
		System.out.println(solveEquation("2x+3x-6x=x+2"));
		System.out.println(solveEquation("x=x+2"));
		System.out.println(solveEquation("1+1=x"));
		System.out.println(solveEquation("3x=33+22+11"));
		System.out.println(solveEquation("x-10=10-x+x+2x"));
    	System.out.println(solveEquation("0x=0"));
		
    	

	}
}
