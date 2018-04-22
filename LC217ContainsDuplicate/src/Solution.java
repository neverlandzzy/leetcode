import java.util.HashSet;


public class Solution {
	/*
	 * Given an array of integers, find if the array contains any duplicates. Your function should 
	 * return true if any value appears at least twice in the array, and it should return false if 
	 * every element is distinct.
	 */
	
    public static boolean containsDuplicate(int[] nums) {
        
    	if (nums == null || nums.length == 0) {
    		return false;
    	}
    	
    	HashSet<Integer> set = new HashSet<>();
        
        for (int i: nums) {
        	if (!set.add(i)) {
        		return true;
        	}
        }
        
        return false;
    }
    
    public static void main(String[] args) {
		int[] test1 = {1,2,3,4,5,6,7,8,9,10};
		int[] test2 = {1,2,3,4,5,6,7,8,9,1};
		int[] test3 = {};
		
		System.out.println(containsDuplicate(test1));
		System.out.println(containsDuplicate(test2));
		System.out.println(containsDuplicate(test3));
	}
}
