import java.util.ArrayList;
import java.util.List;


public class Solution {
	/*
	 * You are given an integer array nums and you have to return a new counts array. 
	 * The counts array has the property where counts[i] is the number of smaller elements to the 
	 * right of nums[i].
	 * 
	 * Example:
	 * 
	 * Given nums = [5, 2, 6, 1]
	 * 
	 * To the right of 5 there are 2 smaller elements (2 and 1).
	 * To the right of 2 there is only 1 smaller element (1).
	 * To the right of 6 there is 1 smaller element (1).
	 * To the right of 1 there is 0 smaller element.
	 * Return the array [2, 1, 1, 0].
	 */
	
	
	// https://leetcode.com/discuss/74110/11ms-java-solution-using-merge-sort-with-explanation
	
	private static int[] count;
	
    public static List<Integer> countSmaller(int[] nums) {
    	List<Integer> result = new ArrayList<Integer>();
    	
    	count = new int[nums.length];
    	int[] index = new int[nums.length];
    	
    	for (int i = 0; i < nums.length; i++) {
    		index[i] = i;
    	}
    	
        mergesort(nums, index, 0, nums.length - 1);
        for(int i = 0; i < count.length; i++){
        	result.add(count[i]);
        }
    	/*
        for (int i: nums) {
        	System.out.print(i + ", ");
        }
        System.out.println();
        
        for (int i: index) {
        	System.out.print(i + ", ");
        }
        System.out.println();
        */
    	return result;
    	
    }
    
    private static void mergesort(int[] nums, int[] index, int start, int end) {
    	
    	if (start < end) {
    		int mid = (start + end) / 2;
    	
    		mergesort(nums, index, start, mid);
    		mergesort(nums, index, mid+1, end);
    		
    		merge(nums, index, start, mid, end);
    	}
    }
    
    private static void merge(int[] nums, int[] index, int start, int mid, int end) {
    	int[] tmp = new int[end - start + 1];
		
		int i = start; 
		int j = mid + 1;
		int k = 0;
		int rightCount = 0;
		

		while (i <= mid && j <= end) {
			if (nums[index[j]] < nums[index[i]]) {
				tmp[k] = index[j];
				rightCount++;
				j++;
			} else {
				tmp[k] = index[i];
				count[index[i]] += rightCount;
				i++;
			}
				k++;
		}

		while (i <= mid) {
			tmp[k] = index[i];
			count[index[i]] += rightCount;
			k++;
			i++;
		}
		
		while (j <= end) {
			tmp[k] = index[j];
			k++;
			j++;
		}
		
		for (int m = start; m <= end; m++) {
			index[m] = tmp[m - start];
		}
    }
    
    public static void main(String[] args) {
    	long startTime = System.nanoTime();
		int[] nums = {5,2,6,1};
		
		System.out.println(countSmaller(nums));
    	long endTime = System.nanoTime();
    	System.out.println("Runtime: "+(endTime - startTime) + " ns");
	}
}
