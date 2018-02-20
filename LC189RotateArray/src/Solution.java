
public class Solution {
	
	/* Rotate an array of n elements to the right by k steps.
	 * 
	 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] 
	 * is rotated to [5,6,7,1,2,3,4].
	 * 
	 */
	
    public static void rotate(int[] nums, int k) {
        
    	int n = nums.length;
    	if (k > n) {
    		k = k % n;
    	}
    	
    	reverse(nums, 0, n-1);
        
    	reverse(nums, k ,n-1);
        reverse(nums, 0, k-1);
        
    }
    
    
    public static void reverse(int[] nums, int start, int end) {
    	int i = start;
    	int j = end;
    	
    	while (i < j) {
    		int tmp = nums[i];
    		nums[i] = nums[j];
    		nums[j] = tmp;
    		i++;
    		j--;
    	}
    }
    
	public static void main(String[] args) {
		int[] testArray = {1};
		
		for(int m: testArray) {
			System.out.print(m);
		}
		
		rotate(testArray, 2);
		
		System.out.println("\nAfter rotation, Array is:");
		for(int m: testArray) {
			System.out.print(m);
		}
		
	}
}
