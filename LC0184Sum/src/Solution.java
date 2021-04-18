import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Solution {
	/**
	 * Given an array S of n integers, are there elements a, b, c, and d in S 
	 * such that a + b + c + d = target? Find all unique quadruplets in the array which gives 
	 * the sum of target.
	 * 
	 * Note:
	 * Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a � b � c � d)
	 * The solution set must not contain duplicate quadruplets.
	 * 
	 * 
	 * For example, given array S = {1 0 -1 0 -2 2}, and target = 0.
	 * A solution set is:
	 * 	(-1,  0, 0, 1)
	 * 	(-2, -1, 1, 2)
	 * 	(-2,  0, 0, 2)
	 */
	
	// Reuse code from 3Sum
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums);
        
        int n = nums.length;
        
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                
                int a = nums[i];
                int b = nums[j];
                
                int left = j + 1;
                int right = n - 1;
                int k = target - a - b;
                
                while (left < right) {
                    if (nums[left] + nums[right] == k) {
                        List<Integer> list = new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        result.add(list);
                        
                        left++;
                        right--;
                        
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (nums[left] + nums[right] < k) {
                        left++;
                    } else {
                        right--;
                    }
                }   
            }
        }
        
        return result;
    }
    
    // Another solution, same idea and complexity
    /*
	public static List<List<Integer>> fourSum(int[] nums, int target) {
		
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length-3; i++) {
			for (int j = i+1; j < nums.length-2; j++) {
				for(int lo = j+1, hi = nums.length-1; lo < hi;) {
					if (nums[i] + nums[j] + nums[lo] +nums[hi] < target) {
						lo++;
					} else if (nums[i] + nums[j] + nums[lo] +nums[hi] > target) {
						hi--;
					} else {
						if(i == 0 || ((i > 0) && nums[i] != nums[i-1])) {
							List<Integer> temp = new ArrayList<Integer>();
							temp.add(nums[i]);
							temp.add(nums[j]);
							temp.add(nums[lo]);
							temp.add(nums[hi]);
							result.add(temp);
						}
	        			while(lo < hi && nums[lo] == nums[lo+1]) {
	        				lo++;
	        			}

	        			while(lo < hi && nums[hi] == nums[hi-1]) {
	        				hi--;
	        			}
	    				lo++;
	    				hi--;
					}
				}
				while( j < nums.length-2 && nums[j] == nums[j+1]) {
    				j++;
    			}
			}
		}
		
		return result;
	}
	*/
    public static void main(String[] args) {
    	int[] test = {-1, 0, 1, 0, -2, 2,2};
    	int[] test2 = {-3, -2, -1, 0, 0, 1, 2, 3};
    	int[] test3 = {0,0,0,0};
    	
    	//System.out.println(fourSum(test, 0));
    	//System.out.println(fourSum(test2, 0));
    	System.out.println(fourSum(test3, 0));
    	
    }
}
