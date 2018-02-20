import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Solution {
	
	// 1.
    public static int calPoints(String[] ops) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < ops.length; i++) {
        	String s = ops[i];
        	
        	if (s.equals("C")) {
        		int lastPoint = stack.pop();
        		sum -= lastPoint;
        		
        	} else if (s.equals("D")) {
        		int point = stack.peek();
        		point *= 2;
        		stack.push(point);
        		sum += point;
        	} else if (s.equals("+")) {
        		int val1 = stack.pop();
        		int val2 = stack.pop();
        		int point = val1 + val2;
        		
        		sum += point;
        		stack.push(val2);
        		stack.push(val1);
        		stack.push(point);
        	} else {
        		int point = Integer.parseInt(s);
        		sum += point;
        		stack.push(point);
        	}
        }
        
        return sum;
    }
    
    // 2. 
    public static String nextClosestTime(String time) {
        String[] times = time.split(":");
        int hour = Integer.valueOf(times[0]);
        int min = Integer.valueOf(times[1]);
        
        int[] digits = new int[4];
        
        digits[0] = Character.getNumericValue(times[0].charAt(0));
        digits[1] = Character.getNumericValue(times[0].charAt(1));
        digits[2] = Character.getNumericValue(times[1].charAt(0));
        digits[3] = Character.getNumericValue(times[1].charAt(1));
        
        List<String> hours = new ArrayList<>();
        List<String> mins = new ArrayList<>();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        helper(digits, sb1, hours, 24);
        helper(digits, sb2, mins, 59);
        
        String resH = null;
        String resM = null;
        String resH2 = null;
        String resM2 = null;
        
        int minDiff1 = Integer.MAX_VALUE;
        int minDiff2 = Integer.MAX_VALUE;
        
        for (String hs: hours) {
        	for (String ms: mins) {
        		int diff = different(hour, Integer.parseInt(hs), min, Integer.parseInt(ms));
        		if (diff <= 0) {
        			if (diff < minDiff2) {
	        			resH2 = hs;
	        			resM2 = hs;
	        			minDiff2 = diff;
        			}
        		} else {        		
	        		if (diff < minDiff1) {
	        			resH = hs;
	        			resM = ms;
	        			minDiff1 = diff;
	        		}
        		}
        	}
        }
        
        if (resH != null) {
        	return resH + ":" + resM;
        } else {
        	return resH2 + ":" + resM2;
        }
    }
    
    private static void helper(int[] digits, StringBuilder sb, List<String> times, int limit) {
    	if (sb.length() == 2) {
    		if (Integer.valueOf(sb.toString()) <= limit) {
    			times.add(sb.toString());
    		}
    		return;
    	}
    	
    	for (int i = 0; i < digits.length; i++) {
    		sb.append(digits[i]);
    		helper(digits, sb, times, limit);
    		sb.deleteCharAt(sb.length() - 1);
    	}
    }
    
    private static int different(int h1, int h2, int m1, int m2) {
    	
    	return (m2 - m1) + 60 * (h2 - h1);
    }
    
    // 3.
    public int[] findRedundantConnection(int[][] edges) {
        
    }
    public static void main(String[] args) {
    	String[] test11 = {"5","2","C","D","+"};
    	String[] test12 = {"5","-2","4","C","D","9","+","+"};
    	String[] test13 = {};

    	//System.out.println(calPoints(test11));
    	//System.out.println(calPoints(test12));
    	//System.out.println(calPoints(test13));
    	
    	String test21 = "19:04";
    	String test22 = "23:59";
    	
    	System.out.println(nextClosestTime(test21));
    	System.out.println(nextClosestTime(test22));
	}
}
