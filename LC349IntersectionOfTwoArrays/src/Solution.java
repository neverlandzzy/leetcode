import java.util.HashSet;
import java.util.Set;


public class Solution {
	/*
	 * Given two arrays, write a function to compute their intersection.
	 * 
	 * Example:
	 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
	 * 
	 * Note:
	 * Each element in the result must be unique.
	 * The result can be in any order.
	 */
	
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        
        for (int n: nums1) {
            set1.add(n);
        }
        
        for (int n : nums2) {
            if (set1.contains(n)) {
                set2.add(n);
            }
        }
        
        int[] result = new int[set2.size()];
        int i = 0;
        
        for (int n: set2) {
            result[i++] = n;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
		int[] nums1 = {1, 2, 2, 1}; 
		int[] nums2 = {2, 2, 1};
		int[] intersection = intersection(nums1, nums2);
		
		for (int n: intersection) {
			System.out.print(n + ", ");
		}
		
	}
}
