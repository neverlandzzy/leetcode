
public class Solution {
	/*
	 * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
	 * Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.
	 * 
	 * Note:
	 * A naive algorithm of O(n2) is trivial. You MUST do better than that.
	 * 
	 * Example:
	 * Given nums = [-2, 5, -1], lower = -2, upper = 2,
	 * Return 3.
	 * 
	 * The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.
	 */
	
	// 315, 327, 493
	static int count;

    public static int countRangeSum(int[] nums, int lower, int upper) {
    	if (nums == null || nums.length == 0) {
    		return 0;
    	}
    	
    	// count = 0; -- if we define it as static var, we'll need to reset it. 
    	int n = nums.length;
    	long[] sum = new long[n + 1]; //sum[i]: nums的前i项和
    	long[] tmp = new long[n + 1];
    	sum[0] = 0;
    	
    	for (int i = 1; i <= n; i++) {
    		sum[i] = sum[i - 1] + nums[i - 1];
    	}

    	/*
    	for (long i: sum) {
    		System.out.print(i + ", ");
    	}
    	System.out.println();
    	*/
    	mergeSort(sum, tmp, 0, n, upper, lower);
    	
    	/*
    	for (long i: sum) {
    		System.out.print(i + ", ");
    	}
    	
    	System.out.println();
    	
    	for (long i: tmp) {
    		System.out.print(i + ", ");
    	}
    	
    	System.out.println();
    	*/
    	return count;
    }
    
    private static void mergeSort(long[] sum, long[] tmp, int start, int end, int upper, int lower) {
    	if (start < end) {
    		int mid = start + (end - start) / 2;
    		mergeSort(sum, tmp, start, mid, upper, lower);
    		mergeSort(sum, tmp, mid + 1, end, upper, lower);
    		merge(sum, tmp, start, mid, end, upper, lower);
    	}
    }
    
   
    private static void merge(long[] sum, long[] tmp, int start, int mid, int end, int upper, int lower) {
    	int j = mid + 1;
    	int k = start;
    	int low = mid + 1;
    	int high = mid + 1;
    	
    	for (int i = start; i <= mid; i++) {
        	// 对sum的左半部分进行遍历，对左半部(start ... mid)每一个元素sum[i], 在右半部分别找到low和high使得
        	// high is the first index satisfy sums[j] - sums[i]  > upper
        	// low  is the first index satisfy sums[j] - sums[i] >= lower
        	// Then the number of sums in [lower, upper] is high - low
    		// 
    		// 理解：
    		// 这个步骤在排序前，因此右半数组中的任一元素在 sum 数组中的位置正是在左数组任一元素之后
    		// 而左右两边的数组已经是排好序的，所以在high 和low之间的sum一定满足条件， 因此high到low之间有几个元素，就有几个区间
    		while (low <= end && sum[low] - sum[i] < lower) {
    			low++;
    		}
    		
    		while (high <= end && sum[high] - sum[i] <= upper) {
    			high++;
    		}
    		
    		while (j <= end && sum[j] < sum[i]) {
    			tmp[k] = sum[j];
    			k++;
    			j++;
    		}
    		tmp[k] = sum[i];
    		k++;
    		count += high - low;
    	}
    	
    	while (j <= end) {
    		tmp[k] = sum[j];
    		k++;
    		j++;
    	}
    	
    	for (int i = start; i <= end; i++) {
    		sum[i] = tmp[i];
    	}
    }
    public static void main(String[] args) {
    	long startTime = System.nanoTime();
 
    	int[] test = {2, -1, 5, 4, -3, 7, 4};
    	System.out.println(countRangeSum(test, -2, 2));
		
    	long endTime = System.nanoTime();
    	System.out.println("Runtime: "+(endTime - startTime) + " ns");
	}
}
