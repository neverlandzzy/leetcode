import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * Given an integer array of size n, find all elements that appear more 
	 * than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.
	 */
    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        
        int number1 = 0;
        int number2 = 0;
        int counter1 = 0;
        int counter2 = 0;
        
        for (int i = 0; i < nums.length; i++) {
        	if (nums[i] == number1) {
        		counter1++;
        	} else if (nums[i] == number2) {
        		counter2++;
        	} else if (counter1 == 0) {
        		number1 = nums[i];
        		counter1 = 1;
        	} else if (counter2 == 0 && number1 != nums[i]) {
        		number2 = nums[i];
        		counter2 = 1;
        	} else {
        		counter1--;
        		counter2--;
        	}
        }
        
        if (isMajority(nums, number1)) {
        	result.add(number1);
        }
        
        if (number2 != number1 && isMajority(nums, number2)) {
        	result.add(number2);
        }
        
        return result;
        
    }
    
    private static boolean isMajority(int[] nums, int val) {
    	
    	int counter = 0;
    	
    	for (int i = 0; i < nums.length; i++) {
    		if (nums[i] == val) {
    			counter++;
    		}
    	}
    	
    	if (counter > nums.length / 3) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public static void main(String[] args) {
		int[] test1 = {3, 4, 3, 3, 6, 1, 3, 3};
		int[] test2 = {1, 2, 3, 4, 5, 6, 7};
		int[] test3 = {2,3,4,3,2,4,3,2,3,2,3,5,2};
		int[] test4 = {1,2,2,3,2,1,1,3};
 		
		//System.out.println(majorityElement(test1));
		//System.out.println(majorityElement(test2));
		//System.out.println(majorityElement(test3));
		System.out.println(majorityElement(test4));
	}
}
