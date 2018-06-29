
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
        int n1 = nums1.length;
        int n2 = nums2.length;
        
        if ((n1 + n2) % 2 == 0) {
            return (double)(kthElement(nums1, nums2, 0, 0, (n1 + n2) / 2) + kthElement(nums1, nums2, 0, 0, (n1 + n2) / 2 + 1)) / 2.0;
        } else {
            return (double)kthElement(nums1, nums2, 0, 0, (n1 + n2) / 2 + 1);
        }
    }
    
    private static int kthElement(int[] nums1, int[] nums2, int i1, int i2, int k) {
        if (i1 >= nums1.length) {
            return nums2[i2 + k - 1];
        }
        
        if (i2 >= nums2.length) {
            return nums1[i1 + k - 1];
        }
        
        if (k == 1) {
            return nums1[i1] < nums2[i2] ? nums1[i1] : nums2[i2];
        }
        
        int mid1 = i1 + k / 2 - 1 >= nums1.length ? Integer.MAX_VALUE : nums1[i1 + k / 2 - 1];
        int mid2 = i2 + k / 2 - 1 >= nums2.length ? Integer.MAX_VALUE : nums2[i2 + k / 2 - 1];
        
        if (mid1 > mid2) {
            return kthElement(nums1, nums2, i1, i2 + k / 2, k - k / 2);
        } else {
            return kthElement(nums1, nums2, i1 + k / 2, i2, k - k / 2);
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
