
public class Solution {
	/*
	 * Implement next permutation, which rearranges numbers into the lexicographically next 
	 * greater permutation of numbers. If such arrangement is not possible, it must rearrange 
	 * it as the lowest possible order (ie, sorted in ascending order).
	 * 
	 * The replacement must be in-place, do not allocate extra memory.
	 * 
	 * Here are some examples. Inputs are in the left-hand column and its corresponding 
	 * outputs are in the right-hand column.
	 * 
	 * 1,2,3 → 1,3,2
	 * 3,2,1 → 1,2,3
	 * 1,1,5 → 1,5,1
	 * 
	 */
	
	
    public static void nextPermutation(int[] nums) {
        int cur = nums.length-1;
        
        while((cur > 0) && (nums[cur-1] >= nums[cur])) {
        		cur--;
        }

       reverse(nums, cur, nums.length-1);
       
       if (cur > 0) {
    	   int next = cur;
    	   cur--;
    	   
    	   while (nums[cur] >= nums[next]) {
    		   next++;
    	   }
    	   
    	   int tmp = nums[cur];
    	   nums[cur] = nums[next];
    	   nums[next] = tmp;
       }
    }
    
    public static void reverse(int[] nums, int start, int end) {
    	
    	while (start < end) {
    		int tmp;
    		tmp = nums[start];
    		nums[start] = nums[end];
    		nums[end] = tmp;
    		start++;
    		end--;
    	}
    }
    
    public static void main(String[] args) {
		int[] test1 = {5,1,1};
		
		for(int i = 0; i < test1.length; i++)
			System.out.print(test1[i]);
		
		System.out.println();
		
		nextPermutation(test1);
		for(int i = 0; i < test1.length; i++)
			System.out.print(test1[i]);
		
		System.out.println();
		System.out.println();
		
		int[] test2 = {6, 8, 7, 4, 3, 2};
		
		for(int i = 0; i < test2.length; i++)
			System.out.print(test2[i]);
		
		System.out.println();
		
		nextPermutation(test2);
		for(int i = 0; i < test2.length; i++)
			System.out.print(test2[i]);
		
		System.out.println();
		System.out.println();
		
		int[] test3 = {1, 1};
		
		for(int i = 0; i < test3.length; i++)
			System.out.print(test3[i]);
		
		System.out.println();
		
		nextPermutation(test3);
		for(int i = 0; i < test3.length; i++)
			System.out.print(test3[i]);
		
	}
}
