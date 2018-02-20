import java.util.Comparator;
import java.util.PriorityQueue;


public class Solution {

	
    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
        	return temperatures;
        }
        
        int n = temperatures.length;
        int[] result = new int[n];
        
        for (int i = n - 1; i >= 0; i--) {
        	int counter = 1;
        	for (int j = i + 1; j < n; j++) {
        		if (temperatures[j] <= temperatures[i]) {
        			counter++;
        		} else {
        			result[i] = counter;
        			break;
        		}
        	}
        }
        
        return result;
    }
    
    // Brute-Force -- TLE
   /*
    public static int monotoneIncreasingDigits(int N) {
    	for (int i = N; i >= 0; i--) {
    		if (valid(N)) {
    			return N;
    		} else {
    			N--;
    		}
    	}
    	return -1;
    }
    
    private static boolean valid(int N) {    	
    	int min = Integer.MAX_VALUE;
    	
    	while (N > 0) {
    		int digit = N % 10;
    		if (min >= digit) {
    			min = digit;
    			N /= 10;
    		} else {
    			return false;
    		}
    	}
    	
    	return true;
    }
    */
    public static int monotoneIncreasingDigits(int N) {
    	if (N < 10) {
    		return N;
    	}
    	
    	String s = String.valueOf(N);
    	int n = s.length();
    	int index = -1;
    	
    	for (int i = n - 2; i >= 0; i--) {
    		if (s.charAt(i) > s.charAt(i + 1) || (index != -1 && s.charAt(index) == s.charAt(i))) {
    			index = i;
    		}
    	}
    	
    	return index == -1 ? N : N - Integer.parseInt(s.substring(index + 1)) - 1;
    }
    
    public static int deleteAndEarn(int[] nums) {
    	
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	
    	int max = 0;
        int[] map = new int[10001];
        
        for (int i = 0; i < nums.length; i++) {
        	map[nums[i]]++;
        }
       
        for (int i = 0; i < nums.length; i++) {
        	if (i > 0 && nums[i] == nums[i - 1]) {
        		continue;
        	}
        	
        	max = Math.max(max, helper(map, nums[i]));
        }
        
        return max;
    }
    
    private static int helper(int[] map, int cur) {
    	if (map[cur] == 0) {
    		return 0;
    	}
    	
    	int result = cur;
    	map[cur]--;
    	
    	
    	result += helper(map, cur + 1);
    	result += helper(map, cur - 1);
    	
    	map[cur]++;
    	return result;
    }
    
	public static void main(String[] args) {
		/*
		int[] test11 = {73, 74, 75, 71, 69, 72, 76, 73};
	    int[] result11 = dailyTemperatures(test11);
	    for (int i: result11) {
	    	System.out.print(i + ", ");
	    }
	    System.out.println();
	    
		*/
		System.out.println(monotoneIncreasingDigits(7983));
		
		
		int[] test31 = {3, 4, 2};
		int[] test32 = {2, 2, 3, 3, 3, 4};
		
		System.out.println(deleteAndEarn(test31));
		System.out.println(deleteAndEarn(test32));
	    
	}
}
