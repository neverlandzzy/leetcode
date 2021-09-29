import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	/**
	 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? 
	 * Find all unique triplets in the array which gives the sum of zero.
	 * 
	 * Note:
	 * Note: The solution set must not contain duplicate triplets.
	 * 
	 *   For example, given array S = {-1 0 1 2 -1 -4},
	 *   A solution set is:
	 *   (-1, 0, 1)
	 *   (-1, -1, 2)
	 */
	
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int t = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                if (nums[left] + nums[right] == t) {
                    List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right]));
                    result.add(list);
                    
                    left++;
                    right--;
                    
                    while (left < right && nums[left - 1] == nums[left]) {
                        left++;
                    }
                    
                    while (left < right && nums[right + 1] == nums[right]) {
                        right--;
                    }
                } else if (nums[left] + nums[right] < t) {
                    left++;
                } else {
                    right--;
                }
            }
            
        }
        
        return result;
    }
    
    // Another solution, same idea and complexity
    /*
    public static List<List<Integer>> threeSum(int[] nums) {
    	
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int n = nums.length;
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int target = -nums[i];
            
            int low = i + 1;
            int high = n - 1;
            
            while (low < high) {
                if (nums[low] + nums[high] == target) {
                    result.add(Arrays.asList(nums[low], nums[high], nums[i]));
                    
                    while (low < high && nums[low] == nums[low + 1]) {
                        low++;
                    }
                    
                    while (low < high && nums[high] == nums[high - 1]) {
                        high--;
                    }
                    low++;
                    high--;
                } else if (nums[low] + nums[high] < target) {
                    low++;
                } else {
                    high--;
                }
            }
        }
        
        return result;        
    }
    */
    public static void main(String[] args) {
    	int[] test = {-1, 0, 1, 2, -1, -4};
    	int[] test2 = {};
    	System.out.println(threeSum(test));
    	System.out.println(threeSum(test2));
    }
}
