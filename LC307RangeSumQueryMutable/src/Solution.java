
public class Solution {
	
	/*
	 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), 
	 * inclusive.
	 * 
	 * The update(i, val) function modifies nums by updating the element at index i to val.
	 * 
	 * Example:
	 * 
	 * Given nums = [1, 3, 5]
	 * 
	 * sumRange(0, 2) -> 9
	 * update(1, 2)
	 * sumRange(0, 2) -> 8
	 * 
	 * Note:
	 * The array is only modifiable by the update function.
	 * You may assume the number of calls to update and sumRange function is distributed evenly.
	 */
	public static void main(String[] args) {
		int[] nums = {1, 3, 5};
		
		NumArray numArray = new NumArray(nums);
		
		//System.out.println(numArray.sumRange(0, 1));
		//System.out.println(numArray.sumRange(1, 10));
		//System.out.println(numArray.sumRange(1, 2));
		
		numArray.update(1, 8);
		//System.out.println(numArray.sumRange(0, 1));
		//System.out.println(numArray.sumRange(1, 10));
		//System.out.println(numArray.sumRange(1, 2));
		
	}
}
