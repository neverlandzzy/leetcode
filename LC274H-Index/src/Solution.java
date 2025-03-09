import java.util.Arrays;


public class Solution {
	/**
	 * Given an array of citations (each citation is a non-negative integer) of a researcher, 
	 * write a function to compute the researcher's h-index.
	 * 
	 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of 
	 * his/her N papers have at least h citations each, and the other N − h papers have no more than 
	 * h citations each."
	 * 
	 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in 
	 * total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher 
	 * has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations 
	 * each, his h-index is 3.
	 * 
	 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
	 */
	
	
	// Solution 1: Sort, Time: O(nlogn), Space: O(1)
	/*
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        int n = citations.length;
        
        for (int i = 0; i < n; i++) {
        	// 从i起，(n - i) papers has at least(n - i)
        	if (citations[i] >= n-i) {
        		return n-i;
        	}
        }
        
        return 0;
    }
    */
	
	// Solution 2: Bucket Sort, Time: O(n), Space: O(n)
	// e.g. 3, 0, 6, 1, 5
	// bucket: index: 0, 1, 2, 3, 4, 5
	//         val:   1, 1, 0, 1, 0, 2
    public static int hIndex(int[] citations) {
    	int n = citations.length;
    	int[] bucket = new int[n + 1];
    	
    	for (int c: citations) {
    		if  (c >= n) {
    			bucket[n]++;
    		} else {
    			bucket[c]++;
    		}
    	}
    	
    	int sum = 0;
    	for (int i = n; i >= 0; i--) {
    		sum += bucket[i];
    		if (sum >= i) {
    			return i;
    		}
    	}
    	
    	return 0;
    }
    
    public static void main(String[] args) {
		int[] test = {3,0,6,1,5,7};
		
    	//int[] test = {0, 1, 0,0 , 0};
    	//int[] test = {2,4,1,0,5};
		System.out.println(hIndex(test));
	}
}
