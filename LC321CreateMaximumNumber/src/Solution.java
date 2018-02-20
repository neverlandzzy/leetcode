
public class Solution {
	/*
	 * Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n 
	 * from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. 
	 * You should try to optimize your time and space complexity.
	 * 
	 * Example 1:
	 * nums1 = [3, 4, 6, 5]
	 * nums2 = [9, 1, 2, 5, 8, 3]
	 * k = 5
	 * return [9, 8, 6, 5, 3]
	 * 
	 * Example 2:
	 * nums1 = [6, 7]
	 * nums2 = [6, 0, 4]
	 * k = 5
	 * return [6, 7, 6, 0, 4]
	 * 
	 * Example 3:
	 * nums1 = [3, 9]
	 * nums2 = [8, 9]
	 * k = 3
	 * return [9, 8, 9]
	 */
	
	// http://blog.csdn.net/u010025211/article/details/50527279
	// https://discuss.leetcode.com/topic/32272/share-my-greedy-solution
	
	// O(n^3)
	
	// 先从两个简单问题开始：
	// 1. Create the maximum number of one array -- 从一个数组中选择i个数，使这i个数表示的数值是所有候选中最大的。
	// 2. Create the maximum number of two array using all of their digits -- 合并两个数组使得形成最大的值
    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
    	if (k == 0) {
    		return new int[0];
    	}
    	
        int m = nums1.length;
        int n = nums2.length;
        int[] result = new int[k];
        
        // i = Math.max(0, k - n) : 遍历从全部采用nums2的元素开始：
        // 当k < n时，i = 0， 即从nums1取0个元素，从nums2取k个元素
        // 当k > n时，i = k - n, 即从nums2取n（全部）个元素，从nums1中取其余的k - n个元素
        for (int i = Math.max(0, k - n); i <= k && i <= m; i++) {
        	int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i));
        	
        	if (greater(candidate, 0, result, 0)) {
        		result = candidate;
        	}
        }
        
        return result;
    }
    
    
    // 1. Create the maximum number of one array using k elements --  O(n)
    private static int[] maxArray(int[] nums, int k) {
    	int n = nums.length;
    	int[] result = new int[k];
    	
    	for (int i = 0, j = 0; i < n; i++) {
    		
    		// n - i + j > k : nums[]剩余的元素(n - i) + 当前result中的元素(j)大于k时，即还有多余的元素时
    		while (n - i + j > k && j > 0 && result[j - 1] < nums[i]) {
    			j--;
    		}
    		if (j < k) {
    			result[j] = nums[i];
    			j++;
    		}
    	}
    	
    	return result;
    }
    
    // 2. Create the maximum number of two arrays using all of their digits (k = nums1.length + nums2.length) -- O(mn)
    private static int[] merge(int[] nums1, int[] nums2) {
    	int k = nums1.length + nums2.length;
    	int[] result = new int[k];
    	for (int i = 0, j = 0, l = 0; l < k; l++) {
    		result[l] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
    	}
    	
    	return result;
    }
    
    private static boolean greater(int[] nums1, int i, int[] nums2, int j) {
    	while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
    		i++;
    		j++;
    	}
    	
    	return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    
    
    public static void main(String[] args) {
    	int[] test1 = {6, 7};
    	int[] test2 = {6, 0, 4};
    	
    	int[] result = maxNumber(test1, test2, 5);
    	
    	for (int k: result) {
    		System.out.print(k + ", ");
    	}
    	
    	/*
    	int[] test = maxArray(test2, 2);
    	for (int k: test) {
    		System.out.print(k + ", ");
    	}
    	*/
	}
}
