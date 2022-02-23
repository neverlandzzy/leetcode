
public class Solution {
	/**
	 * Given an unsorted integer array, find the first missing positive integer.
	 * 
	 * For example,
	 * Given [1,2,0] return 3,
	 * and [3,4,-1,1] return 2.
	 * 
	 * Your algorithm should run in O(n) time and uses constant space.
	 */
	
	
	
	public static int firstMissingPositive(int[] nums) {
		// 尽量使数组第i位存 i+1， 即：A[0] = 1, A[1] = 2, A[2] = 3，..., A[i] = i + 1
		// 如果找到 A[i] != i + 1 ==> 即A[i] != A[A[i] - 1]， 则交换
		// 这里不能直接用A[i] != i + 1判断，因为e.g.[1, 1]， 若A[i] == A[A[i] - 1] 则交换后一样，会出现死循环
		// 再扫描数组找哪里缺少值
        int i = 0;
        
        while (i < nums.length) {
            if (nums[i] < nums.length && nums[i] > 0 && nums[i] != nums[nums[i] - 1]) {
                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            } else {
                i++;
            }
        }
        
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != j + 1) {
                return j + 1;
            }
        }
        
        return nums.length + 1;
	}
	
	public static void main(String[] args) {
		int[] test = {3,1,4,2,2};
		
		System.out.println(firstMissingPositive(test));
		for (int i: test) {
			System.out.print(i + " ");
		}
	}
	    
}
