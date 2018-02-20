import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Solution {
	/*
	 * Given two arrays, write a function to compute their intersection.
	 * 
	 * Example:
	 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
	 * 
	 * 
	 * Note:
	 * 
	 * Each element in the result should appear as many times as it shows in both arrays.
	 * The result can be in any order.
	 * 
	 * Follow up:
	 * 
	 * What if the given array is already sorted? How would you optimize your algorithm?
	 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
	 * What if elements of nums2 are stored on disk, and the memory is limited such that you 
	 * cannot load all elements into the memory at once?
	 */
	
    public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        
        for (int n: nums1) {
            if (map.containsKey(n)) {
                map.put(n, map.get(n) + 1);
            } else {
                map.put(n, 1);
            }
        }
        
        for (int n: nums2) {
            if (map.containsKey(n) && map.get(n) > 0) {
                map.put(n, map.get(n) - 1);
                list.add(n);
            }
        }
        
        int[] result = new int[list.size()];
        int i = 0;
        
        for (int n: list) {
        	result[i++] = n;
        }
        
        return result;
        
    }
    
    public static void main(String[] args) {
		int[] nums1 = {1, 2, 2, 1}; 
		int[] nums2 = {2, 2, 1};
		int[] intersection = intersect(nums1, nums2);
		
		for (int n: intersection) {
			System.out.print(n + ", ");
		}
		
	}

}
