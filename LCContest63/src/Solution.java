import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


public class Solution {
    public static int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
        	return -1;
        }
        
        int index = -1;
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < nums.length; i++) {
        	if (max < nums[i]) {
        		index = i;
        		max = nums[i];
        	}
        }
        
        for (int i = 0; i < nums.length; i++) {
        	if (i != index && nums[i] * 2 > max) {
        		return -1;
        	}
        }
        
        return index;
    }
    
    
    public static List<String> ipToCIDR(String ip, int range) {
    	List<String> result = new ArrayList<>();
    	
        String[] ipArray = ip.split("\\.");

        int[] nums = new int[ipArray.length];
        
        for (int i = 0; i < nums.length; i++) {
        	nums[i] = Integer.parseInt(ipArray[i]);

        }

        
        return result;
    }
    
    public static int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        String start = "0000";
        if (set.contains(start)) {
        	return -1;
        }
        
        visited.add(start);
        queue.offer(start);
        int result = 1;
        
        while (!queue.isEmpty()) {
        	int size = queue.size();
        	for (int i = 0; i < size; i++) {
        		String s = queue.poll();
        		for (String next: move(s)) {
        			if (next.equals(target)) {
        				return result++;
        			}
        			
                    if (set.contains(next) || visited.contains(next)) {
                    	continue;
                    } else {
                    	queue.offer(next);
                    	visited.add(next);
                    }
        		}
        	}
        	result++;
        }
        
        
        return -1;
    }
    
    private static List<String> move(String s) {
    	List<String> result = new ArrayList<>();
    			
    	for (int i = 0; i < s.length(); i++) {
    		char[] charArray = s.toCharArray();
    		char c = charArray[i];
    		
    		if (c == '0') {
    			charArray[i] = '9';
    			result.add(String.valueOf(charArray));
    			charArray[i] = '1';
    			result.add(String.valueOf(charArray));
    		} else if (c == '9') {
    			charArray[i] = '0';
    			result.add(String.valueOf(charArray));
    			charArray[i] = '8';
    			result.add(String.valueOf(charArray));
    		} else {
    			charArray[i] = (char)(Integer.valueOf(c) + 1);
    			result.add(String.valueOf(charArray));
    			charArray[i] = (char)(Integer.valueOf(c) - 1);
    			result.add(String.valueOf(charArray));
    		}
    	}
    	
    	return result;
    }
    
    public static void main(String[] args) {
    	/*
		int[] test11 = {3, 6, 1, 0};
		int[] test12 = {1, 2, 3, 4};
		
		System.out.println(dominantIndex(test11));
		System.out.println(dominantIndex(test12));
		
    	
    	System.out.println(ipToCIDR("255.0.0.7", 10));
    	*/
    	
    	String[] test31 = {"0201","0101","0102","1212","2002"};
    	String[] test32 = {"8888"};
    	String[] test33 = {"8887","8889","8878","8898","8788","8988","7888","9888"};
    	String[] test34 = {"0000"};
    	
    	//System.out.println(openLock(test31, "0202"));
    	//System.out.println(openLock(test32, "0009"));
    	System.out.println(openLock(test33, "8888"));
    	//System.out.println(openLock(test34, "8888"));

    	
    	
	}
}
