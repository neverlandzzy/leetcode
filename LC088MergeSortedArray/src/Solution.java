
public class Solution {
	/*
	 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
	 * 
	 * Note:
	 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold 
	 * additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n 
	 * respectively.
	 */
	
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        
        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[index] = nums1[i];
                i--;
            } else {
                nums1[index] = nums2[j];
                j--;
            }
            
            index--;
        }
        
        while (i >= 0) {
            nums1[index--] = nums1[i--];
        } 
        
        while (j >= 0){
            nums1[index--] = nums2[j--];
        }
    }
    
    public static void main(String[] args) {
		int[] m = new int[15];
		int[] n = {1,4,7,10,21};
		
		m[0] = 2;
		m[1] = 3;
		m[2] = 6;
		m[3] = 9;
		m[4] = 10;
		m[5] = 12;
		
		for (int k: m) {
			System.out.print(k + " ");
		}
		System.out.println();
		for (int k: n) {
			System.out.print(k + " ");
		}
		System.out.println();
		
		merge(m, 6, n, 5);
		
		for (int k: m) {
			System.out.print(k + " ");
		}
	}
}
