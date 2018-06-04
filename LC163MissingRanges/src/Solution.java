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
        List<String> result = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            if (lower == upper) {
                result.add("" + lower);
            } else {
                result.add(lower + "->" + upper);
            }
            
            return result;
        }
        
        int pre = 0;
        int i = 1;
        int n = nums.length;
        
        if (nums[pre] > lower) {
            if (nums[pre] == lower + 1) {
                result.add(String.valueOf(lower));
            } else {
                result.add(lower + "->" + (nums[pre] - 1));
            }
        }
        
        while (i < n) {
            if (nums[pre] == Integer.MAX_VALUE) {
                i++;
                pre++;
                continue;
            }
            if (nums[i] > nums[pre] + 1) {
                if (nums[i] == nums[pre] + 2) {
                    result.add(String.valueOf(nums[i] - 1));
                } else {
                    result.add((nums[pre] + 1) + "->" + (nums[i] - 1));
                }
            }
            i++;
            pre++;
        }
        
        if (nums[pre] < upper) {
            if (nums[pre] + 1 == upper) {
                result.add(String.valueOf(upper));
            } else {
                result.add((nums[pre] + 1) + "->" + upper);
            }
        }
        
        return result;
    }
    
    // 原来的解法，现在已经不能AC，因为整数越界
    /*
    public static List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        
        int prev = lower - 1;
        
        for (int i = 0; i <= nums.length; i++) {
        	int cur = (i == nums.length || nums[i] > upper) ? upper + 1 :  nums[i];
        	
        	if (cur - prev >= 2) {
        		result.add(getRangeString(prev + 1, cur - 1));
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
    */
    public static void main(String[] args) {
		int[] test = {0,1,3,50,75};
		
		System.out.println(findMissingRanges(test, 0, 99));
		System.out.println(findMissingRanges(test, 0, 50));
	}
}
