import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given a sorted integer array where the range of elements are [lower, upper] inclusive, 
	 * return its missing ranges.
	 * 
	 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, 
	 * return ["2", "4->49", "51->74", "76->99"].
	 */
	
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        
        int prev = lower - 1;
        
        for (int i = 0; i <= nums.length; i++) {
        	int cur = (i == nums.length || nums[i] > upper) ? upper + 1 :  nums[i];
        	
        	if (cur - prev >= 2) {
        		result.add(getRangeString(prev+1, cur-1));
        	}
        	
        	prev = cur;
        }
        
        return result;
    }

    
    private static String getRangeString(int lower, int upper) {
    	   	
    	if (lower == upper) {
    		return Integer.toString(lower);
    	} else {
    		return lower + "->" + upper;
    	}
    	
    }
    
    public static void main(String[] args) {
		int[] test = {0,1,3,50,75};
		
		System.out.println(findMissingRanges(test, 0, 99));
		System.out.println(findMissingRanges(test, 0, 50));
	}
}
