import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Solution {
	/*
	 * Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. 
	 * If there is a tie, the smaller elements are always preferred.
	 * 
	 * Example 1:
	 * Input: [1,2,3,4,5], k=4, x=3
	 * Output: [1,2,3,4]
	 * 
	 * Example 2:
	 * Input: [1,2,3,4,5], k=4, x=-1
	 * Output: [1,2,3,4]
	 * 
	 * Note:
	 * 	1. The value k is positive and will always be smaller than the length of the sorted array.
	 * 	2. Length of the given array is positive and will not exceed 10^4
	 * 	3. Absolute value of elements in the array and x will not exceed 10^4
	 * 
	 * UPDATE (2017/9/19):
	 * The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition 
	 * to get the latest changes.
	 */
	
    public static List<Integer> findClosestElements(int[] arr, int k, int x) {
    	int n = arr.length;
        int start = 0;
        int end = n - 1;
        int counter = k;
        
        int large = -1;
        List<Integer> result = new ArrayList<>();
        
        while (start < end) {
        	int mid = start + (end - start) / 2;
        	if (arr[mid] == x) {
        		large = mid;
        		break;
        	}
        	
        	if (arr[mid] < x) {
        		start = mid + 1;
        	} else {
        		end = mid;
        	}
        }

        if (large == -1) {
        	large = start;
        }
        
        int small = large - 1;
        
        while (small >= 0 && large < n && counter > 0) {
    		if (Math.abs(x - arr[small]) <= Math.abs(x - arr[large])) {
    			small--;
    		} else {
    			large++;
    		}
    		counter--;
        	
        }
        
        while (counter > 0) {
        	if (small < 0) {
        		large++;
        	} else {
        		small--;
        	}
        	counter--;
        }
        

        for (int i = small + 1; i < large; i++) {
            result.add(arr[i]);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
    	
    	int[] test1 = {1, 2, 3, 4, 5};
    	int[] test2 = {0, 1, 1, 1, 2, 3, 6, 7, 8, 9};
    	int[] test3 = {0, 0, 1, 2, 3, 3, 4, 7, 7, 8};
		System.out.println(findClosestElements(test1, 4, 3));
		System.out.println(findClosestElements(test1, 4, -1));
		System.out.println(findClosestElements(test2, 9, 4));
		System.out.println(findClosestElements(test3, 3, 5));
	}
}
