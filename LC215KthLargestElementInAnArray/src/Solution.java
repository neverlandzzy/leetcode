import java.util.PriorityQueue;


public class Solution {
	/**
	 * Find the kth largest element in an unsorted array. Note that 
	 * it is the kth largest element in the sorted order, not the kth distinct element.
	 * 
	 * For example,
	 * Given [3,2,1,5,6,4] and k = 2, return 5.
	 * 
	 * Note: 
	 * You may assume k is always valid, 1 ≤ k ≤ array's length.
	 */
	
	// Solution 1: Priority Queue O(nlogK) -- follow-up question, what if input is a stream
	/*
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        
        for (int i = 0; i < k; i++) {
            heap.offer(nums[i]);
        }
        
        for (int i = k; i < nums.length; i++) {
            if (heap.peek() < nums[i]) {
                heap.poll();
                heap.offer(nums[i]);
            }
        }
        
        return heap.poll();
    }
    */
    // Solution 2: Selection Algorithm  time: O(n)
	// https://leetcode.com/discuss/51518/java-o-n-solution-using-selection-algorithm
    
	public static int findKthLargest(int[] nums, int k) {
		int n = nums.length;
		int i = quickSelect(nums, 0, n - 1, n - k + 1);
		
		return nums[i];
	}
	
	// quick select 模板：找nums中第k小
	private static int quickSelect(int[] nums, int low, int high, int k){
		int left = low;
		int right = high;
		
		// 原方法选的是nums[high]做pivot，最好选随机点，或者中点。 否则，如果input是sorted array，时间复杂度就变成了n^2
		int pivot = nums[(left + high) / 2];
		swap(nums, (left + high) / 2, high);
		
		// put nums that are <= pivot to the left
	    // put nums that are  > pivot to the right
		while (left < right) {
			if (nums[left] > pivot) {
				right--;
				swap(nums, left, right);
				//right--;
			} else {
				left++;
			}
		}
	
		swap(nums, left, high);
//		for (int n: nums) {
//			System.out.print(n + ", ");
//		}
//
//		System.out.println();
		
		// count the nums that are <= pivot from low
		int count = left - low + 1;

		if (count == k) {
			return left;
		}
		
		if (count < k) {
			// <= pivot的数不够，从右面继续找 k - count个
			return quickSelect(nums, left + 1, high, k - count);
		} else {
			// <= pivot的数太多，从左边找k个
			return quickSelect(nums, low, left - 1, k);
		}
	}
	
	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	/*
	private static int quickSelect(int[] nums, int k, int low, int high){
		int left = low;
		int right = high;
		int pivot = nums[(left + high) / 2];
		
		while (low <= high) {
			while (nums[low] > pivot) {
				low++;
			}
			
			while (nums[high] < pivot) {
				high--;
			}
			
			if (low <= high) {
				swap(nums, low, high);
				low++;
				high--;
			}
			
			System.out.println("=============");
			for(int n: nums) System.out.print(n);
			System.out.println();
			System.out.println("left = " + low);
			System.out.println("right = " + high);
			System.out.println("leftBound = " + left);
			System.out.println("rightBound = " + right);
			System.out.println("+++++++++++++");
		}
		
		if (high > left && k < high) {
			System.out.println("Right");
			return quickSelect(nums, k, left, high);
		}
		
		
		if (low < right && k > low) {
			System.out.println("left");
			return quickSelect(nums, k, low, right);
		}
		
		return nums[k];
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	*/
	
    public static void main(String[] args) {
		int[] test = {3,2,1,5,6,4};
		System.out.println(findKthLargest(test, 2));

	}
	
}
