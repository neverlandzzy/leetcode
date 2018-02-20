
public class Solution {

	
	public static void main(String[] args) {
		
		int[] nums = {-2, 0, 3, -5, 2, -1};
		
		NumArray numArray = new NumArray(nums);
		
		System.out.println(numArray.sumRange(0, 2));
		System.out.println(numArray.sumRange(2, 5));
		System.out.println(numArray.sumRange(0, 5));
	}
	
}
