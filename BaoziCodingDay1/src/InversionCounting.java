import java.util.ArrayList;
import java.util.List;


public class InversionCounting {
	/*
	 * Given an array A, count the number of inversions in the array.
	 * 
	 * Formally speaking, two elements A[i] and A[j] form an inversion if A[i] > A[j] and i < j
	 * 
	 * Example:
	 * 
	 * A : [6, 3, 4]
	 * 
	 * Output : 2
	 * as the 2 inversions are (6, 3), (6, 4)
	 * The challenge part is can you do better than O(N^2)?
	 */
	
    public static int countInversions(List<Integer> a) {
        if (a == null || a.size() == 0) return 0;
        int[] tmp = new int[a.size()];
        
        return mergeSort(0, a.size() - 1, a, tmp);
    }
    
    private static int mergeSort(int low, int high, List<Integer> a, int[] tmp) {
    	int counter = 0;
    	
    	if (low < high) {
	    	int mid = low + (high - low)/2;
	    	
	    	counter += mergeSort(low, mid, a, tmp);
	    	counter += mergeSort(mid+1, low, a, tmp);
	    	counter += merge(low, mid, high, a, tmp);
    	}
    	return counter;
    }
    
    private static int merge(int low, int mid, int high, List<Integer> a, int[] tmp) {
    	int counter = 0;
    	  	
    	for (int i = low; i <= high; i++) {
    		tmp[i] = a.get(i);
    	}

    	int i = low;
    	int j = mid+1;
    	int k = low;
    	
    	while (i <= mid && j <= high) {
    		if (tmp[i] <= tmp[j]) {
    			a.set(k, tmp[i]);
    			i++;
    		} else {
    			a.set(k, tmp[j]);
    			counter += mid + 1 - i;
    			j++;
    		}
    		k++;
    	}
    	
        while (i <= mid) {
            a.set(k, tmp[i]);
            i++;
            k++;
        }
    	return counter;
    }
    /*


    public static int merge(int low, int medium, int high, int[] input, int[] helper) {
        int inversionCount = 0;

        for (int i = low; i <= high; i++)
            helper[i] = input[i];

        int i = low;
        int j = medium + 1;
        int k = low;

        while (i <= medium && j <= high) {
            if (helper[i] <= helper[j]) {
                input[k] = helper[i];
                i++;
            } else {
                input[k] = helper[j];
                // the number of elements in the first half which the j element needs to jump over.
                // there is an inversion between each of those elements and j.
                inversionCount += (medium + 1 - i);
                j++;
            }
            k++;
        }

        // finish writing back in the input the elements from the first part
        while (i <= medium) {
            input[k] = helper[i];
            i++;
            k++;
        }
        return inversionCount;
    }
     */
    
    public static void main(String[] args) {
		List<Integer> test = new ArrayList<Integer>();
		test.add(6);
		test.add(3);
		test.add(4);
		//test.add(3);
		
		System.out.println(countInversions(test));
	}
}
