import java.util.ArrayList;
import java.util.List;


public class Solution {
	/**
	 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 
	 * 6 LEDs on the bottom represent the minutes (0-59).
	 * 
	 * Each LED represents a zero or one, with the least significant bit on the right.
	 * 
	 * Given a non-negative integer n which represents the number of LEDs that are currently on, 
	 * return all possible times the watch could represent.
	 * 
	 * Example:
	 * 
	 * Input: n = 1
	 * Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
	 * 
	 * Note:
	 * 
	 * The order of output does not matter.
	 * 
	 * The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
	 * 
	 * The minute must be consist of two digits and may contain a leading zero, for example "10:2" is 
	 * not valid, it should be "10:02"·	.
	 */
	
	// Solution 1: Iteration
	/*
    public static List<String> readBinaryWatch(int num) {
    	
    	List<String> result = new ArrayList<String>();
    	
    	for (int hour = 0; hour < 12; hour++) {
    		for (int min = 0; min < 60; min++) {
    			if ((Integer.bitCount(hour) + Integer.bitCount(min)) == num) {
    				result.add(String.format("%d:%02d", hour, min));
    			}
    		}
    	}
    	
    	return result;
    */
    	
    // Solution 2: Combination + Permutation

    public static List<String> readBinaryWatch(int num) {
        int[] hourDigits = {1, 2, 4, 8};
        int[] minsDigits = {1, 2, 4, 8, 16, 32};
        
        List<String> result = new ArrayList<>();

        
        for (int i = 0; i <= num; i++) {
            List<Integer> mins = new ArrayList<>();
            List<Integer> hours = new ArrayList<>();
            
            helper(mins, minsDigits, 60, i, 0, 0);
            helper(hours, hourDigits, 12, num - i, 0, 0);
            
            for (int m: mins) {
                for (int h: hours) {
                    result.add(h + ":" + (m < 10 ? "0" + m : m));
                }
            }
        }
        
        return result;
        
    }
    
    
    private static void helper(List<Integer> result, int[] digits, int limit, int num, int sum, int pos) {
        if (num == 0) {
            if (sum < limit) {
                result.add(sum);
            }
            return;
        }
        
        if (sum >= limit) {
            return;
        }
        
        for (int i = pos; i < digits.length; i++) {
            helper(result, digits, limit, num - 1, sum + digits[i], i + 1);
        }
    }
    
	/*
	public static List<String> readBinaryWatch(int num) {
    	List<String> result = new ArrayList<String>();
   
    	int[] hourDigits = {1,2,4,8};
    	int[] minDigits = {1,2,4,8,16,32};
    	
    	for (int i = 0; i <= num; i++) {
    		List<Integer> hours =  getDigitList(hourDigits, i, 12);
    		List<Integer> mins = getDigitList(minDigits, num - i, 60);
    		
    		for(int h: hours) {
    			for (int m: mins) {
    				result.add(h + ":" + (m < 10 ? "0" + m : m));
    			}
    		}
    	}
    	return result;
    }
    
    private static List<Integer> getDigitList(int[] nums, int count, int limit) {
    	List<Integer> result = new ArrayList<Integer>();
    	getDigitListHelper(result, nums, count, 0, 0, limit);
    	return result;
    }
    
    private static void getDigitListHelper(List<Integer> result, int[]nums, int count, int sum, int pos, int limit) {
    	if (count == 0) {
    	    
    	    if (sum < limit) {
    		    result.add(sum);
    	    }
    		return;
    	}
    	
    	for (int i = pos; i < nums.length; i++) {
    		getDigitListHelper(result, nums, count - 1, sum + nums[i], i + 1, limit);
    	}
    }
    */
    public static void main(String[] args) {
    	List<String> list = readBinaryWatch(2);
		System.out.println(list.size());
	}
}
