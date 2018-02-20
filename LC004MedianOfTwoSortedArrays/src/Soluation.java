
public class Soluation {
	/*
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively. 
	 * Find the median of the two sorted arrays. 
	 * 
	 * The overall run time complexity should be O(log (m+n)).
	 */
	
	
	/*
	 * This solution is not optimal. Complexity is O(m+n).
	 * 
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        
        int[] combinedArray = new int[size];
        
        int i = 0;
        int j = 0;
        int k = 0;
        
        while (i < nums1.length && j < nums2.length) {
        	if (nums1[i] <= nums2[j]) {
        		combinedArray[k] = nums1[i];
        		i++;
        		k++;
        	} else {
        		combinedArray[k] = nums2[j];
        		j++;
        		k++;
        	}
        }
        
        if (i == nums1.length) {
        	while(k < size) {
        		combinedArray[k] = nums2[j];
        		k++;
        		j++;
        	}
        }
        
        if (j == nums2.length) {
        	while(k < size) {
        		combinedArray[k] = nums1[i];
        		k++;
        		i++;
        	}
        }
        
        if(size%2 == 1) {
        	return combinedArray[(size-1)/2];
        } else {
        	return (double)(combinedArray[size/2-1] + combinedArray[size/2])/2;
        }
        
    }
    */
	
	/* Solution 2: iteration
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		int m = nums1.length;
		int n = nums2.length;
		
		if (m > n) {
			return findMedianSortedArrays(nums2, nums1);
		}
		
		int imin = 0;
		int imax = m;
		double num1 = 0;
		double num2 = 0;
		
		while(imin <= imax) {
			int i = (imin + imax) / 2;
			int j = (m + n + 1) / 2 - i;
			
			if ((i > 0) && (j < n) && (nums1[i-1] > nums2[j])) {
				imax = i - 1;
			} else if ((j > 0) && (i < m) && (nums2[j-1] > nums1[i])) {
				imin = i + 1;

			} else {
				if(i == 0) {
					num1 = nums2[j-1];					
				} else if (j == 0) {
					num1 = nums1[i-1];
				} else {
					num1 = Math.max(nums1[i-1], nums2[j-1]);
				}

				if ((m+n) % 2 == 1) {
					return num1;
				}
				
				if (i == m) {
					num2 = nums2[j];
				} else if (j == n) {
					num2 = nums1[i];
				} else {
					num2 = Math.min(nums1[i], nums2[j]);
				}
				return (num1 + num2)/2.0;
			}
				
		}
		return num1;
	}
	*/
	
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        int k = nums1.length + nums2.length;
        
        if (k % 2 != 0) {
            return (double) kthElement(nums1, nums2, k/2 + 1, 0, 0);
        } else {
            return ((double) kthElement(nums1, nums2, k/2, 0, 0) + (double) kthElement(nums1, nums2, k/2 + 1, 0, 0)) / 2.0;
        }
    }
    

    private static int kthElement(int[] nums1, int[] nums2, int k, int nums1Start, int nums2Start) {
        if (nums1Start >= nums1.length) {
            return nums2[nums2Start + k - 1];
        }
        
        if (nums2Start >= nums2.length) {
            return nums1[nums1Start + k - 1];
        }
        
        if (k == 1) {
            return Math.min(nums1[nums1Start], nums2[nums2Start]);
        }
        
        int nums1Mid = nums1Start + k / 2 - 1 < nums1.length ? nums1[nums1Start + k / 2 - 1] : Integer.MAX_VALUE;
        int nums2Mid = nums2Start + k / 2 - 1 < nums2.length ? nums2[nums2Start + k / 2 - 1] : Integer.MAX_VALUE;
        
        if (nums1Mid < nums2Mid) {
            return kthElement(nums1, nums2, k - k/2, nums1Start + k / 2, nums2Start);
        } else {
            return kthElement(nums1, nums2, k - k/2, nums1Start, nums2Start + k / 2);
        }
    }
   
    public static void main(String[] args) {
    	
    	int[] num1 = {1,3,5,7,9};
    	int[] num2 = {2,4,6,8,10,11,20};
    	int[] num3 = {0,4};
    	int[] num4 = {1,2,3};
    	int[] num5 = {4,5,6,7};
    	double median = findMedianSortedArrays(num1, num2);
    	
    	System.out.println(median);

    }
}
