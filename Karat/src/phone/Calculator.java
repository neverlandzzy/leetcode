package phone;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Calculator {
	/*
	 * 【基本题】basic calculator，没有括号只有加减的。 給你一個string例如"2+3-999"回傳計算結果int。
	 * 【Follow up 1】加上括号，例如"2+((8+2)+(3-999))"一樣回傳計算結果  -- 类似LC224 不過input的字串不會有空白或是特殊符號, 可以省一些處理
	 * 【Follow up 2】在【Follow up 1】基础上加了变量名。给你一个map比如{'a':1, 'b':2, 'c':3}，假设输入为"a+b+c+1"输出要是7，如果有未定义的变量，
	 *               比如"a+b+c+1+d"输出就是7+d
	 * 
	 */
	
	public static int calculator1(String s) {
		int result = 0;
		
		int sign = 1;
		int num = 0;
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '+') {
				result += sign * num;
				sign = 1;
				num = 0;
			} else if (c == '-') {
				result += sign * num;
				num = 0;
				sign = -1;
			} else if (c >= '0' && c <= '9') {
				num = num * 10 + (int)(c - '0');
			}
		}
		result += sign * num;
		
		return result;
	}
	
	//【Follow up 1】
	public static int calculator2(String s) {
		int sign = 1;
		int result = 0;
		int num = 0;
		
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(sign);
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '+' || c == '-') {
				result += sign * num;
				sign = stack.peek() * (c == '+' ? 1 : -1);
				num = 0;
			} else if (c >= '0' && c <= '9') {
				num = num * 10 + (int)(c - '0');
			} else if (c == '(') {
				stack.push(sign);
			} else if (c == ')') {
				stack.pop();
			}
		}
		
		result += sign * num;
		return result;
	}
	
	//【Follow up 2】
	// http://www.1point3acres.com/bbs/thread-305094-1-1.html
	public static String calculator3(String s, Map<String, Integer> map) {
		int sign = 1;
		int total = 0;
		int num = 0;
		StringBuilder sb = new StringBuilder();
		StringBuilder variable = new StringBuilder();
		StringBuilder result = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(sign);
		
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '+' || c == '-') {
				if (variable.length() != 0) {
					if (map.containsKey(variable.toString())) {
						total += sign * map.get(variable.toString());
					} else {
						sb.append(sign == 1 ? "+" : "-").append(variable);
					}
					variable.setLength(0);
				} else {
					total += sign * num;
					num = 0;
				}
				sign = stack.peek() * (c == '+' ? 1 : -1);
			} else if (c >= '0' && c <= '9') {
				num = num * 10 + (int)(c - '0');
			} else if (Character.isLetter(c)) {
				variable.append(c);
			} else if (c == '(') {
				stack.push(sign);
			} else if (c == ')') {
				stack.pop();
			}
		}
		
		if (variable.length() != 0) {
			if (map.containsKey(variable.toString())) {
				total += sign * map.get(variable.toString());
			} else {
				sb.append(sign == 1 ? "+" : "-").append(variable);
			}
		} else {
			total += sign * num;
		}
		
		result.append(total).append(sb);
		return result.toString();
	}
	
	public static void main(String[] args) {
		System.out.println("*********** 【基本题】***********");
		String test11 = "2+3-999";
		String test12 = "2 + 3    - 999";
		String test13 = "";
		String test14 = " 2-1 + 2 ";
		String test15 = " -2";
		String test16 = " -2 - 2 + 9";
		
		System.out.println(calculator1(test11));
		System.out.println(calculator1(test12));
		System.out.println(calculator1(test13));
		System.out.println(calculator1(test14));
		System.out.println(calculator1(test15));
		System.out.println(calculator1(test16));
		
		System.out.println("*********** 【Follow up 1】(LC224) ***********");
		String test21 = "2+((8+2)+(3-999))";
		String test22 = "2 + 3    - 999";
		String test23 = "";
		String test24 = " 2-1 + 2 ";
		String test25 = " -2";
		String test26 = " -( 1+ ( 4  + 5+2)- 3) + ( 6+8)";
		
		System.out.println(calculator2(test21));
		System.out.println(calculator2(test22));
		System.out.println(calculator2(test23));
		System.out.println(calculator2(test24));
		System.out.println(calculator2(test25));
		System.out.println(calculator2(test26));
		
		System.out.println("*********** 【Follow up 2】有变量 ***********");
		String test31 = "a + b + c + 1 - d";
		String test32 = "e - 8 + temperature - pressure";
		String test33 = "e - 8 - (temperature - pressure)";
		String test34 = "e - (8 + pressure) - temperature ";
		String test35 = "-( 1+ ( 4  + 5+2)- 3) + ( 6+8)";
		
		Map<String, Integer> map1 = new HashMap<>();
		Map<String, Integer> map2 = new HashMap<>();
		Map<String, Integer> map3 = new HashMap<>();
		Map<String, Integer> map4 = new HashMap<>();
		Map<String, Integer> map5 = new HashMap<>();
		
		map1.put("a", 1);
		map1.put("b", 2);
		map1.put("c", 3);
		
		map2.put("e", 1);
		map2.put("temperature", 12);
		
		map3.put("e", 1);
		map3.put("temperature", 12);
		
		map4.put("e", 1);
		map4.put("temperature", 12);
		
		map5.put("e", 1);
		map5.put("temperature", 12);
		
		System.out.println(calculator3(test31, map1));
		System.out.println(calculator3(test32, map2));
		System.out.println(calculator3(test33, map3));
		System.out.println(calculator3(test34, map4));
		System.out.println(calculator3(test35, map5));
	}
}
