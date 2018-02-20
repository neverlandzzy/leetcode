package phone;

public class LocalMinimal {
	// 类似 LC162
	
	// Solution 1: 扫一遍 O(n)
	// 本解法返回的是index，注意要求
	// 确认：1. 数组保证相邻元素不相等？
	//      2. 数组为空或长度为0？
	//      3. 边界情况， 只有一个元素的情况？
	private static int findLocalMinimalElement(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		
		for (int i = 1; i < nums.length - 1; i++) {
			if (nums[i] < nums[i - 1] && nums[i] < nums[i + 1]) {
				return i;
			}
		}
		
		return nums[0] < nums[nums.length - 1] ? 0 : nums.length - 1;
	}
	
	// Solution 2: 二分法O(logn)
	private static int findLocalMinimalElementII(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		
	    while (start + 1< end) {
	    	int mid = start + (end - start) / 2;
	    	
	    	if (mid > 0 && nums[mid] > nums[mid - 1]) {
	    		end = mid;
	    	} else if (mid < nums.length - 1 && nums[mid] > nums[mid + 1]) {
	    		start = mid;
	    	} else {
	    		return mid;
	    	}
	    }
	    
	    return nums[start] < nums[end] ? start : end;
	}
	
    public static void main(String[] args) {
		int[] test1 = {1,2,3,1};
		int[] test2 = {1,2,3,4};
		int[] test3 = {4,3,2,1};
		
		System.out.println(findLocalMinimalElement(test1));
		System.out.println(findLocalMinimalElement(test2));
		System.out.println(findLocalMinimalElement(test3));
		
		System.out.println(findLocalMinimalElementII(test1));
		System.out.println(findLocalMinimalElementII(test2));
		System.out.println(findLocalMinimalElementII(test3));
		
	}
}
