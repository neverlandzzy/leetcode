public class Solution {
	/**
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
	
	// 315, 327, 493
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
                    // 此处不能用count++来计算，因为当merge [2, 3, 4] 和[1,5]时，当发现 (3,1)满足条件后，即认为前面数组中3和3以后的元素
					// 都满足条件，因此count += j - (mid + 1) 包括了(3, 1)和(4, 1)，然后j会向后移1位，比较3和5, 然后i向后移，比较4和5
//					printArray(nums);
//					System.out.println("i = " + i + " j = " + j + " mid = " + mid);
                }
                count += j - (mid + 1);
            }
            //Arrays.sort(nums, start, end + 1); 
            merge(nums, start, mid, end);
        }
    }

//    private static void printArray(int[]nums) {
//		for (int i: nums) {
//			System.out.print(i + ", ");
//		}
//		System.out.println();
//	}
    
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
		//System.out.println(reversePairs(test1));
		System.out.println(reversePairs(test2));
	}
}
