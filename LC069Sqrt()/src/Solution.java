
public class Solution {
	/*
	 * Implement int sqrt(int x).
	 * Compute and return the square root of x.
	 */
	/*
    public static int mySqrt(int x) {
        int lo = 1;
        int hi = x;
        int mid = 0;
        
        if (x < 2) return x;
        
        while (lo < hi) {
        	mid = (lo + hi)/2;
        	int div = x/mid;
        	if (div == mid) {
        		return mid;
        	}

        	if (div > mid) {
        		lo = mid+1;
        	} else if (div < mid) {
        		hi = mid;
        	} 

        }	
        
        return lo-1;
    }
    */
	
	// 找last position of i, where i * i <= x;
	public static int mySqrt(int x) {
		if (x == 0 || x == 1) {
			return x;
		}
		
		int start = 1; 
		int end = x;
		
		while (start + 1 < end) {
			int mid = start + (end - start) / 2;
			int div = x / mid;
			
			if (mid < div) {
				start = mid;
			} else if (mid > div){
				end = mid;
			} else {
				return mid;
			}
		}
		
		if (end < x / end) {
			return end;
		}
		
		return start;
	}
	
    
    public static void main(String[] args) {
		System.out.println(mySqrt(81));
		System.out.println(mySqrt(2147483647));
	}
}
