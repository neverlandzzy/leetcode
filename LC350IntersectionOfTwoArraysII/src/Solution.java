import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Solution {
	/**
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
	 * 1. What if the given array is already sorted? How would you optimize your algorithm?
	 * 2. What if nums1's size is small compared to nums2's size? Which algorithm is better?
	 * 3. What if elements of nums2 are stored on disk, and the memory is limited such that you 
	 *    cannot load all elements into the memory at once?
	 */
	
	// [Follow up]
	// https://zhuanlan.zhihu.com/p/32786833
	// 1. What if the given array is already sorted? How would you optimize your algorithm?
	//    可以用双指针，时间复杂度依然是O(m + n)，但空间复杂度是O(1)
	// 2. What if nums1's size is small compared to nums2's size? Which algorithm is better?
	//    将nums1（较小的）放入HashMap，扫描nums2
	// 3. What if elements of nums2 are stored on disk, and the memory is limited such that
	//    you cannot load all elements into the memory at once?
	//    将nums1放入HashMap，将nums2分批读到内存中处理
	// 4. What if both nums1 and nums2 are too large such that you cannot load them into memory at once?
	//    若nums1和nums2都很大不能存在内存，则先做外排序，然后分批读到内存中用双指针做。
	
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
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
