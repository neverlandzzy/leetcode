import java.util.Arrays;


public class Solution {
	/*
	 * Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
	 * 
	 * You need to return the number of important reverse pairs in the given array.
	 * 
	 * Example1:
	 * 
	 * Input: [1,3,2,3,1]
	 * Output: 2
	 * 
	 * Example2:
	 * 
	 * Input: [2,4,3,5,1]
	 * Output: 3
	 * 
	 * Note:
	 * The length of the given array will not exceed 50,000.
	 * All the numbers in the input array are in the range of 32-bit integer.
	 */
	
	static int count = 0;
	
    public static int reversePairs(int[] nums) {
    	count = 0;
        mergeSort(nums, 0, nums.length - 1);   
        return count;
    }
    
    private static void mergeSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            
            for (int i = start, j = mid + 1; i <= mid; i++) {
                
                while (j <= end && (long)nums[i] > (long)nums[j] * 2) {
                    j++;
                }
                count += j - (mid + 1);
            }
            //Arrays.sort(nums, start, end + 1); 
            merge(nums, start, mid, end);
        }
    }
    
    private static void merge(int[]nums, int start, int mid, int end) {
		int[] tmp = new int[end - start + 1];

		int i = start; 
		int j = mid + 1;
		int k = 0;
		
		while (i <= mid && j <= end) {
			if (nums[i] <= nums[j]) {
				tmp[k++] = nums[i];
				i++;
			} else {
				tmp[k++] = nums[j];
				j++;
			}
		}
		
		while (i <= mid) {
			tmp[k] = nums[i];
			k++;
			i++;
		}
        
        while (j <= end) {
			tmp[k] = nums[j];
			k++;
			j++;
		}
		
		for (int m = 0; m < tmp.length; m++) {
			nums[m + start] = tmp[m];
		}
	}
    
    public static void main(String[] args) {
    	
    	int[] test1 = {1,3,2,3,1};
    	int[] test2 = {2,4,3,5,1};
		System.out.println(reversePairs(test1));
		System.out.println(reversePairs(test2));
	}
}
