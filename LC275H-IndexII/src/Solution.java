
public class Solution {
	
	/**
	 * Follow up for H-Index: What if the citations array is sorted in ascending order? 
	 * Could you optimize your algorithm?
	 * 
	 * Expected runtime complexity is in O(log n) and the input is sorted.
	 */
    public static int hIndex(int[] citations) {
        int n = citations.length;
        
        if (n == 0 || citations[n - 1] == 0) {
        	return 0;
        }
    	
    	int start = 0;
        int end = n - 1;
        
        while (start < end) {
        	int mid = start + (end - start)/2;
        	
        	if (citations[mid] >= n - mid) {
        		end = mid;
        	} else {
        		start = mid + 1;
        	}
        }
        
        return n - start;
    }
    
    
    public static void main(String[] args) {
		//int[] test = {0, 1, 3, 5, 6, 7};
    	int[] test = {0};
		
		System.out.println(hIndex(test));
	}
}
